/**
 * 
 */
package Game.View;

import Game.Model.PhaseData;
import Game.Model.Player;
import Game.Risk.DataHolder;

import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.*;

/**
 * This class represent the Phase View for the Main game.
 * 
 * @author Jay
 */
public class PhaseView implements Observer {
	private boolean isStartupPhaseActive = true;
	private static final String STARTUP_ADD_ARMY = "startup:add_army";
	private DataHolder holder = DataHolder.getInstance();

	private JButton btnPhases = new JButton();
    private JPanel panelPhases = new JPanel();
    private JLabel labelPhases = new JLabel();
    private JComboBox<String> neighbourCountryCombo = new JComboBox<>();
    private JComboBox<Integer> noOfArmiesCombo = new JComboBox<>();
    private JComboBox<String> comboCountry = new JComboBox<>();

    private DefaultComboBoxModel<String> comboModelCountries = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> comboBoxModelNeighbourCountries = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> comboBoxModelNoOfArmies = new DefaultComboBoxModel<>();
    
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

        this.initializeListeners();
	}

	/**
	 * Return Phases Panel for main risk view.
	 * 
	 * @return panelPhases Panel for Phases View.
	 */
	public JPanel getPanel() {
		 return this.panelPhases;
	}

	void changePhaseTitle() {
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

	public void startInitialArmyAssignment() {
		if (holder.isArmiesAssignedForAll()) {
			this.holder.changePhases();
			this.isStartupPhaseActive = false;
			return;
		}

		Player player = holder.getActivePlayer();

		if (player.getNoOfArmiesToAssign() == 0) {
			this.holder.changeTurn();
			return;
		}

		Random random = new Random();

		if (player.getType() == 1) {
			HashMap<String, Integer> countriesConquered = player.getCountriesConquered();
			Object[] entries = countriesConquered.keySet().toArray();

			int randomCountryIndex = random.nextInt(entries.length);
			String randomCountry = (String) entries[randomCountryIndex];
			int noOfArmies = countriesConquered.get(randomCountry);

			player.updateCountry(randomCountry, ++noOfArmies);
			holder.updatePlayer(player);

			holder.changeTurn();
		} else {
			loadCountryListInCombo();
			btnPhases.setText("Add Army");
			btnPhases.setActionCommand(STARTUP_ADD_ARMY);
		}
	}

	private void initializeListeners() {
		this.btnPhases.addActionListener((ActionEvent e) -> {
			String actionCommands = btnPhases.getActionCommand();

			switch (actionCommands) {
				case STARTUP_ADD_ARMY:
					assignArmiesForStartupPhase();
					break;
			}
		});
	}

	private void assignArmiesForStartupPhase() {
		int selectedCountry = comboCountry.getSelectedIndex();

		if (selectedCountry < 1)
			return;

		Player player = holder.getActivePlayer();

		String country = comboModelCountries.getElementAt(selectedCountry);
		country = country.split("-")[1].trim();
		int noOfArmies = player.getCountriesConquered().get(country);

		player.updateCountry(country, ++noOfArmies);
		player.assignInitialArmies();

		holder.updatePlayer(player);

		System.out.println(player.getNoOfArmiesToAssign() + " left for " + player.getName());
		holder.changeTurn();
	}

	/**
	 * Loads country list in the combo box.
	 * It takes an account of the active player.
	 */
	public void loadCountryListInCombo() {
		comboModelCountries.removeAllElements();
		Player player = holder.getActivePlayer();

		comboModelCountries.addElement("No country");

		for (Map.Entry<String, Integer> countryName : player.getCountriesConquered().entrySet()) {
			comboModelCountries.addElement(countryName.getValue() + " - " + countryName.getKey());
		}

		comboCountry.setModel(comboModelCountries);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			switch ((String) arg) {
				case PhaseData.CHANGE_TURN:
					if (this.isStartupPhaseActive)
						this.startInitialArmyAssignment();
					break;
			}
		}
	}

}
