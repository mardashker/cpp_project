package com.example.railwaystation.controllers;

import com.example.railwaystation.classes.Game.AssetsReader;
import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Game.LevelReader;
import com.example.railwaystation.classes.Helpers.*;
import com.example.railwaystation.classes.Logic.Game;
import com.example.railwaystation.classes.Moduls.CashRegister;
import com.example.railwaystation.classes.Moduls.Door;
import com.example.railwaystation.classes.Moduls.Users.UserPrototypeManager;
import com.example.railwaystation.classes.Rendering.CanvasRendering;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Level1Controller implements Initializable {
    public Canvas canvasL1;
   //якщо каса в стані відкритого то навіщо там bool?
   //навіщо width height?
    //зробити картинки мапою щоб діставати по ключу
    public double mapValue(double a){
        return a*50;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AssetsReader.loadAssets();
        URL assetFolder = Level1Controller.class.getClassLoader().getResource("com/example/railwaystation/assets");
        Collection<GameLevel> gameLevels = LevelReader.loadLevels();
        if(gameLevels.isEmpty())
            return;
        GameLevel gl = gameLevels.stream().collect(Collectors.toList()).get(0);
        CanvasRendering ctx=new CanvasRendering(canvasL1);
        gl.get_doorsList().forEach(door -> door.DrawSprite(ctx));
        gl.get_cashRegistersList().forEach(c -> c.DrawSprite(ctx));
        gl.get_poligons().forEach(q -> q.DrawSprite(ctx));




        var doors = gl.get_doorsList();

        List<ConstUserGenerator> generators = new ArrayList<>();
        try {
            var prototypeManager = new UserPrototypeManager();
            doors.stream().forEach(door -> {
                try {
                    door.setState(true);
                    generators.add(new ConstUserGenerator(door));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            for(int i=0; i < 4; i++){
                generators.stream().forEach(generator ->{
                    double temp = Math.random();
                    var user = generator.generateUser();
                    if(user != null) {
                        double tmpx = user.getPosition().getX() + temp;
                        double tmpy = user.getPosition().getY() + temp;
                        user.setPosition(new Coordinates(user.getPosition().getX() + temp, user.getPosition().getY() + temp));
                        ctx.DrawSprite(user.getPosition(), Game.cell_width, Game.cell_height, 0, user.getSprite());
                    }
                } );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


