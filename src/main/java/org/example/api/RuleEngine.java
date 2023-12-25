package org.example.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.example.boards.TicTacToeBoard;
import org.example.game.Board;
import org.example.game.GameState;

public class RuleEngine {

  // TODO: Implement fork logic. Section 5 : Video 1
  public Map<String, List<Rule<TicTacToeBoard>>> ruleMap = new HashMap<>();

  public RuleEngine() {
    String key = TicTacToeBoard.class.getName();
    ruleMap.put(key, new ArrayList<>());
    ruleMap
      .get(key)
      .add(
        new Rule<>(board -> outerTraversals((i, j) -> board.getSymbol(i, j)))
      );
    ruleMap
      .get(key)
      .add(
        new Rule<>(board -> outerTraversals((i, j) -> board.getSymbol(j, i)))
      );
    ruleMap
      .get(key)
      .add(new Rule<>(board -> traverse(i -> board.getSymbol(i, i))));
    ruleMap
      .get(key)
      .add(new Rule<>(board -> traverse(i -> board.getSymbol(i, 2 - i))));
    ruleMap
      .get(key)
      .add(
        new Rule<>(board -> {
          int countOfFilledCells = 0;
          for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
              if (board.getCell(i, j) != null) {
                countOfFilledCells++;
              }
            }
          }
          if (countOfFilledCells == 9) {
            return new GameState(true, "-");
          }
          return new GameState(false, "-");
        })
      );
  }

  public GameState getState(Board board) {
    if (board instanceof TicTacToeBoard) {
      TicTacToeBoard b = (TicTacToeBoard) board;
      List<Rule<TicTacToeBoard>> rules = ruleMap.get(
        TicTacToeBoard.class.getName()
      );
      for (Rule<TicTacToeBoard> r : rules) {
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

  private GameState outerTraversals(BiFunction<Integer, Integer, String> next) {
    GameState result = new GameState(false, "-");
    for (int i = 0; i < 3; i++) {
      final int ii = i;
      GameState traversal = traverse(j -> next.apply(ii, j));
      if (traversal.isOver()) {
        result = traversal;
        break;
      }
    }
    return result;
  }

  private GameState traverse(Function<Integer, String> traversal) {
    GameState result = new GameState(false, "-");
    boolean possibleStreak = true;
    for (int j = 0; j < 3; j++) {
      if (
        traversal.apply(j) == null ||
        !traversal.apply(0).equals(traversal.apply(j))
      ) {
        possibleStreak = false;
        break;
      }
    }
    if (possibleStreak) {
      result = new GameState(true, traversal.apply(0));
    }
    return result;
  }
}

class Rule<T extends Board> {

  Function<T, GameState> condition;

  public Rule(Function<T, GameState> condition) {
    this.condition = condition;
  }
}
/*
      GameState rowWin = outerTraversal((i, j) -> board1.getSymbol(i, j));
      if (rowWin.isOver()) return rowWin;

      GameState colWin = outerTraversal((i, j) -> board1.getSymbol(j, i));
      if (colWin.isOver()) return colWin;

      GameState diagWin = traverse(i -> board1.getSymbol(i, i));
      if (diagWin.isOver()) return diagWin;

      GameState revDiagWin = traverse(i -> board1.getSymbol(i, 2 - i));
      if (revDiagWin.isOver()) return revDiagWin;

      int countOfFilledCells = 0;

      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (board1.getCell(i, j) != null) {
            countOfFilledCells++;
          }
        }
      }
      if (countOfFilledCells == 9) {
        return new GameState(true, firstCharacter);
      }
      return new GameState(false, firstCharacter);
    } else {
      return new GameState(false, "-");
*/
