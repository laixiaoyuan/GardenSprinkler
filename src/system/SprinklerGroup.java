package system;

import java.util.ArrayList;
import java.util.List;

public class SprinklerGroup {

    List<Sprinkler> sList;
    String groupName;
    double waterVolume;

    public SprinklerGroup(){
        sList = new ArrayList<Sprinkler>();
    }

    public List<Sprinkler> getSprinklerList(){
        return sList;
    }

    public String getName(){
        return groupName;
    }

    public void setWaterVolume(double val){
        this.waterVolume=val;
    }

    public double getWaterVolume(){
        return waterVolume;
    }


}