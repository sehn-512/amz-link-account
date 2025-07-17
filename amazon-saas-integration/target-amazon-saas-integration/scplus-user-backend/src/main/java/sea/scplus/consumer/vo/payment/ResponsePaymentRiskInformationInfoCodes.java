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
public class ResponsePaymentRiskInformationInfoCodes implements Serializable {
	
	public ResponsePaymentRiskInformationInfoCodes() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	String[] velocity;
	
	@JsonInclude(value = Include.NON_NULL)
	String[] address;
	
	@JsonInclude(value = Include.NON_NULL)
	String[] customerList;
	
	@JsonInclude(value = Include.NON_NULL)
	String[] deviceBehavior;
	
	@JsonInclude(value = Include.NON_NULL)
	String[] identityChange;
	
	@JsonInclude(value = Include.NON_NULL)
	String[] internet;
	
	@JsonInclude(value = Include.NON_NULL)
	String[] globalVelocity;

	@JsonInclude(value = Include.NON_NULL)
	String[] phone;
	
	@JsonInclude(value = Include.NON_NULL)
	String[] suspicious;

	public String[] getInternet() {
		return internet;
	}

	public void setInternet(String[] internet) {
		this.internet = internet;
	}

	public String[] getPhone() {
		return phone;
	}

	public void setPhone(String[] phone) {
		this.phone = phone;
	}

	public String[] getGlobalVelocity() {
		return globalVelocity;
	}

	public void setGlobalVelocity(String[] globalVelocity) {
		this.globalVelocity = globalVelocity;
	}

	public String[] getSuspicious() {
		return suspicious;
	}

	public void setSuspicious(String[] suspicious) {
		this.suspicious = suspicious;
	}

	public String[] getVelocity() {
		return velocity;
	}

	public void setVelocity(String[] velocity) {
		this.velocity = velocity;
	}

	public String[] getAddress() {
		return address;
	}

	public void setAddress(String[] address) {
		this.address = address;
	}

	public String[] getCustomerList() {
		return customerList;
	}

	public void setCustomerList(String[] customerList) {
		this.customerList = customerList;
	}

	public String[] getDeviceBehavior() {
		return deviceBehavior;
	}

	public void setDeviceBehavior(String[] deviceBehavior) {
		this.deviceBehavior = deviceBehavior;
	}

	public String[] getIdentityChange() {
		return identityChange;
	}

	public void setIdentityChange(String[] identityChange) {
		this.identityChange = identityChange;
	}
	
	
	
}
