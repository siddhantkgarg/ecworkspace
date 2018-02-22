package Util;
import java.util.Calendar;
import java.util.Date;

public class Utils {

	public static Date getNextDate(int n) {
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, n);
		dt = c.getTime();
		return dt;
	}
	
}
