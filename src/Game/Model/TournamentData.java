package Game.Model;

import Game.Controller.StartupController;
import Game.Risk.DataHolder;
import Game.View.RiskMainInterface;
import Game.View.TournamentView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

/**
 * Model to keep track of the tournament data
 * @author ndkcha
 */
public class TournamentData extends Observable {
    public static final String START_GAME = "start:game";
    private DataHolder holder = DataHolder.getInstance();
    private List<List<String>> mapBuffer = new ArrayList<>();
    private HashMap<String, List<String>> gameLogs = new HashMap<>();
    private int currentGame = -1, currentMap = 0;

    /**
     * Add a game for the logs
     * @param gameId id of the game
     * @param logs logs to store
     */
    public void addGame(String gameId, List<String> logs) {
        if (!this.gameLogs.containsKey(gameId)) {
            List<String> newLogs = new ArrayList<>(logs);
            this.gameLogs.put(gameId, newLogs);
        }
    }

    /**
     * Get the game logs of a game
     * @param gameId id of the game
     * @return list of logs
     */
    public List<String> getGameLogs(String gameId) {
        return gameLogs.get(gameId);
    }

    /**
     * Gets the list of maps to play
     * @return list of maps
     */
    public List<List<String>> getMapBuffer() {
        return mapBuffer;
    }

    /**
     * Add the future game paths (such as map path and image path) to temporary buffer
     * @param path path to the map file
     * @param imagePath path to the bmp file
     * @param noOfGames number of games to play with this map
     */
    public void addGamePath(String path, String imagePath, String noOfGames) {
        List<String> map = new ArrayList<>();
        map.add(path);
        map.add(imagePath);
        map.add(noOfGames);
        this.mapBuffer.add(map);
    }

    /**
     * Start the games in tournament
     */
    public void startNextGame() {
        this.setChanged();
        this.notifyObservers(START_GAME);

        this.changeGame();

        if (this.currentMap >= this.mapBuffer.size())
            return;

        holder.refreshHolder();

        System.out.println("New Game = CurrentMap: " + currentMap + " CurrentGame: " + currentGame);
        holder.setGameId(currentMap + ":" + currentGame);

        holder.bmpFile= new File(mapBuffer.get(currentMap).get(1));

        StartupController startupController = new StartupController(new File(mapBuffer.get(currentMap).get(0)));

        startupController.processFiles();
        startupController.assignCountries();


        RiskMainInterface mainInterface = new RiskMainInterface(true);

    }

    /**
     * Change the game iterator.
     * If the map is out of game, change the map
     */
    public void changeGame() {
        if (this.currentMap >= this.mapBuffer.size())
            return;

        this.currentGame++;

        if (this.currentGame >= Integer.parseInt(this.mapBuffer.get(currentMap).get(2))) {
            this.currentMap++;
            this.currentGame = 0;
        }
    }
}
