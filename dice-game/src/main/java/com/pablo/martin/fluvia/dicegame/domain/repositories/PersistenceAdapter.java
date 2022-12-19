package com.pablo.martin.fluvia.dicegame.domain.repositories;

import com.pablo.martin.fluvia.dicegame.domain.models.Player;
import com.pablo.martin.fluvia.dicegame.domain.models.Roll;

import java.util.Optional;


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
     * Passar el model a entity
     * Cridar al repositori i dir-li save el player entity (guarda o sobreescriu)
     * Retornar lo obtingut, passant-lo abans al model.
     * @param player
     * @return
     */
    Player saveOrReplacePlayer(Player player);

    /**
     * Cridar al repositori que em busqui el player entity segons el id
     * i ho retorno, passant abans l'entitat al model.
     * @param id
     * @return
     */
    Optional<Player> findPlayerById(Long id);

    /**
     * This method has the pre-condition the player must exist.
     * Demanar a la persistencia que linki aquesta tirada al player
     *      IMPORTANT: depen del modelatge de les relacions
     * Retornar el model de la tirada, indicant si és guanyadora o no.
     * @param playerId
     * @param roll
     * @return
     */
    Roll addRollToPlayer(Long playerId, Roll roll);

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

    /**
     * Preguntar al repositori de player si ja hi ha algun registrat
     * @param id
     * @return
     */
    boolean existsPlayerById(Long id);



}
