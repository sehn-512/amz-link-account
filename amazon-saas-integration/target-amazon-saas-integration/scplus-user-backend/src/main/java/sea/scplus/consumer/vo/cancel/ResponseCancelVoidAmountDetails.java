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

 */
public class ResponseCancelVoidAmountDetails implements Serializable {
	
	public ResponseCancelVoidAmountDetails() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	String voidAmount;

	@JsonInclude(value = Include.NON_NULL)
	String originalTransactionAmount;
	
	@JsonInclude(value = Include.NON_NULL)
	String currency;

	public String getVoidAmount() {
		return voidAmount;
	}

	public void setVoidAmount(String voidAmount) {
		this.voidAmount = voidAmount;
	}

	public String getOriginalTransactionAmount() {
		return originalTransactionAmount;
	}

	public void setOriginalTransactionAmount(String originalTransactionAmount) {
		this.originalTransactionAmount = originalTransactionAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
