package com.pablo.martin.fluvia.dicegame.domain.repositories;

import com.pablo.martin.fluvia.dicegame.domain.models.Player;


public interface PersistenceAdapter {

    /**
     * Crear una nova entitat amb el nom proporcionat.
     * Dir-li al repositori que guardi la nova entitat.
     * Retornar el model de Player obtingut a partir de l'entitat retornada per la persistencia.
     * @param username
     * @return
     */
    Player registerNewPlayer(String username);

    /**
     * Demanar al repository que busqui si ja existeix un usuari amb aquest nom.
     * Important: Només es crida si username NO default (assert)?
     * Obs: en l'interfaç del repository usar mètode boolean existsPlayerByPlayerName
     *      -> exists NomEntitat By NomCamp (tipusNomCamp valor)
     *
     *      if (registrat) -> return true
     * @param username
     * @return
     */
    boolean isUsernameRegistered(String username);



}
