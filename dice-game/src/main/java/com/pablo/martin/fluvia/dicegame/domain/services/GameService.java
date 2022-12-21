package com.pablo.martin.fluvia.dicegame.domain.services;

import com.pablo.martin.fluvia.dicegame.domain.models.Player;
import com.pablo.martin.fluvia.dicegame.domain.models.Roll;

import java.util.List;

public interface GameService {

    /**
     * Comprovar que el nom no està ja registrat (excepte si és el nom default). Excepció si duplicat.
     * Dir-li a l'adaptador que guardi nou player amb aquest nom.
     * Retornar el model Player basic retornat.
     * @param username
     * @return
     */
    Player registerNewPlayer(String username);

    /**
     * Buscar el model Player basic corresponent a l'id.
     * Si el nou nom igual al ja guardat retornar lo trovat.
     * Else:
     * Comprovar que el nou nom no està ja registrat (ni el nou és el default). Excepció si duplicat.
     * Dir-li a l'adaptador que actualitzi el player.
     * Retornar el model Player basic retornat.
     * @param id
     * @param username
     * @return
     */
    Player updateName(Long id, String username);

    /**
     * Comprovar que el player existeix
     * Demanar a l'adaptador que guardi la tirada associant-lo al player
     * Afegir al model de tirada retornat info de si s'ha guanyat o no i retornar-ho
     * @parm id
     * @param roll
     * @return
     */
    Roll addRoll(Long id, Roll roll);

    /**
     * IF el player existeix:
     * Demanar a l'adaptador que elimini les tirades associades a l'id d'aquest player.
     * @param id
     */
    void deletePlayerRolls(Long id);

    /**
     * Assegurar que el player existeix.
     * Demanar a l'adaptador tots els models bàsics de les tirades d'aquest player.
     * Afegir al llistat retornat info de si s'ha guanyat o no en cada tirada i retornar-ho
     * @param id
     * @return
     */
    List<Roll> getPlayerRolls(Long id);

    /**
     * Buscar el model Player basic corresponent a l'id.
     * Obtenir la llista de tirades del player (amb la info de si s'ha guanyat o no)
     * Demanar al model del player que calculi el winrate i s'ho guardi (xo que no guardi les tirades)
     * Retornar el model
     * @param id
     * @return
     */
    Player getPlayerWinRated(Long id);

    /**
     * Obtenir del adaptador la llista dels players en format model bàsic.
     * Per a cada model del llistat:
     * Actualitzar-lo a model player win rated.
     * Ordenar el llistat segons el valor del ratio de major a menor
     * Retornar el llistat
     * @return
     */
    List<Player> findAllPlayersRankedDesc();

    /**
     * Demanar a l'adaptador el numero total de rolls guardades.
     * Demanar a l'adaptador el número total de rolls guanyadors guardats.
     * Retornar el ratio
     * @return
     */
    float getAverageWinRate(); // independent del tipus de BBDD

    /**
     * Demanar a l'adaptador quin és el màxim winrate entre tots els players guardats
     * Demanar a l'adaptador el(s) player(s) que tinguin el màxim winrate llegit.
     * @return
     */
    List<Player> findBestPlayers(); // independent del tipus de BBDD

    /**
     * Demanar a l'adaptador quin és el min winrate entre tots els players guardats
     * Demanar a l'adaptador el(s) player(s) que tinguin el min winrate llegit.
     * @return
     */
    List<Player> findWorstPlayers();

    //-----------------------------------------------------------------------------------------------




}