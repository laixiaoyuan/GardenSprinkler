package system;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/* The schedule class has scheduled DayOfWeek, start time and end time.
 * By using toDate() function, the schedule would be converted to the
 * date of next DayOfWeek with the start hour and start minutes. 
 */

public class Schedule {
	
	private String schedID;
	private int schedDay;
	private int startHour;
	private int startMin;
	private int endHour;
	private int endMin;
	
	public Schedule(String id, int day, int startHour, int startMin, int endHour, int endMin){
		schedID=id;
		schedDay = day;
		this.startHour = startHour;
		this.startMin = startMin;
		this.endHour = endHour;
		this.endMin = endMin;
	}
	
	public String getID(){
		return schedID;
	}
	
	public int getDay(){
		return schedDay;
	}
	
	public int getStartHour(){
		return startHour;
	}
	
	public int getStartMin(){
		return startMin;
	}
	
	public int getEndHour(){
		return endHour;
	}
	
	public int getEndMin(){
		return endMin;
	}
	
	public Date toDate(){
		Calendar c = Calendar.getInstance();
		int currDay = c.get(Calendar.DAY_OF_WEEK);
		if(schedDay>=currDay){
			c.add(Calendar.DATE,schedDay-currDay);
		}else{
			c.add(Calendar.DATE, schedDay+7-currDay);
		}		
		c.set(Calendar.MINUTE, startMin);
		c.set(Calendar.HOUR, startHour);
		
		return c.getTime();
	}
	
	public long getDuration(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MINUTE, startMin);
		c.set(Calendar.HOUR, startHour);
		Date start = c.getTime();
		c.set(Calendar.MINUTE, endMin);
		c.set(Calendar.HOUR, endHour);
		Date end = c.getTime(); 
		return end.getTime()-start.getTime();
	}
	
//	public static void main(String[] args){
//		Schedule ss = new Schedule("0",2,10,0,10,15);
//		System.out.println(ss.toDate());
//		System.out.println(ss.getDuration());
//		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
//		System.out.println(df.format(new Date()));
//	}
}
