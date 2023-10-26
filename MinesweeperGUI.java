import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class MinesweeperGUI implements MouseListener {
    static JFrame frameGame = new JFrame("Minesweeper");
    static int cellSize;
    static JLabel label = new JLabel();
    static JPanel panel = new JPanel();
    static JPanel boardPanel = new JPanel();
    JButton[][] game = new JButton[100][100];
    Color tileColor;
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
    Border customBorder = new CompoundBorder(
            new LineBorder(new Color(250, 242, 237), 1), // Outer border (color of your choice)
            BorderFactory.createRaisedBevelBorder() // Inner empty border for margin
    );
    Color navy = new Color(50, 41, 158);
    // ImageIcon gameOver = new ImageIcon("D:\\gameOver.jpg");

    static class Tiles extends JButton {
        static int row;
        static int column;

        public Tiles(int row, int column) {
            this.row = row;
            this.column = column;
        }

    }



    public void initialize(int row, int column, int minesNum, int cellSize, Color tileColor) {
        this.tileColor = tileColor;
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
        label.setBackground(navy);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(new Color(250, 242, 237));

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
                tile.setBackground(tileColor);
                tile.setBorder(customBorder);
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

    public void levelSound() {
        try {
            // Load an audio file
            File soundFile = new File("D:\\LevelSound2.wav");
            Clip clip1 = AudioSystem.getClip();
            clip1.open(AudioSystem.getAudioInputStream(soundFile));

            Thread soundThread = new Thread(() -> {
                clip1.start();
                // You can optionally add a delay or sleep here
                try {
                    Thread.sleep(clip1.getMicrosecondLength() / 10);
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
                clip1.close();
            });
            soundThread.start();

        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void tileSound() {
        try {
            // Load an audio file
            File soundFile = new File("D:\\TileSound.wav");
            Clip clip1 = AudioSystem.getClip();
            clip1.open(AudioSystem.getAudioInputStream(soundFile));

            Thread soundThread = new Thread(() -> {
                clip1.start();
                // You can optionally add a delay or sleep here
                try {
                    Thread.sleep(clip1.getMicrosecondLength() / 10);
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
                clip1.close();
            });
            soundThread.start();

        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void flagSound() {
        try {
            // Load an audio file
            File soundFile = new File("D:\\FlagSound.wav");
            Clip clip1 = AudioSystem.getClip();
            clip1.open(AudioSystem.getAudioInputStream(soundFile));

            Thread soundThread = new Thread(() -> {
                clip1.start();
                // You can optionally add a delay or sleep here
                try {
                    Thread.sleep(clip1.getMicrosecondLength() / 10);
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
                clip1.close();
            });
            soundThread.start();

        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void bombSound() {
        try {
            // Load an audio file
            File soundFile = new File("D:\\BombSound.wav");
            Clip clip1 = AudioSystem.getClip();
            clip1.open(AudioSystem.getAudioInputStream(soundFile));

            Thread soundThread = new Thread(() -> {
                clip1.start();
                // You can optionally add a delay or sleep here
                try {
                    Thread.sleep(clip1.getMicrosecondLength() / 10);
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
                clip1.close();
            });
            soundThread.start();

        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void resultsSound() {
        try {
            // Load an audio file
            File soundFile = new File("D:\\OutroMusic.wav");
            Clip clip1 = AudioSystem.getClip();
            clip1.open(AudioSystem.getAudioInputStream(soundFile));

            Thread soundThread = new Thread(() -> {
                clip1.start();
                // You can optionally add a delay or sleep here
                try {
                    Thread.sleep(clip1.getMicrosecondLength() / 10000);
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
                clip1.close();
            });
            soundThread.start();

        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void revealCell(int row, int column) {
        gameBoard.getBoard()[row][column].setRevealed();
        if (gameBoard.getBoard()[row][column].hasMine) {
            bombSound();
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
        resultsSound();
        ImageIcon imageIcon = new ImageIcon("D:\\gameOver.jpg"); // Replace with the actual path to your image
        Image image = imageIcon.getImage().getScaledInstance(cellSize*row, cellSize*column, Image.SCALE_SMOOTH);
        ImageIcon gameOver = new ImageIcon(image);
        JLabel imageLabel = new JLabel(gameOver);
        JPanel results = new JPanel();
        results.setSize(500, 500);
        results.add(imageLabel);
        results.setBackground(navy);
        boardPanel.setVisible(false);
        frameGame.add(results);
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
            } else if (!gameBoard.getBoard()[i][j].revealed) {
                tileSound();
                game[i][j].setBackground(new Color(250, 242, 237));
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
            if (gameBoard.getBoard()[i][j].revealed) {
                e.consume();
            } else {
                rightClicked[i][j]++;
                flagSound();
                if (rightClicked[i][j] % 2 == 1) {
                    game[i][j].setIcon(flag);
                    gameBoard.getBoard()[i][j].setFlagged(true);
                } else {
                    game[i][j].setIcon(null);
                    gameBoard.getBoard()[i][j].setFlagged(false);
                }
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
