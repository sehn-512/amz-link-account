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
  "accountIdentifier": "string",
  "associateName": "Generated number by Contact center",
  "contractEndDate": "2022-02-10",
  "contractStartDate": "2020-02-10",
  "customer": {
    "address1": "105 Challenger Rd.",
    "address2": "8th Floor",
    "city": "Ridgefield Park",
    "country": "US",
    "email": "michael.j@jackson5.com",
    "firstName": "Jackson",
    "lastName": "Michael",
    "phone": 2010009999,
    "state": "NJ",
    "zipcode": "07660"
  },
  "mobile": false,
  "mobileNumber": 2010009999,
  "model": "RF23R6301SR/AA",
  "orderDate": "2019-11-20",
  "orderIdentifier": "SamsungSCMP20191111190030",
  "orderItemIdentifier": "SamsungSCMP20191111190030-01",
  "purchaseDate": "2019-11-21",
  "retailItemAmount": 11.99,
  "serialNumber": 11111111111115,
  "sku": "REB-DOP36P-PB6",
  "spcNetPrice": 10.99,
  "spcPayDate": "2019-11-23",
  "spcPaymentStatus": "G",
  "spcPaymentType": "S",
  "spcTaxAmt": 0.69,
  "spcTaxRate": 6.25,
  "storeName": "Contact center"
}
 *
 */
public class RequestContractCreate implements Serializable {
	
	public RequestContractCreate() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String token;
	
	@JsonInclude(value = Include.NON_NULL)
	private String accountIdentifier;
	
	@JsonInclude(value = Include.NON_NULL)
	private String associateName;
	
	@JsonInclude(value = Include.NON_NULL)
	private String contractEndDate;
	
	@JsonInclude(value = Include.NON_NULL)
	private String contractStartDate;
	
	@JsonInclude(value = Include.NON_NULL)
	private boolean mobile;
	
	@JsonInclude(value = Include.NON_NULL)
	private String mobileNumber;
	
	@JsonInclude(value = Include.NON_NULL)
	private String model;
	
	@JsonInclude(value = Include.NON_NULL)
	private String orderDate;
	
	@JsonInclude(value = Include.NON_NULL)
	private String orderIdentifier;
	
	@JsonInclude(value = Include.NON_NULL)
	private String orderItemIdentifier;
	
	@JsonInclude(value = Include.NON_NULL)
	private String purchaseDate;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double retailItemAmount;
	
	@JsonInclude(value = Include.NON_NULL)
	private String serialNumber;
	
	@JsonInclude(value = Include.NON_NULL)
	private String sku;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double spcNetPrice;
	
	@JsonInclude(value = Include.NON_NULL)
	private String spcPayDate;
	
	@JsonInclude(value = Include.NON_NULL)
	private String spcPaymentStatus;
	
	@JsonInclude(value = Include.NON_NULL)
	private String spcPaymentType;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double spcTaxAmt;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double spcTaxRate;
	
	@JsonInclude(value = Include.NON_NULL)
	private String storeName;
	
	@JsonInclude(value = Include.NON_NULL)
	private String contractNo;
	
	@JsonInclude(value = Include.NON_NULL)
	private RequestContractCreateCustomerInfo customer;

	@JsonInclude(value = Include.NON_NULL)
	private String technicianId;

	@JsonInclude(value = Include.NON_NULL)
	private String ascNo;

	@JsonInclude(value = Include.NON_NULL)
	private String ticketNo;

	@JsonInclude(value = Include.NON_NULL)
	private double discountAmount;

	@JsonInclude(value = Include.NON_NULL)
	private String promotionType;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

	public String getAssociateName() {
		return associateName;
	}

	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}

	public String getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public boolean isMobile() {
		return mobile;
	}

	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderIdentifier() {
		return orderIdentifier;
	}

	public void setOrderIdentifier(String orderIdentifier) {
		this.orderIdentifier = orderIdentifier;
	}

	public String getOrderItemIdentifier() {
		return orderItemIdentifier;
	}

	public void setOrderItemIdentifier(String orderItemIdentifier) {
		this.orderItemIdentifier = orderItemIdentifier;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Double getRetailItemAmount() {
		return retailItemAmount;
	}

	public void setRetailItemAmount(Double retailItemAmount) {
		this.retailItemAmount = retailItemAmount;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Double getSpcNetPrice() {
		return spcNetPrice;
	}

	public void setSpcNetPrice(Double spcNetPrice) {
		this.spcNetPrice = spcNetPrice;
	}

	public String getSpcPayDate() {
		return spcPayDate;
	}

	public void setSpcPayDate(String spcPayDate) {
		this.spcPayDate = spcPayDate;
	}

	public String getSpcPaymentStatus() {
		return spcPaymentStatus;
	}

	public void setSpcPaymentStatus(String spcPaymentStatus) {
		this.spcPaymentStatus = spcPaymentStatus;
	}

	public String getSpcPaymentType() {
		return spcPaymentType;
	}

	public void setSpcPaymentType(String spcPaymentType) {
		this.spcPaymentType = spcPaymentType;
	}

	public Double getSpcTaxAmt() {
		return spcTaxAmt;
	}

	public void setSpcTaxAmt(Double spcTaxAmt) {
		this.spcTaxAmt = spcTaxAmt;
	}

	public Double getSpcTaxRate() {
		return spcTaxRate;
	}

	public void setSpcTaxRate(Double spcTaxRate) {
		this.spcTaxRate = spcTaxRate;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public RequestContractCreateCustomerInfo getCustomer() {
		return customer;
	}

	public void setCustomer(RequestContractCreateCustomerInfo customer) {
		this.customer = customer;
	}

	public String getTechnicianId() {
		return technicianId;
	}

	public void setTechnicianId(String technicianId) {
		this.technicianId = technicianId;
	}

	public String getAscNo() {
		return ascNo;
	}

	public void setAscNo(String ascNo) {
		this.ascNo = ascNo;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}
}
