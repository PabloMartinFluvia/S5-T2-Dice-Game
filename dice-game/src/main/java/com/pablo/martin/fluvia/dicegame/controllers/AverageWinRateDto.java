package com.pablo.martin.fluvia.dicegame.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pablo.martin.fluvia.dicegame.utils.PercentageSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AverageWinRateDto {

    @JsonProperty("Average win rate")
    @JsonSerialize(using = PercentageSerializer.class)
    private Float average;

    public AverageWinRateDto(Float average) {
        this.average = average;
    }
}
