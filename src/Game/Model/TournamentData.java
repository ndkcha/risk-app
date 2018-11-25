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
    private HashMap<String, TournamentGame> games = new HashMap<>();

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

    public void startGame() {
        this.setChanged();
        this.notifyObservers(START_GAME);
        holder.refreshHolder();

        holder.bmpFile= new File(mapBuffer.get(0).get(1));
        StartupController startupController = new StartupController(new File(mapBuffer.get(0).get(0)));

        startupController.processFiles();
        startupController.assignCountries();


        RiskMainInterface mainInterface = new RiskMainInterface(true);

    }
}
