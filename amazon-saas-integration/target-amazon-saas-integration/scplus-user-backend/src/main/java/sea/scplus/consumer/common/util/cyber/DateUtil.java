package sea.scplus.consumer.common.util.cyber;

import java.util.Calendar;

public class DateUtil
{
	public static String getDate(String format) 
	{
		java.text.SimpleDateFormat sd = new java.text.SimpleDateFormat(format);
		return sd.format(new java.util.Date());
	}
	
	public static String getDate(String yyyyMMdd, int next) 
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(yyyyMMdd.substring(0, 4)),
				Integer.parseInt(yyyyMMdd.substring(4, 6)) - 1,
				Integer.parseInt(yyyyMMdd.substring(6, 8)));
		cal.add(Calendar.DATE, next);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		month += 1;
		int date = cal.get(Calendar.DATE);
		StringBuilder string = new StringBuilder("");
		string.append(year).append((month < 10) ? "0" + month : month)
				.append((date < 10) ? "0" + date : date);
		return string.toString();
	}
}
