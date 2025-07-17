package sea.scplus.consumer.service.impl;

import java.io.IOException;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sea.scplus.consumer.common.mail.SMTPMailManager;
import sea.scplus.consumer.common.mail.SMTPMailMessage;
import sea.scplus.consumer.common.mail.SMTPMailTemplate;
import sea.scplus.consumer.persistence.api.FrontCommonMapper;
import sea.scplus.consumer.service.FrontCommonService;
import sea.scplus.consumer.vo.ConsumerContactHistory;
import sea.scplus.consumer.vo.RequestContractCreate;
import sea.scplus.consumer.vo.order.CreateOrder;
import sea.scplus.consumer.vo.payment.RequestPayment;

@Service
public class FrontCommonServiceImpl implements FrontCommonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FrontCommonServiceImpl.class);

    @Value("${spring.profiles.active}")
	private String server_mode;
    
    @Value("${email.image.url}")
	private String EMAIL_IMAGE_URL;
    
    @Value("${spring.mail.properties.mail.smtp.from}")
	private String EMAIL_FROM;
    
    @Value("${email.error.to}")
	private String EMAIL_ERROR_TO;
    
    @Value("${domain.name}")
	private String SITE_URL;
    
    @Value("${domain.protocol}")
	private String SITE_PROTOCOL;
    
    @Autowired
	private FrontCommonMapper frontCommonMapper;
    
    @Autowired
	private SMTPMailManager mailManagerSMTP;
    
	@Override
	public void sendContractConfirm(RequestPayment consumerRequests, RequestContractCreate contract, ConsumerContactHistory consumerContactHistory) throws IOException {
		
//		TicketFile ticketFile = selectPDFRequestSummary(consumerRequests.getCpa_ticket_id(), consumerRequests.getStatus());
//		LOGGER.debug("ticketFile : " + ticketFile);
		String pdfFilePath = "";
//		pdfFilePath = createPdfToDisk(ticketFile);
		
		String consumerEmailAddress = consumerRequests.getOrderInformation().getBillTo().getEmail();
        String orderID = contract.getOrderIdentifier();
        String contractNo = contract.getContractNo();
        String productName = consumerRequests.getLineItems().get(0).getProductName();
        String modelCode = contract.getModel();
        String serialNumber = contract.getSerialNumber();
        String cardNumber = consumerRequests.getPaymentInformation().getCard().getNumber();
        String productAmount = consumerRequests.getLineItems().get(0).getUnitPrice();
        String taxAmount = consumerRequests.getLineItems().get(0).getTaxAmount();
        String totalAmount = consumerRequests.getOrderInformation().getAmountDetails().getTotalAmount();
        String subject = "Samsung Care+ Order Confirmation for CE POS - "+orderID;
        
		// send PDF
		SMTPMailMessage mailMessage = new SMTPMailMessage();
		mailMessage.setFrom(EMAIL_FROM);
		mailMessage.setTo(consumerEmailAddress);
		mailMessage.setSubject(subject);
		
		Context tempModel = new VelocityContext();
		
		tempModel.put("title", "Samsung Care+ Order Confirmation for CE POS");
		tempModel.put("customerName", consumerRequests.getOrderInformation().getBillTo().getFirstName() + " " + consumerRequests.getOrderInformation().getBillTo().getLastName());
		tempModel.put("orderId", orderID);
		tempModel.put("contractNo", contractNo);
		tempModel.put("modelCode", modelCode);
		tempModel.put("serialNumber", serialNumber);
		tempModel.put("domainAddress", SITE_PROTOCOL + "://" + SITE_URL);
		tempModel.put("product", productName);
		tempModel.put("currency", consumerRequests.getOrderInformation().getAmountDetails().getCurrency());
		tempModel.put("productAmount", productAmount);
		tempModel.put("paymentMethod", cardNumber.substring(cardNumber.length()-4));
		tempModel.put("taxAmount", taxAmount);
		tempModel.put("amountPaid", totalAmount);
		
		tempModel.put("addressIco", EMAIL_IMAGE_URL+"addressIco.png");
		
		// Get a pdf filename 
        /**** Get The Absolute Path Of The File ****/
//		LOGGER.debug("Downloading PDF File From The Server ....!");
//      LOGGER.debug("pdfFilePath .... : " + pdfFilePath);
		String contents = mailManagerSMTP.mergeMailMessage(SMTPMailTemplate.ORDER_CONFIRM, mailMessage, tempModel, pdfFilePath);
//        consumerContactHistory.setFile_id(ticketFile.getFile_id());
        consumerContactHistory.setResponse_type("EMAIL");
        consumerContactHistory.setContent(contents);
        consumerContactHistory.setFile_id(pdfFilePath);
        consumerContactHistory.setSubject(subject);
		if ( "local".equals(server_mode)) {
			frontCommonMapper.insertConsumerContactHistoryWithoutDKMS(consumerContactHistory);
		} else {
			frontCommonMapper.insertConsumerContactHistory(consumerContactHistory);
		}
		mailManagerSMTP.send(mailMessage, contents, pdfFilePath); // frenchify

	}

	@Override
	public void sendContractCancel(CreateOrder cancelOrder, ConsumerContactHistory consumerContactHistory) throws IOException {
		
		String pdfFilePath = "";
		
		String consumerEmailAddress = consumerContactHistory.getEmail();
        String orderID = cancelOrder.getOrder_id();
        Double totalAmount = cancelOrder.getTotal_price();
        String subject = "Samsung Care+ Order Cancel for CE POS - "+orderID;
        
		SMTPMailMessage mailMessage = new SMTPMailMessage();
		mailMessage.setFrom(EMAIL_FROM);
		mailMessage.setTo(consumerEmailAddress);
		mailMessage.setSubject(subject);
		
		Context tempModel = new VelocityContext();

		tempModel.put("title", "Cancel order for CE POS");
		tempModel.put("customerName", cancelOrder.getFirst_name() + " " + cancelOrder.getLast_name());
		tempModel.put("domainAddress", SITE_PROTOCOL + "://" + SITE_URL);
		tempModel.put("product", cancelOrder.getOrderItem().get(0).getSku());
		tempModel.put("currency", "USD");
		tempModel.put("amountRefunded", totalAmount);
		
		String contents = mailManagerSMTP.mergeMailMessage(SMTPMailTemplate.ORDER_CANCEL, mailMessage, tempModel, pdfFilePath);
        consumerContactHistory.setResponse_type("EMAIL");
        consumerContactHistory.setContent(contents);
        consumerContactHistory.setFile_id(pdfFilePath);
        consumerContactHistory.setSubject(subject);
        
        frontCommonMapper.insertConsumerContactHistory(consumerContactHistory);
        
		mailManagerSMTP.send(mailMessage, contents, pdfFilePath);
		
	}

	@Override
	public void sendErrorContractProcess(String orderItemId, String referenceNo, String errorDate )
			throws IOException {

		String pdfFilePath = "";
		
		String[] email = EMAIL_ERROR_TO.split(",");
        String subject = "[" + server_mode + "] Orders with missing referenced contract" ;

        SMTPMailMessage mailMessage = new SMTPMailMessage();
		mailMessage.setFrom(EMAIL_FROM);
		mailMessage.setTo(email);
		mailMessage.setSubject(subject);
		
		Context tempModel = new VelocityContext();

		tempModel.put("title", "Error during order processing for CE POS");
		tempModel.put("OrderItemId", orderItemId);
		tempModel.put("referenceNo", referenceNo);
		tempModel.put("errorDate", errorDate);
		
		String contents = mailManagerSMTP.mergeMailMessage(SMTPMailTemplate.ORDER_ERROR, mailMessage, tempModel, pdfFilePath);
        
		mailManagerSMTP.send(mailMessage, contents, pdfFilePath);
		
	}
	
	public ConsumerContactHistory selectContactHistory(String contract_no) {
		ConsumerContactHistory consumerContactHistory = frontCommonMapper.selectContactHistory(contract_no);
		return consumerContactHistory;
	}
	
}
