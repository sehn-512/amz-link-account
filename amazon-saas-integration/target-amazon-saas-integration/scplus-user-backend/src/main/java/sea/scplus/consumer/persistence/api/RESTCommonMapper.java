package sea.scplus.consumer.persistence.api;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sea.scplus.consumer.vo.AttributeInfo;
import sea.scplus.consumer.vo.ConsumerContactHistory;
import sea.scplus.consumer.vo.RequestContractCreate;
import sea.scplus.consumer.vo.SaveFrontError;
import sea.scplus.consumer.vo.cancel.RequestCancel;
import sea.scplus.consumer.vo.order.CreateOrder;
import sea.scplus.consumer.vo.payment.RequestPayment;

@Mapper
public interface RESTCommonMapper {


	public String getReferenceId();
	
	public String getOrderId();
	
	public String getEmailHookId();

	public void insertCreateOrder(CreateOrder createOrder);

	public void insertCreateOrderWithoutDKMS(CreateOrder createOrder);

	public CreateOrder getOrderInfo(String contractNo);

	public void insertCancelOrder(CreateOrder createOrder);
	
	public CreateOrder getCancelOrderInfo(String contractNo);

	public void insertCreateContractInfo(RequestContractCreate requestContractCreate);
	public void insertCreateContractInfoWithoutDKMS(RequestContractCreate requestContractCreate);

	public void insertPaymentInfo(RequestPayment requestPayment);
	public void insertPaymentInfoWithoutDKMS(RequestPayment requestPayment);

	public void insertFrontError(SaveFrontError saveFrontError);

	public void updateCreateOrder(CreateOrder createOrder);

	public int getOrderInfoBySerialNo(String serialNo);

	public RequestPayment getPaymentInfo(String orderId);

}
