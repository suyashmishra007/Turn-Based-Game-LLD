package org.example.placements;

import java.util.Optional;
import org.example.api.Rule;
import org.example.api.RuleEngine;
import org.example.boards.TicTacToeBoard;
import org.example.game.*;

public interface Placement {
  RuleEngine ruleEngine = new RuleEngine();

  Optional<Cell> place(TicTacToeBoard board, Player player);

  Placement next();
}
