package Game.View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Game.Controller.Controller;
import Game.Controller.MapEditorController;
import Game.Model.Player;
import Game.Risk.DataHolder;

/**
 * Start of the game: Main screen and game settings.
 * This class consist of initial menu and game settings.
 *
 * @author Jay, ndkcha
 */
public class GameSettingsView {
    // Panel height and width.
    private static final int WIDTH = 100;
    private static final int HEIGHT = 50;
    private int num_players = 2; // Minimum number of players

    private JFrame frame;
    private JButton startGame, btnLoadMap, mapEditor;

    private DataHolder holder = DataHolder.getInstance();

    public void startGame() {
        // New JFrame Object.
        frame = new JFrame("Risk: The Conquest Game");

        // JButtons objects.
        startGame = new JButton("Start Game");
        btnLoadMap = new JButton("Load Map");
        mapEditor = new JButton("Map Editor");
        JButton credits = new JButton("credits");
        JButton help = new JButton("Help");

        frame.add(startGame);
        frame.add(btnLoadMap);
        frame.add(mapEditor);
        frame.add(credits);
        frame.add(help);

        frame.setLayout(new FlowLayout());
        frame.setSize(WIDTH, HEIGHT);

        frame.pack();
        frame.setVisible(true);
        frame.validate();

        initializeLoadMap();
    }

    /**
     * This method will provide the game settings panel for Number of players,
     * difficulty, player names and there type with colors
     */
    @SuppressWarnings("unchecked")
    public void gameSettings() {
        System.out.println("Game Setting panel is opened");
        final JFrame jf = new JFrame();
        jf.setBounds(100, 100, WIDTH * 8, HEIGHT * 4);

        JPanel p1 = new JPanel(); // Settings panel
        JPanel p2 = new JPanel(); // Players Names Panel.
        JPanel p3 = new JPanel(); // Players color Panel
        JPanel p4 = new JPanel(); // Select map and Cancel panel

        // Textfields for players name
        final JTextField player1_name;
        final JTextField player2_name;
        final JTextField player3_name;
        final JTextField player4_name;
        final JTextField player5_name;
        final JTextField player6_name;

        // Select sist for player type and color of player
        final JComboBox player1_select;
        final JComboBox player2_select;
        final JComboBox player3_select;
        final JComboBox player4_select;
        final JComboBox player5_select;
        final JComboBox player6_select;

        // Combo select lists
        final JComboBox players_list;
        final JComboBox difficulty_list;
        final JComboBox countries_list;
        final JComboBox troops_list;

        jf.setTitle("Game Settings");

        String[] player1 = {"Human"};
        player1_select = new JComboBox(player1);
        player1_select.setSelectedIndex(0);
        player1_select.setBackground(Color.BLUE);

        String[] player2 = {"Human", "Computer"};
        player2_select = new JComboBox(player2);
        player2_select.setSelectedIndex(1);
        player2_select.setBackground(Color.GREEN);

        String[] player3 = {"Human", "Computer"};
        player3_select = new JComboBox(player3);
        player3_select.setSelectedIndex(1);
        player3_select.setBackground(Color.YELLOW);

        String[] player4 = {"Human", "Computer"};
        player4_select = new JComboBox(player4);
        player4_select.setSelectedIndex(1);
        player4_select.setBackground(Color.MAGENTA);

        String[] player5 = {"Human", "Computer"};
        player5_select = new JComboBox(player5);
        player5_select.setSelectedIndex(1);
        player5_select.setBackground(Color.RED);

        String[] player6 = {"Human", "Computer"};
        player6_select = new JComboBox(player6);
        player6_select.setSelectedIndex(1);
        player6_select.setBackground(Color.ORANGE);

        String[] players = {"2 Players", "3 Players", "4 Players", "5 Players", "6 Players"};
        players_list = new JComboBox(players);
        players_list.setSelectedIndex(0);

        String[] difficulty = {"Difficulty", "Easy", "Medium", "Hard"};
        difficulty_list = new JComboBox(difficulty);
        difficulty_list.setSelectedIndex(0);

        String[] countries = {"Countries Distribution ", "Auto", "Manual"};
        countries_list = new JComboBox(countries);
        countries_list.setSelectedIndex(0);

        String[] troops = {"Troops Distribution", "Auto", "Manual"};
        troops_list = new JComboBox(troops);
        troops_list.setSelectedIndex(0);

        JButton selectMap = new JButton("Select Map");
        JButton jb_cancel = new JButton("Cancel");

        jf.getContentPane().setLayout(null);
        jf.getContentPane().setLayout(new GridLayout(4, 6));
        p1.setLayout(new GridLayout(1, 5));
        p2.setLayout(new GridLayout(1, 5));
        p3.setLayout(new GridLayout(1, 5));

        player1_name = new JTextField("Player One");
        player2_name = new JTextField("Player two");
        player3_name = new JTextField("Player three");
        player4_name = new JTextField("Player four");
        player5_name = new JTextField("Player five");
        player6_name = new JTextField("Player six");

        // Display players name and colors based on selection on number of players.
        players_list.addActionListener((ActionEvent e) -> {
            num_players = players_list.getSelectedIndex() + 2;

            switch (num_players) {
                case 3:
                    player3_name.setVisible(true);
                    player4_name.setVisible(false);
                    player5_name.setVisible(false);
                    player6_name.setVisible(false);

                    player3_select.setVisible(true);
                    player4_select.setVisible(false);
                    player5_select.setVisible(false);
                    player6_select.setVisible(false);
                    break;

                case 4:
                    player3_name.setVisible(true);
                    player4_name.setVisible(true);
                    player5_name.setVisible(false);
                    player6_name.setVisible(false);

                    player3_select.setVisible(true);
                    player4_select.setVisible(true);
                    player5_select.setVisible(false);
                    player6_select.setVisible(false);
                    break;

                case 5:
                    player3_name.setVisible(true);
                    player4_name.setVisible(true);
                    player5_name.setVisible(true);
                    player6_name.setVisible(false);

                    player3_select.setVisible(true);
                    player4_select.setVisible(true);
                    player5_select.setVisible(true);
                    player6_select.setVisible(false);
                    break;

                case 6:
                    player3_name.setVisible(true);
                    player4_name.setVisible(true);
                    player5_name.setVisible(true);
                    player6_name.setVisible(true);

                    player3_select.setVisible(true);
                    player4_select.setVisible(true);
                    player5_select.setVisible(true);
                    player6_select.setVisible(true);
                    break;

                default:
                    player3_name.setVisible(false);
                    player4_name.setVisible(false);
                    player5_name.setVisible(false);
                    player6_name.setVisible(false);

                    player3_select.setVisible(false);
                    player4_select.setVisible(false);
                    player5_select.setVisible(false);
                    player6_select.setVisible(false);
                    break;
            }
        });

        jb_cancel.addActionListener((ActionEvent evt) -> jf.dispose());

        p1.add(players_list);
        p1.add(difficulty_list);
        p1.add(countries_list);
        p1.add(troops_list);

        p2.add(player1_name);
        p2.add(player2_name);
        p2.add(player3_name);
        p2.add(player4_name);
        p2.add(player5_name);
        p2.add(player6_name);

        p3.add(player1_select);
        p3.add(player2_select);
        p3.add(player3_select);
        p3.add(player4_select);
        p3.add(player5_select);
        p3.add(player6_select);

        player3_name.setVisible(false);
        player4_name.setVisible(false);
        player5_name.setVisible(false);
        player6_name.setVisible(false);

        player3_select.setVisible(false);
        player4_select.setVisible(false);
        player5_select.setVisible(false);
        player6_select.setVisible(false);

        p4.add(selectMap);
        p4.add(jb_cancel);

        jf.getContentPane().add(p1);
        jf.getContentPane().add(p2);
        jf.getContentPane().add(p3);
        jf.getContentPane().add(p4);

        selectMap.addActionListener((ActionEvent e) -> {
            System.out.println(e.getActionCommand());
            holder.clearPlayers();
            System.out.println("number of player selected: " + num_players);

            Player pl1 = new Player(player1_name.getText(), "BLUE");
            Player pl2 = new Player(player2_name.getText(), "GREEN");
            Player pl3 = new Player(player3_name.getText(), "YELLOW");
            Player pl4 = new Player(player4_name.getText(), "MAGENTA");
            Player pl5 = new Player(player5_name.getText(), "RED");
            Player pl6 = new Player(player6_name.getText(), "ORANGE");

            holder.addPlayer(pl1);
            holder.addPlayer(pl2);

            switch (num_players) {
                case 6:
                    holder.addPlayer(pl6);
                case 5:
                    holder.addPlayer(pl5);
                case 4:
                    holder.addPlayer(pl4);
                case 3:
                    holder.addPlayer(pl3);
            }

            System.out.println("Select Map Button is clicked");
            chooseOptionFrame().dispose();
            File map_file = map_selector("map");
            holder.bmpFile = map_selector("bmp");

            Controller c = new Controller();
            c.reinforcement();
            c.gameStart(map_file);
        });

        jf.setVisible(true);
    }

