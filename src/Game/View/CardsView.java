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
	 private JButton Exchange_card_button;
	 private JPanel Card_panel;
	 private JList<String> Card_Jlist = new JList<>();
	 private JScrollPane jScrollPane1;
	    
	/**
	 * 
	 */
	public CardsView() {
		// TODO Auto-generated constructor stub
		
		Card_panel = new JPanel();
        jLabel3 = new JLabel();
        Card_Jlist = new JList<>();
        Exchange_card_button = new JButton();
        jScrollPane1 = new JScrollPane();
		jLabel3.setText("Card");

		Exchange_card_button.setText("Exchange");
        Exchange_card_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Exchange_card_buttonActionPerformed(evt);
            }
        });

        Card_Jlist.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(Card_Jlist);

        javax.swing.GroupLayout Card_panelLayout = new javax.swing.GroupLayout(Card_panel);
        Card_panel.setLayout(Card_panelLayout);
        Card_panelLayout.setHorizontalGroup(
            Card_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Card_panelLayout.createSequentialGroup()
                .addGroup(Card_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Card_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Card_panelLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(Exchange_card_button)))
                .addGap(0, 27, Short.MAX_VALUE))
            .addGroup(Card_panelLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Card_panelLayout.setVerticalGroup(
            Card_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Card_panelLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Exchange_card_button))
        );
	}

	/**
	 * Return Cards Panel for main risk view.
	 * 
	 * @return Card_panel Panel for Cards View.
	 */
	public JPanel getPanel() {
		 return this.Card_panel;
	}
	
	private void Exchange_card_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Exchange_card_buttonActionPerformed
        // TODO add your handling code here:
		
		JPanel panel = new JPanel();
//	    JButton calculate = new JButton("Calculate");
//	    JButton exchange = new JButton("Exchange");
	    
	    Object[] options = {"Calculate",
                "Exchange"};
	    
	    panel.add(new JLabel("Select the cards: "));
	    JCheckBox checkBox1 = new JCheckBox("card1");  
        checkBox1.setBounds(100,100, 50,50);  
        JCheckBox checkBox2 = new JCheckBox("card2", true);  
        checkBox2.setBounds(100,150, 50,50); 
        
	    panel.add(checkBox1);
	    panel.add(checkBox2);
//	    panel.add(calculate);
//	    panel.add(exchange);
	    
	    int result = JOptionPane.showOptionDialog(null, panel, "Exchange Cards",
	            JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
	            null, options, null);
	    
	    //panel.setVisible(true);
	    //this.pack();
//		calculate.addActionListener((ActionEvent evt) -> {
//			CardsController cc = new CardsController();
//			cc.calculateExchangeArmies(); // Calculate armies on card exchange      
//		});
//		
//		exchange.addActionListener((ActionEvent evnt) -> {
//			CardsController cc = new CardsController();
//			cc.assignPlayerCards("attacker", "defender", "country"); // send data need for cards exchange
//		});
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		// Add the data from CardController to manupulate card exchange.
		
	}

}
