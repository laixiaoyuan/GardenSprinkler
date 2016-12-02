package system;

import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;

import data.DataFile;

public class SprinklerTask extends TimerTask {
	
	SprinklerGroup group;
	long duration;
	
	public SprinklerTask(SprinklerGroup group, long duration){
		this.group = group;
		this.duration = duration;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		group.setEnableGroup();
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		group.setDisableGroup();
		DataFile f = new DataFile();
		try {
			f.writeData(group.getName(), new Date(), 
					(int)(group.getSprinklerList().size() * group.getWaterVolume()* duration / 1000));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
