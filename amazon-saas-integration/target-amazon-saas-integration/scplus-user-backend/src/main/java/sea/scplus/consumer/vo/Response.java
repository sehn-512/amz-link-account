package sea.scplus.consumer.vo;

import java.io.Serializable;

public class Response implements Serializable{
	private static final long serialVersionUID = -4754502940820722424L;
	
	private Meta meta;
	private ResponseMyDataCategories data;
	public Meta getMeta() {
		return meta;
	}
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	public ResponseMyDataCategories getData() {
		return data;
	}
	public void setData(ResponseMyDataCategories data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Response [meta=" + meta + ", data=" + data + "]";
	}

}
