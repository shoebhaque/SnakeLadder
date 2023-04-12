package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize = 40, width=10,height=10;        //titlesize means size of small boxes

    public static final int buttonLine = height*tileSize + 50;        //it will create button just below the all line
    public static final int infoLine =  buttonLine-30;               // it is for comment line we give above button

    private static Dice dice = new Dice();
    private Player playerOne, playerTwo;

    private boolean gameStarted =false, playerOneTurn = false, playerTowTurn = false;

    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize,height*tileSize + 100);

        //These loop is helpul to create 10*10 grid
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }

        }
       //these will give the path of image
        Image img = new Image("C:\\Users\\shoeb\\OneDrive\\Desktop\\SnakeLadder\\SnakeLadder\\src\\main\\istockphoto-455302535-1024x1024 (1).png");
       //these will set image on board and view
        ImageView board = new ImageView();
        board.setImage(img);
        //these will adjust your image acroding to your board
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);

        //Button
        Button playerOneButton = new Button("Player One");
        Button playerTwoButton = new Button("Player Two");
        Button startButton = new Button("Start");

        //it will create button
        playerOneButton.setTranslateY(buttonLine);    //it will display button on the button line
        playerOneButton.setTranslateX(20);            //Here we adjust button according our board
        playerOneButton.setDisable(true);
        playerTwoButton.setTranslateY(buttonLine);    //it will display button on the button line
        playerTwoButton.setTranslateX(300);           //Here we adjust button according our board
        playerTwoButton.setDisable(true);
        startButton.setTranslateY(buttonLine);        //it will display button on the button line
        startButton.setTranslateX(170);               //Here we adjust button according our board

        //Displaying comment above the button
        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label diceLabel = new Label("Start the Game");

        playerOneLabel.setTranslateY(infoLine);   //it will display button on the button line
        playerOneLabel.setTranslateX(20);         //Here we adjust button according our board
        playerTwoLabel.setTranslateY(infoLine);   //it will display button on the button line
        playerTwoLabel.setTranslateX(300);        //Here we adjust button according our board
        diceLabel.setTranslateY(infoLine);        //it will display button on the button line
        diceLabel.setTranslateX(170);             //Here we adjust button according our board

     //Here we Diciding size of coin ,coin colour, and the name of the player
      playerOne = new Player(tileSize, Color.BLACK, " Amit");
      playerTwo = new Player(tileSize-5, Color.WHITE, " Sumit");

      //Player Action
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            //Here we are working on Player one button after one it will disable after started game it will move turn by turn
            public void handle(ActionEvent actionEvent) {
               if(gameStarted){
                   if(playerOneTurn){
                       int diceValue = dice.getRolledDiceValue();
                       diceLabel.setText("Dice Value : " + diceValue);
                       playerOne.movePlayer(diceValue);
                      //Winning condition
                       if (playerOne.isWinner()){
                           diceLabel.setText("Winner is" + playerOne.getName());
                           playerOneTurn = false;
                           playerOneButton.setDisable(true);
                           playerOneLabel.setText("");

                           playerTowTurn = true;
                           playerTwoButton.setDisable(true);
                           playerTwoLabel.setText("");


                           startButton.setDisable(false);
                           startButton.setText("Restart");
                           gameStarted = false;
                       }
                       else{
                           playerOneTurn = false;
                           playerOneButton.setDisable(true);
                           playerOneLabel.setText("");

                           playerTowTurn = true;
                           playerTwoButton.setDisable(false);
                           playerTwoLabel.setText("Your Turn" + playerTwo.getName());
                       }

                   }
               }

            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTowTurn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : " + diceValue);
                        playerTwo.movePlayer(diceValue);
                        //Winning condition
                        if (playerTwo.isWinner()){
                            diceLabel.setText("Winner is" + playerTwo.getName());
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTowTurn = true;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");


                            startButton.setDisable(false);
                            startButton.setText("Restart");
                        }
                        else {
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your Turn" + playerOne.getName());

                            playerTowTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                        }


                    }
                }

            }
        });

           startButton.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent actionEvent) {
                   gameStarted = true;
                   diceLabel.setText("Game Started");
                   startButton.setDisable(true);
                   playerOneTurn = true;
                   playerOneLabel.setText("Your Turn" + playerOne.getName());
                   playerOneButton.setDisable(false);
                   playerOne.startingPosition();

                   playerTowTurn = false;
                   playerTwoLabel.setText("");
                   playerTwoButton.setDisable(true);
                   playerTwo.startingPosition();

               }
           });


        //here we adding board,playeOneButton,PlayerTwoButton,startButton
        //it will helping to adding the comment ont the board
        root.getChildren().addAll(board,
                                  playerOneButton,playerTwoButton,startButton,
                                  playerOneLabel,playerTwoLabel,diceLabel,
                                  playerOne.getCoin(),playerTwo.getCoin());

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SnakeLadder.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}