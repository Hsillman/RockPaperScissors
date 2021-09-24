package com.game.rpsgame.game;

import com.game.rpsgame.player.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.game.rpsgame.player.Player.Move.*;

@Service
public class GameService {

    private List<Game> listOfGames = new ArrayList<>();
    private Game thegame;
    private int counter = 0;

    public Game getThegame(){
        return thegame;
    }

    public List<Game> getListOfGames() {
        return listOfGames;
    }

    public List<Game> addNewGame(Player player) {
            Game g  = new Game();
            g.generateLongId();
            g.setStatus("START");
            g.setNumberOfRounds(player.getNumberOfRounds());
            g.addPlayerToGame(player);
            listOfGames.add(g);
            return this.getListOfGames();

    }

    public List<Game>  requestJoinGame(Long id, Player player) {
        Boolean foundGame = false;
        for(Game g : listOfGames){
            if(g.getId().equals(id) && g.getListOfPlayers().size() < 2){
                if(g.getListOfPlayers().get(0).getNumberOfRounds() == player.getNumberOfRounds()) {
                    g.addPlayerToGame(player);
                    g.setStatus("JOINED");
                    g.setNumberOfRounds(player.getNumberOfRounds());
                    foundGame = true;
                    thegame = g;
                }else{
                    throw new IllegalStateException("The players must agree on the total number of rounds");
                }
            }else if (g.getId().equals(id) && foundGame == false){throw new IllegalStateException("Failed to join the room");}

        }
        return this.getListOfGames();
    }

    public List<Game> makeAplay(Long id, String name) {
        if(counter == 2){
            for(Game game : listOfGames){
                if(game.getId().equals(id) ){
                    if (game.getScoreBoard().values().stream().mapToInt(Integer::valueOf).sum() == game.getNumberOfRounds()) {
                        listOfGames.remove(game);
                        throw new IllegalStateException("This game has ended, the result was "+game.getScoreBoard());
                    }
                    List<Player> list_players = game.getListOfPlayers();
                    for(Player player : list_players) {
                            player.setMove(null);
                    }
                }
            }
            counter = 0;
        }
           for(Game g : listOfGames){
               if(g.getId().equals(id) ){
                   //counter++;
                   List<Player> players = g.getListOfPlayers();
                   for(Player p : players){
                       if(p.getName().equals(name) ){
                            if(p.getMove() == null){
                                if(p.getStrategy().equals("rocksOnly")){
                                    p.setMove(ROCK);
                                }
                                if(p.getStrategy().equals("random")){
                                    p.setMove(p.setRandomMove());
                                }
                                counter++;
                                if(counter == 2){ //tirar
                                    if(players.indexOf(p) == 1){
                                        if( ((p.getMove().equals(ROCK) && players.get(0).getMove().equals(SCISSORS)) || (p.getMove().equals(PAPER) && players.get(0).getMove().equals(ROCK)) || (p.getMove().equals(SCISSORS) && players.get(0).getMove().equals(PAPER))) && !(p.getMove() == players.get(0).getMove()) ){
                                            if(g.getScoreBoard().containsKey(p.getName())){
                                                g.getScoreBoard().put(p.getName(), g.getScoreBoard().get(p.getName()) +1);
                                            }else{
                                                g.getScoreBoard().put(p.getName(),1);
                                            }
                                        }else if (!(p.getMove() ==  players.get(0).getMove())){
                                            if(g.getScoreBoard().containsKey(players.get(0).getName())){
                                                g.getScoreBoard().put(players.get(0).getName(), g.getScoreBoard().get(players.get(0).getName()) +1);
                                            }else{
                                                g.getScoreBoard().put(players.get(0).getName(),1);
                                            }
                                        }else{
                                            if(g.getScoreBoard().containsKey("Ties")){
                                                g.getScoreBoard().put("Ties", g.getScoreBoard().get("Ties") +1);
                                            }else{
                                                g.getScoreBoard().put("Ties",1);
                                            }
                                        }
                                    }else{
                                        if( ((p.getMove().equals(ROCK) && players.get(1).getMove().equals(SCISSORS)) || (p.getMove().equals(PAPER) && players.get(1).getMove().equals(ROCK)) || (p.getMove().equals(SCISSORS) && players.get(1).getMove().equals(PAPER))) && !(p.getMove() ==  players.get(1).getMove()) ){
                                            if(g.getScoreBoard().containsKey(p.getName())){
                                                g.getScoreBoard().put(p.getName(), g.getScoreBoard().get(p.getName()) +1);
                                            }else{
                                                g.getScoreBoard().put(p.getName(),1);
                                            }
                                        }else if (!(p.getMove() ==  players.get(1).getMove())){
                                            if(g.getScoreBoard().containsKey(players.get(1).getName())){
                                                g.getScoreBoard().put(players.get(1).getName(), g.getScoreBoard().get(players.get(1).getName()) +1);
                                            }else{
                                                g.getScoreBoard().put(players.get(1).getName(),1);
                                            }
                                        }else{
                                            if(g.getScoreBoard().containsKey("Ties")){
                                                g.getScoreBoard().put("Ties", g.getScoreBoard().get("Ties") +1);
                                            }else{
                                                g.getScoreBoard().put("Ties",1);
                                            }
                                        }
                                    }
                                }
                            }else{
                                throw new IllegalStateException("This player has made a move already");
                            }
                       }
                   }
               }
           }
        return this.getListOfGames();
    }
}
