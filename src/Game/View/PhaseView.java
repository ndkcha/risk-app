package Game.View;

import Game.Controller.ReinforcementController;
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
	private static final String CHANGE_PHASE = "change:phase";
	private static final String REINFORCEMENT_ADD_ARMY_ACTION = "reinforcement:add";
	private static final String FORTIFICATION_SEND_ARMY_ACTION = "fortification:send";
	private DataHolder holder = DataHolder.getInstance();
	private int reinforcementArmyAllocated = 0;
	private boolean isFortificationDone = false;

	private JButton btnPhases = new JButton();
    private JPanel panelPhases = new JPanel();
    private JLabel labelPhases = new JLabel();
    private JComboBox<String> comboNeighbourCountry = new JComboBox<>();
    private JComboBox<Integer> comboNoOfArmies = new JComboBox<>();
    private JComboBox<String> comboCountry = new JComboBox<>();

    private DefaultComboBoxModel<String> comboModelCountries = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> comboModelNeighbourCountries = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<Integer> comboModelNoOfArmies = new DefaultComboBoxModel<>();
    
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
                    .addComponent(comboNoOfArmies, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(Phases_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboNeighbourCountry, 0, 47, Short.MAX_VALUE)
                    .addComponent(comboCountry, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Phases_panelLayout.setVerticalGroup(
            Phases_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(Phases_panelLayout.createSequentialGroup()
                    .addComponent(labelPhases)
                    .addGap(26, 26, 26)
                    .addGroup(Phases_panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPhases)
                        .addComponent(comboNoOfArmies, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(comboNeighbourCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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

	/**
	 * Executes the startup phase for the player's turn.
	 * It allocates the initial armies for the game
	 */
	public void startInitialArmyAssignment() {
		System.out.println("Start initial army assignment - " + this.isStartupPhaseActive);
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
			player.assignInitialArmies();
			holder.updatePlayer(player);

			holder.changeTurn();
		} else {
			loadCountryListInCombo();
			btnPhases.setText("Add Army");
			btnPhases.setActionCommand(STARTUP_ADD_ARMY);
		}
	}

	/**
	 * Initializes action listeners from the UI components
	 */
	private void initializeListeners() {
		this.btnPhases.addActionListener((ActionEvent e) -> {
			String actionCommands = btnPhases.getActionCommand();

			switch (actionCommands) {
				case STARTUP_ADD_ARMY:
					assignArmiesForStartupPhase();
					break;
				case CHANGE_PHASE:
					holder.changePhases();
					break;
				case REINFORCEMENT_ADD_ARMY_ACTION:
					addArmyInReinforcementPhase();
					break;
				case FORTIFICATION_SEND_ARMY_ACTION:
					sendArmyInFortificationPhase();
					break;
			}
		});

		this.comboCountry.addActionListener((ActionEvent e) -> {
			switch (holder.getCurrentPhase()) {
				case PhaseData.REINFORCEMENT_PHASE:
					setupManualReinforcementPhase();
					break;
				case PhaseData.FORTIFICATION_PHASE:
					setupManualFortificationPhase();
					break;
			}
		});
	}

	/**
	 * Add selected number of armies to the country in reinforcement phase
	 */
	private void addArmyInReinforcementPhase() {
		int selectedCountry = comboCountry.getSelectedIndex();
		int selectedNoOfArmies = comboNoOfArmies.getSelectedIndex();

		if ((selectedCountry == -1) || (selectedNoOfArmies == -1))
			return;

		String country = comboModelCountries.getElementAt(selectedCountry);
		int noOfArmies = comboModelNoOfArmies.getElementAt(selectedNoOfArmies);

		Player player = holder.getActivePlayer();
		country = country.split("-")[1].trim();

		String message = player.reinforcementPhase(noOfArmies, country);
		holder.updatePlayer(player);
		holder.sendGameLog(message);

		this.reinforcementArmyAllocated += noOfArmies;

		loadCountryListInCombo();
		setupManualReinforcementPhase();
	}

	/**
	 * Setup manual entries for reinforcement phase
	 */
	private void setupManualReinforcementPhase() {
		comboModelNoOfArmies.removeAllElements();

		if (comboCountry.getSelectedIndex() == 0)
			return;

		if (comboCountry.getSelectedIndex() > 0) {
			ReinforcementController controller = new ReinforcementController();
			int totalNoOfArmies = controller.calculateReinforcementArmies(holder.getActivePlayer());
			int noOfArmies = totalNoOfArmies - this.reinforcementArmyAllocated;

			if (noOfArmies == 0)
				this.changePhaseAhead();
			else {
				for (int i = 1; i <= noOfArmies; i++) {
					comboModelNoOfArmies.addElement(i);
				}

				btnPhases.setText("Add");
				btnPhases.setActionCommand(REINFORCEMENT_ADD_ARMY_ACTION);
			}
		} else
			this.changePhaseAhead();

		comboNoOfArmies.setModel(comboModelNoOfArmies);
	}

	/**
	 * Assign armies for the startup phase from the UI.
	 * It takes the user inputs from the UI components
	 */
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
	 * Setup manual entries for fortification phase.
	 * It will help user to continue with the phase from UI.
	 */
	private void setupManualFortificationPhase() {
		if (this.isFortificationDone)
			return;

		comboModelNoOfArmies.removeAllElements();
		comboModelNeighbourCountries.removeAllElements();

		int selectedCountry = comboCountry.getSelectedIndex();

		if (selectedCountry == -1)
			return;

		Player player = holder.getActivePlayer();

		if (selectedCountry > 0) {
			String countryName = comboModelCountries.getElementAt(selectedCountry);
			countryName = countryName.split("-")[1].trim();
			int noOfArmies = player.getArmiesInCountry(countryName) - 1;
			List<String> neighbours = holder.getCountry(countryName).getNeighbours();
			List<String> neighboursToAdd = new ArrayList<>();

			for (String neighbourName : neighbours) {
				if (player.getCountriesConquered().containsKey(neighbourName))
					neighboursToAdd.add(player.getCountriesConquered().get(neighbourName) + " - " + neighbourName);
			}

			if ((noOfArmies == 0) || (neighboursToAdd.size() == 0))
				this.changePhaseAhead();
			else {
				for (int i = 1; i <= noOfArmies; i++) {
					comboModelNoOfArmies.addElement(i);
				}

				for (String neighbour : neighboursToAdd) {
					comboModelNeighbourCountries.addElement(neighbour);
				}

				btnPhases.setText("Send");
				btnPhases.setActionCommand(FORTIFICATION_SEND_ARMY_ACTION);
			}
		}

		comboNoOfArmies.setModel(comboModelNoOfArmies);
		comboNeighbourCountry.setModel(comboModelNeighbourCountries);
	}

	/** Transfers armies from one country to another country as part of the fortification state. */
	private void sendArmyInFortificationPhase() {
		int selectedCountry = comboCountry.getSelectedIndex();
		int selectedNoOfArmies = comboNoOfArmies.getSelectedIndex();
		int selectedNeighbourCountry = comboNeighbourCountry.getSelectedIndex();

		if ((selectedCountry == -1) || (selectedNoOfArmies == -1) || (selectedNeighbourCountry == -1))
			return;

		Player player = holder.getActivePlayer();

		String country = comboModelCountries.getElementAt(selectedCountry);
		int noOfArmies = comboModelNoOfArmies.getElementAt(selectedNoOfArmies);
		String neighbour = comboModelNeighbourCountries.getElementAt(selectedNeighbourCountry);

		country = country.split("-")[1].trim();
		neighbour = neighbour.split("-")[1].trim();

		String message = player.fortificationPhase(country, neighbour, noOfArmies);
		holder.updatePlayer(player);
		holder.sendGameLog(message);

		this.isFortificationDone = true;
		comboModelNeighbourCountries.removeAllElements();
		comboModelNoOfArmies.removeAllElements();

		comboNeighbourCountry.setModel(comboModelNeighbourCountries);
		comboNoOfArmies.setModel(comboModelNoOfArmies);

		loadCountryListInCombo();
		changePhaseAhead();
		setupManualFortificationPhase();
	}

	/**
	 * Loads country list in the combo box.
	 * It takes an account of the active player.
	 */
	private void loadCountryListInCombo() {
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
				case PhaseData.CHANGE_PHASE:
					this.setupPhaseValues();
					break;
			}
		}
	}

	/**
	 * Reacts to the changes in the phases
	 */
	private void setupPhaseValues() {
		String message = holder.getActivePlayer().getName() + "'s turn: ";
		switch (holder.getCurrentPhase()) {
			case PhaseData.REINFORCEMENT_PHASE:
				this.setupReinforcementPhase();
				this.startReinforcement();
				message = message.concat("Reinforcement Phase");
				break;
			case PhaseData.ATTACK_PHASE:
				this.setupAttackPhase();
				message = message.concat("Attack Phase");
				break;
			case PhaseData.FORTIFICATION_PHASE:
				this.setupFortificationPhase();
				this.startFortificationPhase();
				message = message.concat("Fortification Phase");
				break;
			default:
				message = message.concat("Startup Phase");
				break;
		}
		holder.sendGameLog(message);
	}

	/**
	 * Initializes the default values and settings for the reinforcement phase
	 */
	private void setupReinforcementPhase() {
		labelPhases.setText("Reinforcement Phase");
		this.changePhaseAhead();
		this.reinforcementArmyAllocated = 0;
		comboNeighbourCountry.setVisible(false);
		comboCountry.setVisible(true);
		comboNoOfArmies.setVisible(true);
	}

	/**
	 * Starts the reinforcement phase.
	 * It automates the reinforcements phase when the user type is "Computer"
	 */
	private void startReinforcement() {
		Player player = holder.getActivePlayer();

		if (player.getType() == 0) {
			this.loadCountryListInCombo();
			return;
		}

		ReinforcementController controller = new ReinforcementController();
		changeControlButtonVisibility(false);

		int totalNoOfArmies = controller.calculateReinforcementArmies(player);
		int noOfArmies = totalNoOfArmies - this.reinforcementArmyAllocated;

		if (totalNoOfArmies != 0) {
			Random random = new Random();

			do {
				if (noOfArmies == 0)
					break;

				int armiesToAllocate = random.nextInt(noOfArmies);

				if (armiesToAllocate == 0)
					armiesToAllocate++;

				this.reinforcementArmyAllocated += armiesToAllocate;

				String message = player.reinforcementPhase(armiesToAllocate, null);
				holder.sendGameLog(message);

				noOfArmies = totalNoOfArmies - this.reinforcementArmyAllocated;
			} while (noOfArmies > 0);
			holder.updatePlayer(player);
			System.out.println("Armies allocation has been completed!");
		}

		changeControlButtonVisibility(true);
		holder.changePhases();
	}

	/**
	 * Start the fortification phase
	 */
	private void startFortificationPhase() {
		Player player = holder.getActivePlayer();

		if (player.getType() == 0) {
			loadCountryListInCombo();
			return;
		}

		changeControlButtonVisibility(false);

		String message = player.fortificationPhase(null, null, -1);
		holder.updatePlayer(player);
		holder.sendGameLog(message);

		changeControlButtonVisibility(true);
		holder.changePhases();
	}

	/**
	 * Initializes the default values and settings for the attack phase
	 */
	private void setupAttackPhase() {
		labelPhases.setText("Attack Phase");
		this.changePhaseAhead();
		comboNeighbourCountry.setVisible(false);
		comboCountry.setVisible(false);
		comboNoOfArmies.setVisible(false);
	}

	/**
	 * Initializes the default values and settings for the fortification phase
	 */
	private void setupFortificationPhase() {
		labelPhases.setText("Fortification Phase");
		this.changePhaseAhead();
		this.isFortificationDone = false;
		comboNeighbourCountry.setVisible(true);
		comboCountry.setVisible(true);
		comboNoOfArmies.setVisible(true);
	}

	/**
	 * Prepares the phases button to change the phase.
	 */
	private void changePhaseAhead() {
		btnPhases.setText("Next Phase");
		btnPhases.setActionCommand(CHANGE_PHASE);
	}

	/**
	 * Changes the visibility of the buttons that are used to play the game.
	 * @param visibility true if they are supposed to be visible
	 */
	private void changeControlButtonVisibility(boolean visibility) {
		btnPhases.setVisible(visibility);
		comboCountry.setVisible(visibility);
		comboNeighbourCountry.setVisible(visibility);
		comboNoOfArmies.setVisible(visibility);
	}
}
