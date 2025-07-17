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
  "success": true,
  "msg": "string",
  "code": 0,
  "contractBySerialNoInfoResponse": {
    "contractNo": "string",
    "status": "string"
  }
}
 *
 */
public class ResponseContractBySerialNo implements Serializable {
	
	public ResponseContractBySerialNo() {
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
	private List<ResponseContractBySerialNoInfoResponse> contractBySerialNoInfoResponse;

	
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

	public List<ResponseContractBySerialNoInfoResponse> getContractBySerialNoInfoResponse() {
		return contractBySerialNoInfoResponse;
	}

	public void setContractBySerialNoInfoResponse(
			List<ResponseContractBySerialNoInfoResponse> contractBySerialNoInfoResponse) {
		this.contractBySerialNoInfoResponse = contractBySerialNoInfoResponse;
	}

	
	

}
