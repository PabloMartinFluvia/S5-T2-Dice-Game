package com.pablo.martin.fluvia.dicegame.controllers;

import com.pablo.martin.fluvia.dicegame.domain.models.Player;
import com.pablo.martin.fluvia.dicegame.domain.models.Roll;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GameResponse {

    public ResponseEntity<Player> playerCreated(Player player){
        return ResponseEntity.created(null).body(player); //TODO
    }

    public ResponseEntity<Player> nameUpdated(Player player){
        return ResponseEntity.ok(player);
    }

    public ResponseEntity<Roll> rollDone(Roll roll){
        return ResponseEntity.ok(roll); //TODO
    }

    public ResponseEntity<Void> rollsDeleted(){
        return ResponseEntity.noContent().build();
    }
}
