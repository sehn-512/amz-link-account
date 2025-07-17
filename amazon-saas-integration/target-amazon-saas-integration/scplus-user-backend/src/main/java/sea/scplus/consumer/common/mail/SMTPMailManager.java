/**
 * Copyright (c) 2015 Samsung SEA. All Rights Reserved.
 *
 * Project: MCP Project(2015.03~07)
 */
package sea.scplus.consumer.common.mail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.generic.EscapeTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


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
public class SMTPMailManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(SMTPMailManager.class);

	private static final String ENCODING = "UTF-8";
	
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * sned Mail Async
	 * @param mailTemplate
	 * @param mailMessage
	 * @return {@link AsyncResult<MailMessage>(mailMessage)}
	 * @throws IOException 
	 */
	@Async
	public Future<SMTPMailMessage> send(SMTPMailMessage mailMessage, String mailBody, String attachmentFilename) throws IOException {

		LOGGER.info("Send Mail=to:{}, subject:{}", mailMessage.getTo(), mailMessage.getSubject());

		boolean multipart = true;

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, multipart, ENCODING);
			// subject
			helper.setSubject(mailMessage.getSubject());
			helper.setFrom(mailMessage.getFrom());

			// to
			String[] to = removeNull(mailMessage.getTo());
			if (to == null || to.length == 0) {
				LOGGER.error("Can not check the email recipient..");
				return null;
			}
			helper.setTo(to);

			// cc
			String[] cc = removeNull(mailMessage.getCc());
			if (cc != null && cc.length > 0) {
				helper.setCc(cc);
			}

			// bcc
			String[] bcc = removeNull(mailMessage.getBcc());
			if (bcc != null && bcc.length > 0) {
				helper.setBcc(bcc);
			}

			// Generated Mail text
			helper.setText(mailBody, true);
			
			// Attach file
            if (!attachmentFilename.equals("")) {

            	/**** Get The Absolute Path Of The File ****/
//            	pdfFilePath = Util.getFilePath(req) + File.separator + attachmentFilename;      
            	LOGGER.info("Absolute Path Of The .PDF File Is?= " + attachmentFilename);
            	
            	FileSystemResource file = new FileSystemResource(attachmentFilename);
            	helper.addAttachment(file.getFilename(),
            		    new ByteArrayResource(IOUtils.toByteArray(file.getInputStream())));
//                helper.addAttachment(file.getFilename(), file);
                
            }
            
			
		} catch (MessagingException e) {
			LOGGER.error("send mail fail:{}, causing:{}", mailMessage, e.getMessage());
		}
		
		try{
			mailSender.send(mimeMessage);	
		}catch(Exception e){
			LOGGER.error("Error Email : ",e);
		}
		
		LOGGER.info("send mail Complete-to:{}, form:{}", mailMessage.getTo(), mailMessage.getFrom());
		
		return new AsyncResult<SMTPMailMessage>(mailMessage);
	}
	
	public String mergeMailMessage(SMTPMailTemplate mailTemplate, SMTPMailMessage mailMessage, Context context, String attachmentFilename) throws IOException {

		LOGGER.info("mergeMailMessage Mail=to:{}, subject:{}", mailMessage.getTo(), mailMessage.getSubject());

		String strMailMessage = "";
		
		try {
			// Generated Mail text
			String templateLocation = mailTemplate.getTemplateLocation();
			
//			strMailMessage = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateLocation, ENCODING, templateModel);
			VelocityEngine ve = new VelocityEngine();
			Properties p = new Properties();
			p.setProperty("resource.loader", "class");
			p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			ve.init( p );  
			
			Template template = ve.getTemplate(templateLocation);
			 
			StringWriter stringWriter = new StringWriter();
			template.merge(context, stringWriter);

			strMailMessage = stringWriter.toString();
//			helper.setText(stringWriter.toString(), true);
			
			
		} catch (Exception e) {
			LOGGER.error("send mail fail:{}, causing:{}", mailMessage, e.getMessage());
		}
		
		return strMailMessage;
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
