package sea.scplus.consumer.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class RequestMyDataCategory implements Serializable {
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String origin_system;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<String> category_list;

	@JsonInclude(value = Include.NON_NULL)
	private String status;

	@JsonInclude(value = Include.NON_NULL)
	private List<String> recipient;

	@JsonInclude(value = Include.NON_NULL)
	private String response_type;

	@JsonInclude(value = Include.NON_NULL)
	private String password;

	@JsonInclude(value = Include.NON_NULL)
	private String file_password;

	@JsonInclude(value = Include.NON_NULL)
	private String g_ticket_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<String> file;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<RequestMyDataFileList> file_list;
	
	@JsonInclude(value = Include.NON_NULL)
	private RequestMyDataResponseMessage response_message;

	@JsonInclude(value = Include.NON_NULL)
	private String file_name;

	@JsonInclude(value = Include.NON_NULL)
	private String file_path;

	@JsonInclude(value = Include.NON_NULL)
	private String file_size;

	@JsonInclude(value = Include.NON_NULL)
	private String expire_date;

	public String getOrigin_system() {
		return origin_system;
	}

	public void setOrigin_system(String origin_system) {
		this.origin_system = origin_system;
	}

	public List<String> getCategory_list() {
		return category_list;
	}

	public void setCategory_list(List<String> category_list) {
		this.category_list = category_list;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getRecipient() {
		return recipient;
	}

	public void setRecipient(List<String> recipient) {
		this.recipient = recipient;
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
	
	public String getG_ticket_id() {
		return g_ticket_id;
	}

	public void setG_ticket_id(String g_ticket_id) {
		this.g_ticket_id = g_ticket_id;
	}
	
	public List<String> getFile() {
		return file;
	}

	public void setFile(List<String> file) {
		this.file = file;
	}

	public List<RequestMyDataFileList> getFile_list() {
		return file_list;
	}

	public void setFile_list(List<RequestMyDataFileList> file_list) {
		this.file_list = file_list;
	}

	public RequestMyDataResponseMessage getResponse_message() {
		return response_message;
	}

	public void setResponse_message(RequestMyDataResponseMessage response_message) {
		this.response_message = response_message;
	}

	public String getFile_password() {
		return file_password;
	}

	public void setFile_password(String file_password) {
		this.file_password = file_password;
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

	@Override
	public String toString() {
		return "RequestMyDataCategory [origin_system=" + origin_system + ", category_list=" + category_list
				+ ", status=" + status + ", recipient=" + recipient + ", response_type=" + response_type + ", password="
				+ password + ", file_password=" + file_password + ", g_ticket_id=" + g_ticket_id + ", file=" + file
				+ ", file_list=" + file_list + ", response_message=" + response_message + ", file_name=" + file_name
				+ ", file_path=" + file_path + ", file_size=" + file_size + ", expire_date=" + expire_date + "]";
	}

	
	
}
