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
public class RequestPaymentPaymentInformation {

	@JsonInclude(value = Include.NON_NULL)
	private RequestPaymentPaymentInformationCard card;

	public RequestPaymentPaymentInformationCard getCard() {
		return card;
	}

	public void setCard(RequestPaymentPaymentInformationCard card) {
		this.card = card;
	}

	
	
	
}
