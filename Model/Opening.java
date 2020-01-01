package Model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Opening {
    private Player player1;
    private Player player2;
    private String method;
    private int numMoves=0;
    private Boolean over;

    public Opening(Player p1, Player p2,String m){
        this.player1=p1;
        this.player2=p2;
        this.method=m;
        System.out.println(this.method);
        if(this.method=="Standard") this.numMoves=2;
        else this.numMoves=3;

    };

    public int getNummoves(){return this.numMoves;}


    public void calling(int c){

        if(this.method.equals("Standard")){
            this.OpenStd();
        }
        if(this.method.equals("Pro")) this.Pro();
        if(this.method.equals("LongPro")) this.LongPro();
        if(this.method.equals("Swap")) this.Swap();

        if(this.method.equals("Swap2") && c!=5){
            this.over=this.Swap2();
        }
        if(this.method.equals("Swap2") && c==5 && !this.over.booleanValue()) this.Swap2_1();

    }

    public void CheckError(){

        if(!player1.CheckAllMoves(player2)){
            throw new Error("place stones in different places");
        }else{
            System.out.println("end of opening moves");
        }
    };

    public void CheckinError(Player p, Piece m){
        if(!p.CheckinMoves(m)){
            throw new Error(p.getName()+" place stones in different places");
        }
    };
/*
    Black can place anywhere, white secondly can place anywhere but on black spot.
 */
    public void OpenStd(){

        CheckError();
    };

    private Player GetBlack(){
        if(player1.getColor().get()==1) return player1;
        else return player2;
    }

    private Player GetWhite(){
        if(player1.getColor().get()==2) return player1;
        else return player2;
    }

    private void utilityPro(int c){
        int x0=GetBlack().getPositions().get(0).getX();
        int y0=GetBlack().getPositions().get(0).getY();
        int x=GetBlack().getPositions().get(1).getX();
        int y=GetBlack().getPositions().get(1).getY();
        if(x>x0-c && x<x0+c && y>y0-c && y<y0+c) {
            GetBlack().removeposition(1);
            throw new Error("place black stone out of a "+ c+"x"+c+" square from the center");
        }
        CheckError();
    };

    public void Pro(){
        this.utilityPro(5);
    };

    public void LongPro(){
        this.utilityPro(7);
    };


    public void utilitySwap(){
        player1.addposition(player2.getPositions().get(0));
        player2.addposition(player1.getPositions().get(0));
        player2.addposition(player1.getPositions().get(1));
        player1.removeposition(1);
        player1.removeposition(0);
        player2.removeposition(0);
        if(player1.equals(this.GetBlack())){
            player1.SetColor(2);
            player2.SetColor(1);
        }
        else {
            player2.SetColor(2);
            player1.SetColor(1);
        }

    };

    public void Swap(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Swap ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES) {
            this.utilitySwap();
        }
        CheckError();
    };

    public Boolean Swap2(){
        Boolean over;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Swap ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES) {
            this.utilitySwap();
            CheckError();
        }
        else{
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to stay white?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES) {
                CheckError();
            }
            else{
                Alert alertColors = new Alert(Alert.AlertType.INFORMATION);
                alertColors.setTitle("Swap2 - Opening");
                alertColors.setHeaderText(null);
                alertColors.setContentText("white player insert 2 more stones (1 black and 1 white)");
                alertColors.showAndWait();
                over=Boolean.FALSE;
                return over;
            }
        }
        over=Boolean.TRUE;
        return over;
    };

    public void utilitySwap2(){
        player1.addposition(player2.getPositions().get(0));
        player1.addposition(player2.getPositions().get(1));
        player2.addposition(player1.getPositions().get(0));
        player2.addposition(player1.getPositions().get(1));
        player2.addposition(player1.getPositions().get(2));
        player1.removeposition(2);
        player1.removeposition(1);
        player1.removeposition(0);
        player2.removeposition(1);
        player2.removeposition(0);
        if(player1.equals(this.GetBlack())){
            player1.SetColor(2);
            player2.SetColor(1);
        }
        else {
            player2.SetColor(2);
            player1.SetColor(1);
        }

    };

    public void Swap2_1(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Black player you want to Swap ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES){
            this.utilitySwap2();
        }
        CheckError();
    };
}
