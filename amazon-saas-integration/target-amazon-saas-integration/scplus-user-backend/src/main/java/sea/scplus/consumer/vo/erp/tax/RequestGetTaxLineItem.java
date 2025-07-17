package sea.scplus.consumer.vo.erp.tax;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
{
  "postalCode": "81635",
  "country": "US",
  "lineItem": [
  	  {
		  "modelCode": "UN82RU8000FXZA",
		  "serialNo": "08793CAM901748H",
		  "quantity": 1,
		  "price": 100.00
	  }
	]
}
 *
 */
public class RequestGetTaxLineItem implements Serializable {
	
	public RequestGetTaxLineItem() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String modelCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String serialNo;
	
	@JsonInclude(value = Include.NON_NULL)
	private String sku;
	
	@JsonInclude(value = Include.NON_NULL)
	private int quantity;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double price;

	@JsonInclude(value = Include.NON_NULL)
	private String technicianId;

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getTechnicianId() {
		return technicianId;
	}

	public void setTechnicianId(String technicianId) {
		this.technicianId = technicianId;
	}
}
