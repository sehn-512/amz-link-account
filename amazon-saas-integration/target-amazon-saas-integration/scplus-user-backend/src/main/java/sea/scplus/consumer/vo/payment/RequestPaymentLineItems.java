package sea.scplus.consumer.vo.payment;

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
    "lineItems":[
    		{
    		productCode: "", //serial number
    		productName: "", //model code
    		productSku: "", //sku
    		productDescription: "", //term | DisplayProductName
    		quantity: 1,
    		unitPrice: "100.00",
    		totalPrice: "102.9",
    		taxAmount: "2.9",
    		taxRate: "0.029"
    	}
    ]
    ,
    "orderInformation": {
        "amountDetails": {
            "totalAmount": "102.21",
            "currency": "USD"
        },
        "billTo": {
            "firstName": "John",
            "lastName": "Doe",
            "address1": "1 Market St",
            "locality": "san francisco",
            "administrativeArea": "CA",
            "postalCode": "94105",
            "country": "US",
            "email": "test@cybs.com",
            "phoneNumber": "4158880000"
        }
    }
}
 *
 */
public class RequestPaymentLineItems {

	@JsonInclude(value = Include.NON_NULL)
	private String productCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String productName;
	
	@JsonInclude(value = Include.NON_NULL)
	private String productSku;
	
	@JsonInclude(value = Include.NON_NULL)
	private String productDescription;
	
	@JsonInclude(value = Include.NON_NULL)
	private String unitPrice;
	
	@JsonInclude(value = Include.NON_NULL)
	private String totalPrice;
	
	@JsonInclude(value = Include.NON_NULL)
	private String taxAmount;
	
	@JsonInclude(value = Include.NON_NULL)
	private String taxRate;
	
	@JsonInclude(value = Include.NON_NULL)
	private String quantity;

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

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
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

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	
}
