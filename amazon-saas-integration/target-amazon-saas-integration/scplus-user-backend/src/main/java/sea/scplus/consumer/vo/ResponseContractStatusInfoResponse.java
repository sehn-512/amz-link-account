package sea.scplus.consumer.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
{
  "code": 0,
  "contractStatusInfoResponse": {
    "email": "michael.j@jackson5.com",
    "firstName": "Jackson",
    "lastName": "Michael",
    "contractEndDate": "string",
    "contractNo": "string",
    "contractStartDate": "string",
    "modelCode": "string",
    "modelType": "string",
    "orderDate": "string",
    "purchaseDate": "string",
    "serialNo": "string",
    "spcPrice": 0,
    "retailitemAmt": 0
    "spcSku": "string",
    "skudesc": "string",
    "status": "string",
    "term": 0,
    "cancelAvailable": boolean
  },
  "msg": "string",
  "success": true
}
 *
 */
public class ResponseContractStatusInfoResponse implements Serializable {
	
	public ResponseContractStatusInfoResponse() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private boolean cancelAvailable;
	
	@JsonInclude(value = Include.NON_NULL)
	private String email;
	
	@JsonInclude(value = Include.NON_NULL)
	private String cancelDesc;
	
	@JsonInclude(value = Include.NON_NULL)
	private String firstName;
	
	@JsonInclude(value = Include.NON_NULL)
	private String lastName;
	
	@JsonInclude(value = Include.NON_NULL)
	private String contractEndDate;
	
	@JsonInclude(value = Include.NON_NULL)
	private String contractNo;
	
	@JsonInclude(value = Include.NON_NULL)
	private String contractStartDate;
	
	@JsonInclude(value = Include.NON_NULL)
	private String modelCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String modelType;
	
	@JsonInclude(value = Include.NON_NULL)
	private String orderDate;
	
	@JsonInclude(value = Include.NON_NULL)
	private String purchaseDate;
	
	@JsonInclude(value = Include.NON_NULL)
	private String serialNo;
	
	@JsonInclude(value = Include.NON_NULL)
	private String spcPrice;
	
	@JsonInclude(value = Include.NON_NULL)
	private String retailitemAmt;
	
	@JsonInclude(value = Include.NON_NULL)
	private String spcSku;
	
	@JsonInclude(value = Include.NON_NULL)
	private String term;
	
	@JsonInclude(value = Include.NON_NULL)
	private String status;
	
	@JsonInclude(value = Include.NON_NULL)
	private String contractStatus;
	
	@JsonInclude(value = Include.NON_NULL)
	private String skudesc;
	
	public boolean isCancelAvailable() {
		return cancelAvailable;
	}

	public void setCancelAvailable(boolean cancelAvailable) {
		this.cancelAvailable = cancelAvailable;
	}

	public String getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getSpcPrice() {
		return spcPrice;
	}

	public void setSpcPrice(String spcPrice) {
		this.spcPrice = spcPrice;
	}

	public String getSpcSku() {
		return spcSku;
	}

	public void setSpcSku(String spcSku) {
		this.spcSku = spcSku;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSkudesc() {
		return skudesc;
	}

	public void setSkudesc(String skudesc) {
		this.skudesc = skudesc;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	
	public String getCancelDesc() {
		return cancelDesc;
	}

	public void setCancelDesc(String cancelDesc) {
		this.cancelDesc = cancelDesc;
	}

	public String getRetailitemAmt() {
		
		DecimalFormat form = new DecimalFormat("#0.00");
	    double dNumber = 0.00;
	    
	    if ( this.retailitemAmt != null ) {
	    	dNumber = Double.parseDouble(this.retailitemAmt);
	    }
		
		return ""+form.format(dNumber);
	}

	public void setRetailitemAmt(String retailitemAmt) {
		this.retailitemAmt = retailitemAmt;
	}
	
	

}
