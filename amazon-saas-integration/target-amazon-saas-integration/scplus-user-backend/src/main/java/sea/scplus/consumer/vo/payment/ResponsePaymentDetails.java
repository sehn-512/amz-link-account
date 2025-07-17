package sea.scplus.consumer.vo.payment;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * 
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
 */
public class ResponsePaymentDetails implements Serializable {
	
	public ResponsePaymentDetails() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	String field;

	@JsonInclude(value = Include.NON_NULL)
	String reason;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	
	
	
}
