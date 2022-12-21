package com.pablo.martin.fluvia.dicegame.controllers;

import com.pablo.martin.fluvia.dicegame.domain.models.Player;
import com.pablo.martin.fluvia.dicegame.domain.models.Roll;
import com.pablo.martin.fluvia.dicegame.domain.services.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/players")
@PropertySource("classpath:values.properties")
public class GameController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private GameService gameService;

    private GameResponse response;

    @Autowired
    public GameController(GameService gameService, GameResponse response){
        this.gameService = gameService;
        this.response = response;
    }

    @PostMapping
    public ResponseEntity<?> registerPlayer(
            @RequestParam(defaultValue = "${user.defaultName}") String name){ //param in url must be /players?name=xxx
        Player player = gameService.registerNewPlayer(name);
        return response.forNewPlayer(player);
    }

    @PutMapping
    public ResponseEntity<?> updatePlayerName(
            @RequestParam Long id,
            @RequestParam String name) { //param in url must be /players?id=yyy;name=xxx
        Player player = gameService.updateName(id, name);
        return response.forUpdatedPlayer(player);
    }

    @PostMapping(path = "/{id}/games")
    public ResponseEntity<?> addPlayerRoll(
            @RequestBody Roll nums, // nova tirada és POST -> petició té info dels daus
            @PathVariable Long id){
        Roll roll = gameService.addRoll(id,nums);
        return response.forRoll(roll);
    }

    @DeleteMapping(path = "/{id}/games")
    public ResponseEntity<?> deletePlayerRolls(@PathVariable Long id){
        gameService.deletePlayerRolls(id);
        return response.forDeletedRolls();
    }

    @GetMapping(path = "/{id}/games")
    public ResponseEntity<?> listPlayerRolls(@PathVariable Long id){
        List<Roll> rolls = gameService.getPlayerRolls(id);
        return response.forRolls(rolls);
    }

    @GetMapping(path = {"/{id}/ranking","/{id}"})
    public ResponseEntity<?> showPlayerWinRate(@PathVariable Long id){
        Player player = gameService.getPlayerWinRated(id);
        return response.forRanked(player);
    }

    @GetMapping
    public ResponseEntity<?> listAllPlayersRanked(){
        List<Player> players = gameService.findAllPlayersRankedDesc();
        return response.forRanked(players);
    }

    @GetMapping(path = "/ranking")
    public ResponseEntity<?> getAverageWinRate(){
        float averageWinRate = gameService.getAverageWinRate();
        return response.forAverageWinRate(averageWinRate);
    }

    @GetMapping(path = "/ranking/winner")
    public ResponseEntity<?> findBestPlayers(){
        List<Player> bestPlayers = gameService.findBestPlayers(); //poden ser N en cas d'empat
        return response.forExtremePlayers(bestPlayers);
    }

    @GetMapping(path = "/ranking/loser")
    public ResponseEntity<?> findWorstPlayers(){
        List<Player> worstPlayers = gameService.findWorstPlayers(); //poden ser N en cas d'empat
        return response.forExtremePlayers(worstPlayers);
    }

    //-----------------------------------------------------------------------------


}
