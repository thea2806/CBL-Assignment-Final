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

    /**
     * We test whether this method places the correct number of mines on the board, meaning that
     * it doesn't place two mines in the same cell.
     * For this, we will first create the test cases for a method that returns an int, which should
     * be the same as the number of bombs in the parameter. After we implement this method
     * and it passes the all the tests, we will change it to a void method, which we need in our 
     * program.
     */
    @Test 
    public void generateMinesTest() {

        //Test case for level one that has 10 mines
        int test1 = 10;
        int expectedResult1 = test1;
        assertEquals(expectedResult1, level1.generateMines(level1.getBoard(), test1));

        //Test case for level two that has 40 mines
        int test2 = 40;
        int expectedResult2 = test2;
        assertEquals(expectedResult2, level2.generateMines(level2.getBoard(), test2));

        //Test case for level three that has 99 mines
        int test3 = 99;
        int expectedResult3 = test3;
        assertEquals(expectedResult3, level3.generateMines(level3.getBoard(), test3));
    }

}

