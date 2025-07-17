package sea.scplus.consumer.vo.capture;

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
public class RequestCaptureOrderInformation {

	@JsonInclude(value = Include.NON_NULL)
	private RequestCaptureOrderInformationAmountDetails amountDetails;

	public RequestCaptureOrderInformationAmountDetails getAmountDetails() {
		return amountDetails;
	}

	public void setAmountDetails(RequestCaptureOrderInformationAmountDetails amountDetails) {
		this.amountDetails = amountDetails;
	}

	
	
}
