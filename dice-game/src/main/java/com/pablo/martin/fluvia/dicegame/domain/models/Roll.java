package com.pablo.martin.fluvia.dicegame.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;

@Getter
@Setter
@PropertySource("classpath:values.properties")
public class Roll {

    private RollResult result; // Enum WIN/LOSE,  es guanya si la suma dels daus = 7

    private int[] dices; //longitud = 2, valor de la cara del dau

    @JsonIgnore
    private int WIN_RESULT = 7;

    public Roll updateResult(){
        if(Arrays.stream(dices).sum() == WIN_RESULT){
            result = RollResult.WIN;
        }else {
            result = RollResult.LOSE;
        }
        return this;
    }
}
