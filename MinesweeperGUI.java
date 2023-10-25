import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class MinesweeperGUI implements MouseListener {
    static JFrame frameGame = new JFrame("Minesweeper");
    static int cellSize;
    static JLabel label = new JLabel();
    static JPanel panel = new JPanel();
    static JPanel boardPanel = new JPanel();
    JButton[][] game = new JButton[100][100];
    // Boolean[][] isPressed = new Boolean[100][100];
    MinesweeperBoard gameBoard;
    int neighborMines;
    private int row;
    int column;
    int firstClick;
    int minesNum;
    ImageIcon imageIcon = new ImageIcon("D:\\Flag2.png"); // Replace with the actual path to your image
    Image image = imageIcon.getImage().getScaledInstance(230, 115, Image.SCALE_SMOOTH);
    ImageIcon flag = new ImageIcon(image);
    int[][] rightClicked = new int[24][24];

    static class Tiles extends JButton {
        static int row;
        static int column;

        public Tiles(int row, int column) {
            this.row = row;
            this.column = column;
        }

    }
    public void startGame() {
        JFrame start = new JFrame();
        start.setSize(500, 600);
        start.setLocationRelativeTo(null);
        start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start.setLayout(new GridLayout(3, 1));
        start.setResizable(false);

        JButton level1 = new JButton("Level 1");
        JButton level2 = new JButton("Level 2");
        JButton level3 = new JButton("Level 3");

        ImageIcon level1Im1 = new ImageIcon("D:\\Level1.jpg"); 
        Image level1Im2 = level1Im1.getImage().getScaledInstance(500, 200, Image.SCALE_SMOOTH);
        ImageIcon level1Icon = new ImageIcon(level1Im2);
        level1.setIcon(level1Icon);

        ImageIcon level2Im1 = new ImageIcon("D:\\Level2.jpg"); 
        Image level2Im2 = level2Im1.getImage().getScaledInstance(500, 200, Image.SCALE_SMOOTH);
        ImageIcon level2Icon = new ImageIcon(level2Im2);
        level2.setIcon(level2Icon);

        ImageIcon level3Im1 = new ImageIcon("D:\\Level3.jpg"); 
        Image level3Im2 = level3Im1.getImage().getScaledInstance(500, 200, Image.SCALE_SMOOTH);
        ImageIcon level3Icon = new ImageIcon(level3Im2);
        level3.setIcon(level3Icon); 

        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        Color buttonBackground = new Color(37, 150, 190); // Navy Blue
        Color buttonForeground = Color.WHITE; // White text
        level1.setFont(buttonFont);
        level2.setFont(buttonFont);
        level3.setFont(buttonFont);
        level1.setBackground(buttonBackground);
        level2.setBackground(buttonBackground);
        level3.setBackground(buttonBackground);
        level1.setForeground(buttonForeground);
        level2.setForeground(buttonForeground);
        level3.setForeground(buttonForeground);

        level1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialize(9, 9, 10,50);
                start.dispose(); // Close the level selection window
            }
        });
        level2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialize(16, 16, 40,40);
                start.dispose();
            }
        });

        level3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialize(24, 24, 99,35);
                start.dispose();
            }
        });
        start.add(level1);
        start.add(level2);
        start.add(level3);
        start.setVisible(true);
    }

    public void initialize(int row, int column, int minesNum, int cellSize) {
        this.cellSize = cellSize;
        this.row = row;
        this.column = column;
        this.minesNum = minesNum;
        firstClick = 0;
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
        // gameBoard.board[0][0].hasMine = true;

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
        gameBoard.generateMines(minesNum, game);
        frameGame.setVisible(true);
    }

    public void revealCell(int row, int column) {
        if (gameBoard.getBoard()[row][column].hasMine) {
            endGame();
        } else {
            if (gameBoard.neighborMines(gameBoard.getBoard()[row][column]) > 0) {
                game[row][column].setText(Integer.toString(gameBoard.neighborMines(gameBoard.getBoard()[row][column])));
            }
        }
    }

    public void cellExpansion(int row, int column) {
        if (row < 0 || row >= this.row || column < 0 || column >= this.column) {
            return;
        }

        // Check if the cell has already been revealed
        if (!game[row][column].isEnabled()) {
            return;
        }
        game[row][column].setEnabled(false);
        if (gameBoard.getBoard()[row][column].hasMine) {
            return;
        } else {
            if (gameBoard.neighborMines(gameBoard.getBoard()[row][column]) > 0) {
                game[row][column].setText(Integer.toString(gameBoard.neighborMines(gameBoard.getBoard()[row][column])));
            }
        }
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                cellExpansion(i, j);
            }
        }
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
        MinesweeperGUI game = new MinesweeperGUI();
        game.startGame();
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
                firstClick++;
                break;
            }
        }

        if (SwingUtilities.isLeftMouseButton(e)) {
            // Left-click
            // System.out.println("Left-click on row " + i + ", col " + j);
            if (gameBoard.getBoard()[i][j].getFlagged()) {
                e.consume();
            } else {
                if (firstClick == 1 && gameBoard.getBoard()[i][j].hasMine) {
                    gameBoard.getBoard()[i][j].hasMine = false;
                    System.out.print("first click");
                    Random random = new Random();
                    int row2;
                    int column2;
                    do {
                        row2 = random.nextInt(row);
                        column2 = random.nextInt(column);

                    } while (gameBoard.getBoard()[row2][column2].hasMine);
                    gameBoard.getBoard()[row2][column2].hasMine = true;
                }
                game[i][j].setEnabled(false);
                revealCell(i, j);
                cellExpansion(i, j);
            }
        } else if (SwingUtilities.isRightMouseButton(e)) {
            // Right-click
            rightClicked[i][j]++;
            if (rightClicked[i][j] % 2 == 1) {
                game[i][j].setIcon(flag);
                gameBoard.getBoard()[i][j].setFlagged(true);
            } else {
                game[i][j].setIcon(null);
                gameBoard.getBoard()[i][j].setFlagged(false);
            }
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
