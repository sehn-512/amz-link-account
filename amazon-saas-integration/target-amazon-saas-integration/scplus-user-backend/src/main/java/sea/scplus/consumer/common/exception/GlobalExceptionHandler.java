/**
 * Copyright (c) 2016 Samsung SDSA. All Rights Reserved.
 */
package sea.scplus.consumer.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.google.gson.Gson;

import sea.scplus.consumer.common.web.JsonResponse;
import sea.scplus.consumer.vo.Meta;

/*
 * Defines how Exceptions are handled
 */
@ControllerAdvice
public class GlobalExceptionHandler 
{
	//private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private final Log LOGGER = LogFactory.getLog(getClass());
	
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APP_JSON_CHARSET_UTF_8 = "application/json; charset=UTF-8";

	/*
	 * For normal requests (insufficient parameters, etc)
	 */
	@ExceptionHandler(value = NormalRequestException.class)
	public ResponseEntity<String> NormalRequestException(NormalRequestException e,HttpServletRequest request, HttpServletResponse response) 
	{
		LOGGER.info("GlobalExceptionHandler BadRequestException");
		
		String jsonMessage = "";
		String msg = e.getMessage();
		
		Meta meta = new Meta();
		String s="::";
		if (msg.contains(s)) {
			meta.setError_code(msg.substring(0, msg.indexOf(s)));
			String submsg = msg.substring(msg.indexOf(s) + 2);
			if(submsg.contains(s)){
				meta.setError_code(submsg.substring(0, submsg.indexOf(s)));
				msg = submsg;
			}
			meta.setError_message(msg.substring(msg.indexOf(s)+2));
		} else {
	    	meta.setError_message(msg);
		}
		
		meta.setResult("FAIL");

		JsonResponse jr = new JsonResponse(meta,null);
		jsonMessage = new Gson().toJson(jr);
			
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APP_JSON_CHARSET_UTF_8);
		response.setStatus(HttpServletResponse.SC_OK);
		
		return new ResponseEntity<String>(jsonMessage, headers, HttpStatus.OK);
	}
	
	/*
	 * For bad requests (insufficient parameters, etc)
	 */
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<String> BadRequestException(BadRequestException e,HttpServletRequest request, HttpServletResponse response) 
	{
		LOGGER.info("GlobalExceptionHandler BadRequestException");
		
		String jsonMessage = "";
		String msg = e.getMessage();
		
		Meta meta = new Meta();
		String s="::";
		if (msg.contains(s)) {
			meta.setError_code(msg.substring(0, msg.indexOf(s)));
			String submsg = msg.substring(msg.indexOf(s) + 2);
			if(submsg.contains(s)){
				meta.setError_code(submsg.substring(0, submsg.indexOf(s)));
				msg = submsg;
			}
			meta.setError_message(msg.substring(msg.indexOf(s)+2));
		} else {
	    	meta.setError_message(msg);
		}
		
		meta.setResult("FAIL");

		JsonResponse jr = new JsonResponse(meta,null);
		jsonMessage = new Gson().toJson(jr);
			
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APP_JSON_CHARSET_UTF_8);
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		
		return new ResponseEntity<String>(jsonMessage, headers, HttpStatus.BAD_REQUEST);
	}

	/*
	 * For data integrity issues (adding a duplicate record to DB...)
	 */
	@ExceptionHandler(value = DataIntegrityException.class)
	public ResponseEntity<String> DataIntegrityException(DataIntegrityException e, HttpServletRequest request, HttpServletResponse response) 
	{
		LOGGER.info("GlobalExceptionHandler DataIntegrityException");
		
		String msg = e.getMessage();

		Meta meta = new Meta();
		String s="::";
		if (msg.contains(s)) {
			meta.setError_code(msg.substring(0, msg.indexOf(s)));
			meta.setError_message(msg.substring(msg.indexOf(s)+2));
		} else {
	    	meta.setError_message(msg);
		}
		
		meta.setResult("FAIL");

		JsonResponse jr = new JsonResponse(meta,null);
		String jsonMessage = new Gson().toJson(jr);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APP_JSON_CHARSET_UTF_8);
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		
		return new ResponseEntity<String>(jsonMessage, headers, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = {
			org.springframework.validation.BindException.class, 
			java.sql.SQLException.class, 
			org.springframework.dao.DataIntegrityViolationException.class,
			org.springframework.jdbc.UncategorizedSQLException.class,
			org.springframework.jdbc.BadSqlGrammarException.class,
			org.springframework.dao.DataAccessException.class,
			org.springframework.jdbc.CannotGetJdbcConnectionException.class,
			org.springframework.transaction.CannotCreateTransactionException.class,
			java.sql.SQLRecoverableException.class,
			java.sql.SQLTimeoutException.class
			})
	public ResponseEntity<String> SQLException(Exception e,HttpServletRequest request, HttpServletResponse response) 
	{
		LOGGER.info("FrontGlobalExceptionHandler SQLException");
		
		String msg = e.getMessage();

    	Meta meta = new Meta();
    	//meta.setMessage(msg);
    	LOGGER.info(msg);
    	meta.setError_message("Internal Server Error For This Request.");
    	meta.setResult("FAIL");
		
		JsonResponse jr = new JsonResponse(meta,null);

		String jsonMessage = new Gson().toJson(jr);
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APP_JSON_CHARSET_UTF_8);
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return new ResponseEntity<String>(jsonMessage, headers, HttpStatus.BAD_REQUEST);
	}
	
	/*
	 * For generic errors
	 */
	@ExceptionHandler(value = Exception.class)
	public Object handleException(Exception e) 
	{
		LOGGER.info("[GlobalExceptionHandler.handleException] : ".concat(e.getMessage()));
		
		Meta meta = new Meta();
		
		return constructJson(meta, e);
	}
	
	private String constructMessage(Exception e)
	{
		String exceptionClass = e.getClass().toString().split(" ")[1];
		String[] exceptionClass_split = exceptionClass.split("\\.");
		String message = "[" + exceptionClass_split[exceptionClass_split.length-1] + "] " + e.getMessage();
		return message;
	}
	
	private ResponseEntity<String> constructJson(Meta meta, Exception e) 
	{
		meta.setResult("FAIL");
		meta.setError_message(constructMessage(e));
		
		JsonResponse jr = new JsonResponse(meta);
		String jsonMessage = new Gson().toJson(jr);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APP_JSON_CHARSET_UTF_8);

		// return JSON object
		return new ResponseEntity<String>(jsonMessage, headers, HttpStatus.CREATED);
	}

	public static String paramJson(String paramIn) {
        paramIn = paramIn.substring(8);
        paramIn = paramIn.replaceAll("}}", "}");
        return paramIn;
    }
	
}
