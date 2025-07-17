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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
import sea.scplus.consumer.common.util.TimezoneDateTimeUtil;
import sea.scplus.consumer.persistence.api.RESTCommonMapper;
import sea.scplus.consumer.service.FrontCommonService;
import sea.scplus.consumer.service.RESTCommonService;
import sea.scplus.consumer.service.RESTCyberSourceService;
import sea.scplus.consumer.vo.RequestContractCancel;
import sea.scplus.consumer.vo.RequestContractCreate;
import sea.scplus.consumer.vo.RequestContractCreateCustomerInfo;
import sea.scplus.consumer.vo.RequestContractStatus;
import sea.scplus.consumer.vo.RequestSerialNoValidation;
import sea.scplus.consumer.vo.RequestSpcValidation;
import sea.scplus.consumer.vo.ResponseContractCancel;
import sea.scplus.consumer.vo.ResponseContractCreate;
import sea.scplus.consumer.vo.ResponseContractStatus;
import sea.scplus.consumer.vo.ResponseSerialNoValidation;
import sea.scplus.consumer.vo.ResponseSpcValidation;
import sea.scplus.consumer.vo.cancel.RequestCancel;
import sea.scplus.consumer.vo.cancel.RequestCancelClientReferenceInformation;
import sea.scplus.consumer.vo.cancel.ResponseCancel;
import sea.scplus.consumer.vo.erp.tax.RequestGetTax;
import sea.scplus.consumer.vo.erp.tax.ResponseGetTax;
import sea.scplus.consumer.vo.erp.tax.ResponseGetTaxLineItem;
import sea.scplus.consumer.vo.order.CreateOrder;
import sea.scplus.consumer.vo.order.CreateOrderItem;
import sea.scplus.consumer.vo.payment.RequestPayment;
import sea.scplus.consumer.vo.payment.RequestPaymentClientReferenceInformation;
import sea.scplus.consumer.vo.payment.RequestPaymentProcessingInformation;
import sea.scplus.consumer.vo.payment.ResponsePayment;
import sea.scplus.consumer.vo.ConsumerContactHistory;

@Service
public class RESTCommonServiceImpl implements RESTCommonService {
	
	private final Log LOGGER = LogFactory.getLog(getClass());

	@Autowired
	private RESTCyberSourceService restCyberSourceService;
	
	@Autowired
	private FrontCommonService frontCommonService;
	
	@Autowired
	private RESTCommonMapper restCommonMapper;
	
	@Autowired
	private MessageSourceAccessor messageSourceAccessor;

	@Value("${scplus.channel}")
	private String spChannel;
    
    @Value("${scplus.company}")
	private String spCompany;
    
	@Value("${scplus.baseUrl}")
	private String spBaseUrl;
	
	@Value("${scplus.username}")
	private String scpus_username;
	
	@Value("${scplus.password}")
	private String scpus_password;
	
	@Value("${scplus.legacyusername}")
	private String scpus_legacy_username;
	
	@Value("${scplus.legacypassword}")
	private String scpus_legacy_password;
	
	@Value("${scplus.signinURL}")
	private String scpus_signinURL;
	
	@Value("${scplus.channelSigninURL}")
	private String scpus_channelSigninURL;
	
	@Value("${scplus.gspnCheckSerialNoValidationURL}")
	private String scpus_gspnCheckSerialNoValidationURL;
	
	@Value("${scplus.contractStatusURL}")
	private String scpus_contractStatusURL;
	
	@Value("${scplus.getSpcValidationURL}")
	private String scpus_getSpcValidationURL;
	
	@Value("${scplus.contractCancelURL}")
	private String scpus_contractCancelURL;
	
	@Value("${scplus.contractCreateURL}")
	private String scpus_contractCreateURL;
	
	/**
	 {
	  "email": "Francesca10@gmail.com",
	  "policyNo": "675172616",
	  "serialNo": "694095854396915"
	 }
	 
	 
	 {
    "result": "SUCCESS",
    "success": true,
    "message": "success",
    "data": {
        "code": "200",
        "msg": "SUCCESS.",
        "success": true,
        "contractStatusInfoResponse": {
            "email": "yisangho717@gmail.com",
            "firstName": "John",
            "lastName": "Doe",
            "contractEndDate": "2024-03-04",
            "contractNo": "685902482",
            "contractStartDate": "2021-03-04",
            "modelCode": "QN85Q90TAFXZA",
            "modelType": "Televisions",
            "orderDate": "2021-03-04",
            "purchaseDate": "2021-03-04",
            "serialNo": "09MM3CAN800469D",
            "spcPrice": "190.41",
            "spcSku": "LD-DOP36L-PB6-L00",
            "term": "36"
        }
    }
}
	 * @throws IOException 
	 */
	@Override
	public ResponseContractStatus getContractInfo(RequestContractStatus requestContractStatus) throws IOException {
		
		String channelToken = getTokenFromSCPUS(scpus_username, scpus_password);
		
		requestContractStatus.setToken(channelToken);
		
		requestContractStatus.setChannel(spChannel);
		requestContractStatus.setCompany(spCompany);
		ResponseContractStatus ret = contractStatus(requestContractStatus);
			
		return ret;
	}
	
