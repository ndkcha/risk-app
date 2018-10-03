/**
 * 
 */
package View;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SpringLayout;


/**
 * Full Game Play Panel consist of Game Logs, Map, Dice info, Player Info, 
 * Reinforcement, Attack and Fortification controls.
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
		playerData = playerInfoGUI;
//        map = newMap;
//        diceArea = newDice;
//        cardsArea = newCards;
//        controlsArea = newControls;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        init();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setVisible(true);
	}
	
	private void init(){
		Container contentPane = this.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
        
        contentPane.add(playerData);
        
        /*playerData constraints.*/
        layout.putConstraint(SpringLayout.WEST, playerData, 5,  SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, playerData, 0,  SpringLayout.EAST, playerData);
        layout.putConstraint(SpringLayout.NORTH, playerData, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, playerData, -5,  SpringLayout.NORTH, playerData);
        
        /*map constraints.*/
        layout.putConstraint(SpringLayout.WEST, playerData, 5,  SpringLayout.EAST, playerData);
        layout.putConstraint(SpringLayout.NORTH, playerData, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, playerData, 0, SpringLayout.SOUTH, playerData);
        layout.putConstraint(SpringLayout.EAST, playerData, -5, SpringLayout.WEST, playerData);
        
        /*diceArea constraints.*/
        layout.putConstraint(SpringLayout.EAST, playerData, -5,  SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, playerData, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, playerData, 0, SpringLayout.SOUTH, playerData);
        
        /*cardsArea constraints.*/
        layout.putConstraint(SpringLayout.WEST, playerData, 5,  SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, playerData, -5, SpringLayout.SOUTH, contentPane);
        
        /*controlsArea constraints.*/
        layout.putConstraint(SpringLayout.WEST, playerData, 5,  SpringLayout.EAST, playerData);
        layout.putConstraint(SpringLayout.EAST, playerData, -5,  SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, playerData, 0, SpringLayout.NORTH, playerData);
        layout.putConstraint(SpringLayout.SOUTH, playerData, 0, SpringLayout.SOUTH, playerData);
        
        this.pack();
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
