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
    "authReversal": {
      "method": "POST",
      "href": "/pts/v2/payments/6166162077966149703006/reversals"
    },
    "self": {
      "method": "GET",
      "href": "/pts/v2/payments/6166162077966149703006"
    },
    "capture": {
      "method": "POST",
      "href": "/pts/v2/payments/6166162077966149703006/captures"
    }
  },
  "clientReferenceInformation": {
    "code": "202103241603260393"
  },
  "consumerAuthenticationInformation": {
    "token": "Axj77wSTTkSwXC7w3VVeACpRHD+HfiYCojh/DvxPSB/IqEO14dJMvRi0qK5gTk05EsFwu8N1VXgA0T0a"
  },
  "errorInformation": {
    "reason": "DECISION_PROFILE_REJECT",
    "message": "The order has been rejected by Decision Manager"
  },
  "id": "6166162077966149703006",
  "orderInformation": {
    "amountDetails": {
      "authorizedAmount": "292.26",
      "currency": "USD"
    }
  },
  "paymentInformation": {
    "accountFeatures": {
      "category": "A"
    },
    "scheme": "VISA CREDIT",
    "bin": "411111",
    "accountType": "Visa Gold",
    "issuer": "RIVER VALLEY CREDIT UNION",
    "binCountry": "US"
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
  "riskInformation": {
    "localTime": "15:03:27",
    "score": {
      "result": "37",
      "factorCodes": [
        "B",
        "D",
        "G"
      ],
      "modelUsed": "default"
    },
    "infoCodes": {
      "phone": [
        "MM-ACBST"
      ],
      "identityChange": [
        "ID-M-NoHistory",
        "ID-X-NoHistory"
      ],
      "internet": [
        "FREE-EM"
      ]
    },
    "profile": {
      "earlyDecision": "ACCEPT"
    },
    "casePriority": "3"
  },
  "status": "AUTHORIZED_RISK_DECLINED",
  "submitTimeUtc": "2021-03-24T20:03:28Z"
}

 */
public class ResponsePaymentLinksAuthReversal implements Serializable {
	
	public ResponsePaymentLinksAuthReversal() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	String method;

	@JsonInclude(value = Include.NON_NULL)
	String href;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	
	
}
