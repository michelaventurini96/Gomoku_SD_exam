package Model;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

class Piece extends Group {

    private int player;		// the player that this piece belongs to
    private Ellipse piece;	// ellipse representing the player's piece
    private int x;          // x position
    private int y;          // y position

    public Piece(int player) {
        this.player = player;
        this.piece = new Ellipse();
        this.getChildren().add(this.piece);
        this.setPiece(this.player);
    }

    // overridden version of the resize method to give the piece the correct size
    @Override
    public void resize(double width, double height) {
        super.resize(width, height);

        this.piece.setCenterX(width / 2.0);
        this.piece.setCenterY(height / 2.0);
        this.piece.setRadiusX(width / 2.0);
        this.piece.setRadiusY(height / 2.0);
    }


    // method that will set the piece type
    public void setPiece(final int type) {
        this.player = type;
        if (this.player == Board.EMPTY_SPACE )
            this.piece.setFill(Color.TRANSPARENT);
        else
            this.piece.setFill(this.player == Board.WHITE_PLAYER ? Board.WHITE_COLOR : Board.BLACK_COLOR);
    }

    //method that will allow to remove the piece
    public void removePiece(final int type) {
        this.player = type;
        if (this.player==Board.BLACK_PLAYER)
            this.piece.setFill(Color.TRANSPARENT);

    }

    // returns the type of this piece
    public int getPiece() {
        return (this.player);
    }

    public void setX(final int x) {
        this.x = x;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public int getX() {
        return (this.x);
    }

    public int getY() {
        return (this.y);
    }
}
