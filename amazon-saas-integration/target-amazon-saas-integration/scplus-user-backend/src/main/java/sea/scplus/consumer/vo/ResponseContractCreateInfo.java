package sea.scplus.consumer.vo;

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
  "code": 0,
  "contractInfo": {
    "contractNo": "string",
    "orderIdentifier": "string",
    "orderItemIdentifier": "string",
    "orderItemPartnerIdentifier": "string",
    "orderPartnerIdentifier": "string"
  },
  "msg": "string",
  "success": true
}
 *
 */
public class ResponseContractCreateInfo implements Serializable {
	
	public ResponseContractCreateInfo() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String contractNo;
	
	@JsonInclude(value = Include.NON_NULL)
	private String orderIdentifier;
	
	@JsonInclude(value = Include.NON_NULL)
	private String orderItemIdentifier;
	
	@JsonInclude(value = Include.NON_NULL)
	private String orderItemPartnerIdentifier;
	
	@JsonInclude(value = Include.NON_NULL)
	private String orderPartnerIdentifier;

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getOrderIdentifier() {
		return orderIdentifier;
	}

	public void setOrderIdentifier(String orderIdentifier) {
		this.orderIdentifier = orderIdentifier;
	}

	public String getOrderItemIdentifier() {
		return orderItemIdentifier;
	}

	public void setOrderItemIdentifier(String orderItemIdentifier) {
		this.orderItemIdentifier = orderItemIdentifier;
	}

	public String getOrderItemPartnerIdentifier() {
		return orderItemPartnerIdentifier;
	}

	public void setOrderItemPartnerIdentifier(String orderItemPartnerIdentifier) {
		this.orderItemPartnerIdentifier = orderItemPartnerIdentifier;
	}

	public String getOrderPartnerIdentifier() {
		return orderPartnerIdentifier;
	}

	public void setOrderPartnerIdentifier(String orderPartnerIdentifier) {
		this.orderPartnerIdentifier = orderPartnerIdentifier;
	}
	
	
	

}
