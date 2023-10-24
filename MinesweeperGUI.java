import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MinesweeperGUI implements MouseListener {
    static JFrame frameGame = new JFrame("Minesweeper");
    static int cellSize = 70;
    static JLabel label = new JLabel();
    static JPanel panel = new JPanel();
    static JPanel boardPanel = new JPanel();
    JButton[][] game = new JButton[100][100];
    //Boolean[][] isPressed = new Boolean[100][100];
    MinesweeperBoard gameBoard;
    int neighborMines;
    int row;
    int column;

    static class Tiles extends JButton {
        static int row;
        static int column;

        public Tiles(int row, int column) {
            this.row = row;
            this.column = column;
        }

    }

    public MinesweeperGUI(int row, int column) {
        this.row = row;
        this.column = column;
        frameGame.setSize(cellSize * row, cellSize * column);
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
        // boardPanel.setBackground(Color.green);
        frameGame.add(boardPanel);

        // Tiles board[][] = new Tiles [row][column];

        int i;
        int j;
        gameBoard = new MinesweeperBoard(row, column);
        

        // MinesweeperBoard board = new MinesweeperBoard(Tiles.row, Tiles.column);
        Cell[][] place = new Cell[100][100];

        for (i = 0; i < row; i++) {
            for (j = 0; j < column; j++) {
                Tiles tile = new Tiles(i, j);
                tile.setMargin(new Insets(0, 0, 0, 0));
                tile.setPreferredSize(new Dimension(cellSize, cellSize));
                Cell cell = new Cell(i, j);
                // board.hasMine = true;
                game[i][j] = tile;
                boardPanel.add(tile);
                // Location = new Point(x, y);
                tile.addMouseListener(this);

                // cell.setFocusable(false);

            }
        }
        gameBoard.generateMines(10, game);
        frameGame.setVisible(true);
    }

    public void revealCell(int row, int column) {

    }

    public JButton getButton(int i, int j) {
        return game[i][j];
    }

    public void endGame() {
        JPanel results = new JPanel();
        results.setSize(500, 500);
        results.setBackground(Color.BLUE);
        boardPanel.setVisible(false);
        frameGame.add(results);
    }
    public static void main(String[] args) {
        MinesweeperGUI minesweeper = new MinesweeperGUI(9, 9);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // MinesweeperBoard board = new MinesweeperBoard(Tiles.row, Tiles.column);
        int i = 0;
        int j = 0;
        Cell board = new Cell(Tiles.row, Tiles.column);
        boolean buttonPressed = false;

        for (i = 0; i <= Tiles.row; i++) {
            for (j = 0; j <= Tiles.column; j++) {
                if (game[i][j] == e.getSource()) {
                    buttonPressed = true;
                    break;
                }
            }
            if (buttonPressed) {
                break; 
            }
        }

        if (SwingUtilities.isLeftMouseButton(e)) {
            // Left-click 
            if(gameBoard.getBoard()[i][j].hasMine) {
                    game[i][j].setText("MINE");
                    endGame();
                } else {
                if(gameBoard.neighborMines(gameBoard.getBoard()[i][j])>0) {
                    game[i][j].setText(Integer.toString(gameBoard.neighborMines(gameBoard.getBoard()[i][j])));
                }
            }
            System.out.println("Left-click on row " + i + ", col " + j);
            game[i][j].setEnabled(false);
            gameBoard.revealCell(board);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            // Right-click 
            game[i][j].setText("F");
        }

    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        return;

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        return;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        return;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        return;
    }

}
