package system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class SprinklerGroup {

	boolean status;
	String groupName;
	List<Sprinkler> sList;
	List<Schedule> schedList;
	Timer groupTimer;
	//	List<TimerTask> taskList;
	Map<String,TimerTask> schedTaskMap;
	int waterVolume;

	private int sprinklerCounter;
	private int schedCounter;

	private final static long schedInterval = 1000*60*60*24*7;

	public SprinklerGroup(String groupName){
		status=false;
		this.groupName = groupName;
		sList = new ArrayList<Sprinkler>();
		schedList = new ArrayList<Schedule>();
		groupTimer = new Timer();
//		taskList = new ArrayList<>();
		schedTaskMap = new HashMap<>();
	}

	public Sprinkler addSprinkler(){
		sprinklerCounter++;
		String newSprinklerID = sprinklerCounter+ String.valueOf(groupName.charAt(0));
		Sprinkler s = new Sprinkler(newSprinklerID);
		sList.add(s);
		return s;
	}

	public List<Sprinkler> getSprinklerList(){
		return sList;
	}

	public String getName(){
		return groupName;
	}

	public boolean getStatus(){
		return status;
	}

	public void setEnableGroup(){
		for(Sprinkler s: sList){
			s.setEnable();
		}
		status = true;
	}

	public void setDisableGroup(){
		for(Sprinkler s: sList){
			s.setDisable();
		}
		status = false;
	}

	public void setWaterVolume(int val){
		waterVolume=val;
	}

	public int getWaterVolume(){
		return waterVolume;
	}

	public List<Schedule> getSchedule(){
		return schedList;
	}

	public void addNewSchedule(int day, int startHour, int startMin, int endHour, int endMin){
		String id = generateSchedID();
		Schedule newSchedule = new Schedule(id,day,startHour,startMin,endHour,endMin);
		schedList.add(newSchedule);
	}

	public void deleteSchedule(String schedID){
		if(schedTaskMap.containsKey(schedID)){
			schedTaskMap.remove(schedID);
		}
		for(int i=0;i<schedList.size();i++){
			Schedule s = schedList.get(i);
			if(s.getID().equals(schedID)){
				schedList.remove(i);
			}
		}
	}

	public void addTempTask(long duration){
		TimerTask newTask = new SprinklerTask(this,duration);
		groupTimer.schedule(newTask, (long)2000);
	}

	public void addSchedTask(Schedule sched){
		TimerTask newTask = new SprinklerTask(this,sched.getDuration());
		schedTaskMap.put(sched.getID(), newTask);
		groupTimer.schedule(newTask, sched.toDate(), schedInterval);
	}

	public String generateSchedID(){
		String newSchedID = String.valueOf(groupName.charAt(0))+ schedCounter;
		schedCounter++;
		return newSchedID;
	}
}
