package system;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class System {

    private boolean isOn;
    protected List<Sprinkler> sprinklerList;
    protected  List<SprinklerGroup> sGroupList;
    protected WeeklySchedule ws;



    public System(){
        ServiceInterface si = new ServiceInterface(this);
        SystemMonitor sm = new SystemMonitor(this);
        sprinklerList = new ArrayList<Sprinkler>();
        ws = new WeeklySchedule();
    }

    public boolean getStatus(){
        return isOn;
    }

    public void setStatus(boolean isOn){
        this.isOn=isOn;
    }

    public void addSprinkler(Sprinkler s){

    }

    public double getWaterVolume(Sprinkler sprinkler){
        return sprinkler.getWaterVolume();
    }

    public double getWaterVolume(SprinklerGroup group){
        return group.getWaterVolume();
    }

    public void setWaterVolume(Sprinkler sprinkler){

    }

    public void setWaterVolume(SprinklerGroup group){

    }

//	public List<double> getWaterConsumption(){
//
//	}

    public void setPattern(Sprinkler s, int day, Time start, Time stop){
        Pattern newPattern = new Pattern(s.getID(),start,stop);
        ws.setWeeklySchedule(day,newPattern);
    }

    public void setPattern(SprinklerGroup sg, int day, Time start, Time stop){
        List<Sprinkler> sList = sg.getSprinklerList();
        for(Sprinkler s : sList){
            Pattern newPattern = new Pattern(s.getID(),start,stop);
            ws.setWeeklySchedule(day,newPattern);
        }
    }


}
