package sea.scplus.consumer.service;

import java.io.IOException;
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
import sea.scplus.consumer.vo.payment.RequestPayment;
import sea.scplus.consumer.vo.payment.ResponsePayment;

@Service
public interface RESTCyberSourceService{

	public ResponsePayment payment(RequestPayment requestPayment);

	public ResponseCancel cancel(RequestCancel requestCancel);
	
}