package test;
import Model.Player;
import Model.Piece;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
* Test class for the player class implementation.
*
*/

class PlayerTest {


    @Test

    /*
    *  Test constructor,getname(),getColor(),SetColor()
    */
    void Create_new_Player(){
        Player p=new Player("giulia","white");
        assertEquals(p.getName(),"giulia");
        assertEquals(p.getColor().intValue(),2);
        p.SetColor(1);
        assertEquals(p.getColor().intValue(),1);
    };

    @Test
        /*
         *  Test constructor,addposition(),getPositions(),removeposition()
         */
    void Fill_moves(){
        Player p=new Player("giulia","white");
        Piece pie=new Piece(2);
        pie.setX(1);
        pie.setY(2);
        p.addposition(pie);
        assertEquals(p.getPositions().get(0),pie);
        p.removeposition(0);
        assertEquals(p.getPositions().size(),0);
    };

    @Test
        /*
         *  Test constructor,CheckinMoves()
         */
    void Check_Inmoves(){
        Player p=new Player("giulia","white");
        Piece pie=new Piece(2);
        pie.setX(1);
        pie.setY(2);
        p.addposition(pie);
        Piece pie1=new Piece(2);
        pie1.setX(4);
        pie1.setY(2);
        assertEquals(p.CheckinMoves(pie1),false);

    }

    @Test
        /*
         *  Test constructor,CheckAllMoves()
         */
    void Check_Allmoves(){
        Player p=new Player("giulia","white");
        Piece pie=new Piece(2);
        pie.setX(1);
        pie.setY(2);
        p.addposition(pie);
        Player p1=new Player("giulia","black");
        Piece pie1=new Piece(2);
        pie1.setX(4);
        pie1.setY(2);
        p1.addposition(pie1);
        assertEquals(p.CheckAllMoves(p1),true);

    }

}