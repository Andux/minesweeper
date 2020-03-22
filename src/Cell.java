import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Cell extends Button {
    boolean revealed;
    boolean mined;
    boolean flagged;
    int proximityCount = 0;
    int row;
    int col;

    public Cell(boolean revealed, boolean mined, boolean flagged, int col, int row) {
        this.revealed = revealed;
        this.mined = mined;
        this.flagged = flagged;
        this.col = col;
        this.row = row;

        doGraphic();

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                handleClick();
            }
        });
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
    }

    public void handleClick() {
        if (!revealed)
            reveal();
    }

    public void reveal() {
        setRevealed(true);
        doGraphic();


        Main.getFace().setDead();


    }
    /* public void revealAdjacentCells(int col, int row) {
        for (int subRow = row - 1; subRow < row + 2; subRow++) {
            for (int subCol = col - 1; subCol < col + 2; subCol++) {
                if (subRow > -1 && subRow < rows && subCol > -1 && subCol < cols && !cells[subCol][subRow].isRevealed()) {
                    cells[subCol][subRow].reveal();
                }
            }
        }
    }

     */


}
