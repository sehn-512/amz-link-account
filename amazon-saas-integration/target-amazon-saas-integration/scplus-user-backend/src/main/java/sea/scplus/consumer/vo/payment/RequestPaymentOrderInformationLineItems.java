package sea.scplus.consumer.vo.payment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
{
    "clientReferenceInformation": {
        "code": "TC50171_3"
    },
    "processingInformation": {
        "capture": true
    },
    "paymentInformation": {
        "card": {
            "number": "4111111111111111",
            "expirationMonth": "12",
            "expirationYear": "2031"
        }
    },
    "orderInformation": {
        "amountDetails": {
            "totalAmount": "102.21",
            "currency": "USD"
        },
        "billTo": {
            "firstName": "John",
            "lastName": "Doe",
            "address1": "1 Market St",
            "address2": "no room",
            "locality": "san francisco",
            "administrativeArea": "CA",
            "postalCode": "94105",
            "country": "US",
            "email": "test@cybs.com",
            "phoneNumber": "4158880000"
        },
        "lineItems": [
	      {
	        "productCode": "",
	        "productName": "",
	        "productSku": "",
	        "quantity": "",
	        "unitPrice": "",
	        "unitOfMeasure": "",
	        "totalAmount": "",
	        "taxAmount": "",
	        "taxRate": "",
	        "taxAppliedAfterDiscount": "",
	        "taxStatusIndicator": "",
	        "taxTypeCode": "",
	        "amountIncludesTax": "",
	        "typeOfSupply": "",
	        "commodityCode": "",
	        "discountAmount": "",
	        "discountApplied": "",
	        "discountRate": "",
	        "invoiceNumber": "",
	        "taxDetails": [
	          {
	            "type": "",
	            "amount": "",
	            "rate": "",
	            "code": "",
	            "taxId": "",
	            "applied": "",
	            "exemptionCode": ""
	          }],
	        "productDescription": "",
	    }]
    }
}
 *
 */
public class RequestPaymentOrderInformationLineItems {

	@JsonInclude(value = Include.NON_NULL)
	private String productCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String productDescription;

	@JsonInclude(value = Include.NON_NULL)
	private String productName;
	
	@JsonInclude(value = Include.NON_NULL)
	private String productSku;
	
	@JsonInclude(value = Include.NON_NULL)
	private String quantity;

	@JsonInclude(value = Include.NON_NULL)
	private String unitPrice;
	
	@JsonInclude(value = Include.NON_NULL)
	private String unitOfMeasure;
	
	@JsonInclude(value = Include.NON_NULL)
	private String totalAmount;
	
	@JsonInclude(value = Include.NON_NULL)
	private String taxAmount;
	
	@JsonInclude(value = Include.NON_NULL)
	private String taxRate;
	
	@JsonInclude(value = Include.NON_NULL)
	private String taxAppliedAfterDiscount;
	
	@JsonInclude(value = Include.NON_NULL)
	private String taxStatusIndicator;
	
	@JsonInclude(value = Include.NON_NULL)
	private String taxTypeCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String amountIncludesTax;
	
	@JsonInclude(value = Include.NON_NULL)
	private String typeOfSupply;
	
	@JsonInclude(value = Include.NON_NULL)
	private String commodityCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String discountAmount;
	
	@JsonInclude(value = Include.NON_NULL)
	private String discountApplied;
	
	@JsonInclude(value = Include.NON_NULL)
	private String discountRate;
	
	@JsonInclude(value = Include.NON_NULL)
	private String invoiceNumber;

	List<RequestPaymentOrderInformationLineItemsTaxDetails> taxDetails;

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getTaxAppliedAfterDiscount() {
		return taxAppliedAfterDiscount;
	}

	public void setTaxAppliedAfterDiscount(String taxAppliedAfterDiscount) {
		this.taxAppliedAfterDiscount = taxAppliedAfterDiscount;
	}

	public String getTaxStatusIndicator() {
		return taxStatusIndicator;
	}

	public void setTaxStatusIndicator(String taxStatusIndicator) {
		this.taxStatusIndicator = taxStatusIndicator;
	}

	public String getTaxTypeCode() {
		return taxTypeCode;
	}

	public void setTaxTypeCode(String taxTypeCode) {
		this.taxTypeCode = taxTypeCode;
	}

	public String getAmountIncludesTax() {
		return amountIncludesTax;
	}

	public void setAmountIncludesTax(String amountIncludesTax) {
		this.amountIncludesTax = amountIncludesTax;
	}

	public String getTypeOfSupply() {
		return typeOfSupply;
	}

	public void setTypeOfSupply(String typeOfSupply) {
		this.typeOfSupply = typeOfSupply;
	}

	public String getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getDiscountApplied() {
		return discountApplied;
	}

	public void setDiscountApplied(String discountApplied) {
		this.discountApplied = discountApplied;
	}

	public String getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public List<RequestPaymentOrderInformationLineItemsTaxDetails> getTaxDetails() {
		return taxDetails;
	}

	public void setTaxDetails(List<RequestPaymentOrderInformationLineItemsTaxDetails> taxDetails) {
		this.taxDetails = taxDetails;
	}

	
}
