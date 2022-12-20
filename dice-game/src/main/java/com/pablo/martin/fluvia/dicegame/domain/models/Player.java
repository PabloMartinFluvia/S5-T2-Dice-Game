package com.pablo.martin.fluvia.dicegame.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pablo.martin.fluvia.dicegame.utils.PercentageSerializer;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class Player {

    private Long playerId; //autogenerat

    private String username; //no repetit, si no proporcionat -> ANONIM

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime registerDate; //valor segons quan es crea

    @JsonInclude(Include.NON_NULL)
    @JsonSerialize(using = PercentageSerializer.class)
    private Float winRate; // ratio d'exit

    @JsonInclude(Include.NON_ABSENT)
    private List<Roll> rolls;  //col·lecció de tirades efectuades pel jugador

    public Player updateWinRate(List<Roll> rolls){
        if (rolls.isEmpty()){
            winRate = 0f;
        }else {
            float numRolls = rolls.size();
            float numWins = rolls.stream().filter(roll -> roll.getResult().equals(RollResult.WIN)).count();
            winRate = numWins/numRolls;
        }
        return this;
    }
}
