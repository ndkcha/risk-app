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
public class WorldDominationView implements Observer {

	private JScrollPane jScrollPane3;
	private JList<String> Player_Jlist;
    private JPanel Player_Panel;
    private JLabel labelPlayerTitle;
    
	/**
	 * 
	 */
	public WorldDominationView() {
		// TODO Auto-generated constructor stub
		
		Player_Panel = new JPanel();
	    jScrollPane3 = new JScrollPane();
	    Player_Jlist = new JList<>();
	    labelPlayerTitle = new JLabel();
	    
		Player_Jlist.setModel(new AbstractListModel<String>() {
            String[] strings = {" "};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        jScrollPane3.setViewportView(Player_Jlist);

        labelPlayerTitle.setText("Player :");

        jScrollPane3.setViewportView(Player_Jlist);

        GroupLayout Player_PanelLayout = new GroupLayout(Player_Panel);
        Player_Panel.setLayout(Player_PanelLayout);
        Player_PanelLayout.setHorizontalGroup(
            Player_PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(Player_PanelLayout.createSequentialGroup()
                    .addGroup(Player_PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(Player_PanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                        .addGroup(Player_PanelLayout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(labelPlayerTitle)))
                    .addContainerGap(15, Short.MAX_VALUE))
        );
        Player_PanelLayout.setVerticalGroup(
            Player_PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(Player_PanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(labelPlayerTitle)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(28, Short.MAX_VALUE))
        );
	}

	/**
	 * Return Phase Panel for main risk view.
	 * 
	 * @return Player_Panel Panel for Phase View.
	 */
	public JPanel getPanel() {
		 return this.Player_Panel;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
