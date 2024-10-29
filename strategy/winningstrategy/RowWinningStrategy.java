package tictactoe.strategy.winningstrategy;

import tictactoe.dto.Board;
import tictactoe.dto.Move;
import tictactoe.dto.Symbol;

import java.util.HashMap;

public class RowWinningStrategy implements  WinningStrategy {

    private HashMap<Integer, HashMap<Symbol, Integer>> counts = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol sym = move.getPlayer().getSymbol();
        if (!counts.containsKey(row)) {
            counts.put(row, new HashMap<>());
        }
        HashMap<Symbol, Integer> rowMap = counts.get(row);
        if (!rowMap.containsKey(sym)){
            rowMap.put(sym, 0);
        }
        rowMap.put(sym, rowMap.get(sym) + 1);
        if (rowMap.get(sym) == board.getDimension()) {
            return true;
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol sym = move.getPlayer().getSymbol();
        HashMap<Symbol, Integer> rowMap = counts.get(row);
        rowMap.put(sym, rowMap.get(sym) - 1);
    }
}
