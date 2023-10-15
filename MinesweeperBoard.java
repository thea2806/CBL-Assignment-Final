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

    public Cell[][] getBoard() {
        return this.board;
    }

    public int neighborMines(Cell cell) {
        int sizeRow2 = sizeRow + 2;
        int sizeColumn2 = sizeColumn + 2;
        int i;
        int j;
        int neighborMines = 0;
        int[][] boardCopy = new int[sizeRow2][sizeColumn2];

        for (i = 0; i < sizeRow; i++) {
            for (j = 0; j < sizeColumn; j++) {
                if (board[i][j].hasMine) {
                    boardCopy[i + 1][j + 1] = 1;
                }
            }
        }

        for (i = cell.row; i <= cell.row + 2; i++) {
            for (j = cell.column; j <= cell.column + 2; j++) {
                neighborMines += boardCopy[i][j];
            }
        }
        return neighborMines;
    }

    public void generateMines(Cell board[][], int minesNum) {
        int rows = sizeRow;
        int columns = sizeColumn;
        Random random = new Random();
        for (int i = 0; i < minesNum; i++) {
            int row;
            int col;
            do {
                row = random.nextInt(rows);
                col = random.nextInt(columns);

            } while (board[row][col].hasMine);
            board[row][col].hasMine = true;
        }
    }

    public boolean specialAbilities(Cell[][] board, int abilitiesNum) {
        return false;
    }
}
