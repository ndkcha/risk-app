package Game.View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Game.Controller.Controller;
import Game.Controller.MapEditorController;
import Game.Controller.StartupController;
import Game.Controller.TournamentController;
import Game.Model.Player;
import Game.Risk.DataHolder;

/**
 * Start of the game: Main screen and game settings. This class consist of
 * initial menu and game settings.
 *
 * @author Jay, ndkcha
 * @version 1.2.0
 */
public class GameSettingsView {
    private Integer[] noOfTurns = { 10, 15, 20, 25, 30, 35, 40, 45, 50 };
	private JTextField textPlayer1, textPlayer2, textPlayer3, textPlayer4, textPlayer5, textPlayer6;
	private JComboBox comboPlayer1, comboPlayer2, comboPlayer3, comboPlayer4, comboPlayer5, comboPlayer6;
	private JComboBox comboPlayers, comboTroops;
	private JComboBox<Integer> comboNoOfTurns;
	private JFrame frameGameSettings;

	// Panel height and width.
	private static final int WIDTH = 100;
	private static final int HEIGHT = 50;
	private int num_players = 2; // Minimum number of players

	private JFrame frame;
	private JButton startGame, btnLoadMap, mapEditor, loadGame, tournament;

	private DataHolder holder = DataHolder.getInstance();

	/** An empty constructor */
	public GameSettingsView() { }

	/**
	 * This method will start the game and give startup options to user.
	 */
	public void startGame() {
		// New JFrame Object.
		frame = new JFrame("Risk: The Conquest Game");

		// JButtons objects.
		startGame = new JButton("Single Mode");
		btnLoadMap = new JButton("Load Map");
		mapEditor = new JButton("Map Editor");
		loadGame = new JButton("Load Game");
		tournament = new JButton("Tournament");

		frame.add(startGame);
		frame.add(tournament);
		frame.add(loadGame);
		frame.add(btnLoadMap);
		frame.add(mapEditor);

		frame.setLayout(new FlowLayout());
		frame.setSize(WIDTH, HEIGHT);

		frame.pack();
		frame.setVisible(true);
		frame.validate();

		initializeLoadMap();
		initializeLoadGame();
	}

	/**
	 * This method will provide the game settings panel for Number of players,
	 * difficulty, player names and there type with colors
	 *
	 * @param isTournamentMode is it a tournament mode?
	 */
	@SuppressWarnings("unchecked")
	public void gameSettings(boolean isTournamentMode) {
		frameGameSettings = new JFrame();
		frameGameSettings.setBounds(100, 100, WIDTH * 8, HEIGHT * 4);

		// Settings panel
		JPanel panelSettings = getTheSettingsPanel(isTournamentMode);
		// Players Names Panel.
		JPanel panelPlayers = getThePlayersPanel();
		// Players color Panel
		JPanel panelColor = getTheColorPanel(isTournamentMode);
		// Select map and Cancel panel
		JPanel panelActions = getActionsPanel(isTournamentMode);

		// Combo select lists
		frameGameSettings.setTitle("Game Settings");

		frameGameSettings.getContentPane().setLayout(null);
		frameGameSettings.getContentPane().setLayout(new GridLayout(5, 6));
		panelSettings.setLayout(new GridLayout(1, 5));
		panelPlayers.setLayout(new GridLayout(1, 5));
		panelColor.setLayout(new GridLayout(1, 5));

		frameGameSettings.getContentPane().add(panelSettings);
		frameGameSettings.getContentPane().add(panelPlayers);
        frameGameSettings.getContentPane().add(panelColor);
		frameGameSettings.getContentPane().add(panelActions);

		frameGameSettings.setVisible(true);
	}

    /**
     * Get the frame for game settings view
     * @return frame for game settings
     */
    public JFrame getFrameGameSettings() {
        return frameGameSettings;
    }

    /**
	 * Initializes the players
	 * @return panels that has all the players
	 */
	private JPanel getThePlayersPanel() {
		JPanel panel = new JPanel();

		textPlayer1 = new JTextField("ndkcha");
		textPlayer2 = new JTextField("jatin");
		textPlayer3 = new JTextField("gunnu");
		textPlayer4 = new JTextField("roohani");
		textPlayer5 = new JTextField("kunal");
		textPlayer6 = new JTextField("player");

		textPlayer3.setVisible(false);
		textPlayer4.setVisible(false);
		textPlayer5.setVisible(false);
		textPlayer6.setVisible(false);

		panel.add(textPlayer1);
		panel.add(textPlayer2);
		panel.add(textPlayer3);
		panel.add(textPlayer4);
		panel.add(textPlayer5);
		panel.add(textPlayer6);

		return panel;
	}

