package sea.scplus.consumer.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

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
import sea.scplus.consumer.vo.erp.tax.RequestGetTax;
import sea.scplus.consumer.vo.erp.tax.ResponseGetTax;
import sea.scplus.consumer.vo.payment.RequestPayment;
import sea.scplus.consumer.vo.payment.ResponsePayment;

@Service
public interface RESTCommonService{
	public String getTokenFromSCPUS(String username, String password) throws IOException;

	public ResponseSerialNoValidation gspnCheckSerialNoValidation(RequestSerialNoValidation requestSerialNoValidation);
	
	public ResponseContractStatus contractStatus(RequestContractStatus requestContractStatus);

	public List<ResponseSpcValidation> getSpcValidation(RequestSpcValidation requestSpcValidation);

	public String getTokenFromSCPUSByUserID(String username, String password) throws IOException;

	public ResponseContractCancel contractCancel(RequestContractCancel requestContractCancel);

	public ResponseContractCreate contractCreate(RequestContractCreate requestContractCreate);

	public List<ResponseSpcValidation> checkSerialNoValidation(RequestSerialNoValidation requestSerialNoValidation) throws IOException ;

	public ResponseGetTax getTax(RequestGetTax requestGetTax);

	public ResponseContractCreate payment(RequestPayment requestPayment) throws IOException, ParseException ;

	public ResponseContractStatus getContractInfo(RequestContractStatus requestContractStatus) throws IOException;

	public ResponseContractCancel cancel(RequestContractCancel requestContractCancel) throws IOException, ParseException;
}