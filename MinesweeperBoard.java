import java.util.Random;
import javax.swing.JButton;

public class MinesweeperBoard {
    Cell[][] board = new Cell[100][100];
    int sizeRow;
    int sizeColumn;

    public MinesweeperBoard(int rowSize, int columnSize) {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
        this.sizeRow = rowSize;
        this.sizeColumn = columnSize;
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

    public void generateMines(int minesNum, JButton[][] game) {

        int rows = sizeRow;
        int columns = sizeColumn;
        int row;
        int col;
        Random random = new Random();
        int minesPlaced = 0;

        while (minesPlaced < minesNum) {
            row = random.nextInt(rows);
            col = random.nextInt(columns);

            if (!board[row][col].hasMine) {
                board[row][col].hasMine = true;
                minesPlaced++;
            }

        }
    }

    public void specialAbilities(Cell[][] board, int abilitiesNum) {
        int rows = sizeRow;
        int columns = sizeColumn;
        Random random = new Random();
        for (int i = 0; i < abilitiesNum; i++) {
            int row;
            int col;
            do {
                row = random.nextInt(rows);
                col = random.nextInt(columns);

            } while (board[row][col].hasMine && board[row][col].hasAbility);
            board[row][col].hasAbility = true;

        }
    }

    public void revealCell(Cell cell) {
        if(board[cell.row][cell.column].hasMine) {
            endGame();
        } else {
            board[cell.row][cell.column].setRevealed();
            String neighborMines = String.valueOf(neighborMines(cell));
            cell.tile.setText(neighborMines);
        }
    }

    public void endGame() {
        System.out.print("end game");
    }

    public String getText() {
        return null;
    }
}
