package sea.scplus.consumer.vo.emailhook;

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
    "country": "",
    "customer_id": "",
    "id": "",
    "samsung_acct_id": "",
    "source": "",
    "timestamp": "",
    "type": "",
    "payload": {
        "order_id": "53020",
        "status": "FAILED",
        "email_id": "338092",
        "email_type": "Welcome Customer For New Order",
        "subject": "Your Order Confirmation",
        "customer_name": "Junseong Park",
        "customer_email": "junpark@sea.samsung.com",
        "send_date": "2021-03-10 14:21:01"
    }
}
 *
 */
public class RequestEmailHook implements Serializable {
	
	public RequestEmailHook() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String country;
	
	@JsonInclude(value = Include.NON_NULL)
	private String customer_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String samsung_acct_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String source;
	
	@JsonInclude(value = Include.NON_NULL)
	private String timestamp;
	
	@JsonInclude(value = Include.NON_NULL)
	private String type;
	
	@JsonInclude(value = Include.NON_NULL)
	private RequestEmailHookPayload payload;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSamsung_acct_id() {
		return samsung_acct_id;
	}

	public void setSamsung_acct_id(String samsung_acct_id) {
		this.samsung_acct_id = samsung_acct_id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RequestEmailHookPayload getPayload() {
		return payload;
	}

	public void setPayload(RequestEmailHookPayload payload) {
		this.payload = payload;
	}


}
