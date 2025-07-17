package sea.scplus.consumer.common.filter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;

import sea.scplus.consumer.common.util.SessionUtil;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.debug("[AuthInterceptor.preHandle]");

        boolean flag = true;
        
        LOGGER.debug("============= AuthInterceptor preHandle Start ====================================");
        
        String PAGE_URL = request.getRequestURI();
        String Referer_page = request.getHeader("Referer");
        String controllerName = "";
        String USER_ID = "";
        
        LOGGER.info("PAGE_URL:["+PAGE_URL+"]");
        LOGGER.info("Referer_page:["+Referer_page+"]");
        
        if ( "/WEB-INF/views/cepos/logout".contains(PAGE_URL) || 
        		"/WEB-INF/views/cepos/login".contains(PAGE_URL) || 
        		"/WEB-INF/views/cepos/login_error.jsp".contains(PAGE_URL) ||
        		"/WEB-INF/views/login.jsp".contains(PAGE_URL)
        		) {
    		USER_ID = "After logout";
    	} else {
    		
    		ServletRequestAttributes serviceRequestAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    		HttpSession httpSession = serviceRequestAttr.getRequest().getSession();
    		
    		if ( httpSession.getAttribute("ssoUser") == null ) {
    			LOGGER.info("============= AuthInterceptor preHandle SessionUtil  ====================================");
    			
    	        LOGGER.info("USER_ID:["+USER_ID+"]");
    			
    	        USER_ID = "User id is empty";
    	        
    	        Cookie cookie = new Cookie("redirect_url", PAGE_URL);
    			cookie.setPath("/index.html");
    	        cookie.setMaxAge(-1);
    			response.addCookie(cookie);
    			
    			response.sendRedirect("/index.html");
    			return false;
    			
    		} else {
    			USER_ID = SessionUtil.getSessionUserId();
    			
    			Enumeration<String> e = request.getParameterNames();
    			while(e.hasMoreElements()) {
    				String key = e.nextElement();
    	            String values[] = request.getParameterValues(key);
    	            if(values != null) {
    	            	if(values.length == 1) {
    	            		request.setAttribute(key, cleanXSS(values[0]));
    	            	} else {
    	            		for(int i=0, s=values.length; i<s; i++) {
    	            			values[i] = cleanXSS(values[i]);
    	            		}
    	            		request.setAttribute(key, values);
    	            	}
    	            }
    			}
    			
    		}
    	}
        
        LOGGER.debug("==============AuthInterceptor preHandle End ====================================================");
        
        return flag;
    }
    

	/**
	     * Cross-site processing xss character escape
	*
	* @param value
	* @return
	*/
	public String cleanXSS(String value) {
		if (value != null) {

				Pattern scriptPattern = Pattern.compile("<script(.*?)>(.*?)</script(.*?)>", Pattern.CASE_INSENSITIVE);

				value = scriptPattern.matcher(value).replaceAll("");

				scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\'(.*?)\\'",
						Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

				value = scriptPattern.matcher(value).replaceAll("");

				scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
						Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

				value = scriptPattern.matcher(value).replaceAll("");

				scriptPattern = Pattern.compile("</?script(.*?)>", Pattern.CASE_INSENSITIVE);

				value = scriptPattern.matcher(value).replaceAll("");

				scriptPattern = Pattern.compile("</?html:(\\s)*script(.*?)>",
						Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

				value = scriptPattern.matcher(value).replaceAll("");

				scriptPattern = Pattern.compile("eval\\((.*?)\\)",
						Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

				value = scriptPattern.matcher(value).replaceAll("");

				scriptPattern = Pattern.compile("expression\\((.*?)\\)",
						Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

				value = scriptPattern.matcher(value).replaceAll("");

				scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);

				value = scriptPattern.matcher(value).replaceAll("");

				scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);

				value = scriptPattern.matcher(value).replaceAll("");

				scriptPattern = Pattern.compile(
						"autofocus|FSCommand(.*?)=|on(Abort|Activate|AfterPrint|AfterUpdate|BeforeActivate|BeforeCopy|BeforeCut|BeforeDeactivate|BeforeEditFocus|BeforePaste|BeforePrint|BeforeUnload|BeforeUpdate|Begin|Blur|Bounce|CellChange|Change|Click|ContextMenu|ControlSelect|Copy|DataAvailable|Cut|DataSetChanged|DataSetComplete|DblClick|Deactivate|DragDragEnd|DragLeave|DragEnter|DragOver|DragDrop|DragStart|Drop|End|Error|ErrorUpdate|FilterChange|Finish|Focus|FocusIn|FocusOut|HashChange|Help|Input|KeyDown|KeyPress|KeyUp|LayoutComplete|Load|LoseCapture|MediaComplete|MediaError|Message|MouseDown|MouseEnter|MouseLeave|MouseMove|MouseOut|MouseOver|MouseUp|MouseWheel|Move|MoveEnd|MoveStart|Offline|line|OutOfSync|Paste|Pause|PopState|Progress|PropertyChange|ReadyStateChange|Redo|Repeat|Reset|Resize|ResizeEnd|ResizeStart|Resume|Reverse|RowsEnter|RowExit|RowDelete|RowInserted|Scroll|Seek|Select|SelectiChange|SelectStart|Start|Stop|Storage|SyncRestored|Submit|TimeError|TrackChange|Undo|Unload|URLFlip)(.*?)=|seekSegmentTime",
						Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

				value = scriptPattern.matcher(value).replaceAll("");

				value = value.replaceAll("<", "&lt;");
				value = value.replaceAll(">", "&gt;");

				value = value.replaceAll("!", "&#33;");
				value = value.replaceAll("\"", "&#34;");
				value = value.replaceAll("'", "&#39;");
				value = value.replaceAll("\\+", "&#43;");
				value = value.replaceAll("=", "&#61;");
		}

		return value;
	}
    
    private boolean isAjaxRequest(HttpServletRequest request) {
        final String header = request.getHeader("AJAX");
        if( header != null && header.equals("true") ) {
            return true;
        }
        return false;
    }
    
    /**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		LOGGER.debug("[AuthInterceptor.afterCompletion]");
    	
    	LOGGER.info("============= AuthInterceptor afterCompletion Start ====================================");
        LOGGER.info("==============AuthInterceptor afterCompletion End ====================================================");
	}
	
	private String requestToString(HttpServletRequest request) {
        boolean doShow = true;

//    	String sessionId = request.getSession().getId();
        String userId = null;
        String ipAddress = request.getHeader("Proxy-Client-IP") != null ? request.getHeader("Proxy-Client-IP") : request.getRemoteAddr();
        String requestURI = request.getRequestURI();
        StringBuffer parameter = null;
        //String serverName = SeaProperty.getPropertyValue("server.name");
        String serverPort = String.valueOf(request.getServerPort());

        StringBuffer sbMessage = new StringBuffer();
        sbMessage.append("\n#######################################################################################################################").append("\n");
        sbMessage.append("[ Request ]").append("\n");
        sbMessage.append("    ServerPort    : ").append(request.getServerPort()).append("\n");
        sbMessage.append("    RemoteAddr    : ").append(request.getRemoteAddr()).append("\n");
        sbMessage.append("    Client-IP     : ").append(ipAddress).append("\n");
        sbMessage.append("    ContentType   : ").append(request.getContentType()).append("\n");
        sbMessage.append("    RequestURI    : ").append(requestURI).append("\n");
        sbMessage.append("    PathInfo      : ").append(request.getPathInfo()).append("\n");
        sbMessage.append("    ContentLength : ").append(request.getContentLength()).append("\n");
        sbMessage.append("    Method        : ").append(request.getMethod()).append("\n");

        if (true) {
            sbMessage.append(" [Parameters]").append("\n");
            String szName;
            String[] arrValue;
            int nIndex = 1;
            Enumeration<String> e = request.getParameterNames();
            Hashtable ht = new Hashtable();
            StringBuffer szValue = new StringBuffer();
            while (e.hasMoreElements()) {
                szName = (String) e.nextElement();
                try {
                    arrValue = request.getParameterValues(szName);
                    String value = request.getParameter(szName);

                    if (parameter == null) {
                        parameter = new StringBuffer();
                        parameter.append(szName).append("=").append(value);
                    } else {
                        parameter.append("&").append(szName).append("=").append(value);
                    }
                } catch (Exception ee) {
                    continue;
                }
                szValue = new StringBuffer();
                if (arrValue != null && arrValue.length > 0) {
                    int nValue = arrValue.length;
                    for (int i = 0; i < nValue; i++) {

                        szValue.append("[").append(arrValue[i]).append("]").append(i < nValue - 1 ? "," : "");

                    }
                }
                ht.put(szName, szValue.toString());
            }
            String sortStr = hashKeySort(ht);
            sbMessage.append(sortStr);

            if (true) {
                sbMessage.append(" [Attribute]").append("\n");
                Object objValue;
                nIndex = 1;
                e = request.getAttributeNames();
                while (e.hasMoreElements()) {
                    szName = (String) e.nextElement();
                    objValue = request.getAttribute(szName);
                    if (nIndex < 10)
                        sbMessage.append("    [ ").append(nIndex++).append("] ").append(szName).append(" : ");
                    else
                        sbMessage.append("    [").append(nIndex++).append("] ").append(szName).append(" : ");
                    if (objValue != null) {
                        //sbMessage.append("[").append(((objValue instanceof String) ? (String) objValue : objValue.getClass().toString())).append("]");
                        if (objValue instanceof String) {
                            sbMessage.append((String) objValue);
                        } else if (objValue instanceof Integer) {
                            sbMessage.append(((Integer) objValue).toString());
                        } else {
                            sbMessage.append(objValue.getClass().toString());
                        }
                    }
                    sbMessage.append("\n");
                }
            }
        }

        if (true) {
            sbMessage.append(" [Header]").append("\n");
            int nIndex = 1;
            try {
                Enumeration enu = request.getHeaderNames();
                if (enu.hasMoreElements()) {
                    while (enu.hasMoreElements()) {
                        String name = (String) enu.nextElement();
                        String value = request.getHeader(name);
                        if (nIndex < 10) {
                            sbMessage.append("    [ ").append(nIndex++).append("] ").append(name).append(" : ");
                        } else {
                            sbMessage.append("    [").append(nIndex++).append("] ").append(name).append(" : ");
                        }
                        if (value != null) {
                            sbMessage.append("[").append(value).append("]");
                        }
                        sbMessage.append("\n");
                    }
                } else {
                    sbMessage.append("   There is no header attributes.");
                }
            } catch (Exception e) {
            	LOGGER.error(e.getMessage(), e);
            }
        }

        if (true) {
            sbMessage.append(" [Sessions]").append("\n");
            int nIndex = 1;
            try {
                HttpSession session = request.getSession();
                sbMessage.append("    [ 0] Session ID : [").append(session.getId()).append("]\n");

                Enumeration enu = session.getAttributeNames();
                if (enu.hasMoreElements()) {
                    while (enu.hasMoreElements()) {
                        String name = (String) enu.nextElement();
                        Object attribute = (Object) session.getAttribute(name);
                        if (nIndex < 10) {
                            sbMessage.append("    [ ").append(nIndex++).append("] ").append(name).append(" : ");
                        } else {
                            sbMessage.append("    [").append(nIndex++).append("] ").append(name).append(" : ");
                        }
                        if (attribute != null) {
                            if (attribute instanceof String) {
                                sbMessage.append("[").append(attribute.toString()).append("]");
                            } else {
                                sbMessage.append("[").append(attribute.toString()).append("](").append(attribute.getClass().toString()).append(")");
                            }
                        }
                        sbMessage.append("\n");

                        //if( "singleId".equals(name) && "d2.han".equals(attribute.toString()) ) doShow = false;
                        if ("singleId".equals(name) || "userId".equals(name)) {
                            userId = attribute.toString();
                        }
                    }
                } else {
                    sbMessage.append("   There is no session attributes.");
                }
            } catch (Exception e) {
            	LOGGER.error(e.getMessage(), e);
            }
        }
        sbMessage.append("\n#######################################################################################################################").append("\n");

        if (doShow)
            return sbMessage.toString();
        else
            return "";
    }
	
	private static String hashKeySort(Hashtable hTable) {
        StringBuffer sb = new StringBuffer();
        SortedMap m = Collections.synchronizedSortedMap(new TreeMap(hTable));
        Set s = m.keySet();
        synchronized (m) { // Synchronizing on m, not s!
            Iterator i = s.iterator(); // Must be in synchronized block
            int nIndex = 0;
            while (i.hasNext()) {
                nIndex++;
                String key = (String) i.next();
                if (nIndex < 10)
                    sb.append("    [ ").append(nIndex).append("] ").append(key).append(" : ");
                else
                    sb.append("    [").append(nIndex).append("] ").append(key).append(" : ");
                sb.append(hTable.get(key));
                sb.append("\n");
            }
        }
        return sb.toString();
    }

}
