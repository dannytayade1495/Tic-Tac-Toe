package tictactoe.strategy.botPlayingStrategy;

import tictactoe.dto.Board;
import tictactoe.dto.Cell;
import tictactoe.dto.CellState;
import tictactoe.dto.Move;

import java.util.List;

public class EasyBotPlayingStartegy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board) {
        for (List<Cell> cells : board.getBoard()){
            for (Cell cell : cells){
                if (cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(null,cell);
                }
            }
        }
        return null;
    }
}
