package org.example.game;

public class GameState {
    boolean isOver;

    public boolean isOver() {
        return isOver;
    }

    public String getWinner() {
        return winner;
    }

    String winner;

    public GameState(boolean isOver, String winner) {
        this.isOver = isOver;
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "isOver=" + isOver +
                ", winner='" + winner + '\'' +
                '}';
    }
}

