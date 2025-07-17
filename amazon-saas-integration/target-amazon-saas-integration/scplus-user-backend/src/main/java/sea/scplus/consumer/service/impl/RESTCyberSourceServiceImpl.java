package sea.scplus.consumer.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import sea.scplus.consumer.common.constant.CommonValue;
import sea.scplus.consumer.common.exception.BadRequestException;
import sea.scplus.consumer.service.RESTCommonService;
import sea.scplus.consumer.service.RESTCyberSourceService;
import sea.scplus.consumer.vo.RequestContractCancel;
import sea.scplus.consumer.vo.RequestContractCreate;
import sea.scplus.consumer.vo.RequestContractStatus;
import sea.scplus.consumer.vo.RequestSerialNoValidation;
import sea.scplus.consumer.vo.RequestSpcValidation;
import sea.scplus.consumer.vo.ResponseContractCancel;
import sea.scplus.consumer.vo.ResponseContractCreate;
import sea.scplus.consumer.vo.ResponseContractStatus;
import sea.scplus.consumer.vo.ResponseSerialNoValidation;
import sea.scplus.consumer.vo.ResponseSpcValidation;
import sea.scplus.consumer.vo.cancel.RequestCancel;
import sea.scplus.consumer.vo.cancel.ResponseCancel;
import sea.scplus.consumer.vo.payment.RequestPayment;
import sea.scplus.consumer.vo.payment.ResponsePayment;

@Service
public class RESTCyberSourceServiceImpl implements RESTCyberSourceService {
	
	private final Log LOGGER = LogFactory.getLog(getClass());

	@Value("${spring.profiles.active}")
	private String server_mode;
	
	@Value("${cs.host}")
	private String spHost;
	
	@Value("${cs.baseURL}")
	private String spBaseUrl;
	
	@Value("${cs.merchantID}")
	private String cs_merchantID;
	
	@Value("${cs.merchantKeyIdSignature}")
	private String cs_merchantKeyIdSignature;
	
	@Value("${cs.merchantsecretKeyDigest}")
	private String cs_merchantsecretKeyDigest;
	
	@Value("${cs.paymentURL}")
	private String cs_paymentURL;
	
	@Value("${cs.cancelURL}")
	private String cs_cancelURL;

	public static String GenerateDigest(String bodyText) throws NoSuchAlgorithmException {
//	     String bodyText = "{ your JSON payload }";
	     MessageDigest md = MessageDigest.getInstance("SHA-256");
	     md.update(bodyText.getBytes(StandardCharsets.UTF_8));
	     byte[] digest = md.digest();
	     return "SHA-256=" + Base64.getEncoder().encodeToString(digest);
	}
	
	public String GenerateHeaderString(String host, String date, String requestURI, String digest) {
		
		StringBuilder signatureHeaderValue = new StringBuilder();
		 signatureHeaderValue.append("host: " + host);
		 signatureHeaderValue.append("\ndate: " + date);
		 signatureHeaderValue.append("\n(request-target): post /" + requestURI);
		 signatureHeaderValue.append("\ndigest: " + digest);
		 signatureHeaderValue.append("\nv-c-merchant-id: " + cs_merchantID);
		 
		return signatureHeaderValue.toString();
		 
	}
	
	public static String GenerateSignatureFromParams(String keyString, String signatureParams) throws InvalidKeyException, NoSuchAlgorithmException {
		byte[] decodedKey = Base64.getDecoder().decode(keyString);
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
		Mac hmacSha256 = Mac.getInstance("HmacSHA256");
		hmacSha256.init(originalKey);
		hmacSha256.update(signatureParams.getBytes());
		byte[] HmachSha256DigestBytes = hmacSha256.doFinal();
		return Base64.getEncoder().encodeToString(HmachSha256DigestBytes);
	}
	
