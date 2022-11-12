package com.example.railwaystation.classes.Game;

import com.example.railwaystation.classes.Helpers.Coordinates;
import com.example.railwaystation.classes.Helpers.Star.DoorPolygonResolver;
import com.example.railwaystation.classes.Logic.Game;
import com.example.railwaystation.classes.Moduls.CashRegister;
import com.example.railwaystation.classes.Moduls.Door;
import com.example.railwaystation.classes.Moduls.GameObject;
import com.example.railwaystation.classes.Rendering.ResourceManagerCashRegister;
import com.example.railwaystation.classes.Rendering.ResourceManagerDoor;
import com.example.railwaystation.classes.Rendering.ResourceManagerGameObjects;
import com.example.railwaystation.classes.Rendering.ResourceManagerQueueCell;
import javafx.scene.image.Image;
import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//клас для читання інфи для Level з файлу
public class LevelReader {
    public static int level_width = 1;
    public static int level_height = 1;

    public static Collection<GameLevel> loadLevels() {
        URL levelFolder = LevelReader.class.getClassLoader().getResource("com/example/railwaystation/assets/levels");
        File dir = new File(levelFolder.getPath());
        List<GameLevel> levels = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.isFile() && file.getPath().endsWith(".json")) {
                GameLevel lvl = loadLevel(file.getPath());
                if (lvl != null) levels.add(lvl);
            }
        }
        return levels;
    }

    @Nullable
    public static GameLevel loadLevel(String fileName) {
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
        try {
            int width = (int) (long) jsonObjectdecode.get("width");
            int height = (int) (long) jsonObjectdecode.get("height");

            level_width = width;
            level_height = height;

            CellState[][] matrix = new CellState[width][height];

            JSONArray doorsJSON = (JSONArray) jsonObjectdecode.get("doors");
            List<Door> doors = new ArrayList<>();
            for (Object o : doorsJSON) {
                Door door = doorFromJSON((JSONObject) o);
                if (door == null)
                    continue;
                matrix[(int) door.getPosition().getX()][(int) door.getPosition().getY()] = CellState.DOOR;
                doors.add(door);
            }

            JSONArray queuePoligonsJSON = (JSONArray) jsonObjectdecode.get("queues");
            List<QueuePoligon> queuePoligons = new ArrayList<>();
            for (Object o : queuePoligonsJSON) {
                QueuePoligon qPoligon = queueFromJSON((JSONArray) o);
                if (qPoligon == null)
                    continue;
                for (GameObject cell : qPoligon.get_queueCells()) {
                    matrix[(int) cell.getPosition().getX()][(int) cell.getPosition().getY()] = CellState.QUEUE;
                }
                queuePoligons.add(qPoligon);
            }

            JSONArray cashRegistersJSON = (JSONArray) jsonObjectdecode.get("cash_registers");
            List<CashRegister> cashRegisters = new ArrayList<>();
            for (Object o : cashRegistersJSON) {
                CashRegister cashRegister = cashRegisterFromJSON((JSONObject) o);
                if (cashRegister == null) {
                    continue;
                }
                matrix[(int) cashRegister.getPosition().getX()][(int) cashRegister.getPosition().getY()] = CellState.CASH_REGISTER_PART;
                cashRegisters.add(cashRegister);
            }
            if (cashRegisters.size() != queuePoligons.size())
                return null;

            return new GameLevel(doors, cashRegisters, queuePoligons, matrix);
        } catch (Exception ex) {
            return null;
        }


    }

    @Nullable
    static private Door doorFromJSON(JSONObject o) {
        try {
            Door newDoor = new Door();

            newDoor.setWidth(Game.cell_width);
            newDoor.setHeight(Game.cell_height);

            double x = (long) ((JSONObject) o).get("x");
            double y = (long) ((JSONObject) o).get("y");

            newDoor.setPosition(new Coordinates(x, y));

            // TODO: add fallback image(in case image is not present)
            Image sp = ResourceManagerDoor.getSprite((String) o.get("side"));
            newDoor.setSprite(sp);

            return newDoor;
        } catch (Exception ex) {
            return null;
        }
    }

    @Nullable
    static private CashRegister cashRegisterFromJSON(JSONObject o) {
        try {
            CashRegister cashRegister = new CashRegister();

            cashRegister.setWidth(Game.cell_width);
            cashRegister.setHeight(Game.cell_height);

            double x = (long) ((JSONObject) o).get("x");
            double y = (long) ((JSONObject) o).get("y");

            cashRegister.setPosition(new Coordinates(x, y));

            // TODO: add fallback image(in case image is not present)
            Image sp = ResourceManagerCashRegister.getSprite((String) o.get("side"));
            cashRegister.setSprite(sp);

            return cashRegister;
        } catch (Exception ex) {
            return null;
        }
    }

    @Nullable
    static private QueuePoligon queueFromJSON(JSONArray o) {
        try {
            List<GameObject> qcells = new ArrayList<>();
            for (Object o1 : (JSONArray) o) {
                JSONObject cellJSON = (JSONObject) o1;
                GameObject cell = new GameObject();

                double x = (long) cellJSON.get("x");
                double y = (long) cellJSON.get("y");

                cell.setHeight(Game.cell_height);
                cell.setWidth(Game.cell_width);
                cell.setPosition(new Coordinates(x, y));
                cell.setSprite(ResourceManagerQueueCell.getSprite((String) cellJSON.get("sidemask")));
                qcells.add(cell);
            }
            return new QueuePoligon(qcells);
        } catch (Exception ex) {
            return null;
        }
    }
}

