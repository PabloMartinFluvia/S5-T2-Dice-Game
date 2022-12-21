package com.pablo.martin.fluvia.dicegame.exceptions;

public class PlayerNotFoundException extends RuntimeException{

    public PlayerNotFoundException(Long id){
        super("Doesn't exists any player with requested id: "+id);
    }
}
