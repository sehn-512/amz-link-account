package sea.scplus.consumer.vo;

import java.io.Serializable;

public class SSOUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String userSeqNo;
	private String loginId;
	private String email;
	private String userName;
	private String userType;
	private String userType_name;
	private String user_group;
	private String first_name;
	private String last_name;
	private String seq;
	private String base_date;
	private String dept_name;
	private String position_name;
	private String user_group_name;
	
	public String getUserSeqNo() {
		return userSeqNo;
	}
	public void setUserSeqNo(String userSeqNo) {
		this.userSeqNo = userSeqNo;
	}
	public String getLoginId() {
		if (loginId == null) {
			loginId = "";
		}
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserType_name() {
		return userType_name;
	}
	public void setUserType_name(String userType_name) {
		this.userType_name = userType_name;
	}
	public String getUser_group() {
		return user_group;
	}
	public void setUser_group(String user_group) {
		this.user_group = user_group;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		if (last_name == null) {
			last_name = "";
		}
		this.last_name = last_name;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	public String getBase_date() {
		return base_date;
	}
	public void setBase_date(String base_date) {
		this.base_date = base_date;
	}
	
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getPosition_name() {
		return position_name;
	}
	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	
	public String getUser_group_name() {
		return user_group_name;
	}
	public void setUser_group_name(String user_group_name) {
		this.user_group_name = user_group_name;
	}
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("\n*******************************");
		sb.append("\n User Seq No = ").append(userSeqNo);
		sb.append("\n Login ID = ").append(loginId);
		sb.append("\n Email = ").append(email);
		sb.append("\n User Last Name = ").append(last_name);
		sb.append("\n User Name = ").append(userName);
		sb.append("\n User Type = ").append(userType);
		sb.append("\n User Type Name = ").append(userType_name);
		sb.append("\n user group = ").append(user_group);
		sb.append("\n base_date = ").append(base_date);
		sb.append("\n dept_name = ").append(dept_name);
		sb.append("\n position_name = ").append(position_name);
		sb.append("\n user_group_name = ").append(user_group_name);
		sb.append("\n*******************************");
		return sb.toString();
	}
}