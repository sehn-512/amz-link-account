package sea.scplus.consumer.vo.cancel;

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
public class RequestCancelOrderInformation {

	@JsonInclude(value = Include.NON_NULL)
	private RequestCancelOrderInformationAmountDetails amountDetails;

	public RequestCancelOrderInformationAmountDetails getAmountDetails() {
		return amountDetails;
	}

	public void setAmountDetails(RequestCancelOrderInformationAmountDetails amountDetails) {
		this.amountDetails = amountDetails;
	}

	
	
}
