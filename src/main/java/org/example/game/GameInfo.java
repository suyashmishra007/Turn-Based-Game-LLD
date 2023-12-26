package org.example.game;

public class GameInfo {

  private boolean isOver;
  private String winner;
  private boolean hasFork;
  private Player player;

  public boolean isOver() {
    return isOver;
  }

  public String getWinner() {
    return winner;
  }

  public boolean isHasFork() {
    return hasFork;
  }

  public Player getPlayer() {
    return player;
  }

  public GameInfo(GameState gameState, boolean hasFork, Player player) {
    this.isOver = gameState.isOver();
    this.winner = gameState.getWinner();
    this.hasFork = hasFork;
    this.player = player;
  }
}
