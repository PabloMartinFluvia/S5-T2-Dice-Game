package com.pablo.martin.fluvia.dicegame.domain.models;

import java.util.Date;
import java.util.List;

public class User {

    private Long userId; //autogenerat

    private String userName; //no repetit, si no proporcionat -> ANONIM

    private Date registerDate; //valor segons quan es crea

    private float winRate; // % d'èxit, a decidir el format de com es guarda el valor i num de decimals

    private List<Roll> rolls;  //col·lecció de tirades efectuades pel jugador

}
