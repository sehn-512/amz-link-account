package sea.scplus.consumer.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sea.scplus.consumer.common.constant.CommonValue;
import sea.scplus.consumer.common.exception.NormalRequestException;
import sea.scplus.consumer.common.util.SessionUtil;
import sea.scplus.consumer.common.util.TimezoneDateTimeUtil;
import sea.scplus.consumer.common.util.Util;
import sea.scplus.consumer.persistence.api.RESTCommonMapper;
import sea.scplus.consumer.service.FrontCommonService;
import sea.scplus.consumer.service.RESTAPIService;
import sea.scplus.consumer.vo.*;
import sea.scplus.consumer.vo.cancel.RequestCancel;
import sea.scplus.consumer.vo.cancel.RequestCancelClientReferenceInformation;
import sea.scplus.consumer.vo.cancel.ResponseCancel;
import sea.scplus.consumer.vo.capture.*;
import sea.scplus.consumer.vo.emailhook.RequestEmailHook;
import sea.scplus.consumer.vo.emailhook.RequestEmailHookPayload;
import sea.scplus.consumer.vo.erp.billing.RequestGetBillingOrder;
import sea.scplus.consumer.vo.erp.billing.ResponseGetBillingOrder;
import sea.scplus.consumer.vo.erp.ecommerce.RequestGetECommerce;
import sea.scplus.consumer.vo.erp.ecommerce.ResponseGetECommerce;
import sea.scplus.consumer.vo.erp.sales.RequestGetSalesOrder;
import sea.scplus.consumer.vo.erp.sales.ResponseGetSalesOrder;
import sea.scplus.consumer.vo.erp.tax.RequestGetTax;
import sea.scplus.consumer.vo.erp.tax.ResponseGetTax;
import sea.scplus.consumer.vo.order.CreateOrder;
import sea.scplus.consumer.vo.order.CreateOrderItem;
import sea.scplus.consumer.vo.payment.*;
import sea.scplus.consumer.vo.reversal.RequestReversal;
import sea.scplus.consumer.vo.reversal.ResponseReversal;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class RESTAPIServiceImpl implements RESTAPIService {

    private final Log LOGGER = LogFactory.getLog(getClass());

    @Value("${spring.profiles.active}")
    private String server_mode;

    @Autowired
    private FrontCommonService frontCommonService;

    @Autowired
    private RESTCommonMapper restCommonMapper;

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;


    @Value("${cepos.tokenURL}")
    private String cepos_getTokenURL;

    @Value("${cepos.baseURL}")
    private String cepos_baseUrl;

    @Value("${cepos.targetSystem}")
    private String cepos_targetSystem;

    @Value("${cepos.username}")
    private String cepos_username;

    @Value("${cepos.password}")
    private String cepos_password;

    @Value("${cepos.gspnCheckSerialNoValidationURL}")
    private String cepos_gspnCheckSerialNoValidationURL;

    @Value("${cepos.getSpcValidationURL}")
    private String cepos_getSpcValidationURL;

    @Value("${cepos.contractStatusURL}")
    private String cepos_getContractStatusURL;

    @Value("${cepos.contractCancelURL}")
    private String cepos_getContractCancelURL;

    @Value("${cepos.contractCreateURL}")
    private String cepos_contractCreateURL;

    @Value("${cepos.cancelRequestURL}")
    private String cepos_cancelRequestURL;

    @Value("${cepos.searchContractBySerialNoURL}")
    private String cepos_searchContractBySerialNoURL;

    @Value("${cepos.getTicketInfoURL}")
    private String cepos_getTicketInfoURL;

    @Value("${cepos.cyberPaymentURL}")
    private String cepos_cyberPaymentURL;

    @Value("${cepos.cyberCancelURL}")
    private String cepos_cyberCancelURL;

    @Value("${cepos.cyberCaptureURL}")
    private String cepos_cyberCaptureURL;

    @Value("${cepos.cyberCreditURL}")
    private String cepos_cyberCreditlURL;

    @Value("${cepos.cyberReversalURL}")
    private String cepos_cyberReversalURL;

    @Value("${cepos.getTaxURL}")
    private String cepos_getTaxURL;

    @Value("${cepos.getSalesOrderURL}")
    private String cepos_getSalesOrderURL;

    @Value("${cepos.getBillingOrderURL}")
    private String cepos_getBillingOrderURL;

    @Value("${cepos.getECommerceURL}")
    private String cepos_getECommerceURL;

    @Value("${cepos.sendFinanceReconURL}")
    private String cepos_sendFinanceReconURL;


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

    @Value("${scplus.emaiHookURL}")
    private String scpus_emaiHookURL;

    /**
     * Q  : Creation Requested to TPA
     * A  : Active
     * CQ : Customer Cancel Requested
     * RQ : Cancel Requested from Retailer
     * NQ : Cancel Requested to TPA
     * N  : Cancelled
     * VQ : Reactivate Request by SEA
     * VT : Reactivate Request to TPA
     * F  : Create Fail
     * X  : Expired
     * <p>
     * {
     * "email": "Francesca10@gmail.com",
     * "policyNo": "675172616",
     * "serialNo": "694095854396915"
     * }
     * <p>
     * <p>
     * {
     * "result": "SUCCESS",
     * "success": true,
     * "message": "success",
     * "data": {
     * "code": "200",
     * "msg": "SUCCESS.",
     * "success": true,
     * "contractStatusInfoResponse": {
     * "email": "yisangho717@gmail.com",
     * "firstName": "John",
     * "lastName": "Doe",
     * "contractEndDate": "2024-03-04",
     * "contractNo": "685902482",
     * "contractStartDate": "2021-03-04",
     * "modelCode": "QN85Q90TAFXZA",
     * "modelType": "Televisions",
     * "orderDate": "2021-03-04",
     * "purchaseDate": "2021-03-04",
     * "serialNo": "09MM3CAN800469D",
     * "spcPrice": "190.41",
     * "spcSku": "LD-DOP36L-PB6-L00",
     * "term": "36",
     * "cancelAvailable": true,
     * }
     * }
     * }
     *
     * @throws IOException
     * @throws ParseException
     */
    @Override
    public ResponseContractStatus getContractInfo(RequestContractStatus requestContractStatus) throws IOException, ParseException {

        //Get a Token from CEPOS API Server
        String ceposToken = getTokenFromCEPOS();

        String channelToken = getTokenFromSCPUS(scpus_username, scpus_password);

        requestContractStatus.setToken(channelToken);

        requestContractStatus.setChannel(spChannel);
        requestContractStatus.setCompany(spCompany);
        ResponseContractStatus ret = contractStatus(ceposToken, requestContractStatus);

        if (ret.isSuccess() == false) {
            LOGGER.debug("ret:" + ret.isSuccess());
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA));

        }


        ResponseContractStatusInfoResponse retDetail = ret.getContractStatusInfoResponse();
        retDetail.setCancelAvailable(false);

        //Add cancelAvailable field to response data
        if (ret.isSuccess() == true) {
            String status = ret.getContractStatusInfoResponse().getStatus();

            if ("Q".equals(status) || "A".equals(status) || "VQ".equals(status) || "VT".equals(status)) {
                retDetail.setContractStatus(CommonValue.Constant.CONTRACT.STATUS.ACTIVE);
            } else if ("CQ".equals(status) || "RQ".equals(status) || "NQ".equals(status)) {
                retDetail.setContractStatus(CommonValue.Constant.CONTRACT.STATUS.CANCELLING);
            } else if ("N".equals(status)) {
                retDetail.setContractStatus(CommonValue.Constant.CONTRACT.STATUS.CANCELLED);
            } else if ("X".equals(status)) {
                retDetail.setContractStatus(CommonValue.Constant.CONTRACT.STATUS.EXPIRED);
            } else {
                LOGGER.debug("ret:" + ret.isSuccess());
                throw new NormalRequestException(CommonValue.Message.Error.CONTACT_CALL_CENTER);
            }

            //the CancelAvailable field set "true" when the contract status is "Q" or "A".
            if ("Q".equals(status) || "A".equals(status)) {
                String orderDate = ret.getContractStatusInfoResponse().getOrderDate();
                String strCancelAvailiableDate = TimezoneDateTimeUtil.getAddDayToCurrentDateString(orderDate, 30);
                String strCurrentDate = TimezoneDateTimeUtil.getCurrentDateString();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date cancelAvailiableDate = sdf.parse(strCancelAvailiableDate);
                Date currentDate = sdf.parse(strCurrentDate);

                if (currentDate.before(cancelAvailiableDate)) {
                    retDetail.setCancelAvailable(true);
                } else {
                    retDetail.setCancelDesc("Please contact Asurion (https://asurion.com/) for cancellation and refund after 30 days of puchase");
                }
            }

            String contractNo = ret.getContractStatusInfoResponse().getContractNo();
            //Check cancel request in cancel order table
            CreateOrder cancelOrderInfo = restCommonMapper.getCancelOrderInfo(contractNo);
            if (cancelOrderInfo != null) {
                if (CommonValue.Constant.CANCEL.STATUS.REQUESTED.equals(cancelOrderInfo.getPos_ret_cd()) ||
                        CommonValue.Constant.CANCEL.STATUS.PROCESSED_GERP.equals(cancelOrderInfo.getPos_ret_cd()) ||
                        CommonValue.Constant.CANCEL.STATUS.PROCESSED_PG.equals(cancelOrderInfo.getPos_ret_cd()) ||
                        CommonValue.Constant.CANCEL.STATUS.PROCESSED_SCPLUS.equals(cancelOrderInfo.getPos_ret_cd())
                ) {
                    retDetail.setCancelAvailable(false);

                } else if (CommonValue.Constant.CANCEL.STATUS.CANCELLED.equals(cancelOrderInfo.getPos_ret_cd())) {
                    retDetail.setCancelAvailable(false);

                }
            }
        }

        ret.setContractStatusInfoResponse(retDetail);

        return ret;
    }

    /*
	request
	{
	  "cancelDate": "2019-11-21",
	  "cancelReason": "cancelReason",
	  "contractNo": 600009999,
	  "email": "michael.j@jackson5.com",
	  "firstName": "Jackson",
	  "lastName": "Michael",
	}
	*/
    @Override
    public ResponseContractCancel cancelRequest(RequestContractCancel requestContractCancel) throws ParseException, IOException {

        RequestContractStatus requestContractStatus = new RequestContractStatus();
        requestContractStatus.setEmail(requestContractCancel.getEmail());
        requestContractStatus.setPolicyNo(requestContractCancel.getContractNo());
        ResponseContractStatus ret = getContractInfo(requestContractStatus);
        String contractNo = ret.getContractStatusInfoResponse().getContractNo();
        //Check cancel request in cancel order table
        CreateOrder cancelOrderInfo = restCommonMapper.getCancelOrderInfo(contractNo);
        if (cancelOrderInfo != null) {
            if (CommonValue.Constant.CANCEL.STATUS.REQUESTED.equals(cancelOrderInfo.getPos_ret_cd()) ||
                    CommonValue.Constant.CANCEL.STATUS.PROCESSED_GERP.equals(cancelOrderInfo.getPos_ret_cd()) ||
                    CommonValue.Constant.CANCEL.STATUS.PROCESSED_PG.equals(cancelOrderInfo.getPos_ret_cd()) ||
                    CommonValue.Constant.CANCEL.STATUS.PROCESSED_SCPLUS.equals(cancelOrderInfo.getPos_ret_cd())
            ) {
                throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.CANCEL_PROCESSING));

            } else if (CommonValue.Constant.CANCEL.STATUS.CANCELLED.equals(cancelOrderInfo.getPos_ret_cd())) {
                throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.CANCEL_ALREADY));

            }
        }

        //Get a Token from CEPOS API Server
        String ceposToken = getTokenFromCEPOS();

        ResponseContractCancel response = new ResponseContractCancel();

        response = cancelRequestByCEPOS(ceposToken, requestContractCancel);

        return response;
    }

    @Override
    public List<ResponseSpcValidation> checkSerialNoValidation(RequestSerialNoValidation requestSerialNoValidation) throws IOException, ParseException {

        List<ResponseSpcValidation> response = null;

        //Get a Token from CEPOS API Server
        String ceposToken = getTokenFromCEPOS();

        LOGGER.debug("==================================================================================================");
        LOGGER.debug("ceposToken:" + ceposToken);
        LOGGER.debug("==================================================================================================");

        //Get a Token

        LOGGER.debug("==================================================================================================");
        LOGGER.debug("scpus_username:" + scpus_username);
        LOGGER.debug("scpus_password:" + scpus_password);
        LOGGER.debug("==================================================================================================");
        String channelToken = getTokenFromSCPUS(scpus_username, scpus_password);

        LOGGER.debug("==================================================================================================");
        LOGGER.debug("channelToken:" + channelToken);
        LOGGER.debug("==================================================================================================");

        //check status of serial number
        RequestContractBySerialNo requestContractBySerialNo = new RequestContractBySerialNo();
        requestContractBySerialNo.setToken(channelToken);
        requestContractBySerialNo.setCompany(spCompany);
        requestContractBySerialNo.setSerialNo(requestSerialNoValidation.getSerialNo());
        ResponseContractBySerialNo ret = contractBySerialNo(ceposToken, requestContractBySerialNo);

        LOGGER.debug("==================================================================================================");
        LOGGER.debug("server_mode:" + server_mode);

        //Skip check serial number whether duplicate or not in SC+US 09LP3CXR400771R
        if (("local".equals(server_mode) || "dev".equals(server_mode)) && "09LP3CXR400771R".equals(requestSerialNoValidation.getSerialNo())) {

        } else {
            //Add cancelAvailable field to response data
            if (ret.isSuccess()) {
                LOGGER.debug("ret:" + ret.isSuccess());
                List<ResponseContractBySerialNoInfoResponse> retDetail = ret.getContractBySerialNoInfoResponse();
                if (!retDetail.isEmpty()) {
                    String status = ret.getContractBySerialNoInfoResponse().get(0).getStatus();

                    //No join a contract
                    String error_code = ret.getCode();
                    if ("303".equals(error_code)) {
                        throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.ELIGIBILITY_303));

                    } else if ("304".equals(error_code)) {
                        throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.ELIGIBILITY_304));

                    } else if ("305".equals(error_code)) {
                        throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.ELIGIBILITY_305));

                    } else {
                        throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.NO_JOIN_CONTRACT));

                    }


                }

            } else if (ret.isSuccess() == false) {
                throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.NO_JOIN_CONTRACT));

            }
        }
        LOGGER.debug("==================================================================================================");

        ResponseSerialNoValidation responseSerialNoValidation = new ResponseSerialNoValidation();

        //Check Serial Number
        requestSerialNoValidation.setToken(channelToken);
        requestSerialNoValidation.setCompany(spCompany);
        responseSerialNoValidation = gspnCheckSerialNoValidation(ceposToken, requestSerialNoValidation);

        LOGGER.debug("==================================================================================================");
        LOGGER.debug("ceposToken:" + ceposToken);
        LOGGER.debug("requestSerialNoValidation:" + requestSerialNoValidation.toString());
        LOGGER.debug("==================================================================================================");

        if (responseSerialNoValidation.isSuccess() == true) {
            LOGGER.debug("==================================================================================================");
            LOGGER.debug("scpus_username:" + scpus_legacy_username);
            LOGGER.debug("scpus_password:" + scpus_legacy_password);
            LOGGER.debug("==================================================================================================");
            //Get a Legacy Token
            String legacyToken = getTokenFromSCPUSByUserID(scpus_legacy_username, scpus_legacy_password);

            LOGGER.debug("==================================================================================================");
            LOGGER.debug("legacyToken:" + legacyToken);
            LOGGER.debug("==================================================================================================");

            //Get a insurance product
            RequestSpcValidation requestSpcValidation = new RequestSpcValidation();
            requestSpcValidation.setToken(legacyToken);
            requestSpcValidation.setChannel(spChannel);
            requestSpcValidation.setCompany(spCompany);
            requestSpcValidation.setModelcode(requestSerialNoValidation.getModelCode());
            response = getSpcValidation(ceposToken, requestSpcValidation);

            if (response == null) {
                throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.NO_DATA));
            }

            ResponseSpcValidation item = new ResponseSpcValidation();

            for (int i = 0; i < response.size(); i++) {
                item = response.get(i);

                String skudesc = item.getSKUDESC();
                if (skudesc == null || skudesc.equals("")) {
                    item.setSKUDESC(item.getSKU());
                    response.set(i, item);
                }

            }

            LOGGER.debug("==================================================================================================");
            LOGGER.debug("ceposToken:" + ceposToken);
            LOGGER.debug("response:" + response.toString());
            LOGGER.debug("==================================================================================================");

            String purchaseDate = responseSerialNoValidation.getPurchaseDate();
            String manufactureDate = responseSerialNoValidation.getManufactureDate();

            if (purchaseDate == null || purchaseDate.equals("")) {
                purchaseDate = manufactureDate;
            } else if (manufactureDate == null || manufactureDate.equals("")) {
                purchaseDate = TimezoneDateTimeUtil.getCurrentDateString();
            }
            LOGGER.debug("111");
            //A orderdate is returned when purchasedate is greater than orderdate.
            purchaseDate = TimezoneDateTimeUtil.getBiggerDate(purchaseDate);
            LOGGER.debug("222");

            ServletRequestAttributes serviceRequestAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            LOGGER.debug("333");
            HttpSession httpSession = serviceRequestAttr.getRequest().getSession();
            LOGGER.debug("444");
            httpSession.setAttribute("purchaseDate", purchaseDate);

            return response;

        } else if (!responseSerialNoValidation.isSuccess()) {

            String error_code = responseSerialNoValidation.getCode();
            if ("304".equals(error_code)) {
                throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.ELIGIBILITY_304));

            } else if ("305".equals(error_code)) {
                throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.ELIGIBILITY_305));

            } else {
                throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_YOUR_DATA));

            }

        } else {

            LOGGER.debug("==================================================================================================");
            LOGGER.debug("response:" + response);
            LOGGER.debug("==================================================================================================");
            return response;

        }

    }

    @Override
    public ResponseGetTicketInfo getTicketInfo(String ticketNo) throws IOException, ParseException {

        String response = null;
        ResponseGetTicketInfo responseGetTicketInfo = null;

        //Get a Token from CEPOS API Server
        String ceposToken = getTokenFromCEPOS();

        LOGGER.debug("==================================================================================================");
        LOGGER.debug("ceposToken:" + ceposToken);
        LOGGER.debug("==================================================================================================");

        //Get a Token
        LOGGER.debug("==================================================================================================");
        LOGGER.debug("scpus_username:" + scpus_username);
        LOGGER.debug("scpus_password:" + scpus_password);
        LOGGER.debug("==================================================================================================");
        String channelToken = getTokenFromSCPUS(scpus_username, scpus_password);

        LOGGER.debug("==================================================================================================");
        LOGGER.debug("channelToken:" + channelToken);
        LOGGER.debug("==================================================================================================");

        try {
            String functionPath = cepos_getTicketInfoURL;
            String apiURL = cepos_baseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }

            JSONObject jsonObject = new JSONObject();

            jsonObject.accumulate("token", channelToken);
            jsonObject.accumulate("company", spCompany);
            jsonObject.accumulate("ticketNo", ticketNo);

            String urlParameters = jsonObject.toString();
            LOGGER.debug("urlParameters : " + urlParameters);
            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setRequestProperty("Authorization", "Bearer " + ceposToken);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

            try {

                JsonObject jsonObj = new JsonParser().parse(resJson).getAsJsonObject();
                boolean success = jsonObj.get("success").getAsBoolean();
                if (success) {
                    response = jsonObj.get("data").getAsJsonObject().get("responseGetTicketInfoData").toString();
                    LOGGER.debug("data : " + response);
                    ObjectMapper mapper = new ObjectMapper();
                    responseGetTicketInfo = mapper.reader().forType(ResponseGetTicketInfo.class).readValue(response);

                    if (responseCode == 200) {
                        LOGGER.debug("myResponse : " + responseGetTicketInfo);

                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return responseGetTicketInfo;

    }

    /**
     * {
     * "postalCode": "81635",
     * "country": "US",
     * "lineItem": [
     * {
     * "modelCode": "UN82RU8000FXZA",
     * "serialNo": "08793CAM901748H",
     * "quantity": 1,
     * "price": 100.00
     * }
     * ]
     * }
     *
     * @return {
     * "result": "SUCCESS",
     * "success": true,
     * "message": "success",
     * "data": [
     * {
     * "postalCode": "81635",
     * "country": "US",
     * "subtotal": 100,
     * "total": 102.9,
     * "totalTax": 2.9,
     * "lineItem": [
     * {
     * "modelCode": "UN82RU8000FXZA",
     * "serialNo": "08793CAM901748H",
     * "quantity": 1,
     * "price": 100.00,
     * "totalTax": 2.9,
     * }
     * ]
     * }
     * ]
     * }
     */
    @Override
    public ResponseGetTax getTax(RequestGetTax requestGetTax) {

        //Get a Token from CEPOS API Server
        String ceposToken = getTokenFromCEPOS();

        ResponseGetTax response = new ResponseGetTax();

        response = getTaxByCEPOS(ceposToken, requestGetTax);

        return response;

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

        //Get a Token from CEPOS API Server
        String ceposToken = getTokenFromCEPOS();
        String channelToken = getTokenFromSCPUS(scpus_username, scpus_password);
        String errorDateTime = TimezoneDateTimeUtil.getCurrentDateTimeString("EST", "yyyy-MM-dd HH:mm:ss");

        ResponseContractCreate responseContractCreate = new ResponseContractCreate();

        RequestPaymentProcessingInformation processingInformation = new RequestPaymentProcessingInformation();
        processingInformation.setCapture(true);
        processingInformation.setCommerceIndicator("internet");

        RequestPaymentOrderInformation requestPaymentOrderInformation = new RequestPaymentOrderInformation();
        requestPaymentOrderInformation = requestPayment.getOrderInformation();
        requestPaymentOrderInformation.setLineItems(requestPayment.getLineItems());

        requestPayment.setOrderInformation(requestPaymentOrderInformation);
        requestPayment.setProcessingInformation(processingInformation);

        RequestContractCreate requestContractCreate = new RequestContractCreate();
        requestContractCreate = mappingContractInfo(requestPayment);

        RequestPaymentClientReferenceInformation clientReferenceInformation = new RequestPaymentClientReferenceInformation();
        String referenceCode = requestContractCreate.getOrderItemIdentifier();
        clientReferenceInformation.setCode(referenceCode);
        requestPayment.setClientReferenceInformation(clientReferenceInformation);

        CreateOrder createOrder = new CreateOrder();
        createOrder = mappingCreateOrderInfo(requestPayment, requestContractCreate);

        //check status of serial number VIA SC+US
        RequestContractBySerialNo requestContractBySerialNo = new RequestContractBySerialNo();
        requestContractBySerialNo.setToken(channelToken);
        requestContractBySerialNo.setCompany(spCompany);
        requestContractBySerialNo.setSerialNo(requestContractCreate.getSerialNumber().trim());
        ResponseContractBySerialNo ret = contractBySerialNo(ceposToken, requestContractBySerialNo);

        LOGGER.debug("==================================================================================================");
        LOGGER.debug("server_mode:" + server_mode);

        //Skip check serial number whether duplicate or not in SC+US
        if (("local".equals(server_mode) || "dev".equals(server_mode)) && "09LP3CXR400771R".equals(requestPayment.getOrderInformation().getLineItems().get(0).getProductCode())) {

        } else {
            //Add cancelAvailable field to response data
            if (ret.isSuccess()) {
                LOGGER.debug("ret:" + ret.isSuccess());
                List<ResponseContractBySerialNoInfoResponse> retDetail = ret.getContractBySerialNoInfoResponse();
                if (!retDetail.isEmpty()) {
                    String status = ret.getContractBySerialNoInfoResponse().get(0).getStatus();

                    //No join a contract
                    responseContractCreate.setSuccess(false);
                    responseContractCreate.setMsg(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.NO_JOIN_CONTRACT)));
                    return responseContractCreate;

                }

            } else {
                responseContractCreate.setSuccess(false);
                responseContractCreate.setMsg(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.NO_JOIN_CONTRACT)));
                return responseContractCreate;

            }
        }

        //check status of serial number VIA Cybersource
        String serialNo = requestContractCreate.getSerialNumber();
        int iCnt = restCommonMapper.getOrderInfoBySerialNo(serialNo);

        LOGGER.debug("==================================================================================================");
        LOGGER.debug("serialNo:" + serialNo);
        LOGGER.debug("iCnt:" + iCnt);

        //Skip check serial number whether duplicate or not in Cybersource
        if (iCnt > 0) {
            //Prevent duplicate payment of contract
            responseContractCreate.setSuccess(false);
            responseContractCreate.setMsg(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.DUP_PAYMENT)));
            return responseContractCreate;
        }

        //Insert Order Information
        if ("local".equals(server_mode)) {
            restCommonMapper.insertCreateOrderWithoutDKMS(createOrder);
        } else {
            restCommonMapper.insertCreateOrder(createOrder);
        }

        //send payment info to Cybersource via API Server
        requestPayment.setToken(ceposToken);
        ResponsePayment paymentResponse = paymentCybersource(ceposToken, requestPayment);

        LOGGER.debug("==================================================================================================");
        LOGGER.debug("Cybersource response.getId():" + paymentResponse.getId());

        createOrder.setCs_id(paymentResponse.getId());
        createOrder.setCs_status(paymentResponse.getStatus());

        if (!paymentResponse.isSuccess() ||
                paymentResponse.get_links() == null ||
                paymentResponse.getErrorInformation() != null) {
            responseContractCreate.setSuccess(false);
            responseContractCreate.setMsg(paymentResponse.getMessage());
            responseContractCreate.setCode(paymentResponse.getReason());

            //restCommonMapper.insertCreateOrder(createOrder);
            restCommonMapper.updateCreateOrder(createOrder);

            if (paymentResponse.getErrorInformation() != null) {
                responseContractCreate.setMsg(paymentResponse.getErrorInformation().getMessage());
                responseContractCreate.setCode(paymentResponse.getErrorInformation().getReason());
            }

            if (paymentResponse.getMessage() == null && paymentResponse.getReason() == null && paymentResponse.getErrorInformation() == null) {
                responseContractCreate.setMsg(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.CONTACT_CALL_CENTER)));
                frontCommonService.sendErrorContractProcess(referenceCode, "abnormal Cybersource", errorDateTime);
            }


            return responseContractCreate;

        }

        LOGGER.debug("==================================================================================================");
        LOGGER.debug("Cybersource payment :" + paymentResponse.isSuccess());

        if (paymentResponse.isSuccess()) {
            String csID = paymentResponse.getId();
            String csResponseCode = paymentResponse.getProcessorInformation().getResponseCode();

            createOrder.setCs_submit_time_utc(paymentResponse.getSubmitTimeUtc());
            createOrder.setCs_response_code(csResponseCode);
            createOrder.setPos_ret_cd(CommonValue.Constant.CANCEL.STATUS.PROCESSED_PG);

            LOGGER.debug("==================================================================================================");
            LOGGER.debug("Cybersource getErrorInformation():" + paymentResponse.getErrorInformation());

            if (paymentResponse.getErrorInformation() != null) {
                responseContractCreate.setMsg(paymentResponse.getErrorInformation().getMessage());
                responseContractCreate.setCode(paymentResponse.getErrorInformation().getReason());

                createOrder.setCs_status(paymentResponse.getStatus());
                createOrder.setCs_response_code(paymentResponse.getErrorInformation().getReason());

                LOGGER.debug("==================================================================================================");
                LOGGER.debug("Cybersource response.getStatus() :" + paymentResponse.getStatus());

                //whether capture or reversal
                if ("AUTHORIZED_RISK_DECLINED".equals(paymentResponse.getStatus())) {

                    LOGGER.debug("==================================================================================================");
                    LOGGER.debug("Cybersource response.get_links().getCapture():" + paymentResponse.get_links().getCapture());

                    if (paymentResponse.get_links().getCapture() != null) {

                        RequestCapture requestCapture = new RequestCapture();
                        requestCapture.setCs_id(paymentResponse.getId());
                        requestCapture.setToken(ceposToken);

                        RequestCaptureClientReferenceInformation captureClientReferenceInformation = new RequestCaptureClientReferenceInformation();
                        captureClientReferenceInformation.setCode(referenceCode);

                        RequestCaptureOrderInformation requestCaptureOrderInformation = new RequestCaptureOrderInformation();
                        RequestCaptureOrderInformationAmountDetails requestCaptureOrderInformationAmountDetails = new RequestCaptureOrderInformationAmountDetails();
                        requestCaptureOrderInformationAmountDetails.setCurrency("USD");
                        requestCaptureOrderInformationAmountDetails.setTotalAmount(requestPayment.getOrderInformation().getAmountDetails().getTotalAmount());
                        requestCaptureOrderInformation.setAmountDetails(requestCaptureOrderInformationAmountDetails);

                        requestCapture.setOrderInformation(requestCaptureOrderInformation);
                        requestCapture.setClientReferenceInformation(captureClientReferenceInformation);

                        ResponseCapture responseCapture = captureCybersource(ceposToken, requestCapture);

                        LOGGER.debug("==================================================================================================");
                        LOGGER.debug("Cybersource responseCapture.isSuccess():" + responseCapture.isSuccess());

                        if (responseCapture.isSuccess()) {
                            createOrder.setCs_status(responseCapture.getStatus());
                            createOrder.setCs_submit_time_utc(responseCapture.getSubmitTimeUtc());
                            createOrder.setCs_id(responseCapture.getId());

                            LOGGER.debug("==================================================================================================");
                            LOGGER.debug("Cybersource responseCapture.getId():" + responseCapture.getId());
                        }
                    }
                }

            }

            String salesOrderNumber = "fail";
            String billingOrderNumber = "fail";
            String ecommerce = "fail";
            String recon = "fail";
            String cardNumber = requestPayment.getPaymentInformation().getCard().getNumber();
            String card_type = Util.getCardType(cardNumber);

            createOrder.setSales_order_number(salesOrderNumber);
            createOrder.setBilling_order_number(billingOrderNumber);
            createOrder.setEcommerce(ecommerce);
            createOrder.setRecon(recon);
            createOrder.setCard_type(card_type);

            requestContractCreate.setToken(channelToken);

            //insert Request Payment Information to reproduce contract
            requestPayment.setOrderId(requestContractCreate.getOrderIdentifier());  //Set of ORDER ID

            //remove card information
            requestPayment.getPaymentInformation().getCard().setExpirationMonth("");
            requestPayment.getPaymentInformation().getCard().setExpirationYear("");
            requestPayment.getPaymentInformation().getCard().setSecurityCode("");

            if (cardNumber.length() > 10)
                requestPayment.getPaymentInformation().getCard().setNumber(cardNumber.substring(cardNumber.length() - 4, cardNumber.length()));
            else
                requestPayment.getPaymentInformation().getCard().setNumber(cardNumber);

            try {
                if ("local".equals(server_mode)) {
                    restCommonMapper.insertPaymentInfoWithoutDKMS(requestPayment);
                    //insert Request Contract Information to reproduce contract
                    restCommonMapper.insertCreateContractInfoWithoutDKMS(requestContractCreate);
                } else {
                    restCommonMapper.insertPaymentInfo(requestPayment);
                    //insert Request Contract Information to reproduce contract
                    restCommonMapper.insertCreateContractInfo(requestContractCreate);
                }

            } catch (Exception exception) {
                LOGGER.error("ERROR TO INSERT insertPaymentInfo or insertCreateContractInfo:-------------\n" + exception.getMessage());
                final RequestCancel requestCancel = new RequestCancel();
                final RequestCancelClientReferenceInformation cancelClientReferenceInformation = new RequestCancelClientReferenceInformation();
                cancelClientReferenceInformation.setCode(paymentResponse.getClientReferenceInformation().getCode());
                requestCancel.setClientReferenceInformation(cancelClientReferenceInformation);
                requestCancel.setCs_id(csID);
                final ResponseCancel paymentCancelResponse = cancelCybersource(ceposToken, requestCancel);
                if (paymentCancelResponse.isSuccess()) {
                    responseContractCreate.setSuccess(false);
                    responseContractCreate.setMsg("FORCE_CANCEL_CYBER_SOURCE_DUE_TO_DB_INSERT_ERROR");
                    responseContractCreate.setCode(paymentCancelResponse.getReason());
                } else {
                    responseContractCreate.setSuccess(false);
                    responseContractCreate.setMsg("FAIL_TO_FORCE_CANCEL_CYBER_SOURCE_DUE_TO_DB_INSERT_ERROR");
                    responseContractCreate.setCode(paymentCancelResponse.getReason());
                }
                createOrder.setScpus_ret_cd(null);
                createOrder.setScpus_ret_message(null);

                restCommonMapper.updateCreateOrder(createOrder);
                frontCommonService.sendErrorContractProcess(referenceCode, csID, errorDateTime);

                return responseContractCreate;

            }

            //create contract through SC+US
            responseContractCreate = contractCreate(ceposToken, requestContractCreate);

            createOrder.setScpus_ret_cd(responseContractCreate.getCode() == null ? "" : responseContractCreate.getCode());
            createOrder.setScpus_ret_message(responseContractCreate.getMsg() == null ? "" : responseContractCreate.getMsg());
            createOrder.setCs_approval_code(paymentResponse.getProcessorInformation().getApprovalCode());
            createOrder.setCs_network_transaction_id(paymentResponse.getProcessorInformation().getNetworkTransactionId());
            createOrder.setCs_transaction_id(paymentResponse.getProcessorInformation().getTransactionId());

            if (responseContractCreate.isSuccess()) {

                //send email
                String email = requestPayment.getOrderInformation().getBillTo().getEmail();
                String contractNo = responseContractCreate.getContractInfo().getContractNo();

                ConsumerContactHistory consumerContactHistory = new ConsumerContactHistory();
                consumerContactHistory.setReference_code(referenceCode);
                consumerContactHistory.setContract_no(contractNo);
                consumerContactHistory.setRequest_path("SYSTEM");
                consumerContactHistory.setResponse_type("EMAIL");
                consumerContactHistory.setReason_type("ORDER_CONFIRM");
                consumerContactHistory.setEmail(email);

                requestContractCreate.setContractNo(contractNo);

                //Get a contract information
                RequestContractStatus requestContractStatus = new RequestContractStatus();
                requestContractStatus.setEmail(email);
                requestContractStatus.setPolicyNo(contractNo);
                ResponseContractStatus responseContractStatus = getContractInfo(requestContractStatus);

                RequestPaymentOrderInformationLineItems lineItem = requestPayment.getOrderInformation().getLineItems().get(0);
                lineItem.setProductName(responseContractStatus.getContractStatusInfoResponse().getSkudesc());
                List<RequestPaymentOrderInformationLineItems> lineItems = new ArrayList<RequestPaymentOrderInformationLineItems>();
                lineItems.add(lineItem);
                requestPayment.setLineItems(lineItems);
                frontCommonService.sendContractConfirm(requestPayment, requestContractCreate, consumerContactHistory);

                //call email hooking api
                RequestEmailHook requestEmailHook = new RequestEmailHook();
                requestEmailHook = mappingEmailHook(consumerContactHistory, createOrder);
                callEmailHook(requestEmailHook);

                //call GERP API SaleOrder, BillingOrder, ECommerce
                //SaleOrder SD10513
                ResponseGetSalesOrder responseGetSalesOrder = new ResponseGetSalesOrder();
                RequestGetSalesOrder requestGetSalesOrder = new RequestGetSalesOrder();

                //Normal sales order "YN01"
                requestGetSalesOrder = mappingSalesOrderInfo("YN01", requestPayment, requestContractCreate, createOrder);
                responseGetSalesOrder = getSalesOrderByCEPOS(ceposToken, requestGetSalesOrder);

                createOrder.setContract_no(responseContractCreate.getContractInfo().getContractNo());
                createOrder.setPos_ret_cd("true");
                createOrder.setPos_ret_message("success");
                createOrder.setPos_ret_cd(CommonValue.Constant.CANCEL.STATUS.PROCESSED_SCPLUS);

                if (responseGetSalesOrder.isSuccess()) {
                    salesOrderNumber = responseGetSalesOrder.getSalesOrderNumber();

                    //BillingOrder SD02376
                    ResponseGetBillingOrder responseGetBillingOrder = new ResponseGetBillingOrder();
                    RequestGetBillingOrder requestGetBillingOrder = new RequestGetBillingOrder();

                    //Normal Billing order "YN01"
                    requestGetBillingOrder = mappingBillingOrderInfo(salesOrderNumber, requestContractCreate);
                    responseGetBillingOrder = getBillingOrderByCEPOS(ceposToken, requestGetBillingOrder);

                    if (responseGetBillingOrder.isSuccess()) {
                        billingOrderNumber = responseGetBillingOrder.getBillingOrderNumber();

                        //ECommerce FI11101
                        ResponseGetECommerce responseGetECommerce = new ResponseGetECommerce();
                        RequestGetECommerce requestGetECommerce = new RequestGetECommerce();

                        //Normal "SCPO" Cancel "SCPR"
                        requestGetECommerce = mappingECommerceInfo("SCPO", card_type, requestPayment, requestContractCreate);
                        responseGetECommerce = getECommerceByCEPOS(ceposToken, requestGetECommerce);
                        ecommerce = responseGetECommerce.getMessage();

                        if (responseGetECommerce.isSuccess()) {
                            //insert order data
                            createOrder.setSales_order_number(salesOrderNumber);
                            createOrder.setBilling_order_number(billingOrderNumber);
                            createOrder.setEcommerce(ecommerce);
                            createOrder.setPos_ret_cd(CommonValue.Constant.CANCEL.STATUS.PROCESSED_GERP);

                            ResponseFinanceRecon responseFinanceRecon = new ResponseFinanceRecon();
                            RequestFinanceRecon requestFinanceRecon = new RequestFinanceRecon();

                            //Normal Billing order "01"
                            requestFinanceRecon = mappingFinanceReconInfo(createOrder);

                            requestFinanceRecon.setToken(channelToken);
                            responseFinanceRecon = sendFinanceReconByCEPOS(ceposToken, requestFinanceRecon);

                            if (responseFinanceRecon.isSuccess()) {
                                createOrder.setRecon("success!");
                                createOrder.setPos_ret_cd(CommonValue.Constant.CANCEL.STATUS.PROCESSED_RECON);

                                restCommonMapper.updateCreateOrder(createOrder);
                                return responseContractCreate;

                            } else {
                                frontCommonService.sendErrorContractProcess(referenceCode, csID, errorDateTime);
                                restCommonMapper.updateCreateOrder(createOrder);

                                return responseContractCreate;
                            }
                        } else {
                            frontCommonService.sendErrorContractProcess(referenceCode, csID, errorDateTime);
                            createOrder.setSales_order_number(salesOrderNumber);
                            createOrder.setBilling_order_number(billingOrderNumber);
                            restCommonMapper.updateCreateOrder(createOrder);

                            return responseContractCreate;
                        }
                    } else {
                        frontCommonService.sendErrorContractProcess(referenceCode, csID, errorDateTime);
                        createOrder.setSales_order_number(salesOrderNumber);
                        restCommonMapper.updateCreateOrder(createOrder);

                        return responseContractCreate;
                    }
                } else {
                    frontCommonService.sendErrorContractProcess(referenceCode, csID, errorDateTime);
                    restCommonMapper.updateCreateOrder(createOrder);

                    return responseContractCreate;
                }
            } else {
                frontCommonService.sendErrorContractProcess(referenceCode, csID, errorDateTime);
                restCommonMapper.updateCreateOrder(createOrder);

                return responseContractCreate;
            }
        } else {
            responseContractCreate.setSuccess(false);
            responseContractCreate.setMsg(paymentResponse.getMessage());
            responseContractCreate.setCode(paymentResponse.getReason());

            createOrder.setScpus_ret_cd(paymentResponse.getReason());
            createOrder.setScpus_ret_message(paymentResponse.getMessage());

            restCommonMapper.updateCreateOrder(createOrder);

            return responseContractCreate;

        }
    }

    private RequestFinanceRecon mappingFinanceReconInfo(CreateOrder createOrder) {
        RequestFinanceRecon requestFinanceRecon = new RequestFinanceRecon();

        requestFinanceRecon.setBillingOrderNumber(createOrder.getBilling_order_number());
        //from contract start date to order date
        requestFinanceRecon.setContractDate(createOrder.getOrder_date());
        requestFinanceRecon.setContractNo(createOrder.getContract_no());
        requestFinanceRecon.setOrderItemIdentifier(createOrder.getOrderItem().get(0).getOrder_item_id());
        requestFinanceRecon.setPostingKey("01");
        requestFinanceRecon.setSalesOrderNumber(createOrder.getSales_order_number());
        requestFinanceRecon.setTotalAmount(createOrder.getTotal_price());
        requestFinanceRecon.setZipCode(createOrder.getZip_code());

        return requestFinanceRecon;
    }

    private RequestEmailHook mappingEmailHook(ConsumerContactHistory consumerContactHistory, CreateOrder cancelOrder) throws ParseException {
        RequestEmailHook requestEmailHook = new RequestEmailHook();

        RequestEmailHookPayload payload = new RequestEmailHookPayload();
        payload.setCustomer_email(consumerContactHistory.getEmail());
        payload.setChannel("CEPOS");
        payload.setCustomer_name(cancelOrder.getFirst_name() + " " + cancelOrder.getLast_name());
        payload.setEmail_id(restCommonMapper.getEmailHookId());
        payload.setOrder_id(consumerContactHistory.getReference_code());
        payload.setSend_date(TimezoneDateTimeUtil.getCurrentDateTimeString("EST", "yyyy-MM-dd HH:mm:ss"));
        payload.setStatus("success");
        payload.setSubject(consumerContactHistory.getSubject());

        requestEmailHook.setCountry("US");
        requestEmailHook.setCustomer_id("");
        requestEmailHook.setId("");
        requestEmailHook.setSamsung_acct_id("");
        requestEmailHook.setSource("CEPOS");
        requestEmailHook.setTimestamp(TimezoneDateTimeUtil.getCurrentDateTimeString("EST", "yyyy-MM-dd HH:mm:ss"));
        requestEmailHook.setType(consumerContactHistory.getReason_type());

        requestEmailHook.setPayload(payload);

        return requestEmailHook;
    }

    /**
     * {
     * "pi_if_key": "C7NHJ0WN01",
     * "doc_type": "YR01",			//normal : YN01  cancel: YR01
     * "purch_date": "20210401",
     * "purch_no_c": "CG5ZCHTR01",
     * "ref_doc": "3000374934",    //cancel
     * "ref_doc_l": "3000374934", //cancel
     * "material": "P-CT-1XXX050",
     * "purch_no_s": "CG5ZCHTR01",
     * "condvalue": "5.96",
     * "name": "Donghyun Kim",
     * "add_field_value": "89.99",
     * "text_line": "CE6789012346" //cancel
     * }
     */
    private RequestGetSalesOrder mappingSalesOrderInfo(String docType, RequestPayment requestPayment, RequestContractCreate requestContractCreate, CreateOrder createOrder) {
        RequestGetSalesOrder ret = new RequestGetSalesOrder();

        ret.setPi_if_key(requestContractCreate.getOrderItemIdentifier());
        ret.setDoc_type(docType);
        ret.setDoc_date(requestContractCreate.getOrderDate().replaceAll("-", ""));
        ret.setPurch_date(requestContractCreate.getOrderDate().replaceAll("-", ""));
        ret.setPurch_no_c(requestContractCreate.getOrderItemIdentifier());
        ret.setMaterial(createOrder.getOrderItem().get(0).getSvcpacksku());
        ret.setPurch_no_s(requestContractCreate.getContractNo());
        ret.setCondvalue("" + requestContractCreate.getSpcTaxAmt());
        ret.setName(requestPayment.getOrderInformation().getBillTo().getFirstName() + " " + requestPayment.getOrderInformation().getBillTo().getLastName());
        ret.setEmail(createOrder.getEmail());
        ret.setAddress(createOrder.getAddress());
        ret.setState(createOrder.getState());
        ret.setZip_code(createOrder.getZip_code());
        ret.setCity(createOrder.getCity());
        //ret.setAdd_field_value(requestPayment.getOrderInformation().getAmountDetails().getTotalAmount());
        //20210427 Modify from total amount to net amount
        ret.setAdd_field_value("" + requestContractCreate.getSpcNetPrice());


        return ret;
    }

    /**
     * {
     * "billingDate": "20210403",
     * "pricingDate": "20210403",
     * "salesOrderNumber": "CSD2347801"
     * }
     */
    private RequestGetBillingOrder mappingBillingOrderInfo(String salesOrderNumber, RequestContractCreate requestContractCreate) {
        RequestGetBillingOrder ret = new RequestGetBillingOrder();

        ret.setBillingType("YN01");
        ret.setSalesOrderNumber(salesOrderNumber);
        ret.setBillingDate(requestContractCreate.getOrderDate().replaceAll("-", ""));
        ret.setPricingDate(requestContractCreate.getOrderDate().replaceAll("-", ""));

        return ret;
    }

    /**
     * {
     * "trans_type": "SCPO",
     * "web_ordno": "CTEU47BD01",
     * "ord_date": "20210401",
     * "sales_order": "CTEU47BD01",
     * "anetid": "CTEU47BD01",
     * "trans_date": "20210401",
     * "ccins": "VISA",
     * "ccnum": "1111",
     * "wrbtr": "53.30"
     * }
     */
    private RequestGetECommerce mappingECommerceInfo(String trans_type, String ccins, RequestPayment requestPayment, RequestContractCreate requestContractCreate) {
        RequestGetECommerce ret = new RequestGetECommerce();

        ret.setTrans_type(trans_type);
        ret.setWeb_ordno(requestContractCreate.getOrderItemIdentifier());
        ret.setOrd_date(requestContractCreate.getOrderDate().replaceAll("-", ""));
        ret.setSales_order(requestContractCreate.getOrderItemIdentifier());
        ret.setAnetid(requestContractCreate.getOrderItemIdentifier());
        ret.setTrans_date(requestContractCreate.getOrderDate().replaceAll("-", ""));
        ret.setCcins(ccins);
        ret.setCcnum("1111");
        ret.setWrbtr(requestPayment.getOrderInformation().getAmountDetails().getTotalAmount());

        return ret;
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
        createOrder.setSales_order_number("");
        createOrder.setBilling_order_number("");
        createOrder.setEcommerce("");
        createOrder.setRecon("");
        createOrder.setCard_type("");
        createOrder.setAddress(requestPayment.getOrderInformation().getBillTo().getAddress1());
        createOrder.setCity(requestPayment.getOrderInformation().getBillTo().getLocality());
        createOrder.setZip_code(requestPayment.getOrderInformation().getBillTo().getPostalCode());
        createOrder.setState(requestPayment.getOrderInformation().getBillTo().getAdministrativeArea());

        String sku = requestContract.getSku();
        String svcpacksku = "";
        List<ResponseSpcValidation> product = SessionUtil.currentProduct();
        for (ResponseSpcValidation item : product) {
            if (sku.equals(item.getSKU())) {
                svcpacksku = item.getSVCPACKSKU();
                break;
            }
        }

        orderItem.setModel_cd(requestContract.getModel());
        orderItem.setOrder_item_id(requestContract.getOrderItemIdentifier());
        orderItem.setOrder_id(requestContract.getOrderIdentifier());
        orderItem.setQuantity(1);
        orderItem.setSerial_number(requestContract.getSerialNumber());
        orderItem.setSku(requestContract.getSku());
        orderItem.setSvcpacksku(svcpacksku);
        orderItem.setTax_amount(requestContract.getSpcTaxAmt());
        orderItem.setTotal_price(requestContract.getRetailItemAmount());
        orderItem.setUnit_price(requestContract.getSpcNetPrice());
        orderItem.setTax_rate(requestContract.getSpcTaxRate());

        list.add(orderItem);
        createOrder.setOrderItem(list);

        return createOrder;
    }

    /**
     * {
     * "accountIdentifier": "",
     * "associateName": "A lil Samsung shop",
     * "contractEndDate": "2022-02-10",
     * "contractStartDate": "2020-02-10",
     * "customer": {
     * "address1": "105 Challenger Rd.",
     * "address2": "8th Floor",
     * "city": "Ridgefield Park",
     * "country": "US",
     * "email": "michael.j@jackson5.com",
     * "firstName": "Jackson",
     * "lastName": "Michael",
     * "phone": 2010009999,
     * "state": "NJ",
     * "zipcode": "07660"
     * },
     * "mobile": true,
     * "mobileNumber": "2010009999",
     * "model": "RF23R6301SR/AA",
     * "orderDate": "2019-11-20",
     * "orderIdentifier": "SamsungSCMP20191111190030",
     * "orderItemIdentifier": "SamsungSCMP20191111190030-01",
     * "purchaseDate": "2019-11-21",
     * "retailItemAmount": 11.99,
     * "serialNumber": 11111111111115,
     * "sku": "REB-DOP36P-PB6",
     * "spcNetPrice": 10.99,
     * "spcPayDate": "2019-11-23",
     * "spcPaymentStatus": "G",
     * "spcPaymentType": "S",
     * "spcTaxAmt": 0.69,
     * "spcTaxRate": 6.25,
     * "storeName": "A lil Samsung shop"
     * }
     *
     * @throws ParseException
     */
    private RequestContractCreate mappingContractInfo(RequestPayment requestPayment) throws ParseException {
        RequestContractCreate contractInfo = new RequestContractCreate();

        ServletRequestAttributes serviceRequestAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession httpSession = serviceRequestAttr.getRequest().getSession();
        String purchaseDate = (String) httpSession.getAttribute("purchaseDate");

        String orderDate = TimezoneDateTimeUtil.getCurrentDateString();

        //contract start date is purchase date + 12 months
        String contractStartDate = TimezoneDateTimeUtil.getAddMonthToCurrentDateString(purchaseDate, 12);
        contractInfo.setContractStartDate(contractStartDate);

        //contract end date is contractStartDate + term
        int term = Integer.parseInt(requestPayment.getOrderInformation().getLineItems().get(0).getProductDescription());
        String contractEndDate = TimezoneDateTimeUtil.getAddMonthToCurrentDateString(contractStartDate, term);
        contractEndDate = TimezoneDateTimeUtil.getAddDayToCurrentDateString(contractEndDate, -1);

        contractInfo.setContractEndDate(contractEndDate);

        RequestContractCreateCustomerInfo customer = new RequestContractCreateCustomerInfo();
        customer.setAddress1(requestPayment.getOrderInformation().getBillTo().getAddress1());
        customer.setAddress2(requestPayment.getOrderInformation().getBillTo().getAddress2());
        customer.setCity(requestPayment.getOrderInformation().getBillTo().getLocality());
        customer.setCountry(requestPayment.getOrderInformation().getBillTo().getCountry());
        customer.setEmail(requestPayment.getOrderInformation().getBillTo().getEmail());
        customer.setFirstName(requestPayment.getOrderInformation().getBillTo().getFirstName());
        customer.setLastName(requestPayment.getOrderInformation().getBillTo().getLastName());
        customer.setPhone(requestPayment.getOrderInformation().getBillTo().getPhoneNumber());
        customer.setState(requestPayment.getOrderInformation().getBillTo().getAdministrativeArea());
        customer.setZipcode(requestPayment.getOrderInformation().getBillTo().getPostalCode());
        customer.setAddressType("10"); // address type   10: order info    20: billing info    30: shipping info
        contractInfo.setCustomer(customer);

        contractInfo.setMobile(false);
        contractInfo.setMobileNumber(requestPayment.getOrderInformation().getBillTo().getPhoneNumber());
        contractInfo.setModel(requestPayment.getOrderInformation().getLineItems().get(0).getProductName());
        contractInfo.setOrderDate(orderDate);

        String orderIdentifier = restCommonMapper.getOrderId();
        contractInfo.setOrderIdentifier(orderIdentifier);

//		String orderItemIdentifier = restCommonMapper.getOrderId();
        contractInfo.setOrderItemIdentifier(orderIdentifier + "01");

        contractInfo.setAccountIdentifier("");
        contractInfo.setAssociateName("");
        contractInfo.setStoreName("");
        contractInfo.setPurchaseDate(purchaseDate);
        contractInfo.setRetailItemAmount(Double.parseDouble(requestPayment.getOrderInformation().getAmountDetails().getTotalAmount()));
        contractInfo.setSerialNumber(requestPayment.getOrderInformation().getLineItems().get(0).getProductCode().trim());
        contractInfo.setSku(requestPayment.getOrderInformation().getLineItems().get(0).getProductSku());
        contractInfo.setSpcNetPrice(Double.parseDouble(requestPayment.getOrderInformation().getLineItems().get(0).getUnitPrice()));
        contractInfo.setSpcPayDate(contractStartDate);
        contractInfo.setSpcPaymentStatus("C"); //G: Payment in progress, C: Payment Completed. F: Failed, C만전송
        contractInfo.setSpcPaymentType("S"); //S : Single Payment M: Monthly Payment
        contractInfo.setSpcTaxAmt(Double.parseDouble(requestPayment.getOrderInformation().getLineItems().get(0).getTaxAmount()));
        contractInfo.setSpcTaxRate(Double.parseDouble(requestPayment.getOrderInformation().getLineItems().get(0).getTaxRate()));

        RequestSerialNoValidation requestSerialNoValidation = SessionUtil.currentUser();
        String agentId = requestSerialNoValidation.getAgentId();

        if (agentId != null && !agentId.equals("")) {
            contractInfo.setAssociateName(agentId);
            contractInfo.setStoreName("Contact center");
        }

        String storename = requestSerialNoValidation.getStorename();

        if (storename != null && !storename.equals("")) {
            if ("membersappce".equals(storename)) {
                contractInfo.setStoreName("Members App");
            }
        }

        if(StringUtils.isNotEmpty(requestPayment.getOrderInformation().getTechnicianId())) {
            contractInfo.setTechnicianId(requestPayment.getOrderInformation().getTechnicianId());
            contractInfo.setAscNo(requestPayment.getOrderInformation().getAscNo());
            contractInfo.setTicketNo(requestPayment.getOrderInformation().getTicketNo());
            contractInfo.setDiscountAmount(requestPayment.getOrderInformation().getDiscountAmount());
            contractInfo.setPromotionType("TECHNICIAN");
        }

        return contractInfo;
    }

    private CreateOrder mappingCancelOrderInfo(RequestContractCancel requestPayment, CreateOrder requestContract) {

        CreateOrder createOrder = new CreateOrder();
        List<CreateOrderItem> list = new ArrayList<CreateOrderItem>();
        CreateOrderItem orderItem = new CreateOrderItem();

        String cancelOrderId = restCommonMapper.getOrderId();
        createOrder.setCancel_order_id(cancelOrderId);

        String cancelOrderItemId = cancelOrderId + "01"; //restCommonMapper.getOrderId();

        createOrder.setCancel_order_date(requestPayment.getCancelDate());
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

        //Get a Token from CEPOS API Server
        String ceposToken = getTokenFromCEPOS();

        ResponseContractCancel responseContractCancel = new ResponseContractCancel();
        RequestCancel requestCancel = new RequestCancel();
        ResponseCancel responseCancel = new ResponseCancel();
        String contractNo = requestContractCancel.getContractNo();
        CreateOrder orderInfo = restCommonMapper.getOrderInfo(contractNo);

        String date = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")));
        String cancelDate = TimezoneDateTimeUtil.getCurrentDateString();
        requestContractCancel.setCancelDate(cancelDate);
        requestContractCancel.setEmail(orderInfo.getEmail());
        requestContractCancel.setFirstName(orderInfo.getFirst_name());
        requestContractCancel.setLastName(orderInfo.getLast_name());
//		orderInfo.setEmail(requestContractCancel.getEmail());
//		orderInfo.setFirst_name(requestContractCancel.getFirstName());
//		orderInfo.setLast_name(requestContractCancel.getLastName());

        CreateOrder cancelOrder = new CreateOrder();
        cancelOrder = mappingCancelOrderInfo(requestContractCancel, orderInfo);

        RequestCancelClientReferenceInformation clientReferenceInformation = new RequestCancelClientReferenceInformation();
        String referenceCode = cancelOrder.getOrderItem().get(0).getCancel_order_item_id();
        clientReferenceInformation.setCode(referenceCode);

        LOGGER.debug("==================================================================================================");
        LOGGER.debug("Cybersource cancel request ID :" + orderInfo.getCs_id());

        requestCancel.setClientReferenceInformation(clientReferenceInformation);
        requestCancel.setCs_id(orderInfo.getCs_id());

        cancelOrder.setCs_status("200");
        cancelOrder.setCs_submit_time_utc(date);
        cancelOrder.setCs_id("cancel by Consumer");
        cancelOrder.setEmail(requestContractCancel.getEmail());
        cancelOrder.setFirst_name(requestContractCancel.getFirstName());
        cancelOrder.setLast_name(requestContractCancel.getLastName());
        cancelOrder.setBilling_order_number("");
        cancelOrder.setSales_order_number("");
        cancelOrder.setEcommerce("");
        cancelOrder.setRecon("");
        cancelOrder.setCard_type("" + orderInfo.getCard_type());
        cancelOrder.setCancel_type("cancel");

        String channelToken = getTokenFromSCPUS(scpus_username, scpus_password);
        requestContractCancel.setToken(channelToken);
        requestContractCancel.setRefundOrderItemId(referenceCode);

        //create contract through SC+US
        responseContractCancel = contractCancel(ceposToken, requestContractCancel);

        cancelOrder.setScpus_ret_cd(responseContractCancel.getCode());
        cancelOrder.setScpus_ret_message(responseContractCancel.getMsg());
        //cancelOrder.setCs_response_code(response.getProcessorInformation().getResponseCode());

        if (responseContractCancel.isSuccess() == true) {

            //send email
            ConsumerContactHistory consumerContactHistory = new ConsumerContactHistory();
            consumerContactHistory.setReference_code(referenceCode);
            consumerContactHistory.setContract_no(contractNo);
            consumerContactHistory.setRequest_path("SYSTEM");
            consumerContactHistory.setResponse_type("EMAIL");
            consumerContactHistory.setReason_type("CANCEL_CONFIRM");
            consumerContactHistory.setEmail(requestContractCancel.getEmail());

            frontCommonService.sendContractCancel(cancelOrder, consumerContactHistory);

            RequestEmailHook requestEmailHook = new RequestEmailHook();
            requestEmailHook = mappingEmailHook(consumerContactHistory, cancelOrder);
            callEmailHook(requestEmailHook);

            //insert order data
            cancelOrder.setPos_ret_cd(CommonValue.Constant.CANCEL.STATUS.PROCESSED_SCPLUS);
            cancelOrder.setPos_ret_message("success");

            restCommonMapper.insertCancelOrder(cancelOrder);

            return responseContractCancel;

        } else {

            cancelOrder.setPos_ret_cd(CommonValue.Constant.CANCEL.STATUS.PROCESSED_PG);
            restCommonMapper.insertCancelOrder(cancelOrder);

            return responseContractCancel;
        }

    }

    /*
     * To cancel, it is sent to Cybersource and SC+US
     */
    public ResponseContractCancel cancel_07212021(RequestContractCancel requestContractCancel) throws IOException, ParseException {

        String cancelDate = TimezoneDateTimeUtil.getCurrentDateString();
        requestContractCancel.setCancelDate(cancelDate);

        //Get a Token from CEPOS API Server
        String ceposToken = getTokenFromCEPOS();

        ResponseContractCancel responseContractCancel = new ResponseContractCancel();
        RequestCancel requestCancel = new RequestCancel();
        ResponseCancel responseCancel = new ResponseCancel();
        String contractNo = requestContractCancel.getContractNo();
        CreateOrder orderInfo = restCommonMapper.getOrderInfo(contractNo);
        orderInfo.setEmail(requestContractCancel.getEmail());
        orderInfo.setFirst_name(requestContractCancel.getFirstName());
        orderInfo.setLast_name(requestContractCancel.getLastName());

        CreateOrder cancelOrder = new CreateOrder();
        cancelOrder = mappingCancelOrderInfo(requestContractCancel, orderInfo);

        RequestCancelClientReferenceInformation clientReferenceInformation = new RequestCancelClientReferenceInformation();
        String referenceCode = cancelOrder.getOrderItem().get(0).getCancel_order_item_id();
        clientReferenceInformation.setCode(referenceCode);

        LOGGER.debug("==================================================================================================");
        LOGGER.debug("Cybersource cancel request ID :" + orderInfo.getCs_id());

        requestCancel.setClientReferenceInformation(clientReferenceInformation);
        requestCancel.setCs_id(orderInfo.getCs_id());

        //send void to Cybersource
        ResponseCancel response = cancelCybersource(ceposToken, requestCancel);

        cancelOrder.setCs_id(response.getId());
        cancelOrder.setCs_status(response.getStatus());
        cancelOrder.setCs_submit_time_utc(response.getSubmitTimeUtc());
        cancelOrder.setEmail(requestContractCancel.getEmail());
        cancelOrder.setFirst_name(requestContractCancel.getFirstName());
        cancelOrder.setLast_name(requestContractCancel.getLastName());
        cancelOrder.setBilling_order_number("");
        cancelOrder.setSales_order_number("");
        cancelOrder.setEcommerce("");
        cancelOrder.setRecon("");
        cancelOrder.setCard_type("" + orderInfo.getCard_type());
        cancelOrder.setCancel_type("cancel");

        LOGGER.debug("==================================================================================================");
        LOGGER.debug("Cybersource cancel response ID :" + response.getId());

        if (response.isSuccess() == false) {

            String status = response.getStatus();
            String reason = response.getReason();

            if ("NOT_VOIDABLE".equals(reason) && "INVALID_REQUEST".equals(status)) {

                String orderId = orderInfo.getOrder_id();
                RequestPayment requestPaymentInfo = restCommonMapper.getPaymentInfo(orderId);

                String card_type = getCardType(orderInfo.getCard_type());

                RequestPaymentPaymentInformation t = new RequestPaymentPaymentInformation();
                RequestPaymentPaymentInformationCard tmp = new RequestPaymentPaymentInformationCard();
                RequestPaymentProcessingInformation proInfo = new RequestPaymentProcessingInformation();
                String linkId = orderInfo.getCs_id();
                proInfo.setLinkId(linkId);
                RequestPaymentClientReferenceInformation cliInfo = new RequestPaymentClientReferenceInformation();
                cliInfo.setCode(referenceCode);

                t = requestPaymentInfo.getPaymentInformation();
                tmp = t.getCard();
                tmp.setType(card_type);
                t.setCard(tmp);
                requestPaymentInfo.setPaymentInformation(t);
                requestPaymentInfo.setProcessingInformation(proInfo);
                requestPaymentInfo.setClientReferenceInformation(cliInfo);

                ResponsePayment responseCredit = new ResponsePayment();
                responseCredit = creditCyberSourceService(ceposToken, requestPaymentInfo);

                if (responseCredit.isSuccess() == true) {

                    response.setSuccess(true);

                    cancelOrder.setCs_id(responseCredit.getId());
                    cancelOrder.setCs_status(responseCredit.getStatus());
                    cancelOrder.setCs_submit_time_utc(responseCredit.getSubmitTimeUtc());

                } else {
                    responseContractCancel.setSuccess(false);
                    responseContractCancel.setMsg(responseCredit.getMessage());
                    responseContractCancel.setCode(responseCredit.getReason());
                    responseContractCancel.setTimestamp(responseCredit.getSubmitTimeUtc());

                    restCommonMapper.insertCancelOrder(cancelOrder);

                    return responseContractCancel;
                }

            } else {
                responseContractCancel.setSuccess(false);
                responseContractCancel.setMsg(response.getMessage());
                responseContractCancel.setCode(response.getReason());

                restCommonMapper.insertCancelOrder(cancelOrder);

                return responseContractCancel;

            }
        }

        if (response.isSuccess() == true) {

            String channelToken = getTokenFromSCPUS(scpus_username, scpus_password);
            requestContractCancel.setToken(channelToken);
            requestContractCancel.setRefundOrderItemId(referenceCode);

            //create contract through SC+US
            responseContractCancel = contractCancel(ceposToken, requestContractCancel);

            cancelOrder.setScpus_ret_cd(responseContractCancel.getCode());
            cancelOrder.setScpus_ret_message(responseContractCancel.getMsg());
//					cancelOrder.setCs_response_code(response.getProcessorInformation().getResponseCode());

            if (responseContractCancel.isSuccess() == true) {

                //send email
                ConsumerContactHistory consumerContactHistory = new ConsumerContactHistory();
                consumerContactHistory.setReference_code(referenceCode);
                consumerContactHistory.setContract_no(contractNo);
                consumerContactHistory.setRequest_path("SYSTEM");
                consumerContactHistory.setResponse_type("EMAIL");
                consumerContactHistory.setReason_type("CANCEL_CONFIRM");
                consumerContactHistory.setEmail(requestContractCancel.getEmail());

                frontCommonService.sendContractCancel(cancelOrder, consumerContactHistory);

                RequestEmailHook requestEmailHook = new RequestEmailHook();
                requestEmailHook = mappingEmailHook(consumerContactHistory, cancelOrder);
                callEmailHook(requestEmailHook);

                //insert order data
                cancelOrder.setPos_ret_cd(CommonValue.Constant.CANCEL.STATUS.PROCESSED_SCPLUS);
                cancelOrder.setPos_ret_message("success");

                restCommonMapper.insertCancelOrder(cancelOrder);

                return responseContractCancel;

            } else {

                cancelOrder.setPos_ret_cd(CommonValue.Constant.CANCEL.STATUS.PROCESSED_PG);
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

    private String getCardType(String card_type) {
        if ("VISA".equals(card_type))
            return "001";
        else if ("MASTER".equals(card_type))
            return "002";
        else if ("AEXPRESS".equals(card_type))
            return "003";
        else if ("DISCOVERS".equals(card_type))
            return "004";
        else if ("DINERS".equals(card_type))
            return "005";
        else if ("JCB".equals(card_type))
            return "007";
        else
            return "999";
    }

    @Override
    public String getTokenFromSCPUS(String username, String password) throws IOException {

        String response = "";

        try {
            String urlParameters = "?username=" + username + "&password=" + password;
            String functionPath = scpus_channelSigninURL;
            String apiURL = spBaseUrl;

            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath + urlParameters);

            } else {
                url = new URL(null, apiURL + functionPath + urlParameters, new sun.net.www.protocol.https.Handler());

            }

//			java.net.URL url 			= new URL(null, apiURL+functionPath+urlParameters,new sun.net.www.protocol.https.Handler() );

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
//			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setDoInput(true);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_JSON);

            int responseCode = con.getResponseCode();
            BufferedReader br;
            StringBuilder sb = new StringBuilder();

            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            br.close();
            con.disconnect();

            LOGGER.debug(sb.toString());
            String resJson = sb.toString();
            LOGGER.debug(resJson);

            try {

                if (responseCode == 200) {
                    response = resJson;
                    LOGGER.debug("success : " + response.toString());

                } else {
                    response = "fail";
                    LOGGER.debug("fail : " + resJson);
                }


            } catch (Exception e) {
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

        try {
            String urlParameters = "?userid=" + username + "&passwd=" + password;
            String functionPath = scpus_signinURL;
            String apiURL = spBaseUrl;

            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath + urlParameters);

            } else {
                url = new URL(null, apiURL + functionPath + urlParameters, new sun.net.www.protocol.https.Handler());

            }
//			java.net.URL url 			= new URL(null, apiURL+functionPath+urlParameters,new sun.net.www.protocol.https.Handler() );

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setDoInput(true);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            int responseCode = con.getResponseCode();
            BufferedReader br;
            StringBuilder sb = new StringBuilder();

            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            br.close();
            con.disconnect();

            LOGGER.debug(sb.toString());
            String resJson = sb.toString();
            LOGGER.debug(resJson);

            try {

                if (responseCode == 200) {
                    response = resJson;
                    LOGGER.debug("success : " + response.toString());

                } else {
                    response = "fail";
                    LOGGER.debug("fail : " + resJson);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return response;
    }

    /**
     * http://gcsc.samsung.com/scmpapi/v1/scp/cxEmailDelivery
     * {
     * "channel": "CEPOS",
     * "order_id": "orderid12345",
     * "email_id": 1,
     * "email_type": "Recurring Payment Success",
     * "subject": "Your Order Payment Success",
     * "status": "RECIEVE",
     * "customer_name": "SENT",
     * "customer_email": "test@gmail.com",
     * "send_date": "2021-02-24 05:58:29"
     * }
     */
    private void callEmailHook(RequestEmailHook requestEmailHook) {

        try {
            String functionPath = scpus_emaiHookURL;
            String apiURL = spBaseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }

            ObjectMapper mapper = new ObjectMapper();

            String urlParameters = mapper.writeValueAsString(requestEmailHook);
            LOGGER.debug("urlParameters : " + urlParameters);
            JSONObject jsonObject = new JSONObject(urlParameters);

            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

        } catch (Throwable e) {

            e.printStackTrace();
        }

    }

    /**
     * http://gcsc.samsung.com/scmpapi/v1/getSpcValidation?channel=SESL&company=C310&modelcode=SM-N975UZKAXAG
     * http://gcsc.samsung.com/scmpapi/v1/getSpcValidation?channel=CEPOS&company=C310&modelcode=QN85Q90TAFXZA
     */
    public List<ResponseSpcValidation> getSpcValidation(String ceposToken, RequestSpcValidation requestSpcValidation) {

        String response = "";

        try {
            String functionPath = cepos_getSpcValidationURL;
            String apiURL = cepos_baseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }

            JSONObject jsonObject = new JSONObject();

            jsonObject.accumulate("token", requestSpcValidation.getToken());
            jsonObject.accumulate("company", requestSpcValidation.getCompany());
            jsonObject.accumulate("channel", requestSpcValidation.getChannel());
            jsonObject.accumulate("modelcode", requestSpcValidation.getModelcode());

            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setRequestProperty("Authorization", "Bearer " + ceposToken);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

            try {
                JsonObject jsonObj = new JsonParser().parse(resJson).getAsJsonObject();
                boolean success = jsonObj.get("success").getAsBoolean();
                if (success == true) {
                    response = jsonObj.get("data").toString();
                    ObjectMapper mapper = new ObjectMapper();
                    List<ResponseSpcValidation> responseSpcValidation = Arrays.asList(mapper.readValue(response, ResponseSpcValidation[].class));

                    if (responseCode == 200) {
                        LOGGER.debug("success : " + response.toString());
                        LOGGER.debug("myResponse : " + responseSpcValidation);
                        return responseSpcValidation;

                    }

                } else {
                    response = "fail";
                    LOGGER.debug("fail : " + resJson);

                    throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return null;

    }

    /**
     * {
     * "company": "C310",
     * "modelCode": "SM-N960UZBAATT",
     * "serialNo": 358620092314569
     * }
     */
    public ResponseSerialNoValidation gspnCheckSerialNoValidation(String ceposToken, RequestSerialNoValidation requestSerialNoValidation) {

        ResponseSerialNoValidation responseSerialNoValidation = new ResponseSerialNoValidation();
        responseSerialNoValidation.setSuccess(false);
        String response = "fail";
        try {

            String functionPath = cepos_gspnCheckSerialNoValidationURL;
            String apiURL = cepos_baseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }

            LOGGER.debug("functionPath : " + functionPath);
            LOGGER.debug("apiURL : " + apiURL);

            JSONObject jsonObject = new JSONObject();

            jsonObject.accumulate("token", requestSerialNoValidation.getToken());
            jsonObject.accumulate("company", requestSerialNoValidation.getCompany());
            jsonObject.accumulate("modelCode", requestSerialNoValidation.getModelCode());
            jsonObject.accumulate("serialNo", requestSerialNoValidation.getSerialNo());

            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setRequestProperty("Authorization", "Bearer " + ceposToken);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

            try {

                JsonObject jsonObj = new JsonParser().parse(resJson).getAsJsonObject();
                if (responseCode == 200 || responseCode == 201) {
                    boolean success = jsonObj.get("success").getAsBoolean();
                    if (success == true) {
                        response = jsonObj.get("data").toString();
                        ObjectMapper mapper = new ObjectMapper();
                        responseSerialNoValidation = mapper.reader().forType(ResponseSerialNoValidation.class).readValue(response);

                        if (responseCode == 200) {
                            LOGGER.debug("success : " + responseSerialNoValidation.toString());
                            LOGGER.debug("myResponse : " + responseSerialNoValidation.isSuccess());

                        }

                    } else {

                        LOGGER.debug("fail : " + resJson);
                    }

                } else {
                    response = jsonObj.get("meta").toString();
                    ObjectMapper mapper = new ObjectMapper();
                    Meta meta = mapper.reader().forType(Meta.class).readValue(response);
                    responseSerialNoValidation.setSuccess(false);
                    responseSerialNoValidation.setCode(meta.getError_code());
                    responseSerialNoValidation.setMsg(meta.getError_message());
                    LOGGER.debug("response meta : " + meta);
                    LOGGER.debug("response error_code : " + meta.getError_code());
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return responseSerialNoValidation;
    }


    /**
     * {
     * "channel": "CEPOS",
     * "company": "C310",
     * "email": "Francesca10@gmail.com",
     * "policyNo": "675172616",
     * "serialNo": "694095854396915"
     * }
     */
    public ResponseContractStatus contractStatus(String ceposToken, RequestContractStatus requestContractStatus) {

        ResponseContractStatus responseContractStatus = new ResponseContractStatus();
        String response = "";
        try {
            String functionPath = cepos_getContractStatusURL;
            String apiURL = cepos_baseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }

            JSONObject jsonObject = new JSONObject();

            jsonObject.accumulate("token", requestContractStatus.getToken());
            jsonObject.accumulate("channel", requestContractStatus.getChannel());
            jsonObject.accumulate("company", requestContractStatus.getCompany());
            jsonObject.accumulate("email", requestContractStatus.getEmail());
            jsonObject.accumulate("policyNo", requestContractStatus.getPolicyNo());
            jsonObject.accumulate("serialNo", requestContractStatus.getSerialNo());

            String urlParameters = jsonObject.toString();
            LOGGER.debug("urlParameters : " + urlParameters);
            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setRequestProperty("Authorization", "Bearer " + ceposToken);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

            try {

                JsonObject jsonObj = new JsonParser().parse(resJson).getAsJsonObject();
                if (responseCode == 200 || responseCode == 201) {
                    boolean success = jsonObj.get("success").getAsBoolean();
                    if (success == true) {
                        response = jsonObj.get("data").toString();
                        ObjectMapper mapper = new ObjectMapper();
                        responseContractStatus = mapper.reader().forType(ResponseContractStatus.class).readValue(response);

                        if (responseCode == 200) {
                            LOGGER.debug("success : " + response.toString());
                            LOGGER.debug("myResponse : " + responseContractStatus);

                        }

                    } else {
                        response = "fail";
                        responseContractStatus.setSuccess(false);
                        LOGGER.debug("fail : " + resJson);
                    }

                } else {
                    response = jsonObj.get("meta").toString();
                    ObjectMapper mapper = new ObjectMapper();
                    Meta meta = mapper.reader().forType(Meta.class).readValue(response);
                    responseContractStatus.setSuccess(false);
                    responseContractStatus.setCode(meta.getError_code());
                    responseContractStatus.setMsg(meta.getError_message());
                    LOGGER.debug("response meta : " + meta);
                    LOGGER.debug("response error_code : " + meta.getError_code());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return responseContractStatus;
    }

    /**
     * {
     * "company": "C310",
     * "serialNo": "694095854396915"
     * }
     */
    public ResponseContractBySerialNo contractBySerialNo(String ceposToken, RequestContractBySerialNo requestContractStatus) {

        ResponseContractBySerialNo responseContractBySerialNo = new ResponseContractBySerialNo();
        String response = "";
        try {
            String functionPath = cepos_searchContractBySerialNoURL;
            String apiURL = cepos_baseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }

            JSONObject jsonObject = new JSONObject();

            jsonObject.accumulate("token", requestContractStatus.getToken());
            jsonObject.accumulate("company", requestContractStatus.getCompany());
            jsonObject.accumulate("serialNo", requestContractStatus.getSerialNo());

            String urlParameters = jsonObject.toString();
            LOGGER.debug("urlParameters : " + urlParameters);
            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setRequestProperty("Authorization", "Bearer " + ceposToken);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

            try {

                JsonObject jsonObj = new JsonParser().parse(resJson).getAsJsonObject();
                boolean success = jsonObj.get("success").getAsBoolean();
                if (success == true) {
                    response = jsonObj.get("data").toString();
                    ObjectMapper mapper = new ObjectMapper();
                    responseContractBySerialNo = mapper.reader().forType(ResponseContractBySerialNo.class).readValue(response);

                    if (responseCode == 200) {
                        LOGGER.debug("success : " + response.toString());
                        LOGGER.debug("myResponse : " + responseContractBySerialNo);

                    }

                } else {
                    response = "fail";
                    responseContractBySerialNo.setSuccess(false);
                    LOGGER.debug("fail : " + resJson);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return responseContractBySerialNo;
    }

    /**
     * {
     * "cancelDate": "2019-11-21",
     * "contractNo": 600009999,
     * "sendConfirmEmailYN": "N"
     * }
     */
    public ResponseContractCancel contractCancel(String ceposToken, RequestContractCancel requestContractCancel) {

        ResponseContractCancel responseContractCancel = new ResponseContractCancel();
        String response = "";
        try {
            String functionPath = cepos_getContractCancelURL;
            String apiURL = cepos_baseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }

            JSONObject jsonObject = new JSONObject();

            jsonObject.accumulate("token", requestContractCancel.getToken());
            jsonObject.accumulate("cancelDate", requestContractCancel.getCancelDate());
            jsonObject.accumulate("contractNo", requestContractCancel.getContractNo());
            jsonObject.accumulate("cancelReason", requestContractCancel.getCancelReason());
            jsonObject.accumulate("refundOrderItemId", requestContractCancel.getRefundOrderItemId());
            jsonObject.accumulate("sendConfirmEmailYN", requestContractCancel.getSendConfirmEmailYN());

//		    String urlParameters  = "{ \"cancelDate\": \"2019-11-21\",\"contractNo\": \"600009999\", \"sendConfirmEmailYN\": \"N\"}";
            String urlParameters = jsonObject.toString();
            LOGGER.debug("urlParameters : " + urlParameters);
            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setRequestProperty("Authorization", "Bearer " + ceposToken);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

            try {
                JsonObject jsonObj = new JsonParser().parse(resJson).getAsJsonObject();
                boolean success = jsonObj.get("success").getAsBoolean();
                if (success == true) {
                    response = jsonObj.get("data").toString();
                    ObjectMapper mapper = new ObjectMapper();
                    responseContractCancel = mapper.reader().forType(ResponseContractCancel.class).readValue(response);

                    if (responseCode == 200) {
                        LOGGER.debug("success : " + response.toString());
                        LOGGER.debug("myResponse : " + responseContractCancel);

                    }

                } else {
                    response = "fail";
                    responseContractCancel.setSuccess(false);
                    LOGGER.debug("fail : " + resJson);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return responseContractCancel;
    }

    /**
     * {
     * "cancelDate": "2019-11-21",
     * "cancelReason": "cancelReason",
     * "contractNo": 600009999,
     * "email": "michael.j@jackson5.com",
     * "firstName": "Jackson",
     * "lastName": "Michael",
     * }
     */
    public ResponseContractCancel cancelRequestByCEPOS(String ceposToken, RequestContractCancel requestContractCancel) {

        ResponseContractCancel responseContractCancel = new ResponseContractCancel();
        String response = "";
        try {
            String functionPath = cepos_cancelRequestURL;
            String apiURL = cepos_baseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }

            JSONObject jsonObject = new JSONObject();

            jsonObject.accumulate("cancelDate", requestContractCancel.getCancelDate());
            jsonObject.accumulate("contractNo", requestContractCancel.getContractNo());
            jsonObject.accumulate("cancelReason", requestContractCancel.getCancelReason());
            jsonObject.accumulate("email", requestContractCancel.getEmail());
            jsonObject.accumulate("firstName", requestContractCancel.getFirstName());
            jsonObject.accumulate("lastName", requestContractCancel.getLastName());

            String urlParameters = jsonObject.toString();
            LOGGER.debug("urlParameters : " + urlParameters);
            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setRequestProperty("Authorization", "Bearer " + ceposToken);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

            JsonObject jsonObj = new JsonParser().parse(resJson).getAsJsonObject();
            try {
                if (responseCode == 200 || responseCode == 201) {
                    boolean success = jsonObj.get("success").getAsBoolean();
                    if (success == true) {
                        response = jsonObj.get("data").toString();
                        ObjectMapper mapper = new ObjectMapper();
                        responseContractCancel = mapper.reader().forType(ResponseContractCancel.class).readValue(response);

                        if (responseCode == 200 || responseCode == 201) {
                            LOGGER.debug("success : " + response.toString());
                            LOGGER.debug("myResponse : " + responseContractCancel);

                        }

                    } else {
                        String msg = jsonObj.get("message").getAsString();

                        response = "fail";
                        responseContractCancel.setSuccess(false);
                        responseContractCancel.setMsg(msg);
                        LOGGER.debug("fail : " + resJson);
                    }

                } else {
                    response = jsonObj.get("meta").toString();
                    ObjectMapper mapper = new ObjectMapper();
                    Meta meta = mapper.reader().forType(Meta.class).readValue(response);
                    responseContractCancel.setSuccess(false);
                    responseContractCancel.setError(meta.getError_code());
                    responseContractCancel.setMsg(meta.getError_message());
                    LOGGER.debug("response meta : " + meta);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return responseContractCancel;
    }

    /**
     * {
     * "accountIdentifier": "string",
     * "associateName": "A lil Samsung shop",
     * "contractEndDate": "2022-02-10",
     * "contractStartDate": "2020-02-10",
     * "customer": {
     * "address1": "105 Challenger Rd.",
     * "address2": "8th Floor",
     * "city": "Ridgefield Park",
     * "country": "US",
     * "email": "michael.j@jackson5.com",
     * "firstName": "Jackson",
     * "lastName": "Michael",
     * "phone": 2010009999,
     * "state": "NJ",
     * "zipcode": "07660"
     * },
     * "mobile": true,
     * "mobileNumber": 2010009999,
     * "model": "RF23R6301SR/AA",
     * "orderDate": "2019-11-20",
     * "orderIdentifier": "SamsungSCMP20191111190030",
     * "orderItemIdentifier": "SamsungSCMP20191111190030-01",
     * "purchaseDate": "2019-11-21",
     * "retailItemAmount": 11.99,
     * "serialNumber": 11111111111115,
     * "sku": "REB-DOP36P-PB6",
     * "spcNetPrice": 10.99,
     * "spcPayDate": "2019-11-23",
     * "spcPaymentStatus": "G",
     * "spcPaymentType": "S",
     * "spcTaxAmt": 0.69,
     * "spcTaxRate": 6.25,
     * "storeName": "A lil Samsung shop"
     * }
     */
    public ResponseContractCreate contractCreate(String ceposToken, RequestContractCreate requestContractCreate) {

        LOGGER.debug("============================TECHNICIAN PROMOTION============================");
        LOGGER.debug("technicianId = " + requestContractCreate.getTechnicianId());
        LOGGER.debug("ascNo = " + requestContractCreate.getAscNo());
        LOGGER.debug("ticketNo = " + requestContractCreate.getTicketNo());
        LOGGER.debug("discountAmount = " + requestContractCreate.getDiscountAmount());
        LOGGER.debug("promotionType = " + requestContractCreate.getPromotionType());
        ResponseContractCreate responseContractCreate = new ResponseContractCreate();
        String response = "";
        try {
            String functionPath = cepos_contractCreateURL;
            String apiURL = cepos_baseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }

            ObjectMapper mapper = new ObjectMapper();

            String urlParameters = mapper.writeValueAsString(requestContractCreate);
            LOGGER.debug("urlParameters : " + urlParameters);
            JSONObject jsonObject = new JSONObject(urlParameters);

            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setRequestProperty("Authorization", "Bearer " + ceposToken);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

            try {
                JsonObject jsonObj = new JsonParser().parse(resJson).getAsJsonObject();
                boolean success = jsonObj.get("success").getAsBoolean();
                if (success == true) {
                    response = jsonObj.get("data").toString();
                    responseContractCreate = mapper.reader().forType(ResponseContractCreate.class).readValue(response);

                    if (responseCode == 200) {
                        LOGGER.debug("success : " + response.toString());
                        LOGGER.debug("myResponse : " + responseContractCreate);

                    }

                } else {
                    response = "fail";
                    responseContractCreate.setSuccess(false);

                    if (jsonObj.get("data") != null) {
                        response = jsonObj.get("data").toString();
                        responseContractCreate = mapper.reader().forType(ResponseContractCreate.class).readValue(response);

                    }
                    LOGGER.debug("fail : " + resJson);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return responseContractCreate;
    }


    /**
     *
     */
    public String getTokenFromCEPOS() {

        String response = "";
        try {
            String functionPath = cepos_getTokenURL;
            String apiURL = cepos_baseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }

            JSONObject jsonObject = new JSONObject();

            jsonObject.accumulate("systemid", cepos_username);
            jsonObject.accumulate("password", cepos_password);

//		    String urlParameters  = "{ \"username\": \"C310\", \"password\": \"sample\"}";
            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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
            response = sb.toString();

            try {

                LOGGER.debug("response : " + response);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return response;
    }

    /**
     *
     */
    public ResponsePayment paymentCybersource(String ceposToken, RequestPayment requestPayment) {

        ResponsePayment responsePayment = new ResponsePayment();
        String response = "";

        try {
            String functionPath = cepos_cyberPaymentURL;
            String apiURL = cepos_baseUrl;

            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }


            ObjectMapper Obj = new ObjectMapper();
            String jsonStr = Obj.writeValueAsString(requestPayment);

            JSONObject jsonObject = new JSONObject(jsonStr);

            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setRequestProperty("Authorization", "Bearer " + ceposToken);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

            try {
                JsonObject jsonObj = new JsonParser().parse(resJson).getAsJsonObject();
                boolean success = jsonObj.get("success").getAsBoolean();
                if (success == true) {
                    response = jsonObj.get("data").toString();
                    ObjectMapper mapper = new ObjectMapper();
                    responsePayment = mapper.reader().forType(ResponsePayment.class).readValue(response);

                    if (responseCode == 200) {
                        LOGGER.debug("success : " + response.toString());
                        LOGGER.debug("myResponse : " + responsePayment);

                    }

                } else {
                    response = "fail";
                    responsePayment.setSuccess(false);
                    LOGGER.debug("fail : " + resJson);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return responsePayment;

    }

    /**
     *
     */
    public ResponseCapture captureCybersource(String ceposToken, RequestCapture requestCapture) {

        ResponseCapture responseCapture = new ResponseCapture();
        String response = "";

        try {
            String functionPath = cepos_cyberCaptureURL;
            String apiURL = cepos_baseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }

            ObjectMapper Obj = new ObjectMapper();
            String jsonStr = Obj.writeValueAsString(requestCapture);

            JSONObject jsonObject = new JSONObject(jsonStr);

            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setRequestProperty("Authorization", "Bearer " + ceposToken);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

            try {
                JsonObject jsonObj = new JsonParser().parse(resJson).getAsJsonObject();
                boolean success = jsonObj.get("success").getAsBoolean();
                if (success == true) {
                    response = jsonObj.get("data").toString();
                    ObjectMapper mapper = new ObjectMapper();
                    responseCapture = mapper.reader().forType(ResponseCapture.class).readValue(response);

                    if (responseCode == 200) {
                        LOGGER.debug("success : " + response.toString());
                        LOGGER.debug("myResponse : " + responseCapture);

                    }

                } else {
                    response = "fail";
                    responseCapture.setSuccess(false);
                    LOGGER.debug("fail : " + resJson);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return responseCapture;

    }

    /**
     *
     */
    public ResponseReversal reversalCybersource(String ceposToken, RequestReversal requestReversal) {

        ResponseReversal responseReversal = new ResponseReversal();
        String response = "";

        try {
            String functionPath = cepos_cyberReversalURL;
            String apiURL = cepos_baseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }

            ObjectMapper Obj = new ObjectMapper();
            String jsonStr = Obj.writeValueAsString(requestReversal);

            JSONObject jsonObject = new JSONObject(jsonStr);

            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setRequestProperty("Authorization", "Bearer " + ceposToken);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

            try {
                JsonObject jsonObj = new JsonParser().parse(resJson).getAsJsonObject();
                boolean success = jsonObj.get("success").getAsBoolean();
                if (success == true) {
                    response = jsonObj.get("data").toString();
                    ObjectMapper mapper = new ObjectMapper();
                    responseReversal = mapper.reader().forType(ResponseReversal.class).readValue(response);

                    if (responseCode == 200) {
                        LOGGER.debug("success : " + response.toString());
                        LOGGER.debug("myResponse : " + responseReversal);

                    }

                } else {
                    response = "fail";
                    responseReversal.setSuccess(false);
                    LOGGER.debug("fail : " + resJson);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return responseReversal;

    }

    /**
     *
     */
    public ResponseCancel cancelCybersource(String ceposToken, RequestCancel requestCancel) {

        ResponseCancel responseCancel = new ResponseCancel();
        String response = "";

        try {
            String functionPath = cepos_cyberCancelURL;
            String apiURL = cepos_baseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }

            ObjectMapper Obj = new ObjectMapper();
            String jsonStr = Obj.writeValueAsString(requestCancel);

            JSONObject jsonObject = new JSONObject(jsonStr);

            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setRequestProperty("Authorization", "Bearer " + ceposToken);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

            try {
                JsonObject jsonObj = new JsonParser().parse(resJson).getAsJsonObject();
                boolean success = jsonObj.get("success").getAsBoolean();
                if (success == true) {
                    response = jsonObj.get("data").toString();
                    ObjectMapper mapper = new ObjectMapper();
                    responseCancel = mapper.reader().forType(ResponseCancel.class).readValue(response);

                    if (responseCode == 200) {
                        LOGGER.debug("success : " + response.toString());
                        LOGGER.debug("myResponse : " + responseCancel);

                    }

                } else {
                    response = "fail";
                    responseCancel.setSuccess(false);
                    LOGGER.debug("fail : " + resJson);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return responseCancel;

    }


    /**
     *
     */
    public ResponsePayment creditCyberSourceService(String ceposToken, RequestPayment requestPayment) {

        ResponsePayment responsePayment = new ResponsePayment();
        String response = "";

        try {
            String functionPath = cepos_cyberCreditlURL;
            String apiURL = cepos_baseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);
            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());
            }

            ObjectMapper Obj = new ObjectMapper();
            String jsonStr = Obj.writeValueAsString(requestPayment);

            JSONObject jsonObject = new JSONObject(jsonStr);

            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setRequestProperty("Authorization", "Bearer " + ceposToken);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

            try {
                JsonObject jsonObj = new JsonParser().parse(resJson).getAsJsonObject();
                boolean success = jsonObj.get("success").getAsBoolean();
                if (success == true) {
                    response = jsonObj.get("data").toString();
                    ObjectMapper mapper = new ObjectMapper();
                    responsePayment = mapper.reader().forType(ResponsePayment.class).readValue(response);

                    if (responseCode == 200) {
                        LOGGER.debug("success : " + response.toString());
                        LOGGER.debug("myResponse : " + responsePayment);

                    }

                } else {
                    response = "fail";
                    responsePayment.setSuccess(false);
                    LOGGER.debug("fail : " + resJson);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return responsePayment;

    }

    /**
     * {
     * "cancelDate": "2019-11-21",
     * "cancelReason": "cancelReason",
     * "contractNo": 600009999,
     * "email": "michael.j@jackson5.com",
     * "firstName": "Jackson",
     * "lastName": "Michael",
     * }
     */
    public ResponseGetTax getTaxByCEPOS(String ceposToken, RequestGetTax requestGetTax) {

        ResponseGetTax responseGetTax = new ResponseGetTax();
        String response = "";
        try {
            String functionPath = cepos_getTaxURL;
            String apiURL = cepos_baseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }

            JSONObject jsonObject = new JSONObject();

            jsonObject.accumulate("price", requestGetTax.getLineItem().get(0).getPrice());
            jsonObject.accumulate("postalCode", requestGetTax.getPostalCode());
            jsonObject.accumulate("product", requestGetTax.getLineItem().get(0).getModelCode());

            String urlParameters = jsonObject.toString();
            LOGGER.debug("urlParameters : " + urlParameters);
            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setRequestProperty("Authorization", "Bearer " + ceposToken);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

            JsonObject jsonObj = new JsonParser().parse(resJson).getAsJsonObject();
            try {
                if (responseCode == 200 || responseCode == 201) {
                    boolean success = jsonObj.get("success").getAsBoolean();
                    if (success == true) {
                        response = jsonObj.get("data").toString();
                        ObjectMapper mapper = new ObjectMapper();
                        responseGetTax = mapper.reader().forType(ResponseGetTax.class).readValue(response);

                        if (responseCode == 200 || responseCode == 201) {
                            LOGGER.debug("success : " + response.toString());
                            LOGGER.debug("myResponse : " + responseGetTax);

                        }

                    } else {
                        response = "fail";
                        responseGetTax.setSuccess(false);
                        LOGGER.debug("fail : " + resJson);
                    }

                } else {
                    response = jsonObj.get("meta").toString();
                    ObjectMapper mapper = new ObjectMapper();
                    Meta meta = mapper.reader().forType(Meta.class).readValue(response);
                    responseGetTax.setSuccess(false);
                    LOGGER.debug("response meta : " + meta);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return responseGetTax;
    }

    /**
     * {
     * "pi_if_key": "C7NHJ0WN01",
     * "doc_type": "YR01",			//normal : YN01  cancel: YR01
     * "purch_date": "20210401",
     * "purch_no_c": "CG5ZCHTR01",
     * "ref_doc": "3000374934",    //cancel
     * "ref_doc_l": "3000374934", //cancel
     * "material": "P-CT-1XXX050",
     * "purch_no_s": "CG5ZCHTR01",
     * "condvalue": "5.96",
     * "name": "Donghyun Kim",
     * "add_field_value": "89.99",
     * "text_line": "CE6789012346" //cancel
     * }
     */
    public ResponseGetSalesOrder getSalesOrderByCEPOS(String ceposToken, RequestGetSalesOrder requestGetSalesOrder) {

        ResponseGetSalesOrder responseGetSalesOrder = new ResponseGetSalesOrder();
        String response = "";
        try {
            String functionPath = cepos_getSalesOrderURL;
            String apiURL = cepos_baseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }

            ObjectMapper Obj = new ObjectMapper();
            String jsonStr = Obj.writeValueAsString(requestGetSalesOrder);

            JSONObject jsonObject = new JSONObject(jsonStr);

            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setRequestProperty("Authorization", "Bearer " + ceposToken);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

            JsonObject jsonObj = new JsonParser().parse(resJson).getAsJsonObject();
            try {
                if (responseCode == 200 || responseCode == 201) {
                    boolean success = jsonObj.get("success").getAsBoolean();
                    if (success == true) {
                        response = jsonObj.get("data").toString();
                        ObjectMapper mapper = new ObjectMapper();
                        responseGetSalesOrder = mapper.reader().forType(ResponseGetSalesOrder.class).readValue(response);

                        if (responseCode == 200 || responseCode == 201) {
                            LOGGER.debug("success : " + response.toString());
                            LOGGER.debug("myResponse : " + responseGetSalesOrder);

                        }

                    } else {
                        response = "fail";
                        responseGetSalesOrder.setSuccess(false);
                        LOGGER.debug("fail : " + resJson);
                    }

                } else {
                    response = jsonObj.get("meta").toString();
                    ObjectMapper mapper = new ObjectMapper();
                    Meta meta = mapper.reader().forType(Meta.class).readValue(response);
                    responseGetSalesOrder.setSuccess(false);
                    LOGGER.debug("response meta : " + meta);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return responseGetSalesOrder;
    }

    /**
     * "request":
     * {
     * "billingDate": "20210403",
     * "pricingDate": "20210403",
     * "salesOrderNumber": "CSD2347801"
     * }
     */
    public ResponseGetBillingOrder getBillingOrderByCEPOS(String ceposToken, RequestGetBillingOrder requestGetBillingOrder) {

        ResponseGetBillingOrder responseGetBillingOrder = new ResponseGetBillingOrder();
        String response = "";
        try {
            String functionPath = cepos_getBillingOrderURL;
            String apiURL = cepos_baseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }

            ObjectMapper Obj = new ObjectMapper();
            String jsonStr = Obj.writeValueAsString(requestGetBillingOrder);

            JSONObject jsonObject = new JSONObject(jsonStr);

            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setRequestProperty("Authorization", "Bearer " + ceposToken);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

            JsonObject jsonObj = new JsonParser().parse(resJson).getAsJsonObject();
            try {
                if (responseCode == 200 || responseCode == 201) {
                    boolean success = jsonObj.get("success").getAsBoolean();
                    if (success == true) {
                        response = jsonObj.get("data").toString();
                        ObjectMapper mapper = new ObjectMapper();
                        responseGetBillingOrder = mapper.reader().forType(ResponseGetBillingOrder.class).readValue(response);

                        if (responseCode == 200 || responseCode == 201) {
                            LOGGER.debug("success : " + response.toString());
                            LOGGER.debug("myResponse : " + responseGetBillingOrder);

                        }

                    } else {
                        response = "fail";
                        responseGetBillingOrder.setSuccess(false);
                        LOGGER.debug("fail : " + resJson);
                    }

                } else {
                    response = jsonObj.get("meta").toString();
                    ObjectMapper mapper = new ObjectMapper();
                    Meta meta = mapper.reader().forType(Meta.class).readValue(response);
                    responseGetBillingOrder.setSuccess(false);
                    LOGGER.debug("response meta : " + meta);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return responseGetBillingOrder;
    }

    /**
     * "request":
     * {
     * "trans_type": "SCPO",
     * "web_ordno": "CTEU47BD01",
     * "ord_date": "20210401",
     * "sales_order": "CTEU47BD01",
     * "anetid": "CTEU47BD01",
     * "trans_date": "20210401",
     * "ccins": "VISA",
     * "ccnum": "1111",
     * "wrbtr": "53.30"
     * }
     */
    public ResponseGetECommerce getECommerceByCEPOS(String ceposToken, RequestGetECommerce requestGetECommerce) {

        ResponseGetECommerce responseGetECommerce = new ResponseGetECommerce();
        String response = "";
        try {
            String functionPath = cepos_getECommerceURL;
            String apiURL = cepos_baseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }

            ObjectMapper Obj = new ObjectMapper();
            String jsonStr = Obj.writeValueAsString(requestGetECommerce);

            JSONObject jsonObject = new JSONObject(jsonStr);

            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setRequestProperty("Authorization", "Bearer " + ceposToken);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

            JsonObject jsonObj = new JsonParser().parse(resJson).getAsJsonObject();
            try {
                if (responseCode == 200 || responseCode == 201) {
                    boolean success = jsonObj.get("success").getAsBoolean();
                    if (success == true) {
                        response = jsonObj.get("data").toString();
                        ObjectMapper mapper = new ObjectMapper();
                        responseGetECommerce = mapper.reader().forType(ResponseGetECommerce.class).readValue(response);

                        if (responseCode == 200 || responseCode == 201) {
                            LOGGER.debug("success : " + response.toString());
                            LOGGER.debug("myResponse : " + responseGetECommerce);

                        }

                    } else {
                        response = "fail";
                        responseGetECommerce.setSuccess(false);
                        LOGGER.debug("fail : " + resJson);
                    }

                } else {
                    response = jsonObj.get("meta").toString();
                    ObjectMapper mapper = new ObjectMapper();
                    Meta meta = mapper.reader().forType(Meta.class).readValue(response);
                    responseGetECommerce.setSuccess(false);
                    LOGGER.debug("response meta : " + meta);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return responseGetECommerce;
    }

    /**
     * "request":
     * {
     * "token":"",
     * "orderItemIdentifier": "C310",
     * "contractNo": "607435319",
     * "salesOrderNumber": "1550788742",
     * "billingOrderNumber": "3404508137",
     * "contractDate": "2021-04-14",
     * "totalAmount": "199.99",
     * "zipCode": "77450",
     * "postingKey": "01"
     * }
     */
    public ResponseFinanceRecon sendFinanceReconByCEPOS(String ceposToken, RequestFinanceRecon requestFinanceRecon) {

        ResponseFinanceRecon responseFinanceRecon = new ResponseFinanceRecon();
        String response = "";
        try {
            String functionPath = cepos_sendFinanceReconURL;
            String apiURL = cepos_baseUrl;
            java.net.URL url = null;
            if ("local".equals(server_mode) || "dev".equals(server_mode)) {
                url = new URL(apiURL + functionPath);

            } else {
                url = new URL(null, apiURL + functionPath, new sun.net.www.protocol.https.Handler());

            }

            ObjectMapper Obj = new ObjectMapper();
            String jsonStr = Obj.writeValueAsString(requestFinanceRecon);

            JSONObject jsonObject = new JSONObject(jsonStr);

            byte[] postDataBytes = jsonObject.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("target_system", cepos_targetSystem);
            con.setRequestProperty("Authorization", "Bearer " + ceposToken);
            con.setDoOutput(true);

            if ("prod".equals(server_mode)) {
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(postDataBytes);
                wr.flush();
                wr.close();
            } catch (Exception e) {
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

            JsonObject jsonObj = new JsonParser().parse(resJson).getAsJsonObject();
            try {
                if (responseCode == 200 || responseCode == 201) {
                    boolean success = jsonObj.get("success").getAsBoolean();
                    if (success == true) {
                        response = jsonObj.get("data").toString();
                        ObjectMapper mapper = new ObjectMapper();
                        responseFinanceRecon = mapper.reader().forType(ResponseFinanceRecon.class).readValue(response);

                        if (responseCode == 200 || responseCode == 201) {
                            LOGGER.debug("success : " + response.toString());
                            LOGGER.debug("myResponse : " + responseFinanceRecon);

                        }

                    } else {
                        response = "fail";
                        responseFinanceRecon.setSuccess(false);
                        LOGGER.debug("fail : " + resJson);
                    }

                } else {
                    response = jsonObj.get("meta").toString();
                    ObjectMapper mapper = new ObjectMapper();
                    Meta meta = mapper.reader().forType(Meta.class).readValue(response);
                    responseFinanceRecon.setSuccess(false);
                    LOGGER.debug("response meta : " + meta);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Throwable e) {

            e.printStackTrace();
        }
        return responseFinanceRecon;
    }

}
