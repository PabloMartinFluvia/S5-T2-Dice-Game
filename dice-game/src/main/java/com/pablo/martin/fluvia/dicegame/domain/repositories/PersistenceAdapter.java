package com.pablo.martin.fluvia.dicegame.domain.repositories;

import com.pablo.martin.fluvia.dicegame.domain.models.Player;
import com.pablo.martin.fluvia.dicegame.domain.models.Roll;

import java.util.List;
import java.util.Optional;


public interface PersistenceAdapter {

    /**
     * Crear una nova entitat player amb el nom proporcionat.
     * Dir-li al repositori que guardi la nova entitat.
     * Retornar el model Player basic (mateixos camps que player entity) equivalent a l'entity guardada
     * @param username
     * @return
     */
    Player registerNewPlayer(String username);

    /**
     * Passar el model a entity
     * Indicar al repositori que sobreescrigui el player entity.
     * Retornar lo obtingut, passant-lo abans al model basic de Player
     * @param player
     * @return
     */
    Player replaceBasicPlayer(Player player);

    /**
     * This method has the pre-condition the player must exist.
     * Demanar a la persistencia que linki aquesta tirada al player
     *      IMPORTANT: depen del modelatge de les relacions
     * Retornar el model equivalent de l'entitat tirada guardada.
     * @param playerId
     * @param roll
     * @return
     */
    Roll addRollToPlayer(Long playerId, Roll roll);

    /**
     * Cridar al repositori que em busqui el Optional player entity segons el id
     * Retornar l'Optional mapjeat al model basic de Player
     * @param id
     * @return
     */
    Optional<Player> findBasicPlayerById(Long id);

    /**
     * Demanar al repository de rolls que elimini totes les tirades associades
     * a aquest model bàsic de player.
     * @param player
     */
    void deletePlayerRolls(Player player);

    /**
     * Demanar al repositori de players tots els models basics de players guardats.
     * @return
     */
    List<Player> findAllBasicPlayer();

    long countRolls();


    long countWinnersRolls();

    /**
     * Demanar al repository de player que busqui si ja existeix un usuari amb aquest nom.
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

    /**
     * Demanar al repository de rolls la llista de models bàsics de rolls associats
     * a aquest model bàsic de player.
     * @param player
     * @return
     */
    List<Roll> loadPlayerRolls(Player player);

    //-----------------------------------------------------------------------------------------------













}
