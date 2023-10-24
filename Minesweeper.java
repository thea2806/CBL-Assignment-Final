import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Minesweeper {
    static JFrame frameGame = new JFrame("Minesweeper");
    static int cellSize = 70;
    static JLabel label = new JLabel();
    static JPanel panel = new JPanel();
    static JPanel boardPanel = new JPanel();
    static JButton[][] game = new JButton[100][100];
    

    private class Tiles extends JButton {
        int row;
        int column;

        public Tiles(int row, int column) {
            this.row = row;
            this.column = column;
        }

    }

    public Minesweeper(int row, int column, JFrame frameGame) {

        frameGame.setSize(cellSize * row, cellSize * column);
        // frameGame.setResizable(false);
        frameGame.setLocationRelativeTo(null);
        frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameGame.setLayout(new BorderLayout());

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setText("Minesweeper");
        label.setBackground(Color.BLUE);
        label.setFont(new Font("Arial", Font.BOLD, 22));

        panel.setLayout(new BorderLayout());
        panel.add(label);
        frameGame.add(panel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(row, column));
        boardPanel.setBackground(Color.green);
        frameGame.add(boardPanel);

        int i;
        int j;

        for (i = 0; i < row; i++) {
            for (j = 0; j < column; j++) {
                Tiles cell = new Tiles(i, j);
                cell.setMargin(new Insets(0, 0, 0, 0));
                cell.setPreferredSize(new Dimension(cellSize, cellSize));
                MinesweeperBoard board = new MinesweeperBoard(row, column);
                game[i][j] = cell;

                // cell.setFocusable(false);
                // cell.setMargin(new Inserts(0,0,0,0));

                boardPanel.add(cell);
            }
        }

        frameGame.setVisible(true);
    }

    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper(8, 8, frameGame);
    }

}

