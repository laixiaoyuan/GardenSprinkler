package system;

import java.sql.Time;
import java.util.List;

public class ServiceInterface {

    private System sys;

    public ServiceInterface(System sys) {
        // TODO Auto-generated constructor stub
        this.sys=sys;
    }

    //system status
    public boolean getStatus(){
        return sys.getStatus();
    }

    public void setStatus(boolean isOn){
        sys.setStatus(isOn);;
    }

    public List<Sprinkler> getSprinklerList(){
        return sys.sprinklerList;
    }

    public List<SprinklerGroup> getSGroupList(){
        return sys.sGroupList;
    }

    public double getSprinklerWaterVolume(Sprinkler sprinkler){
        return sys.getWaterVolume(sprinkler);
    }

    public double getSprinklerGroupWaterVolume(SprinklerGroup group){
        return sys.getWaterVolume(group);
    }

    public void setWaterVolume(Sprinkler sprinkler){
        sys.setWaterVolume(sprinkler);
    }

    public void setWaterVolume(SprinklerGroup group){
        sys.setWaterVolume(group);
    }

    public void setNewPattern(Sprinkler s,int day,Time start,Time stop){
        sys.setPattern(s, day, start, stop);
    }

    public void setNewPattern(SprinklerGroup sg,int day, Time start, Time stop){
        sys.setPattern(sg, day, start, stop);
    }

//	public double getWaterConsumption(){
//		return sys.getWaterConsumption();
//	}

}
