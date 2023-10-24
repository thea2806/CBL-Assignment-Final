import javax.swing.JButton;

public class Cell {
    int row;
    int column;
    boolean hasMine; //De vzt daca se poate altcumva
    int neighborMines;
    boolean revealed;
    boolean hasAbility;
    JButton tile = new JButton();

    public Cell(int row, int column) {        
        this.row = row;
        this.column = column;
        this.hasMine = false;
        revealed = false;
        hasAbility = false;
    }

    public void addButton() {
        MinesweeperGUI board = new MinesweeperGUI(row,column); //row inseamna rowSize
        JButton button = new JButton();
        button = board.getButton(row, column);
        this.tile = button;
    }

    public JButton getButton() {
        return this.tile;
    }


    public void setNeighborMines(int neighborMines) {
        this.neighborMines = neighborMines;
    }

    public void setRevealed() {
        this.revealed = true;
    }

}
