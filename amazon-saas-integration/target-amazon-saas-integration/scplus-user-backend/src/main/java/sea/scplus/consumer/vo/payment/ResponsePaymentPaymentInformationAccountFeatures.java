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
public class ResponsePaymentPaymentInformationAccountFeatures implements Serializable {
	
	public ResponsePaymentPaymentInformationAccountFeatures() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentPaymentInformationAccountFeaturesBalances balances ;
	
	@JsonInclude(value = Include.NON_NULL)
	String accountType;
	
	@JsonInclude(value = Include.NON_NULL)
	String accountStatus;
	
	@JsonInclude(value = Include.NON_NULL)
	String balanceAmount;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	balanceAmountType;
	
	@JsonInclude(value = Include.NON_NULL)
	String balanceSign;
	
	@JsonInclude(value = Include.NON_NULL)
	String affluenceIndicator;
	
	@JsonInclude(value = Include.NON_NULL)
	String category;
	
	@JsonInclude(value = Include.NON_NULL)
	String commercial;
	
	@JsonInclude(value = Include.NON_NULL)
	String group;
	
	@JsonInclude(value = Include.NON_NULL)
	String healthCare;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	payroll;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	level3Eligible;
	
	@JsonInclude(value = Include.NON_NULL)
	String 		pinlessDebit;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	signatureDebit;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	prepaid;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	regulated;

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getBalanceAmountType() {
		return balanceAmountType;
	}

	public void setBalanceAmountType(String balanceAmountType) {
		this.balanceAmountType = balanceAmountType;
	}

	public String getBalanceSign() {
		return balanceSign;
	}

	public void setBalanceSign(String balanceSign) {
		this.balanceSign = balanceSign;
	}

	public String getAffluenceIndicator() {
		return affluenceIndicator;
	}

	public void setAffluenceIndicator(String affluenceIndicator) {
		this.affluenceIndicator = affluenceIndicator;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCommercial() {
		return commercial;
	}

	public void setCommercial(String commercial) {
		this.commercial = commercial;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getHealthCare() {
		return healthCare;
	}

	public void setHealthCare(String healthCare) {
		this.healthCare = healthCare;
	}

	public String getPayroll() {
		return payroll;
	}

	public void setPayroll(String payroll) {
		this.payroll = payroll;
	}

	public String getLevel3Eligible() {
		return level3Eligible;
	}

	public void setLevel3Eligible(String level3Eligible) {
		this.level3Eligible = level3Eligible;
	}

	public String getPinlessDebit() {
		return pinlessDebit;
	}

	public void setPinlessDebit(String pinlessDebit) {
		this.pinlessDebit = pinlessDebit;
	}

	public String getSignatureDebit() {
		return signatureDebit;
	}

	public void setSignatureDebit(String signatureDebit) {
		this.signatureDebit = signatureDebit;
	}

	public String getPrepaid() {
		return prepaid;
	}

	public void setPrepaid(String prepaid) {
		this.prepaid = prepaid;
	}

	public String getRegulated() {
		return regulated;
	}

	public void setRegulated(String regulated) {
		this.regulated = regulated;
	}

	public ResponsePaymentPaymentInformationAccountFeaturesBalances getBalances() {
		return balances;
	}

	public void setBalances(ResponsePaymentPaymentInformationAccountFeaturesBalances balances) {
		this.balances = balances;
	}
	
	

	
}
