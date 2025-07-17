package sea.scplus.consumer.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
{
  "code": 0,
  "msg": "string",
  "success": true
}

{
  "timestamp": "2021-02-11T15:09:38.188+0000",
  "status": 500,
  "error": "Internal Server Error",
  "message": "No message available",
  "path": "/scmpapi/v1/channel/contractCancel"
}
 *
 */
public class ResponseContractCancel implements Serializable {
	
	public ResponseContractCancel() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String msg;
	
	@JsonInclude(value = Include.NON_NULL)
	private boolean success;
	
	@JsonInclude(value = Include.NON_NULL)
	private String timestamp;
	
	@JsonInclude(value = Include.NON_NULL)
	private String status;
	
	@JsonInclude(value = Include.NON_NULL)
	private String error;
	
	@JsonInclude(value = Include.NON_NULL)
	private String path;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	

}
