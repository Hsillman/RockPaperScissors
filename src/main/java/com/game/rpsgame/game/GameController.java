package com.game.rpsgame.game;

import com.game.rpsgame.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/game")
public class GameController {

    private final GameService gameService;


    //This is saying that the gameService will be injected to the GameController constructor. This is why the class GameService is a @Service
    //This is called dependecy injection
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    //GET method for the home page (ip:8080/api/game)
    @GetMapping
    public String  getThegame() {
        //Checks if the game is not null. If the game is null then it returns that there are no games available
        //If the game isn't null, checks if it has finished (and tell the user that the game must be closed if thats the case)
        //There is a check to see if the number of players is lower than 2 (if thats the case, then it returns that there is only one player in a game)
        //If the game contains 2 players, it checks to see whose turn it is (that is, who has the move set to null). Prints some information about the game as well.
        //Finally there is an else that simply returns the game info
        if(gameService.getTheGame() != null) {
            if(gameService.getTheGame().getStatus().equals(Game.Status.GAME_FINISHED)){
                return "Please end the game with id " +gameService.getTheGame().getId().toString()+" in order to play another one";
            }
            if(gameService.getTheGame().getListOfPlayers().size() < 2 ){
                return "There is a game going on right now. The id of the game is " + gameService.getTheGame().getId().toString() +
                        " and the number of rounds is set to " + gameService.getTheGame().getNumberOfRounds() +
                        ". There is only one player in the game: " + gameService.getTheGame().getListOfPlayers().get(0).getName();
            }else {
                if (gameService.getTheGame().getListOfPlayers().get(0).getMove() == null){
                    return "There is a game going on right now. The id of the game is " + gameService.getTheGame().getId().toString() +
                            " and the number of rounds is set to " + gameService.getTheGame().getNumberOfRounds() +
                            ". These are the players in the game: " + gameService.getTheGame().getListOfPlayers().get(0).getName() +
                            " and " + gameService.getTheGame().getListOfPlayers().get(1).getName() + ". Now its time for "+
                            gameService.getTheGame().getListOfPlayers().get(0).getName() + " to make a play.";
                }else if (gameService.getTheGame().getListOfPlayers().get(1).getMove() == null){
                    return "There is a game going on right now. The id of the game is " + gameService.getTheGame().getId().toString() +
                            " and the number of rounds is set to " + gameService.getTheGame().getNumberOfRounds() +
                            ". These are the players in the game: " + gameService.getTheGame().getListOfPlayers().get(0).getName() +
                            " and " + gameService.getTheGame().getListOfPlayers().get(1).getName() + ". Now its time for "+
                            gameService.getTheGame().getListOfPlayers().get(1).getName() + " to make a play.";
                }else{
                    return "There is a game going on right now. The id of the game is " + gameService.getTheGame().getId().toString() +
                            " and the number of rounds is set to " + gameService.getTheGame().getNumberOfRounds() +
                            ". These are the players in the game: " + gameService.getTheGame().getListOfPlayers().get(0).getName() +
                            " and " + gameService.getTheGame().getListOfPlayers().get(1).getName();
                }

            }

        }
        return "There are no games available. Please start one!";
    }

    //POST method of the home page (ip:8080/api/game)
    @PostMapping
    public Game registerNewGame(@RequestBody Player player){
         return gameService.addNewGame(player);
    }

    //POST method for when a player joins a game
    @PostMapping(path = "{id}/join")
    public Game requestJoinGame(@PathVariable("id") Long id , @RequestBody Player player){
        return  gameService.requestJoinGame(id,player);
    }

    //POST method for when the game is ended by someone
    @PostMapping(path = "{id}/end")
    public Game  endGame(@PathVariable("id") Long id ){
        return  gameService.endGame(id);
    }

    //POST method for a player to make a play
    @PostMapping(path = "{id}/{name}/play")
    public Game makeAplay(@PathVariable("id") Long id,@PathVariable("name") String name ){
        return gameService.makeAplay(id,name);
    }



}
