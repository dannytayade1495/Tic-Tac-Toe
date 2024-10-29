package tictactoe.strategy.botPlayingStrategy;

import tictactoe.dto.*;

import java.util.List;

public class MediumBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board) {
        List<List<Cell>> cellsList = board.getBoard();
        for (int row = 0; row < board.getDimension(); row++){
            for (int col = 0; col < board.getDimension(); col++){
                Cell cell = cellsList.get(row).get(col);
                if (cell.getPlayer() != null){
                    if (cell.getPlayer().getPlayerType().equals(PlayerType.HUMAN)){
                        if (cellsList.get(row).get((col+1)%board.getDimension()).getCellState().equals(CellState.EMPTY)){
                            return new Move(null, cellsList.get(row).get((col+1)%board.getDimension()));
                        }
                    }
                }
            }
        }
        return null;
    }
}
