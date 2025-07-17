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
{
  "token":"",
  "email": "michael.j@jackson5.com",
  "cancelDate": "2019-11-21",
  "cancelReason": "cancel by Customer",
  "contractNo": 600009999,
  "sendConfirmEmailYN": "N"
}
 *
 */
public class RequestContractCancel implements Serializable {
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String cancelDate;
	
	@JsonInclude(value = Include.NON_NULL)
	private String cancelReason;
	
	@JsonInclude(value = Include.NON_NULL)
	private String contractNo;
	
	@JsonInclude(value = Include.NON_NULL)
	private String sendConfirmEmailYN;
	
	@JsonInclude(value = Include.NON_NULL)
	private String token;
	
	@JsonInclude(value = Include.NON_NULL)
	private String email;
	
	@JsonInclude(value = Include.NON_NULL)
	private String firstName;
	
	@JsonInclude(value = Include.NON_NULL)
	private String lastName;
	
	@JsonInclude(value = Include.NON_NULL)
	private String refundOrderItemId;
	
	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getContractNo() {
		return contractNo;
	}
	
	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getSendConfirmEmailYN() {
		return sendConfirmEmailYN;
	}

	public void setSendConfirmEmailYN(String sendConfirmEmailYN) {
		this.sendConfirmEmailYN = sendConfirmEmailYN;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRefundOrderItemId() {
		return refundOrderItemId;
	}

	public void setRefundOrderItemId(String refundOrderItemId) {
		this.refundOrderItemId = refundOrderItemId;
	}

	
	
}
