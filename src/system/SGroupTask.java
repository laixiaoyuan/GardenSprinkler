package system;

import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;

import data.DataFile;

public class SGroupTask extends TimerTask {
	
	SprinklerGroup group;
	long duration;
	
	public SGroupTask(SprinklerGroup group, long duration){
		this.group = group;
		this.duration = duration;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		Date startTime = new Date();
		group.setEnableGroup();
		while(group.getStatus()){
			try {
				Thread.sleep(duration);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		group.setDisableGroup();
		Date currTime = new Date();
		DataFile f = new DataFile();
		try {
			f.writeData(group.getName(), currTime, 
					(int)(group.getSprinklerList().size() * group.getWaterVolume()
							* (currTime.getTime()-startTime.getTime()) / 1000));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