	/**
	 * 
	 * 
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
	@Override
	public ResponsePayment payment(RequestPayment requestPayment) {
		
		ResponsePayment responsePayment = new ResponsePayment();
		String response = "";
		try 
		{
			String functionPath = cs_paymentURL;
			String apiURL		= spBaseUrl;
			
			String date = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")));
			
			java.net.URL url = null;
			if ( "local".equals(server_mode)) {
				url 			= new URL(apiURL+functionPath);
				
			} else {
				url 			= new URL(null, apiURL+functionPath, new sun.net.www.protocol.https.Handler() );
				
			}
		
			ObjectMapper mapper = new ObjectMapper();
			
	        String urlParameters  = mapper.writeValueAsString(requestPayment);
	        LOGGER.debug("urlParameters : " + urlParameters); 
	        JSONObject jsonObject = new JSONObject(urlParameters);
	         
	        byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");
		    String strDigest = GenerateDigest(jsonObject.toString());
		    String signatureParams = GenerateHeaderString(spHost, date, cs_paymentURL, strDigest);
		    String signature = GenerateSignatureFromParams(cs_merchantsecretKeyDigest, signatureParams);
		    String strSignature = "keyid=\""+cs_merchantKeyIdSignature+"\", algorithm=\"HmacSHA256\", headers=\"host date (request-target) digest v-c-merchant-id\", signature=\""+signature+"\"";
			
		    LOGGER.debug("date : " + date);
		    LOGGER.debug("strDigest : " + strDigest);
		    LOGGER.debug("strSignature : " + strSignature);
		    
		    HttpURLConnection con 	= (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
		    con.setRequestProperty("v-c-merchant-id", cs_merchantID);
		    con.setRequestProperty("date", date);
		    con.setRequestProperty("host", spHost);
		    con.setRequestProperty("digest", strDigest);
		    con.setRequestProperty("signature", strSignature);
		    
		    con.setDoOutput(true);
		    
		    ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() 
			 { 
			 @Override
			 public boolean verify(String hostname, SSLSession session) {
			 return true;
			 } 
			 });
			
		    try {
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.write(postDataBytes);
				wr.flush();
				wr.close();
			}catch(Exception e){
				e.printStackTrace();
				throw e;
			}
		    
			int responseCode = con.getResponseCode();
			
			LOGGER.debug("responseCode : " + responseCode);
			
			
        	BufferedReader br;
        	StringBuilder sb = new StringBuilder();
        	String line;
        	if (responseCode == 200 || responseCode == 201) {
        		br = new BufferedReader(new InputStreamReader(con.getInputStream()));
           
        	} else {
        		br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
           
        	}
        	
        	while ((line = br.readLine()) != null) {
        		sb.append(line);
        	}
        	
        	br.close();
        	con.disconnect();
	
        	LOGGER.debug(sb.toString());
        	String resJson = sb.toString();	
        	LOGGER.debug(resJson);
	           
			try{
				
				responsePayment = mapper.reader().forType(ResponsePayment.class).readValue(resJson);
				if (responseCode == 200 || responseCode == 201) {
					responsePayment.setSuccess(true);
					LOGGER.debug("success : " + response.toString());
					LOGGER.debug("myResponse : " + responsePayment);
					
				} else {
					response = "fail";
					responsePayment.setSuccess(false);
					LOGGER.debug("fail : " + resJson);
				}
				

			}catch(Exception e){
				e.printStackTrace();
			}
	           
		} catch (Throwable e) {

			e.printStackTrace();
		}
		return responsePayment;
		
	}
	
	
	/**
	 * 
	 * 
request
{
    "clientReferenceInformation": {
        "code": "test_void"
    }
}


response
{
  "_links": {
    "self": {
      "method": "GET",
      "href": "/pts/v2/voids/6143961494646116603006"
    }
  },
  "clientReferenceInformation": {
    "code": "202102261938150104"
  },
  "id": "6143961494646116603006",
  "orderInformation": {
    "amountDetails": {
      "currency": "USD"
    }
  },
  "status": "VOIDED",
  "submitTimeUtc": "2021-02-27T03:22:29Z",
  "voidAmountDetails": {
    "currency": "usd",
    "voidAmount": "210.41"
  }
}

{
  "id": "6143963336366129503004",
  "submitTimeUtc": "2021-02-27T03:25:33Z",
  "status": "INVALID_REQUEST",
  "reason": "NOT_VOIDABLE",
  "message": "Decline - The capture or credit is not voidable because the capture or credit information has already been submitted to your processor. Or, you requested a void for a type of transaction that cannot be voided."
}
	 */
	@Override
	public ResponseCancel cancel(RequestCancel requestCancel) {
		
		ResponseCancel responseCancel = new ResponseCancel();
		String response = "";
		try 
		{
			String functionPath = cs_cancelURL;
			String apiURL		= spBaseUrl;
			
			String cs_id = requestCancel.getCs_id();
			functionPath = functionPath.replaceAll("tmpid", cs_id);
			
			String date = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")));
			
			java.net.URL url = null;
			if ( "local".equals(server_mode)) {
				url 			= new URL(apiURL+functionPath);
				
			} else {
				url 			= new URL(null, apiURL+functionPath, new sun.net.www.protocol.https.Handler() );
				
			}
		
			ObjectMapper mapper = new ObjectMapper();
			
	        String urlParameters  = mapper.writeValueAsString(requestCancel);
	        LOGGER.debug("urlParameters : " + urlParameters); 
	        JSONObject jsonObject = new JSONObject(urlParameters);
	         
	        byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");
		    String strDigest = GenerateDigest(jsonObject.toString());
		    String signatureParams = GenerateHeaderString(spHost, date, functionPath, strDigest);
		    String signature = GenerateSignatureFromParams(cs_merchantsecretKeyDigest, signatureParams);
		    String strSignature = "keyid=\""+cs_merchantKeyIdSignature+"\", algorithm=\"HmacSHA256\", headers=\"host date (request-target) digest v-c-merchant-id\", signature=\""+signature+"\"";
			
		    LOGGER.debug("date : " + date);
		    LOGGER.debug("strDigest : " + strDigest);
		    LOGGER.debug("strSignature : " + strSignature);
		    
		    HttpURLConnection con 	= (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
		    con.setRequestProperty("v-c-merchant-id", cs_merchantID);
		    con.setRequestProperty("date", date);
		    con.setRequestProperty("host", spHost);
		    con.setRequestProperty("digest", strDigest);
		    con.setRequestProperty("signature", strSignature);
		    
		    con.setDoOutput(true);
			
		    ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() 
			 { 
			 @Override
			 public boolean verify(String hostname, SSLSession session) {
			 return true;
			 } 
			 });
		    
		    try {
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.write(postDataBytes);
				wr.flush();
				wr.close();
			}catch(Exception e){
				e.printStackTrace();
				throw e;
			}
		    
			int responseCode = con.getResponseCode();
			
			LOGGER.debug("responseCode : " + responseCode);
			
			
        	BufferedReader br;
        	StringBuilder sb = new StringBuilder();
        	String line;
        	if (responseCode == 200 || responseCode == 201) {
        		br = new BufferedReader(new InputStreamReader(con.getInputStream()));
           
        	} else {
        		br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
           
        	}
        	
        	while ((line = br.readLine()) != null) {
        		sb.append(line);
        	}
        	
        	br.close();
        	con.disconnect();
	
        	LOGGER.debug(sb.toString());
        	String resJson = sb.toString();	
        	LOGGER.debug(resJson);
	           
			try{
				
				responseCancel = mapper.reader().forType(ResponseCancel.class).readValue(resJson);
				if (responseCode == 200 || responseCode == 201) {
					responseCancel.setSuccess(true);
					LOGGER.debug("success : " + response.toString());
					LOGGER.debug("myResponse : " + responseCancel);
					
				} else {
					response = "fail";
					responseCancel.setSuccess(false);
					LOGGER.debug("fail : " + resJson);
				}
				

			}catch(Exception e){
				e.printStackTrace();
			}
	           
		} catch (Throwable e) {

			e.printStackTrace();
		}
		return responseCancel;
		
	}
	
	

}
