/**
 * Copyright (c) 2015 Samsung SEA. All Rights Reserved.
 *
 * Project: MCP Project(2015.03~07)
 */
package sea.scplus.consumer.common.mail;

/**
 *
 * Class Name : SMTPMailTemplate
 * Description : SMTPMailTemplate enum class
 *
 * @author Wonho Lee
 * @since April 5, 2015
 * @version 1.0
 */
public enum SMTPMailTemplate {

	ORDER_CONFIRM("templates/vm/order_confirm.vm"),
	ORDER_CANCEL("templates/vm/order_cancel.vm"),
	ORDER_ERROR("templates/vm/order_error.vm"),
	;

	private final String templateLocation;

	private SMTPMailTemplate(String templateLocation) {
		this.templateLocation = templateLocation;
	}

	/**
	 * velocity vm file location
	 * @return velocity vm
	 */
	public String getTemplateLocation() {
		return templateLocation;
	}

}
