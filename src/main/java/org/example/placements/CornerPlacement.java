package org.example.placements;

import java.util.Optional;
import org.example.boards.TicTacToeBoard;
import org.example.game.Cell;
import org.example.game.Player;
import org.example.helper.Utils;

public class CornerPlacement implements Placement {

  private static CornerPlacement cornerPlacement;

  private CornerPlacement() {}

  public static synchronized CornerPlacement get() {
    cornerPlacement =
      (CornerPlacement) Utils.getIfNull(cornerPlacement, CornerPlacement::new);
    return cornerPlacement;
  }

  @Override
  public Optional<Cell> place(TicTacToeBoard board, Player player) {
    return Optional.ofNullable(placeInCorner(board));
  }

  @Override
  public Placement next() {
    return null;
  }

  public Cell placeInCorner(TicTacToeBoard board) {
    final int[][] corners = new int[][] {
      { 0, 0 },
      { 0, 2 },
      { 2, 0 },
      { 2, 2 },
    };
    for (int i = 0; i < 4; i++) {
      if (board.getSymbol(corners[i][0], corners[i][1]) == null) {
        return new Cell(corners[i][0], corners[i][1]);
      }
    }
    return null;
  }
}
