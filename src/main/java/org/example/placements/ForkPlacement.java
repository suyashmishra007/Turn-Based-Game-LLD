package org.example.placements;

import java.util.Optional;
import org.example.boards.TicTacToeBoard;
import org.example.game.Cell;
import org.example.game.GameInfo;
import org.example.game.Player;
import org.example.helper.Utils;

public class ForkPlacement implements Placement {

  private ForkPlacement() {}

  private static ForkPlacement forkPlacement;

  public static synchronized ForkPlacement get() {
    forkPlacement =
      (ForkPlacement) Utils.getIfNull(forkPlacement, ForkPlacement::new);
    return forkPlacement;
  }

  @Override
  public Optional<Cell> place(TicTacToeBoard board, Player player) {
    Cell best = null;
    GameInfo gameInfo = ruleEngine.getInfo(board);
    if (gameInfo.isHasFork()) {
      best = gameInfo.getForkCell();
    }
    return Optional.ofNullable(best);
  }

  @Override
  public Placement next() {
    return CenterPlacement.get();
  }
}
