package sea.scplus.consumer.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class RequestDoNotSell implements Serializable {
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String ticket_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String target_system;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<RequestDoNotSellData> data;

	public String getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(String ticket_id) {
		this.ticket_id = ticket_id;
	}

	public String getTarget_system() {
		return target_system;
	}

	public void setTarget_system(String target_system) {
		this.target_system = target_system;
	}

	public List<RequestDoNotSellData> getData() {
		return data;
	}

	public void setData(List<RequestDoNotSellData> data) {
		this.data = data;
	}
	
}
