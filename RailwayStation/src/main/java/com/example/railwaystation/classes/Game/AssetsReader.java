package com.example.railwaystation.classes.Game;

import com.example.railwaystation.classes.Rendering.ResourceManagerCashRegister;
import com.example.railwaystation.classes.Rendering.ResourceManagerDoor;
import com.example.railwaystation.classes.Rendering.ResourceManagerQueueCell;
import com.example.railwaystation.classes.Rendering.ResourceManagerUser;
import javafx.scene.image.Image;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AssetsReader {
    public static void loadAssets(){
        BufferedReader bw = null;
        URL assetFolder = AssetsReader.class.getClassLoader().getResource("com/example/railwaystation/assets");
        try {
            bw = new BufferedReader(new FileReader(assetFolder.getPath() + "/" + "assets.json"));
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load assets");
        }
        StringBuilder text = new StringBuilder();
        bw.lines().forEach(text::append);

        JSONObject assetObjJSON = null;
        try {
            assetObjJSON = (JSONObject) new JSONParser().parse(text.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            JSONObject usersJSON = (JSONObject) assetObjJSON.get("users");
            ResourceManagerUser.setSprite("ordinary", new Image(assetFolder.getPath() + "/" + (String) usersJSON.get("ordinary")));
            ResourceManagerUser.setSprite("disabled", new Image(assetFolder.getPath() + "/" + (String) usersJSON.get("disabled")));
            ResourceManagerUser.setSprite("pregnant", new Image(assetFolder.getPath() + "/" + (String) usersJSON.get("pregnant")));
            ResourceManagerUser.setSprite("student", new Image(assetFolder.getPath() + "/" + (String) usersJSON.get("student")));
            ResourceManagerUser.setSprite("senior", new Image(assetFolder.getPath() + "/" + (String) usersJSON.get("senior")));

            JSONObject doorsJSON = (JSONObject) assetObjJSON.get("door");
            ResourceManagerDoor.setSprite("north", new Image(assetFolder.getPath() + "/" + (String) doorsJSON.get("north")));
            ResourceManagerDoor.setSprite("east", new Image(assetFolder.getPath() + "/" + (String) doorsJSON.get("east")));
            ResourceManagerDoor.setSprite("south", new Image(assetFolder.getPath() + "/" + (String) doorsJSON.get("south")));
            ResourceManagerDoor.setSprite("west", new Image(assetFolder.getPath() + "/" + (String) doorsJSON.get("west")));

            JSONObject cashRegistersJSON = (JSONObject) assetObjJSON.get("cash_register");
            ResourceManagerCashRegister.setSprite("north", new Image(assetFolder.getPath() + "/" + (String) cashRegistersJSON.get("north")));
            ResourceManagerCashRegister.setSprite("east", new Image(assetFolder.getPath() + "/" + (String) cashRegistersJSON.get("east")));
            ResourceManagerCashRegister.setSprite("south", new Image(assetFolder.getPath() + "/" + (String) cashRegistersJSON.get("south")));
            ResourceManagerCashRegister.setSprite("west", new Image(assetFolder.getPath() + "/" + (String) cashRegistersJSON.get("west")));

            JSONObject queueCellsJSON = (JSONObject) assetObjJSON.get("queue_cells");
            ResourceManagerQueueCell.setSprite("0000", new Image(assetFolder.getPath() + "/" + (String) queueCellsJSON.get("0000")));
            ResourceManagerQueueCell.setSprite("0001", new Image(assetFolder.getPath() + "/" + (String) queueCellsJSON.get("0001")));
            ResourceManagerQueueCell.setSprite("0010", new Image(assetFolder.getPath() + "/" + (String) queueCellsJSON.get("0010")));
            ResourceManagerQueueCell.setSprite("0011", new Image(assetFolder.getPath() + "/" + (String) queueCellsJSON.get("0011")));
            ResourceManagerQueueCell.setSprite("0100", new Image(assetFolder.getPath() + "/" + (String) queueCellsJSON.get("0100")));
            ResourceManagerQueueCell.setSprite("0101", new Image(assetFolder.getPath() + "/" + (String) queueCellsJSON.get("0101")));
            ResourceManagerQueueCell.setSprite("0110", new Image(assetFolder.getPath() + "/" + (String) queueCellsJSON.get("0110")));
            ResourceManagerQueueCell.setSprite("0111", new Image(assetFolder.getPath() + "/" + (String) queueCellsJSON.get("0111")));
            ResourceManagerQueueCell.setSprite("1000", new Image(assetFolder.getPath() + "/" + (String) queueCellsJSON.get("1000")));
            ResourceManagerQueueCell.setSprite("1001", new Image(assetFolder.getPath() + "/" + (String) queueCellsJSON.get("1001")));
            ResourceManagerQueueCell.setSprite("1010", new Image(assetFolder.getPath() + "/" + (String) queueCellsJSON.get("1010")));
            ResourceManagerQueueCell.setSprite("1011", new Image(assetFolder.getPath() + "/" + (String) queueCellsJSON.get("1011")));
            ResourceManagerQueueCell.setSprite("1100", new Image(assetFolder.getPath() + "/" + (String) queueCellsJSON.get("1100")));
            ResourceManagerQueueCell.setSprite("1101", new Image(assetFolder.getPath() + "/" + (String) queueCellsJSON.get("1101")));
            ResourceManagerQueueCell.setSprite("1110", new Image(assetFolder.getPath() + "/" + (String) queueCellsJSON.get("1110")));
            ResourceManagerQueueCell.setSprite("1111", new Image(assetFolder.getPath() + "/" + (String) queueCellsJSON.get("1111")));
        }
        catch (Exception ex){
            System.out.println("Some icons does not exist or cannot be loaded");
            System.out.println(ex.getMessage());
        }
    }
}
