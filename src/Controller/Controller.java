/**
 * @file Controllers for the whole Risk Game
 */
package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;

import Model.Player;
import Risk.DataHolder;
import View.*;
import java.util.List;
//import View.MapView;


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
	
	private MapEditotView map_editor;
	
	//ActionListener for "Start Game" button.
	private ActionListener startGameListener;
	
	//ActionListener for "Start Game" button.
	private ActionListener mapEditorListener;
	
	private Views mainGUI;
	private Views cardsGUI;
	private Views controlsGUI;
	private Views diceRollGUI;
	private Views playerInfoGUI;
	
	/**
	 * BufferedReader Object to read the image file.
	 */
	private BufferedImage image;
	
	/**
	 * Boolean to check for .bmp file for the map.
	 */
	private boolean graphicalMap = false;
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 2353535256045293828L;

	private StartupController startupController;
        private ReinforcementController reinforcementController;
	
	// Initialization of Game and listeners.
	public void initialize() {
		gameSettings = new GameSettingsView();
		gameSettings.startGame();
		map_editor = new MapEditotView();
		startGameListener();
		mapEditorListener();
	}
	
	public void gameStart(File map_file) {
		mainGUI = new Views();
		playerInfoGUI = new Views();
		this.startupController = new StartupController(map_file);
		this.startupController.processFiles();
		this.startupController.assignCountries();
		this.startupController.assignArmies();
                RiskMainInterface.createInstance(playerInfoGUI);
		setPlayerView(playerInfoGUI);

		MapView mapView = new MapView();
		mapView.paintUi();
		mapView.plotPlayers();
	}
        
        public void reinforcement(){
            System.out.println("\n reinforcement phase");
            //temporary logic for simulating turns taking turns
                List<Player> p= this.holder.getPlayerList();
                Player[] players = new Player[p.size()];
                int playersTurn = 0;
                
                //players taking turn for each phase
                
                for(int i=playersTurn;i<p.size();i++){
                
                    if (playersTurn == players.length){
                        playersTurn = 0;
                    } else{
                        playersTurn++;
                    }
                    
                    System.out.println("\n\nReinforcemnet phase of Player "+playersTurn);
                    this.reinforcementController =new ReinforcementController();
                    int armies=this.reinforcementController.calculateReinformentArmies(playersTurn);
                    this.reinforcementController.updateArmiesInCountries(playersTurn, armies);
                }
        }

	public void setPlayerView(Views newView) {
		this.playerInfoGUI = newView;
	}
	
	//Sets listener for Play Game button.
	public void startGameListener() {
		startGameListener =  new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Start Game Button is clicked");
				gameSettings.gameSettings();
				gameSettings.chooseOptionFrame().dispose();
			}
		};
		this.gameSettings.startGameAction(startGameListener);
	}
	
	public void mapEditorListener() {
		mapEditorListener =  new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Map Editor button is clicked");
				map_editor.initComponents();
			}
		};
		this.gameSettings.mapEditorAction(mapEditorListener);
	}

	public Controller() {
		// TODO Auto-generated constructor stub
	}
}
