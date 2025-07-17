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
      "globalVelocity": [
        "VELI-EM",
        "VELI-SA"
      ],
      "suspicious": [
        "RISK-TB"
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
public class ResponsePaymentRiskInformationIpAddress implements Serializable {
	
	public ResponsePaymentRiskInformationIpAddress() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	String anonymizerStatus;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	locality;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	country;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	administrativeArea;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	routingMethod;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	carrier;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	organization;

	public String getAnonymizerStatus() {
		return anonymizerStatus;
	}

	public void setAnonymizerStatus(String anonymizerStatus) {
		this.anonymizerStatus = anonymizerStatus;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAdministrativeArea() {
		return administrativeArea;
	}

	public void setAdministrativeArea(String administrativeArea) {
		this.administrativeArea = administrativeArea;
	}

	public String getRoutingMethod() {
		return routingMethod;
	}

	public void setRoutingMethod(String routingMethod) {
		this.routingMethod = routingMethod;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	
	
	
}
