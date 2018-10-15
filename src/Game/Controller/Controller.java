package Game.Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;

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
	private StartupController startupController;
        private ReinforcementController reinforcementController;
        private FortificationController fortificationController;
	// Initialization of Game and listeners.
	public void initialize() {
		gameSettings = new GameSettingsView();
		gameSettings.startGame();
		startGameListener();
		mapEditorListener();
	}
	
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
                    //reinforcement(playersTurn);
                    fortification(playersTurn);
                }
                
	}
        
        public void reinforcement(int playersTurn){
            //retrieving the player number whose turn is goin on
            List<Player> p= this.holder.getPlayerList();
            Player player = p.get(playersTurn - 1);
                    
                    System.out.println("\n\nReinforcemnet phase of Player "+player.getName());
                    this.reinforcementController =new ReinforcementController();
                    int armies=this.reinforcementController.calculateReinformentArmies(playersTurn);
                    this.reinforcementController.updateArmiesInCountries(playersTurn, armies); 
        }
        
        public void fortification(int playersTurn){
            //retrieving the player number whose turn is goin on
            List<Player> p= this.holder.getPlayerList();
            Player player = p.get(playersTurn - 1);
            System.out.println("\n\nFortification phase of Player "+player.getName());
            this.fortificationController=new FortificationController();
            this.fortificationController.fortification(playersTurn);
        }

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
	
	private void mapEditorListener() {
		ActionListener mapEditorListener =  (ActionEvent e) -> {
			MapEditorController controller = new MapEditorController();
			controller.initAndDisplayView();
			System.out.println("Map Editor button is clicked");
		};
		this.gameSettings.mapEditorAction(mapEditorListener);
	}

	public Controller() { }
}
