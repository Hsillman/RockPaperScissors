package com.game.rpsgame.game;

import com.game.rpsgame.player.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.game.rpsgame.player.Player.Move.*;
import static com.game.rpsgame.player.Player.Strategy.*;
import static com.game.rpsgame.game.Game.Status.*;

@Service
public class GameService {

    private List<Game> listOfGames = new ArrayList<>();

    public List<Game> getListOfGames() {
        return listOfGames;
    }

//    public List<Long> getListOfGameId(){
//        List<Long> listOfGameId = new ArrayList<>();
//        for(Game g : this.getListOfGames()){
//            listOfGameId.add(g.getId());
//        }
//        return listOfGameId;
//    }

    public List<Game> addNewGame(Player player) {
            Game g  = new Game();
            g.generateLongId();
            g.setStatus(START);
            g.setNumberOfRounds(player.getNumberOfRounds());
            g.addPlayerToGame(player);
            g.getScoreBoard().put(player.getName(),0);
            listOfGames.add(g);
            return this.getListOfGames();

    }

    public List<Game>  requestJoinGame(Long id, Player player) {
        Boolean foundGame = false;
        for(Game g : listOfGames){
            if(g.getId().equals(id) && g.getListOfPlayers().size() < 2){
                if(g.getListOfPlayers().get(0).getNumberOfRounds() == player.getNumberOfRounds()) {
                    g.addPlayerToGame(player);
                    g.setStatus(JOINED);
                    g.setNumberOfRounds(player.getNumberOfRounds());
                    g.getScoreBoard().put(player.getName(),0);
                    foundGame = true;
                }else{
                    throw new IllegalStateException("The players must agree on the total number of rounds");
                }
            }else if (g.getId().equals(id) && foundGame == false){throw new IllegalStateException("Failed to join the room");}

        }
        return this.getListOfGames();
    }



    public List<Game> makeAplay(Long id, String name) {

        for(Game g : listOfGames){
            if(g.getId().equals(id) ){
                if (g.getStatus().equals(GAME_FINISHED)) {
                    throw new IllegalStateException("This game has ended, the result was " + g.getScoreBoard());
                }
                List<Player> players = g.getListOfPlayers();
                if (g.getStatus().equals(ROUND_FINISHED)){
                    for (Player player : players) {
                        player.setMove(null);
                    }
                    g.setStatus(JOINED);
                }
                for(Player p : players){
                    if(p.getName().equals(name) ){
                        if(p.getMove() == null){
                            if(p.getStrategy().equals(ROCKS)){
                                p.setMove(ROCK);
                            }
                            if(p.getStrategy().equals(RANDOM)){
                                p.setMove(p.setRandomMove());
                            }
                            if(g.getStatus().equals(WAITING_FOR_PLAY)){
                                g.setStatus(ROUND_FINISHED);
                            }else {
                                g.setStatus(WAITING_FOR_PLAY);
                            }
                            if(g.getStatus().equals(ROUND_FINISHED)){
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
                        }else{
                            throw new IllegalStateException("This player has made a move already");
                        }
                    }
                }
                if (g.getScoreBoard().values().stream().mapToInt(Integer::valueOf).sum() == g.getNumberOfRounds() && g.getStatus().equals(ROUND_FINISHED)) {
                    g.setStatus(GAME_FINISHED);
                }
            }
        }
        return this.getListOfGames();
    }

    public List<Game> endGame(Long id) {
        for(Game g : listOfGames) {
            if (g.getId().equals(id)) {
                if(g.getStatus().equals(GAME_FINISHED)) {
                    listOfGames.remove(g);
                    return this.getListOfGames();
                }else{
                    throw new IllegalStateException("This game is not over yet!!!");
                }
            }
        }
        return this.getListOfGames();
    }




}
