package sea.scplus.consumer.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseGetTicketInfo implements Serializable {

	public ResponseGetTicketInfo() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String ticketNo;
	
	@JsonInclude(value = Include.NON_NULL)
	private String status;
	
	@JsonInclude(value = Include.NON_NULL)
	private String assignedASCNo;
	
	@JsonInclude(value = Include.NON_NULL)
	private String technicianID;

	@JsonInclude(value = Include.NON_NULL)
	private String serial;


	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssignedASCNo() {
		return assignedASCNo;
	}

	public void setAssignedASCNo(String assignedASCNo) {
		this.assignedASCNo = assignedASCNo;
	}

	public String getTechnicianID() {
		return technicianID;
	}

	public void setTechnicianId(String technicianId) {
		this.technicianID = technicianID;
	}

	public String getSerial() { return serial; }

	public void setSerial(String serial) { this.serial = serial; }

	@Override
	public String toString() {
		return "ResponseGetTicketInfo{" +
				"ticketNo='" + ticketNo + '\'' +
				", status='" + status + '\'' +
				", assignedASCNo ='" + assignedASCNo + '\'' +
				", technicianID='" + technicianID + '\'' +
				", serial='" + serial + '\'' +
				'}';
	}
}
