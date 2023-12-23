package org.example.game;

public class Move {
    public static  Cell cell;
    public  Player player;

    public Player getPlayer() {
        return player;
    }

    public Move(Cell cell, Player player) {
        this.cell = cell;
        this.player = player;
    }

    public static Cell getCell(){
        return cell;
    }

}

