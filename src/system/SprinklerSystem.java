package system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import data.DataFile;

public class SprinklerSystem {

	private final static int defaultVolume = 10;
	private final static String[] defaultGroupName = {"NORTH","SOUTH","EAST","WEST"};
	private final static int defaultSchedDay = 3;
	private final static int defaultStartHour = 10;
	private final static int defaultStartMin = 0;
	private final static int defaultEndHour = 10;
	private final static int defaultEndMin = 5;
	private final static int defaultSysTemp = 70;
	private final static int defaultMaxTemp = 90;
	private final static int defaultMinTemp = 60;
	private final static int defaultDuration = 2000;

	private boolean isOn;
	private int sysTemp;
	private int maxTemp;
	private int minTemp;
	private List<Sprinkler> srList;
	private List<SprinklerGroup> sGroupList;
	private Map<String,SprinklerGroup> sGroupMap;
	private DataFile dataFile;

	public SprinklerSystem(){
		this.isOn = true;
		sysTemp = defaultSysTemp;
		maxTemp = defaultMaxTemp;
		minTemp = defaultMinTemp;
		srList = new ArrayList<Sprinkler>();
		sGroupList = new ArrayList<SprinklerGroup>();
		sGroupMap = new HashMap<String,SprinklerGroup>();
		for(int i=0;i<defaultGroupName.length;i++){
			String groupName = defaultGroupName[i];
			SprinklerGroup newGroup = new SprinklerGroup(groupName);
			newGroup.setWaterVolume(defaultVolume);
			newGroup.addNewSchedule(defaultSchedDay,defaultStartHour,defaultStartMin,defaultEndHour,defaultEndMin);
			srList.add(newGroup.addSprinkler());
			srList.add(newGroup.addSprinkler());
			sGroupList.add(newGroup);
			sGroupMap.put(groupName,newGroup);
		}
		dataFile = new DataFile();
		dataFile.loadData();
	}

	public boolean getSysStatus(){
		return isOn;
	}

	public void setSysStatus(boolean isOn){
		this.isOn=isOn;
	}

	public int getSysTemp(){
		return sysTemp;
	}

	public void setCurrSysTemp(int val){
		sysTemp = val;
		if(val>maxTemp){
			for(SprinklerGroup group : sGroupList){
				group.addTempTask(defaultDuration);
			}
		}
		if(val<minTemp){
			for(SprinklerGroup group : sGroupList){
				group.setDisableGroup();
			}
		}
	}

	public int getMaxTemp(){
		return maxTemp;
	}

	public void setMaxTemp(int val){
		maxTemp=val;
	}

	public int getMinTemp(){
		return minTemp;
	}

	public void setMinTemp(int val){
		minTemp=val;
	}

	public void setSprinklerStatus(String sID, boolean stat){
		for(Sprinkler s:srList){
			if(s.getID().equals(sID)){
				if(stat==true) s.setEnable();
				else s.setDisable();
			}
		}
	}

	public Map<String,Boolean[]> getSprinklerStatus(String groupName){
		Map<String, Boolean[]> res = new HashMap<>();
		SprinklerGroup g = sGroupMap.get(groupName);
		for(Sprinkler spkl : g.getSprinklerList()){
			Boolean[] status = new Boolean[2];
			status[0]=spkl.getWorkStatus();
			status[1]=spkl.getFuncStatus();
			res.put(spkl.getID(), status);
		}
		return res;
	}

	public List<SprinklerGroup> getGroupList(){
		List<SprinklerGroup> res = new ArrayList<>();
		for(Entry<String, SprinklerGroup> e : sGroupMap.entrySet()){
			res.add(e.getValue());
		}
		return res;
	}

	public void setGroupStatus(String groupName,boolean stat){
		SprinklerGroup g = sGroupMap.get(groupName);
		if(stat){
			g.setEnableGroup();
		} else{
			g.setDisableGroup();
		}
	}

	public Map<String,Boolean> getGroupStatus(){
		Map<String, Boolean> res = new HashMap<>();
		for(Entry<String, SprinklerGroup> e : sGroupMap.entrySet()){
			res.put(e.getKey(), e.getValue().getStatus());
		}
		return res;
	}

	public int getWaterVolume(String groupName){
		SprinklerGroup g = sGroupMap.get(groupName);
		return g.getWaterVolume();
	}

	public void setWaterVolume(String groupName, int val){
		SprinklerGroup g = sGroupMap.get(groupName);
		g.setWaterVolume(val);
	}

	public List<Sprinkler> getSprinklerList(String groupName){
		SprinklerGroup g = sGroupMap.get(groupName);
		return g.getSprinklerList();
	}

	public List<Schedule> getSchedule(String groupName){
		SprinklerGroup g = sGroupMap.get(groupName);
		return g.getSchedule();
	}

	public void addSchedule(String groupName, int day,
							int startHour, int startMin, int endHour, int endMin){
		SprinklerGroup g = sGroupMap.get(groupName);
		g.addNewSchedule(day, startHour, startMin, endHour, endMin);
	}

	public int[] getSysWCData(){
		return dataFile.getSysWC();
	}

	public int[] getGroupWCData(String groupName){
		return dataFile.getGroupWC(groupName);
	}

//	public static void main(String[] args){
//		SprinklerSystem sys = new SprinklerSystem();
//		int[] res = sys.getSysWCData();
//		for(int i=0;i<res.length;i++){
//			System.out.print(res[i]+" ");
//		}
//	}
}