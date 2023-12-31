package org.example.placements;

import java.util.Optional;
import org.example.boards.TicTacToeBoard;
import org.example.game.Cell;
import org.example.game.Player;
import org.example.helper.Utils;

public class CenterPlacement implements Placement {

  private static CenterPlacement centerPlacement;

  private CenterPlacement() {}

  public static synchronized CenterPlacement get() {
    centerPlacement =
      (CenterPlacement) Utils.getIfNull(centerPlacement, CenterPlacement::new);
    return centerPlacement;
  }

  @Override
  public Optional<Cell> place(TicTacToeBoard board, Player player) {
    Cell center = null;
    if (board.getSymbol(1, 1) == null) {
      center = new Cell(1, 1);
    }
    return Optional.ofNullable(center);
  }

  @Override
  public Placement next() {
    return CornerPlacement.get();
  }
}
