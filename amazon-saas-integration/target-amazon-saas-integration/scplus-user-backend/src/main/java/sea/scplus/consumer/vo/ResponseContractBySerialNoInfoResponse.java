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
  "contractBySerialNoInfoResponse": {
    "contractNo": "string",
    "status": "string"
  },
  "msg": "string",
  "success": true
}
 *
 */
public class ResponseContractBySerialNoInfoResponse implements Serializable {
	
	public ResponseContractBySerialNoInfoResponse() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String contractNo;
	
	@JsonInclude(value = Include.NON_NULL)
	private String status;

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
