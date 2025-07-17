package sea.scplus.consumer.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class RequestCheckCode implements Serializable {
	
	
	private static final long serialVersionUID = -4754502940820722424L;
	
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
}
