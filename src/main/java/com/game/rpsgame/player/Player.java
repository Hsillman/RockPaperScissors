package com.game.rpsgame.player;


import java.util.Random;

public class Player {
    private String name;
    public enum Strategy {ROCKS,RANDOM}
    public enum Move {ROCK,PAPER,SCISSORS}
    private Move move;
    private int numberOfRounds;
    private Strategy opponentStrategy;

    public Strategy getOpponentStrategy() {
        return opponentStrategy;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public void setRandomMove(){
        int pick = new Random().nextInt(Move.values().length);
        this.setMove(Move.values()[pick]);
    }

    public Player(String name, Strategy opponentStrategy, int numberOfRounds) {
        this.name = name;
        this.opponentStrategy = opponentStrategy;
        this.numberOfRounds = numberOfRounds;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }


    public int getNumberOfRounds() {
        return numberOfRounds;
    }

}