	@Override
	public List<ResponseSpcValidation> checkSerialNoValidation(RequestSerialNoValidation requestSerialNoValidation) throws IOException {
		
		List<ResponseSpcValidation> response  = null; 

		//Get a Token
		String channelToken = getTokenFromSCPUS(scpus_username, scpus_password);
		
		ResponseSerialNoValidation responseSerialNoValidation = new ResponseSerialNoValidation();

		//Check Serial Number  
		requestSerialNoValidation.setToken(channelToken);
		requestSerialNoValidation.setCompany(spCompany);
		responseSerialNoValidation = gspnCheckSerialNoValidation(requestSerialNoValidation);
		
		if (responseSerialNoValidation.isSuccess() == true) {
			
			//Get a Legacy Token
			String legacyToken = getTokenFromSCPUSByUserID(scpus_legacy_username, scpus_legacy_password);
			
			//Get a insurance product
			RequestSpcValidation requestSpcValidation = new RequestSpcValidation();
			requestSpcValidation.setToken(legacyToken);
			requestSpcValidation.setChannel(spChannel);
			requestSpcValidation.setCompany(spCompany);
			requestSpcValidation.setModelcode(requestSerialNoValidation.getModelCode());
			response  = getSpcValidation(requestSpcValidation);
			
			return response;
			
		} else {
			
			
			return response;
			
		}
		
	}
	
