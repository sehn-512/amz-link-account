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
    "merchantAdvice":{
      "code":"00"
    },
    "avs": {
      "code": "Y",
      "codeRaw": "Y"
    }
  },
  "status": "AUTHORIZED",
  "submitTimeUtc": "2021-02-22T05:12:43Z"
}

 */
public class ResponsePaymentProcessorInformation implements Serializable {
	
	public ResponsePaymentProcessorInformation() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String authIndicator;
	
	@JsonInclude(value = Include.NON_NULL)
	private String approvalCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String networkTransactionId;
	
	@JsonInclude(value = Include.NON_NULL)
	private String providerTransactionId;
	
	@JsonInclude(value = Include.NON_NULL)
	private String transactionId;
	
	@JsonInclude(value = Include.NON_NULL)
	private String responseCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String responseCodeSource;
	
	@JsonInclude(value = Include.NON_NULL)
	private String responseDetails;
	
	@JsonInclude(value = Include.NON_NULL)
	private String responseCategoryCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String forwardedAcquirerCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String systemTraceAuditNumber;
	
	@JsonInclude(value = Include.NON_NULL)
	private String 	paymentAccountReferenceNumber;
	
	@JsonInclude(value = Include.NON_NULL)
	private String transactionIntegrityCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String amexVerbalAuthReferenceNumber;
	
	@JsonInclude(value = Include.NON_NULL)
	private String masterCardServiceCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String masterCardServiceReplyCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String masterCardAuthenticationType;
	
	@JsonInclude(value = Include.NON_NULL)
	private String name;
	
	@JsonInclude(value = Include.NON_NULL)
	private String merchantNumber;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentProcessorInformationAvs avs;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentProcessorInformationCustomer customer;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentProcessorInformationRouting routing;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentProcessorInformationElectronicVerificationResults electronicVerificationResults;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentProcessorInformationAchVerification achVerification;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentProcessorInformationCardVerification cardVerification;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentProcessorInformationMerchantAdvice merchantAdvice;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentProcessorInformationConsumerAuthenticationResponse consumerAuthenticationResponse;

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public String getNetworkTransactionId() {
		return networkTransactionId;
	}

	public void setNetworkTransactionId(String networkTransactionId) {
		this.networkTransactionId = networkTransactionId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public ResponsePaymentProcessorInformationAvs getAvs() {
		return avs;
	}

	public void setAvs(ResponsePaymentProcessorInformationAvs avs) {
		this.avs = avs;
	}

	public ResponsePaymentProcessorInformationMerchantAdvice getMerchantAdvice() {
		return merchantAdvice;
	}

	public void setMerchantAdvice(ResponsePaymentProcessorInformationMerchantAdvice merchantAdvice) {
		this.merchantAdvice = merchantAdvice;
	}

	public ResponsePaymentProcessorInformationConsumerAuthenticationResponse getConsumerAuthenticationResponse() {
		return consumerAuthenticationResponse;
	}

	public void setConsumerAuthenticationResponse(
			ResponsePaymentProcessorInformationConsumerAuthenticationResponse consumerAuthenticationResponse) {
		this.consumerAuthenticationResponse = consumerAuthenticationResponse;
	}

	public ResponsePaymentProcessorInformationCardVerification getCardVerification() {
		return cardVerification;
	}

	public void setCardVerification(ResponsePaymentProcessorInformationCardVerification cardVerification) {
		this.cardVerification = cardVerification;
	}

	public String getAuthIndicator() {
		return authIndicator;
	}

	public void setAuthIndicator(String authIndicator) {
		this.authIndicator = authIndicator;
	}

	public String getProviderTransactionId() {
		return providerTransactionId;
	}

	public void setProviderTransactionId(String providerTransactionId) {
		this.providerTransactionId = providerTransactionId;
	}

	public String getResponseCodeSource() {
		return responseCodeSource;
	}

	public void setResponseCodeSource(String responseCodeSource) {
		this.responseCodeSource = responseCodeSource;
	}

	public String getResponseDetails() {
		return responseDetails;
	}

	public void setResponseDetails(String responseDetails) {
		this.responseDetails = responseDetails;
	}

	public String getResponseCategoryCode() {
		return responseCategoryCode;
	}

	public void setResponseCategoryCode(String responseCategoryCode) {
		this.responseCategoryCode = responseCategoryCode;
	}

	public String getForwardedAcquirerCode() {
		return forwardedAcquirerCode;
	}

	public void setForwardedAcquirerCode(String forwardedAcquirerCode) {
		this.forwardedAcquirerCode = forwardedAcquirerCode;
	}

	public String getSystemTraceAuditNumber() {
		return systemTraceAuditNumber;
	}

	public void setSystemTraceAuditNumber(String systemTraceAuditNumber) {
		this.systemTraceAuditNumber = systemTraceAuditNumber;
	}

	public String getPaymentAccountReferenceNumber() {
		return paymentAccountReferenceNumber;
	}

	public void setPaymentAccountReferenceNumber(String paymentAccountReferenceNumber) {
		this.paymentAccountReferenceNumber = paymentAccountReferenceNumber;
	}

	public String getTransactionIntegrityCode() {
		return transactionIntegrityCode;
	}

	public void setTransactionIntegrityCode(String transactionIntegrityCode) {
		this.transactionIntegrityCode = transactionIntegrityCode;
	}

	public String getAmexVerbalAuthReferenceNumber() {
		return amexVerbalAuthReferenceNumber;
	}

	public void setAmexVerbalAuthReferenceNumber(String amexVerbalAuthReferenceNumber) {
		this.amexVerbalAuthReferenceNumber = amexVerbalAuthReferenceNumber;
	}

	public String getMasterCardServiceCode() {
		return masterCardServiceCode;
	}

	public void setMasterCardServiceCode(String masterCardServiceCode) {
		this.masterCardServiceCode = masterCardServiceCode;
	}

	public String getMasterCardServiceReplyCode() {
		return masterCardServiceReplyCode;
	}

	public void setMasterCardServiceReplyCode(String masterCardServiceReplyCode) {
		this.masterCardServiceReplyCode = masterCardServiceReplyCode;
	}

	public String getMasterCardAuthenticationType() {
		return masterCardAuthenticationType;
	}

	public void setMasterCardAuthenticationType(String masterCardAuthenticationType) {
		this.masterCardAuthenticationType = masterCardAuthenticationType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMerchantNumber() {
		return merchantNumber;
	}

	public void setMerchantNumber(String merchantNumber) {
		this.merchantNumber = merchantNumber;
	}

	public ResponsePaymentProcessorInformationRouting getRouting() {
		return routing;
	}

	public void setRouting(ResponsePaymentProcessorInformationRouting routing) {
		this.routing = routing;
	}

	public ResponsePaymentProcessorInformationElectronicVerificationResults getElectronicVerificationResults() {
		return electronicVerificationResults;
	}

	public void setElectronicVerificationResults(
			ResponsePaymentProcessorInformationElectronicVerificationResults electronicVerificationResults) {
		this.electronicVerificationResults = electronicVerificationResults;
	}
	
	

}
