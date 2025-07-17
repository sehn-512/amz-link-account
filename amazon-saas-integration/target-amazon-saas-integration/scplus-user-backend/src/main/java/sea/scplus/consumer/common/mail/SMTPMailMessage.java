/**
 * Copyright (c) 2015 Samsung SEA. All Rights Reserved.
 *
 * Project: MCP Project(2015.03~07)
 */
package sea.scplus.consumer.common.mail;

import java.util.Map;

import org.springframework.mail.SimpleMailMessage;

/**
 * 
 * Class Name : SMTPMailMessage
 * Description : SMTPMailMessage class
 *
 * @author  Wonho Lee
 * @since April 5, 2015
 * @version 1.0
 */
public class SMTPMailMessage extends SimpleMailMessage {

	private static final long serialVersionUID = 443813348809411341L;

	/** velocity template vm(html) value */
	private Map<String, Object> templateModel;

	public Map<String, Object> getTemplateModel() {
		return templateModel;
	}

	public void setTemplateModel(Map<String, Object> templateModel) {
		this.templateModel = templateModel;
	}
	
}
