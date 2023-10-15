import java.util.Random;

public class MinesweeperBoard {
    Cell[][] board = new Cell[100][100];
    int sizeRow;
    int sizeColumn;

    public MinesweeperBoard(int sizeRow, int sizeColumn) {
        for (int i = 0; i < sizeRow; i++) {
            for (int j = 0; j < sizeColumn; j++) {
                board[i][j] = new Cell(i, j, false);
            }
        }
        this.sizeRow = sizeRow;
        this.sizeColumn = sizeColumn;
    }

    public int neighborMines(Cell cell) {
        return 0;
    }
}