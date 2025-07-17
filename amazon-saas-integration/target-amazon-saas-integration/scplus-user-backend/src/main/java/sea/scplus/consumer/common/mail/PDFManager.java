/**
 * Copyright (c) 2015 Samsung SEA. All Rights Reserved.
 *
 * Project: MCP Project(2015.03~07)
 */
package sea.scplus.consumer.common.mail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Future;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.generic.EscapeTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * Class Name : SMTPMailManager
 * Description : SMTPMailManager Component Class
 *
 * @author  Wonho Lee
 * @since April 5, 2015
 * @version 1.0
 */
@Component
public class PDFManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(PDFManager.class);

	private static final String ENCODING = "UTF-8";
	
	/**
	 * Save PDF
	 * @param mailTemplate
	 * @param mailMessage
	 * @return {@link AsyncResult<MailMessage>(mailMessage)}
	 * @throws Exception 
	 */
	public byte[] save(SMTPMailTemplate mailTemplate, SMTPMailMessage mailMessage, String cpa_ticket_id, String PDF_PATH) throws Exception {

		LOGGER.info("Send Mail=to:{}, subject:{}", mailMessage.getTo(), mailMessage.getSubject());

		// Generated PDF Content text
		Map<String, Object> templateModel = mailMessage.getTemplateModel();
		Assert.notNull(templateModel);
		templateModel.put("esc", new EscapeTool());
		String templateLocation = mailTemplate.getTemplateLocation();
		
//		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateLocation, ENCODING, templateModel);
		Template template = Velocity.getTemplate(templateLocation);
		
		StringWriter stringWriter = new StringWriter();
		template.merge((Context) templateModel, stringWriter);

		
		String text = stringWriter.toString();
		
		LOGGER.info("PDF Save : " + text);
		
		// Save As String to PDF
		PDFSave pdfSave = new PDFSave();
		byte[] file = pdfSave.buildPdfDocument(text, cpa_ticket_id, PDF_PATH);
			
		LOGGER.info("save PDF Complete-file:{}", file);
		LOGGER.info("save PDF Complete-to:{}, form:{}", mailMessage.getTo(), mailMessage.getFrom());
		
		return file;
	}

	/**
	 * String array removed null
	 * @param arr
	 * @return removed null Array
	 */
	private String[] removeNull(String[] arr) {
		if (arr == null || arr.length == 0) {
			return arr;
		}

		ArrayList<String> removedList = new ArrayList<String>();
		for (String str : arr) {
			removedList.add(str);
		}

		return removedList.toArray(new String[0]);
	}

}
