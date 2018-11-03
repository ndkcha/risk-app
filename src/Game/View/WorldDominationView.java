/**
 * 
 */
package Game.View;

import Game.Model.Player;
import Game.Risk.DataHolder;

import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 * @author Jay
 *
 */
public class WorldDominationView implements Observer {
	private DataHolder holder = DataHolder.getInstance();

	private JList<String> listPlayer = new JList<>();
    private JPanel playerPanel = new JPanel();
    private JLabel labelPlayerTitle = new JLabel();

	private DefaultListModel<String> listModelPlayers = new DefaultListModel<>();
    
	/**
	 * 
	 */
	public WorldDominationView() {
		listPlayer.setEnabled(false);
		JScrollPane jScrollPane3 = new JScrollPane();
        jScrollPane3.setViewportView(listPlayer);

        labelPlayerTitle.setText("Player :");

        jScrollPane3.setViewportView(listPlayer);

        GroupLayout Player_PanelLayout = new GroupLayout(playerPanel);
        playerPanel.setLayout(Player_PanelLayout);
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
	 * @return playerPanel Panel for Phase View.
	 */
	public JPanel getPanel() {
		 return this.playerPanel;
	}

	/** reloads the list of players on UI */
	void reloadTheList() {
		listModelPlayers.removeAllElements();

		for (Player player: holder.getPlayerList()) {
			listModelPlayers.addElement(player.getName() + " (" + player.getColor() + ") [" +
				player.getCountriesConquered().size() + " countries]");
		}

		listPlayer.setModel(listModelPlayers);
	}

	/** sets the label according to the active player */
	void setActivePlayerLabel() {
		Player player = holder.getActivePlayer();

		labelPlayerTitle.setText("Player: (turn: " + player.getName() + ")");
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println(arg1);
	}

}
