package sea.scplus.consumer.common.filter;


import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.apache.ibatis.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 	1.0
 * @author
 */
@WebFilter(urlPatterns="/*")
public class LogFilter implements Filter {
	
	//private static final Logger logger = LoggerFactory.getLogger(LogFilter.class);
	private final Log logger = LogFactory.getLog(getClass());
	
	public void init(FilterConfig arg0) throws ServletException {
	}   
	
	public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws ServletException, java.io.IOException {
		
		String requestURI = ((HttpServletRequest)request).getRequestURI();
		if( !requestURI.endsWith(".png") && !requestURI.endsWith(".gif") && !requestURI.endsWith(".js") && !requestURI.endsWith(".css")){
			logger.info( toString((HttpServletRequest)request) );
		}
		chain.doFilter(request, response);
    }

    private String toString(HttpServletRequest request) {
    	boolean doShow = true;
    	
//    	String sessionId = request.getSession().getId();
//    	String userId = null;
    	String ipAddress = request.getHeader("Proxy-Client-IP")!=null?request.getHeader("Proxy-Client-IP"):request.getRemoteAddr();
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
        
        if (true)
        {
            sbMessage.append(" [Parameters]").append("\n");
            String szName;
            String[] arrValue;
            int nIndex = 1;
            Enumeration<String> e = request.getParameterNames();
            Hashtable ht = new Hashtable();
            StringBuffer szValue = new StringBuffer();
            while (e.hasMoreElements()){
                szName = (String) e.nextElement();
                try{
                    arrValue = request.getParameterValues(szName);
                    String value = request.getParameter(szName);
                    
                    if(parameter==null){
                    	parameter = new StringBuffer();
                    	parameter.append(szName).append("=").append(value);
                    }else{
                    	parameter.append("&").append(szName).append("=").append(value);
                    }
                }catch (Exception ee){
                    continue;
                }
                szValue = new StringBuffer();
                if (arrValue != null && arrValue.length > 0)
                {
                    int nValue = arrValue.length;
                    for (int i = 0; i < nValue; i++)
                    {
                    	
						szValue.append("[").append(arrValue[i]).append("]").append(i < nValue - 1 ? "," : "");
                        
                    }
                }
                ht.put(szName, szValue.toString());
            }
            String sortStr = hashKeySort(ht);
            sbMessage.append(sortStr);
            
            if (true)
            {
                sbMessage.append(" [Attribute]").append("\n");
                Object objValue;
                nIndex = 1;
                e = request.getAttributeNames();
                while (e.hasMoreElements())
                {
                    szName = (String) e.nextElement();
                    objValue = request.getAttribute(szName);
                    if (nIndex < 10)
                        sbMessage.append("    [ ").append(nIndex++).append("] ").append(szName).append(" : ");
                    else
                        sbMessage.append("    [").append(nIndex++).append("] ").append(szName).append(" : ");
                    if (objValue != null)
                    {
                        //sbMessage.append("[").append(((objValue instanceof String) ? (String) objValue : objValue.getClass().toString())).append("]");
                        if( objValue instanceof String ){
                        	sbMessage.append( (String)objValue );
                        }else if( objValue instanceof Integer ){
                        	sbMessage.append( ((Integer)objValue).toString() );
                        }else{
                        	sbMessage.append( objValue.getClass().toString() );
                        }
                    }
                    sbMessage.append("\n");
                }
            }
        }

        if (true)
        {
            sbMessage.append(" [Header]").append("\n");
            int nIndex = 1;
            try{
	    		Enumeration enu = request.getHeaderNames();
	    		if(enu.hasMoreElements()){
	    			while(enu.hasMoreElements()){
	    				String name = (String)enu.nextElement();
	    				String value = request.getHeader(name);
	    				if( nIndex<10 ){ 
	    					sbMessage.append("    [ ").append(nIndex++).append("] ").append(name).append(" : ");
	    				}else{
	    					sbMessage.append("    [").append(nIndex++).append("] ").append(name).append(" : ");
	    				}
						if (value != null){
							sbMessage.append("[").append(value).append("]");
						}
						sbMessage.append("\n");
	    			}
	    		}else{
	    			sbMessage.append("   There is no header attributes.");
	    		}
            }catch(Exception e){
    			logger.error(e.getMessage(), e);
    		}
        } 

        if (true)
        {
            sbMessage.append(" [Sessions]").append("\n");
            int nIndex = 1;
            try{
	            HttpSession session = request.getSession();
	            sbMessage.append("    [ 0] Session ID : [").append(session.getId()).append("]\n");
	            
	    		Enumeration enu = session.getAttributeNames();
	    		if(enu.hasMoreElements()){
	    			while(enu.hasMoreElements()){
	    				String name = (String)enu.nextElement();
	    				Object attribute = (Object)session.getAttribute(name);
	    				if( nIndex<10 ){ 
	    					sbMessage.append("    [ ").append(nIndex++).append("] ").append(name).append(" : ");
	    				}else{
	    					sbMessage.append("    [").append(nIndex++).append("] ").append(name).append(" : ");
	    				}
						if (attribute != null){
							if(attribute instanceof String){
								sbMessage.append("[").append(attribute.toString()).append("]");
							}else{
								sbMessage.append("[").append(attribute.toString()).append("](").append(attribute.getClass().toString()).append(")");
							}
						}
						sbMessage.append("\n");
	    			}
	    		}else{
	    			sbMessage.append("   There is no session attributes.");
	    		}
            }catch(Exception e){
    			logger.error(e.getMessage(), e);
    		}
        } 
        sbMessage.append("\n#######################################################################################################################").append("\n");
        
        if( doShow )
        	return sbMessage.toString();
        else 
        	return "";
    }

   
    private static String hashKeySort(Hashtable hTable)
    {
        StringBuffer sb = new StringBuffer();
        SortedMap m = Collections.synchronizedSortedMap(new TreeMap(hTable));
        Set s = m.keySet();
        synchronized (m)
        { // Synchronizing on m, not s!
            Iterator i = s.iterator(); // Must be in synchronized block
            int nIndex = 0;
            while (i.hasNext())
            {
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

 
    
	public void destroy() {
		// TODO Auto-generated method stub		
	}

 
}
