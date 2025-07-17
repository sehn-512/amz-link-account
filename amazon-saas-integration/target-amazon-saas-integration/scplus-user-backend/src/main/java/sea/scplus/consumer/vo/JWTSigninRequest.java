package sea.scplus.consumer.vo;

import java.io.Serializable;

public class JWTSigninRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public JWTSigninRequest()
	{
		
	}

	public JWTSigninRequest(String username, String password) {
		this.setSystemid(username);
		this.setPassword(password);
	}
	
	private String systemid;
	private String password;
	
	public String getSystemid() {
		return systemid;
	}

	public void setSystemid(String systemid) {
		this.systemid = systemid;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
