package sea.scplus.consumer.vo.erp.billing;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
{
  "success": true,
  "message": "success",
  "code": "000000"
}
 *
 */
public class ResponseGetBillingOrder implements Serializable {
	
	public ResponseGetBillingOrder() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private boolean success;
	
	@JsonInclude(value = Include.NON_NULL)
	private String message;
	
	@JsonInclude(value = Include.NON_NULL)
	private String code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String billingOrderNumber;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBillingOrderNumber() {
		return billingOrderNumber;
	}

	public void setBillingOrderNumber(String billingOrderNumber) {
		this.billingOrderNumber = billingOrderNumber;
	}
	
	
	
	
}
