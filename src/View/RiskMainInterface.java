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
        init();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
//        this.setVisible(true);
	}
	
	public void PlayerInfoView() {
		
	}
	
	private void init(){
		//Create and set up the window.
        JFrame frame = new JFrame("SpringDemo2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Set up the content pane.
        Container contentPane = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
 
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        JLabel playerData = new JLabel("Map Here");
		this.add(playerData);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
		
        JLabel controlsArea = new JLabel("Controls Here.");
		this.setLayout(new FlowLayout());
		this.add(controlsArea);
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel diceArea = new JLabel("Player info");
		this.setLayout(new FlowLayout());
		this.add(diceArea);
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
		diceArea.setPreferredSize(new Dimension(400,200));
		
		JLabel cardsArea = new JLabel("Cards Here.");
		this.setLayout(new FlowLayout());
		this.add(cardsArea);
		
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
		cardsArea.setPreferredSize(new Dimension(400,150));
		
		JLabel map = new JLabel("Dice Here.");
		this.setLayout(new FlowLayout());
		this.add(map);
		
        //Create and add the components.
        //JLabel label = new JLabel("Label: ");
        //JTextField textField = new JTextField("Text field", 15);
		contentPane.add(playerData);
        contentPane.add(map);
        contentPane.add(diceArea);
        contentPane.add(cardsArea);
        contentPane.add(controlsArea);
 
        /*playerData constraints.*/
        layout.putConstraint(SpringLayout.WEST, playerData, 5,  SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, playerData, 0,  SpringLayout.EAST, cardsArea);
        layout.putConstraint(SpringLayout.NORTH, playerData, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, playerData, -5,  SpringLayout.NORTH, cardsArea);
        
        /*map constraints.*/
        layout.putConstraint(SpringLayout.WEST, map, 5,  SpringLayout.EAST, playerData);
        layout.putConstraint(SpringLayout.NORTH, map, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, map, 0, SpringLayout.SOUTH, playerData);
        layout.putConstraint(SpringLayout.EAST, map, -5, SpringLayout.WEST, diceArea);
        
        /*diceArea constraints.*/
        layout.putConstraint(SpringLayout.EAST, diceArea, -5,  SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, diceArea, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, diceArea, 0, SpringLayout.SOUTH, playerData);
        
        /*cardsArea constraints.*/
        layout.putConstraint(SpringLayout.WEST, cardsArea, 5,  SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, cardsArea, -5, SpringLayout.SOUTH, contentPane);
        
        /*controlsArea constraints.*/
        layout.putConstraint(SpringLayout.WEST, controlsArea, 5,  SpringLayout.EAST, cardsArea);
        layout.putConstraint(SpringLayout.EAST, controlsArea, -5,  SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, controlsArea, 0, SpringLayout.NORTH, cardsArea);
        layout.putConstraint(SpringLayout.SOUTH, controlsArea, 0, SpringLayout.SOUTH, cardsArea);
        this.pack();
 
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
