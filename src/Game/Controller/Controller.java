package Game.Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;

import Game.Model.Player;
import Game.Risk.DataHolder;
import Game.View.*;

/**
 * MVC - Common Controller to control the interaction between models and views.
 * 
 * @author Jay
 *
 */
public class Controller {
	
	// Game Driver: Hold all the data for gameplay
	private DataHolder holder = DataHolder.getInstance();
	// Store object of GameSettingsView class.
	private GameSettingsView gameSettings;

	private Views playerInfoGUI;
	private StartupController startupController;
        private ReinforcementController reinforcementController;
        private FortificationController fortificationController;

	/**
	 * Run the game by calling startGame functions.
	 */
	public void gameInitializer() {
		gameSettings = new GameSettingsView();
		gameSettings.startGame();
		startGameListener();
		mapEditorListener();
	}

	/**
	 * This method fill start the game after game settings and uploading map file.
	 * @param map_file File object.
	 */
	public void gameStart(File map_file) {

		StartupController startupController = new StartupController(map_file);
		startupController.processFiles(); // Reads the Map file
		startupController.assignCountries(); // assign the contries
		startupController.assignArmies(); // assign armies

		RiskMainInterface.createInstance();

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


	/**
	 * Listener for Start Game button.
	 */
	private void startGameListener() {
		ActionListener startGameListener = (ActionEvent e) -> {
			System.out.println("Start Game Button is clicked");
			gameSettings.gameSettings(); // Open game settings.
			gameSettings.chooseOptionFrame().dispose(); // close the previous window.
		};
		this.gameSettings.startGameAction(startGameListener);
	}

	/**
	 * Listener for Map Editor button.
	 */
	private void mapEditorListener() {
		ActionListener mapEditorListener = (ActionEvent e) -> {
			MapEditorController controller = new MapEditorController();
			controller.initAndDisplayView();
			System.out.println("Map Editor button is clicked");
		};
		this.gameSettings.mapEditorAction(mapEditorListener);
	}

}
