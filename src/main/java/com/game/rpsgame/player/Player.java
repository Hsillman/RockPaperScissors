package com.game.rpsgame.player;


import java.util.Random;

public class Player {
    private String name;
    private String strategy;
    private int score;
    public enum Move {ROCK,PAPER,SCISSORS}
    private Move move;
    private int numberOfRounds;

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public Move setRandomMove(){
        int pick = new Random().nextInt(Move.values().length);
        return Move.values()[pick];
    }


    public Player(String name, String strategy, int numberOfRounds) {
        this.name = name;
        this.strategy = strategy;
        this.numberOfRounds = numberOfRounds;
    }

    public Player(String name, String strategy, int score, int numberOfRounds) {
        this.name = name;
        this.strategy = strategy;
        this.score = score;
        this.numberOfRounds = numberOfRounds;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public String getStrategy() {
        return strategy;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }
}
