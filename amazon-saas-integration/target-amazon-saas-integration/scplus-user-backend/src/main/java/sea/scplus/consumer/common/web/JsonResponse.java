/**
 * Copyright (c) 2016 Samsung SDSA. All Rights Reserved.
 */
package sea.scplus.consumer.common.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.Serializable;

public class JsonResponse implements WebResponse 
{
	private static final long serialVersionUID = -5335355802591932596L;

	private Object meta;
	private Object data;
	
	/**
	 * Default Constructor overloading
	 */
	public JsonResponse() {}
	
	/**
	 * Data Constructor overloading
	 * @param meta
	 */
	public JsonResponse(Object meta, Object data) {
		this.meta = meta;
		this.data = data;
	}

	public JsonResponse(Object meta) {
		this.meta = meta;
	}

	public Object getMeta() {
		return meta;
	}

	public void setMeta(Object meta) {
		this.meta = meta;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JsonResponse [meta=" + meta + ", data=" + toString(data) + "]";
	}

	private String toString(Object object){
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		try {
			return mapper.writeValueAsString(object);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
