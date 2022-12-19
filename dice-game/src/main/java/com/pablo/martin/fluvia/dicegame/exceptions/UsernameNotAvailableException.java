package com.pablo.martin.fluvia.dicegame.exceptions;

public class UsernameNotAvailableException extends RuntimeException{

    public UsernameNotAvailableException (String username){
        super("Already exists another player registered with this username: "+username+".");
    }
}
