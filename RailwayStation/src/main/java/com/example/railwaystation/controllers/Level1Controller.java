package com.example.railwaystation.controllers;

import com.example.railwaystation.classes.Helpers.Coordinates;
import com.example.railwaystation.classes.Moduls.CashRegister;
import com.example.railwaystation.classes.Moduls.Door;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.net.URL;
import java.util.ResourceBundle;

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
        Image img = (new Image("file:src/main/resources/com/example/railwaystation/img/icons/door1.png"));
        Image img2= (new Image("file:src/main/resources/com/example/railwaystation/img/icons/cash.png"));
        Image img3= (new Image("file:src/main/resources/com/example/railwaystation/img/icons/nocash.png"));

        Door door_obj = new Door(new Coordinates(3,16), 200, 200,img, 0, true);
        Door door_obj2 = new Door(new Coordinates(6,16), 200, 200,img, 0, true);
        Door door_obj3 = new Door(new Coordinates(9,16), 200, 200,img, 0, true);


        canvasL1.getGraphicsContext2D().drawImage(door_obj.getSprite(),mapValue(door_obj.getPosition().getX()),mapValue(door_obj.getPosition().getY()));
        canvasL1.getGraphicsContext2D().drawImage(door_obj.getSprite(),mapValue(door_obj2.getPosition().getX()),mapValue(door_obj.getPosition().getY()));
        canvasL1.getGraphicsContext2D().drawImage(door_obj.getSprite(),mapValue(door_obj3.getPosition().getX()),mapValue(door_obj.getPosition().getY()));


        CashRegister cash_obj=new CashRegister(new Coordinates(4,0),200,200,img2,0,null, null,true);
        CashRegister cash_obj2=new CashRegister(new Coordinates(7,0),200,200,img3,0,null, null,true);

        canvasL1.getGraphicsContext2D().drawImage(cash_obj.getSprite(),mapValue(cash_obj.getPosition().getX()),mapValue(cash_obj.getPosition().getY()));
        canvasL1.getGraphicsContext2D().drawImage(cash_obj2.getSprite(),mapValue(cash_obj2.getPosition().getX()),mapValue(cash_obj2.getPosition().getY()));

    }

}
