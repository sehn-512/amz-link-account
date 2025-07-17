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
  "subtotal": 100.00,
  "total": 102.9,
  "totalTax": 2.9,
  "lineItem": [
  	  {
		  "modelCode": "UN82RU8000FXZA",
		  "serialNo": "08793CAM901748H",
		  "quantity": 1,
		  "price": 100.00,
		  "totalTax": 2.9,
	  }
	]
}
 *
 */
public class ResponseGetTaxLineItem implements Serializable {
	
	public ResponseGetTaxLineItem() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String modelCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String serialNo;
	
	@JsonInclude(value = Include.NON_NULL)
	private int quantity;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double price;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double totalTax;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double taxRate;

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

	public Double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Double totalTax) {
		this.totalTax = totalTax;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	
	

	
}
