import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MinesweeperGUI {
    public void board() {
        Color navy = new Color(6, 57, 112);
        JPanel menu = new JPanel();
        JPanel board = new JPanel();
        menu.setBackground(Color.blue);
        //board.setBackground(navy);
        menu.setLayout(null);
        //board.setLayout(null);
        menu.setBounds(0, 0, 500, 75);
       // board.setBounds(0, 75, 500, 425);
        JFrame frame = new JFrame();
        frame.setTitle("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(500, 500);
        board=grid(9, 9);
        board.setLayout(null);
        board.setBounds(0, 75, 500, 425);
        frame.add(board);
        frame.add(menu);
        //frame.add(board);
        board.setBackground(navy);
        frame.setLayout(new GridLayout());
        frame.setVisible(true);
    }

    void level() {
        JFrame chooseLevel = new JFrame();
        chooseLevel.setBounds(0, 0, 500, 500);
        JButton level1 = new JButton("Level: easy");
        JButton level2 = new JButton("Level: medium");
        JButton level3 = new JButton("Level: hard");
        level1.setBounds(100, 25, 300, 50);
        level2.setBounds(100, 100, 300, 50);
        level3.setBounds(100, 175, 300, 50);
        chooseLevel.setLayout(null);
        chooseLevel.add(level1);
        chooseLevel.add(level2);
        chooseLevel.add(level3);
        chooseLevel.setLayout(new FlowLayout());
        chooseLevel.setVisible(true);
    }

    JPanel grid(int sizeRow, int sizeColumn) {
        JPanel board = new JPanel();
        //board.setBounds(0, 75, 500, 425);
        GridLayout grid = new GridLayout(sizeRow, sizeColumn);
        //board.setLayout(null);
        board.setLayout(grid);
        for(int i=0;i<sizeRow*sizeColumn;i++) {
            JButton tile = new JButton();
            tile.setPreferredSize(new Dimension(50,50));
            tile.setBackground(Color.pink);
            board.add(tile);
        }
        /*    JButton[][] buttons = new JButton[sizeRow][sizeColumn];
            for (int i = 0; i<sizeRow; i++) {
                for(int j = 0; j<sizeColumn;j++) {
                    buttons[i][j] = new JButton();
                    buttons[i][j].setPreferredSize(new Dimension(15,15));
                    board.add(buttons[i][j]);

                }
            }*/
      return board;

    }

    public static void main(String[] args) {

        MinesweeperGUI game = new MinesweeperGUI();
        // game.level();
        game.board();
    }
}
