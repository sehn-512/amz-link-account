package sea.scplus.consumer.vo;

import java.io.Serializable;
import java.util.List;

public class ResponseObject implements Serializable{
	
	private static final long serialVersionUID = -4754502940820722424L;
	private List<ResponseTicket> data;
	private Meta meta;
	
	public List<ResponseTicket> getData() {
		return data;
	}
	public void setData(List<ResponseTicket> ticket_list) {
		this.data = ticket_list;
	}
	public Meta getMeta() {
		return meta;
	}
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	@Override
	public String toString() {
		return "ResponseObject [data=" + data + ", meta=" + meta + "]";
	}

}
