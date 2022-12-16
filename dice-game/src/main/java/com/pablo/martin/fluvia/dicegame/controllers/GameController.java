package com.pablo.martin.fluvia.dicegame.controllers;

import com.pablo.martin.fluvia.dicegame.domain.models.Player;
import com.pablo.martin.fluvia.dicegame.domain.models.Roll;
import com.pablo.martin.fluvia.dicegame.domain.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/players")
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
            @RequestParam(defaultValue = "{user.defaultName}") String name){ //param in url must be /players?name=xxx
        Player player = gameService.registerNewPlayer(name);
        return gameResponse.playerCreated(player);
    }

    @PutMapping
    public ResponseEntity<?> updateName(
            @RequestParam Long id,
            @RequestParam String name) { //param in url must be /players?id=yyy;name=xxx
        Player player = gameService.updateName(id, name);
        return gameResponse.playerCreated(player);
    }

    @PostMapping(path = "{id}/games")
    public ResponseEntity<?> newRoll(
            @RequestBody Roll nums, // nova tirada és POST -> petició té info dels daus
            @PathVariable Long id){
        Roll roll = gameService.newRoll(id,nums);
        return gameResponse.rollDone(roll);
    }

    @DeleteMapping(path = "{id}/games")
    public ResponseEntity<?> deleteRolls(@PathVariable Long id){
        gameService.deleteRolls(id);
        return gameResponse.rollsDeleted();
    }
}
