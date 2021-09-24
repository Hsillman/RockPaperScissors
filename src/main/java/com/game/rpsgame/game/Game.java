package com.game.rpsgame.game;

import com.game.rpsgame.player.Player;

import java.util.*;

public class Game {
    private List<Player> listOfPlayers = new ArrayList<>();
    private Long id;

    private int numberOfRounds;
    private Map<String, Integer> scoreBoard = new HashMap<String, Integer>();
    public enum Status {START,JOINED,WAITING_FOR_PLAY,ROUND_FINISHED,GAME_FINISHED}
    private Status status;

    public Game() {
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public Map<String, Integer> getScoreBoard() {
        return scoreBoard;
    }

    public void setScoreBoard(Map<String, Integer> scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public void addPlayerToGame(Player player){
            this.getListOfPlayers().add(player);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void generateLongId (){
         this.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Player> getListOfPlayers() {
        return listOfPlayers;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }
}
