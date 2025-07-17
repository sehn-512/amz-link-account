package sea.scplus.consumer.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * {
	  "code": "string",
	  "manufactureDate": "string",
	  "msg": "string",
	  "success": true
	}
 *
 */
public class ResponseSerialNoValidation implements Serializable {
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String purchaseDate;
	
	@JsonInclude(value = Include.NON_NULL)
	private String manufactureDate;
	
	@JsonInclude(value = Include.NON_NULL)
	private String wtyExpirationDate;
	
	@JsonInclude(value = Include.NON_NULL)
	private String msg;
	
	@JsonInclude(value = Include.NON_NULL)
	private boolean success;
	
	public ResponseSerialNoValidation() {
		super();
	}
	
	/**
	 * @param code
	 * @param manufactureDate
	 * @param wtyExpirationDate
	 * @param msg
	 * @param success
	 */
	public ResponseSerialNoValidation(String code, String purchaseDate, String manufactureDate, String wtyExpirationDate, String msg, boolean success) {
		super();
		this.code = code;
		this.purchaseDate = purchaseDate;
		this.manufactureDate = manufactureDate;
		this.wtyExpirationDate = wtyExpirationDate;
		this.msg = msg;
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(String manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	
	public String getWtyExpirationDate() {
		return wtyExpirationDate;
	}

	public void setWtyExpirationDate(String wtyExpirationDate) {
		this.wtyExpirationDate = wtyExpirationDate;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
