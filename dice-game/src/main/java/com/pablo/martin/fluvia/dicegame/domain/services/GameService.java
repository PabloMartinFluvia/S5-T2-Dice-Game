package com.pablo.martin.fluvia.dicegame.domain.services;

import com.pablo.martin.fluvia.dicegame.domain.models.Player;
import com.pablo.martin.fluvia.dicegame.domain.models.Roll;

import java.util.List;

public interface GameService {

    /**
     * Comprovar que el nom no està ja registrat (excepte si és el nom default). Excepció si duplicat.
     * Dir-li al repositori que guardi una nova entitat amb aquest nom.
     * Retornar el model que es retorna quan el repositori guarda
     * @param username
     * @return
     */
    Player registerNewPlayer(String username);

    /**
     * Comprovar que el player amb aquest id existeix, obtenint del repository el player si està.
     * Si el nou nom igual al ja guardat no modificar la BD i retornar el trovat
     * Comprovar que el nou nom no està ja registrat (ni el nou és el default). Excepció si duplicat.
     * Canviar el nom del model.
     * Dir-li al repository que guardi el model (sobreescrivint l'existent)
     * Retornar el model que es retorna quan el repositori guarda/sobreescriu.
     * @param id
     * @param username
     * @return
     */
    Player updateName(Long id, String username);

    /**
     * Comprovar que el player existeix
     * TODO: com guardar la tirada
     * Retornar la tirada (indicant si ha guanyat o no)
     * @param id
     * @param roll
     * @return
     */
    Roll addRoll(Long id, Roll roll);

    /**
     * Dir al repository que elimini les tirades associades a l'id d'aquest player.
     * @param id
     */
    void deletePlayerRolls(Long id);

    /**
     * Comprovar que el player existeix
     * Demanar al repositori la llista de totes les tirades
     * Retornar el llistat de Rolls. (Ha d'incloure els 2 nums dels daus i si la tirada és guanyadora o no)
     * @param id
     * @return
     */
    List<Roll> findPlayerRolls(Long id);

    /**
     * Comprovar que el player existeix.
     * Demanar al repositori el Player en questió
     * Retornar-lo.
     * @param id
     * @return
     */
    Player findPlayer(Long id);

    /**
     * Demanar al repositori tots els registres de player.
     * IMPORTANT: NO S'HA DE RETORNAR AL CONTROLADOR LA LLISTA DE TIRADES PERÒ SI EL % DE CADASCUN D'ELLS
     * Retornar-los.
     * @return
     */
    List<Player> getAllPlayers();

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