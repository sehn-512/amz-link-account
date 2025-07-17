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
public class ResponsePaymentConsumerAuthenticationInformation implements Serializable {
	
	public ResponsePaymentConsumerAuthenticationInformation() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	ResponsePaymentConsumerAuthenticationInformationIvr ivr;
	
	@JsonInclude(value = Include.NON_NULL)
	String acsRenderingType;
	
	@JsonInclude(value = Include.NON_NULL)
	String acsTransactionId;
	
	@JsonInclude(value = Include.NON_NULL)
	String acsUrl;
	
	@JsonInclude(value = Include.NON_NULL)
	String authenticationPath;
	
	@JsonInclude(value = Include.NON_NULL)
	String authorizationPayload;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	authenticationTransactionId;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	cardholderMessage;
	
	@JsonInclude(value = Include.NON_NULL)
	String cavv;
	
	@JsonInclude(value = Include.NON_NULL)
	String cavvAlgorithm;
	
	@JsonInclude(value = Include.NON_NULL)
	String challengeCancelCode;
	
	@JsonInclude(value = Include.NON_NULL)
	String challengeRequired;
	
	@JsonInclude(value = Include.NON_NULL)
	String decoupledAuthenticationIndicator;
	
	@JsonInclude(value = Include.NON_NULL)
	String directoryServerErrorCode;
	
	@JsonInclude(value = Include.NON_NULL)
	String directoryServerErrorDescription;
	
	@JsonInclude(value = Include.NON_NULL)
	String ecommerceIndicator;
	
	@JsonInclude(value = Include.NON_NULL)
	String eci;
	
	@JsonInclude(value = Include.NON_NULL)
	String eciRaw;
	
	@JsonInclude(value = Include.NON_NULL)
	String effectiveAuthenticationType;
	
	@JsonInclude(value = Include.NON_NULL)
	String networkScore;
	
	@JsonInclude(value = Include.NON_NULL)
	String pareq;
	
	@JsonInclude(value = Include.NON_NULL)
	String paresStatus;
	
	@JsonInclude(value = Include.NON_NULL)
	String proofXml;
	
	@JsonInclude(value = Include.NON_NULL)
	String proxyPan;
	
	@JsonInclude(value = Include.NON_NULL)
	String sdkTransactionId;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	signedParesStatusReason;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	specificationVersion;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	stepUpUrl;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	threeDSServerTransactionId;
	
	@JsonInclude(value = Include.NON_NULL)
	String ucafAuthenticationData;
	
	@JsonInclude(value = Include.NON_NULL)
	String ucafCollectionIndicator;
	
	@JsonInclude(value = Include.NON_NULL)
	String veresEnrolled;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	whiteListStatusSource;
	
	@JsonInclude(value = Include.NON_NULL)
	String xid;
	
	@JsonInclude(value = Include.NON_NULL)
	String directoryServerTransactionId;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	authenticationResult;
	
	@JsonInclude(value = Include.NON_NULL)
	String authenticationStatusMsg;
	
	@JsonInclude(value = Include.NON_NULL)
	String indicator;
	
	@JsonInclude(value = Include.NON_NULL)
	String interactionCounter;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	whiteListStatus;
	
	@JsonInclude(value = Include.NON_NULL)
	String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	public ResponsePaymentConsumerAuthenticationInformationIvr getIvr() {
		return ivr;
	}

	public void setIvr(ResponsePaymentConsumerAuthenticationInformationIvr ivr) {
		this.ivr = ivr;
	}

	public String getAcsRenderingType() {
		return acsRenderingType;
	}

	public void setAcsRenderingType(String acsRenderingType) {
		this.acsRenderingType = acsRenderingType;
	}

	public String getAcsTransactionId() {
		return acsTransactionId;
	}

	public void setAcsTransactionId(String acsTransactionId) {
		this.acsTransactionId = acsTransactionId;
	}

	public String getAcsUrl() {
		return acsUrl;
	}

	public void setAcsUrl(String acsUrl) {
		this.acsUrl = acsUrl;
	}

	public String getAuthenticationPath() {
		return authenticationPath;
	}

	public void setAuthenticationPath(String authenticationPath) {
		this.authenticationPath = authenticationPath;
	}

	public String getAuthorizationPayload() {
		return authorizationPayload;
	}

	public void setAuthorizationPayload(String authorizationPayload) {
		this.authorizationPayload = authorizationPayload;
	}

	public String getAuthenticationTransactionId() {
		return authenticationTransactionId;
	}

	public void setAuthenticationTransactionId(String authenticationTransactionId) {
		this.authenticationTransactionId = authenticationTransactionId;
	}

	public String getCardholderMessage() {
		return cardholderMessage;
	}

	public void setCardholderMessage(String cardholderMessage) {
		this.cardholderMessage = cardholderMessage;
	}

	public String getCavv() {
		return cavv;
	}

	public void setCavv(String cavv) {
		this.cavv = cavv;
	}

	public String getCavvAlgorithm() {
		return cavvAlgorithm;
	}

	public void setCavvAlgorithm(String cavvAlgorithm) {
		this.cavvAlgorithm = cavvAlgorithm;
	}

	public String getChallengeCancelCode() {
		return challengeCancelCode;
	}

	public void setChallengeCancelCode(String challengeCancelCode) {
		this.challengeCancelCode = challengeCancelCode;
	}

	public String getChallengeRequired() {
		return challengeRequired;
	}

