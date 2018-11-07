/**
 * 
 */
package Game.View;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 * Class for the Dice View on Risk Main screen with observable pattern.
 * 
 * @author Jay
 * @version 1.2.0
 */
public class DiceView implements Observer {

	private JLabel jLabel1;
	private JButton DiceValue;
	private JList<String> Dice_Jlist;
	private JPanel Dice_Panel;
	private JScrollPane jScrollPane2;
	
	/**
	 * Constructor for the Dice View.
	 */
	public DiceView() {
		// TODO Auto-generated constructor stub
		
		jLabel1 = new JLabel();
		DiceValue = new JButton();
		Dice_Jlist = new JList<>();
		Dice_Panel = new JPanel();
		jScrollPane2 = new JScrollPane();
		
		jLabel1.setText("Dice");
        DiceValue.setText("Roll");

        Dice_Jlist.setModel(new AbstractListModel<String>() {
            String[] strings = {" "};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        jScrollPane2.setViewportView(Dice_Jlist);
        
        GroupLayout Dice_PanelLayout = new GroupLayout(Dice_Panel);
        Dice_Panel.setLayout(Dice_PanelLayout);
        Dice_PanelLayout.setHorizontalGroup(
            Dice_PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(Dice_PanelLayout.createSequentialGroup()
                    .addGroup(Dice_PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(Dice_PanelLayout.createSequentialGroup()
                            .addGroup(Dice_PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(Dice_PanelLayout.createSequentialGroup()
                                    .addGap(56, 56, 56)
                                    .addComponent(jLabel1))
                                .addGroup(Dice_PanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(DiceValue, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 118, Short.MAX_VALUE))
                        .addComponent(jScrollPane2, GroupLayout.Alignment.TRAILING))
                    .addContainerGap())
        );
        Dice_PanelLayout.setVerticalGroup(
            Dice_PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(Dice_PanelLayout.createSequentialGroup()
                    .addComponent(jLabel1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(DiceValue)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addContainerGap())
        );
        
	}

	/**
	 * Return Dice Panel for main risk view.
	 * 
	 * @return Dice_Panel Panel for Dice View.
	 */
	public JPanel getPanel() {
		 return this.Dice_Panel;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
