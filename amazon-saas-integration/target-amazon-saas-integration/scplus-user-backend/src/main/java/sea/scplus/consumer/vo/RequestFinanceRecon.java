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
  "orderItemIdentifier": "C310",
  "contractNo": "607435319",
  "salesOrderNumber": "1550788742",
  "billingOrderNumber": "3404508137",
  "contractDate": "2021-04-14",
  "totalAmount": "199.99",
  "zipCode": "77450",
  "postingKey": "01"
}
 *
 */
public class RequestFinanceRecon implements Serializable {
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String token;
	
	@JsonInclude(value = Include.NON_NULL)
	private String orderItemIdentifier;
	
	@JsonInclude(value = Include.NON_NULL)
	private String contractNo;
	
	@JsonInclude(value = Include.NON_NULL)
	private String salesOrderNumber;
	
	@JsonInclude(value = Include.NON_NULL)
	private String billingOrderNumber;
	
	@JsonInclude(value = Include.NON_NULL)
	private String contractDate;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double totalAmount;
	
	@JsonInclude(value = Include.NON_NULL)
	private String zipCode;

	@JsonInclude(value = Include.NON_NULL)
	private String postingKey;

	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOrderItemIdentifier() {
		return orderItemIdentifier;
	}

	public void setOrderItemIdentifier(String orderItemIdentifier) {
		this.orderItemIdentifier = orderItemIdentifier;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	public String getBillingOrderNumber() {
		return billingOrderNumber;
	}

	public void setBillingOrderNumber(String billingOrderNumber) {
		this.billingOrderNumber = billingOrderNumber;
	}

	public String getContractDate() {
		return contractDate;
	}

	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPostingKey() {
		return postingKey;
	}

	public void setPostingKey(String postingKey) {
		this.postingKey = postingKey;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
}
