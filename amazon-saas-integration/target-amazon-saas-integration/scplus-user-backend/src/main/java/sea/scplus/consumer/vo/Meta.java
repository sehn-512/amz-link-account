/**
 * Copyright (c) 2016 Samsung SDSA. All Rights Reserved.
 */
package sea.scplus.consumer.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Meta implements Serializable 
{
	
	private static final long serialVersionUID = -7290111095544086823L;

	@JsonInclude(value = Include.NON_NULL)
	private String result;
	
	@JsonInclude(value = Include.NON_NULL)
	private String error_code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String error_message;
	
	@JsonInclude(value = Include.NON_NULL)
	private Object success;
	
	@JsonInclude(value = Include.NON_NULL)
	private String message;
	
	@JsonInclude(value = Include.NON_NULL)
	private String error_type;
	
	@JsonInclude(value = Include.NON_NULL)
	private Object data;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	public Object getSuccess() {
		return success;
	}

	public void setSuccess(Object success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError_type() {
		return error_type;
	}

	public void setError_type(String error_type) {
		this.error_type = error_type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
