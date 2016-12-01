package system;

import java.sql.Time;

public class Sprinkler {

    String ID;
    double waterVolume;

    public Sprinkler(){


    }

    public String getID(){
        return ID;
    }

    public void setWaterVolume(double val){
        this.waterVolume=val;
    }

    public double getWaterVolume(){
        return waterVolume;
    }



}