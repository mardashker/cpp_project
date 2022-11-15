package com.example.railwaystation.controllers;

import com.example.railwaystation.App;
import com.example.railwaystation.classes.Game.AssetsReader;
import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Game.LevelReader;
import com.example.railwaystation.classes.Helpers.ConstUserGenerator;
import com.example.railwaystation.classes.Helpers.Coordinates;
import com.example.railwaystation.classes.Helpers.Star.DoorPolygonResolver;
import com.example.railwaystation.classes.Interfaces.Generator;
import com.example.railwaystation.classes.Logic.Game;
import com.example.railwaystation.classes.Logic.GameLoop;
import com.example.railwaystation.classes.Logic.Handlers.ClickOnCanvasHandler;
import com.example.railwaystation.classes.Moduls.Users.PrototypeRegistry;
import com.example.railwaystation.classes.Moduls.Users.User;
import com.example.railwaystation.classes.Rendering.Camera2D;
import com.example.railwaystation.classes.Rendering.CanvasRendering;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Level3Controller implements Initializable {
    public Text usercountText;
    public Text maxuserText;

    public Canvas canvasL1;
    private double mouseX;
    private double mouseY;

    Thread thread ;
    AtomicReference<Double> amount_people;
    public CanvasRendering ctx;
    public Camera2D _camera;
    public Canvas canvasinfo;

    public Spinner Amount;
    public GameLoop loop;
    public int maxCount = 40;

    private Collection<User> userCollection = new ArrayList<>();
    @FXML
    private void backToMain() throws IOException {
        App.setRoot("chooselevel_other");
    }
    public void PaintEverything() {
        AssetsReader.loadAssets();
        URL assetFolder = Level1Controller.class.getClassLoader().getResource("com/example/railwaystation/assets");
        Collection<GameLevel> gameLevels = LevelReader.loadLevels();
        if (gameLevels.isEmpty())
            return;
        GameLevel gl = gameLevels.stream().collect(Collectors.toList()).get(0);
        ctx = new CanvasRendering(canvasL1);
        ctx.DrawGrid(Arrays.stream(gl.get_matrix()).toList().size() * Game.cell_width,
                Arrays.stream(gl.get_matrix()[0]).toList().size() * Game.cell_height, Game.cell_width, Game.cell_height);
        gl.get_doorsList().forEach(door -> door.DrawSprite(ctx));
        gl.get_cashRegistersList().forEach(c -> c.DrawSprite(ctx));
        gl.get_poligons().forEach(q -> q.DrawSprite(ctx));
    }

    @FXML
    public void startGame() throws IOException {

        try {
            thread = new Thread(loop);
            thread.start();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public double mapValue(double a) {
        return a * 50;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Game.Init();
        canvasL1.setWidth(Game.cell_width * LevelReader.level_width);
        canvasL1.setHeight(Game.cell_height * LevelReader.level_height);


        AssetsReader.loadAssets();
        Collection<GameLevel> gameLevels = LevelReader.loadLevels();
        if (gameLevels.isEmpty())
            return;
        GameLevel gl = gameLevels.stream().collect(Collectors.toList()).get(3);
        _camera = new Camera2D(new Coordinates(0,0), 1);
        ctx = new CanvasRendering(canvasL1);
        ctx.set_camera(_camera);
        ctx.DrawGrid(Arrays.stream(gl.get_matrix()).toList().size() * Game.cell_width,
                Arrays.stream(gl.get_matrix()[0]).toList().size() * Game.cell_height, Game.cell_width, Game.cell_height);


        var doors = gl.get_doorsList();
        List<Generator> generators = new ArrayList<>();

        var prototypeManager = new PrototypeRegistry();
        doors.stream().forEach(door -> {
            try {
                door.setState(true);
                generators.add(new ConstUserGenerator(door));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Game game = new Game();
        Game.resolver = DoorPolygonResolver.calculate(gl);
        Game.currentLevel = gl;
        GameLoop loop = new GameLoop(new Game(), generators, ctx, _camera);
        this.loop = loop;
        /* click handler to show queue info */
        canvasL1.addEventHandler(MouseEvent.MOUSE_CLICKED, new ClickOnCanvasHandler(canvasinfo,_camera));

        canvasL1.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        canvasL1.setOnMouseDragged(e -> {
            if(e.getButton() == MouseButton.PRIMARY){
                double deltaX = e.getX() - mouseX;
                double deltaY = e.getY() - mouseY;
                mouseX = e.getX();
                mouseY = e.getY();
                _camera.set_position(_camera.get_position().add(new Coordinates(-deltaX,-deltaY)));
            }
        });
        canvasL1.addEventHandler(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent scrollEvent) {
                double deltaY = scrollEvent.getDeltaY() * scrollEvent.getMultiplierY();
                double zoom_factor = 0.25;
                if(deltaY > 0)
                    _camera.set_zoom(_camera.get_zoom() + zoom_factor);
                else if(deltaY < 0)
                    _camera.set_zoom((Math.max(_camera.get_zoom() - zoom_factor, 0.5)));
            }
        });

        //Візуальна частина --------------------------------------------------------------------------------------------------------
        SpinnerValueFactory<Integer> valueFactoryAmount = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 40, 40, 1);
        Amount.setValueFactory(valueFactoryAmount);
        //------------------------------------------------------------------------------------------------------------------------

        maxuserText.setText(String.valueOf(Game.getMaxUserCount()));

        Amount.valueProperty().addListener((ChangeListener<Integer>) (observableValue, oldValue, newValue) -> {
            maxCount = (newValue);
            Game.setMaxUserCount(maxCount);
            maxuserText.setText(String.valueOf(Game.getMaxUserCount()));

        });
    }
}
