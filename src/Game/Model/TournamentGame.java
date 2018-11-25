package Game.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The game object in the tournament HashMap
 * @author ndkcha
 */
public class TournamentGame {
    private Player player;
    private List<String> gameLogs = new ArrayList<>();
    private MapData mapData;

    /**
     * Initialize the object
     * @param player player who have won the game
     * @param gameLogs things that happened in the game
     * @param mapData map played for the game
     */
    public TournamentGame(Player player, List<String> gameLogs, MapData mapData) {
        this.player = player;
        this.gameLogs = gameLogs;
        this.mapData = mapData;
    }

    /**
     * Get the winner
     * @return player who've won
     */
    public Player getPlayer() {
        return player;
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
