package org.example.placements;

import java.util.Optional;

import org.example.boards.TicTacToeBoard;
import org.example.game.Cell;
import org.example.game.Move;
import org.example.game.Player;
import org.example.helper.Utils;
public class OffensivePlacement implements Placement {

  private OffensivePlacement() {

  }
  private static OffensivePlacement offensivePlacement;

  public synchronized static OffensivePlacement get() {
    offensivePlacement = (OffensivePlacement) Utils.getIfNull(offensivePlacement, OffensivePlacement::new);
    return offensivePlacement;
  }

  @Override
  public Optional<Cell> place(TicTacToeBoard board, Player player) {
    return Optional.ofNullable(offense(player, board));
  }

  @Override
  public Placement next() {
    return DefensivePlacement.get();
  }

  private Cell offense(Player player, TicTacToeBoard board) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board.getSymbol(i, j) == null) {
          Move move = new Move(new Cell(i, j), player);
          TicTacToeBoard boardCopy = board.copy();
          boardCopy.move(move);
          if (ruleEngine.getState(boardCopy).isOver()) {
            return move.getCell();
          }
        }
      }
    }
    return null;
  }
}