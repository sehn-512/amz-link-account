package sea.scplus.consumer.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class RequestDoNotSellData implements Serializable {
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String origin_system;
	
	@JsonInclude(value = Include.NON_NULL)
	private String status;

	public String getOrigin_system() {
		return origin_system;
	}

	public void setOrigin_system(String origin_system) {
		this.origin_system = origin_system;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
