package Model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Player {
    private final String name;
    private int score = 0;
    private int  color;
    private Set<Move> position=new LinkedHashSet<Move>();
    //The idea is to set the position for each player and initialize
    // it with the opening rules.

    public Player(String name,String color){
        this.name = name;
        if(color=="Black") {this.color = 1;}
        else {this.color=2;}
    }

    public void addposition(Move m){
        position.add(m);

    }

    public void removeposition(Move m){
        position.remove(m);
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return this.color;
    }

    public void SetColor(int newcol) {
        this.color=newcol;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void PrintPositions(){
        List<Move> list = new ArrayList<Move>(position);
        //return list.get(numMov);
        System.out.println("movements for player "+this.color+":");
        for(Move model : list) {
            System.out.println(model.row+" "+model.col);
        }
    }

    public Set<Move> getPositions(){return this.position;}

    public boolean CheckinMoves(Move m){
        if(position.contains(m)) return true;
        else return false;
    }

    public boolean CheckAllMoves(Player p){
        Set<Move> intersection = new LinkedHashSet<Move>(this.position);
        intersection.retainAll(p.getPositions());
        if(intersection.isEmpty()) return true;
        else return false;
    }
}
