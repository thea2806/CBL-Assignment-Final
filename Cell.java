public class Cell {
    int row;
    int column;
    boolean hasMine; //De vzt daca se poate altcumva
    int neighborMines;
    boolean revealed;
    boolean hasAbility;

    public Cell(int row, int column, boolean hasMine) {
        this.row = row;
        this.column = column;
        this.hasMine = hasMine;
        revealed = false;
        hasAbility = false;
    }

    public void setNeighborMines(int neighborMines) {
        this.neighborMines = neighborMines;
    }

}
