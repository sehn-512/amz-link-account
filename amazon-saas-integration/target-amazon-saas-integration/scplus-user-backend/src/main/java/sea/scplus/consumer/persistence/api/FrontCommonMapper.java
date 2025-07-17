package sea.scplus.consumer.persistence.api;

import org.apache.ibatis.annotations.Mapper;

import sea.scplus.consumer.vo.ConsumerContactHistory;

@Mapper
public interface FrontCommonMapper {

	public int insertConsumerContactHistory(ConsumerContactHistory consumerContactHistory);
	public int insertConsumerContactHistoryWithoutDKMS(ConsumerContactHistory consumerContactHistory);

	public ConsumerContactHistory selectContactHistory(String contract_no);
}
