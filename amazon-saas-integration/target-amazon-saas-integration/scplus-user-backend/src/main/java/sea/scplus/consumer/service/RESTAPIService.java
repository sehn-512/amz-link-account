package sea.scplus.consumer.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import sea.scplus.consumer.vo.*;
import sea.scplus.consumer.vo.cancel.RequestCancel;
import sea.scplus.consumer.vo.cancel.ResponseCancel;
import sea.scplus.consumer.vo.erp.tax.RequestGetTax;
import sea.scplus.consumer.vo.erp.tax.ResponseGetTax;
import sea.scplus.consumer.vo.payment.RequestPayment;
import sea.scplus.consumer.vo.payment.ResponsePayment;

@Service
public interface RESTAPIService{
	public String getTokenFromSCPUS(String username, String password) throws IOException;

	public String getTokenFromSCPUSByUserID(String username, String password) throws IOException;

	public List<ResponseSpcValidation> checkSerialNoValidation(RequestSerialNoValidation requestSerialNoValidation) throws IOException, ParseException ;

	public ResponseGetTicketInfo getTicketInfo(String ticketNo) throws IOException, ParseException ;

	public ResponseGetTax getTax(RequestGetTax requestGetTax);

	public ResponseContractCreate payment(RequestPayment requestPayment) throws IOException, ParseException ;

	public ResponseContractStatus getContractInfo(RequestContractStatus requestContractStatus) throws IOException, ParseException;

	public ResponseContractCancel cancel(RequestContractCancel requestContractCancel) throws IOException, ParseException;

	public ResponseContractCancel cancelRequest(RequestContractCancel requestCancel) throws ParseException, IOException;
}