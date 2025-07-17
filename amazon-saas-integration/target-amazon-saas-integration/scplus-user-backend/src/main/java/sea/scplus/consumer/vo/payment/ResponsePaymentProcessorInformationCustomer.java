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
public class ResponsePaymentProcessorInformationCustomer implements Serializable {
	
	public ResponsePaymentProcessorInformationCustomer() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String personalIdResult;

	public String getPersonalIdResult() {
		return personalIdResult;
	}

	public void setPersonalIdResult(String personalIdResult) {
		this.personalIdResult = personalIdResult;
	}
	
	
}
