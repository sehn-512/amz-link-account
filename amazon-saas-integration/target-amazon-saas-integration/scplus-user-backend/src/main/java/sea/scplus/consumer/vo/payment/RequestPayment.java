package sea.scplus.consumer.vo.payment;

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
    "clientReferenceInformation": {
        "code": "TC50171_3"
    },
    "processingInformation": {
        "capture": true
    },
    "paymentInformation": {
        "card": {
            "number": "4111111111111111",
            "securityCode": "123",
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
        "lineItems": [{
    		productCode: "", //serial number
    		productName: "", //model code
    		productSku: "", //sku
    		productDescription: "", //term
    		quantity: 1,
    		unitPrice: "100.00",
    		totalPrice: "102.9",
    		taxAmount: "2.9",
    		taxRate: "0.029"
    	}]
    }
}
 *
 */
public class RequestPayment implements Serializable {
	
	public RequestPayment() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String token;
	
	@JsonInclude(value = Include.NON_NULL)
	private String orderId;
	
	@JsonInclude(value = Include.NON_NULL)
	List<RequestPaymentOrderInformationLineItems> lineItems;
	
	@JsonInclude(value = Include.NON_NULL)
	private RequestPaymentClientReferenceInformation clientReferenceInformation;

	@JsonInclude(value = Include.NON_NULL)
	private RequestPaymentProcessingInformation processingInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private RequestPaymentPaymentInformation paymentInformation;

	@JsonInclude(value = Include.NON_NULL)
	private RequestPaymentOrderInformation orderInformation;

	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<RequestPaymentOrderInformationLineItems> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<RequestPaymentOrderInformationLineItems> lineItems) {
		this.lineItems = lineItems;
	}

	public RequestPaymentClientReferenceInformation getClientReferenceInformation() {
		return clientReferenceInformation;
	}

	public void setClientReferenceInformation(RequestPaymentClientReferenceInformation clientReferenceInformation) {
		this.clientReferenceInformation = clientReferenceInformation;
	}

	public RequestPaymentProcessingInformation getProcessingInformation() {
		return processingInformation;
	}

	public void setProcessingInformation(RequestPaymentProcessingInformation processingInformation) {
		this.processingInformation = processingInformation;
	}

	public RequestPaymentPaymentInformation getPaymentInformation() {
		return paymentInformation;
	}

	public void setPaymentInformation(RequestPaymentPaymentInformation paymentInformation) {
		this.paymentInformation = paymentInformation;
	}

	public RequestPaymentOrderInformation getOrderInformation() {
		return orderInformation;
	}

	public void setOrderInformation(RequestPaymentOrderInformation orderInformation) {
		this.orderInformation = orderInformation;
	}
	
	
}
