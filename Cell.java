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
        //MinesweeperGUI.Tiles button = new MinesweeperGUI.Tiles(row, column);
        this.row = row;
        this.column = column;
        this.hasMine = false;
        revealed = false;
        hasAbility = false;
       // this.tile = tiles;
    }



    public void setNeighborMines(int neighborMines) {
        this.neighborMines = neighborMines;
    }

}
