package sea.scplus.consumer.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class AttributeInfo implements Serializable {
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String attribute_name;
	
	@JsonInclude(value = Include.NON_NULL)
	private String attribute_desc;
	
	@JsonInclude(value = Include.NON_NULL)
	private String interface_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String attribute_required;
	
	@JsonInclude(value = Include.NON_NULL)
	private String attribute_type;
	
	@JsonInclude(value = Include.NON_NULL)
	private int attribute_size;

	@JsonInclude(value = Include.NON_NULL)
	private List<AttributeSub> attributeSub;
	
	public String getAttribute_name() {
		return attribute_name;
	}

	public void setAttribute_name(String attribute_name) {
		this.attribute_name = attribute_name;
	}

	public String getAttribute_desc() {
		return attribute_desc;
	}

	public void setAttribute_desc(String attribute_desc) {
		this.attribute_desc = attribute_desc;
	}

	public String getInterface_id() {
		return interface_id;
	}

	public void setInterface_id(String interface_id) {
		this.interface_id = interface_id;
	}

	public String getAttribute_required() {
		return attribute_required;
	}

	public void setAttribute_required(String attribute_required) {
		this.attribute_required = attribute_required;
	}

	public String getAttribute_type() {
		return attribute_type;
	}

	public void setAttribute_type(String attribute_type) {
		this.attribute_type = attribute_type;
	}

	public int getAttribute_size() {
		return attribute_size;
	}

	public void setAttribute_size(int attribute_size) {
		this.attribute_size = attribute_size;
	}

	public List<AttributeSub> getAttributeSub() {
		return attributeSub;
	}

	public void setAttributeSub(List<AttributeSub> attributeSub) {
		this.attributeSub = attributeSub;
	}

	
	
}
