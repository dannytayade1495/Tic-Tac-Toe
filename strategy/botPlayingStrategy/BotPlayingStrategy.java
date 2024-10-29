package tictactoe.strategy.botPlayingStrategy;

import tictactoe.dto.Board;
import tictactoe.dto.Move;

public interface BotPlayingStrategy {

    Move makeMove(Board board);
}
