package Model;


public abstract class GomokuGame {
    private  Player p1;
    private  Player p2;
    private static int nBet = 0;
    private static int gridSize;
    public abstract int initGame();
    public abstract void setRules();

    public void setPlayers(Player p1, Player p2){
        System.out.println(p1.getColor());
        System.out.println(p2.getColor());
        this.p1 = p1;
        this.p2 = p2;
    }

    public void setSize (int size){
        this.gridSize = size;
    }

    public String GetName(){return "";}

    public static int getnBet(){
        return nBet;
    }

    public  Player getP1() {
        return p1;
    }

    public  Player getP2() {
        return p2;
    }

    public static int getGridDim() { return gridSize;  }

}
