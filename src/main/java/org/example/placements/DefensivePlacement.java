package org.example.placements;

import java.util.Optional;
import org.example.boards.TicTacToeBoard;
import org.example.game.Cell;
import org.example.game.Move;
import org.example.game.Player;
import org.example.helper.Utils;

public class DefensivePlacement implements Placement {

  private static DefensivePlacement defensivePlacement;

  private DefensivePlacement() {}

  public static synchronized DefensivePlacement get() {
    defensivePlacement =
      (DefensivePlacement) Utils.getIfNull(
        defensivePlacement,
        DefensivePlacement::new
      );
    return defensivePlacement;
  }

  @Override
  public Optional<Cell> place(TicTacToeBoard board, Player player) {
    return Optional.ofNullable(defense(player, board));
  }

  @Override
  public Placement next() {
    return ForkPlacement.get();
  }

  private Cell defense(Player player, TicTacToeBoard board) {
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