	@Override
	public ResponseGetTax getTax(RequestGetTax requestGetTax) {
		
		ResponseGetTax response  = new ResponseGetTax(); 

		response.setSuccess(true);
		response.setCountry("US");
		response.setPostalCode("12345");
		response.setSubtotal(100.00);
		response.setTotalTax(2.9);
		response.setTotal(102.9);
		
		List<ResponseGetTaxLineItem> lineItem = new ArrayList<ResponseGetTaxLineItem>();
		
		ResponseGetTaxLineItem item = new ResponseGetTaxLineItem();
		item.setModelCode("SM-N975UZKAXAG");
		item.setPrice(100.00);
		item.setQuantity(1);
		item.setSerialNo("test-serialno");
		item.setTotalTax(2.9);
		
		lineItem.add(item);
		
		response.setLineItem(lineItem);
		
		if (response.isSuccess() == true) {
			
			return response;
			
		} else {

			return response;
			
		}
		
	}
	
/* {
{
    "paymentInformation": {
        "card": {
            "number": "4111111111111111",
            "securityCode": "123",
            "expirationMonth": "12",
            "expirationYear": "2031"
        }
    },
    "lineItems": [
        {
            "productCode": "09MM3CAN800469D",
            "productName": "QN85Q90TAFXZA",
            "productSku": "LD-DOP36L-PB6-L00",
            "productDescription": "36",
            "quantity": 1,
            "unitPrice": "190.41",
            "totalPrice": "210.41",
            "taxAmount": "10.0",
            "taxRate": "0.029"
        }
    ],
    "orderInformation": {
        "amountDetails": {
            "totalAmount": "210.41",
            "currency": "USD"
        },
        "billTo": {
            "firstName": "John",
            "lastName": "Doe",
            "address1": "1 Market St",
            "locality": "san francisco",
            "administrativeArea": "CA",
            "postalCode": "94105",
            "country": "US",
            "email": "test@cybs.com",
            "phoneNumber": "4158880000"
        }
    }
}

response from Cyber Source
{
    "result": "SUCCESS",
    "success": true,
    "message": "success",
    "data": {
        "id": "6141476946856556003006",
        "success": true,
        "status": "AUTHORIZED",
        "submitTimeUtc": "2021-02-24T06:21:35Z",
        "_links": {
            "self": {
                "method": "GET",
                "href": "/pts/v2/payments/6141476946856556003006"
            },
            "void": {
                "method": "POST",
                "href": "/pts/v2/payments/6141476946856556003006/voids"
            }
        },
        "clientReferenceInformation": {
            "code": "202102240121340009"
        },
        "orderInformation": {
            "amountDetails": {
                "totalAmount": "210.41",
                "authorizedAmount": "210.41",
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
            "cardVerification": {
                "resultCode": "3"
            },
            "avs": {
                "code": "Y",
                "codeRaw": "Y"
            }
        }
    }
}	

Return code 201 but error 	
{
  "clientReferenceInformation": {
    "code": "202102250945440048"
  },
  "errorInformation": {
    "reason": "INVALID_ACCOUNT",
    "message": "Decline - Invalid account number"
  },
  "id": "6142643450526644503006",
  "status": "DECLINED"
}

	
response from SC+US
{
  "success": true,
  "msg": "The contract has been successfully created with us. It will be active soon.",
  "code": 0,
  "contractInfo": {
    "contractNo": "689608095",
    "orderIdentifier": "CE202102240121350009",
    "orderItemIdentifier": "CE202102240121350010",
    "orderPartnerIdentifier": "CE202102240121350009",
    "orderItemPartnerIdentifier": "CE202102240121350010"
  }
}
	*/
	@Override
	public ResponseContractCreate payment(RequestPayment requestPayment) throws IOException, ParseException {
		
		ResponseContractCreate responseContractCreate = new ResponseContractCreate();
		
		RequestPaymentClientReferenceInformation clientReferenceInformation = new RequestPaymentClientReferenceInformation();
		String referenceCode = restCommonMapper.getReferenceId();
		clientReferenceInformation.setCode(referenceCode);
		
		RequestPaymentProcessingInformation processingInformation = new RequestPaymentProcessingInformation();
		processingInformation.setCapture(true);
		processingInformation.setCommerceIndicator("internet");
		
		requestPayment.setClientReferenceInformation(clientReferenceInformation);
		requestPayment.setProcessingInformation(processingInformation);
		
		RequestContractCreate requestContractCreate = new RequestContractCreate();
		requestContractCreate = mappingContractInfo(requestPayment);
		
		CreateOrder createOrder = new CreateOrder();
		createOrder = mappingCreateOrderInfo(requestPayment, requestContractCreate);
		
		//send payment info to Cybersource
		ResponsePayment response = restCyberSourceService.payment(requestPayment);
		
		createOrder.setCs_id(response.getId());
		createOrder.setCs_response_code(response.getProcessorInformation().getResponseCode());
		createOrder.setCs_status(response.getStatus());
		createOrder.setCs_submit_time_utc(response.getSubmitTimeUtc());
		
		if (response.isSuccess() == false) {
			responseContractCreate.setSuccess(false);
			responseContractCreate.setMsg(response.getMessage());
			responseContractCreate.setCode(response.getReason());
			
			restCommonMapper.insertCreateOrder(createOrder);
			
			return responseContractCreate;
			
		}
		
		if ( response.isSuccess() == true && response.getErrorInformation() == null) {
			
			String channelToken = getTokenFromSCPUS(scpus_username, scpus_password);
			requestContractCreate.setToken(channelToken);
			
			//create contract through SC+US 
			responseContractCreate = contractCreate(requestContractCreate);
			
			createOrder.setScpus_ret_cd(responseContractCreate.getCode());
			createOrder.setScpus_ret_message(responseContractCreate.getMsg());
			createOrder.setCs_approval_code(response.getProcessorInformation().getApprovalCode());
			createOrder.setCs_network_transaction_id(response.getProcessorInformation().getNetworkTransactionId());
			createOrder.setCs_transaction_id(response.getProcessorInformation().getTransactionId());
			
			if (responseContractCreate.isSuccess() == true) {
				
				//send email
				ConsumerContactHistory consumerContactHistory = new ConsumerContactHistory();
				consumerContactHistory.setReference_code(referenceCode);
				consumerContactHistory.setContract_no(responseContractCreate.getContractInfo().getContractNo());
				consumerContactHistory.setRequest_path("SYSTEM");
				consumerContactHistory.setResponse_type("EMAIL");
				consumerContactHistory.setReason_type("ORDER_CONFIRM");
				consumerContactHistory.setEmail(requestPayment.getOrderInformation().getBillTo().getEmail());
				
				requestContractCreate.setContractNo(responseContractCreate.getContractInfo().getContractNo());
				frontCommonService.sendContractConfirm(requestPayment, requestContractCreate, consumerContactHistory);
				
				//insert order data
				createOrder.setContract_no(responseContractCreate.getContractInfo().getContractNo());
				createOrder.setPos_ret_cd("true");
				createOrder.setPos_ret_message("success");
				
				restCommonMapper.insertCreateOrder(createOrder);
				
				return responseContractCreate;
				
			} else {
				
				restCommonMapper.insertCreateOrder(createOrder);
				
				return responseContractCreate;
				
			}
			
		} else {
			
			if (response.getErrorInformation().getReason().isEmpty() == false) {
				responseContractCreate.setSuccess(false);
				responseContractCreate.setMsg(response.getErrorInformation().getMessage());
				responseContractCreate.setCode(response.getErrorInformation().getReason());
				
				restCommonMapper.insertCreateOrder(createOrder);
				
				return responseContractCreate;
				
			} else {
				
				responseContractCreate.setSuccess(false);
				responseContractCreate.setMsg(response.getMessage());
				responseContractCreate.setCode(response.getReason());
				
				createOrder.setScpus_ret_cd(response.getReason());
				createOrder.setScpus_ret_message(response.getMessage());
				
				restCommonMapper.insertCreateOrder(createOrder);
				
				return responseContractCreate;
			}
			
		}
				
		
	}
	
	
	private CreateOrder mappingCreateOrderInfo(RequestPayment requestPayment, RequestContractCreate requestContract) {
	
		CreateOrder createOrder = new CreateOrder();
		List<CreateOrderItem> list = new ArrayList<CreateOrderItem>();
		CreateOrderItem orderItem = new CreateOrderItem();
		
		createOrder.setEmail(requestContract.getCustomer().getEmail());
		createOrder.setFirst_name(requestContract.getCustomer().getFirstName());
		createOrder.setLast_name(requestContract.getCustomer().getLastName());
		createOrder.setOrder_id(requestContract.getOrderIdentifier());
		createOrder.setOrder_date(requestContract.getOrderDate());
		createOrder.setContract_no("");
		createOrder.setScpus_ret_cd("");
		createOrder.setScpus_ret_message("");
		createOrder.setPay_date(requestContract.getOrderDate());
		createOrder.setUnit_price(requestContract.getSpcNetPrice());
		createOrder.setTotal_price(requestContract.getRetailItemAmount());
		createOrder.setTax_rate(requestContract.getSpcTaxRate());
		createOrder.setTax_amount(requestContract.getSpcTaxAmt());
		createOrder.setPayment_type(requestContract.getSpcPaymentType());
		createOrder.setPayment_status(requestContract.getSpcPaymentStatus());
		createOrder.setContract_start_date(requestContract.getContractStartDate());
		createOrder.setContract_end_date(requestContract.getContractEndDate());
		createOrder.setOrder_reference_code(requestPayment.getClientReferenceInformation().getCode());
		createOrder.setCs_id("");
		createOrder.setCs_approval_code("");
		createOrder.setCs_network_transaction_id("");
		createOrder.setCs_transaction_id("");
		createOrder.setCs_response_code("");
		createOrder.setCs_status("");
		createOrder.setCs_submit_time_utc("");
		createOrder.setPos_ret_cd("");
		createOrder.setPos_ret_message("");
		
		orderItem.setModel_cd(requestContract.getModel());
		orderItem.setOrder_item_id(requestContract.getOrderItemIdentifier());
		orderItem.setOrder_id(requestContract.getOrderIdentifier());
		orderItem.setQuantity(1);
		orderItem.setSerial_number(requestContract.getSerialNumber());
		orderItem.setSku(requestContract.getSku());
		orderItem.setTax_amount(requestContract.getSpcTaxAmt());
		orderItem.setTotal_price(requestContract.getRetailItemAmount());
		orderItem.setUnit_price(requestContract.getSpcNetPrice());
		orderItem.setTax_rate(requestContract.getSpcTaxRate());
		
		list.add(orderItem);
		createOrder.setOrderItem(list);
		
		return createOrder;
	}

