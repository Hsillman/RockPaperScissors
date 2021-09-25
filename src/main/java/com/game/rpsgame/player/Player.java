package com.game.rpsgame.player;


import java.util.Random;

public class Player {
    private String name;
    public enum Strategy {ROCKS,RANDOM}
    private Strategy strategy;
    public enum Move {ROCK,PAPER,SCISSORS}
    private Move move;
    private int numberOfRounds;
    private Strategy opponentStrategy;

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

    public Player(String name, Strategy strategy, int numberOfRounds) {
        this.name = name;
        this.strategy = strategy;
        this.numberOfRounds = numberOfRounds;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
