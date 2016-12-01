package system;

import java.util.ArrayList;
import java.util.List;

public class SprinklerSystem {

    private final static int defaultVolume = 10;
    private final static String[] defaultGroup = {"NORTH","SORTH","EAST","WEST"};
//    private final static Schedule defaultSchedule= new Schedule(3,10,0,10,5);

    private boolean isOn;
    private List<SprinklerGroup> sGroupList;

    public SprinklerSystem(){
        sGroupList = new ArrayList<SprinklerGroup>();
        for(int i=0;i<defaultGroup.length;i++){
//            SprinklerGroup newGroup = new SprinklerGroup(defaultGroup[i]);
//            newGroup.setWaterVolume(defaultVolume);
//            newGroup.addNewSchedule(defaultSchedule);
//            newGroup.addSprinkler();
//            newGroup.addSprinkler();
//            sGroupList.add(newGroup);
        }
    }

    public boolean getStatus(){
        return isOn;
    }

    public void setStatus(boolean isOn){
        this.isOn=isOn;
    }

    public int getWaterVolume(String groupName){
        int val=0;
        for(SprinklerGroup group : sGroupList){
            if(group.getName().equals(groupName)){
//                val = group.getWaterVolume();
                break;
            }
        }
        return val;
    }

    public void setWaterVolume(String groupName, int val){
        for(SprinklerGroup group : sGroupList){
            if(group.getName().equals(groupName)){
                group.setWaterVolume(val);
                break;
            }
        }
    }

    public List<Sprinkler> getSprinklerList(String groupName){
        List<Sprinkler> res = new ArrayList<>();
        for(SprinklerGroup group : sGroupList){
            if(group.getName().equals(groupName)){
                res = group.getSprinklerList();
                break;
            }
        }
        return res;
    }

    public List<Schedule> getSchedule(String groupName){
        List<Schedule> res = new ArrayList<>();
        for(SprinklerGroup group : sGroupList){
            if(group.getName().equals(groupName)){
//                res = group.getSchedule();
                break;
            }
        }
        return res;
    }

    public void addSchedule(String groupName, int day,
                            int startHour, int startMin, int endHour, int endMin){
        for(SprinklerGroup group : sGroupList){
            if(group.getName().equals(groupName)){
//                group.addNewSchedule(new Schedule(day,startHour,startMin,endHour,endMin));
                break;
            }
        }
    }




}