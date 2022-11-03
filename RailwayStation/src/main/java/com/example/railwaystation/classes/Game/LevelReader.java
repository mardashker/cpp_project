package com.example.railwaystation.classes.Game;

import com.example.railwaystation.classes.Helpers.Coordinates;
import com.example.railwaystation.classes.Logic.Game;
import com.example.railwaystation.classes.Moduls.CashRegister;
import com.example.railwaystation.classes.Moduls.Door;
import com.example.railwaystation.classes.Moduls.GameObject;
import com.example.railwaystation.classes.Rendering.ResourceManagerGameObjects;
import javafx.scene.image.Image;
import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

//клас для читання інфи для Level з файлу
public class LevelReader {
    @Nullable
    static GameLevel loadLevel(String fileName)  {
        BufferedReader bw = null;
        try {
            bw = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            return null;
        }
        StringBuilder text = new StringBuilder();
        bw.lines().forEach(text::append);

        JSONObject jsonObjectdecode = null;
        try {
            jsonObjectdecode = (JSONObject) new JSONParser().parse(text.toString());
        } catch (ParseException e) {
            return null;
        }

        int width = (int) jsonObjectdecode.get("width");
        int height = (int) jsonObjectdecode.get("height");

        // Read doors
        JSONArray doorsJSON = (JSONArray) jsonObjectdecode.get("doors");
        List<Door> doors = doorsJSON.stream().map(d -> {
            JSONObject dJSON = (JSONObject) d;
            Door newDoor = new Door();

            newDoor.setWidth(Game.cell_width);
            newDoor.setHeight(Game.cell_height);

            Double x = (Double) dJSON.get("x");
            Double y = (Double) dJSON.get("y");

            Double angle = (Double) dJSON.get("angle");

            newDoor.setPosition(new Coordinates(x,y,angle));
            Image sp = ResourceManagerGameObjects.getSprite("game");
            newDoor.setSprite(sp);
            // TODO: add fallback image(in case image is not present)
            return newDoor;
        }).toList();

        // TODO: Read queues
        List<QueuePoligon> queuePoligons = null;
//        String xd = (String) jsonObjectdecode.get("doors").get("x");
//        String yd = (String) jsonObjectdecode.get("doors").get("y");
//        String sided = (String) jsonObjectdecode.get("doors").get("side");

        // TODO: Read cash registers
        List<QueuePoligon> cashRegisters = null;
//        JSONArray cashRegistersJSON = (JSONArray) jsonObjectdecode.get("cash_register");
//        List<CashRegister> cashRegisters = cashRegistersJSON.stream().map(cr -> {
//            CashRegister newCashRegister =
//        })



        // TODO: init matrix (width times height) and fill it with data
        // Collection<Collection<CellState>>
        GameLevel newLevel = new GameLevel();
        return newLevel;
    }
    static void saveLevel(Door doorobj, CashRegister cashRegister, List<GameObject> queueCellList ) {
//
//        JSONObject file = new JSONObject();
//
//        file.put("doors", "x:": doorobj.getCoordinates().getX());
//        file.put("Roll No.", new Integer(1704310046));
//        file.put("Tution Fees", new Double(65400));
//
//        // To print in JSON format.
//        System.out.print(file);
    }
}
