/**
 * 
 */
package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * 
 * 
 * @author Jay
 *
 */
public class Views extends JPanel{

	/**
	 * 
	 */
	public Views() {
		// TODO Auto-generated constructor stub
		JLabel label = new JLabel("All Cards");
		this.setLayout(new FlowLayout());
		this.add(label);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(400,150));
	}

	/**
	 * Creates cards view.
	 * @return 
	 */
	public Views cardsView(){
		JLabel label = new JLabel("All Cards");
		this.setLayout(new FlowLayout());
		this.add(label);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(400,150));
		
		return this;
	}
	
	/**
	 * Creates Dice roll view.
	 */
	public void diceRollView(){
		JLabel label = new JLabel("Roll Dice");
		this.setLayout(new FlowLayout());
		this.add(label);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * Creates the Player Info view on the main window.
	 */
	public void playerInfoView() {
		JLabel label = new JLabel("Players data Here.");
		this.add(label);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
