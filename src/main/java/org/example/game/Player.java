package org.example.game;

public class Player {

  private String playerSymbol;

  public Player(String playerSymbol) {
    this.playerSymbol = playerSymbol;
  }

  public String symbol() {
    return playerSymbol;
  }

  public Player flip() {
    return new Player(playerSymbol.equals("O") ? "X" : "O");
  }
}
 