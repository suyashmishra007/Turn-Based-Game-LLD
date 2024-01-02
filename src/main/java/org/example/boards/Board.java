package org.example.boards;

import org.example.game.Move;

// All board classes will extend generic responsibilities.
// EB, All board like chess , sudoku or circular boards.
public interface Board {
  Board move(Move move);
  Board copy();
}
