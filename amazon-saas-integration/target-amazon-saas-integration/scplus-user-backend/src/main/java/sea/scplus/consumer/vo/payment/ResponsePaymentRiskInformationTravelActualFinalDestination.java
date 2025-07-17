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
      "href": "/pts/v2/payments/6166169299916154503006/reversals"
    },
    "self": {
      "method": "GET",
      "href": "/pts/v2/payments/6166169299916154503006"
    },
    "capture": {
      "method": "POST",
      "href": "/pts/v2/payments/6166169299916154503006/captures"
    }
  },
  "clientReferenceInformation": {
    "code": "202103241615280396"
  },
  "consumerAuthenticationInformation": {
    "token": "Axj77wSTTkTKBII1fH9eACpRHD+HluACojh/Dy3DSB/IqEO14dJMvRi0qK5gTk05EygSCNXx/XgA/zob"
  },
  "errorInformation": {
    "reason": "DECISION_PROFILE_REJECT",
    "message": "The order has been rejected by Decision Manager"
  },
  "id": "6166169299916154503006",
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
    "localTime": "15:15:30",
    "score": {
      "result": "60",
      "factorCodes": [
        "V"
      ],
      "modelUsed": "default"
    },
    "infoCodes": {
      "phone": [
        "MM-ACBST"
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
  "submitTimeUtc": "2021-03-24T20:15:30Z"
}

 */
public class ResponsePaymentRiskInformationTravelActualFinalDestination implements Serializable {
	
	public ResponsePaymentRiskInformationTravelActualFinalDestination() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	String country;
	
	@JsonInclude(value = Include.NON_NULL)
	String locality;
	
	@JsonInclude(value = Include.NON_NULL)
	String latitude;
	
	@JsonInclude(value = Include.NON_NULL)
	String longitude;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	
	
}
