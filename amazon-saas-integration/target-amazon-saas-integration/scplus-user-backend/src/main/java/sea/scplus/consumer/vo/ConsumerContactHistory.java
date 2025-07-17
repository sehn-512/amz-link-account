package sea.scplus.consumer.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ConsumerContactHistory implements Serializable {

	private static final long serialVersionUID = -4754502940820722424L;
	@JsonInclude(value = Include.NON_NULL)
	private String contact_history_id ;
	
	@JsonInclude(value = Include.NON_NULL)
	private String reference_code      ;
	
	@JsonInclude(value = Include.NON_NULL)
	private String contract_no      ;
	
	@JsonInclude(value = Include.NON_NULL)
	private String reason_type       ;
	@JsonInclude(value = Include.NON_NULL)
	private String request_path       ;
	@JsonInclude(value = Include.NON_NULL)
	private String response_type      ;
	@JsonInclude(value = Include.NON_NULL)
	private String send_date  		  ;
	@JsonInclude(value = Include.NON_NULL)
	private String content			  ;
	@JsonInclude(value = Include.NON_NULL)
	private String file_id	          ;
	@JsonInclude(value = Include.NON_NULL)
	private String email	          ;
	@JsonInclude(value = Include.NON_NULL)
	private String subject	          ;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getReference_code() {
		return reference_code;
	}


	public void setReference_code(String reference_code) {
		this.reference_code = reference_code;
	}


	public String getContract_no() {
		return contract_no;
	}


	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}


	public String getContact_history_id() {
		return contact_history_id;
	}
	public void setContact_history_id(String contact_history_id) {
		this.contact_history_id = contact_history_id;
	}
	public String getRequest_path() {
		return request_path;
	}
	public void setRequest_path(String request_path) {
		this.request_path = request_path;
	}
	public String getResponse_type() {
		return response_type;
	}
	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}
	public String getSend_date() {
		return send_date;
	}
	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public String getReason_type() {
		return reason_type;
	}
	public void setReason_type(String reason_type) {
		this.reason_type = reason_type;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	
}
