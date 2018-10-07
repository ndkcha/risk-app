/**
 * 
 */
package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.UIManager;


/**
 * Full Game Play Panel consist of Game Logs, Map, Dice info, Player Info, 
 * Reinforcement, Attack and Fortification controls.
 * 
 * Springlayour Refference: https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/SpringDemo4Project/src/layout/SpringDemo4.java
 * 
 * @author Jay
 *
 */
public class RiskMainInterface  extends JFrame{

	/**
	 * MainView object.
	 */
	private static RiskMainInterface mainView;
	
	/**
	 * PlayerInfoView class object.
	 */
	private Views playerData;
	
	/**
	 * 
	 */
	public RiskMainInterface(Views playerInfoGUI) {
		// TODO Auto-generated constructor stub
		//playerData = playerInfoGUI;
//        map = newMap;
//        diceArea = newDice;
//        cardsArea = newCards;
//        controlsArea = newControls;
        //this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initi();
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
//        this.setVisible(true);
	}
	
	public void PlayerInfoView() {
		
	}
	
	private void initi(){
		//Create and set up the window.
        JFrame frame = new JFrame("Risk: The Conquest Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Set up the content pane.
        Container contentPane = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
 
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        
        JLabel map = new JLabel("Map");
		layout.putConstraint(SpringLayout.NORTH, map, -900, SpringLayout.NORTH, frame.getContentPane());
		layout.putConstraint(SpringLayout.WEST, map, 5, SpringLayout.WEST, frame.getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, map, 200, SpringLayout.SOUTH, frame.getContentPane());
		layout.putConstraint(SpringLayout.EAST, map, 700, SpringLayout.EAST, frame.getContentPane());
		
		JLabel diceArea = new JLabel("Roll Dice");
		layout.putConstraint(SpringLayout.NORTH, diceArea, -900, SpringLayout.NORTH, frame.getContentPane());
		layout.putConstraint(SpringLayout.WEST, diceArea, 1100, SpringLayout.WEST, frame.getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, diceArea, 200, SpringLayout.SOUTH, frame.getContentPane());
		layout.putConstraint(SpringLayout.EAST, diceArea, 700, SpringLayout.EAST, map);
        
        JLabel playerData = new JLabel("Players");
        playerData.setBackground(UIManager.getColor("CheckBox.foreground"));
        playerData.setForeground(Color.RED);
        layout.putConstraint(SpringLayout.NORTH, playerData, -700, SpringLayout.NORTH, frame.getContentPane());
		layout.putConstraint(SpringLayout.WEST, playerData, 1100, SpringLayout.WEST, frame.getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, playerData, 200, SpringLayout.SOUTH, frame.getContentPane());
		layout.putConstraint(SpringLayout.EAST, playerData, 300, SpringLayout.EAST, frame.getContentPane());
		
		JLabel gameHistory = new JLabel("Game History");
		layout.putConstraint(SpringLayout.NORTH, gameHistory, -600, SpringLayout.NORTH, frame.getContentPane());
		layout.putConstraint(SpringLayout.WEST, gameHistory, 1100, SpringLayout.WEST, frame.getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, gameHistory, 300, SpringLayout.SOUTH, frame.getContentPane());
		layout.putConstraint(SpringLayout.EAST, gameHistory, 300, SpringLayout.EAST, frame.getContentPane());
		
		JLabel cardsArea = new JLabel("Cards");
		layout.putConstraint(SpringLayout.NORTH, cardsArea, 210, SpringLayout.NORTH, frame.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cardsArea, 5, SpringLayout.WEST, frame.getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, cardsArea, 200, SpringLayout.SOUTH, frame.getContentPane());
		layout.putConstraint(SpringLayout.EAST, cardsArea, 100, SpringLayout.EAST, frame.getContentPane());
		
        JLabel controlsArea = new JLabel("Controls");
        layout.putConstraint(SpringLayout.NORTH, controlsArea, 210, SpringLayout.NORTH, frame.getContentPane());
		layout.putConstraint(SpringLayout.WEST, controlsArea, 400, SpringLayout.WEST, frame.getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, controlsArea, 200, SpringLayout.SOUTH, frame.getContentPane());
		layout.putConstraint(SpringLayout.EAST, controlsArea, 200, SpringLayout.EAST, frame.getContentPane());

		contentPane.add(playerData);
        contentPane.add(map);
        contentPane.add(diceArea);
        contentPane.add(cardsArea);
        contentPane.add(controlsArea);
        contentPane.add(gameHistory);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
	
	public static void createInstance(Views playerInfoGUI)
	{
		if(mainView == null){
			mainView = new RiskMainInterface(playerInfoGUI);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
