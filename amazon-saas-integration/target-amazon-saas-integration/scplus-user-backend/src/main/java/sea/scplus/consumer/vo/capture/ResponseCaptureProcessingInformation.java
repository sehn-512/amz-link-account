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

 */
public class ResponseCaptureProcessingInformation implements Serializable {
	
	public ResponseCaptureProcessingInformation() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	boolean enhancedDataEnabled;

	public boolean isEnhancedDataEnabled() {
		return enhancedDataEnabled;
	}

	public void setEnhancedDataEnabled(boolean enhancedDataEnabled) {
		this.enhancedDataEnabled = enhancedDataEnabled;
	}

	
	
}
