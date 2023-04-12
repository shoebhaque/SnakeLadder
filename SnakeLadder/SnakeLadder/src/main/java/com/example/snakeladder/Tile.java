package com.example.snakeladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    Tile(int tileSize){
        //these will create one single box size and colour of these box border colur
        setWidth(tileSize);
        setHeight(tileSize);
        setFill(Color.AZURE);
        setStroke(Color.BLACK);
    }
}
