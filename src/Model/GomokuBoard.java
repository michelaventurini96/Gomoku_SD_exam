package Model;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import Controller.BoardController;

public class GomokuBoard {

    private final BoardController controller;
    private final StackPane sp_mainlayout;
    static private final int gridSize  = GomokuGame.getGridDim();
    private  String nameg;

    public GomokuBoard(Stage mainStage,GomokuGame n) {
        this.controller = new BoardController(gridSize,n);
        this.sp_mainlayout = new StackPane();
        this.sp_mainlayout.getChildren().add(this.controller);
        this.nameg=n.GetName();
        this.start(mainStage);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("GOMOKU version: "+nameg);
        primaryStage.setScene(new Scene(this.sp_mainlayout, Board.APPLICATION_WIDTH, Board.APPLICATION_HEIGHT));
        primaryStage.show();
    }

}
