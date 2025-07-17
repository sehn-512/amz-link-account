package sea.scplus.consumer.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class Util {

	private final static Logger LOGGER = LoggerFactory.getLogger(Util.class);

	public static String nullToString(Object obj) {
		
		if ( obj == null ) {
			return "";
		}else {
			return obj + "";
		}
	}
	
	public static String encodeForHTML(String value){
		if(value != null && !StringUtils.isEmpty(value)){
			try{
				return ESAPI.encoder().encodeForHTML(value);
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}else{
			return value;
		}
	}
	
	public static String getCurrentTimeUsingDate() {
	    Date date = new Date();
	    String strDateFormat = "yyyyMMddhh24mmss";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String formattedDate= dateFormat.format(date);
	    LOGGER.debug("Current time of the day using Date - 12 hour format: " + formattedDate);
	    return formattedDate;
	}
	
	public static String getCurrentDate() {
	    Date date = new Date();
	    String strDateFormat = "MMM. dd, yyyy";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String formattedDate= dateFormat.format(date);
	    LOGGER.debug("Current time of the day using Date - 12 hour format: " + formattedDate);
	    return formattedDate;
	}
	    
	/**** Method #1 - This Method Is Used To Retrieve The File Path From The Server ****/
	public static String getFilePath(HttpServletRequest req) throws FileNotFoundException {
		String appPath = "", fullPath = "", downloadPath = "download";

		/**** Retrieve The Absolute Path Of The Application ****/
		appPath = req.getSession().getServletContext().getRealPath("");	
		fullPath = appPath + File.separator + downloadPath;
		LOGGER.debug("Destination Location For The File Is?= " + fullPath);
		return fullPath;
	}

	/**** Method #2 - This Method Is Used To Get The No. Of Columns In The ResultSet ****/
	public static int getColumnCount(ResultSet res) throws SQLException {
		int totalColumns = res.getMetaData().getColumnCount();		
		return totalColumns;
	}
	
	public static String getMessage(String msg) {
		
		String s="::";
		if (msg.contains(s)) {
			String submsg = msg.substring(msg.indexOf(s) + 2);
			if(submsg.contains(s)){
				msg = submsg;
			}
			msg = msg.substring(msg.indexOf(s)+2);
		} 
		
		return msg;
		
	}
	
	public static String getCardType(String CreditCardNumber) {
		
		Pattern  regVisa = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
		Pattern  regMaster = Pattern.compile("^5[1-5][0-9]{14}$");
		Pattern  regExpress = Pattern.compile("^3[47][0-9]{13}$");
		Pattern  regDiners = Pattern.compile("^3(?:0[0-5]|[68][0-9])[0-9]{11}$");
		Pattern  regDiscover = Pattern.compile("^6(?:011|5[0-9]{2})[0-9]{12}$");
		Pattern  regJCB = Pattern.compile("^(?:2131|1800|35d{3})d{11}$");

		Matcher matcher = regVisa.matcher(CreditCardNumber);
		Matcher matcher1 = regMaster.matcher(CreditCardNumber);
		Matcher matcher2 = regExpress.matcher(CreditCardNumber);
		Matcher matcher3 = regDiners.matcher(CreditCardNumber);
		Matcher matcher4 = regDiscover.matcher(CreditCardNumber);
		Matcher matcher5 = regJCB.matcher(CreditCardNumber);
		
	    if (matcher.find() == true)
	        return "VISA";
	    else if (matcher1.find() == true)
	        return "MASTER";
	    else  if (matcher2.find() == true)
	        return "AEXPRESS";
	    else if (matcher3.find() == true)
	        return "DINERS";
	    else if (matcher4.find() == true)
	        return "DISCOVERS";
	    else if (matcher5.find() == true)
	        return "JCB";
	    else
	        return "invalid";

	}

	/**** Method #3 - This Method Is Used To Set The Download File Properties ****/
	public static void downloadFileProperties(HttpServletRequest req,HttpServletResponse resp, String toBeDownloadedFile, File downloadFile) {
		try {

			/**** Get The Mime Type Of The File & Setting The Binary Type If The Mime Mapping Is Not Found ****/
			String mimeType = req.getSession().getServletContext().getMimeType(toBeDownloadedFile);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}

			/**** Setting The Content Attributes For The Response Object ****/
			resp.setContentType(mimeType);
			resp.setContentLength((int) downloadFile.length());

			/**** Setting The Headers For The Response Object ****/
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			resp.setHeader(headerKey, headerValue);

			/**** Get The Output Stream Of The Response ****/
			OutputStream outStream = resp.getOutputStream();
			FileInputStream inputStream = new FileInputStream(downloadFile);
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			/**** Write Each Byte Of Data Read From The Input Stream Write Each Byte Of Data  Read From The Input Stream Into The Output Stream ****/
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outStream.close();
		} catch(IOException ioExObj) {
			LOGGER.error("Exception While Performing The I/O Operation?= " + ioExObj);
		}
	}
	
	public static String strToMask(String src) {
		
		String maskSrc = "";
		
		int len = src.length();
		int halfLength = len / 2;
		
		maskSrc = src.substring(0, halfLength) + getMaskStr(halfLength);
		
		return maskSrc;
	}
	
	private static String getMaskStr(int halfLength) {
		
		String mask = "";
		
		for ( int i = 0 ; i < halfLength ; i++ )
			mask += "*";
		
		return mask; 
	}
	
//	public static void main(String[] args) {
//		System.out.println(getCardType("4111111111111111"));
//		System.out.println(getCardType("2222630000001125"));
//		System.out.println(getCardType("5555555555554444"));
//		System.out.println(getCardType("378282246310005"));
//		System.out.println(getCardType("6011111111111117"));
//		System.out.println(getCardType("3566111111111113"));
//		System.out.println(getCardType("50339619890917"));
//		System.out.println(getCardType("6759411100000008"));
//		System.out.println(getCardType("135412345678911"));
//		System.out.println("2021-04-04".replaceAll("-", ""));
//	}
}