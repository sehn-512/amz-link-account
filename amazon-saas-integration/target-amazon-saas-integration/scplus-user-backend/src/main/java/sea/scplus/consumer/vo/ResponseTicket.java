package sea.scplus.consumer.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResponseTicket implements Serializable {
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String ticket_id;

	@JsonInclude(value = Include.NON_NULL)
	private String state;

	@JsonInclude(value = Include.NON_NULL)
	private String country_code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String lang_code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String identifier_type;
	
	@JsonInclude(value = Include.NON_NULL)
	private String identifier_value;
	
	@JsonInclude(value = Include.NON_NULL)
	private Boolean has_authenticated;
	
	@JsonInclude(value = Include.NON_NULL)
	private String user_request_date;
	
	@JsonInclude(value = Include.NON_NULL)
	private String request_type;

	@JsonInclude(value = Include.NON_NULL)
	private List<String>  origin_system;

	public List<String> getOrigin_system() {
		return origin_system;
	}

	@JsonInclude(value = Include.NON_NULL)
	private String password;
	
	@JsonInclude(value = Include.NON_NULL)
	private String result_code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String error_code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String message;
	
	@JsonInclude(value = Include.NON_NULL)
	private String sa_guid;
	
	@JsonInclude(value = Include.NON_NULL)
	private String g_ticket_id;
	
	public void setOrigin_system(List<String> origin_system) {
		this.origin_system = origin_system;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(String ticket_id) {
		this.ticket_id = ticket_id;
	}
	public String getIdentifier_type() {
		return identifier_type;
	}
	public void setIdentifier_type(String identifier_type) {
		this.identifier_type = identifier_type;
	}
	public String getIdentifier_value() {
		return identifier_value;
	}
	public void setIdentifier_value(String identifier_value) {
		this.identifier_value = identifier_value;
	}
	public String getLang_code() {
		return lang_code;
	}
	public void setLang_code(String lang_code) {
		this.lang_code = lang_code;
	}
	public Boolean getHas_authenticated() {
		return has_authenticated;
	}
	public void setHas_authenticated(Boolean has_authenticated) {
		this.has_authenticated = has_authenticated;
	}
	public String getUser_request_date() {
		return user_request_date;
	}
	public void setUser_request_date(String user_request_date) {
		this.user_request_date = user_request_date;
	}
	public String getRequest_type() {
		return request_type;
	}
	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ResponseTicket [ticket_id=" + ticket_id + ", state=" + state + ", country_code=" + country_code
				+ ", lang_code=" + lang_code + ", identifier_type=" + identifier_type + ", identifier_value="
				+ identifier_value + ", has_authenticated=" + has_authenticated + ", user_request_date="
				+ user_request_date + ", request_type=" + request_type + ", origin_system=" + origin_system + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSa_guid() {
		return sa_guid;
	}

	public void setSa_guid(String sa_guid) {
		this.sa_guid = sa_guid;
	}

	public String getG_ticket_id() {
		return g_ticket_id;
	}

	public void setG_ticket_id(String g_ticket_id) {
		this.g_ticket_id = g_ticket_id;
	}
	
}
