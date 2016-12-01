package system;

//read temperature periodically
//send to system monitor
public class Sensor {

    String sensorID;
    Temperature currTemp;

    public Temperature getTemperature(){
        return currTemp;
    }

}
