package tictactoe;

import tictactoe.controller.GameController;
import tictactoe.dto.*;
import tictactoe.exceptions.DuplicateSymbolException;
import tictactoe.exceptions.InvalidBotCountException;
import tictactoe.exceptions.InvalidPlayerCountException;
import tictactoe.strategy.winningstrategy.ColumnWinningStrategy;
import tictactoe.strategy.winningstrategy.DiagonalWinningStrategy;
import tictactoe.strategy.winningstrategy.RowWinningStrategy;
import tictactoe.strategy.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InvalidBotCountException, DuplicateSymbolException, InvalidPlayerCountException {
        int dimension = 3;

        List<Player> players = new ArrayList<>(dimension - 1);
        players.add(new Player("Dhananjay", new Symbol('X'), 1, PlayerType.HUMAN));
        players.add(new Bot("Disha", new Symbol('O'), 2, PlayerType.BOT, BotDifficultyLevel.EASY));
//        players.add(new Player("Shubham", new Symbol('O'), 2, PlayerType.HUMAN));
//        players.add(new Bot("Akash", new Symbol('O'), 2, PlayerType.BOT, BotDifficultyLevel.MEDIUM));
        List<WinningStrategy> strategies = new ArrayList<>();
        strategies.add(new RowWinningStrategy());
        strategies.add(new ColumnWinningStrategy());
        strategies.add(new DiagonalWinningStrategy());

        GameController controller = new GameController();
        Game game = controller.startGame(dimension, players, strategies);

        controller.printBoard(game);

        while (game.getGameState().equals(GameState.IN_PROGRESS)) {
            game.makeMove();
            controller.printBoard(game);
            System.out.println("Do you want to undo your move? Y/N");
            String input = scanner.next();
            if (input.equals("Y")) {
                controller.undoMove(game);
            }
        }
        if (game.getGameState().equals(GameState.ENDED)) {
            controller.printBoard(game);
            System.out.println(game.getWinner().getPlayerName() + " won the game");
        } else {
            System.out.println("The game is DRAW");
        }
    }

}