	/**
{
  "accountIdentifier": "string",
  "associateName": "A lil Samsung shop",
  "contractEndDate": "2022-02-10",
  "contractStartDate": "2020-02-10",
  "customer": {
    "address1": "105 Challenger Rd.",
    "address2": "8th Floor",
    "city": "Ridgefield Park",
    "country": "US",
    "email": "michael.j@jackson5.com",
    "firstName": "Jackson",
    "lastName": "Michael",
    "phone": 2010009999,
    "state": "NJ",
    "zipcode": "07660"
  },
  "mobile": true,
  "mobileNumber": "2010009999",
  "model": "RF23R6301SR/AA",
  "orderDate": "2019-11-20",
  "orderIdentifier": "SamsungSCMP20191111190030",
  "orderItemIdentifier": "SamsungSCMP20191111190030-01",
  "purchaseDate": "2019-11-21",
  "retailItemAmount": 11.99,
  "serialNumber": 11111111111115,
  "sku": "REB-DOP36P-PB6",
  "spcNetPrice": 10.99,
  "spcPayDate": "2019-11-23",
  "spcPaymentStatus": "G",
  "spcPaymentType": "S",
  "spcTaxAmt": 0.69,
  "spcTaxRate": 6.25,
  "storeName": "A lil Samsung shop"
}
	 * @throws ParseException 
	 */
	private RequestContractCreate mappingContractInfo(RequestPayment requestPayment) throws ParseException {
		RequestContractCreate contractInfo = new RequestContractCreate();
		
		String contractStartDate = TimezoneDateTimeUtil.getCurrentDateString();
		contractInfo.setContractStartDate(contractStartDate);
		int term = Integer.parseInt(requestPayment.getLineItems().get(0).getProductDescription());
		contractInfo.setContractEndDate(TimezoneDateTimeUtil.getAddMonthToCurrentDateString(contractStartDate, term));
		
		RequestContractCreateCustomerInfo customer = new RequestContractCreateCustomerInfo();
		customer.setAddress1(requestPayment.getOrderInformation().getBillTo().getAddress1());
		customer.setAddress2("");
		customer.setCity(requestPayment.getOrderInformation().getBillTo().getLocality());
		customer.setCountry(requestPayment.getOrderInformation().getBillTo().getCountry());
		customer.setEmail(requestPayment.getOrderInformation().getBillTo().getEmail());
		customer.setFirstName(requestPayment.getOrderInformation().getBillTo().getFirstName());
		customer.setLastName(requestPayment.getOrderInformation().getBillTo().getLastName());
		customer.setPhone(requestPayment.getOrderInformation().getBillTo().getPhoneNumber());
		customer.setState(requestPayment.getOrderInformation().getBillTo().getAdministrativeArea());
		customer.setZipcode(requestPayment.getOrderInformation().getBillTo().getPostalCode());
		contractInfo.setCustomer(customer);
		
		contractInfo.setMobile(true);
		contractInfo.setMobileNumber(requestPayment.getOrderInformation().getBillTo().getPhoneNumber());
		contractInfo.setModel(requestPayment.getLineItems().get(0).getProductName());
		contractInfo.setOrderDate(contractStartDate);
		
		String orderIdentifier = restCommonMapper.getOrderId();
		contractInfo.setOrderIdentifier(orderIdentifier);
		
		String orderItemIdentifier = restCommonMapper.getOrderId();
		contractInfo.setOrderItemIdentifier(orderItemIdentifier);
		
		contractInfo.setPurchaseDate(contractStartDate);
		contractInfo.setRetailItemAmount(Double.parseDouble(requestPayment.getOrderInformation().getAmountDetails().getTotalAmount()));
		contractInfo.setSerialNumber(requestPayment.getLineItems().get(0).getProductCode());
		contractInfo.setSku(requestPayment.getLineItems().get(0).getProductSku());
		contractInfo.setSpcNetPrice(Double.parseDouble(requestPayment.getLineItems().get(0).getUnitPrice()));
		contractInfo.setSpcPayDate(contractStartDate);
		contractInfo.setSpcPaymentStatus("C"); //G: Payment in progress, C: Payment Completed. F: Failed, C만전송
		contractInfo.setSpcPaymentType("S"); //S : Single Payment M: Monthly Payment
		contractInfo.setSpcTaxAmt(Double.parseDouble(requestPayment.getLineItems().get(0).getTaxAmount()));
		contractInfo.setSpcTaxRate(Double.parseDouble(requestPayment.getLineItems().get(0).getTaxRate()));
		
		return contractInfo;
	}
	