	/**
	 * Initializes player details panel
	 * @param isTournamentMode is it a tournament module?
	 * @return panel that has details about players
	 */
	@SuppressWarnings("unchecked")
	private JPanel getTheColorPanel(boolean isTournamentMode) {
		JPanel panel = new JPanel();

		String[] player1 = { "Human", "Aggressive", "Benevolent", "Random", "Cheater" };
		comboPlayer1 = new JComboBox(player1);
		comboPlayer1.setSelectedIndex(isTournamentMode ? 1 : 0);
		comboPlayer1.setBackground(Color.BLUE);

		String[] player2 = { "Human", "Aggressive", "Benevolent", "Random", "Cheater" };
		comboPlayer2 = new JComboBox(player2);
		comboPlayer2.setSelectedIndex(1);
		comboPlayer2.setBackground(Color.GREEN);

		String[] player3 = { "Human", "Aggressive", "Benevolent", "Random", "Cheater" };
		comboPlayer3 = new JComboBox(player3);
		comboPlayer3.setSelectedIndex(1);
		comboPlayer3.setBackground(Color.YELLOW);

		String[] player4 = { "Human", "Aggressive", "Benevolent", "Random", "Cheater" };
		comboPlayer4 = new JComboBox(player4);
		comboPlayer4.setSelectedIndex(1);
		comboPlayer4.setBackground(Color.MAGENTA);

		String[] player5 = { "Human", "Aggressive", "Benevolent", "Random", "Cheater" };
		comboPlayer5 = new JComboBox(player5);
		comboPlayer5.setSelectedIndex(1);
		comboPlayer5.setBackground(Color.RED);

		String[] player6 = { "Human", "Aggressive", "Benevolent", "Random", "Cheater" };
		comboPlayer6 = new JComboBox(player6);
		comboPlayer6.setSelectedIndex(1);
		comboPlayer6.setBackground(Color.ORANGE);

		comboPlayer3.setVisible(false);
		comboPlayer4.setVisible(false);
		comboPlayer5.setVisible(false);
		comboPlayer6.setVisible(false);

		panel.add(comboPlayer1);
		panel.add(comboPlayer2);
		panel.add(comboPlayer3);
		panel.add(comboPlayer4);
		panel.add(comboPlayer5);
		panel.add(comboPlayer6);

		return panel;
	}

	/**
	 * Initializes settings view
	 * @param isTournamentMode is it a tournament mode?
	 * @return panel for the settings
	 */
	@SuppressWarnings("unchecked")
	private JPanel getTheSettingsPanel(boolean isTournamentMode) {
		JPanel panel = new JPanel();

		String[] players = { "2 Players", "3 Players", "4 Players", "5 Players", "6 Players" };
		comboPlayers = new JComboBox(players);
		comboPlayers.setSelectedIndex(0);

		// Display players name and colors based on selection on number of
		// players.
		comboPlayers.addActionListener((ActionEvent e) -> this.listenToNoOfPlayersCombo());

		String[] troops = { "Troops Distribution", "Auto", "Manual" };
		comboTroops = new JComboBox(troops);
		comboTroops.setSelectedIndex(0);

		comboNoOfTurns = new JComboBox<>(noOfTurns);
		comboNoOfTurns.setSelectedIndex(0);

		panel.add(comboPlayers);
		panel.add(comboNoOfTurns);
		if (!isTournamentMode) {
			panel.add(comboTroops);
		}

		return panel;
	}

	/**
	 * Initializes the action panel
	 * @param isTournamentMode is it a tournament mode?
	 * @return panel for the action buttons
	 */
	private JPanel getActionsPanel(boolean isTournamentMode) {
		JPanel panel = new JPanel();

		JButton selectMap = new JButton(isTournamentMode ? "Start Tournament" : "Select Map");
		JButton jb_cancel = new JButton("Cancel");

		selectMap.addActionListener((ActionEvent e) -> listenToSelectMapBtn(isTournamentMode));
		jb_cancel.addActionListener((ActionEvent evt) -> frameGameSettings.dispose());

		panel.add(selectMap);
		panel.add(jb_cancel);

		return panel;
	}

	/**
	 * Listen to the selector action of map button
	 * @param isTournamentMode is it a tournament module?
	 */
	private void listenToSelectMapBtn(boolean isTournamentMode) {
		holder.clearPlayers();

		Player pl1 = new Player(textPlayer1.getText(), comboPlayer1.getSelectedIndex(), "BLUE");
		Player pl2 = new Player(textPlayer2.getText(), comboPlayer2.getSelectedIndex(), "GREEN");
		Player pl3 = new Player(textPlayer3.getText(), comboPlayer3.getSelectedIndex(), "YELLOW");
		Player pl4 = new Player(textPlayer4.getText(), comboPlayer4.getSelectedIndex(), "MAGENTA");
		Player pl5 = new Player(textPlayer5.getText(), comboPlayer5.getSelectedIndex(), "RED");
		Player pl6 = new Player(textPlayer6.getText(), comboPlayer6.getSelectedIndex(), "ORANGE");

		holder.addPlayer(pl1);
		holder.addPlayer(pl2);

		int indexMaxLimit = comboNoOfTurns.getSelectedIndex();
		holder.maxTurnLimit = comboNoOfTurns.getItemAt(indexMaxLimit);

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

//			chooseOptionFrame().dispose();
		frameGameSettings.setVisible(false);
		if (isTournamentMode) {
			holder.isArmiesAutomatic = true;
			TournamentController.getInstance().initTournament();
		} else {
			File map_file = map_selector("map");
			holder.bmpFile = map_selector("bmp");

			holder.isArmiesAutomatic = (comboTroops.getSelectedIndex() != 2);

			Controller c = new Controller();
			c.gameStart(map_file);
		}
	}

