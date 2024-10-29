package tictactoe.strategy.winningstrategy;

import tictactoe.dto.Board;
import tictactoe.dto.Move;
import tictactoe.dto.Symbol;

import java.util.HashMap;

public class DiagonalWinningStrategy implements WinningStrategy {

    private HashMap<Symbol, Integer> leftDiagonal = new HashMap<>();
    private HashMap<Symbol, Integer> rightDiagonal = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int column = move.getCell().getColumn();
        Symbol sym = move.getPlayer().getSymbol();

        if (row == column){
            if(!leftDiagonal.containsKey(sym)){
                leftDiagonal.put(sym, 0);
            }
            leftDiagonal.put(sym, leftDiagonal.get(sym) + 1);
            if(leftDiagonal.get(sym) == board.getDimension()){
                return true;
            }
        }

        if (row + column == board.getDimension()-1){
            if(!rightDiagonal.containsKey(sym)){
                rightDiagonal.put(sym, 0);
            }
            rightDiagonal.put(sym, rightDiagonal.get(sym) + 1);
            if(rightDiagonal.get(sym) == board.getDimension()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();
        Symbol sym = move.getPlayer().getSymbol();
        if (row == col){
            leftDiagonal.put(sym, leftDiagonal.get(sym) - 1);
        }
        if (row + col == board.getDimension()-1){
            rightDiagonal.put(sym, rightDiagonal.get(sym) + 1);
        }
    }
}
