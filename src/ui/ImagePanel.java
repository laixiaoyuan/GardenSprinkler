package ui;

import javax.swing.*;
import javax.xml.bind.JAXBPermission;
import java.awt.*;
import java.util.Map;

/**
 * Created by Lexie on 12/2/16.
 */
public class ImagePanel extends JPanel{
    JLabel picLabel;
    ImageIcon imageOn;
    ImageIcon imageOff;
    Map<String, Boolean[]> northSprinklerStatusMap;
    Map<String, Boolean[]> southSprinklerStatusMap;
    Map<String, Boolean[]> eastSprinklerStatusMap;
    Map<String, Boolean[]> westSprinklerStatusMap;

    public ImagePanel() {

        super();
        setLayout(new GridLayout(3, 3));

        imageOn = new ImageIcon(getClass().getResource("statusON.ico"));
        imageOff = new ImageIcon(getClass().getResource("statusOFF.png"));

//        createImageBasedOnStatus();



    }

    public void getSprinklerStatusMap(String groupName, Map<String, Boolean[]> sprinklerStatusMap) {
        switch (groupName) {
            case "NORTH":
                northSprinklerStatusMap = sprinklerStatusMap;
                break;
            case "SOUTH":
                southSprinklerStatusMap = sprinklerStatusMap;
                break;
            case "EAST":
                eastSprinklerStatusMap = sprinklerStatusMap;
                break;
            case "WEST":
                westSprinklerStatusMap = sprinklerStatusMap;
                break;
            default:
                System.out.println("Invalid input group name");
        }
    }

    private JPanel createGroupImageBasedOnStatus(String groupName) {
        // north:
        for (Map.Entry<String, Boolean[]> entry : northSprinklerStatusMap.entrySet()) {
            if (entry.getValue()[0]) {  // == true
                JLabel jLabel = new JLabel(new ImageIcon(imageOn));
            }
        }





    }



}
