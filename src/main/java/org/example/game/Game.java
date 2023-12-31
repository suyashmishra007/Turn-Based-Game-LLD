package org.example.game;

import org.example.boards.Board;

public class Game {

  private GameConfig gameConfig;
  private Board board;
  private String winnner;
  private int lastMoveInMillis;
  private int maxTimePerPlayer;

  public void move(Move move, int timeStampInMillis) {
    int timeTakenSinceLastMove = timeStampInMillis - lastMoveInMillis;
    move.getPlayer().setTimeTake(timeTakenSinceLastMove);
    if (gameConfig.timed) {
      moveForTimedGame(move, timeTakenSinceLastMove);
    } else {
      board.move(move);
    }
  }

  private boolean moveMadeInTime(int timeTakenSinceLastMove) {
    return timeTakenSinceLastMove < maxTimePerPlayer;
  }

  private boolean moveMadeInTime(Player player) {
    return player.getTimeUsedInMillis() < maxTimePerPlayer;
  }

  private void moveForTimedGame(Move move, int timeTakenSinceLastMove) {
    if (gameConfig.timePerMove != null) {
      if (moveMadeInTime(timeTakenSinceLastMove)) {
        board.move(move);
      } else {
        winnner = move.getPlayer().flip().symbol();
      }
    } else {
      if (moveMadeInTime(move.getPlayer())) {
        board.move(move);
      } else {
        winnner = move.getPlayer().flip().symbol();
      }
    }
  }
}
