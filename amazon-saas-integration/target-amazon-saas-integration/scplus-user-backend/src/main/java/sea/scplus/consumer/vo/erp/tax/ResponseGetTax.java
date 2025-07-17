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
  "taxRate": 0.29
  "lineItem": [
  	  {
		  "modelCode": "UN82RU8000FXZA",
		  "serialNo": "08793CAM901748H",
		  "quantity": 1,
		  "price": 100.00,
		  "totalTax": 2.9,
		  "taxRate": 0.29
	  }
	]
}
 *
 */
public class ResponseGetTax implements Serializable {
	
	public ResponseGetTax() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private boolean success;
	
	@JsonInclude(value = Include.NON_NULL)
	private String postalCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String country;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double subtotal;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double total;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double totalTax;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double taxRate;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<ResponseGetTaxLineItem> lineItem;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Double totalTax) {
		this.totalTax = totalTax;
	}

	public List<ResponseGetTaxLineItem> getLineItem() {
		return lineItem;
	}

	public void setLineItem(List<ResponseGetTaxLineItem> lineItem) {
		this.lineItem = lineItem;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	
	
}
