package sea.scplus.consumer.vo.reversal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
{
    "clientReferenceInformation": {
        "code": "TC50171_3"
    },
    "orderInformation": {
        "amountDetails": {
            "totalAmount": "10",
            "currency": "USD"
        }
    }
}
 *
 */
public class RequestReversalReversalInformation {

	@JsonInclude(value = Include.NON_NULL)
	String reason;
	
	@JsonInclude(value = Include.NON_NULL)
	private RequestReversalReversalInformationAmountDetails amountDetails;

	public RequestReversalReversalInformationAmountDetails getAmountDetails() {
		return amountDetails;
	}

	public void setAmountDetails(RequestReversalReversalInformationAmountDetails amountDetails) {
		this.amountDetails = amountDetails;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
