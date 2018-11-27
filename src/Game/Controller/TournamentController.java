package Game.Controller;

import Game.Model.TournamentData;
import Game.Risk.DataHolder;
import Game.View.DialogSelectGameNo;
import Game.View.GameSettingsView;
import Game.View.RiskMainInterface;
import Game.View.TournamentView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.List;

public class TournamentController {
    private static TournamentController controller;
    private DataHolder holder = DataHolder.getInstance();
    private TournamentView tournamentView = new TournamentView();

    TournamentController() { }

    public static TournamentController getInstance() {
        if (controller == null)
            controller = new TournamentController();
        return controller;
    }

    /**
     * Start the tournament
     */
    public void start() {
        tournamentView.initComponents();

        int noOfMaps = selectNumberOfMaps();

        for (int i = 0; i < noOfMaps; i++) {
            String mapPath = map_selector("map", i);
            String bmpPath = map_selector("bmp", i);
            String noOfGames = String.valueOf(selectNumberOfGames());
            tournamentView.addGamePath(mapPath, bmpPath, noOfGames);
        }

        GameSettingsView gameSettingsView = new GameSettingsView();
        gameSettingsView.gameSettings(true);
    }

    /**
     * Initialize the tournament
     */
    public void initTournament() {
        holder.attachObserverToPhase(tournamentView);
        tournamentView.startGame();
    }

    /**
     * Select the number of games in each map
     * @return number of games
     */
    private int selectNumberOfGames() {
        DialogSelectGameNo dialog = new DialogSelectGameNo();
        return dialog.selectNumberOfGames("games");
    }

    /**
     * Select the number of maps
     * @return number of maps
     */
    private int selectNumberOfMaps() {
        DialogSelectGameNo dialog = new DialogSelectGameNo();
        return dialog.selectNumberOfGames("maps");
    }

    /**
     * Upload .map along with .bmp file to be used for the game.
     *
     * @param ext Extension of the file.
     * @param gameIndex index of the game
     * @return map_path Stores the absolute path of the map file and bmp file.
     */
    @SuppressWarnings("Duplicates")
    private String map_selector(String ext, int gameIndex) {
        System.out.println("Map and BMP file selector opened");
        JFrame frame = new JFrame("Select Map File for Game " + (gameIndex + 1));

        // Upload map file.
        // https://coderanch.com/t/466536/java/closing-jFileChooser-window
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select " + ext + " File for Game " + (gameIndex + 1));
        fileChooser.setCurrentDirectory(new File("./files/map"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Map Files", ext);
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(frame);
        // Get the path of the file.
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String map_path = selectedFile.getAbsolutePath();
            frame.dispose();
            return map_path;
        }
        if (ext.equals("map"))
            return map_selector(ext, gameIndex);
        return null;
    }
}
