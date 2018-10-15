package Game.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import Game.Model.Player;
import Game.Risk.DataHolder;
import Game.View.*;
import java.util.List;


/**
 * MVC - Controller to control the interaction between models and views.
 * 
 * @author Jay
 *
 */
public class Controller {
    private DataHolder holder = DataHolder.getInstance();
    // Store object of GameSettingsView class.
	private GameSettingsView gameSettings;

	private Views playerInfoGUI;
	
	// Initialization of Game and listeners.
	public void initialize() {
		gameSettings = new GameSettingsView();
		gameSettings.startGame();
		startGameListener();
		mapEditorListener();
	}
	/**
 * 
 * method game start functioning
 * @param map_file file object
 */
	public void gameStart(File map_file) {
		playerInfoGUI = new Views();

		StartupController startupController = new StartupController(map_file);
		startupController.processFiles();
		startupController.assignCountries();
		startupController.assignArmies();

		RiskMainInterface.createInstance(playerInfoGUI);
		setPlayerView(playerInfoGUI);

		MapView mapView = new MapView();
		mapView.paintUi();
		mapView.plotPlayers();
	}
        
        
        /**
 * 
 * method reinforcement functioning
 
 */
	public void reinforcement() {
		System.out.println("\n reinforcement phase");
		//temporary logic for simulating turns taking turns
		List<Player> p= this.holder.getPlayerList();
		int playersTurn = 0;

		//players taking turn for each phase

		for(int i=playersTurn;i<p.size();i++){

			if (playersTurn == p.size()){
				playersTurn = 0;
			} else{
				playersTurn++;
			}

			System.out.println("\n\nReinforcemnet phase of Player "+playersTurn);
			ReinforcementController reinforcementController =new ReinforcementController();
			int armies = reinforcementController.calculateReinformentArmies(playersTurn);
			reinforcementController.updateArmiesInCountries(playersTurn, armies);
		}
	}
/**
 * 
 * method setting the player view
 * @param newView views object
 */
	private void setPlayerView(Views newView) {
		this.playerInfoGUI = newView;
	}
	
	//Sets listener for Play Game button.
	private void startGameListener() {
		ActionListener startGameListener = (ActionEvent e) -> {
				System.out.println("Start Game Button is clicked");
				gameSettings.gameSettings();
				gameSettings.chooseOptionFrame().dispose();
		};
		this.gameSettings.startGameAction(startGameListener);
	}
	//Sets listener for Map Editor button.
	private void mapEditorListener() {
		ActionListener mapEditorListener =  (ActionEvent e) -> {
			MapEditorController controller = new MapEditorController();
			controller.initAndDisplayView();
			System.out.println("Map Editor button is clicked");
		};
		this.gameSettings.mapEditorAction(mapEditorListener);
	}
/**
 * 
 * method public is called
 */
	public Controller() { }
}
