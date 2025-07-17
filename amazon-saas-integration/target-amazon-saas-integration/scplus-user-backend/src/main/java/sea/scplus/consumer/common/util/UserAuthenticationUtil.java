package sea.scplus.consumer.common.util;

import javax.servlet.http.HttpServletRequest;

import sea.scplus.consumer.common.util.cyber.Encrypt;

@SuppressWarnings("all")
public class UserAuthenticationUtil 
{
	public static Integer getValidUserSeqNo(HttpServletRequest request, String profId) {
		String userSeqNoInSession = null;
		String userSeqNoInCookie = null;
		if(request != null){
			String uValue = (String)request.getSession().getAttribute("uvalue");
			if(uValue != null ){
				userSeqNoInSession = Encrypt.DecodeBySType(uValue);
			}
		}
		if(profId != null){
			userSeqNoInCookie = Encrypt.DecodeBySType(profId);
		}
		if(isValidSession(userSeqNoInSession, userSeqNoInCookie)) {
			return Integer.valueOf(userSeqNoInCookie);
		} else {
			return null;
		}
	}
	
	private static boolean isValidSession(String userSeqNoInSession, String userSeqNoInCookie) {
		String profile = System.getProperty("spring.profiles.active");
		if( "staging".equals(profile) || "dev".equals(profile) || "local".equals(profile) ){
			// for now, hard-coded for prof_id : ddb9b3352227bbb3aa72752438805f9485a7848339d9f54d0067d4966d972fc2
			if ("382088".equals(userSeqNoInCookie) || "382106".equals(userSeqNoInCookie) || "427950".equals(userSeqNoInCookie) || "211470634".equals(userSeqNoInCookie) || "382107".equals(userSeqNoInCookie) || "103507628".equals(userSeqNoInCookie) || "103497002".equals(userSeqNoInCookie) || "101178489".equals(userSeqNoInCookie) || "381913".equals(userSeqNoInCookie) || "421003".equals(userSeqNoInCookie) || "428296".equals(userSeqNoInCookie) || "381780".equals(userSeqNoInCookie) || "428299".equals(userSeqNoInCookie) || "428315".equals(userSeqNoInCookie) || "101598716".equals(userSeqNoInCookie) || "102336522".equals(userSeqNoInCookie) || "102336769".equals(userSeqNoInCookie) || "100724086".equals(userSeqNoInCookie) || "206274678".equals(userSeqNoInCookie) || "102374468".equals(userSeqNoInCookie) || "428301".equals(userSeqNoInCookie) || "101228579".equals(userSeqNoInCookie) || "100911751".equals(userSeqNoInCookie)) {
				return true;
			}
			// end of hard-coding
		}
		
		
		if(userSeqNoInSession != null && userSeqNoInSession.equals(userSeqNoInCookie)) {
			return true;
		} else {
			
			// delete cookie prof_id
			
			
			return false;
		}
		
	}
}

