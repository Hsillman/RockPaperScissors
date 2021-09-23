package com.game.rpsgame.game;

import com.game.rpsgame.player.Player;

import java.util.*;

public class Game {
    private List<Player> listOfPlayers = new ArrayList<>();
    private Long id;
    private String status;
    private int numberOfRounds;
    private Map<String, Integer> scoreBoard = new HashMap<String, Integer>();

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
