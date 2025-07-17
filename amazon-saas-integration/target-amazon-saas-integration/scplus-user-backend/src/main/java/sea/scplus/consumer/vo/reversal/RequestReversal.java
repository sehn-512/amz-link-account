package sea.scplus.consumer.vo.reversal;

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
public class RequestReversal implements Serializable {
	
	public RequestReversal() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String token;
	
	@JsonInclude(value = Include.NON_NULL)
	private String cs_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private RequestReversalClientReferenceInformation clientReferenceInformation;

	@JsonInclude(value = Include.NON_NULL)
	private RequestReversalReversalInformation reversalInformation;

	
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

	public RequestReversalClientReferenceInformation getClientReferenceInformation() {
		return clientReferenceInformation;
	}

	public void setClientReferenceInformation(RequestReversalClientReferenceInformation clientReferenceInformation) {
		this.clientReferenceInformation = clientReferenceInformation;
	}

	public RequestReversalReversalInformation getReversalInformation() {
		return reversalInformation;
	}

	public void setReversalInformation(RequestReversalReversalInformation reversalInformation) {
		this.reversalInformation = reversalInformation;
	}

}
