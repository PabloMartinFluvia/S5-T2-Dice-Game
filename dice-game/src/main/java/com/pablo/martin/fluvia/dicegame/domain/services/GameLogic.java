package com.pablo.martin.fluvia.dicegame.domain.services;

import com.pablo.martin.fluvia.dicegame.domain.models.Player;
import com.pablo.martin.fluvia.dicegame.domain.models.Roll;
import com.pablo.martin.fluvia.dicegame.domain.repositories.PersistenceAdapter;
import com.pablo.martin.fluvia.dicegame.exceptions.PlayerNotFoundException;
import com.pablo.martin.fluvia.dicegame.exceptions.UsernameNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@PropertySource("classpath:values.properties")
public class GameLogic implements GameService{

    private PersistenceAdapter persistenceAdapter;

    private final String defaultName;

    @Autowired
    public GameLogic(PersistenceAdapter persistenceAdapter, @Value("${user.defaultName}") String defaultName) {
        this.persistenceAdapter = persistenceAdapter;
        this.defaultName = defaultName;
    }

    /**
     * Comprovar que el nom no està ja registrat (excepte si és el nom default). Excepció si duplicat.
     * Dir-li al repositori que guardi una nova entitat amb aquest nom.
     * Retornar el model que es retorna quan el repositori guarda
     *
     * @param username
     * @return
     */
    @Override
    public Player registerNewPlayer(String username) {
        assertUsernameAvailable(username);
        return persistenceAdapter.registerNewPlayer(username);
    }

    /**
     * Comprovar que el player amb aquest id existeix, obtenint del repository el player si està.
     * Si el nou nom igual al ja guardat no modificar la BD i retornar el trovat
     * Comprovar que el nou nom no està ja registrat (ni el nou és el default). Excepció si duplicat.
     * Canviar el nom del model.
     * Dir-li al repository que guardi el model (sobreescrivint l'existent)
     * Retornar el model que es retorna quan el repositori guarda/sobreescriu.
     *
     * @param id
     * @param username
     * @return
     */
    @Override
    public Player updateName(Long id, String username) {
        Player player = findPlayer(id);
        if(!player.getUsername().equals(username)){
            assertUsernameAvailable(username);
            player.setUsername(username);
            return persistenceAdapter.saveOrReplacePlayer(player);
        }
        return player; //old name = new name -> return found (old)
    }

    /**
     * Demanar al repositori el Player en qüestió i si no el trova llançar excepció
     * Retornar-lo.
     *
     * @param id
     * @return
     */
    @Override
    public Player findPlayer(Long id) {
        return persistenceAdapter.findPlayerById(id).orElseThrow(() -> new PlayerNotFoundException(id));
    }

    /**
     * Comprovar que el player existeix
     * Demanar a l'adapdador que guardi la tirada associant-lo al player
     * Retornar la tirada (indicant si ha guanyat o no)
     *
     * @param id
     * @param roll
     * @return
     */
    @Override
    public Roll addRoll(Long id, Roll roll) {
        assertExistsPlayerById(id);
        return persistenceAdapter.addRollToPlayer(id,roll);
    }

    private void assertUsernameAvailable(String username){
        if(!username.equals(defaultName) && persistenceAdapter.isUsernameRegistered(username)){
            // name already exists AND it's not the default
            throw new UsernameNotAvailableException(username);
        }
    }

    private void assertExistsPlayerById(Long id){
        if(!persistenceAdapter.existsPlayerById(id)){
            throw new PlayerNotFoundException(id);
        }
    }





    /**
     * Dir al repository que elimini les tirades associades a l'id d'aquest player.
     *TODO: com dir que elimini les tirades (segons l'estructura de les entitats i del model)
     *
     * @param id
     */
    @Override
    public void deletePlayerRolls(Long id) {
        //TODO: get player + update + save player VS exist player + call adapter removeRolls(playerId)
    }

    /**
     * Comprovar que el player existeix
     * Demanar al repositori la llista de totes les tirades
     * Retornar el llistat de Rolls. (Ha d'incloure els 2 nums dels daus i si la tirada és guanyadora o no)
     *
     * @param id
     * @return
     */
    @Override
    public List<Roll> findPlayerRolls(Long id) {
        return null;
    }



    /**
     * Demanar al repositori tots els registres de player.
     * IMPORTANT: NO S'HA DE RETORNAR AL CONTROLADOR LA LLISTA DE TIRADES PERÒ SI EL % DE CADASCUN D'ELLS
     * Retornar-los.
     *
     * @return
     */
    @Override
    public List<Player> getAllPlayers() {
        return null;
    }

    /**
     * Demanar al repositori la llista de tots els players.
     * Si el llistat no inclou el % de cadascun caldà "pasar la data"
     * Calcular el % mig
     * Retornar el valor.
     *
     * @return
     */
    @Override
    public float getAverageWinRate() {
        return 0;
    }

    /**
     * Demanar al repositori el player amb pitjor win rate
     * Podrien ser N, en cas que hi hagi empat(s)
     * Important, cal que el Player inclogui el win rate
     * Retornar-lo(s)
     *
     * @return
     */
    @Override
    public List<Player> findWorstPlayers() {
        return null;
    }

    /**
     * Demanar al repositori el player amb millor win rate
     * Podrien ser N, en cas que hi hagi empat(s)
     * Important, cal que el Player inclogui el win rate
     * Retornar-lo(s)
     *
     * @return
     */
    @Override
    public List<Player> findBestPlayers() {
        return null;
    }
}
