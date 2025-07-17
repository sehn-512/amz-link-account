package sea.scplus.consumer.vo.capture;

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
public class ResponseCapture implements Serializable {
	
	public ResponseCapture() {
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
	private String reconciliationId;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<ResponseCaptureDetails> details;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponseCaptureLinks _links;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponseCaptureClientReferenceInformation clientReferenceInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponseCaptureOrderInformation orderInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponseCaptureProcessorInformation processorInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponseCapturePointOfSaleInformation pointOfSaleInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponseCaptureProcessingInformation processingInformation;
	

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

	public ResponseCaptureLinks get_links() {
		return _links;
	}

	public void set_links(ResponseCaptureLinks _links) {
		this._links = _links;
	}

	public ResponseCaptureClientReferenceInformation getClientReferenceInformation() {
		return clientReferenceInformation;
	}

	public void setClientReferenceInformation(ResponseCaptureClientReferenceInformation clientReferenceInformation) {
		this.clientReferenceInformation = clientReferenceInformation;
	}

	public ResponseCaptureOrderInformation getOrderInformation() {
		return orderInformation;
	}

	public void setOrderInformation(ResponseCaptureOrderInformation orderInformation) {
		this.orderInformation = orderInformation;
	}

	public List<ResponseCaptureDetails> getDetails() {
		return details;
	}

	public void setDetails(List<ResponseCaptureDetails> details) {
		this.details = details;
	}

	public ResponseCaptureProcessorInformation getProcessorInformation() {
		return processorInformation;
	}

	public void setProcessorInformation(ResponseCaptureProcessorInformation processorInformation) {
		this.processorInformation = processorInformation;
	}

	public ResponseCapturePointOfSaleInformation getPointOfSaleInformation() {
		return pointOfSaleInformation;
	}

	public void setPointOfSaleInformation(ResponseCapturePointOfSaleInformation pointOfSaleInformation) {
		this.pointOfSaleInformation = pointOfSaleInformation;
	}

	public ResponseCaptureProcessingInformation getProcessingInformation() {
		return processingInformation;
	}

	public void setProcessingInformation(ResponseCaptureProcessingInformation processingInformation) {
		this.processingInformation = processingInformation;
	}

	public String getReconciliationId() {
		return reconciliationId;
	}

	public void setReconciliationId(String reconciliationId) {
		this.reconciliationId = reconciliationId;
	}
	
	
	
	
}
