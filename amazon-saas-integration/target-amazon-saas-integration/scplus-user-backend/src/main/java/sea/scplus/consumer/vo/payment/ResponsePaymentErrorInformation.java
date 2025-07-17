package sea.scplus.consumer.vo.payment;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * 
When return code 201
{
  "clientReferenceInformation": {
    "code": "202102250945440048"
  },
  "errorInformation": {
    "reason": "INVALID_ACCOUNT",
    "message": "Decline - Invalid account number"
  },
  "id": "6142643450526644503006",
  "status": "DECLINED"
}

 */
public class ResponsePaymentErrorInformation implements Serializable {
	
	public ResponsePaymentErrorInformation() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	String reason;

	@JsonInclude(value = Include.NON_NULL)
	String message;
	
	@JsonInclude(value = Include.NON_NULL)
	List<ResponsePaymentErrorInformationDetails> details;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ResponsePaymentErrorInformationDetails> getDetails() {
		return details;
	}

	public void setDetails(List<ResponsePaymentErrorInformationDetails> details) {
		this.details = details;
	}
	
	
	
}
