package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static java.lang.System.*;

public class InvalidMoves {

    private int size;
    private int[][] board;
    private Stack<Move> moves;
    private int currentIndex = 1;

    /**
     * Create a new game state.
     * @param size Board size
     */
    protected InvalidMoves(int size) {
        this.size = size;
        this.board = new int[size][size];
        this.moves = new Stack<>();
    }

    /**
     * Get the current player index for this state
     * @return Current player # who has to make a move
     */
    protected int getCurrentIndex() {
        return this.currentIndex;
    }

    /**
     * Get an ordered list of moves that were made on this state.
     * @return ArrayList of moves, ordered from first move to last move made
     */
    public List<Move> getMoves() {
        return new ArrayList(moves);
    }

    /**
     * Return the last move made on this state.
     * @return Previous move that was made
     */
    public Move getLastMove() {
        return !moves.isEmpty() ? moves.peek() : null;
    }

    /**
     * Make a move on this state.
     * @param move Move to make
     */
    protected void makeMove(Move move) {
        this.moves.push(move);
        this.board[move.row][move.col] = currentIndex;
        this.currentIndex = currentIndex == 1 ? 2 : 1;
    }


    /**
     * Helper method to check if an index lies within the bounds of the board.
     * @param index Value to check
     * @return True if this value lies between the bounds of the board (0 to
     * size - 1)
     */
    private boolean inBounds(int index) {
        return index >= 0 && index < size;
    }

    /**
     * Iterates along the board from a start position and counts the
     * consecutive stones belonging to a player. The row/column increment
     * defines the direction of the iteration - e.g. +1, -1 would iterate
     * diagonally down to the right. The start position must be occupied by
     * the player in question.
     * @param row Row start pos
     * @param col Column start pos
     * @param rowIncrement Row increment
     * @param colIncrement Column increment
     * @return The number of consecutive unbroken stones found
     */

    private int countConsecutiveStones(int stop, int row, int col, int rowIncrement,int colIncrement) {
        int count = 0;
        int index = board[row][col];
        for(int i = 1; i <= stop; i++) {
            if(inBounds(row + (rowIncrement*i)) && inBounds(col +(colIncrement*i))) {
                if(board[row + (rowIncrement*i)][col + (colIncrement*i)] ==index) {
                    count++;
                } else {
                    break;
                }
            }
        }
        return count;
    }
    /**
     * The rule of three and three bans a move that simultaneously forms two open rows
     * of three stones
     * (rows not blocked by an opponent's stone at either end).
     */
    private int ThreeAndThree(Move move){
        int count=0;
        for (int k = -1; k <= 1; k++) {
            int j = -1;
            while (j <= 1) {
                int c = countConsecutiveStones(2,move.row, move.col, k, j);
                if (c == 3) {count++;
                    if (count==2) {
                        out.println("Invalid move");
                        break;}}
                j = j + 1;
            }
        }
        return 0;
    }


    /**
     * The rule of four and four bans a move that simultaneously forms two rows of
     * four stones (open or not).
     */
    private int FourAndFour(Move move){
        int count=0;
        for (int k = -1; k <= 1; k++) {
            int j = -1;
            while (j <= 1) {
                int c = countConsecutiveStones(3,move.row, move.col, k, j);
                if (c == 3) {count++;
                        if (count==2) {
                            out.println("Invalid move");
                            break;}}
                j = j + 1;
            }
        }
        return 0;
    }

    /**
     * Alternatively, a handicap may be given such that after the ﬁrst "three and three"
     * play has been made,
     * the opposing player may place two stones as their next turn.
     * These stones must block an opponent's row of three
     */
}
