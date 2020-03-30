import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;

public class Cell extends Button {
    boolean revealed;
    boolean mined;
    boolean flagged;
    int proximityCount = 0;
    int row;
    int col;
    Board theBoard;
    Face face;

    public Cell(boolean revealed, boolean mined, boolean flagged, int col, int row, Board theBoard, Face face) {
        this.revealed = revealed;
        this.mined = mined;
        this.flagged = flagged;
        this.col = col;
        this.row = row;
        this.theBoard = theBoard;
        this.face = face;


        doGraphic();

        setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown())
                ;//face.setO();
        });
        setOnMouseReleased(e -> {
            ;//face.setSmile();
        });

        setOnMouseClicked(e -> {
            MouseButton mouseButton = e.getButton();
            if (mouseButton == MouseButton.PRIMARY)
                handleLeftClick();
            else if (mouseButton == MouseButton.SECONDARY)
                handleRightClick();
        });

        //print DEBUG STUFF related to padding
        //System.out.println("this.getPadding() returns: " + this.getPadding());
        System.out.println("getWidth() returns: " + getWidth());

        //Joe's working code below
        //setMaxSize(32, 32);
        //setMinSize(32, 32);
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public boolean isMined() {
        return mined;
    }

    public void setMined(boolean mined) {
        this.mined = mined;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public void toggleFlag() {
        if (isFlagged()) {
            setFlagged(false);
            theBoard.decrementFlagCount();
        }
        else {
            setFlagged(true);
            theBoard.incrementFlagCount();
        }
    }

    public int getProximityCount() {
        return proximityCount;
    }

    public void setProximityCount(int proximityCount) {
        this.proximityCount = proximityCount;
    }

    public void incrementProximityCount() {
        proximityCount++;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void doGraphic() {
        if (revealed) {
            if (mined)
                this.setGraphic(new ImageView("file:res/mine-red.png"));
            else
                this.setGraphic(new ImageView("file:res/" + proximityCount + ".png"));
        }
        else {
            if (flagged)
                this.setGraphic(new ImageView("file:res/flag.png"));
            else
                this.setGraphic(new ImageView("file:res/cover.png"));
        }
        System.out.println("AFTER doing graphic, getWidth() returns: " + getWidth());
        System.out.println("AFTER doing graphic, getPadding() returns: " + getPadding());
    }

    public void handleLeftClick() {
        if (!theBoard.isGameOver()) {
            if (theBoard.isVirginBoard()) {
                theBoard.addMines(theBoard.getMines(), col, row);
                theBoard.solveProximityCount();
                theBoard.setVirginBoard(false);
            }
            if (!isFlagged()) {
                if (!revealed)
                    reveal();
                else
                    theBoard.revealAdjacentCells(col, row);
            }
        }
        if (theBoard.onlyMinesLeft())
            face.setWin();
    }

    public void handleRightClick() {
        if (!theBoard.isGameOver()) {
            if (!revealed)
                toggleFlag();
            doGraphic();
        }
    }

    public void reveal() {
        setRevealed(true);
        doGraphic();

        if (isMined())
            //theBoard.gameOver();
            face.setDead();

        if (proximityCount == 0)
            theBoard.revealAdjacentCells(col, row);

    }
}
