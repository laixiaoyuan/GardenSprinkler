package ui;

import system.Schedule;

import javax.swing.*;
import java.util.List;

/**
 * Created by Lexie on 11/17/16.
 */
public class UIController {

    JFrame frame;

    private List<Schedule> northScheduleList;
    private List<Schedule> southScheduleList;
    private List<Schedule> eastScheduleList;
    private List<Schedule> westScheduleList;

    public UIController() {
        frame = new UI();




    }

    private List<Schedule> getLocalScheduleList(String groupName) {
        switch (groupName) {
            case "NORTH":
                return northScheduleList;
            case "SOUTH":
                return southScheduleList;
            case "EAST":
                return eastScheduleList;
            case "WEST":
                return westScheduleList;
            default:
                System.out.println("Group name invalid");
                return null;
        }
    }

    // create the List<Schedule> instance from local;
    private void createLocalScheduleList(String groupName, List<Schedule> scheduleList) {
        switch (groupName) {
            case "NORTH":
                northScheduleList = scheduleList;
                break;
            case "SOUTH":
                southScheduleList = scheduleList;
                break;
            case "EAST":
                eastScheduleList = scheduleList;
                break;
            case "WEST":
                westScheduleList = scheduleList;
                break;
            default:
                System.out.println("Group name invalid");
                break;
        }
    }



    public static void main(String[] args) {
        new UIController();
    }

}
