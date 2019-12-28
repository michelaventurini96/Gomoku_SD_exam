package Model;

import java.util.Scanner;

public class Opening {
    private Player player1;
    private Player player2;
    public Opening(Player p1, Player p2){
        this.player1=p1;
        this.player2=p2;
    };

    public void CheckError(){
        if(!player1.CheckAllMoves(player2)){
            throw new Error("place stones in different places");
        }
    };

    public void CheckinError(Player p, Move m){
        if(!p.CheckinMoves(m)){
            throw new Error(p.getName()+" place stones in different places");
        }
    };
/*
    Black can place anywhere, white secondly can place anywhere but on black spot.
 */
    public void OpenStd(Move moveb, Move movew){
        player1.addposition(moveb);
        if(!moveb.equals(movew)) { player2.addposition(movew);}
        else { throw new Error("white player can't overlap on black");}
    };

    public void Pro(Move movew, Move moveb){
        //set the black stone in the centre of the board ( (0,0) to change eventually).
        Move centre=new Move(0,0);
         player1.addposition(centre);
         player2.addposition(movew);  //white can place anywhere.
         // set the black stone out of a 5x5 square from the center.
        CheckError();
        if(moveb.col<centre.col+ 5  && moveb.row<centre.row + 5 ){
            throw new Error("place black stone out of a 5x5 square from the center");
        }else {
            player1.addposition(moveb);
        }
        CheckError();
    };

    public void LongPro(Move movew, Move moveb){
        //set the black stone in the centre of the board ( (0,0) to change eventually).
        Move centre=new Move(0,0);
        player1.addposition(centre);
        player2.addposition(movew);  //white can place anywhere.
        // set the black stone out of a 7x7 square from the center.
        CheckError();
        if(moveb.col<centre.col+ 7  && moveb.row<centre.row + 7 ){
            throw new Error("place black stone out of a 7x7 square from the center");
        }else {
            player1.addposition(moveb);
        }
        CheckError();
    };

    public void utilitySwap(Move moveb1, Move moveb2, Move movebw){
        player1.removeposition(moveb1);
        player1.removeposition(moveb2);
        player2.addposition(moveb1);
        player2.addposition(moveb2);
        player1.SetColor(2);
        player2.SetColor(1);
    };

    public void Swap(Move moveb1, Move moveb2, Move movebw){
        player1.addposition(moveb1);
        CheckinError(player1,moveb2);
        player1.addposition(moveb2);
        CheckinError(player1,movebw);
        player1.addposition(movebw);
        System.out.println("Do you swap?");
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        if(s=="si") {
            this.utilitySwap(moveb1, moveb2, movebw);
        }else{
            player1.removeposition(movebw);
            player2.addposition(movebw);
        }
        //TODOOO: add new white position
        CheckError();
    };

    public void Swap2(Move moveb1, Move moveb2, Move movebw){
        player1.addposition(moveb1);
        CheckinError(player1,moveb2);
        player1.addposition(moveb2);
        CheckinError(player1,movebw);
        player1.addposition(movebw);
        System.out.println("Do you swap?");
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        if(s.equals("si")) {
            this.utilitySwap(moveb1, moveb2, movebw);
        }
        if(!s.equals("si")){
            System.out.println("Do you want to go white? player 2");
            Scanner scan1 = new Scanner(System.in);
            String s1 = scan1.next();
            if(s1.equals("si")) {
                player1.removeposition(movebw);
                player2.addposition(movebw);
                CheckError();
            }
            else{
                Move mb=new Move(0,0);
                Move mw=new Move(1,0);
                CheckinError(player2,mb);
                player2.addposition(mb);
                CheckinError(player2,mw);
                player2.addposition(mw);
                System.out.println("Player1 choose color");
                Scanner scan2 = new Scanner(System.in);
                String s2 = scan2.next();
                if(s2.equals("Black")){
                    player1.removeposition(movebw);
                    player1.addposition(mb);
                    player2.removeposition(mb);
                    player2.addposition(movebw);
                    CheckError();
                }
                else{
                    player1.removeposition(moveb1);
                    player1.removeposition(moveb2);
                    player2.removeposition(mw);
                    player1.addposition(mw);
                    player2.addposition(moveb1);
                    player2.addposition(moveb2);
                    CheckError();
                }
            }

        }
    };
}
