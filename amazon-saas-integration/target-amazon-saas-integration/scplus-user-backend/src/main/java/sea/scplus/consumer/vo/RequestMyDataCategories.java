package sea.scplus.consumer.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class RequestMyDataCategories implements Serializable {
	
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String ticket_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String target_system;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<RequestMyDataCategory> data;

	@JsonInclude(value = Include.NON_NULL)
	private String interface_log_id;

	@JsonInclude(value = Include.NON_NULL)
	private String interface_id;

	// ADD
	@JsonInclude(value = Include.NON_NULL)
	private String response_type;

	@JsonInclude(value = Include.NON_NULL)
	private String password;
	
	@JsonInclude(value = Include.NON_NULL)
	private String g_ticket_id;

	@JsonInclude(value = Include.NON_NULL)
	private String event_status;

	@JsonInclude(value = Include.NON_NULL)
	private String complete_date;

	// to need check.
	@JsonInclude(value = Include.NON_NULL)
	private String sa_guid;

	// to need check.
	@JsonInclude(value = Include.NON_NULL)
	private String event_type;

	// to need check.
	@JsonInclude(value = Include.NON_NULL)
	private String origin_system;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<RequestMyDataFileList> file_list;
	
	@JsonInclude(value = Include.NON_NULL)
	private RequestMyDataResponseMessage response_message;
	
	@JsonInclude(value = Include.NON_NULL)
	private String country_code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String lang_code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String user_request_date;
	
	@JsonInclude(value = Include.NON_NULL)
	private String service_code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String channel_type_code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String origin_ticket_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String file_name;
	
	@JsonInclude(value = Include.NON_NULL)
	private String file_path;
	
	@JsonInclude(value = Include.NON_NULL)
	private String file_size;
	
	@JsonInclude(value = Include.NON_NULL)
	private String expire_date;
	
	@JsonInclude(value = Include.NON_NULL)
	private String exemtion_code;

	public String getInterface_log_id() {
		return interface_log_id;
	}

	public void setInterface_log_id(String interface_log_id) {
		this.interface_log_id = interface_log_id;
	}

	public String getInterface_id() {
		return interface_id;
	}

	public void setInterface_id(String interface_id) {
		this.interface_id = interface_id;
	}

	public String getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(String ticket_id) {
		this.ticket_id = ticket_id;
	}

	public String getTarget_system() {
		return target_system;
	}

	public void setTarget_system(String target_system) {
		this.target_system = target_system;
	}

	public List<RequestMyDataCategory> getData() {
		return data;
	}

	public void setData(List<RequestMyDataCategory> data) {
		this.data = data;
	}
	
	public String getG_ticket_id() {
		return g_ticket_id;
	}

	public void setG_ticket_id(String g_ticket_id) {
		this.g_ticket_id = g_ticket_id;
	}

	public String getEvent_status() {
		return event_status;
	}

	public void setEvent_status(String event_status) {
		this.event_status = event_status;
	}

	public String getComplete_date() {
		return complete_date;
	}

	public void setComplete_date(String complete_date) {
		this.complete_date = complete_date;
	}

	public String getSa_guid() {
		return sa_guid;
	}

	public void setSa_guid(String sa_guid) {
		this.sa_guid = sa_guid;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public List<RequestMyDataFileList> getFile_list() {
		return file_list;
	}

	public void setFile_list(List<RequestMyDataFileList> file_list) {
		this.file_list = file_list;
	}

	public String getResponse_type() {
		return response_type;
	}

	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RequestMyDataResponseMessage getResponse_message() {
		return response_message;
	}

	public void setResponse_message(RequestMyDataResponseMessage response_message) {
		this.response_message = response_message;
	}

	public String getOrigin_system() {
		return origin_system;
	}

	public void setOrigin_system(String origin_system) {
		this.origin_system = origin_system;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getLang_code() {
		return lang_code;
	}

	public void setLang_code(String lang_code) {
		this.lang_code = lang_code;
	}

	public String getUser_request_date() {
		return user_request_date;
	}

	public void setUser_request_date(String user_request_date) {
		this.user_request_date = user_request_date;
	}

	public String getService_code() {
		return service_code;
	}

	public void setService_code(String service_code) {
		this.service_code = service_code;
	}

	public String getChannel_type_code() {
		return channel_type_code;
	}

	public void setChannel_type_code(String channel_type_code) {
		this.channel_type_code = channel_type_code;
	}

	public String getOrigin_ticket_id() {
		return origin_ticket_id;
	}

	public void setOrigin_ticket_id(String origin_ticket_id) {
		this.origin_ticket_id = origin_ticket_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFile_size() {
		return file_size;
	}

	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}

	public String getExpire_date() {
		return expire_date;
	}

	public void setExpire_date(String expire_date) {
		this.expire_date = expire_date;
	}

	public String getExemtion_code() {
		return exemtion_code;
	}

	public void setExemtion_code(String exemtion_code) {
		this.exemtion_code = exemtion_code;
	}
	
}
