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
public class ResponsePaymentRiskInformation implements Serializable {
	
	public ResponsePaymentRiskInformation() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	String localTime;

	@JsonInclude(value = Include.NON_NULL)
	String casePriority;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentRiskInformationScore score;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentRiskInformationRules rules;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentRiskInformationVelocity velocity;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentRiskInformationIpAddress ipAddress;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentRiskInformationProviders providers;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentRiskInformationTravel travel;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentRiskInformationInfoCodes infoCodes;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentRiskInformationProfile profile;

	public String getLocalTime() {
		return localTime;
	}

	public void setLocalTime(String localTime) {
		this.localTime = localTime;
	}

	public String getCasePriority() {
		return casePriority;
	}

	public void setCasePriority(String casePriority) {
		this.casePriority = casePriority;
	}

	public ResponsePaymentRiskInformationScore getScore() {
		return score;
	}

	public void setScore(ResponsePaymentRiskInformationScore score) {
		this.score = score;
	}

	public ResponsePaymentRiskInformationInfoCodes getInfoCodes() {
		return infoCodes;
	}

	public void setInfoCodes(ResponsePaymentRiskInformationInfoCodes infoCodes) {
		this.infoCodes = infoCodes;
	}

	public ResponsePaymentRiskInformationProfile getProfile() {
		return profile;
	}

	public void setProfile(ResponsePaymentRiskInformationProfile profile) {
		this.profile = profile;
	}

	public ResponsePaymentRiskInformationRules getRules() {
		return rules;
	}

	public void setRules(ResponsePaymentRiskInformationRules rules) {
		this.rules = rules;
	}

	public ResponsePaymentRiskInformationVelocity getVelocity() {
		return velocity;
	}

	public void setVelocity(ResponsePaymentRiskInformationVelocity velocity) {
		this.velocity = velocity;
	}

	public ResponsePaymentRiskInformationIpAddress getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(ResponsePaymentRiskInformationIpAddress ipAddress) {
		this.ipAddress = ipAddress;
	}

	public ResponsePaymentRiskInformationTravel getTravel() {
		return travel;
	}

	public void setTravel(ResponsePaymentRiskInformationTravel travel) {
		this.travel = travel;
	}

	public ResponsePaymentRiskInformationProviders getProviders() {
		return providers;
	}

	public void setProviders(ResponsePaymentRiskInformationProviders providers) {
		this.providers = providers;
	}
	
	
}
