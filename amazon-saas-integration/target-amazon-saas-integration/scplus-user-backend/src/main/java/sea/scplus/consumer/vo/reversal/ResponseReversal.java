package sea.scplus.consumer.vo.reversal;

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
public class ResponseReversal implements Serializable {
	
	public ResponseReversal() {
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
	private List<ResponseReversalDetails> details;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponseReversalLinks _links;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponseReversalClientReferenceInformation clientReferenceInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponseReversalReversalAmountDetails reversalAmountDetails;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponseReversalProcessorInformation processorInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponseReversalIssuerInformation issuerInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponseReversalAuthorizationInformation authorizationInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponseReversalPointOfSaleInformation pointOfSaleInformation;
	

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

	public ResponseReversalLinks get_links() {
		return _links;
	}

	public void set_links(ResponseReversalLinks _links) {
		this._links = _links;
	}

	public ResponseReversalClientReferenceInformation getClientReferenceInformation() {
		return clientReferenceInformation;
	}

	public void setClientReferenceInformation(ResponseReversalClientReferenceInformation clientReferenceInformation) {
		this.clientReferenceInformation = clientReferenceInformation;
	}

	public List<ResponseReversalDetails> getDetails() {
		return details;
	}

	public void setDetails(List<ResponseReversalDetails> details) {
		this.details = details;
	}

	public ResponseReversalProcessorInformation getProcessorInformation() {
		return processorInformation;
	}

	public void setProcessorInformation(ResponseReversalProcessorInformation processorInformation) {
		this.processorInformation = processorInformation;
	}

	public ResponseReversalReversalAmountDetails getReversalAmountDetails() {
		return reversalAmountDetails;
	}

	public void setReversalAmountDetails(ResponseReversalReversalAmountDetails reversalAmountDetails) {
		this.reversalAmountDetails = reversalAmountDetails;
	}

	public ResponseReversalIssuerInformation getIssuerInformation() {
		return issuerInformation;
	}

	public void setIssuerInformation(ResponseReversalIssuerInformation issuerInformation) {
		this.issuerInformation = issuerInformation;
	}

	public ResponseReversalAuthorizationInformation getAuthorizationInformation() {
		return authorizationInformation;
	}

	public void setAuthorizationInformation(ResponseReversalAuthorizationInformation authorizationInformation) {
		this.authorizationInformation = authorizationInformation;
	}

	public ResponseReversalPointOfSaleInformation getPointOfSaleInformation() {
		return pointOfSaleInformation;
	}

	public void setPointOfSaleInformation(ResponseReversalPointOfSaleInformation pointOfSaleInformation) {
		this.pointOfSaleInformation = pointOfSaleInformation;
	}
	
	
	
}
