package com.pablo.martin.fluvia.dicegame.controllers;

import com.pablo.martin.fluvia.dicegame.domain.models.Player;
import com.pablo.martin.fluvia.dicegame.domain.models.Roll;
import com.pablo.martin.fluvia.dicegame.domain.services.GameService;
import com.pablo.martin.fluvia.dicegame.utils.validations.ValidId;
import com.pablo.martin.fluvia.dicegame.utils.validations.ValidRoll;
import com.pablo.martin.fluvia.dicegame.utils.validations.ValidString;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PropertySource("classpath:values.properties")
@RequestMapping(path = "${paths.players}")
@Validated
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
            @RequestParam(defaultValue = "${user.defaultName}") @ValidString String name){
        Player player = gameService.registerNewPlayer(name);
        return response.forNewPlayer(player);
    }

    @PutMapping
    public ResponseEntity<?> updatePlayerName(
            @RequestParam @ValidId long id,
            @RequestParam @ValidString String name) {
        Player player = gameService.updateName(id, name);
        return response.forUpdatedPlayer(player);
    }

    @PostMapping(path = "/{id}/games")
    public ResponseEntity<?> addPlayerRoll(
            @PathVariable @ValidId long id,
            @RequestBody @Valid Roll nums){
        Roll roll = gameService.addRoll(id,nums);
        return response.forRoll(roll);
    }

    @DeleteMapping(path = "/{id}/games")
    public ResponseEntity<?> deletePlayerRolls(@PathVariable @ValidId long id){
        gameService.deletePlayerRolls(id);
        return response.forDeletedRolls();
    }

    @GetMapping(path = "/{id}/games")
    public ResponseEntity<?> listPlayerRolls(@PathVariable @ValidId long id){
        List<Roll> rolls = gameService.getPlayerRolls(id);
        return response.forRolls(rolls);
    }

    @GetMapping(path = {"/{id}/ranking","/{id}"})
    public ResponseEntity<?> showPlayerWinRate(@PathVariable @ValidId long id){
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
}
