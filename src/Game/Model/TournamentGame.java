package Game.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The game object in the tournament HashMap
 * @author ndkcha
 */
public class TournamentGame {
    private List<String> gameLogs;
    private MapData mapData;

    /**
     * Initialize the object
     * @param gameLogs things that happened in the game
     * @param mapData map played for the game
     */
    public TournamentGame(List<String> gameLogs, MapData mapData) {
        this.gameLogs = gameLogs;
        this.mapData = mapData;
    }

    /**
     * Get the logs
     * @return list of log strings
     */
    public List<String> getGameLogs() {
        return gameLogs;
    }

    /**
     * Get the map used for the game
     * @return map object
     */
    public MapData getMapData() {
        return mapData;
    }
}
