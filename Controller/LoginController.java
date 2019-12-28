package Controller;


import Model.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.ActionListener;

public class LoginController {

    private  GomokuGame targetGomoku;

    ActionListener listener1;
    ActionListener listener2;

    ObservableList<String> methods= FXCollections.observableArrayList("Standard","Free","Renju","Omok","Caro");
    ObservableList<String> colors= FXCollections.observableArrayList("Black","White");
    ObservableList<String> sizes = FXCollections.observableArrayList("15x15","19x19");

    @FXML private javafx.scene.control.Button eBottim;

    @FXML private javafx.scene.control.TextField playerf;

    @FXML private javafx.scene.control.TextField players;

    @FXML
    private ChoiceBox choice;

    @FXML
    private ChoiceBox choicecol1;

    @FXML
    private ChoiceBox choicecol2;

    @FXML
    private ChoiceBox choicegridSize;


    @FXML
    private void initialize(){

        choice.setItems(methods);
        choicecol1.setItems(colors);
        choicecol2.setItems(colors);
        choicegridSize.setItems(sizes);

    }



    @FXML
    public void startGame(){

        System.out.println("start game!");
        boolean isMyComboBoxEmpty = choice.getSelectionModel().isEmpty();
        boolean isMyCol1Empty = choicecol1.getSelectionModel().isEmpty();
        boolean isMyCol2Empty = choicecol2.getSelectionModel().isEmpty();
        boolean isGridSizeEmpty = choicegridSize.getSelectionModel().isEmpty();

        if (!isMyComboBoxEmpty && !isMyCol1Empty && !isMyCol2Empty && !isGridSizeEmpty){
            if (!(playerf.getText().equals("")) && !(players.getText().equals(""))) {
                if(choicecol1.getSelectionModel().getSelectedItem().toString()!=choicecol2.getSelectionModel().getSelectedItem().toString()) {
                    Player p1 = new Player(playerf.getText(), choicecol1.getSelectionModel().getSelectedItem().toString());
                    Player p2 = new Player(players.getText(), choicecol2.getSelectionModel().getSelectedItem().toString());
                    String gridSizeString = choicegridSize.getSelectionModel().getSelectedItem().toString();
                    int gridSize;
                    if (gridSizeString.equals("15x15")) gridSize = 15;
                    else gridSize = 19;
                    startGameUsingFactory(p1, p2, gridSize, choice.getSelectionModel().getSelectedItem().toString());
                    Stage stage = (Stage) eBottim.getScene().getWindow();
                    stage.close();

                    Stage mainStage = new Stage(StageStyle.DECORATED);
                    GomokuBoard myBoard = new GomokuBoard(mainStage,targetGomoku);

                    //myBoard.start(mainStage); //decidere se chiamare lo start method qui o nel constructor di GomokuBoard


//                    Parent root = null;
//                    try {
//                        root = FXMLLoader.load(getClass().getResource("../View/boardView.fxml"));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    mainStage.setTitle("Gomoku Board");
//                    mainStage.setScene(new Scene(root, 500, 450));
//                    mainStage.show();
                }
                else{
                    Alert alertColors = new Alert(Alert.AlertType.ERROR);
                    alertColors.setTitle("ERROR - Colors");
                    alertColors.setHeaderText(null);
                    alertColors.setContentText("Choose different colors");
                    alertColors.showAndWait();
                }
            }
            else {
                Alert alertNames = new Alert(Alert.AlertType.ERROR);
                alertNames.setTitle("ERROR - Missing values");
                alertNames.setHeaderText(null);
                alertNames.setContentText("Insert the name of both players");
                alertNames.showAndWait();
            }
        }else{
            System.out.println("Please select the type of Gomoku");
        }



    }

    @FXML
    public void close(){
        Stage stage = (Stage) eBottim.getScene().getWindow();
        // do what you have to do
        stage.close();
    }


    public int startGameUsingFactory(Player p1, Player p2, int gridSize, String game){
        this.targetGomoku = GomokuFactory.getGame(game).orElseThrow(() -> new IllegalArgumentException("Invalid operator"));
        this.targetGomoku.setPlayers(p1, p2);
        this.targetGomoku.setSize(gridSize);
        return targetGomoku.initGame();

    }

}
