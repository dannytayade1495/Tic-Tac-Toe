package tictactoe.dto;

import tictactoe.exceptions.DuplicateSymbolException;
import tictactoe.exceptions.InvalidBotCountException;
import tictactoe.exceptions.InvalidPlayerCountException;
import tictactoe.strategy.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextPlayerMoveIndex;
    private List<WinningStrategy> winningStrategies;

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.winner = null;
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerMoveIndex = 0;
        this.winningStrategies = winningStrategies;
    }

    public void makeMove() {
        Player currentPlayer = players.get(nextPlayerMoveIndex);
        System.out.println(currentPlayer.getPlayerName() + "'s turn!");
        Move move = currentPlayer.makeMove(board);
        System.out.println(currentPlayer.getPlayerName() + " has made a move at " + move.getCell().getRow() + " " + move.getCell().getColumn() + "!");

        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();

        Cell cellToChange = board.getBoard().get(row).get(col);
        if (cellToChange.getCellState().equals(CellState.FILLED)) {
            System.out.println("The cell is already filled! Please choose another one!");
            return;
        } else {
            cellToChange.setCellState(CellState.FILLED);
            cellToChange.setPlayer(currentPlayer);

            Move finalMove = new Move(currentPlayer, cellToChange);
            moves.add(finalMove);

            nextPlayerMoveIndex += 1;
            nextPlayerMoveIndex %= players.size();

            if (checkWinner(finalMove)) {
                winner = currentPlayer;
                gameState = GameState.ENDED;
            } else if (moves.size() == board.getDimension() * board.getDimension()) {
                gameState = GameState.DRAW;
            }
        }

    }

    private boolean checkWinner(Move move) {
        for (WinningStrategy winningStrategy : winningStrategies) {
            if (winningStrategy.checkWinner(board, move)) {
                return true;
            }
        }
        return false;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Player getWinner() {
        return winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public static GameBuilder getBuilder() {
        return new GameBuilder();
    }

    public void printBoard() {
        board.printBoard();
    }

    public void undoMove() {
        Move move = null;
        if (moves.size() == 0) {
            System.out.println("No move made yet!");
            return;
        } else {
            move = moves.get(moves.size() - 1);
            moves.remove(move);
            Cell cell = move.getCell();
            cell.setCellState(CellState.EMPTY);
            cell.setPlayer(null);
            nextPlayerMoveIndex -= 1;
            if (nextPlayerMoveIndex < 0) {
                nextPlayerMoveIndex += players.size();
            }
        }
        if (move != null){
            for (WinningStrategy winningStrategy : winningStrategies) {
                winningStrategy.handleUndo(board, move);
            }
        }

    }

    public static class GameBuilder {
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public GameBuilder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private void validatePlayerCount() throws InvalidPlayerCountException {
            if (players.size() != dimension - 1) {
                throw new InvalidPlayerCountException("Player count must be " + (dimension - 1) + " for this game");
            }
        }

        private void validateBotCount() throws InvalidBotCountException {
            int botCount = 0;
            for (Player player : players) {
                if (player.getPlayerType().equals(PlayerType.BOT)) {
                    botCount++;
                }
            }
            if (botCount > 1) {
                throw new InvalidBotCountException("There can be only 1 bot per game");
            }
        }

        private void validateDifferentSymbols() throws DuplicateSymbolException {
            HashSet<Symbol> symbols = new HashSet<>();
            for (Player player : players) {
                symbols.add(player.getSymbol());
            }

            if (symbols.size() != players.size()) {
                throw new DuplicateSymbolException("Every player needs to have a unique symbol");
            }
        }

        private void validate() throws InvalidBotCountException, DuplicateSymbolException, InvalidPlayerCountException {
            validateBotCount();
            validateDifferentSymbols();
            validatePlayerCount();
        }

        public Game build() throws InvalidBotCountException, DuplicateSymbolException, InvalidPlayerCountException {
            validate();
            return new Game(dimension, players, winningStrategies);
        }
    }
}
