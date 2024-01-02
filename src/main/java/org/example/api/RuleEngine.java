package org.example.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.example.boards.*;
import org.example.boards.TicTacToeBoard.Symbol;
import org.example.game.*;
import org.example.placements.DefensivePlacement;
import org.example.placements.OffensivePlacement;
import org.example.placements.Placement;

public class RuleEngine {

  public Map<String, RuleSet> ruleMap = new HashMap<>();

  public GameInfo getInfo(CellBoard board) {
    if (board instanceof TicTacToeBoard) {
      TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
      GameState gameState = getState(ticTacToeBoard);
      for (Symbol symbol : Symbol.values()) {
        for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
            Player player = new Player(symbol.marker());
            if (ticTacToeBoard.getSymbol(i, j) != null) {
              TicTacToeBoard boardCopy = ticTacToeBoard.move(
                new Move(new Cell(i, j), player)
              );
              // Forced to make defensive move.
              // we still win after that move.
              DefensivePlacement defence = DefensivePlacement.get();
              Optional<Cell> defensiveCell = defence.place(
                boardCopy,
                player.flip()
              );
              if (defensiveCell.isPresent()) {
                boardCopy =
                  boardCopy.move(new Move(defensiveCell.get(), player.flip()));
                OffensivePlacement offence = OffensivePlacement.get();
                Optional<Cell> offensiveCell = offence.place(boardCopy, player);
                if (offensiveCell.isPresent()) {
                  return new GameInfoBuilder()
                    .isOver(gameState.isOver())
                    .winner(gameState.getWinner())
                    .hasFork(true)
                    .forkCell(new Cell(i, j))
                    .player(player.flip())
                    .build();
                }
              }
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
// for (int k = 0; k < 3; k++) {
//   for (int l = 0; l < 3; l++) {
//     forkCell = new Cell(k, l);
//     Board boardCopy1 = boardCopy.move(
//       new Move(forkCell, player.flip())
//     );
//     if (
//       getState(boardCopy1)
//         .getWinner()
//         .equals(player.flip().symbol())
//     ) {
//       canStillWin = true;
//       break;
//     }
//   }
//   if (canStillWin) {
//     break;
//   }
// }