	private CreateOrder mappingCancelOrderInfo(RequestContractCancel requestPayment, CreateOrder requestContract) {
		
		CreateOrder createOrder = new CreateOrder();
		List<CreateOrderItem> list = new ArrayList<CreateOrderItem>();
		CreateOrderItem orderItem = new CreateOrderItem();
		
		String cancelOrderId = restCommonMapper.getOrderId();
		createOrder.setCancel_order_id(cancelOrderId);
		
		String cancelOrderItemId = restCommonMapper.getOrderId();
		
		createOrder.setFirst_name(requestContract.getFirst_name());
		createOrder.setLast_name(requestContract.getLast_name());
		createOrder.setOrder_id(requestContract.getOrder_id());
		createOrder.setOrder_date(requestContract.getOrder_date());
		createOrder.setContract_no(requestContract.getContract_no());
		createOrder.setScpus_ret_cd("");
		createOrder.setScpus_ret_message("");
		createOrder.setTotal_price(requestContract.getTotal_price());
		createOrder.setOrder_reference_code(requestContract.getOrder_reference_code());
		createOrder.setCs_id(requestContract.getCs_id());
		createOrder.setCs_reconciliation_id("");
		createOrder.setCs_status("");
		createOrder.setCs_submit_time_utc("");
		createOrder.setPos_ret_cd("");
		createOrder.setPos_ret_message("");
		
		orderItem = requestContract.getOrderItem().get(0);
		orderItem.setCancel_order_id(cancelOrderId);
		orderItem.setCancel_order_item_id(cancelOrderItemId);
		list.add(orderItem);
		createOrder.setOrderItem(list);
		
		return createOrder;
	}
	/*
SC+US : request
{
  "cancelDate": "2019-11-21",
  "cancelReason": "cancelReason",
  "contractNo": 600009999,
  "sendConfirmEmailYN": "N"
}

SC+US : response
{
  "code": 0,
  "msg": "string",
  "success": true
}

Cyber Source : request
{
    "clientReferenceInformation": {
        "code": "test_void"
    }
}


Cyber Source : 
response : 201
{
	"clientReferenceInformation": {
        "code": "TC50171_3"
    },
    "orderInformation": {
        "amountDetails": {
            "totalAmount": "10",
            "currency": "USD"
        }
    }
}

response : 400
{
  "id": "6143963336366129503004",
  "submitTimeUtc": "2021-02-27T03:25:33Z",
  "status": "INVALID_REQUEST",
  "reason": "NOT_VOIDABLE",
  "message": "Decline - The capture or credit is not voidable because the capture or credit information has already been submitted to your processor. Or, you requested a void for a type of transaction that cannot be voided."
}
		*/
		@Override
		public ResponseContractCancel cancel(RequestContractCancel requestContractCancel) throws IOException, ParseException {
			
			ResponseContractCancel responseContractCancel = new ResponseContractCancel();
			RequestCancel requestCancel = new RequestCancel();
			ResponseCancel responseCancel = new ResponseCancel();
			String contractNo = requestContractCancel.getContractNo();
			CreateOrder orderInfo = restCommonMapper.getOrderInfo(contractNo);
			orderInfo.setEmail(requestContractCancel.getEmail());
			orderInfo.setFirst_name(requestContractCancel.getFirstName());
			orderInfo.setLast_name(requestContractCancel.getLastName());
			
			RequestCancelClientReferenceInformation clientReferenceInformation = new RequestCancelClientReferenceInformation();
			String referenceCode = orderInfo.getOrder_reference_code();
			clientReferenceInformation.setCode(referenceCode);
			
			requestCancel.setClientReferenceInformation(clientReferenceInformation);
			requestCancel.setCs_id(orderInfo.getCs_id());
			
//			RequestContractCreate requestContractCreate = new RequestContractCreate();
//			requestContractCreate = mappingContractInfo(requestPayment);
//			
			CreateOrder cancelOrder = new CreateOrder();
			cancelOrder = mappingCancelOrderInfo(requestContractCancel, orderInfo);
			
			//send void to Cybersource
			ResponseCancel response = restCyberSourceService.cancel(requestCancel);
			
			cancelOrder.setCs_id(response.getId());
			cancelOrder.setCs_status(response.getStatus());
			cancelOrder.setCs_submit_time_utc(response.getSubmitTimeUtc());
			
			if (response.isSuccess() == false) {
				
				responseContractCancel.setSuccess(false);
				responseContractCancel.setMsg(response.getMessage());
				responseContractCancel.setCode(response.getReason());
				
				restCommonMapper.insertCancelOrder(cancelOrder);
				
				return responseContractCancel;
				
			}
			
			if ( response.isSuccess() == true ) {
				
				String channelToken = getTokenFromSCPUS(scpus_username, scpus_password);
				requestContractCancel.setToken(channelToken);
				
				//create contract through SC+US 
				responseContractCancel = contractCancel(requestContractCancel);
				
				cancelOrder.setScpus_ret_cd(responseContractCancel.getCode());
				cancelOrder.setScpus_ret_message(responseContractCancel.getMsg());
//				cancelOrder.setCs_response_code(response.getProcessorInformation().getResponseCode());
				
				if (responseContractCancel.isSuccess() == true) {
					
					//send email
					ConsumerContactHistory consumerContactHistory = new ConsumerContactHistory();
					consumerContactHistory.setReference_code(response.getClientReferenceInformation().getCode());
					consumerContactHistory.setContract_no(contractNo);
					consumerContactHistory.setRequest_path("SYSTEM");
					consumerContactHistory.setResponse_type("EMAIL");
					consumerContactHistory.setReason_type("ORDER_CONFIRM");
					consumerContactHistory.setEmail(requestContractCancel.getEmail());
					
					frontCommonService.sendContractCancel(cancelOrder, consumerContactHistory);
					
					//insert order data
					cancelOrder.setPos_ret_cd("true");
					cancelOrder.setPos_ret_message("success");
					
					restCommonMapper.insertCancelOrder(cancelOrder);
					
					return responseContractCancel;
					
				} else {
					
					restCommonMapper.insertCancelOrder(cancelOrder);
					
					return responseContractCancel;
					
				}
				
			} else {
					
				responseContractCancel.setSuccess(false);
				responseContractCancel.setMsg(response.getMessage());
				responseContractCancel.setCode(response.getReason());
				
				cancelOrder.setScpus_ret_cd(response.getReason());
				cancelOrder.setScpus_ret_message(response.getMessage());
				
				restCommonMapper.insertCancelOrder(cancelOrder);
				
				return responseContractCancel;
				
			}
					
			
		}

