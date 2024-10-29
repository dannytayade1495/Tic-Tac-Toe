package tictactoe.dto;

//import lombok.Data;

//@Data
public class Cell {
    private int row;
    private int col;
    private CellState cellState;
    private Player player;
//    public static int displayCell = 1;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellState = CellState.EMPTY;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getColumn() {
        return col;
    }

    public void setColumn(int column) {
        this.col = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public void display() {
        if (player == null){
            System.out.print("|   |");
        } else {
            System.out.print("| " + player.getSymbol().getSymbol() + " |");
        }
        /*displayCell++;
        if (displayCell == 4 || displayCell == 7 || displayCell == 10) {
            System.out.println();
        }*/
    }

}
