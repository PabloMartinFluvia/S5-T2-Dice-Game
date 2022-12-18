package com.pablo.martin.fluvia.dicegame.controllers;

import com.pablo.martin.fluvia.dicegame.domain.models.Player;
import com.pablo.martin.fluvia.dicegame.domain.models.Roll;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

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

    public ResponseEntity<List<Roll>> rollsReaded(List<Roll> rolls){
        return ResponseEntity.ok(rolls);
    }

    public ResponseEntity<Map<String,Object>> playerWinRateReaded(Player player){ //potser seria millor usar un DTO
        Map<String,Object> body = Map.of("Player id", player.getPlayerId(), "Win rate",player.getWinRate()); //TODO
        return ResponseEntity.ok(body);
    }

    public ResponseEntity<List<Player>> playersListed(List<Player> players){
        return ResponseEntity.ok(players);
    }

    public ResponseEntity<Map.Entry<String,Float>> averageWinRateDone(float winRate){ //potser seria millor usar un DTO
        Map.Entry<String,Float> body = new AbstractMap.SimpleEntry<>("Average win rate",winRate); //TODO
        return ResponseEntity.ok(body);
    }

    public ResponseEntity<?> extremPlayersFound(List<Player> extremePlayers){
        int minIndex = 0;
        if(extremePlayers.size() > minIndex){ //no tie
            return ResponseEntity.ok(extremePlayers.get(minIndex));
        }else {
            return ResponseEntity.ok(extremePlayers); //colection if there's a tie
        }
    }
}
