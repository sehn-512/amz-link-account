/**
 * Copyright (c) 2016 Samsung SDSA. All Rights Reserved.
 */
package sea.scplus.consumer.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;

import sea.scplus.consumer.vo.ConsumerContactHistory;
import sea.scplus.consumer.vo.RequestContractCreate;
import sea.scplus.consumer.vo.order.CreateOrder;
import sea.scplus.consumer.vo.payment.RequestPayment;

public interface FrontCommonService {
    
	public void sendContractConfirm(RequestPayment consumerRequests, RequestContractCreate contract, ConsumerContactHistory consumerContactHistory) throws IOException;

	public void sendContractCancel(CreateOrder createOrder, ConsumerContactHistory consumerContactHistory) throws IOException ;
	
	public void sendErrorContractProcess(String orderItemId, String referenceNo, String errorDate) throws IOException ;
	
	public ConsumerContactHistory selectContactHistory(String contract_no);
	
}
