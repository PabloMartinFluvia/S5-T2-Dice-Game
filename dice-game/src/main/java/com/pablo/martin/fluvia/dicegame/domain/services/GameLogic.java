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
     * Dir-li a l'adaptador que guardi nou player amb aquest nom.
     * Retornar el model Player basic retornat.
     * @param username
     * @return
     */
    @Override
    public Player registerNewPlayer(String username) {
        assertUsernameAvailable(username);
        return persistenceAdapter.registerNewPlayer(username);
    }

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
    @Override
    public Player updateName(Long id, String username) {
        /*
        En el repositori no vui usar @Query + @Modifying en una query SET,
        ja que no em retornaria l'entitat.
        -> Obtenir el model + modificar-lo + sobreescriure
        Les tirades NO és llegeixen, ni es sobreescriuen, ni es retorna info (millor eficiència).
         */
        Player player = loadBasicPlayer(id);
        if(!player.getUsername().equals(username)){
            assertUsernameAvailable(username);
            player.setUsername(username);
            return persistenceAdapter.replaceBasicPlayer(player);
        }
        return player; //old name = new name -> return found (old)
    }

    /**
     * Comprovar que el player existeix
     * Demanar a l'adaptador que guardi la tirada associant-lo al player
     * Afegir al model de tirada retornat info de si s'ha guanyat o no i retornar-ho
     * @param id
     * @param roll
     * @return
     */
    @Override
    public Roll addRoll(Long id, Roll roll) {
        assertExistsPlayerById(id);
        return persistenceAdapter.addRollToPlayer(id,roll).updateResult();
    }

    /**
     * IF el player existeix:
     * Demanar a l'adaptador que elimini les tirades associades a l'id d'aquest player.
     * @param id
     */
    @Override
    public void deletePlayerRolls(Long id) {
        persistenceAdapter.findBasicPlayerById(id).ifPresent(persistenceAdapter::deletePlayerRolls);
    }

    /**
     * Assegurar que el player existeix.
     * Demanar a l'adaptador tots els models bàsics de les tirades d'aquest player.
     * Afegir al llistat retornat info de si s'ha guanyat o no en cada tirada i retornar-ho
     * @param id
     * @return
     */
    @Override
    public List<Roll> getPlayerRolls(Long id) {
        Player player = loadBasicPlayer(id);
        return persistenceAdapter.loadPlayerRolls(player).stream().map(roll -> roll.updateResult()).toList();
    }

    private Player loadBasicPlayer(Long id){
        return persistenceAdapter.findBasicPlayerById(id).orElseThrow(() -> new PlayerNotFoundException(id));
    }

    /*
    Duplicació de default username -> cap problema
     */
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

    //-----------------------------------------------------------------------------------------------







    /**
     * Demanar al repositori el Player en qüestió i si no el trova llançar excepció
     * Retornar-lo.
     *
     * @param id
     * @return
     */
    @Override
    public Player getPlayerWinRated(Long id) {
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
    public List<Player> getAllPlayersWinRated() {
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
