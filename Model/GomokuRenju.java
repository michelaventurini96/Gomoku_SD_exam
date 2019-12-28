package Model;

public class GomokuRenju extends GomokuGame{

    @Override
    public int initGame() {
        System.out.println("Mode Renju");
        System.out.println("Players:" + getP1().getName() + " and " + getP2().getName() + " initial bets = " + getnBet() + " grDim= " + getGridDim());
        return 0;
    }

    @Override
    public void setRules() {

    }
    @Override
    public String GetName(){return "Renju";}
}
