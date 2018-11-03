/**
 * 
 */
package Game.View;

import Game.Model.PhaseData;
import Game.Risk.DataHolder;

import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 * This class represent the Phase View for the Main game.
 * 
 * @author Jay
 */
public class PhaseView implements Observer {
	private DataHolder holder = DataHolder.getInstance();

	private JButton btnPhases = new JButton();
    private JPanel panelPhases = new JPanel();
    private JLabel labelPhases = new JLabel();
    private JComboBox<String> neighbourCountryCombo = new JComboBox<>();
    private JComboBox<Integer> noOfArmiesCombo = new JComboBox<>();
    private JComboBox<String> comboCountry = new JComboBox<>();
    
	/**
	 * Constructor for the phase view interface.
	 */
	public PhaseView() {
		labelPhases.setText("Phases :");
        btnPhases.setText("Next Phase");

        GroupLayout Phases_panelLayout = new GroupLayout(panelPhases);
        panelPhases.setLayout(Phases_panelLayout);
        Phases_panelLayout.setHorizontalGroup(
            Phases_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(Phases_panelLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(labelPhases, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(Phases_panelLayout.createSequentialGroup()
                    .addComponent(btnPhases)
                    .addGap(18, 18, 18)
                    .addComponent(noOfArmiesCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(Phases_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(neighbourCountryCombo, 0, 47, Short.MAX_VALUE)
                    .addComponent(comboCountry, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Phases_panelLayout.setVerticalGroup(
            Phases_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(Phases_panelLayout.createSequentialGroup()
                    .addComponent(labelPhases)
                    .addGap(26, 26, 26)
                    .addGroup(Phases_panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPhases)
                        .addComponent(noOfArmiesCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(neighbourCountryCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(22, Short.MAX_VALUE))
        );
	}

	/**
	 * Return Phases Panel for main risk view.
	 * 
	 * @return panelPhases Panel for Phases View.
	 */
	public JPanel getPanel() {
		 return this.panelPhases;
	}

	public void changePhaseTitle() {
		int activePhase = this.holder.getCurrentPhase();
		String phaseName;
		switch (activePhase) {
			case PhaseData.REINFORCEMENT_PHASE:
				phaseName = "Reinforcement Phase";
				break;
			case PhaseData.ATTACK_PHASE:
				phaseName = "Attack Phase";
				break;
			case PhaseData.FORTIFICATION_PHASE:
				phaseName = "Fortification Phase";
				break;
			default:
				phaseName = "Startup Phase";
				break;
		}

		labelPhases.setText(phaseName);
		btnPhases.setText("Next Phase");
	}

	@Override
	public void update(Observable o, Object arg) {

	}

}
