package com.example.railwaystation.refactored_classes.UI.CanvasManager;

import com.example.railwaystation.classes.Logic.Game;
import com.example.railwaystation.refactored_classes.Models.CashRegister;
import com.example.railwaystation.classes.Rendering.CanvasRendering;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.Optional;

public class CanvasInfo {
    public CanvasRendering ctx_info;
    public Canvas canvasinfo;
    private boolean showInfo = false;

    public CanvasInfo(Canvas canvasinfo) {
        this.canvasinfo = canvasinfo;
    }

    public void ShowQueueInfo(Optional<CashRegister> cashRegistryOpt) {
        ctx_info = new CanvasRendering(canvasinfo);

        if (showInfo) {
            showInfo = false;
            cleanCanvas();
        } else {
            setCanvas();
            showInfo = true;
            GraphicsContext gc = canvasinfo.getGraphicsContext2D();
            gc.setFill(Color.rgb(182, 144, 106));
            gc.fillRect(0, 0, canvasinfo.getWidth(), canvasinfo.getHeight());
            gc.setTextAlign(TextAlignment.LEFT);
            gc.setFill(Color.BLACK);
            gc.setFont(new Font("Georgia Bold", 20));
            gc.fillText(
                    "    User Type              User Info                                                            Tickets Info                                                  ",
                    10, 25);
            gc.fillText(
                    "---------------------------------------------------------------------------------------------------------------------------------------",
                    10, 35);
            gc.setFont(new Font("Georgia", 10));

            gc.fillText(
                    Game.getShowQueueDetailsString(cashRegistryOpt.get().getOurQueue()), 10, 55);
        }


    }

    public void cleanCanvas() {
        canvasinfo.getGraphicsContext2D().clearRect(0, 0, canvasinfo.getWidth(), canvasinfo.getHeight());
        canvasinfo.setHeight(1);
        canvasinfo.setWidth(1);
        canvasinfo.setLayoutX(1);
        canvasinfo.setLayoutY(1);
    }

    public void setCanvas() {
        canvasinfo.setHeight(1000);
        canvasinfo.setWidth(1050);
        canvasinfo.setLayoutX(350);
        canvasinfo.setLayoutY(10);
    }
}
