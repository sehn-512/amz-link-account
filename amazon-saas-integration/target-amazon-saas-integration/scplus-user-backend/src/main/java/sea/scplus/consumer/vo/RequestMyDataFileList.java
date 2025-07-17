package sea.scplus.consumer.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class RequestMyDataFileList implements Serializable {
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String file_name;
	
	@JsonInclude(value = Include.NON_NULL)
	private String file_path;
	
	@JsonInclude(value = Include.NON_NULL)
	private String file_size;
	
	@JsonInclude(value = Include.NON_NULL)
	private String expire_date;

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
		return "RequestMyDataFileList [file_name=" + file_name + ", file_path=" + file_path + ", file_size=" + file_size
				+ ", expire_date=" + expire_date + "]";
	}
	
	
	
}
