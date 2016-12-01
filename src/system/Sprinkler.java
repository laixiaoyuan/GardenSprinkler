package system;

public class Sprinkler {
	
	String sID;
	boolean isOn;
	boolean isFunctional;
	
	public Sprinkler(String sID){
		this.sID=sID;
		isOn = false;
		isFunctional = true;
	}
	
	public String getID(){
		return sID;
	}
	
	public void setEnable(){
		isOn = true && isFunctional;
	}
	
	public void setDisable(){
		isOn = false;
	}
	
	public void setFunction(boolean funcStat){
		isFunctional = funcStat;
	}
	
	public boolean getWorkStatus(){
		return isOn;
	}
	
	public boolean getFuncStatus(){
		return isFunctional;
	}	
}
