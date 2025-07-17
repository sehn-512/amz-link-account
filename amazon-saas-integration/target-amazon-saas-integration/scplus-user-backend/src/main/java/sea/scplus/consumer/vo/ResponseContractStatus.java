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
 * Q  : Creation Requested to TPA
A  : Active
CQ : Customer Cancel Requested
RQ : Cancel Requested from Retailer
NQ : Cancel Requested to TPA
N  : Cancelled
VQ : Reactivate Request by SEA
VT : Reactivate Request to TPA
F  : Create Fail
X  : Expired 

{
  "code": 0,
  "contractStatusInfoResponse": {
    "email": "michael.j@jackson5.com",
    "firstName": "Jackson",
    "lastName": "Michael",
    "contractEndDate": "string",
    "contractNo": "string",
    "contractStartDate": "string",
    "modelCode": "string",
    "modelType": "string",
    "orderDate": "string",
    "purchaseDate": "string",
    "serialNo": "string",
    "spcPrice": 0,
    "spcSku": "string",
    "term": 0,
    "status": "Q",
    "cancelAvailable": boolean
  },
  "msg": "string",
  "success": true
}
 *
 */
public class ResponseContractStatus implements Serializable {
	
	public ResponseContractStatus() {
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
	private ResponseContractStatusInfoResponse contractStatusInfoResponse;

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

	public ResponseContractStatusInfoResponse getContractStatusInfoResponse() {
		return contractStatusInfoResponse;
	}

	public void setContractStatusInfoResponse(ResponseContractStatusInfoResponse contractStatusInfoResponse) {
		this.contractStatusInfoResponse = contractStatusInfoResponse;
	}
	
	

}
