/**
 * @file the Start up Phase of the game. 
 */
package View;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Start of the game: Main screen and game settings.
 * 
 * @author Jay
 *
 */
public class StartUpPhase {

	// Panel height and width.
	int width=100;
    int height=50;
  
    int num_players=2;
	
	private JFrame frame;
	private JButton start_game, load_game, map_editor, credits, help;
	
	public void startUpPhase() {
		// New JFrame Object.
		frame = new JFrame("Risk: The Conquest Game");
		
		// JButtons objects.
		start_game = new JButton("Start Game");
		load_game = new JButton("Load Game");
		map_editor = new JButton("Map Editor");
		credits = new JButton("credits");
		help = new JButton("Help");
		
		frame.add(start_game);
		frame.add(load_game);
		frame.add(map_editor);		
		frame.add(credits);
		frame.add(help);
		
		frame.setLayout(new FlowLayout());
		frame.setSize(width,height);  
        
	    frame.pack();
	    frame.setVisible(true); 	    
		frame.validate();
	}
	
	/**
	 * Returns the frame to be used to dispose it after selection of an option.
	 * @return JFrame
	 */
	public JFrame chooseOptionFrame() {
		return this.frame;
	}
	
	// Action Listener for start game button.
	public void startGameAction(ActionListener newAction) {
		this.start_game.addActionListener(newAction);
	}
	/**
	 * 
	 */
	public StartUpPhase() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