	@Override
	public String getTokenFromSCPUS(String username, String password) throws IOException {
		
		String response = "";
		
		try 
		{
			String urlParameters  = "?username=" + username + "&password="+ password;
			String functionPath = scpus_channelSigninURL;
			String apiURL		= spBaseUrl;
			
			java.net.URL url 			= new URL(apiURL+functionPath+urlParameters);
//			java.net.URL url 			= new URL(null, apiURL+functionPath+urlParameters,new sun.net.www.protocol.https.Handler() );
		
			HttpURLConnection con 	= (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
//			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setDoInput(true);
			con.setDoOutput(true);

//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_JSON);
			
			int responseCode = con.getResponseCode();
           	BufferedReader br;
           	StringBuilder sb = new StringBuilder();
           	
           	if (responseCode == 200) {
              br = new BufferedReader(new InputStreamReader(con.getInputStream()));
              
              String line;
              while ((line = br.readLine()) != null) {
                sb.append(line );
              }
             	
           	} else {
              br = new BufferedReader(new InputStreamReader(con.getErrorStream()));                  
           	}
           	
           	br.close();
           	con.disconnect();
	
           	LOGGER.debug(sb.toString());
           	String resJson = sb.toString();	
           	LOGGER.debug(resJson);
	           
			try{
				
				if (responseCode == 200) {
					response = resJson;
					LOGGER.debug("success : " + response.toString());
					
				} else {
					response = "fail";
					LOGGER.debug("fail : " + resJson);
				}
				

			}catch(Exception e){
				e.printStackTrace();
			}
	           
		} catch (Throwable e) {

			e.printStackTrace();
		}
		return response.replaceAll(System.lineSeparator(), "");
	}
	
	/**
	 * http://gcsc.samsung.com/scmpapi/v1/signin?passwd=sample&userid=47
	 */
	@Override
	public String getTokenFromSCPUSByUserID(String username, String password) throws IOException {
		
		String response = "";
		
		try 
		{
			String urlParameters  = "?userid=" + username + "&passwd="+ password;
			String functionPath = scpus_signinURL;
			String apiURL		= spBaseUrl;
			
			java.net.URL url 			= new URL(apiURL+functionPath+urlParameters);
//			java.net.URL url 			= new URL(null, apiURL+functionPath+urlParameters,new sun.net.www.protocol.https.Handler() );
		
			HttpURLConnection con 	= (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setDoInput(true);
			con.setDoOutput(true);

			int responseCode = con.getResponseCode();
           	BufferedReader br;
           	StringBuilder sb = new StringBuilder();
           	
           	if (responseCode == 200) {
              br = new BufferedReader(new InputStreamReader(con.getInputStream()));
              
              String line;
              while ((line = br.readLine()) != null) {
                sb.append(line );
              }
             	
           	} else {
              br = new BufferedReader(new InputStreamReader(con.getErrorStream()));                  
           	}
           	
           	br.close();
           	con.disconnect();
	
           	LOGGER.debug(sb.toString());
           	String resJson = sb.toString();	
           	LOGGER.debug(resJson);
	           
			try{
				
				if (responseCode == 200) {
					response = resJson;
					LOGGER.debug("success : " + response.toString());
					
				} else {
					response = "fail";
					LOGGER.debug("fail : " + resJson);
				}
				

			}catch(Exception e){
				e.printStackTrace();
			}
	           
		} catch (Throwable e) {

			e.printStackTrace();
		}
		return response.replaceAll(System.lineSeparator(), "");
	}
	
	/**
	 * http://gcsc.samsung.com/scmpapi/v1/getSpcValidation?channel=SESL&company=C310&modelcode=SM-N975UZKAXAG
	 * http://gcsc.samsung.com/scmpapi/v1/getSpcValidation?channel=CEPOS&company=C310&modelcode=QN85Q90TAFXZA
	 */
	@Override
	public List<ResponseSpcValidation> getSpcValidation(RequestSpcValidation requestSpcValidation) {
		
		String response = "";
		
		
		try 
		{
			String channel = requestSpcValidation.getChannel();
			String company = requestSpcValidation.getCompany();
			String modelcode = requestSpcValidation.getModelcode();
			String token = requestSpcValidation.getToken();
			
			String urlParameters  = "?channel=" + channel + "&company="+ company + "&modelcode="+ modelcode;
			String functionPath = scpus_getSpcValidationURL;
			String apiURL		= spBaseUrl;
			
			java.net.URL url 			= new URL(apiURL+functionPath+urlParameters);
//			java.net.URL url 			= new URL(null, apiURL+functionPath+urlParameters,new sun.net.www.protocol.https.Handler() );
		
			HttpURLConnection con 	= (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		    con.setRequestProperty("X-AUTH-TOKEN", token);
		    con.setDoOutput(true);
			
			int responseCode = con.getResponseCode();
           	BufferedReader br;
           	StringBuilder sb = new StringBuilder();
           	
           	String line;
           	if (responseCode == 200) {
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
				
				if (responseCode == 200) {
					ObjectMapper mapper = new ObjectMapper();
					List<ResponseSpcValidation> responseSpcValidation = Arrays.asList(mapper.readValue(resJson,ResponseSpcValidation[].class)); 
					LOGGER.debug("success : " + response.toString());
					LOGGER.debug("myResponse : " + responseSpcValidation);
					return responseSpcValidation;
					
				} else {
					response = "fail";
					LOGGER.debug("fail : " + resJson);
					
					throw new BadRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.SYNTAX)); 	
				}
				

			}catch(Exception e){
				e.printStackTrace();
			}
			
		} catch (Throwable e) {

			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 {
		  "company": "C310",
		  "modelCode": "SM-N960UZBAATT",
		  "serialNo": 358620092314569
		}
	 */
	@Override
	public ResponseSerialNoValidation gspnCheckSerialNoValidation(RequestSerialNoValidation requestSerialNoValidation) {
		
		ResponseSerialNoValidation responseSerialNoValidation = new ResponseSerialNoValidation();
		String response = "";
		try 
		{
			String functionPath = scpus_gspnCheckSerialNoValidationURL;
			String apiURL		= spBaseUrl;
			String token		= requestSerialNoValidation.getToken();
			java.net.URL url 			= new URL(apiURL+functionPath);
//			java.net.URL url 			= new URL(null, apiURL+functionPath+urlParameters,new sun.net.www.protocol.https.Handler() );
		
		    JSONObject jsonObject = new JSONObject();

            jsonObject.accumulate("company", requestSerialNoValidation.getCompany());
            jsonObject.accumulate("modelCode", requestSerialNoValidation.getModelCode());
            jsonObject.accumulate("serialNo", requestSerialNoValidation.getSerialNo());
		    
//		    String urlParameters  = "{ \"company\": \"C310\", \"modelCode\": \"SM-N960UZBAATT\", \"serialNo\": 358620092314569}";
		    byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");
		    
			HttpURLConnection con 	= (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("accept", "*/*");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		    con.setRequestProperty("X-AUTH-TOKEN", token);
		    con.setDoOutput(true);
			
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
           	if (responseCode == 200) {
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
				
				ObjectMapper mapper = new ObjectMapper();
				responseSerialNoValidation = mapper.reader().forType(ResponseSerialNoValidation.class).readValue(resJson);
				
				if (responseCode == 200) {
					LOGGER.debug("success : " + responseSerialNoValidation.toString());
					LOGGER.debug("myResponse : " + responseSerialNoValidation.isSuccess());
					
				} else {
					response = "fail";
					responseSerialNoValidation.setSuccess(false);
					LOGGER.debug("fail : " + resJson);
				}
				

			}catch(Exception e){
				e.printStackTrace();
			}
	           
		} catch (Throwable e) {

			e.printStackTrace();
		}
		return responseSerialNoValidation;
	}
	
	
	/**
	 {
	  "channel": "CEPOS",
	  "company": "C310",
	  "email": "Francesca10@gmail.com",
	  "policyNo": "675172616",
	  "serialNo": "694095854396915"
	 }

	 */
	@Override
	public ResponseContractStatus contractStatus(RequestContractStatus requestContractStatus) {
		
		ResponseContractStatus responseContractStatus = new ResponseContractStatus();
		String response = "";
		try 
		{
			String functionPath = scpus_contractStatusURL;
			String apiURL		= spBaseUrl;
			String token		= requestContractStatus.getToken();
			java.net.URL url 			= new URL(apiURL+functionPath);
//			java.net.URL url 			= new URL(null, apiURL+functionPath+urlParameters,new sun.net.www.protocol.https.Handler() );
		
		    JSONObject jsonObject = new JSONObject();

           jsonObject.accumulate("channel", requestContractStatus.getChannel());
           jsonObject.accumulate("company", requestContractStatus.getCompany());
           jsonObject.accumulate("email", requestContractStatus.getEmail());
           jsonObject.accumulate("policyNo", requestContractStatus.getPolicyNo());
           jsonObject.accumulate("serialNo", requestContractStatus.getSerialNo());
		    
//		    String urlParameters  = "{ \"channel\": \"CEPOS\",\"company\": \"C310\", \"email\": \"Francesca10@gmail.com\", \"policyNo\": \"675172616\", \"serialNo\": \"358620092314569\"}";
           String urlParameters  = jsonObject.toString();
           LOGGER.debug("urlParameters : " + urlParameters); 
           byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");
		    
			HttpURLConnection con 	= (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("accept", "*/*");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		    con.setRequestProperty("X-AUTH-TOKEN", token);
		    con.setDoOutput(true);
			
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
          	if (responseCode == 200) {
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
				
				if (responseCode == 200) {
					ObjectMapper mapper = new ObjectMapper();
					responseContractStatus = mapper.reader().forType(ResponseContractStatus.class).readValue(resJson); 
					LOGGER.debug("success : " + response.toString());
					LOGGER.debug("myResponse : " + responseContractStatus);
					
				} else {
					response = "fail";
					responseContractStatus.setSuccess(false);
					LOGGER.debug("fail : " + resJson);
				}
				

			}catch(Exception e){
				e.printStackTrace();
			}
	           
		} catch (Throwable e) {

			e.printStackTrace();
		}
		return responseContractStatus;
	}
	
	/**
	 {
	  "cancelDate": "2019-11-21",
	  "contractNo": 600009999,
	  "sendConfirmEmailYN": "N"
	}

	 */
	@Override
	public ResponseContractCancel contractCancel(RequestContractCancel requestContractCancel) {
		
		ResponseContractCancel responseContractCancel = new ResponseContractCancel();
		String response = "";
		try 
		{
			String functionPath = scpus_contractCancelURL;
			String apiURL		= spBaseUrl;
			String token		= requestContractCancel.getToken();
			java.net.URL url 			= new URL(apiURL+functionPath);
//			java.net.URL url 			= new URL(null, apiURL+functionPath+urlParameters,new sun.net.www.protocol.https.Handler() );
		
		    JSONObject jsonObject = new JSONObject();

          jsonObject.accumulate("cancelDate", requestContractCancel.getCancelDate());
          jsonObject.accumulate("contractNo", requestContractCancel.getContractNo());
          jsonObject.accumulate("sendConfirmEmailYN", requestContractCancel.getSendConfirmEmailYN());
		    
//		    String urlParameters  = "{ \"cancelDate\": \"2019-11-21\",\"contractNo\": \"600009999\", \"sendConfirmEmailYN\": \"N\"}";
          String urlParameters  = jsonObject.toString();
          LOGGER.debug("urlParameters : " + urlParameters); 
          byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");
		    
			HttpURLConnection con 	= (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("accept", "*/*");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		    con.setRequestProperty("X-AUTH-TOKEN", token);
		    con.setDoOutput(true);
			
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
         	if (responseCode == 200) {
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            
         	} else {
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            
         	}
         	while ((line = br.readLine()) != null) {
              sb.append(line + "\n");
          }
         	
         	br.close();
         	con.disconnect();
	
         	LOGGER.debug(sb.toString());
         	String resJson = sb.toString();	
         	LOGGER.debug(resJson);
	           
			try{
				
				if (responseCode == 200) {
					ObjectMapper mapper = new ObjectMapper();
					responseContractCancel= mapper.reader().forType(ResponseContractCancel.class).readValue(resJson); 
					LOGGER.debug("success : " + response.toString());
					LOGGER.debug("myResponse : " + responseContractCancel);
					
				} else {
					response = "fail";
					responseContractCancel.setSuccess(false);
					LOGGER.debug("fail : " + resJson);
				}
				

			}catch(Exception e){
				e.printStackTrace();
			}
	           
		} catch (Throwable e) {

			e.printStackTrace();
		}
		return responseContractCancel;
	}
	
	/**
{
  "accountIdentifier": "string",
  "associateName": "A lil Samsung shop",
  "contractEndDate": "2022-02-10",
  "contractStartDate": "2020-02-10",
  "customer": {
    "address1": "105 Challenger Rd.",
    "address2": "8th Floor",
    "city": "Ridgefield Park",
    "country": "US",
    "email": "michael.j@jackson5.com",
    "firstName": "Jackson",
    "lastName": "Michael",
    "phone": 2010009999,
    "state": "NJ",
    "zipcode": "07660"
  },
  "mobile": true,
  "mobileNumber": 2010009999,
  "model": "RF23R6301SR/AA",
  "orderDate": "2019-11-20",
  "orderIdentifier": "SamsungSCMP20191111190030",
  "orderItemIdentifier": "SamsungSCMP20191111190030-01",
  "purchaseDate": "2019-11-21",
  "retailItemAmount": 11.99,
  "serialNumber": 11111111111115,
  "sku": "REB-DOP36P-PB6",
  "spcNetPrice": 10.99,
  "spcPayDate": "2019-11-23",
  "spcPaymentStatus": "G",
  "spcPaymentType": "S",
  "spcTaxAmt": 0.69,
  "spcTaxRate": 6.25,
  "storeName": "A lil Samsung shop"
}
	 */
	@Override
	public ResponseContractCreate contractCreate(RequestContractCreate requestContractCreate) {
		
		ResponseContractCreate responseContractCreate = new ResponseContractCreate();
		String response = "";
		try 
		{
			String functionPath = scpus_contractCreateURL;
			String apiURL		= spBaseUrl;
			String token		= requestContractCreate.getToken();
			java.net.URL url 			= new URL(apiURL+functionPath);
//			java.net.URL url 			= new URL(null, apiURL+functionPath+urlParameters,new sun.net.www.protocol.https.Handler() );
		
			ObjectMapper mapper = new ObjectMapper();
			
	        String urlParameters  = mapper.writeValueAsString(requestContractCreate);
	        LOGGER.debug("urlParameters : " + urlParameters); 
	        JSONObject jsonObject = new JSONObject(urlParameters);
	         
	        byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");
		    
			HttpURLConnection con 	= (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("accept", "*/*");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		    con.setRequestProperty("X-AUTH-TOKEN", token);
		    con.setDoOutput(true);
			
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
        	if (responseCode == 200) {
           br = new BufferedReader(new InputStreamReader(con.getInputStream()));
           
        	} else {
           br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
           
        	}
        	while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
         }
        	
        	br.close();
        	con.disconnect();
	
        	LOGGER.debug(sb.toString());
        	String resJson = sb.toString();	
        	LOGGER.debug(resJson);
	           
			try{
				
				if (responseCode == 200) {
					responseContractCreate = mapper.reader().forType(ResponseContractCreate.class).readValue(resJson); 
					LOGGER.debug("success : " + response.toString());
					LOGGER.debug("myResponse : " + responseContractCreate);
					
				} else {
					response = "fail";
					responseContractCreate.setSuccess(false);
					LOGGER.debug("fail : " + resJson);
				}
				

			}catch(Exception e){
				e.printStackTrace();
			}
	           
		} catch (Throwable e) {

			e.printStackTrace();
		}
		return responseContractCreate;
	}

	

	

}