	public void setChallengeRequired(String challengeRequired) {
		this.challengeRequired = challengeRequired;
	}

	public String getDecoupledAuthenticationIndicator() {
		return decoupledAuthenticationIndicator;
	}

	public void setDecoupledAuthenticationIndicator(String decoupledAuthenticationIndicator) {
		this.decoupledAuthenticationIndicator = decoupledAuthenticationIndicator;
	}

	public String getDirectoryServerErrorCode() {
		return directoryServerErrorCode;
	}

	public void setDirectoryServerErrorCode(String directoryServerErrorCode) {
		this.directoryServerErrorCode = directoryServerErrorCode;
	}

	public String getDirectoryServerErrorDescription() {
		return directoryServerErrorDescription;
	}

	public void setDirectoryServerErrorDescription(String directoryServerErrorDescription) {
		this.directoryServerErrorDescription = directoryServerErrorDescription;
	}

	public String getEcommerceIndicator() {
		return ecommerceIndicator;
	}

	public void setEcommerceIndicator(String ecommerceIndicator) {
		this.ecommerceIndicator = ecommerceIndicator;
	}

	public String getEci() {
		return eci;
	}

	public void setEci(String eci) {
		this.eci = eci;
	}

	public String getEciRaw() {
		return eciRaw;
	}

	public void setEciRaw(String eciRaw) {
		this.eciRaw = eciRaw;
	}

	public String getEffectiveAuthenticationType() {
		return effectiveAuthenticationType;
	}

	public void setEffectiveAuthenticationType(String effectiveAuthenticationType) {
		this.effectiveAuthenticationType = effectiveAuthenticationType;
	}

	public String getNetworkScore() {
		return networkScore;
	}

	public void setNetworkScore(String networkScore) {
		this.networkScore = networkScore;
	}

	public String getPareq() {
		return pareq;
	}

	public void setPareq(String pareq) {
		this.pareq = pareq;
	}

	public String getParesStatus() {
		return paresStatus;
	}

	public void setParesStatus(String paresStatus) {
		this.paresStatus = paresStatus;
	}

	public String getProofXml() {
		return proofXml;
	}

	public void setProofXml(String proofXml) {
		this.proofXml = proofXml;
	}

	public String getProxyPan() {
		return proxyPan;
	}

	public void setProxyPan(String proxyPan) {
		this.proxyPan = proxyPan;
	}

	public String getSdkTransactionId() {
		return sdkTransactionId;
	}

	public void setSdkTransactionId(String sdkTransactionId) {
		this.sdkTransactionId = sdkTransactionId;
	}

	public String getSignedParesStatusReason() {
		return signedParesStatusReason;
	}

	public void setSignedParesStatusReason(String signedParesStatusReason) {
		this.signedParesStatusReason = signedParesStatusReason;
	}

	public String getSpecificationVersion() {
		return specificationVersion;
	}

	public void setSpecificationVersion(String specificationVersion) {
		this.specificationVersion = specificationVersion;
	}

	public String getStepUpUrl() {
		return stepUpUrl;
	}

	public void setStepUpUrl(String stepUpUrl) {
		this.stepUpUrl = stepUpUrl;
	}

	public String getThreeDSServerTransactionId() {
		return threeDSServerTransactionId;
	}

	public void setThreeDSServerTransactionId(String threeDSServerTransactionId) {
		this.threeDSServerTransactionId = threeDSServerTransactionId;
	}

	public String getUcafAuthenticationData() {
		return ucafAuthenticationData;
	}

	public void setUcafAuthenticationData(String ucafAuthenticationData) {
		this.ucafAuthenticationData = ucafAuthenticationData;
	}

	public String getUcafCollectionIndicator() {
		return ucafCollectionIndicator;
	}

	public void setUcafCollectionIndicator(String ucafCollectionIndicator) {
		this.ucafCollectionIndicator = ucafCollectionIndicator;
	}

	public String getVeresEnrolled() {
		return veresEnrolled;
	}

	public void setVeresEnrolled(String veresEnrolled) {
		this.veresEnrolled = veresEnrolled;
	}

	public String getWhiteListStatusSource() {
		return whiteListStatusSource;
	}

	public void setWhiteListStatusSource(String whiteListStatusSource) {
		this.whiteListStatusSource = whiteListStatusSource;
	}

	public String getXid() {
		return xid;
	}

	public void setXid(String xid) {
		this.xid = xid;
	}

	public String getDirectoryServerTransactionId() {
		return directoryServerTransactionId;
	}

	public void setDirectoryServerTransactionId(String directoryServerTransactionId) {
		this.directoryServerTransactionId = directoryServerTransactionId;
	}

	public String getAuthenticationResult() {
		return authenticationResult;
	}

	public void setAuthenticationResult(String authenticationResult) {
		this.authenticationResult = authenticationResult;
	}

	public String getAuthenticationStatusMsg() {
		return authenticationStatusMsg;
	}

	public void setAuthenticationStatusMsg(String authenticationStatusMsg) {
		this.authenticationStatusMsg = authenticationStatusMsg;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public String getInteractionCounter() {
		return interactionCounter;
	}

	public void setInteractionCounter(String interactionCounter) {
		this.interactionCounter = interactionCounter;
	}

	public String getWhiteListStatus() {
		return whiteListStatus;
	}

	public void setWhiteListStatus(String whiteListStatus) {
		this.whiteListStatus = whiteListStatus;
	}
	
	
	
	
}
