package com.company;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
        Field.Init();
        Flower c=new Flower();
        c.x=6;
        c.y=7;
        Field.Board[6][7].AddOrganism(c);
        Flower z=new Flower();
        z.x=7;
        z.y=8;
        Field.Board[7][8].AddOrganism(z);
        Emulation.Add(c);
        Emulation.Add(z);
        Emulation.Step();
    }


}

