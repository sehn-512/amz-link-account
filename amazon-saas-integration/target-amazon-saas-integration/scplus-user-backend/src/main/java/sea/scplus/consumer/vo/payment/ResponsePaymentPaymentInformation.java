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
public class ResponsePaymentPaymentInformation implements Serializable {
	
	public ResponsePaymentPaymentInformation() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	String scheme;
	
	@JsonInclude(value = Include.NON_NULL)
	String bin;
	
	@JsonInclude(value = Include.NON_NULL)
	String accountType;
	
	@JsonInclude(value = Include.NON_NULL)
	String issuer;
	
	@JsonInclude(value = Include.NON_NULL)
	String binCountry;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentPaymentInformationCard card;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentPaymentInformationAccountFeatures accountFeatures;

	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentPaymentInformationTokenizedCard tokenizedCard;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentPaymentInformationBank bank;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentPaymentInformationCustomer customer;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentPaymentInformationInstrumentIdentifier instrumentIdentifier;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentPaymentInformationPaymentInstrument 	paymentInstrument;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentPaymentInformationShippingAddress shippingAddress;
	
	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getBinCountry() {
		return binCountry;
	}

	public void setBinCountry(String binCountry) {
		this.binCountry = binCountry;
	}

	public ResponsePaymentPaymentInformationAccountFeatures getAccountFeatures() {
		return accountFeatures;
	}

	public void setAccountFeatures(ResponsePaymentPaymentInformationAccountFeatures accountFeatures) {
		this.accountFeatures = accountFeatures;
	}

	public ResponsePaymentPaymentInformationTokenizedCard getTokenizedCard() {
		return tokenizedCard;
	}

	public void setTokenizedCard(ResponsePaymentPaymentInformationTokenizedCard tokenizedCard) {
		this.tokenizedCard = tokenizedCard;
	}

	public ResponsePaymentPaymentInformationShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ResponsePaymentPaymentInformationShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public ResponsePaymentPaymentInformationInstrumentIdentifier getInstrumentIdentifier() {
		return instrumentIdentifier;
	}

	public void setInstrumentIdentifier(ResponsePaymentPaymentInformationInstrumentIdentifier instrumentIdentifier) {
		this.instrumentIdentifier = instrumentIdentifier;
	}

	public ResponsePaymentPaymentInformationCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(ResponsePaymentPaymentInformationCustomer customer) {
		this.customer = customer;
	}

	public ResponsePaymentPaymentInformationPaymentInstrument getPaymentInstrument() {
		return paymentInstrument;
	}

	public void setPaymentInstrument(ResponsePaymentPaymentInformationPaymentInstrument paymentInstrument) {
		this.paymentInstrument = paymentInstrument;
	}

	public ResponsePaymentPaymentInformationBank getBank() {
		return bank;
	}

	public void setBank(ResponsePaymentPaymentInformationBank bank) {
		this.bank = bank;
	}

	public ResponsePaymentPaymentInformationCard getCard() {
		return card;
	}

	public void setCard(ResponsePaymentPaymentInformationCard card) {
		this.card = card;
	}
	
	
	
}
