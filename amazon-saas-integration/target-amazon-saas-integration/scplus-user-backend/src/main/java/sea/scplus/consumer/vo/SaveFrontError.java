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
 *
 */
public class SaveFrontError implements Serializable {
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String pageUrl;
	
	@JsonInclude(value = Include.NON_NULL)
	private String moduleName;
	
	@JsonInclude(value = Include.NON_NULL)
	private String errorMessage;
	
	@JsonInclude(value = Include.NON_NULL)
	private String sharedValue;

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSharedValue() {
		return sharedValue;
	}

	public void setSharedValue(String sharedValue) {
		this.sharedValue = sharedValue;
	}
}
