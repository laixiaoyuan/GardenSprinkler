package system;

import java.util.Calendar;
import java.util.Date;

//class of daily schedule, include day,start time,stop time
public class Schedule {

    private String schedID;
    private int schedDay;
    private int startHour;
    private int startMin;
    private int endHour;
    private int endMin;

    public Schedule(String schedID, int day, int startHour, int startMin, int endHour, int endMin){
        this.schedID = schedID;
        schedDay = day;
        this.startHour = startHour;
        this.startMin = startMin;
        this.endHour = endHour;
        this.endMin = endMin;
    }

    public int getDay(){
        return schedDay;
    }

    public String getID() {
        return schedID;
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
        c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH),startHour,startMin);
        return c.getTime();
    }
}