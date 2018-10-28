/**
 * 
 */
package Game.View;

import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 * @author Jay
 *
 */
public class CardsView implements Observer {

	 private JLabel jLabel3;
	 private JButton Card_A_button;
	 private JButton Card_B_button;
	 private JButton Card_C_button;
	 private JButton Card_D_button;
	 private JButton Card_E_button;
	 private JPanel Card_panel;
	    
	/**
	 * 
	 */
	public CardsView() {
		// TODO Auto-generated constructor stub
		
		Card_panel = new JPanel();
        jLabel3 = new JLabel();
		Card_A_button = new JButton();
        Card_B_button = new JButton();
        Card_C_button = new JButton();
        Card_D_button = new JButton();
        Card_E_button = new JButton();
        
		jLabel3.setText("Card");

        Card_A_button.setText("Card 1");
        Card_B_button.setText("Card 2");
        Card_C_button.setText("Card 3");
        Card_D_button.setText("Card 4");
        Card_E_button.setText("Card 5");


        GroupLayout Card_panelLayout = new GroupLayout(Card_panel);
        Card_panel.setLayout(Card_panelLayout);
        Card_panelLayout.setHorizontalGroup(
            Card_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(Card_panelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(Card_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(Card_panelLayout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addComponent(jLabel3)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(Card_panelLayout.createSequentialGroup()
                            .addGroup(Card_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(Card_A_button, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                .addComponent(Card_C_button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(Card_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(Card_B_button, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                .addComponent(Card_D_button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                            .addComponent(Card_E_button, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
                .addGroup(Card_panelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(Card_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(Card_panelLayout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addComponent(jLabel3)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(Card_panelLayout.createSequentialGroup()
                            .addGroup(Card_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(Card_A_button, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                .addComponent(Card_C_button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(Card_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(Card_B_button, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                .addComponent(Card_D_button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                            .addComponent(Card_E_button, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        Card_panelLayout.setVerticalGroup(
            Card_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(Card_panelLayout.createSequentialGroup()
                    .addComponent(jLabel3)
                    .addGap(18, 18, 18)
                    .addGroup(Card_panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(Card_A_button)
                        .addComponent(Card_B_button)
                        .addComponent(Card_E_button))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(Card_panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(Card_C_button)
                        .addComponent(Card_D_button))
                    .addGap(0, 8, Short.MAX_VALUE))
        );
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
