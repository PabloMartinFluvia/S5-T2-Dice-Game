package com.pablo.martin.fluvia.dicegame.controllers;

import com.pablo.martin.fluvia.dicegame.domain.models.Player;
import com.pablo.martin.fluvia.dicegame.domain.models.Roll;
import com.pablo.martin.fluvia.dicegame.domain.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/players")
@PropertySource("classpath:values.properties")
public class GameController {

    private GameService gameService;

    private GameResponse gameResponse;

    @Autowired
    public GameController(GameService gameService, GameResponse gameResponse){
        this.gameService = gameService;
        this.gameResponse = gameResponse;
    }

    @PostMapping
    public ResponseEntity<?> registerPlayer(
            @RequestParam(defaultValue = "${user.defaultName}") String name){ //param in url must be /players?name=xxx
        Player player = gameService.registerNewPlayer(name);
        return gameResponse.playerCreated(player);
    }

    @PutMapping
    public ResponseEntity<?> updatePlayerName(
            @RequestParam Long id,
            @RequestParam String name) { //param in url must be /players?id=yyy;name=xxx
        Player player = gameService.updateName(id, name);
        return gameResponse.nameUpdated(player);
    }

    @PostMapping(path = "/{id}/games")
    public ResponseEntity<?> addPlayerRoll(
            @RequestBody Roll nums, // nova tirada és POST -> petició té info dels daus
            @PathVariable Long id){
        Roll roll = gameService.addRoll(id,nums);
        return gameResponse.rollDone(roll);
    }

    @GetMapping(path = "/{id}/games")
    public ResponseEntity<?> findPlayerRolls(@PathVariable Long id){
        List<Roll> rolls = gameService.findPlayerRolls(id);
        return gameResponse.rollsReaded(rolls);
    }

    @GetMapping(path = "/{id}/ranking")
    public ResponseEntity<?> findPlayerWinRate(@PathVariable Long id){
        Player player = gameService.findPlayer(id); //Potser no caldria TOT el Player
        return gameResponse.playerWinRateReaded(player);
    }

    @DeleteMapping(path = "/{id}/games")
    public ResponseEntity<?> deletePlayerRolls(@PathVariable Long id){
        gameService.deletePlayerRolls(id);
        return gameResponse.rollsDeleted();
    }

    @GetMapping
    public ResponseEntity<?> getAllPlayers(){
        List<Player> players = gameService.getAllPlayers();
        return gameResponse.playersListed(players);
    }

    @GetMapping(path = "/ranking")
    public ResponseEntity<?> getAverageWinRate(){
        float averageWinRate = gameService.getAverageWinRate();
        return gameResponse.averageWinRateDone(averageWinRate);
    }

    @GetMapping(path = "/ranking/loser")
    public ResponseEntity<?> findWorstPlayers(){
        List<Player> worstPlayers = gameService.findWorstPlayers(); //poden ser N en cas d'empat
        return gameResponse.extremPlayersFound(worstPlayers);
    }

    @GetMapping(path = "/ranking/winner")
    public ResponseEntity<?> findBestPlayers(){
        List<Player> bestPlayers = gameService.findBestPlayers(); //poden ser N en cas d'empat
        return gameResponse.extremPlayersFound(bestPlayers);
    }
}
