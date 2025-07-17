package sea.scplus.consumer.vo.cancel;

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
	"referenceCode": "6143607079336326803006",
    "clientReferenceInformation": {
        "code": "TC50171_3"
    },
    "orderInformation": {
        "amountDetails": {
            "totalAmount": "10",
            "currency": "USD"
        }
    }
} 
 *
 */
public class RequestCancel implements Serializable {
	
	public RequestCancel() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String token;
	
	@JsonInclude(value = Include.NON_NULL)
	private String cs_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private RequestCancelClientReferenceInformation clientReferenceInformation;

	@JsonInclude(value = Include.NON_NULL)
	private RequestCancelOrderInformation orderInformation;

	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCs_id() {
		return cs_id;
	}

	public void setCs_id(String cs_id) {
		this.cs_id = cs_id;
	}

	public RequestCancelClientReferenceInformation getClientReferenceInformation() {
		return clientReferenceInformation;
	}

	public void setClientReferenceInformation(RequestCancelClientReferenceInformation clientReferenceInformation) {
		this.clientReferenceInformation = clientReferenceInformation;
	}

	public RequestCancelOrderInformation getOrderInformation() {
		return orderInformation;
	}

	public void setOrderInformation(RequestCancelOrderInformation orderInformation) {
		this.orderInformation = orderInformation;
	}
	
	
}
