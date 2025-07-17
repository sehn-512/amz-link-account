package sea.scplus.consumer.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sea.scplus.consumer.common.constant.CommonValue;
import sea.scplus.consumer.common.exception.NormalRequestException;
import sea.scplus.consumer.common.mail.SMTPMailManager;
import sea.scplus.consumer.common.mail.SMTPMailMessage;
import sea.scplus.consumer.common.util.Util;
import sea.scplus.consumer.persistence.api.FrontCommonMapper;
import sea.scplus.consumer.persistence.api.RESTCommonMapper;
import sea.scplus.consumer.service.FrontCommonService;
import sea.scplus.consumer.service.RESTAPIService;
import sea.scplus.consumer.vo.*;
import sea.scplus.consumer.vo.erp.tax.RequestGetTax;
import sea.scplus.consumer.vo.erp.tax.RequestGetTaxLineItem;
import sea.scplus.consumer.vo.erp.tax.ResponseGetTax;
import sea.scplus.consumer.vo.order.CreateOrder;
import sea.scplus.consumer.vo.payment.RequestPayment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/scplus")
public class RESTCommonController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RESTCommonController.class);

    @Value("${spring.mail.properties.mail.smtp.from}")
    private String EMAIL_FROM;

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Autowired
    private RESTAPIService restAPIService;

    @Autowired
    private FrontCommonService frontCommonService;

    @Autowired
    private RESTCommonMapper restCommonMapper;

    @Autowired
    private FrontCommonMapper frontCommonMapper;

    @Autowired
    private SMTPMailManager mailManagerSMTP;

    @RequestMapping(value = "/resend_email", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody Meta resend_email(@RequestBody RequestContractCancel requestCancel,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        LOGGER.debug("[RESTCommonController.resend_email]");
        Meta meta = new Meta();
        meta.setMessage("success resend email");
        meta.setSuccess(true);

        String pdfFilePath = "";
        String contract_no = "";

        HttpSession session = request.getSession();
        if (session.getAttribute("contract_no") == null) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA)));
            return meta;
        }
        contract_no = (String) session.getAttribute("contract_no");
        CreateOrder orderInfo = restCommonMapper.getOrderInfo(contract_no);

        String emailByCustomer = orderInfo.getEmail();
        String contractNoByCustomer = orderInfo.getContract_no();

        ConsumerContactHistory consumerContactHistory = frontCommonService.selectContactHistory(contract_no);
        String customer_email = consumerContactHistory.getEmail();
        String subject = consumerContactHistory.getSubject();
        String content = consumerContactHistory.getContent();

        LOGGER.debug("[emailByCustomer:" + emailByCustomer + "[customer_email:" + customer_email);
        if (!emailByCustomer.equals(customer_email)) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA)));
            return meta;
        }

        LOGGER.debug("[contractNoByCustomer:" + contractNoByCustomer + "[session contract_no:" + contract_no);
        if (!contractNoByCustomer.equals(contract_no)) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA)));
            return meta;
        }

        SMTPMailMessage mailMessage = new SMTPMailMessage();
        mailMessage.setFrom(EMAIL_FROM);
        mailMessage.setTo(customer_email);
        mailMessage.setSubject("[Resend Email] " + subject);

        try {
            mailManagerSMTP.send(mailMessage, content, pdfFilePath);

            consumerContactHistory.setResponse_type("EMAIL");
            consumerContactHistory.setContent(content);
            consumerContactHistory.setFile_id(pdfFilePath);
            consumerContactHistory.setSubject(subject);
            consumerContactHistory.setReason_type("RESEND_CONFIRM");
            frontCommonMapper.insertConsumerContactHistory(consumerContactHistory);

        } catch (Exception e) {
            meta.setMessage(e.getMessage());
            meta.setSuccess(false);
            return meta;
        }
        return meta;

    }

    /**
     * Parameter
     * {
     * "recaptcha": true,
     * "modelCode": "UN82RU8000FXZA",
     * "serialNo": "08793CAM901748H",
     * "ticketNo": "4100157895" (Optional)
     * }
     *
     * @return {
     * "result": "SUCCESS",
     * "success": true,
     * "message": "success",
     * "data": [
     * {
     * "COMPANY": "C310",
     * "CHANNEL": "SESL",
     * "MODELCODE": "SM-N975UZKAXAG",
     * "PROGRAM": "POS",
     * "TERM": "1",
     * "MODELTYPE": "Mobile Phones",
     * "MODELFAMILY": "Note 10+",
     * "MEMORY": "256GB",
     * "SKU": "HHP-AD1P-PB24-L01",
     * "RENEWALSKU": "HHP-AD1P-PB24-L01-R",
     * "LAUNCHDATE": "23-Aug-19",
     * "PRICE": 11.99,
     * "COLOR": "Black",
     * "CARRIER": "Generic",
     * "MSRP": 1099.99,
     * "PAYMENTFREQUENCY": "MONTHLY"
     * },
     * {
     * "COMPANY": "C310",
     * "CHANNEL": "SESL",
     * "MODELCODE": "SM-N975UZKAXAG",
     * "PROGRAM": "POS",
     * "TERM": "24",
     * "MODELTYPE": "Mobile Phones",
     * "MODELFAMILY": "Note 10+",
     * "MEMORY": "256GB",
     * "SKU": "HHP-AD24P-PB36-L01",
     * "RENEWALSKU": null,
     * "LAUNCHDATE": "23-Aug-19",
     * "PRICE": 219.99,
     * "COLOR": "Black",
     * "CARRIER": "Generic",
     * "MSRP": 1099.99,
     * "PAYMENTFREQUENCY": "SINGLEPAY"
     * }
     * ]
     * }
     * @throws Exception
     */
    @RequestMapping(value = "/checkSerialNoValidation", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody Meta checkSerialNoValidation(@RequestBody RequestSerialNoValidation requestSerialNoValidation,
                                                      HttpServletRequest request,
                                                      HttpServletResponse response) throws Exception {

        request.getSession().setAttribute("ssoUser", null);
        request.getSession().setAttribute("product", null);
        request.getSession().invalidate();

        Meta meta = new Meta();
        meta.setResult("SUCCESS");

        if (!requestSerialNoValidation.isRecaptcha()) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_RECAPTCHA));
        }
        LOGGER.debug("modelCode = {}", requestSerialNoValidation.getModelCode());
        if (StringUtils.isEmpty(requestSerialNoValidation.getModelCode())) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_MODELCODE));
        }

        if (StringUtils.isEmpty(requestSerialNoValidation.getSerialNo())) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_SERIALNUMBER));
        }


        //Check Serial number and Model Code
        //List<ResponseSpcValidation> ret  = restCommonService.checkSerialNoValidation(requestSerialNoValidation);
        List<ResponseSpcValidation> ret = restAPIService.checkSerialNoValidation(requestSerialNoValidation);
        LOGGER.debug("ret = {}", ret);
        if (ret == null) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA));

        } else {

            if (ret.isEmpty()) {
                throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.NO_DATA));
            }

            for (ResponseSpcValidation item : ret) {
                String price = item.getPRICE();
                if (StringUtils.isEmpty(price)) {
                    throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.NO_DATA_PRICE));
                }

                if (item.getPRICE().equals("0") || item.getPRICE().equals("0.0")) {
                    throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_PRICE));
                }
            }
        }


        if (StringUtils.isNotEmpty(requestSerialNoValidation.getTicketNo())) {
            ResponseGetTicketInfo responseGetTicketInfo = restAPIService.getTicketInfo(requestSerialNoValidation.getTicketNo());
            if (responseGetTicketInfo != null) {
                ret.forEach(responseSpcValidation -> {
                    if (!StringUtils.equalsIgnoreCase(requestSerialNoValidation.getSerialNo(), responseGetTicketInfo.getSerial())
                            && isEligibleBE(responseGetTicketInfo.getAssignedASCNo())) {
                        responseSpcValidation.setTICKETNO(responseGetTicketInfo.getTicketNo());
                        responseSpcValidation.setASCNO(responseGetTicketInfo.getAssignedASCNo());
                        responseSpcValidation.setTICKETSTATUS(responseGetTicketInfo.getStatus());
                        responseSpcValidation.setTECHNICIANID(responseGetTicketInfo.getTechnicianID());
                        responseSpcValidation.setDISCOUNTAMOUNT(10.00);
                    }
                });
            }
        }
        LOGGER.debug("ret = {}", ret);

        meta.setMessage("success");
        meta.setSuccess(true);
        meta.setData(ret);

        HttpSession session = request.getSession();
        session.setAttribute("ssoUser", requestSerialNoValidation);
        session.setAttribute("product", ret);

