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

	private JScrollPane jScrollPane4;
	private JList<String> Gameplay_Jlist;
	private JPanel Gameplay_panel;
    private JLabel jLabel7;
    private JLabel jLabel6;
    
	/**
	 * 
	 */
	public GameLogsView() {
		// TODO Auto-generated constructor stub
		
		Gameplay_panel = new JPanel();
		jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jScrollPane4 = new JScrollPane();
        Gameplay_Jlist = new JList<>();
		
		jLabel7.setText("             Gameplay   :");
        jScrollPane4.setViewportView(Gameplay_Jlist);

        GroupLayout Gameplay_panelLayout = new GroupLayout(Gameplay_panel);
        Gameplay_panel.setLayout(Gameplay_panelLayout);
        Gameplay_panelLayout.setHorizontalGroup(
            Gameplay_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(Gameplay_panelLayout.createSequentialGroup()
                    .addGroup(Gameplay_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(Gameplay_panelLayout.createSequentialGroup()
                            .addGap(145, 145, 145)
                            .addComponent(jLabel6)
                            .addGap(38, 38, 38)
                            .addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
                        .addGroup(Gameplay_panelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 512, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Gameplay_panelLayout.setVerticalGroup(
            Gameplay_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(Gameplay_panelLayout.createSequentialGroup()
                    .addGroup(Gameplay_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(Gameplay_panelLayout.createSequentialGroup()
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
	 * @return Gameplay_panel Panel for gamelog View.
	 */
	public JPanel getPanel() {
		 return this.Gameplay_panel;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
