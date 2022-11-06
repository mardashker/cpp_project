package com.example.railwaystation.controllers;

import com.example.railwaystation.classes.Game.AssetsReader;
import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Game.LevelReader;
import com.example.railwaystation.classes.Helpers.*;
import com.example.railwaystation.classes.Logic.Game;
import com.example.railwaystation.classes.Moduls.Users.PrototypeRegistry;
import com.example.railwaystation.classes.Rendering.CanvasRendering;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Level1Controller implements Initializable {
    public Canvas canvasL1;
    public CanvasRendering ctx;
   //якщо каса в стані відкритого то навіщо там bool?
   //навіщо width height?
    //зробити картинки мапою щоб діставати по ключу
   @FXML
   public void BogdanFunction(){

   }
    @FXML
    public void startGame(){
        System.out.println("we start game");
        Game game = new Game();
        Game.Init();
        System.out.println("we init game ");
        System.out.println("we InitAssets game");
    }
    public double mapValue(double a){
        return a*50;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Game.Init();
       canvasL1.setHeight(1920);
       canvasL1.setWidth(1920);
        AssetsReader.loadAssets();
        Collection<GameLevel> gameLevels = LevelReader.loadLevels();
        if(gameLevels.isEmpty())
            return;
        GameLevel gl = gameLevels.stream().collect(Collectors.toList()).get(0);
        ctx=new CanvasRendering(canvasL1);
        ctx.DrawGrid(Arrays.stream(gl.get_matrix()).toList().size() * Game.cell_width,
                Arrays.stream(gl.get_matrix()[0]).toList().size() * Game.cell_height, Game.cell_width, Game.cell_height);
        gl.get_doorsList().forEach(door -> door.DrawSprite(ctx));
        gl.get_cashRegistersList().forEach(c -> c.DrawSprite(ctx));
        gl.get_poligons().forEach(q -> q.DrawSprite(ctx));


        var doors = gl.get_doorsList();

        List<ConstUserGenerator> generators = new ArrayList<>();
        var prototypeManager = new PrototypeRegistry();
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
    }
}


