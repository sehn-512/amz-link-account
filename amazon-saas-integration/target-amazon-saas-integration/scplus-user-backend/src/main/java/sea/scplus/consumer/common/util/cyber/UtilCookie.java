
package sea.scplus.consumer.common.util.cyber;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UtilCookie {

	private UtilCookie() {
	}

	final public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(name)) {
					// System.out.println( "UtilCookie.getCookie( search target
					// = " + name + ") is found! <<<< " + cookies[i].getName() +
					// "=" + cookies[i].getValue() );
					return (cookies[i]);
				}
			}
		}
		// System.out.println( "UtilCookie.getCookie( search target = " + name +
		// ") <<<< not found." );
		return null;
	}

	final public static String getCookieValue(HttpServletRequest request, String name) {
		String rtnValue = null;
		Cookie returnCookie = getCookie(request, name);
		if (returnCookie != null)
			rtnValue = returnCookie.getValue();
		return rtnValue;
	}

	final public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name,
			String value, int expire) {
		Cookie targetCookie = getCookie(request, name);
		if (targetCookie == null)
			targetCookie = new Cookie(name, value);
		else
			targetCookie.setValue(value);
		targetCookie.setMaxAge(expire * 24 * 60 * 60);
		response.addCookie(targetCookie);
	}

	final public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name,
			String value, int expire, String path) {
		Cookie targetCookie = getCookie(request, name);
		if (targetCookie == null)
			targetCookie = new Cookie(name, value);
		else
			targetCookie.setValue(value);
		targetCookie.setMaxAge(expire * 24 * 60 * 60);
		targetCookie.setPath(path);
		response.addCookie(targetCookie);
	}

	final public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name,
			String value, int expire, String path, String domain) {
		Cookie targetCookie = getCookie(request, name);
		if (targetCookie == null)
			targetCookie = new Cookie(name, value);
		else
			targetCookie.setValue(value);
		targetCookie.setMaxAge(expire * 24 * 60 * 60);
		targetCookie.setPath(path);
		targetCookie.setDomain(domain);
		response.addCookie(targetCookie);
	}

	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
			int expire, String path, String domain, Boolean isSecure) {
		Cookie targetCookie = getCookie(request, name);
		if (targetCookie == null)
			targetCookie = new Cookie(name, value);
		else
			targetCookie.setValue(value);
		targetCookie.setMaxAge(expire * 24 * 60 * 60);
		targetCookie.setPath(path);
		targetCookie.setDomain(domain);
		targetCookie.setSecure(isSecure);
		response.addCookie(targetCookie);
	}

	final public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		Cookie targetCookie = getCookie(request, name);
		if (targetCookie != null) {
			targetCookie.setMaxAge(0);
			response.addCookie(targetCookie);
		}
	}

	final public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name,
			String domain, String path) {
		Cookie cookie = new Cookie(name, null);
		cookie.setPath(path);
		cookie.setMaxAge(0);
		cookie.setDomain(domain);
		response.addCookie(cookie);
	}

	final public static void deleteAllCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookies[] = request.getCookies();
		Cookie returnCookie = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				returnCookie = cookies[i];
				returnCookie.setMaxAge(0);
				response.addCookie(returnCookie);
			}

		}
	}

	final public static String list(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer("Cookie List:\n");
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				sb.append(cookies[i].getName() + "=" + cookies[i].getValue() + "\n");
			}
		}
		return sb.toString();
	}
}