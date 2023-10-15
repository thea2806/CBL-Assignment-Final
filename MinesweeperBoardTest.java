import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MinesweeperBoardTest {
    MinesweeperBoard level1 = new MinesweeperBoard(9, 9);
    MinesweeperBoard level2 = new MinesweeperBoard(16, 16);
    MinesweeperBoard level3 = new MinesweeperBoard(16, 30);

    @Test
    public void neighborMinesTest() {
        //Test case for level 1
        Cell test1 = level1.board[4][5];
        level1.board[3][4].hasMine = true;
        level1.board[4][6].hasMine = true;
        int expectedResult1 = 2;
        assertEquals(expectedResult1, level1.neighborMines(test1));

        //Test case for level 2
        Cell test2 = level2.board[15][12];
        level2.board[14][11].hasMine = true;
        level2.board[15][13].hasMine = true;
        level2.board[14][13].hasMine = true;
        int expectedResult2 = 3;
        assertEquals(expectedResult2, level2.neighborMines(test2));

        //Test case for level 3
        Cell test3 = level3.board[0][25];
        level3.board[0][24].hasMine = true;
        level3.board[0][26].hasMine = true;
        level3.board[1][25].hasMine = true;
        level3.board[1][26].hasMine = true;
        int expectedResult3 = 4;
        assertEquals(expectedResult3, level3.neighborMines(test3));
    }

}

