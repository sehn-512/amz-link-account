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
public class ResponsePaymentProcessorInformationElectronicVerificationResults implements Serializable {
	
	public ResponsePaymentProcessorInformationElectronicVerificationResults() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String codeRaw;

	@JsonInclude(value = Include.NON_NULL)
	private String email;
	
	@JsonInclude(value = Include.NON_NULL)
	private String emailRaw;
	
	@JsonInclude(value = Include.NON_NULL)
	private String phoneNumber;
	
	@JsonInclude(value = Include.NON_NULL)
	private String phoneNumberRaw;
	
	@JsonInclude(value = Include.NON_NULL)
	private String postalCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String postalCodeRaw;
	
	@JsonInclude(value = Include.NON_NULL)
	private String street;
	
	@JsonInclude(value = Include.NON_NULL)
	private String streetRaw;
	
	@JsonInclude(value = Include.NON_NULL)
	private String name;
	
	@JsonInclude(value = Include.NON_NULL)
	private String nameRaw;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeRaw() {
		return codeRaw;
	}

	public void setCodeRaw(String codeRaw) {
		this.codeRaw = codeRaw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailRaw() {
		return emailRaw;
	}

	public void setEmailRaw(String emailRaw) {
		this.emailRaw = emailRaw;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumberRaw() {
		return phoneNumberRaw;
	}

	public void setPhoneNumberRaw(String phoneNumberRaw) {
		this.phoneNumberRaw = phoneNumberRaw;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPostalCodeRaw() {
		return postalCodeRaw;
	}

	public void setPostalCodeRaw(String postalCodeRaw) {
		this.postalCodeRaw = postalCodeRaw;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetRaw() {
		return streetRaw;
	}

	public void setStreetRaw(String streetRaw) {
		this.streetRaw = streetRaw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameRaw() {
		return nameRaw;
	}

	public void setNameRaw(String nameRaw) {
		this.nameRaw = nameRaw;
	}
	
	

}
