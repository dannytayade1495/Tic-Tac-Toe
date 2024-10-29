package tictactoe.controller;

import tictactoe.dto.Cell;
import tictactoe.dto.Game;
import tictactoe.dto.Move;
import tictactoe.dto.Player;
import tictactoe.exceptions.DuplicateSymbolException;
import tictactoe.exceptions.InvalidBotCountException;
import tictactoe.exceptions.InvalidPlayerCountException;
import tictactoe.strategy.winningstrategy.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> strategies) throws InvalidBotCountException, DuplicateSymbolException, InvalidPlayerCountException {
        return Game.getBuilder().setDimension(dimension).setPlayers(players).setWinningStrategies(strategies).build();
    }

    public void printBoard(Game game) {
        game.printBoard();
//        Cell.displayCell = 1;
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public void undoMove(Game game) {
        game.undoMove();
    }
}
