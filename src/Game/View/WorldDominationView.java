/**
 * 
 */
package Game.View;

import Game.Model.Player;
import Game.Risk.DataHolder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 * Class for World Domination View using observable.
 * 
 * @author Jay
 * @version 1.2.0
 */
public class WorldDominationView implements Observer {
	private DataHolder holder = DataHolder.getInstance();

	private JList<String> listPlayer = new JList<>();
    private JPanel playerPanel = new JPanel();
    private JLabel labelPlayerTitle = new JLabel();

	private DefaultListModel<String> listModelPlayers = new DefaultListModel<>();
    
	/**
	 * Constructor for wolddomination view.
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
			int noOfOwnCountries = player.getCountriesConquered().size();
			int totalCountries = holder.getCountryDataList().size();
			double percent = ((double) noOfOwnCountries)/((double) totalCountries);
			listModelPlayers.addElement(player.getName() + " [" + holder.getNoOfContinents(player) + " c] " +
				BigDecimal.valueOf(percent*100).setScale(2, RoundingMode.HALF_UP) + "% " + player.getTotalPlayerArmies());
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
		if (arg1 instanceof String) {
			if (((String) arg1).startsWith("change")) {
				setActivePlayerLabel();
				reloadTheList();
			}
		}
	}

}
