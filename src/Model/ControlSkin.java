package Model;
import Controller.BoardController;

import javafx.scene.control.SkinBase;

//NOTE: to keep JavaFX happy we dont use the skin here
public class ControlSkin extends SkinBase<BoardController> {
    // default constructor for the class
    public ControlSkin(BoardController gc) {
        super(gc);
    }
}
