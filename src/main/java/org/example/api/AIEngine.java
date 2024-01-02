package org.example.api;

import java.util.Optional;
import org.example.boards.Board;
import org.example.boards.TicTacToeBoard;
import org.example.game.*;
import org.example.placements.OffensivePlacement;
import org.example.placements.Placement;

public class AIEngine {

  RuleEngine ruleEngine = new RuleEngine();

  private static Cell getBasicMove(TicTacToeBoard board1) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board1.getCell(i, j) == null) {
          return new Cell(i, j);
        }
      }
    }
    throw new IllegalArgumentException();
  }

  public Move suggestMove(Player player, Board board) {
    if (board instanceof TicTacToeBoard) {
      TicTacToeBoard board1 = (TicTacToeBoard) board;
      Cell suggestion;
      int threshold = 3;
      if (countMoves(board1) < threshold) {
        suggestion = getBasicMove(board1);
      } else if (countMoves(board1) < threshold + 1) {
        suggestion = getCellToPlay(player, board1);
      } else if (player.getTimeUsedInMillis() > 10000) {
        suggestion = getBasicMove(board1);
      } else {
        suggestion = getOptimalMove(player, board1);
      }
      if (suggestion != null) return new Move(suggestion, player);
      throw new IllegalArgumentException();
    }
    throw new IllegalArgumentException();
  }

  private Cell getOptimalMove(Player player, TicTacToeBoard board) {
    Placement placement = OffensivePlacement.get();
    while (placement != null) {
      Optional<Cell> place = placement.place(board, player);
      if (place.isPresent()) {
        return place.get();
      }
      placement = placement.next();
    }
    return null;
  }

  private Cell getCellToPlay(Player player, TicTacToeBoard board) {
    // 2 Cases :
    // case 1.Can AI win with this move. (Make the winning move)
    // case 2.Will Human win with their next move. (Block human from winning)
    RuleEngine ruleEngine = new RuleEngine();

    Cell best = offence(player, board);
    if (best != null) return best;

    best = defence(player, board);
    if (best != null) return best;

    return getBasicMove(board);
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

  // TODO: Refactor Offence and Defence
  private Cell offence(Player player, TicTacToeBoard board) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board.getSymbol(i, j) == null) {
          Move move = new Move(new Cell(i, j), player);
          TicTacToeBoard boardCopy = board.move(move);
          if (ruleEngine.getState(boardCopy).isOver()) {
            return new Cell(i, j);
          }
        }
      }
    }
    return null;
  }

  private Cell defence(Player player, TicTacToeBoard board) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board.getSymbol(i, j) == null) {
          Move move = new Move(new Cell(i, j), player.flip());
          TicTacToeBoard boardCopy = board.move(move);
          if (ruleEngine.getState(boardCopy).isOver()) {
            return new Cell(i, j);
          }
        }
      }
    }
    return null;
  }
}
