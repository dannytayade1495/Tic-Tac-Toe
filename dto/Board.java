package tictactoe.dto;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private int dimension;
    private List<List<Cell>> board;

    public Board(int dimension){
        this.dimension = dimension;
        board = new ArrayList<>();
        for (int i = 0; i < dimension; i++){
            board.add(new ArrayList<>());
            for (int j = 0; j < dimension; j++){
                board.get(i).add(new Cell(i, j));
            }
        }
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void printBoard(){
        /*for (int row = 0; row < dimension;){
            for (int col = 0; col < dimension;){
                System.out.println("---------------");
                System.out.println("| " + board.get(row).get(col).getPlayer().getSymbol().getSymbol() + " |");
                col++;
            }
            row++;
        }*/

        for (List<Cell> cells : board) {
            for (Cell cell : cells) {
                cell.display();
            }
            System.out.println();
        }
    }
}

