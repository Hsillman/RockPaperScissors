package com.game.rpsgame.game;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.rpsgame.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/game")
public class GameController {

    private final GameService gameService;
    ObjectMapper mapper = new ObjectMapper();


    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game>  getThegame() {
        return  gameService.getListOfGames();

    }

    @PostMapping
    public List<Game> registerNewGame(@RequestBody Player player){
         return gameService.addNewGame(player);
    }

    @PostMapping(path = "{id}/join")
    public List<Game>  requestJoinGame(@PathVariable("id") Long id , @RequestBody Player player){
        return  gameService.requestJoinGame(id,player);
    }

    @PostMapping(path = "{id}/end")
    public List<Game>  endGame(@PathVariable("id") Long id ){
        return  gameService.endGame(id);
    }

    @PostMapping(path = "{id}/{name}/play")
    public List<Game> makeAplay(@PathVariable("id") Long id,@PathVariable("name") String name ){
        return gameService.makeAplay(id,name);
    }



}
