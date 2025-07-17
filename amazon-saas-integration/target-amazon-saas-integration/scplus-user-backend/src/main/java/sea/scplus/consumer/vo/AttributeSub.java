package sea.scplus.consumer.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class AttributeSub implements Serializable {
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String sub_key;
	
	@JsonInclude(value = Include.NON_NULL)
	private String sub_attribute_name;
	
	@JsonInclude(value = Include.NON_NULL)
	private String sub_attribute_desc;
	
	@JsonInclude(value = Include.NON_NULL)
	private String interface_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String sub_attribute_required;
	
	@JsonInclude(value = Include.NON_NULL)
	private String sub_attribute_type;
	
	@JsonInclude(value = Include.NON_NULL)
	private int sub_attribute_size;

	public String getSub_key() {
		return sub_key;
	}

	public void setSub_key(String sub_key) {
		this.sub_key = sub_key;
	}

	public String getSub_attribute_name() {
		return sub_attribute_name;
	}

	public void setSub_attribute_name(String sub_attribute_name) {
		this.sub_attribute_name = sub_attribute_name;
	}

	public String getSub_attribute_desc() {
		return sub_attribute_desc;
	}

	public void setSub_attribute_desc(String sub_attribute_desc) {
		this.sub_attribute_desc = sub_attribute_desc;
	}

	public String getInterface_id() {
		return interface_id;
	}

	public void setInterface_id(String interface_id) {
		this.interface_id = interface_id;
	}

	public String getSub_attribute_required() {
		return sub_attribute_required;
	}

	public void setSub_attribute_required(String sub_attribute_required) {
		this.sub_attribute_required = sub_attribute_required;
	}

	public String getSub_attribute_type() {
		return sub_attribute_type;
	}

	public void setSub_attribute_type(String sub_attribute_type) {
		this.sub_attribute_type = sub_attribute_type;
	}

	public int getSub_attribute_size() {
		return sub_attribute_size;
	}

	public void setSub_attribute_size(int sub_attribute_size) {
		this.sub_attribute_size = sub_attribute_size;
	}

	
	
}
