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
        "channel":"CEPOS",
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
public class RequestEmailHookPayload implements Serializable {
	
	public RequestEmailHookPayload() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String order_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String status;
	
	@JsonInclude(value = Include.NON_NULL)
	private String channel;
	
	@JsonInclude(value = Include.NON_NULL)
	private String email_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String email_type;
	
	@JsonInclude(value = Include.NON_NULL)
	private String subject;
	
	@JsonInclude(value = Include.NON_NULL)
	private String customer_name;
	
	@JsonInclude(value = Include.NON_NULL)
	private String customer_email;
	
	@JsonInclude(value = Include.NON_NULL)
	private String send_date;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getEmail_type() {
		return email_type;
	}

	public void setEmail_type(String email_type) {
		this.email_type = email_type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getSend_date() {
		return send_date;
	}

	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	

}
