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
  "consumerAuthenticationInformation": {
    "token": "Axj//wSTTkPrP2XyK426ACoW2YsGDBmzZuW7dRHD+GUbQCojh/DKNrSB/IqEO4ZNJMvRi0qK5DAnJpyH1n7L5FcbdAAA5Bng"
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

When return code 201 with errorInformation
{
  "clientReferenceInformation": {
    "code": "202102250945440048"
  },
  "errorInformation": {
    "reason": "INVALID_ACCOUNT",
    "message": "Decline - Invalid account number"
  },
  "id": "6142643450526644503006",
  "paymentInsightsInformation": {
    "responseInsights": {
      "categoryCode": "04",
      "category": "GENERIC_ERROR"
    }
  },
  "status": "DECLINED"
}

When return code 400
{
  "submitTimeUtc": "2016-08-11T22:47:57Z",
  "status": "SERVER_ERROR",
  "reason": "SYSTEM_ERROR",
  "message": "Unexpected system error or system timeout.",
  "details": [{
    "field": "This is the flattened JSON object field name/path that is either missing or invalid.",
    "reason": Possible reasons for the error.
			  Possible values:
				MISSING_FIELD
				INVALID_DATA
  }]
}


When return code 502
{
  "submitTimeUtc": "2016-08-11T22:47:57Z",
  "status": "SERVER_ERROR",
  "reason": "SYSTEM_ERROR",
  "message": "Unexpected system error or system timeout."
}

 *
 */
public class ResponsePayment implements Serializable {
	
	public ResponsePayment() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String id;
	
	@JsonInclude(value = Include.NON_NULL)
	private boolean success;
	
	@JsonInclude(value = Include.NON_NULL)
	private String status;
	
	@JsonInclude(value = Include.NON_NULL)
	private String submitTimeUtc;
	
	@JsonInclude(value = Include.NON_NULL)
	private String reason;
	
	@JsonInclude(value = Include.NON_NULL)
	private String message;
	
	@JsonInclude(value = Include.NON_NULL)
	private String reconciliationId;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<ResponsePaymentDetails> details;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponsePaymentLinks _links;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponsePaymentErrorInformation errorInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponsePaymentPaymentInsightsInformation paymentInsightsInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponsePaymentClientReferenceInformation clientReferenceInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponsePaymentCreditAmountDetails creditAmountDetails;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponsePaymentProcessingInformation processingInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponsePaymentProcessorInformation processorInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponsePaymentIssuerInformation issuerInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponsePaymentPaymentInformation paymentInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponsePaymentOrderInformation orderInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponsePaymentPointOfSaleInformation pointOfSaleInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponsePaymentInstallmentInformation installmentInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponsePaymentTokenInformation tokenInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponsePaymentConsumerAuthenticationInformation consumerAuthenticationInformation;
	
	@JsonInclude(value = Include.NON_NULL)
	private ResponsePaymentPaymentAccountInformation paymentAccountInformation;

	@JsonInclude(value = Include.NON_NULL)
	private ResponsePaymentRiskInformation riskInformation;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubmitTimeUtc() {
		return submitTimeUtc;
	}

	public void setSubmitTimeUtc(String submitTimeUtc) {
		this.submitTimeUtc = submitTimeUtc;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getReconciliationId() {
		return reconciliationId;
	}

	public void setReconciliationId(String reconciliationId) {
		this.reconciliationId = reconciliationId;
	}

	public ResponsePaymentPointOfSaleInformation getPointOfSaleInformation() {
		return pointOfSaleInformation;
	}

	public void setPointOfSaleInformation(ResponsePaymentPointOfSaleInformation pointOfSaleInformation) {
		this.pointOfSaleInformation = pointOfSaleInformation;
	}

	public ResponsePaymentInstallmentInformation getInstallmentInformation() {
		return installmentInformation;
	}

	public void setInstallmentInformation(ResponsePaymentInstallmentInformation installmentInformation) {
		this.installmentInformation = installmentInformation;
	}

	public ResponsePaymentTokenInformation getTokenInformation() {
		return tokenInformation;
	}

	public void setTokenInformation(ResponsePaymentTokenInformation tokenInformation) {
		this.tokenInformation = tokenInformation;
	}

	public ResponsePaymentLinks get_links() {
		return _links;
	}

	public void set_links(ResponsePaymentLinks _links) {
		this._links = _links;
	}

	public ResponsePaymentClientReferenceInformation getClientReferenceInformation() {
		return clientReferenceInformation;
	}

	public void setClientReferenceInformation(ResponsePaymentClientReferenceInformation clientReferenceInformation) {
		this.clientReferenceInformation = clientReferenceInformation;
	}

	public ResponsePaymentOrderInformation getOrderInformation() {
		return orderInformation;
	}

	public void setOrderInformation(ResponsePaymentOrderInformation orderInformation) {
		this.orderInformation = orderInformation;
	}

	public ResponsePaymentPaymentAccountInformation getPaymentAccountInformation() {
		return paymentAccountInformation;
	}

	public void setPaymentAccountInformation(ResponsePaymentPaymentAccountInformation paymentAccountInformation) {
		this.paymentAccountInformation = paymentAccountInformation;
	}

	public ResponsePaymentPaymentInformation getPaymentInformation() {
		return paymentInformation;
	}

	public void setPaymentInformation(ResponsePaymentPaymentInformation paymentInformation) {
		this.paymentInformation = paymentInformation;
	}

	public ResponsePaymentProcessorInformation getProcessorInformation() {
		return processorInformation;
	}

	public void setProcessorInformation(ResponsePaymentProcessorInformation processorInformation) {
		this.processorInformation = processorInformation;
	}

	public ResponsePaymentErrorInformation getErrorInformation() {
		return errorInformation;
	}

	public void setErrorInformation(ResponsePaymentErrorInformation errorInformation) {
		this.errorInformation = errorInformation;
	}

	public List<ResponsePaymentDetails> getDetails() {
		return details;
	}

	public void setDetails(List<ResponsePaymentDetails> details) {
		this.details = details;
	}

	public ResponsePaymentConsumerAuthenticationInformation getConsumerAuthenticationInformation() {
		return consumerAuthenticationInformation;
	}

	public void setConsumerAuthenticationInformation(
			ResponsePaymentConsumerAuthenticationInformation consumerAuthenticationInformation) {
		this.consumerAuthenticationInformation = consumerAuthenticationInformation;
	}

	public ResponsePaymentRiskInformation getRiskInformation() {
		return riskInformation;
	}

	public void setRiskInformation(ResponsePaymentRiskInformation riskInformation) {
		this.riskInformation = riskInformation;
	}

	public ResponsePaymentProcessingInformation getProcessingInformation() {
		return processingInformation;
	}

	public void setProcessingInformation(ResponsePaymentProcessingInformation processingInformation) {
		this.processingInformation = processingInformation;
	}

	public ResponsePaymentIssuerInformation getIssuerInformation() {
		return issuerInformation;
	}

	public void setIssuerInformation(ResponsePaymentIssuerInformation issuerInformation) {
		this.issuerInformation = issuerInformation;
	}

	public ResponsePaymentCreditAmountDetails getCreditAmountDetails() {
		return creditAmountDetails;
	}

	public void setCreditAmountDetails(ResponsePaymentCreditAmountDetails creditAmountDetails) {
		this.creditAmountDetails = creditAmountDetails;
	}

	public ResponsePaymentPaymentInsightsInformation getPaymentInsightsInformation() {
		return paymentInsightsInformation;
	}

	public void setPaymentInsightsInformation(ResponsePaymentPaymentInsightsInformation paymentInsightsInformation) {
		this.paymentInsightsInformation = paymentInsightsInformation;
	}
	
	

}
