package org.example.game;

public class Player {

  private int timeUsedInMillis;
  private String playerSymbol;
  private User id;

  public Player(String playerSymbol) {
    this.playerSymbol = playerSymbol;
  }

  public String symbol() {
    return playerSymbol;
  }

  public Player flip() {
    return new Player(playerSymbol.equals("O") ? "X" : "O");
  }

  public void setTimeTake(int timeInMillis) {
    timeUsedInMillis += timeInMillis;
  }

  public int getTimeUsedInMillis() {
    return timeUsedInMillis;
  }
}
