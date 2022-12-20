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

    //-----------------------------------------------------------------------------------------------









    /**
     * Demanar al repositori tots els registres de player.
     * IMPORTANT: NO S'HA DE RETORNAR AL CONTROLADOR LA LLISTA DE TIRADES PERÒ SI EL % DE CADASCUN D'ELLS
     * Retornar-los.
     * @return
     */
    List<Player> getAllPlayersWinRated();

    /**
     * Demanar al repositori la llista de tots els players.
     *      Si el llistat no inclou el % de cadascun caldà "pasar la data"
     * Calcular el % mig
     * Retornar el valor.
     * @return
     */
    float getAverageWinRate();

    /**
     * Demanar al repositori el player amb pitjor win rate
     *      Podrien ser N, en cas que hi hagi empat(s)
     * Important, cal que el Player inclogui el win rate
     * Retornar-lo(s)
     * @return
     */
    List<Player> findWorstPlayers();

    /**
     * Demanar al repositori el player amb millor win rate
     *      Podrien ser N, en cas que hi hagi empat(s)
     * Important, cal que el Player inclogui el win rate
     * Retornar-lo(s)
     * @return
     */
    List<Player> findBestPlayers();
}