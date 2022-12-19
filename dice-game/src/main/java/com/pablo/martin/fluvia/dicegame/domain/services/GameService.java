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

    //-----------------------------------------------------------------------------------------------



    /**
     * Comprovar que el player existeix
     * Demanar al repositori la llista de totes les tirades
     * Retornar el llistat de Rolls. (Ha d'incloure els 2 nums dels daus i si la tirada és guanyadora o no)
     * @param id
     * @return
     */
    List<Roll> getPlayerRolls(Long id);






    /**
     * Demanar al repositori el Player en qüestió i si no el trova llançar excepció
     * Retornar-lo.
     * @param id
     * @return
     */
    Player getPlayerWinRated(Long id);







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