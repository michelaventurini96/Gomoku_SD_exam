package Model;

public class GomokuStd extends GomokuGame {


    public GomokuStd(){}

    @Override
    public int initGame() {
        System.out.println("Mode standard");
        System.out.println("Players:" + getP1().getName() + " and " + getP2().getName() + " initial bets = " + getnBet() + " grDim= " + getGridDim());
        return 0;
    }

    @Override
    public void setRules(){};

    @Override
    public String GetName(){return "Standard";}
}
