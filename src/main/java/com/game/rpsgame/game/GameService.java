package com.game.rpsgame.game;

import com.game.rpsgame.player.Player;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.game.rpsgame.player.Player.Move.*;
import static com.game.rpsgame.player.Player.Strategy.*;
import static com.game.rpsgame.game.Game.Status.*;

@Service
public class GameService {

    private Game theGame;
    public Game getTheGame() { return theGame; }
    public void setTheGame(Game theGame) {this.theGame = theGame;}


    public Game addNewGame(Player player) {
        if(this.getTheGame() == null ) {
            Game g = new Game();
            g.generateLongId();
            g.setStatus(START);
            g.setNumberOfRounds(player.getNumberOfRounds());
            g.addPlayerToGame(player);
            g.getScoreBoard().put(player.getName(), 0);
            this.setTheGame(g);
            return this.getTheGame();
        }else{
            throw new IllegalStateException("There is a game going on!! Unable to start another one");
        }


    }

    public Game  requestJoinGame(Long id, Player player) {

        Boolean foundGame = false;
            if(this.getTheGame().getId().equals(id) && this.getTheGame().getListOfPlayers().size() < 2){
                if(this.getTheGame().getListOfPlayers().get(0).getNumberOfRounds() == player.getNumberOfRounds()) {
                    this.getTheGame().addPlayerToGame(player);
                    this.getTheGame().setStatus(JOINED);
                    this.getTheGame().setNumberOfRounds(player.getNumberOfRounds());
                    this.getTheGame().getScoreBoard().put(player.getName(),0);
                    foundGame = true;
                }else{
                    throw new IllegalStateException("The players must agree on the total number of rounds");
                }
            }else if (this.getTheGame().getId().equals(id) && foundGame == false){
                throw new IllegalStateException("Failed to join the room");
            }else{
                throw new IllegalStateException("Failed to join the room");
            }


        return this.getTheGame();
    }



    public Game makeAplay(Long id, String name) {

            if(this.getTheGame().getId().equals(id) ){
                if (this.getTheGame().getStatus().equals(GAME_FINISHED)) {
                    throw new IllegalStateException("This game has ended, the result was " + this.getTheGame().getScoreBoard());
                }
                List<Player> players = this.getTheGame().getListOfPlayers();
                if (this.getTheGame().getStatus().equals(ROUND_FINISHED)){
                    for (Player player : players) {
                        player.setMove(null);
                    }
                    this.getTheGame().setStatus(JOINED);
                }
                for(Player p : players){
                    if(p.getName().equals(name) ){
                        if(players.size() < 2 ){
                            throw new IllegalStateException("Wait for the opponent to join in order to make a play");
                        }
                        if(p.getMove() == null){

                            if(players.indexOf(p) == 1){
                                if(players.get(0).getOpponentStrategy().equals(ROCKS)){
                                    p.setMove(ROCK);
                                }else{
                                    p.setRandomMove();
                                }
                            }else{
                                if(players.get(1).getOpponentStrategy().equals(ROCKS)){
                                    p.setMove(ROCK);
                                }else{
                                    p.setRandomMove();
                                }
                            }

                            if(this.getTheGame().getStatus().equals(WAITING_FOR_PLAY)){
                                this.getTheGame().setStatus(ROUND_FINISHED);
                            }else {
                                this.getTheGame().setStatus(WAITING_FOR_PLAY);
                            }
                            if(this.getTheGame().getStatus().equals(ROUND_FINISHED)){
                                this.getTheGame().computeResult(players,p,this.getTheGame());
                            }
                        }else{
                            throw new IllegalStateException("This player has made a move already");
                        }
                    }else{
                        if(players.size() < 2 || ( !players.get(0).getName().equals(name)  && !players.get(1).getName().equals(name) ) ){
                            throw new IllegalStateException("You are not part of this game!! Cannot make a play");
                        }
                    }
                }
                if (this.getTheGame().getScoreBoard().values().stream().mapToInt(Integer::valueOf).sum() == this.getTheGame().getNumberOfRounds() && this.getTheGame().getStatus().equals(ROUND_FINISHED)) {
                    this.getTheGame().setStatus(GAME_FINISHED);
                }
            }else{
                throw new IllegalStateException("Game with id "+ id.toString() + " not found!!");
            }

        return this.getTheGame();


    }



    public Game endGame(Long id) {

            if (this.getTheGame().getId().equals(id)) {
                if(this.getTheGame().getStatus().equals(GAME_FINISHED)) {
                    this.setTheGame(null);
                    return this.getTheGame();
                }else{
                    throw new IllegalStateException("This game is not over yet!!!");
                }
            }else{
                throw new IllegalStateException("Game with id "+ id.toString() + " not found!!");
            }

    }




}
