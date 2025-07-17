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
public class RequestPaymentOrderInformation {

	@JsonInclude(value = Include.NON_NULL)
	private RequestPaymentOrderInformationAmountDetails amountDetails;

	@JsonInclude(value = Include.NON_NULL)
	private RequestPaymentOrderInformationBillTo billTo;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<RequestPaymentOrderInformationLineItems> lineItems;

	private String technicianId;
	private String ascNo;
	private String ticketNo;
	private String status;
	private Double discountAmount;

	public RequestPaymentOrderInformationAmountDetails getAmountDetails() {
		return amountDetails;
	}

	public void setAmountDetails(RequestPaymentOrderInformationAmountDetails amountDetails) {
		this.amountDetails = amountDetails;
	}

	public RequestPaymentOrderInformationBillTo getBillTo() {
		return billTo;
	}

	public void setBillTo(RequestPaymentOrderInformationBillTo billTo) {
		this.billTo = billTo;
	}

	public List<RequestPaymentOrderInformationLineItems> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<RequestPaymentOrderInformationLineItems> lineItems) {
		this.lineItems = lineItems;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
}
