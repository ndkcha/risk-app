/**
 * 
 */
package View;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Jay
 *
 */
public class MapEditotView {

	public static final int width=100;
	public static final int height=50;
	
	private JFrame frame;
	private JButton startGame, loadGame, mapEditor, credits, help, selectMap;
	
	public void mapEditorUI(){
		// New JFrame Object.
		frame = new JFrame("Risk: The Conquest Game");
		
		// JButtons objects.
		startGame = new JButton("Start Game");
		loadGame = new JButton("Load Game");
		mapEditor = new JButton("Map Editor");
		credits = new JButton("credits");
		help = new JButton("Help");
		
		frame.add(startGame);
		frame.add(loadGame);
		frame.add(mapEditor);		
		frame.add(credits);
		frame.add(help);
		
		frame.setLayout(new FlowLayout());
		frame.setSize(width,height);  
        
	    frame.pack();
	    frame.setVisible(true); 	    
		frame.validate();
	}
	/**
	 * 
	 */
	public MapEditotView() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
