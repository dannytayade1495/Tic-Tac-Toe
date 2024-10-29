package tictactoe.strategy.winningstrategy;

import tictactoe.dto.Board;
import tictactoe.dto.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
    void handleUndo(Board board, Move move);
}
