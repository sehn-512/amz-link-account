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
public class RequestGetTax implements Serializable {
	
	public RequestGetTax() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String postalCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String country;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<RequestGetTaxLineItem> lineItem;

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

	public List<RequestGetTaxLineItem> getLineItem() {
		return lineItem;
	}

	public void setLineItem(List<RequestGetTaxLineItem> lineItem) {
		this.lineItem = lineItem;
	}

	
	
	
}
