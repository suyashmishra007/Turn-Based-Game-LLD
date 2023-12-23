package org.example.game;

public interface Board {
    void move(Move move);
    Board copy();
}
