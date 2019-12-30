package Model;

public class GomokuRenju extends GomokuGame{

    @Override
    public void initGame() {
        System.out.println("Mode Renju");
        System.out.println("Players:" + getP1().getName() + " and " + getP2().getName() + " initial bets = " + getnBet() + " grDim= " + getGridDim());
        System.out.println("Opening Rules:" + getOp());
        op=new Opening(getP1(),getP2(),getOp());
    }

    @Override
    public void OpeningRules(){op.calling();}

    @Override
    public void setRules() {

    }
    @Override
    public String GetName(){return "Renju";}
}