//		String serialNo = requestSerialNoValidation.getSerialNo();
//		SessionUtil.setSession(serialNo);

        return meta;
    }

    private Boolean isEligibleBE(String ascNo) {
        String[] eligibleBEs = {"BFSCS25", "BFSCS23", "BFSCS09", "BFSCS12", "BFSCS15", "BFSCS01", "BFSCS03", "BFSCS10", "BFSCS37", "BFSCS43"};
        return Arrays.stream(eligibleBEs).anyMatch(be -> be.equalsIgnoreCase(ascNo));
    }

    ;

    /**
     * Parameter
     * {
     * "postalCode": "81635",
     * "country": "US",
     * "lineItem": [
     * {
     * "modelCode": "UN82RU8000FXZA",
     * "serialNo": "08793CAM901748H",
     * "sku": "08793CAM901748H",
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
     * @throws Exception
     */
    @RequestMapping(value = "/getTax", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody Meta getTax(@RequestBody RequestGetTax requestGetTax,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        Meta meta = new Meta();
        meta.setResult("fail");
        meta.setSuccess(false);

        if (requestGetTax.getPostalCode() == null) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_POSTALCODE)));
            return meta;
        }

        String postalCode = requestGetTax.getPostalCode();
        if (postalCode.length() != 5) {
            if (postalCode.length() == 1) {
                postalCode = "0000" + postalCode;
            } else if (postalCode.length() == 2) {
                postalCode = "000" + postalCode;
            } else if (postalCode.length() == 3) {
                postalCode = "00" + postalCode;
            } else if (postalCode.length() == 4) {
                postalCode = "0" + postalCode;
            }
            requestGetTax.setPostalCode(postalCode);
//            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_POSTALCODE));
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("ssoUser") == null) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA)));
            return meta;
        }
        if (session.getAttribute("product") == null) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA)));
            return meta;
        }

        RequestSerialNoValidation consumerInfo = (RequestSerialNoValidation) request.getSession().getAttribute("ssoUser");
        String customerModelCode = consumerInfo.getModelCode().trim();
        String customerSerialNumber = consumerInfo.getSerialNo().trim();

        LOGGER.debug("==================================================================================================");
        LOGGER.debug("Serial number:" + customerSerialNumber);
        LOGGER.debug("Model Code:" + customerModelCode);
        LOGGER.debug("==================================================================================================");

        LOGGER.debug("==================================================================================================");
        Double taxPrice = 0.0;
        String technicianId = null;
        List<RequestGetTaxLineItem> taxProductInfo = requestGetTax.getLineItem();
        for (RequestGetTaxLineItem item : taxProductInfo) {
            taxPrice = item.getPrice();
            technicianId = item.getTechnicianId();
            LOGGER.debug("MODELCODE:" + item.getModelCode());
            LOGGER.debug("Serial number:" + item.getSerialNo());
            LOGGER.debug("PRICE:" + taxPrice);
            if (!customerModelCode.equals(item.getModelCode())) {
                meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA)));
                return meta;
            }
