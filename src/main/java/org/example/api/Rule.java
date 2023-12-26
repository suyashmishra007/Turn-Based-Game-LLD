package org.example.api;

import java.util.function.Function;
import org.example.game.*;

public class Rule<T extends Board> {

  Function<T, GameState> condition;

  public Rule(Function<T, GameState> condition) {
    this.condition = condition;
  }
}
