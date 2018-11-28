package Game.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * A communication bridge to get the GameLogs flowing thru the application
 * @author ndkcha
 * @since 1.2.0
 */
public class GameLogsData extends Observable implements Serializable {
    /** The observable key to identify the presence of this object in the pipeline */
    public static final String GAME_LOG = "gamePlay:logs";
    /** the logs that is to be transmitted */
    public String log;
    public List<String> logs = new ArrayList<>();

    public GameLogsData() { }

    /**
     * Put the logs in the observable pipeline in order to have it listened at the other end.
     * @param log the log to display
     */
    public void sendLogs(String log) {
        this.logs.add(log);
        this.log = log;
        setChanged();
        notifyObservers(GAME_LOG);
    }
}
