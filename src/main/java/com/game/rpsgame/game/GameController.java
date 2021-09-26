package com.game.rpsgame.game;

import com.game.rpsgame.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/game")
public class GameController {

    private final GameService gameService;


    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String  getThegame() {
        if(gameService.getTheGame() != null) {
            if(gameService.getTheGame().getStatus().equals(Game.Status.GAME_FINISHED)){
                return "Please end the game with id " +gameService.getTheGame().getId().toString()+" in order to play another one";
            }
            if(gameService.getTheGame().getListOfPlayers().size() < 2 ){
                return "There is a game going on right now. The id of the game is " + gameService.getTheGame().getId().toString() +
                        " and the number of rounds is set to " + gameService.getTheGame().getNumberOfRounds() +
                        ". There is only one player in the game: " + gameService.getTheGame().getListOfPlayers().get(0).getName();
            }else {
                return "There is a game going on right now. The id of the game is " + gameService.getTheGame().getId().toString() +
                        " and the number of rounds is set to " + gameService.getTheGame().getNumberOfRounds() +
                        ". These are the players in the game: " + gameService.getTheGame().getListOfPlayers().get(0).getName() +
                        " and " + gameService.getTheGame().getListOfPlayers().get(1).getName();
            }

        }
        return "There are no games available. Please start one!";
    }

    @PostMapping
    public Game registerNewGame(@RequestBody Player player){
         return gameService.addNewGame(player);
    }

    @PostMapping(path = "{id}/join")
    public Game requestJoinGame(@PathVariable("id") Long id , @RequestBody Player player){
        return  gameService.requestJoinGame(id,player);
    }

    @PostMapping(path = "{id}/end")
    public Game  endGame(@PathVariable("id") Long id ){
        return  gameService.endGame(id);
    }

    @PostMapping(path = "{id}/{name}/play")
    public Game makeAplay(@PathVariable("id") Long id,@PathVariable("name") String name ){
        return gameService.makeAplay(id,name);
    }



}
