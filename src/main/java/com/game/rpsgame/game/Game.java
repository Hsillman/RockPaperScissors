package com.game.rpsgame.game;

import com.game.rpsgame.player.Player;

import java.util.*;

import static com.game.rpsgame.player.Player.Move.*;
import static com.game.rpsgame.player.Player.Move.PAPER;

public class Game {
    private List<Player> listOfPlayers = new ArrayList<>();
    private Long id;
    private int numberOfRounds;
    private Map<String, Integer> scoreBoard = new HashMap<String, Integer>();
    public enum Status {START,JOINED,WAITING_FOR_PLAY,ROUND_FINISHED,GAME_FINISHED}
    private Status status;

    //this will comput the result of the rounds. It receives a list of the players in the match, a player p and the game itself
    //Notice that it has the same logic. Only thing that cheanges is the player index
    //For example if the game has a list of players [player1,player2] and player1 plays ROCK and player2 plays PAPER, I need to know which player made which move so
    //I can determine the outcome. If player1 (index 0) played ROCK and player2 (index 1) played PAPER, player2 wins
    public void computeResult(List<Player> players, Player p, Game g) {
        if(players.indexOf(p) == 1){
            if( ((p.getMove().equals(ROCK) && players.get(0).getMove().equals(SCISSORS)) || (p.getMove().equals(PAPER) && players.get(0).getMove().equals(ROCK)) || (p.getMove().equals(SCISSORS) && players.get(0).getMove().equals(PAPER))) && !(p.getMove() == players.get(0).getMove()) ){
                g.getScoreBoard().put(p.getName(), g.getScoreBoard().get(p.getName()) +1);
            }else if (!(p.getMove() ==  players.get(0).getMove())){
                g.getScoreBoard().put(players.get(0).getName(), g.getScoreBoard().get(players.get(0).getName()) +1);
            }else{
                g.getScoreBoard().put("Ties", g.getScoreBoard().get("Ties") +1);
            }
        }else{
            if( ((p.getMove().equals(ROCK) && players.get(1).getMove().equals(SCISSORS)) || (p.getMove().equals(PAPER) && players.get(1).getMove().equals(ROCK)) || (p.getMove().equals(SCISSORS) && players.get(1).getMove().equals(PAPER))) && !(p.getMove() ==  players.get(1).getMove()) ){
                g.getScoreBoard().put(p.getName(), g.getScoreBoard().get(p.getName()) +1);
            }else if (!(p.getMove() ==  players.get(1).getMove())){
                g.getScoreBoard().put(players.get(1).getName(), g.getScoreBoard().get(players.get(1).getName()) +1);
            }else{
                g.getScoreBoard().put("Ties", g.getScoreBoard().get("Ties") +1);
            }
        }
    }


    //starts the game with Ties set to 0 inside of the scoreBoard map
    public Game() {
        this.getScoreBoard().put("Ties",0);
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public Map<String, Integer> getScoreBoard() {
        return scoreBoard;
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

    //this will generate the id of the game
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
