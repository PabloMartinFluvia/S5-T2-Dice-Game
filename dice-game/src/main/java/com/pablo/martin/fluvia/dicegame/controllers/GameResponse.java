package com.pablo.martin.fluvia.dicegame.controllers;

import com.pablo.martin.fluvia.dicegame.domain.models.Player;
import com.pablo.martin.fluvia.dicegame.domain.models.Roll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
public class GameResponse {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity<Void> forNewPlayer(Player player){
        URI location = ServletUriComponentsBuilder
                //.fromCurrentContextPath() //uri only server:port
                //.fromCurrentServletMapping() //idem (?)
                .fromCurrentRequestUri() // full path (without query params)
                //.fromCurrentRequest() //full current request (full path + query params)
                .path("/{id}") //.path() appends al path obtingut (.fromxxxx)
                //.queryParam(xxx) //si la uri requereix params (diferents maneres de fer-ho)
                .buildAndExpand(player.getPlayerId()) //passar els valors dels path variables i parametres de query
                .toUri();
        //201 without body, si es vol obtenir el recurs que es faci una petició GET a la uri especificada en location em el header
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Player> forUpdatedPlayer(Player player){
        return ResponseEntity.ok(player);
    }

    public ResponseEntity<Roll> forRoll(Roll roll){
        return ResponseEntity.ok(roll); //TODO
    }

    public ResponseEntity<Void> forDeletedRolls(){
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<Roll>> forRolls(List<Roll> rolls){
        return ResponseEntity.ok(rolls);
    }

    public ResponseEntity<Player> forRanked(Player player){ //potser seria millor usar un DTO
        return ResponseEntity.ok(player);
    }

    public ResponseEntity<List<Player>> forRanked(List<Player> players){
        return ResponseEntity.ok(players);
    }

    public ResponseEntity<AverageWinRateDto> forAverageWinRate(float winRate){
        return ResponseEntity.ok(new AverageWinRateDto(winRate));
    }

    public ResponseEntity<?> forExtremePlayers(List<Player> extremePlayers) {
        if (extremePlayers.size() > 1) { // there's a tie
            return ResponseEntity.ok(extremePlayers);
        } else if (extremePlayers.size() == 1) {
            return ResponseEntity.ok(extremePlayers.get(0));
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
