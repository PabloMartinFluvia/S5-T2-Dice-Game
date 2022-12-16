package com.pablo.martin.fluvia.dicegame.domain.models;

public enum RollResult {
    WIN("WIN"),
    LOSE("LOSE");

    private final String RESULT;

    RollResult(String result){
        this.RESULT = result;
    }

    @Override
    public String toString(){
        return RESULT;
    }
}
