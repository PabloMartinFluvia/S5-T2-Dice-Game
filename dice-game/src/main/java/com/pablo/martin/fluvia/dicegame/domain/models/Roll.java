package com.pablo.martin.fluvia.dicegame.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pablo.martin.fluvia.dicegame.utils.validations.ValidRoll;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;


@ValidRoll
@Getter
@Setter
public class Roll {

    @JsonIgnore
    public static final int WIN_RESULT = 7;

    private RollResult result; // Enum WIN/LOSE,  es guanya si la suma dels daus = 7

    private int[] dices; //longitud = 2, valor de la cara del dau

    public Roll updateResult(){
        if(isWon()){
            result = RollResult.WIN;
        }else {
            result = RollResult.LOSE;
        }
        return this;
    }

    @JsonIgnore
    public boolean isWon(){
        return Arrays.stream(dices).sum() == WIN_RESULT;
    }
}
