package sea.scplus.consumer.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class APIBatchRequest implements Serializable {
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String ticket_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String target_system;
	
	@JsonInclude(value = Include.NON_NULL)
	private String status;
	
	@JsonInclude(value = Include.NON_NULL)
	private String requestStatus;
	
	@JsonInclude(value = Include.NON_NULL)
	private String consumerStatus;
	
	@JsonInclude(value = Include.NON_NULL)
	private String checkStatus;
	
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
	private List<String> request_type_list;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<String> identifier_type_list;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<String> Ticket_id_list;

	@JsonInclude(value = Include.NON_NULL)
	private String country_code;

	@JsonInclude(value = Include.NON_NULL)
	private String preRequestStatus;
	
	@JsonInclude(value = Include.NON_NULL)
	private String preConsumerStatus;
	
	@JsonInclude(value = Include.NON_NULL)
	private String inCompleteStatus;
	
	@JsonInclude(value = Include.NON_NULL)
	private String c_date;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<RequestMyDataCategory> data;
	
	@JsonInclude(value = Include.NON_NULL)
	private String origin_system;

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
	
	@JsonInclude(value = Include.NON_NULL)
	private List<RequestMyDataFileList> file_list;
	
	@JsonInclude(value = Include.NON_NULL)
	private RequestMyDataResponseMessage response_message;

	@JsonInclude(value = Include.NON_NULL)
	private String lang_code;

	@JsonInclude(value = Include.NON_NULL)
	private String user_request_date;

	@JsonInclude(value = Include.NON_NULL)
	private String service_code;

	@JsonInclude(value = Include.NON_NULL)
	private String channel_type_code;
	
	
	
	
	public List<RequestMyDataCategory> getData() {
		return data;
	}

	public void setData(List<RequestMyDataCategory> data) {
		this.data = data;
	}

	public String getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(String ticket_id) {
		this.ticket_id = ticket_id;
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
	
	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getConsumerStatus() {
		return consumerStatus;
	}

	public void setConsumerStatus(String consumerStatus) {
		this.consumerStatus = consumerStatus;
	}
	
	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
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

	public List<String> getRequest_type_list() {
		return request_type_list;
	}

	public void setRequest_type_list(List<String> request_type_list) {
		this.request_type_list = request_type_list;
	}
	
	public List<String> getIdentifier_type_list() {
		return identifier_type_list;
	}

	public void setIdentifier_type_list(List<String> identifier_type_list) {
		this.identifier_type_list = identifier_type_list;
	}

	public List<String> getTicket_id_list() {
		return Ticket_id_list;
	}

	public void setTicket_id_list(List<String> ticket_id_list) {
		Ticket_id_list = ticket_id_list;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getPreRequestStatus() {
		return preRequestStatus;
	}

	public void setPreRequestStatus(String preRequestStatus) {
		this.preRequestStatus = preRequestStatus;
	}

	public String getPreConsumerStatus() {
		return preConsumerStatus;
	}

	public void setPreConsumerStatus(String preConsumerStatus) {
		this.preConsumerStatus = preConsumerStatus;
	}

	public String getInCompleteStatus() {
		return inCompleteStatus;
	}

	public void setInCompleteStatus(String inCompleteStatus) {
		this.inCompleteStatus = inCompleteStatus;
	}

	public String getC_date() {
		return c_date;
	}

	public void setC_date(String c_date) {
		this.c_date = c_date;
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
	
}
