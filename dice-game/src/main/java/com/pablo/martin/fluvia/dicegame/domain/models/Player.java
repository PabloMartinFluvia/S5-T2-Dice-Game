package com.pablo.martin.fluvia.dicegame.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Player {

    //private Long playerId; //autogenerat

    private String username; //no repetit, si no proporcionat -> ANONIM

    //private LocalDateTime registerDate; //valor segons quan es crea

    //private float winRate; // % d'èxit,TODO a decidir el format de com es guarda el valor i num de decimals

    private List<Roll> rolls;  //col·lecció de tirades efectuades pel jugador

}
