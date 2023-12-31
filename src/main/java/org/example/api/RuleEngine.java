package org.example.api;

import java.util.HashMap;
import java.util.Map;
import org.example.boards.Board;
import org.example.boards.CellBoard;
import org.example.boards.TicTacToeBoard;
import org.example.game.*;

public class RuleEngine {

  public Map<String, RuleSet> ruleMap = new HashMap<>();

  public GameInfo getInfo(TicTacToeBoard board) {
    if (board instanceof TicTacToeBoard) {
      GameState gameState = getState(board);
      Cell forkCell = null;
      String[] players = new String[] { "X", "O" };
      for (int index = 0; index < 2; index++) {
        for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
            TicTacToeBoard boardCopy = board.copy();
            String playerSymbol = players[index];
            Player player = new Player(playerSymbol);
            boardCopy.move(new Move(new Cell(i, j), player));
            boolean canStillWin = false;
            for (int k = 0; k < 3; k++) {
              for (int l = 0; l < 3; l++) {
                TicTacToeBoard boardCopy1 = boardCopy.copy();
                forkCell = new Cell(k, l);
                boardCopy1.move(new Move(forkCell, player.flip()));
                if (
                  getState(boardCopy1)
                    .getWinner()
                    .equals(player.flip().symbol())
                ) {
                  canStillWin = true;
                  break;
                }
              }
              if (canStillWin) {
                break;
              }
            }
            if (canStillWin) {
              return new GameInfoBuilder()
                .isOver(gameState.isOver())
                .winner(gameState.getWinner())
                .hasFork(true)
                .forkCell(forkCell)
                .player(player.flip())
                .build();
            }
          }
        }
      }
      return new GameInfoBuilder()
        .isOver(gameState.isOver())
        .winner(gameState.getWinner())
        .build();
    } else {
      throw new IllegalArgumentException();
    }
  }

  public RuleEngine() {
    ruleMap.put(TicTacToeBoard.class.getName(), TicTacToeBoard.getRules());
  }

  public GameState getState(Board board) {
    if (board instanceof TicTacToeBoard) {
      TicTacToeBoard b = (TicTacToeBoard) board;
      for (Rule r : ruleMap.get(TicTacToeBoard.class.getName())) {
        GameState gameState = r.condition.apply(b);
        if (gameState.isOver()) {
          return gameState;
        }
      }
      return new GameState(false, "-");
    } else {
      throw new IllegalArgumentException();
    }
  }
}
