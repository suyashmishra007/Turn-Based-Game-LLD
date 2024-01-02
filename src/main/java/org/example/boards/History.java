package org.example.boards;

import java.util.ArrayList;
import java.util.List;

// Momento Design Pattern : Used to maintain history so that going back to any move in the past is super easy.
// ! We are storing the game state for every move made. Which is costly in terms of memory utilized.
// ! each of the boards  will also store their own History obejcts. which make it quite heavy.

// TODO:  1 -> We have to find a method for us to store history that requires lesser memory space.
// ANS : Can store Board Proxy.

public class History {

  List<Representation> boards = new ArrayList<>();

  public Representation getBoardAtMove(int moveIndex) {
    moveIndex = moveIndex - 1; // One based Indexing
    int initialSize = boards.size();
    for (int i = 0; i < initialSize - (moveIndex + 1); i++) {
      boards.remove(boards.size() - 1);
    }
    return boards.get(moveIndex);
  }

  public Representation undo() {
    boards.remove(boards.size() - 1);
    return boards.get(boards.size() - 1);
  }

  public void add(Representation representation) {
    boards.add(representation);
  }
}
