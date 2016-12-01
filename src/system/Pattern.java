package system;

import java.sql.Time;

//class of daily schedule, include day,start time,stop time
public class Pattern {

    String sID;
    Time start;
    Time stop;

    public Pattern(String sID, Time start, Time stop){
        this.sID=sID;
        this.start=start;
        this.stop=stop;
    }

}
