package Game.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

/**
 * Model to keep track of the tournament data
 * @author ndkcha
 */
public class TournamentData extends Observable {
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

    /**
     * Get the map details
     * @return path to map file, bmp file, and number of times to play
     */
    public List<List<String>> getMapBuffer() {
        return this.mapBuffer;
    }
}
