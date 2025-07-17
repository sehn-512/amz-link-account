package sea.scplus.consumer.common.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import sea.scplus.consumer.controller.RESTCommonController;
import sea.scplus.consumer.vo.RequestSerialNoValidation;
import sea.scplus.consumer.vo.ResponseSpcValidation;
import sea.scplus.consumer.vo.SSOUser;

public class SessionUtil implements HttpSessionListener {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(RESTCommonController.class);
	
	private static Hashtable loginSessionList = new Hashtable();

	public static SSOUser currentSSOUser(){
		ServletRequestAttributes serviceRequestAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession httpSession = serviceRequestAttr.getRequest().getSession();
		
		LOGGER.debug(httpSession.getAttribute("ssoUser").toString());
		
		return (SSOUser)httpSession.getAttribute("ssoUser");
	}
	
	
	public static String getSessionUserId() {
		return currentSSOUser().getLoginId();
	}
	
	public static String getSessionUserName() {
		return currentSSOUser().getUserName();
	}
	
	public static String getSessionUserLastName() {
		return currentSSOUser().getLast_name();
	}
	
	public static String getSessionUserGroup() {
		return currentSSOUser().getUser_group();
	}
	
	public static String getSessionUserType() {
		return currentSSOUser().getUserType();
	}
	
	public static String getSessionEmail() {
		return currentSSOUser().getEmail();
	}
	
	public static String getSessionAuthorityGroupCode() {
		return currentSSOUser().getUser_group();
	}
	
	public static String getSessionAuthorityGroupName() {
		return currentSSOUser().getUser_group_name();
	}
	
	
	public static RequestSerialNoValidation currentUser(){
		ServletRequestAttributes serviceRequestAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession httpSession = serviceRequestAttr.getRequest().getSession();
		
		LOGGER.debug(httpSession.getAttribute("ssoUser").toString());
		
		return (RequestSerialNoValidation)httpSession.getAttribute("ssoUser");
	}
	
	public static List<ResponseSpcValidation> currentProduct(){
		ServletRequestAttributes serviceRequestAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession httpSession = serviceRequestAttr.getRequest().getSession();
		
		LOGGER.debug(httpSession.getAttribute("product").toString());
		
		return (List<ResponseSpcValidation>)httpSession.getAttribute("product");
	}
	
	/**
     * session.setAttribute 실행 되는 순간 같은 sessionId 일경우 1회만 실행
     * @param httpSessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    	ServletRequestAttributes serviceRequestAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = serviceRequestAttr.getRequest().getSession();
		
		LOGGER.debug("sessionCreated -> {}", session.getAttribute("serialNo"));
    }
    
    
    /**
     * session 이 소멸되는 시점에 실행, 기본 단위는 초(1분 미만은 설정할 수 없음)
     * @param httpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
    	LOGGER.debug("sessionDestroyed 실행");
    	ServletRequestAttributes serviceRequestAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = serviceRequestAttr.getRequest().getSession();

        String serialNo = (String) session.getAttribute("serialNo");

        //로그아웃 유저 삭제
        synchronized(loginSessionList){
            loginSessionList.remove(session.getId());
        }

        if(serialNo != null){
            this.updateUserCloseTime(serialNo);
        }

        currentSessionList();
    }
	
	/**
     * session 생성
     * @param request
     * @param value
     */
    public static void setSession(String serialNo){
    	LOGGER.debug("setSession 실행");
    	
    	boolean flag = isSerialNo(serialNo);
    	if ( flag == true ) {
    		LOGGER.debug("remove session due to prev session info ");
    	}
    	
    	ServletRequestAttributes serviceRequestAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = serviceRequestAttr.getRequest().getSession();
        session.setAttribute("serialNo", serialNo);
        session.setMaxInactiveInterval(1800);

        //HashMap에 Login에 성공한 유저 담기
        synchronized(loginSessionList){
            loginSessionList.put(session.getId(), session);
        }
        currentSessionList();
    }
	
	/**
     * 현제 HashTable에 담겨 있는 유저 리스트, 즉 session list
     */
    private static void currentSessionList(){
        Enumeration elements = loginSessionList.elements();
        
        ServletRequestAttributes serviceRequestAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = serviceRequestAttr.getRequest().getSession();
		
		String serialNo = (String)session.getAttribute("serialNo");
		
        while (elements.hasMoreElements()){
            session = (HttpSession)elements.nextElement();

            LOGGER.debug("currentSessionUserList -> serialNo {} ", serialNo);
            //log.info("currentSessionUserList -> sessionId {} ", session.getId());
            //log.info("currentSessionUserList -> hashtable SessionList {} ", loginSessionList.get(session.getId()));
        }
    }
    
	/**
     * 현재 로그인한 유저가 이미 존재 하는지 확인
     * @param request
     * @param loginUserId
     * @return boolean
     */
    public static boolean isSerialNo(String logInSerialNo){
    	ServletRequestAttributes serviceRequestAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    	HttpServletRequest request = serviceRequestAttr.getRequest();
		
    	Enumeration elements = loginSessionList.elements();
        HttpSession session = null;
        while (elements.hasMoreElements()){
            session = (HttpSession)elements.nextElement();
            String serialNo = (String)session.getAttribute("serialNo");
            if(logInSerialNo.equals(serialNo) && (!session.getId().equals(request.getSession().getId()))){
                
            	session.removeAttribute("serialNo");
                session.invalidate();
                
            	//로그아웃 유저 삭제
                synchronized(loginSessionList){
                    loginSessionList.remove(session.getId());
                }
            	
            	return true;
            }
        }
        return false;
    }
    
    /**
     * session 삭제
     * 세션이 remove 되면 destroyed 함수 실행
     * @param request
     */
    public void removeSession(HttpServletRequest request){
    	LOGGER.debug("removeSession 실행");

		HttpSession session = request.getSession();
    	
        String serialNo = (String)session.getAttribute("serialNo");

        session.removeAttribute("serialNo");
        session.invalidate();

        if(serialNo != null){
            this.updateUserCloseTime(serialNo);
        }
    }
    
    /**
     * 유저 나간 시간 업데이트
     * @param userId
     */
    private void updateUserCloseTime(String userId) {
    	LOGGER.debug("updateUserCloseTime {} ", userId);
        //호출부에서 NULL 검사
        //업데이트 로직
    }
    
}