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
public class DiceView implements Observer {

	private JLabel jLabel1;
	private JButton DiceValue;
	private JList<String> Dice_Jlist;
	private JPanel Dice_Panel;
	private JScrollPane jScrollPane2;
	
	/**
	 * 
	 */
	public DiceView() {
		// TODO Auto-generated constructor stub
		
		jLabel1 = new JLabel();
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
