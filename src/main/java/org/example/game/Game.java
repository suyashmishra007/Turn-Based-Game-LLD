package org.example.game;

import org.example.boards.Board;

public class Game {

  private GameConfig gameConfig;
  private Board board;
  private String winnner;

  public Game(GameConfig gameConfig, Board board, String winnner, int lastMoveInMillis, int maxTimePerPlayer, int maxTimePerMove) {
    this.gameConfig = gameConfig;
    this.board = board;
    this.winnner = winnner;
    this.lastMoveInMillis = lastMoveInMillis;
    this.maxTimePerPlayer = maxTimePerPlayer;
    this.maxTimePerMove = maxTimePerMove;
  }

  private int lastMoveInMillis;
  private int maxTimePerPlayer;
  private int maxTimePerMove;

  public void move(Move move, int timeStampInMillis) {
    int timeTakenSinceLastMove = timeStampInMillis - lastMoveInMillis;
    move.getPlayer().setTimeTake(timeTakenSinceLastMove);
    if (gameConfig.timed) {
      moveForTimedGame(move, timeTakenSinceLastMove);
    } else {
      board.move(move);
    }
  }

  private void moveForTimedGame(Move move, int timeTakenSinceLastMove) {
    final int currentTime,endTime;
    if(gameConfig.timePerMove != null){
      currentTime = timeTakenSinceLastMove;
      endTime = maxTimePerMove;
    } else {
      currentTime = move.getPlayer().getTimeUsedInMillis();
      endTime = maxTimePerPlayer;
    }
    if (currentTime < endTime) {
        board.move(move);
    } else {
      winnner = move.getPlayer().flip().symbol();
    }
  }

  public void setConfig(GameConfig gameConfig) {
    this.gameConfig = gameConfig;
  }
}
