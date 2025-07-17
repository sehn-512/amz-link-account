package sea.scplus.consumer.common.util;

import java.util.regex.Pattern;

public final class PasswordValidator 
{
	/* static class */
	private PasswordValidator() {}
	
	/*
	 * i.e. Password must have at least 1 letter
	 * 
	 * params
	 *     txt : the string to check
	 */
	public static boolean hasCharacters(String txt)
	{
		Pattern hasCharacters = Pattern.compile("[a-zA-Z]");
		
		return hasCharacters.matcher(txt).find();
	}
	
	/*
	 * i.e. Password must have at least 1 number
	 * 
	 * params
	 *     txt : the string to check
	 */
	public static boolean hasNumbers(String txt)
	{
		Pattern hasNumbers = Pattern.compile("\\d");
		
		return hasNumbers.matcher(txt).find();
	}

	/*
	 * i.e. Password must have at least 1 capital letter
	 * 
	 * params
	 *     txt : the string to check
	 */
	public static boolean hasCapitalCharacters(String txt)
	{
		Pattern hasCapitalCharacters = Pattern.compile("[A-Z]");
		
		return hasCapitalCharacters.matcher(txt).find();
	}
	
	/*
	 * i.e. Password may not contain len or more repetitions of the same character
	 * 
	 * params
	 *     txt : the string to check
	 *     len : length to check on
	 */
	public static boolean checkForSameCharacters(String txt, int len)
	{
		int cnt = 0;
		boolean success = false;
		
		for (int i = 0; i < txt.length()-1; i++) {
			if (i != txt.length()) {
				if (txt.charAt(i) == txt.charAt(i+1)) {
					++cnt;
				} else {
					cnt =0;
				}
				
				if (cnt == (len-1)) {
					success = true;
					break;
				}
			}
		}
		
		return success;
	}

	/*
	 * i.e. Password may not contain len or more consecutive characters
	 * 
	 * params
	 *     txt : the string to check
	 *     len : length to check on
	 */
	public static boolean checkForAscendingOrDescendingPart(String txt, int len)
	{
	    for (int i = 0; i <= txt.length() - len; ++i) {
	        boolean success = true;
	        char c = txt.charAt(i);
	        for (int j = 1; j < len; ++j) {
	            if (((char) c + j) != txt.charAt(i + j)) {
	                success = false;
	                break;
	            }
	        }
	        
	        if (success) return true;

	        success = true;

	        for (int j = 1; j < len; ++j) {
	            if (((char) c - j) != txt.charAt(i + j)) {
	                success = false;
	                break;
	            }
	        }
	        
	        if (success) return true;
	    }
	    return false;
	}
	
}
