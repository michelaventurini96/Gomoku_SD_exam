package Model;

public class GomokuFree extends GomokuGame {

    @Override
    public void initGame() {
        System.out.println("Mode Free");
        System.out.println("Players:" + getP1().getName() + " and " + getP2().getName() + " initial bets = " + getnBet() + " grDim= " + getGridDim());
        System.out.println("Opening Rules:" + getOp());
        op=new Opening(getP1(),getP2(),getOp());
    }

    @Override
    public void setRules() {

    }
    @Override
    public String GetName(){return "Free";}
}
