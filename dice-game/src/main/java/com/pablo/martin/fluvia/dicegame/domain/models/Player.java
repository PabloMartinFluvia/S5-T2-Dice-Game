package com.pablo.martin.fluvia.dicegame.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pablo.martin.fluvia.dicegame.utils.PercentageSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class Player {

    private Long playerId; //autogenerat

    private String username; //no repetit, si no proporcionat -> ANONIM

    @JsonInclude(Include.NON_NULL)
    @JsonSerialize(using = PercentageSerializer.class)
    private Float winRate; // ratio d'exit

    @JsonInclude(Include.NON_NULL)
    private Integer totalRolls;

    //atribute for rolls collection not needed yet <- none request asks for player info AND rolls done

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime registerDate; //valor segons quan es crea

    public Player updateRollsInfo(List<Roll> rolls){
        if (rolls.isEmpty()){
            winRate = 0f;
            totalRolls = 0;
        }else {
            totalRolls = rolls.size();
            float numWins = rolls.stream().filter(roll -> roll.getResult().equals(RollResult.WIN)).count();
            winRate = numWins/totalRolls;
        }
        return this;
    }
}
