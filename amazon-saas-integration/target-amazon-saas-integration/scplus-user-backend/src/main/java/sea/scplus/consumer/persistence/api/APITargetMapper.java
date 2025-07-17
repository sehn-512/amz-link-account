/**
 * Copyright (c) 2020 Samsung SDSA. All Rights Reserved.
 */
package sea.scplus.consumer.persistence.api;

import org.apache.ibatis.annotations.Mapper;

import sea.scplus.consumer.vo.RequestTicket;

@Mapper
public interface APITargetMapper
{

	int selectVerifyAPITarget(String systemid, String password);
	
}
