package sea.scplus.consumer.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.MessageSourceAccessor;

import sea.scplus.consumer.common.constant.CommonValue;
import sea.scplus.consumer.common.exception.InvalidSessionException;

/*
 * Parses header params from {request}
 */
public class RequestParamUtil 
{
	public static Integer getUserseqno(HttpServletRequest request, MessageSourceAccessor messageSourceAccessor) 
	{
		//String profId = request.getHeader(CommonValue.Constant.PROF_ID);		// causes CORS problems...
		String profId = request.getParameter(CommonValue.Constant.PROF_ID);
		
		Integer userSeqNo = UserAuthenticationUtil.getValidUserSeqNo(request, profId);
		
		if (userSeqNo == null) {
    		throw new InvalidSessionException(messageSourceAccessor.getMessage(CommonValue.Message.Error.SESSION_EXPIRED));
		}
		
		return userSeqNo;
	}
	
	public static String getLanguage(HttpServletRequest request) 
	{
		//String lang = request.getHeader(CommonValue.Constant.LANGUAGE);		// causes CORS problems...
		String lang = request.getParameter(CommonValue.Constant.LANGUAGE);
		
		if (!(CommonValue.Constant.FR).equals(lang) && !(CommonValue.Constant.EN).equals(lang)){
			lang = CommonValue.Constant.EN; // default to English
		}
		return lang;
	}
	
	
	
	public static String getSessionId(HttpServletRequest request) 
	{
		return request.getSession().getId();
	}

	
	public static String getIpAddress(HttpServletRequest request) 
	{
		String ipAddress = request.getRemoteAddr();
		if(ipAddress==null || "".equals(ipAddress)) ipAddress = request.getHeader("x-forwarded-for");
		
		return ipAddress;
	}

	
}