//			if (!customerSerialNumber.equals(item.getSerialNo())) {
//				throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA));
//			}
        }
        LOGGER.debug("==================================================================================================");

        LOGGER.debug("==================================================================================================");
        boolean flag = false;
        List<ResponseSpcValidation> productInfo = (List<ResponseSpcValidation>) request.getSession().getAttribute("product");

        for (ResponseSpcValidation item : productInfo) {
            String price = item.getPRICE();
            if (price == null) break;

            double productPrice = Double.parseDouble(price);

            LOGGER.debug("MODELCODE:" + item.getMODELCODE());
            LOGGER.debug("PRICE:" + productPrice);
            if (productPrice == taxPrice || (StringUtils.isNotEmpty(technicianId) && productPrice - 10 == taxPrice)) {
                flag = true;
            }
        }
        LOGGER.debug("==================================================================================================");

        LOGGER.debug("==================================================================================================");
        if (flag == false) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA)));
            return meta;
        }
        LOGGER.debug("==================================================================================================");


        ResponseGetTax ret = restAPIService.getTax(requestGetTax);

        if (ret == null) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA)));
            return meta;
        } else {
            if (ret.isSuccess() == false) {
                meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.WRONG_POSTALCODE)));
                return meta;
            }
        }

        meta.setMessage("success");
        meta.setResult("success");
        meta.setSuccess(true);
        meta.setData(ret);

        session.setAttribute("tax", ret);

        return meta;
    }

    /**
     * Parameter
     * {
     * "paymentInformation": {
     * "card": {
     * "number": "4111111111111111",
     * "securityCode": "123",
     * "expirationMonth": "12",
     * "expirationYear": "2031"
     * }
     * },
     * "lineItems": [
     * {
     * "productCode": "09MM3CAN800469D",
     * "productName": "QN85Q90TAFXZA",
     * "productSku": "LD-DOP36L-PB6-L00",
     * "productDescription": "36",
     * "quantity": 1,
     * "unitPrice": "190.41",
     * "totalPrice": "210.41",
     * "taxAmount": "10.0",
     * "taxRate": "0.029"
     * }
     * ],
     * "orderInformation": {
     * "amountDetails": {
     * "totalAmount": "210.41",
     * "currency": "USD"
     * },
     * "billTo": {
     * "firstName": "John",
     * "lastName": "Doe",
     * "address1": "1 Market St",
     * "locality": "san francisco",
     * "administrativeArea": "CA",
     * "postalCode": "94105",
     * "country": "US",
     * "email": "yisangho717@gmail.com",
     * "phoneNumber": "4158880000"
     * }
     * }
     * }
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/payment", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody Meta payment(@RequestBody RequestPayment requestPayment,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        Meta meta = new Meta();
        meta.setMessage("success");
        meta.setSuccess(false);

        HttpSession session = request.getSession();
        if (session.getAttribute("ssoUser") == null) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA)));
            return meta;
        }
        if (session.getAttribute("product") == null) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA)));
            return meta;
        }
        if (session.getAttribute("tax") == null) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA)));
            return meta;
        }

        RequestSerialNoValidation consumerInfo = (RequestSerialNoValidation) request.getSession().getAttribute("ssoUser");
        String customerModelCode = consumerInfo.getModelCode().trim();
        String customerSerialNumber = consumerInfo.getSerialNo().trim();

        LOGGER.debug("==================================================================================================");
        LOGGER.debug("Serial number:" + customerSerialNumber);
        LOGGER.debug("Model Code:" + customerModelCode);
        LOGGER.debug("==================================================================================================");

        LOGGER.debug("==================================================================================================");
        ResponseGetTax retTax = (ResponseGetTax) request.getSession().getAttribute("tax");

        //validate totalPrice
        double totalPrice = retTax.getTotal();
        double paymentTotalPrice = Double.parseDouble(requestPayment.getOrderInformation().getAmountDetails().getTotalAmount());
        LOGGER.debug("totalPrice:" + totalPrice);
        LOGGER.debug("paymentTotalPrice:" + paymentTotalPrice);

        if ((totalPrice != paymentTotalPrice)) {
//			throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA));
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_DIFFERENT_TOTAL_PRICE)));
            return meta;
        }

        //validate tax
        double originPrice = retTax.getSubtotal();
        double totalTax = retTax.getTotalTax();
        double paymentTotalTax = Double.parseDouble(requestPayment.getLineItems().get(0).getTaxAmount());
        LOGGER.debug("originPrice:" + originPrice);
        LOGGER.debug("totalTax:" + totalTax);
        LOGGER.debug("paymentTotalTax:" + paymentTotalTax);

        if (totalTax != paymentTotalTax) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_DIFFERENT_TOTAL_TAX)));
            return meta;
        }

        //validate zip_code
        String originZipCode = retTax.getPostalCode();
        String paymentZipCode = requestPayment.getOrderInformation().getBillTo().getPostalCode();
        LOGGER.debug("originZipCode:" + originZipCode);
        LOGGER.debug("paymentZipCode:" + paymentZipCode);

        if (!originZipCode.equals(paymentZipCode)) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_DIFFERENT_ZIPCODE)));
            return meta;
        }

        LOGGER.debug("==================================================================================================");
        String modelCode = "";
        String price = "";
        String coverage = "";
        String sku = "";
        String technicianId = requestPayment.getOrderInformation().getTechnicianId();
        Double discountAmount = requestPayment.getOrderInformation().getDiscountAmount();
        boolean flag = false;
        List<ResponseSpcValidation> productInfo = (List<ResponseSpcValidation>) request.getSession().getAttribute("product");
        for (ResponseSpcValidation item : productInfo) {
            modelCode = item.getMODELCODE();
            price = item.getPRICE();
            coverage = item.getTERM();
            sku = item.getSKU();

            LOGGER.debug("price = " + price + ", originPrice = " + originPrice + ", technicianId = " + technicianId);

            if (originPrice == Double.parseDouble(price) || (StringUtils.isNotEmpty(technicianId) && originPrice + discountAmount == Double.parseDouble(price))) {
                LOGGER.debug("MODELCODE:" + modelCode);
                LOGGER.debug("PRICE:" + price);
                LOGGER.debug("coverage:" + coverage);
                LOGGER.debug("sku:" + sku);
                flag = true;
                break;
            }

        }
        LOGGER.debug("==================================================================================================");

        LOGGER.debug("==================================================================================================");
        if (flag == false) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_DIFFERENT_SKU_PRICE)));
            return meta;
        }

        //validate UnitPrice
        double unitPrice = Double.parseDouble(price);
        double paymentUnitPrice = Double.parseDouble(requestPayment.getLineItems().get(0).getUnitPrice());
        LOGGER.debug("unitPrice:" + unitPrice);
        LOGGER.debug("paymentUnitPrice:" + paymentUnitPrice);

        if (!(unitPrice == paymentUnitPrice || (StringUtils.isNotEmpty(technicianId) && unitPrice == paymentUnitPrice + discountAmount))) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_DIFFERENT_UNIT_PRICE)));
            return meta;
        }

        //validate model code
        LOGGER.debug("==================================================================================================");
        String originModelCode = customerModelCode;
        String paymentModelCode = requestPayment.getLineItems().get(0).getProductName();

        if (!originModelCode.equals(paymentModelCode)) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_DIFFERENT_MODEL_CODE)));
            return meta;
        }


        //validate sku
        LOGGER.debug("==================================================================================================");
        String originSku = sku;
        String paymentSku = requestPayment.getLineItems().get(0).getProductSku();

        if (!originSku.equals(paymentSku)) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_DIFFERENT_SKU)));
            return meta;
        }

        //validate coverage
        String originCoverage = coverage;
        String paymentCoverage = requestPayment.getLineItems().get(0).getProductDescription();
        if (!originCoverage.equals(paymentCoverage)) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_DIFFERENT_COVERAGE)));
            return meta;
        }
        LOGGER.debug("==================================================================================================");


        if (requestPayment.getLineItems().isEmpty()) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_PRODUCTDATA)));
            return meta;
        }

        if (requestPayment.getPaymentInformation().getCard().getNumber() == null || requestPayment.getPaymentInformation().getCard().getNumber().equals("")) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_CARDNUMBER)));
            return meta;
        }

        if (requestPayment.getPaymentInformation().getCard().getSecurityCode() == null || requestPayment.getPaymentInformation().getCard().getSecurityCode().equals("")) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_CVV)));
            return meta;
        }

        if (requestPayment.getPaymentInformation().getCard().getExpirationMonth() == null || requestPayment.getPaymentInformation().getCard().getExpirationMonth().equals("")) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_EXPIRATIONMONTH)));
            return meta;
        }

        if (requestPayment.getPaymentInformation().getCard().getExpirationYear() == null || requestPayment.getPaymentInformation().getCard().getExpirationYear().equals("")) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_EXPIRATIONYEAR)));
            return meta;
        }

        if (requestPayment.getOrderInformation().getAmountDetails().getTotalAmount() == null
                || requestPayment.getOrderInformation().getAmountDetails().getTotalAmount().equals("")
                || requestPayment.getOrderInformation().getAmountDetails().getTotalAmount().equals("0")) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_PRICE)));
            return meta;
        }

        if (requestPayment.getOrderInformation().getAmountDetails().getCurrency() == null || requestPayment.getOrderInformation().getAmountDetails().getCurrency().equals("")) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_CURRENCY)));
            return meta;
        }

        if (requestPayment.getOrderInformation().getBillTo().getAddress1() == null || requestPayment.getOrderInformation().getBillTo().getAddress1().equals("")) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_ADDRESS)));
            return meta;
        }

        if (requestPayment.getOrderInformation().getBillTo().getAdministrativeArea() == null || requestPayment.getOrderInformation().getBillTo().getAdministrativeArea().equals("")) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_STATE)));
            return meta;
        }

        if (requestPayment.getOrderInformation().getBillTo().getCountry() == null || requestPayment.getOrderInformation().getBillTo().getCountry().equals("")) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_COUNTRY)));
            return meta;
        }

        if (requestPayment.getOrderInformation().getBillTo().getEmail() == null || requestPayment.getOrderInformation().getBillTo().getEmail().equals("")) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_EMAIL)));
            return meta;
        }

        if (requestPayment.getOrderInformation().getBillTo().getFirstName() == null || requestPayment.getOrderInformation().getBillTo().getFirstName().equals("")) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_FIRSTNAME)));
            return meta;
        }

        if (requestPayment.getOrderInformation().getBillTo().getLastName() == null || requestPayment.getOrderInformation().getBillTo().getLastName().equals("")) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_LASTNAME)));
            return meta;
        }

        if (requestPayment.getOrderInformation().getBillTo().getLocality() == null || requestPayment.getOrderInformation().getBillTo().getLocality().equals("")) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_CITY)));
            return meta;
        }

        if (requestPayment.getOrderInformation().getBillTo().getPhoneNumber() == null || requestPayment.getOrderInformation().getBillTo().getPhoneNumber().equals("")) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_PHONENUMBER)));
            return meta;
        }

        String phoneNo = requestPayment.getOrderInformation().getBillTo().getPhoneNumber();
        if (phoneNo.length() != 10) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.SYNTAX_FIELD_SIZE, new Object[]{"Phone Number", "10"})));
            return meta;
        }

        if (requestPayment.getOrderInformation().getBillTo().getPostalCode() == null || requestPayment.getOrderInformation().getBillTo().getPostalCode().equals("")) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_POSTALCODE)));
            return meta;
        }

        ResponseContractCreate ret = restAPIService.payment(requestPayment);

        if (ret == null) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_PAYMENT_ERROR)));
            return meta;
        } else {

            if (ret.isSuccess() == false) {
                meta.setMessage(ret.getMsg());
                meta.setError_code(ret.getCode());
                meta.setSuccess(false);
                meta.setResult(ret.getMsg());

                String cybersource_error_message = "";
                if ("AVS_FAILED".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.AVS_FAILED));
                } else if ("CONTACT_PROCESSOR".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.CONTACT_PROCESSOR));
                } else if ("EXPIRED_CARD".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EXPIRED_CARD));
                } else if ("PROCESSOR_DECLINED".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.PROCESSOR_DECLINED));
                } else if ("INSUFFICIENT_FUND".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.INSUFFICIENT_FUND));
                } else if ("STOLEN_LOST_CARD".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.STOLEN_LOST_CARD));
                } else if ("ISSUER_UNAVAILABLE".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.ISSUER_UNAVAILABLE));
                } else if ("UNAUTHORIZED_CARD".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.UNAUTHORIZED_CARD));
                } else if ("CVN_NOT_MATCH".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.CVN_NOT_MATCH));
                } else if ("EXCEEDS_CREDIT_LIMIT".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EXCEEDS_CREDIT_LIMIT));
                } else if ("INVALID_CVN".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.INVALID_CVN));
                } else if ("DECLINED_CHECK".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.DECLINED_CHECK));
                } else if ("BLACKLISTED_CUSTOMER".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.BLACKLISTED_CUSTOMER));
                } else if ("SUSPENDED_ACCOUNT".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.SUSPENDED_ACCOUNT));
                } else if ("PAYMENT_REFUSED".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.PAYMENT_REFUSED));
                } else if ("CV_FAILED".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.CV_FAILED));
                } else if ("INVALID_ACCOUNT".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.INVALID_ACCOUNT));
                } else if ("GENERAL_DECLINE".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.GENERAL_DECLINE));
                } else if ("INVALID_MERCHANT_CONFIGURATION".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.INVALID_MERCHANT_CONFIGURATION));
                } else if ("DECISION_PROFILE_REJECT".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.DECISION_PROFILE_REJECT));
                } else if ("SCORE_EXCEEDS_THRESHOLD".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.SCORE_EXCEEDS_THRESHOLD));
                } else if ("PENDING_AUTHENTICATION".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.PENDING_AUTHENTICATION));
                } else if ("ACH_VERIFICATION_FAILED".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.ACH_VERIFICATION_FAILED));
                } else if ("DECISION_PROFILE_REVIEW".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.DECISION_PROFILE_REVIEW));
                } else if ("CONSUMER_AUTHENTICATION_REQUIRED".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.CONSUMER_AUTHENTICATION_REQUIRED));
                } else if ("CONSUMER_AUTHENTICATION_FAILED".equals(ret.getCode())) {
                    cybersource_error_message = Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Warn.CONSUMER_AUTHENTICATION_FAILED));
                } else {
                    cybersource_error_message = ret.getMsg();
                }

                meta.setMessage(Util.getMessage(cybersource_error_message));
                return meta;
            }
        }

        meta.setMessage("success");
        meta.setSuccess(true);
        meta.setData(ret);

        return meta;
    }

    /**
     * Parameter
     * <p>
     * request
     * {
     * "cancelDate": "2019-11-21",
     * "cancelReason": "cancelReason",
     * "contractNo": 600009999,
     * "email": "michael.j@jackson5.com",
     * "firstName": "Jackson",
     * "lastName": "Michael",
     * }
     * <p>
     * response
     * {
     * "code": 0,
     * "msg": "string",
     * "success": true
     * }
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody Meta cancel(@RequestBody RequestContractCancel requestCancel,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        Meta meta = new Meta();
        meta.setResult("SUCCESS");

//		if ( requestCancel.getCancelDate() == null || requestCancel.getCancelDate().equals("")) {
//			throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_CANCELDATE));
//		}

        if (requestCancel.getContractNo() == null || requestCancel.getContractNo().equals("")) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_CONTRACTNO));
        }

        if (requestCancel.getCancelReason() == null || requestCancel.getCancelReason().equals("")) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_CANCELREASON));
        }

        String contract_no = "";

        HttpSession session = request.getSession();
        if (session.getAttribute("contract_no") == null) {
            meta.setMessage(Util.getMessage(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA)));
            return meta;
        }
        contract_no = (String) session.getAttribute("contract_no");

        ResponseContractCancel ret = restAPIService.cancel(requestCancel);

        if (ret == null) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA));

        } else {
            if (ret.isSuccess() == false) {
                meta.setMessage(ret.getMsg());
                meta.setError_code(ret.getError());
                meta.setSuccess(false);
                meta.setResult(ret.getStatus());

                return meta;
            }
        }

        meta.setMessage("success");
        meta.setSuccess(true);
        meta.setData(ret);

        return meta;
    }

    /**
     * {
     * "email": "Francesca10@gmail.com",
     * "policyNo": "675172616",
     * "serialNo": "694095854396915"
     * }
     * <p>
     * {
     * "email": "yisangho717@gmail.com",
     * "policyNo": "683845457",
     * "serialNo": "09MM3CAN800469D"
     * }
     *
     * @throws IOException
     */
    @RequestMapping(value = "/getContractInfo", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody Meta getContractInfo(@RequestBody RequestContractStatus requestContractStatus,
                                              HttpServletRequest request,
                                              HttpServletResponse response) throws Exception {

        Meta meta = new Meta();
        meta.setResult("SUCCESS");

        if (requestContractStatus.getEmail() == null || requestContractStatus.getEmail().equals("")) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_EMAIL));
        }

        if (requestContractStatus.getPolicyNo() == null && requestContractStatus.getSerialNo() == null) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA));
        }

        if (requestContractStatus.getPolicyNo() != null && requestContractStatus.getSerialNo() != null) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA));
        }


        ResponseContractStatus ret = restAPIService.getContractInfo(requestContractStatus);

        if (ret == null) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA));

        } else {
            if (ret.isSuccess() == false) {
                meta.setMessage(ret.getMsg());
                meta.setError_code(ret.getCode());
                meta.setSuccess(false);
                meta.setResult(ret.getMsg());

                return meta;
            }
        }

        meta.setMessage("success");
        meta.setSuccess(true);
        meta.setData(ret);

        String contract_no = ret.getContractStatusInfoResponse().getContractNo();
        LOGGER.debug("===============================/getContractInfo=====================================================");
        LOGGER.debug("contract_no : [" + contract_no + "]");

        String serialNo = ret.getContractStatusInfoResponse().getSerialNo();
        String email = ret.getContractStatusInfoResponse().getEmail();
        String firstNm = ret.getContractStatusInfoResponse().getFirstName();
        String lastNm = ret.getContractStatusInfoResponse().getLastName();
        LOGGER.debug("serialNo : [" + serialNo + "]");

        serialNo = Util.strToMask(serialNo);
        email = Util.strToMask(email);
        firstNm = Util.strToMask(firstNm);
        lastNm = Util.strToMask(lastNm);

        LOGGER.debug("serialNo : [" + serialNo + "]");
        ret.getContractStatusInfoResponse().setSerialNo(serialNo);
        ret.getContractStatusInfoResponse().setEmail(email);
        ret.getContractStatusInfoResponse().setFirstName(firstNm);
        ret.getContractStatusInfoResponse().setLastName(lastNm);

        serialNo = ret.getContractStatusInfoResponse().getSerialNo();
        LOGGER.debug("serialNo : [" + serialNo + "]");
        LOGGER.debug("===============================/getContractInfo=====================================================");

        HttpSession session = request.getSession();
        session.setAttribute("contract_no", contract_no);

        return meta;
    }

    /**
     * Parameter
     * <p>
     * request
     * {
     * "cancelDate": "2019-11-21",
     * "cancelReason": "cancelReason",
     * "contractNo": 600009999,
     * "email": "michael.j@jackson5.com",
     * "firstName": "Jackson",
     * "lastName": "Michael",
     * }
     * <p>
     * response
     * {
     * "code": 0,
     * "msg": "string",
     * "success": true
     * }
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cancelRequest", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody Meta cancelRequest(@RequestBody RequestContractCancel requestCancel,
                                            HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {

        Meta meta = new Meta();
        meta.setResult("SUCCESS");

        if (requestCancel.getCancelDate() == null || requestCancel.getCancelDate().equals("")) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_CANCELDATE));
        }

        if (requestCancel.getContractNo() == null || requestCancel.getContractNo().equals("")) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_CONTRACTNO));
        }

        if (requestCancel.getCancelReason() == null || requestCancel.getCancelReason().equals("")) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_CANCELREASON));
        }

        if (requestCancel.getCancelReason() == null || requestCancel.getCancelReason().equals("")) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_FIRSTNAME));
        }

        if (requestCancel.getCancelReason() == null || requestCancel.getCancelReason().equals("")) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_LASTNAME));
        }

        if (requestCancel.getCancelReason() == null || requestCancel.getCancelReason().equals("")) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.EMPTY_EMAIL));
        }

        ResponseContractCancel ret = restAPIService.cancelRequest(requestCancel);

        if (ret == null) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Error.INVALID_INPUT_DATA));

        } else {
            if (ret.isSuccess() == false) {
                meta.setMessage(ret.getMsg());
                meta.setError_code(ret.getError());
                meta.setSuccess(false);
                meta.setResult(ret.getStatus());

                return meta;
            }
        }

        meta.setMessage("success");
        meta.setSuccess(true);
        meta.setData(ret);

        return meta;
    }

    /**
     * Parameter
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveFrontError", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody Meta saveFrontError(@RequestBody SaveFrontError saveFrontError,
                                             HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {

        Meta meta = new Meta();
        meta.setResult("SUCCESS");

        if (saveFrontError.getPageUrl() == null || saveFrontError.getPageUrl().equals("")) {
            throw new NormalRequestException(messageSourceAccessor.getMessage(CommonValue.Message.Warn.NO_PAGEURL));
        }

        restCommonMapper.insertFrontError(saveFrontError);
        meta.setMessage("success");
        meta.setSuccess(true);

        return meta;
    }
}
