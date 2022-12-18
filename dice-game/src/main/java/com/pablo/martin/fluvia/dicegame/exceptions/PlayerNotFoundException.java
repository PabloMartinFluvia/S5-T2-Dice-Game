package com.pablo.martin.fluvia.dicegame.exceptions;

public class PlayerNotFoundException extends RuntimeException{

    public PlayerNotFoundException(Long id){
        super("Doesn't exist any player with id: "+id);
    }
}
