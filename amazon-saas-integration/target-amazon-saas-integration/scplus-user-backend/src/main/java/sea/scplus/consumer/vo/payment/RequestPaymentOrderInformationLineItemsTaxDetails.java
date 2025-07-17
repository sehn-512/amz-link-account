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
public class RequestPaymentOrderInformationLineItemsTaxDetails {

	@JsonInclude(value = Include.NON_NULL)
	private String type;

	@JsonInclude(value = Include.NON_NULL)
	private String amount;
	
	@JsonInclude(value = Include.NON_NULL)
	private String rate;
	
	@JsonInclude(value = Include.NON_NULL)
	private String code;

	@JsonInclude(value = Include.NON_NULL)
	private String taxId;
	
	@JsonInclude(value = Include.NON_NULL)
	private String applied;
	
	@JsonInclude(value = Include.NON_NULL)
	private String exemptionCode;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getApplied() {
		return applied;
	}

	public void setApplied(String applied) {
		this.applied = applied;
	}

	public String getExemptionCode() {
		return exemptionCode;
	}

	public void setExemptionCode(String exemptionCode) {
		this.exemptionCode = exemptionCode;
	}
	
	
}
