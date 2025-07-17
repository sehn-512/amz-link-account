package sea.scplus.consumer.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class RequestValidation implements Serializable {
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String validation_type;

	@JsonInclude(value = Include.NON_NULL)
	private String request_uri;

	@JsonInclude(value = Include.NON_NULL)
	private String ticket_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String group_code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String code;

	@JsonInclude(value = Include.NON_NULL)
	private String target_system;
	
	@JsonInclude(value = Include.NON_NULL)
	private String status;
	
	@JsonInclude(value = Include.NON_NULL)
	private String request_type;

	@JsonInclude(value = Include.NON_NULL)
	private String start_date;

	@JsonInclude(value = Include.NON_NULL)
	private String end_date;
	
	@JsonInclude(value = Include.NON_NULL)
	private Integer page_index;
	
	@JsonInclude(value = Include.NON_NULL)
	private Integer per_page;

	@JsonInclude(value = Include.NON_NULL)
	private Integer start;

	@JsonInclude(value = Include.NON_NULL)
	private Integer total_count;

	@JsonInclude(value = Include.NON_NULL)
	private String interface_log_id;

	@JsonInclude(value = Include.NON_NULL)
	private String interface_id;

	@JsonInclude(value = Include.NON_NULL)
	private String identifier_type;

	@JsonInclude(value = Include.NON_NULL)
	private String state;

	@JsonInclude(value = Include.NON_NULL)
	private String date;

	@JsonInclude(value = Include.NON_NULL)
	private String Origin_system;

	@JsonInclude(value = Include.NON_NULL)
	private String Category_list;

	@JsonInclude(value = Include.NON_NULL)
	private String Recipient;

	@JsonInclude(value = Include.NON_NULL)
	private String api_key;

	@JsonInclude(value = Include.NON_NULL)
	private String c_date;
	
	@JsonInclude(value = Include.NON_NULL)
	private String sa_guid;
	
	@JsonInclude(value = Include.NON_NULL)
	private String event_type;
	
	@JsonInclude(value = Include.NON_NULL)
	private String event_status;
	
	@JsonInclude(value = Include.NON_NULL)
	private String complete_date;

	@JsonInclude(value = Include.NON_NULL)
	private List<RequestMyDataCategory> data;
		
	@JsonInclude(value = Include.NON_NULL)
	private List<String> origin_system_list;

	@JsonInclude(value = Include.NON_NULL)
	private String file_name;

	@JsonInclude(value = Include.NON_NULL)
	private String file_path;

	@JsonInclude(value = Include.NON_NULL)
	private String file_size;

	@JsonInclude(value = Include.NON_NULL)
	private String expire_date;

	@JsonInclude(value = Include.NON_NULL)
	private String message_name;

	@JsonInclude(value = Include.NON_NULL)
	private String message_value;

	@JsonInclude(value = Include.NON_NULL)
	private String password;

	@JsonInclude(value = Include.NON_NULL)
	private String file_expired_date;

	@JsonInclude(value = Include.NON_NULL)
	private String g_ticket_id;

	@JsonInclude(value = Include.NON_NULL)
	private String access_data_type;

	@JsonInclude(value = Include.NON_NULL)
	private List<RequestMyDataFileList> file_list;

	@JsonInclude(value = Include.NON_NULL)
	private String origin_ticket_id;

	@JsonInclude(value = Include.NON_NULL)
	private String response_type;

	@JsonInclude(value = Include.NON_NULL)
	private String exemtion_code;
	
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

	public String getMessage_name() {
		return message_name;
	}

	public void setMessage_name(String message_name) {
		this.message_name = message_name;
	}

	public String getMessage_value() {
		return message_value;
	}

	public void setMessage_value(String message_value) {
		this.message_value = message_value;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFile_expired_date() {
		return file_expired_date;
	}

	public void setFile_expired_date(String file_expired_date) {
		this.file_expired_date = file_expired_date;
	}
	
	public String getG_ticket_id() {
		return g_ticket_id;
	}

	public void setG_ticket_id(String g_ticket_id) {
		this.g_ticket_id = g_ticket_id;
	}

	public String getAccess_data_type() {
		return access_data_type;
	}

	public void setAccess_data_type(String access_data_type) {
		this.access_data_type = access_data_type;
	}

	public String getC_date() {
		return c_date;
	}

	public void setC_date(String c_date) {
		this.c_date = c_date;
	}

	public String getRequest_uri() {
		return request_uri;
	}

	public void setRequest_uri(String request_uri) {
		this.request_uri = request_uri;
	}

	public String getValidation_type() {
		return validation_type;
	}

	public void setValidation_type(String validation_type) {
		this.validation_type = validation_type;
	}

	public String getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(String ticket_id) {
		this.ticket_id = ticket_id;
	}

	public String getGroup_code() {
		return group_code;
	}

	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIdentifier_type() {
		return identifier_type;
	}

	public void setIdentifier_type(String identifier_type) {
		this.identifier_type = identifier_type;
	}

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

	public String getTarget_system() {
		return target_system;
	}

	public void setTarget_system(String target_system) {
		this.target_system = target_system;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public Integer getPage_index() {
		return page_index;
	}

	public void setPage_index(Integer page_index) {
		this.page_index = page_index;
	}

	public Integer getPer_page() {
		return per_page;
	}

	public void setPer_page(Integer per_page) {
		this.per_page = per_page;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getTotal_count() {
		return total_count;
	}

	public void setTotal_count(Integer total_count) {
		this.total_count = total_count;
	}

	public String getRequest_type() {
		return request_type;
	}

	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOrigin_system() {
		return Origin_system;
	}

	public void setOrigin_system(String origin_system) {
		Origin_system = origin_system;
	}

	public String getCategory_list() {
		return Category_list;
	}

	public void setCategory_list(String category_list) {
		Category_list = category_list;
	}

	public String getRecipient() {
		return Recipient;
	}

	public void setRecipient(String recipient) {
		Recipient = recipient;
	}

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	public List<RequestMyDataCategory> getData() {
		return data;
	}

	public void setData(List<RequestMyDataCategory> data) {
		this.data = data;
	}

	public List<String> getOrigin_system_list() {
		return origin_system_list;
	}

	public void setOrigin_system_list(List<String> origin_system_list) {
		this.origin_system_list = origin_system_list;
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

	public List<RequestMyDataFileList> getFile_list() {
		return file_list;
	}

	public void setFile_list(List<RequestMyDataFileList> file_list) {
		this.file_list = file_list;
	}

	public String getOrigin_ticket_id() {
		return origin_ticket_id;
	}

	public void setOrigin_ticket_id(String origin_ticket_id) {
		this.origin_ticket_id = origin_ticket_id;
	}

	public String getResponse_type() {
		return response_type;
	}

	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}

	public String getExemtion_code() {
		return exemtion_code;
	}

	public void setExemtion_code(String exemtion_code) {
		this.exemtion_code = exemtion_code;
	}
	
}
