package sea.scplus.consumer.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import sea.scplus.consumer.common.constant.CommonValue.Constant;

public class TimezoneDateTimeUtil 
{
	public static String getCurrentDateTimeString(String timezone) throws ParseException{
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		TimeZone currentTimezone = TimeZone.getTimeZone(timezone);
		
		if(currentTimezone == null){
			
			return Constant.EMPTY_STRING;
		}
		else{
		
			sdf1.setTimeZone(currentTimezone);
			
			Date now = new Date();
			
		    String currentDateTime = sdf1.format(now);
	
			return currentDateTime;
		}
	}
	
	public static String getCurrentDateString() throws ParseException{
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			
		Date now = new Date();
		
	    String currentDate = sdf1.format(now);

		return currentDate;
	}
	
	public static String getAddMonthToCurrentDateString(String dateAsString, int addMonth) throws ParseException{
		
		String format = "yyyy-MM-dd" ;
        SimpleDateFormat sdf = new SimpleDateFormat(format) ;
        Date dateAsObj = sdf.parse(dateAsString) ;
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateAsObj);
        cal.add(Calendar.MONTH, addMonth);
        Date dateAsObjAfterAMonth = cal.getTime() ;

        String currentDate = sdf.format(dateAsObjAfterAMonth);
        
		return currentDate;
	}
	
	public static String getAddDayToCurrentDateString(String dateAsString, int addDay) throws ParseException{
		
		String format = "yyyy-MM-dd" ;
        SimpleDateFormat sdf = new SimpleDateFormat(format) ;
        Date dateAsObj = sdf.parse(dateAsString) ;
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateAsObj);
        cal.add(Calendar.DATE, addDay);
        Date dateAsObjAfterAMonth = cal.getTime() ;

        String currentDate = sdf.format(dateAsObjAfterAMonth);
		return currentDate;
	}
	
	public static String getBiggerDate(String purchasedate) throws ParseException{
		
		String orderDate = getCurrentDateString();
		String format = "yyyy-MM-dd" ;
        SimpleDateFormat sdf = new SimpleDateFormat(format) ;
        Date objPur = sdf.parse(purchasedate) ;
        Date objOrder = sdf.parse(orderDate) ;
        
        if ( objPur.after(objOrder) ) {
        	return orderDate;
        } else 
        	return purchasedate;
        
    }
	
	public static String getCurrentDateTimeString(String timezone, String datetimeFormat) throws ParseException{
		
		SimpleDateFormat sdf1 = new SimpleDateFormat(datetimeFormat);
		
		TimeZone currentTimezone = TimeZone.getTimeZone(timezone);
		
		if(currentTimezone == null){
			
			return Constant.EMPTY_STRING;
		}
		else{
		
			sdf1.setTimeZone(currentTimezone);
			
			Date now = new Date();
			
		    String currentDateTime = sdf1.format(now);
	
			return currentDateTime;
		}
	}
	
//	public static void main(String[] arg) throws ParseException {
//		String tmp = TimezoneDateTimeUtil.getCurrentDateTimeString("EST","yyyy-MM-dd HH:mm:ss");
//		System.out.println("tmp:"+ tmp);
//		
//		System.out.println(getBiggerDate("2022-05-01"));
//		//Long l = 202103231758280255;
//		
//	}
}