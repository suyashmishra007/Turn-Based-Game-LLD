package org.example.api;

import org.example.boards.TicTacToeBoard;
import org.example.game.*;

public class AIEngine {

  private static Move getBasicMove(Player Computer, TicTacToeBoard board1) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board1.getCell(i, j) == null) {
          return new Move(new Cell(i, j), Computer);
        }
      }
    }
    throw new IllegalArgumentException();
  }

  public Move suggestMove(Player computer, Board board) {
    if (board instanceof TicTacToeBoard) {
      TicTacToeBoard board1 = (TicTacToeBoard) board;
      Move suggestion;
      int threshold = 3;
      if (countMoves(board1) < threshold) {
        suggestion = getBasicMove(computer, board1);
      } else {
        suggestion = getSmartMove(computer, board1);
      }
      if (suggestion != null) return suggestion;
      throw new IllegalArgumentException();
    }
    throw new IllegalArgumentException();
  }

  private Move getSmartMove(Player player, TicTacToeBoard board) {
    // 2 Cases :
    // case 1.Can AI win with this move. (Make the winning move)
    // case 2.Will Human win with their next move. (Block human from winning)
    RuleEngine ruleEngine = new RuleEngine();

    // Victorious move
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board.getSymbol(i, j) == null) {
          Move move = new Move(new Cell(i, j), player);
          TicTacToeBoard boardCopy = board.copy();
          boardCopy.move(move);
          if (ruleEngine.getState(boardCopy).isOver()) {
            return move;
          }
        }
      }
    }
    // Defensive move
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board.getSymbol(i, j) == null) {
          Move move = new Move(new Cell(i, j), player.flip());
          TicTacToeBoard boardCopy = board.copy();
          boardCopy.move(move);
          if (ruleEngine.getState(boardCopy).isOver()) {
            return new Move(new Cell(i, j), player);
          }
        }
      }
    }
    return getBasicMove(player, board);
  }

  private int countMoves(TicTacToeBoard board) {
    int totalMovesHappen = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board.getCell(i, j) != null) {
          totalMovesHappen++;
        }
      }
    }
    return totalMovesHappen;
  }
}
