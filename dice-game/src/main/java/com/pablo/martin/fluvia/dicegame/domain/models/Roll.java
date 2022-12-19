package com.pablo.martin.fluvia.dicegame.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class Roll {

    private RollResult result; // Enum WIN/LOSE,  es guanya si la suma dels daus = 7

    private int[] dices; //longitud = 2, valor de la cara del dau

    public Roll updateResult(){
        if(Arrays.stream(dices).sum() == 7){
            result = RollResult.WIN;
        }else {
            result = RollResult.LOSE;
        }
        return this;
    }
}
