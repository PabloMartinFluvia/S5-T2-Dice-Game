package com.pablo.martin.fluvia.dicegame.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

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
    private Float winRate; // % d'èxit,TODO a decidir el format de com es guarda el valor i num de decimals

    @JsonInclude(Include.NON_ABSENT)
    private List<Roll> rolls;  //col·lecció de tirades efectuades pel jugador

}
