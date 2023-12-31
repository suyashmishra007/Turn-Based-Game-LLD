package org.example.api;

import java.util.function.Function;
import org.example.boards.CellBoard;
import org.example.game.*;

public class Rule {

  Function<CellBoard, GameState> condition;

  public Rule(Function<CellBoard, GameState> condition) {
    this.condition = condition;
  }
}
