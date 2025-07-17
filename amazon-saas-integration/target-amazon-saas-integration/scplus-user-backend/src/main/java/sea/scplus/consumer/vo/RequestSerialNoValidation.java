package sea.scplus.consumer.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;

/**
 * 
 * {
  "token":"",
  "accountIdentifier": "",
  "company": "C310",
  "modelCode": "SM-N960UZBAATT",
  "serialNo": 358620092314569,
  "ticketNo": "41700122334", (Optional)
}
 *
 */

public class RequestSerialNoValidation implements Serializable {
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private boolean recaptcha;
	
	@JsonInclude(value = Include.NON_NULL)
	private String accountIdentifier;
	
	@JsonInclude(value = Include.NON_NULL)
	private String company;
	
	@JsonInclude(value = Include.NON_NULL)
	private String modelCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String serialNo;
	
	@JsonInclude(value = Include.NON_NULL)
	private String agentId;
	
	@JsonInclude(value = Include.NON_NULL)
	private String storename;
	
	@JsonInclude(value = Include.NON_NULL)
	private String token;

	@JsonInclude(value = Include.NON_NULL)
	private String ticketNo;


	public boolean isRecaptcha() {
		return recaptcha;
	}

	public void setRecaptcha(boolean recaptcha) {
		this.recaptcha = recaptcha;
	}

	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
}
