package tictactoe.dto;

import java.util.List;
import java.util.Scanner;

public class Player {
    private static Scanner scanner = new Scanner(System.in);
    private String playerName;
    private Symbol symbol;
    private int id;
    private PlayerType playerType;

    public Player(String playerName, Symbol symbol, int id, PlayerType playerType) {
        this.playerName = playerName;
        this.symbol = symbol;
        this.id = id;
        this.playerType = playerType;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Move makeMove(Board board){
        System.out.print("Select the row number : ");
        int row = scanner.nextInt();
        System.out.print("Select the column number : ");
        int column = scanner.nextInt();
        Cell cell = new Cell(row, column);
        return new Move(this, cell);
    }
}
