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
public class RequestCancelClientReferenceInformation {

	@JsonInclude(value = Include.NON_NULL)
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
