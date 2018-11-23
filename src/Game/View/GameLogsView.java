package Game.View;

import Game.Model.GameLogsData;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 * This View save all the logs in gamelogs.
 * 
 * @author Jay, ndkcha
 * @version 1.2.0
 */
public class GameLogsView implements Observer {
	private JList<String> listGamePlay = new JList<>();
	private JPanel panelGamePlay = new JPanel();
    private DefaultListModel<String> listModelGamePlay = new DefaultListModel<>();
    
	/**
	 * Initialize the view
	 */
	public GameLogsView() {
		JScrollPane jScrollPane4 = new JScrollPane();
		JLabel jLabel7 = new JLabel();
		JLabel jLabel6 = new JLabel();

		jLabel7.setText("             Gameplay   :");
        jScrollPane4.setViewportView(listGamePlay);

        GroupLayout layoutPanel = new GroupLayout(panelGamePlay);
        panelGamePlay.setLayout(layoutPanel);
        layoutPanel.setHorizontalGroup(
            layoutPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layoutPanel.createSequentialGroup()
                    .addGroup(layoutPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layoutPanel.createSequentialGroup()
                            .addGap(145, 145, 145)
                            .addComponent(jLabel6)
                            .addGap(38, 38, 38)
                            .addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layoutPanel.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 512, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layoutPanel.setVerticalGroup(
            layoutPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layoutPanel.createSequentialGroup()
                    .addGroup(layoutPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layoutPanel.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel6))
                        .addComponent(jLabel7))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 471, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
	}

	/**
	 * Return game logs Panel for main risk view.
	 * 
	 * @return panelGamePlay Panel for gamelog View.
	 */
	public JPanel getPanel() {
		 return this.panelGamePlay;
	}

	public void loadInitialData(List<String> logs) {
		listModelGamePlay.removeAllElements();

		for (String log : logs) {
			listModelGamePlay.add(0, log);
		}

		listGamePlay.setModel(listModelGamePlay);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			switch ((String) arg) {
				case GameLogsData.GAME_LOG:
					GameLogsData logs = (GameLogsData) o;
					this.printLog(logs.log);
					break;
			}
		}
	}

	/**
	 * Attaches the log in to the list
	 * @param log the log to attach
	 */
	private void printLog(String log) {
		listModelGamePlay.add(0, log);
		listGamePlay.setModel(listModelGamePlay);
	}
}
