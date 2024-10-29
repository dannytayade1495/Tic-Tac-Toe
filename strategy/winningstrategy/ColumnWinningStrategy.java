package tictactoe.strategy.winningstrategy;

import tictactoe.dto.Board;
import tictactoe.dto.Move;
import tictactoe.dto.Symbol;

import java.util.HashMap;

public class ColumnWinningStrategy implements WinningStrategy {

    private HashMap<Integer, HashMap<Symbol, Integer>> counts = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getColumn();
        Symbol sym = move.getPlayer().getSymbol();
        if (!counts.containsKey(col)) {
            counts.put(col, new HashMap<>());
        }
        HashMap<Symbol, Integer> colMap = counts.get(col);
        if (!colMap.containsKey(sym)){
            colMap.put(sym, 0);
        }
        colMap.put(sym, colMap.get(sym) + 1);
        if (colMap.get(sym) == board.getDimension()) {
            return true;
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int col = move.getCell().getColumn();
        Symbol sym = move.getPlayer().getSymbol();
        HashMap<Symbol, Integer> colMap = counts.get(col);
        colMap.put(sym, colMap.get(sym) - 1);
    }
}
