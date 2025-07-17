package sea.scplus.consumer.common.util.cyber;

import javax.servlet.http.HttpServletRequest;

import sea.scplus.consumer.common.util.cyber.RuleChecker;

public class AccountUtil {
	
	public static String handleRequestParam(HttpServletRequest req, String key) {
		String result = "";
		try {
			result = (req.getParameter(key) == null ? "" : req
					.getParameter(key).trim());
			result = RuleChecker.Replace(result, "\"", "&quot;");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String trimToEmpty(String str) {
		return str == null ? "" : str.trim();
	}

	public static String toDB(String str) {
		String result = str;
		result = RuleChecker.Replace(result, "'", "''");
		result = RuleChecker.Replace(result, "--", "");
		return result;
	}

	// return 7-char zip code in format: "@#@ #@#" (with space, not without)
	public static String formatCanadianPostalCode(String s) {
		if (s != null && s.length() == 6) {
			return (s.substring(0, 3) + " " + s.substring(3, 6));
		} else {
			return (s);
		}
	}

	public static boolean isEmptyString(String s) {
		return (null == s || s.length() == 0);
	}

	public static boolean isValidPassword(String pwd, String alias) {
		boolean ok = !isEmptyString(pwd) && !pwd.equalsIgnoreCase(alias)
				&& pwd.length() >= 8 && containsAlpha(pwd)
				&& containsDigit(pwd) && !containsFourSeqDigits(pwd)
				&& !containsFourSeqAlphas(pwd);
		return (ok);
	}

	public static boolean isValidPassword(String pwd) {
		boolean ok = !isEmptyString(pwd) && pwd.length() >= 8
				&& containsAlpha(pwd) && containsDigit(pwd)
				&& !containsFourSeqDigits(pwd) && !containsFourSeqAlphas(pwd);
		return (ok);
	}

	public static boolean containsAlpha(String src) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		alphabet += alphabet.toUpperCase();
		char[] alpha = alphabet.toCharArray();
		// boolean hasAlpha = StringUtils.indexOfAny(src, alpha) > -1;
		// System.out.println( "containsAlpha(" + src + ")" + hasAlpha );
		boolean hasAlpha = indexOfAny(src, alpha) > -1;
		return (hasAlpha);
	}

	public static boolean containsDigit(String src) {
		char[] digit = "0123456789".toCharArray();
		boolean hasDigit = indexOfAny(src, digit) > -1;
		return (hasDigit);
	}

	public static boolean containsFourSeqDigits(String s) {
		if (null == s)
			return false;
		boolean flag = false;
		String forward = "0123456789";
		int nLength = 4;
		int stopAfter = forward.length() - nLength;
		String reverse = (new StringBuffer(forward)).reverse().toString();
		for (int n = 0; n <= stopAfter; n++) {
			String fw = forward.substring(n, n + nLength);
			String re = reverse.substring(n, n + nLength);
			if (s.indexOf(fw) > -1 || s.indexOf(re) > -1) {
				flag = true;
				break;
			}
		}
		return (flag);
	}

	public static boolean containsFourSeqAlphas(String s) {
		if (null == s)
			return false;
		boolean flag = false;
		String forward = "abcdefghijklmnopqrstuvwxyz";
		String reverse = (new StringBuffer(forward)).reverse().toString();
		String FORWARD = forward.toUpperCase();
		String REVERSE = (new StringBuffer(FORWARD)).reverse().toString();
		int nLength = 4;
		int stopAfter = forward.length() - nLength;
		for (int n = 0; n <= stopAfter; n++) {
			String fw = forward.substring(n, n + nLength);
			String re = reverse.substring(n, n + nLength);
			String FW = FORWARD.substring(n, n + nLength);
			String RE = REVERSE.substring(n, n + nLength);
			if (s.indexOf(fw) > -1 || s.indexOf(re) > -1 || s.indexOf(FW) > -1
					|| s.indexOf(RE) > -1) {
				flag = true;
				break;
			}
		}
		return (flag);
	}

	public static int indexOfAny(String src, char[] charSet) {
		if (!isEmptyString(src)) {
			for (int n = 0; n < charSet.length; n++) {
				char ch = charSet[n];
				if (src.indexOf(ch) > -1) {
					return (n);
				}
			}
		}
		return (-1);
	}

}