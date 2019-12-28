package Model;

import java.util.List;

class GameLogic {
    private Board myBoard;
    //players of the board
    private int current_player;
    private int opposing_player;

    private GomokuGame game;
   //list of all the pieces
    private List<Piece> pieceChunk;

    public GameLogic(Board board,GomokuGame g) {
        this.myBoard = board;
        this.resetGame();
        this.game=g;
    }


    // public method that will try to place a piece in the given x (col ),y (row )coordinate
    public void placePiece(final int x, final int y) {
        if (this.getPiece(x, y) != 0)
            return;

        if (!this.isValidMove(x, y))
            return;

        this.myBoard.pieces[x][y].setPiece(this.current_player);
        this.InsertMove(x,y);
        this.swapPlayers(); //cambia il colore
    }


    private void checkPiece(final int x, final int y, final int player) {
        if (!this.validCoords(x, y))
            return;
    }

    private boolean checkPosition(final int x, final int y, final int player) {
        if (!this.validCoords(x, y))
            return (false);
        int pieceType = this.getPiece(x, y);
        Piece piece = this.myBoard.pieces[x][y];
        if (pieceType == Board.EMPTY_SPACE)
            return (true);
        if (pieceType == player && !this.pieceChunk.contains(piece))
            this.pieceChunk.add(piece);
        return (false);
    }

    private void InsertMove(final int x, final int y){
        Move m=new Move(y,x);
        if(this.current_player==this.game.getP1().getColor()){
            this.game.getP1().addposition(m);
        }
        else{
            this.game.getP2().addposition(m);
        }
    }

    // private method for swapping the players
    private void swapPlayers() {
       if (this.current_player== Board.WHITE_PLAYER) {
           this.current_player= Board.BLACK_PLAYER;
           this.opposing_player= Board.WHITE_PLAYER;
        }
        else {
           this.current_player=Board.WHITE_PLAYER;
           this.opposing_player= Board.BLACK_PLAYER;
        }
    }


    // private method for getting a piece on the board. this will return the board
    // value unless we access an index that doesnt exist. this is to make the code
    // for determining reverse chains much easier
    private int getPiece(final int x, final int y) {
        if (this.validCoords(x, y))
            return (this.myBoard.pieces[x][y].getPiece());
        return (-1);
    }

    private boolean validCoords(final int x, final int y) {
        if ((x >= 0 && x < myBoard.board_size) && (y >= 0 && y < myBoard.board_size)) {
            return (true);
        }
        return (false);
    }

    // public method for resetting the game
    public void resetGame() {
        this.resetRenders();

        this.current_player=Board.BLACK_PLAYER;
        this.opposing_player=Board.WHITE_PLAYER;
    }

    // private method that will reset the renders
    private void resetRenders() {
        for (int i = 0; i < myBoard.board_size; ++i) {
            for (int j = 0; j < myBoard.board_size; ++j) {
                this.myBoard.pieces[i][j].setPiece(Board.EMPTY_SPACE);
            }
        }
    }

    public boolean isValidMove(int x, int y) {
        if (this.myBoard.pieces[x][y].getPiece() != Board.EMPTY_SPACE)
            return (false);
        return (true);
    }



}
