package com.pablo.martin.fluvia.dicegame.domain.services;

import com.pablo.martin.fluvia.dicegame.domain.models.Player;
import com.pablo.martin.fluvia.dicegame.domain.models.Roll;


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
    Roll newRoll(Long id, Roll roll);

    /**
     * Dir al repository que elimini les tirades associades a l'id d'aquest player.
     * @param id
     */
    void deleteRolls(Long id);
}
