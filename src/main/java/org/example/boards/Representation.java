package org.example.boards;

// Comman useCases : 1. Heavy Obejcts , 2.Connections
public class Representation {

  public Representation(TicTacToeBoard boards) {
    representation = boards.toString();
  }

  String representation;
}
