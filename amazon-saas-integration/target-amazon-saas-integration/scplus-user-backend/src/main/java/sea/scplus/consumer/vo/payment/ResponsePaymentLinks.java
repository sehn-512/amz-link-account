package sea.scplus.consumer.vo.payment;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * 
When return code 201
{
  "_links": {
    "void": {
      "method": "POST",
      "href": "/pts/v2/payments/6139707629806620603002/voids"
    },
    "self": {
      "method": "GET",
      "href": "/pts/v2/payments/6139707629806620603002"
    }
  },
  "clientReferenceInformation": {
    "code": "TC50171_3"
  },
  "id": "6139707629806620603002",
  "orderInformation": {
    "amountDetails": {
      "totalAmount": "102.21",
      "authorizedAmount": "102.21",
      "currency": "USD"
    }
  },
  "paymentAccountInformation": {
    "card": {
      "type": "001"
    }
  },
  "paymentInformation": {
    "accountFeatures": {
      "category": "A"
    },
    "tokenizedCard": {
      "type": "001"
    }
  },
  "processorInformation": {
    "approvalCode": "831000",
    "networkTransactionId": "558196000003814",
    "transactionId": "558196000003814",
    "responseCode": "000",
    "avs": {
      "code": "Y",
      "codeRaw": "Y"
    }
  },
  "status": "AUTHORIZED",
  "submitTimeUtc": "2021-02-22T05:12:43Z"
}

 */
public class ResponsePaymentLinks implements Serializable {
	
	public ResponsePaymentLinks() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentLinksAuthReversal authReversal;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentLinksCapture capture;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentLinksVoid VOID;

	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentLinksSelf self;

	@JsonProperty("void")
	public ResponsePaymentLinksVoid getVOID() {
		return VOID;
	}

	public void setVOID(ResponsePaymentLinksVoid vOID) {
		VOID = vOID;
	}

	public ResponsePaymentLinksSelf getSelf() {
		return self;
	}

	public void setSelf(ResponsePaymentLinksSelf self) {
		this.self = self;
	}

	public ResponsePaymentLinksAuthReversal getAuthReversal() {
		return authReversal;
	}

	public void setAuthReversal(ResponsePaymentLinksAuthReversal authReversal) {
		this.authReversal = authReversal;
	}

	public ResponsePaymentLinksCapture getCapture() {
		return capture;
	}

	public void setCapture(ResponsePaymentLinksCapture capture) {
		this.capture = capture;
	}
	
	
	
}