    private void initializeLoadMap() {
        System.out.println("Button: Load map");

        btnLoadMap.addActionListener((ActionEvent e) -> {
            File mapFile = map_selector("map");

            MapEditorController editorController = new MapEditorController();
            editorController.loadExistingMap(mapFile);
            editorController.initAndDisplayView();
        });
    }

    /**
     * Upload .map along with .bmp file to be used for the game.
     *
     * @param ext extension of the file.
     * @return map_path Stores the absolute path of the map file and bmp file.
     */
    private File map_selector(String ext) {
        System.out.println("Map and BMP file selector opened");
        JFrame frame = new JFrame("Select Map File");

        // Upload map file. https://coderanch.com/t/466536/java/closing-jFileChooser-window
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("./files/map"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Map Files", ext);
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(frame);
        // Get the path of the file.
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String map_path = selectedFile.getAbsolutePath();
            frame.dispose();
            if (map_path.substring(map_path.lastIndexOf(".")).equalsIgnoreCase("." + ext))
                return selectedFile;
        }
        if (ext.equals("map"))
            return map_selector(ext);
        return null;
    }

    /**
     * Returns the frame to be used to dispose it after selection of an option.
     *
     * @return JFrame
     */
    public JFrame chooseOptionFrame() {
        return this.frame;
    }

    /**
     * Action Listener for start game button.
     */
    public void startGameAction(ActionListener newAction) {
        this.startGame.addActionListener(newAction);
    }


    /**
     * Action Listener for Map Editor button.
     */
    public void mapEditorAction(ActionListener newAction) {
        this.mapEditor.addActionListener(newAction);
    }


    public GameSettingsView() {
    }
}
