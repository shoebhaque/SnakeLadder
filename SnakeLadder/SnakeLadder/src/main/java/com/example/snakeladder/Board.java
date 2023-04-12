package com.example.snakeladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer,Integer>> positionCordinate;   //idices of number have some cordinate For X & Y positon

    ArrayList<Integer> snakeLadderPosition;

    public Board(){
        //here we initialize the object
        positionCordinate = new ArrayList<>();
        populatePositionCoordinates();
        populateSnakeLadder();
    }
    private void populatePositionCoordinates(){
        positionCordinate.add(new Pair<>(0,0));      //dummy value
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                //we need to genrate x Coordinate
                int xCord =0;
                if(i%2==0){
                    //if we itrate in j loop we x will increases
                    xCord = j * SnakeLadder.tileSize + SnakeLadder.tileSize/2;
                }
                else{
                    //if x is increse decreses the x value
                     xCord = (SnakeLadder.tileSize * SnakeLadder.height) -(j * SnakeLadder.tileSize) - (SnakeLadder.tileSize/2);

                }
                //We need to genrate y Cordinate
                //Here we are doing fix our tile size and subtract tile size with value of i and subtract reduce tileSize
                int yCord = (SnakeLadder.tileSize * SnakeLadder.height) -(i * SnakeLadder.tileSize) - (SnakeLadder.tileSize/2);
               //Here we adding yCord  & xCordto our position cordinate
                positionCordinate.add(new Pair<>(xCord,yCord));

            }

        }
    }

    private void populateSnakeLadder(){
        snakeLadderPosition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeLadderPosition.add(i);   //i have mapped each index with the same value
        }
        //snakeLadderPosition.set(1,38);
        snakeLadderPosition.set(4,14);
        snakeLadderPosition.set(9,31);
        snakeLadderPosition.set(17,7);
        snakeLadderPosition.set(21,42);
        snakeLadderPosition.set(28,84);
        snakeLadderPosition.set(51,67);
        snakeLadderPosition.set(54,34);
        snakeLadderPosition.set(62,19);
        snakeLadderPosition.set(64,60);
        snakeLadderPosition.set(71,91);
        snakeLadderPosition.set(80,100);
        snakeLadderPosition.set(87,24);
        snakeLadderPosition.set(93,73);
        snakeLadderPosition.set(95,75);
        snakeLadderPosition.set(98,79);

    }

    public int getNewPosition(int currentPosition){
        if(currentPosition>0 && currentPosition<=100){
            return snakeLadderPosition.get(currentPosition);
        }
        return -1;
    }


    int getXCoordinate(int position){
        if(position>=1 && position<=100)     //these x coridinarte for player for moving cordinate
            return positionCordinate.get(position).getKey();
        return -1;
    }

    int getYCoordinate(int position){
        if(position>=1 && position<=100)
            return positionCordinate.get(position).getValue();
        return -1;
    }

   public static void main(String[] args) {
        Board board = new Board();
        for (int i = 0; i < board.positionCordinate.size(); i++) {
            System.out.println(i + " $ x :" + board.positionCordinate.get(i).getKey() +
                                " Y " + board.positionCordinate.get(i).getValue());
        }
    }

}
