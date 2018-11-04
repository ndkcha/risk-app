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
public class GameLogsView implements Observer {

	private JScrollPane jScrollPane4 = new JScrollPane();
	private JList<String> listGamePlay = new JList<>();
	private JPanel panelGamePlay = new JPanel();
    private JLabel jLabel7 = new JLabel();
    private JLabel jLabel6 = new JLabel();
    
	/**
	 * Initialize the view
	 */
	public GameLogsView() {
		
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

	@Override
	public void update(Observable o, Object arg) {

	}

}
