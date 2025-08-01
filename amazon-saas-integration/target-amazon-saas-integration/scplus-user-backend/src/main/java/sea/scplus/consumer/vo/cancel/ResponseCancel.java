package sea.scplus.consumer.vo.cancel;

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
    "_links": {
        "self": {
            "method": "GET",
            "href": "/pts/v2/voids/6143607320636689303001"
        }
    },
    "clientReferenceInformation": {
        "code": "test_void"
    },
    "id": "6143607320636689303001",
    "orderInformation": {
        "amountDetails": {
            "currency": "USD"
        }
    },
    "status": "VOIDED",
    "submitTimeUtc": "2021-02-26T17:32:12Z",
    "voidAmountDetails": {
        "currency": "USD",
        "voidAmount": "102.21"
    }
}

When return code 201 with errorInformation
{
  "clientReferenceInformation": {
    "code": "202102250945440048"
  },
  "id": "6142643450526644503006",
  "status": "DECLINED"
}

When return code 400
{
  "submitTimeUtc": "2016-08-11T22:47:57Z",
  "status": "SERVER_ERROR",
  "reason": "SYSTEM_ERROR",
  "message": "Unexpected system error or system timeout.",
  "details": [{
    "field": "This is the flattened JSON object field name/path that is either missing or invalid.",
    "reason": Possible reasons for the error.
			  Possible values:
				MISSING_FIELD
				INVALID_DATA
  }]
}


When return code 502
{
  "submitTimeUtc": "2016-08-11T22:47:57Z",
  "status": "SERVER_ERROR",
  "reason": "SYSTEM_ERROR",
  "message": "Unexpected system error or system timeout."
}

 *
 */
public class ResponseCancel implements Serializable {
	
	public ResponseCancel() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String id;
	
	@JsonInclude(value = Include.NON_NULL)
	private boolean success;
	
	@JsonInclude(value = Include.NON_NULL)
	private String status;
	
	@JsonInclude(value = Include.NON_NULL)
	private String submitTimeUtc;
	
	@JsonInclude(value = Include.NON_NULL)
	private String reason;
	
	@JsonInclude(value = Include.NON_NULL)
	private String message;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<ResponseCancelDetails> details;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponseCancelLinks _links;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponseCancelClientReferenceInformation clientReferenceInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponseCancelOrderInformation orderInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponseCancelProcessorInformation processorInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponseCancelVoidAmountDetails voidAmountDetails;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubmitTimeUtc() {
		return submitTimeUtc;
	}

	public void setSubmitTimeUtc(String submitTimeUtc) {
		this.submitTimeUtc = submitTimeUtc;
	}

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

	public ResponseCancelLinks get_links() {
		return _links;
	}

	public void set_links(ResponseCancelLinks _links) {
		this._links = _links;
	}

	public ResponseCancelClientReferenceInformation getClientReferenceInformation() {
		return clientReferenceInformation;
	}

	public void setClientReferenceInformation(ResponseCancelClientReferenceInformation clientReferenceInformation) {
		this.clientReferenceInformation = clientReferenceInformation;
	}

	public ResponseCancelOrderInformation getOrderInformation() {
		return orderInformation;
	}

	public void setOrderInformation(ResponseCancelOrderInformation orderInformation) {
		this.orderInformation = orderInformation;
	}

	public List<ResponseCancelDetails> getDetails() {
		return details;
	}

	public void setDetails(List<ResponseCancelDetails> details) {
		this.details = details;
	}

	public ResponseCancelVoidAmountDetails getVoidAmountDetails() {
		return voidAmountDetails;
	}

	public void setVoidAmountDetails(ResponseCancelVoidAmountDetails voidAmountDetails) {
		this.voidAmountDetails = voidAmountDetails;
	}

	public ResponseCancelProcessorInformation getProcessorInformation() {
		return processorInformation;
	}

	public void setProcessorInformation(ResponseCancelProcessorInformation processorInformation) {
		this.processorInformation = processorInformation;
	}
	
	
	
	
}
