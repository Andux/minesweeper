import javafx.scene.layout.GridPane;

import java.util.ArrayList;

import static javafx.scene.layout.GridPane.getMargin;

public class Board {
    private int rows;
    private int cols;
    private int mines;
    private Cell[][] cells;
    private int flagCount = 0;
    Face face;
    private boolean virginBoard = true;
    private boolean gameOver = false;

    public Board(int cols, int rows, int mines, Face face) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        cells = new Cell[cols][rows];
        this.face = face;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[col][row] = new Cell(false, false, false, col, row, this, face);
            }
        }
    }

    public boolean isVirginBoard() {
        return virginBoard;
    }
    public void setVirginBoard(boolean b) {
        virginBoard = b;
    }
    public boolean isGameOver() {
        return gameOver;
    }
    public void setGameOver(boolean b) {
        gameOver = b;
    }
    public int getMines() {
        return mines;
    }

    public GridPane returnGridPane() {
        GridPane toReturn = new GridPane();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                toReturn.add(cells[col][row], col, row);
            }
        }
        return toReturn;
    }

    public void addMines(int mines, int fCol, int fRow) {
        int minesToAdd = mines;
        ArrayList<Cell> eligibleCells = new ArrayList();
        //load all cells into ArrayList
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                eligibleCells.add(cells[col][row]);
            }
        }
        //remove cells adjacent to first click
        for (int row = fRow - 1; row < fRow + 2; row++) {
            for (int col = fCol - 1; col < fCol + 2; col++) {
                if (row > -1 && row < rows && col > -1 && col < cols) {
                    eligibleCells.remove(cells[col][row]);
                }
            }
        }
        //push mines into remaining eligible cells
        while (minesToAdd > 0) {
            int randomIndex = (int) (Math.random() * eligibleCells.size());
            Cell chosenCell = eligibleCells.get(randomIndex);
            cells[chosenCell.getCol()][chosenCell.getRow()].setMined(true);
            eligibleCells.remove(chosenCell);
            minesToAdd--;
        }
    }

    public void addMinesFixed() {
        cells[1][1].setMined(true);
        cells[3][2].setMined(true);
        cells[3][3].setMined(true);
    }

    public void solveProximityCount() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (cells[row][col].isMined()) {
                    for (int subRow = row - 1; subRow < row + 2; subRow++) {
                        for (int subCol = col - 1; subCol < col + 2; subCol++) {
                            if (subRow > -1 && subRow < rows && subCol > -1 && subCol < cols) {
                                cells[subRow][subCol].incrementProximityCount();
                            }
                        }
                    }
                }
            }
        }
    }

    public void gameOver() {
        setGameOver(true);
        System.out.println("GAME OVER");
        System.out.println("rows =" + rows);
        System.out.println("cols =" + cols);
        for (int row = 0; row < rows; rows++) {
            for (int col = 0; col < cols; cols++) {
                if (!cells[col][row].isRevealed())
                  cells[col][row].reveal();
            }
        }
    }

    public int getFlagCount() {
        return flagCount;
    }
    public void decrementFlagCount() {
        flagCount--;
    }
    public void incrementFlagCount() {
        flagCount++;
    }

    public void revealAdjacentCells(int col, int row) {
        for (int subRow = row - 1; subRow < row + 2; subRow++) {
            for (int subCol = col - 1; subCol < col + 2; subCol++) {
                if (subRow > -1 && subRow < rows && subCol > -1 && subCol < cols && !cells[subCol][subRow].isRevealed() && !cells[subCol][subRow].isFlagged()) {
                    cells[subCol][subRow].reveal();
                }
            }
        }
    }
    public boolean onlyMinesLeft() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Cell theCell = cells[row][col];
                if (!theCell.isRevealed() && !theCell.isMined())
                    return false;
            }
        }
        return true;
    }
}