	/**
	 * Listener for the combo list of the players.
	 * It will listen to the select event and hide/show the elements in other panels.
	 */
	private void listenToNoOfPlayersCombo() {
		num_players = comboPlayers.getSelectedIndex() + 2;

		switch (num_players) {
			case 3:
				textPlayer3.setVisible(true);
				textPlayer4.setVisible(false);
				textPlayer5.setVisible(false);
				textPlayer6.setVisible(false);
				comboPlayer3.setVisible(true);
				comboPlayer4.setVisible(false);
				comboPlayer5.setVisible(false);
				comboPlayer6.setVisible(false);

				break;

			case 4:
				textPlayer3.setVisible(true);
				textPlayer4.setVisible(true);
				textPlayer5.setVisible(false);
				textPlayer6.setVisible(false);
				comboPlayer3.setVisible(true);
				comboPlayer4.setVisible(true);
				comboPlayer5.setVisible(false);
				comboPlayer6.setVisible(false);

				break;

			case 5:
				textPlayer3.setVisible(true);
				textPlayer4.setVisible(true);
				textPlayer5.setVisible(true);
				textPlayer6.setVisible(false);
				comboPlayer3.setVisible(true);
				comboPlayer4.setVisible(true);
				comboPlayer5.setVisible(true);
				comboPlayer6.setVisible(false);
				break;

			case 6:
				textPlayer3.setVisible(true);
				textPlayer4.setVisible(true);
				textPlayer5.setVisible(true);
				textPlayer6.setVisible(true);
				comboPlayer3.setVisible(true);
				comboPlayer4.setVisible(true);
				comboPlayer5.setVisible(true);
				comboPlayer6.setVisible(true);
				break;

			default:
				textPlayer3.setVisible(false);
				textPlayer4.setVisible(false);
				textPlayer5.setVisible(false);
				textPlayer6.setVisible(false);
				comboPlayer3.setVisible(false);
				comboPlayer4.setVisible(false);
				comboPlayer5.setVisible(false);
				comboPlayer6.setVisible(false);
				break;
		}
	}

	/**
	 * This method will initialize the map loader.
	 */
	private void initializeLoadMap() {
		btnLoadMap.addActionListener((ActionEvent e) -> {
			File mapFile = map_selector("map");

			MapEditorController editorController = new MapEditorController();
			boolean anyErrors = editorController.loadExistingMap(mapFile);
			if (!anyErrors) {
				editorController.initAndDisplayView();
			}
		});
	}

	/**
	 * This method will start the Tournament.
	 * @param listener listener to assign the tournament
	 */
	public void initializeStartTournament(ActionListener listener) {
		tournament.addActionListener(listener);
	}

	/**
	 * This method will load game.
	 */
	private void initializeLoadGame() {
		loadGame.addActionListener((ActionEvent e) -> showSavedGame());
	}

	private void showSavedGame() {
		JFrame frame = new JFrame("Select Saved Game File");
		JFileChooser jFileChooser = new JFileChooser();

		jFileChooser.setCurrentDirectory(new File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Saved Game Files", "ser");
		jFileChooser.setFileFilter(filter);

		int result = jFileChooser.showOpenDialog(frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = jFileChooser.getSelectedFile();
			DataHolder dataHold = DataHolder.getInstance();
			dataHold.loadSavedGame(file);
			
			StartupController startController = new StartupController();
			startController.resumeGame();
		}

		else if (result == JFileChooser.CANCEL_OPTION) {
			chooseOptionFrame().dispose();
		}

	}

	/**
	 * Upload .map along with .bmp file to be used for the game.
	 *
	 * @param ext Extension of the file.
	 * @return map_path Stores the absolute path of the map file and bmp file.
	 */
	@SuppressWarnings({"Duplicates", "unchecked"})
	private File map_selector(String ext) {
		JFrame frame = new JFrame("Select Map File");

		// Upload map file.
		// https://coderanch.com/t/466536/java/closing-jFileChooser-window
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
	 *
	 * @param newAction Action Listenser object.
	 */
	public void startGameAction(ActionListener newAction) {
		this.startGame.addActionListener(newAction);
	}

	/**
	 * Action Listener for Map Editor button.
	 *
	 * @param newAction Action Listenser object.
	 */
	public void mapEditorAction(ActionListener newAction) {
		this.mapEditor.addActionListener(newAction);
	}

}
