package Game.View;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.filechooser.FileNameExtensionFilter;

import Game.Controller.AttackController;
import Game.Model.ContinentData;
import Game.Model.CountryData;
import Game.Model.PhaseData;
import Game.Model.Player;
import Game.Risk.DataHolder;

/**
 * This class represent the Phase View for the Main game.
 * 
 * @author Jay, ndkcha
 * @version 1.2.0
 */
@SuppressWarnings("deprecation")
public class PhaseView implements Observer {
	private boolean isStartupPhaseActive = true;
	private static final String STARTUP_ADD_ARMY = "startup:add_army";
	private static final String CHANGE_PHASE = "change:phase";
	private static final String ACTION_REINFORCEMENT_ADD_ARMY = "reinforcement:add";
	private static final String ACTION_FORTIFICATION_SEND_ARMY = "fortification:send";
	private static final String ACTION_PREPARE_ATTACK = "attack:prepare";
	private static final String CARD_EXCHANGE_ACTION = "card:exchange";
	private DataHolder holder = DataHolder.getInstance();
	private int reinforcementArmyAllocated = 0;
	private boolean isFortificationDone = false;

	private JFrame frame;
	private JButton btnPhases = new JButton();
	private JButton saveGameBtn = new JButton();
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
		saveGameBtn.setText("Save Game");

		GroupLayout Phases_panelLayout = new GroupLayout(panelPhases);
		panelPhases.setLayout(Phases_panelLayout);
		Phases_panelLayout.setHorizontalGroup(Phases_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(Phases_panelLayout.createSequentialGroup().addGap(21, 21, 21)
						.addComponent(labelPhases, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Phases_panelLayout.createSequentialGroup().addComponent(btnPhases).addGap(18, 18, 18)
						.addComponent(comboNoOfArmies, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(Phases_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
						.addComponent(comboNeighbourCountry, 0, 47, Short.MAX_VALUE)
						.addComponent(comboCountry, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Phases_panelLayout.createSequentialGroup().addComponent(saveGameBtn)));

		Phases_panelLayout.setVerticalGroup(Phases_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(Phases_panelLayout.createSequentialGroup().addComponent(labelPhases).addGap(26, 26, 26)
						.addGroup(Phases_panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(btnPhases).addComponent(comboNoOfArmies, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(comboNeighbourCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(comboCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(22, Short.MAX_VALUE).addComponent(saveGameBtn)));

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
	 * Executes the startup phase for the player's turn. It allocates the initial
	 * armies for the game
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
			case ACTION_REINFORCEMENT_ADD_ARMY:
				addArmyInReinforcementPhase();
				break;
			case ACTION_FORTIFICATION_SEND_ARMY:
				sendArmyInFortificationPhase();
				break;
			case CARD_EXCHANGE_ACTION:
				determineToSkipCardExchange();
				break;
			case ACTION_PREPARE_ATTACK:
				prepareAttack();
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
			case PhaseData.ATTACK_PHASE:
				setupManualAttackPhase();
				break;
			}
		});

		this.saveGameBtn.addActionListener((ActionEvent e) -> {
			saveGame();
		});

	}

	public void saveGame() {
		System.out.println("file selector for saving game is opened");
		frame = new JFrame("Save Game");
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setApproveButtonText("SAVE");
		jFileChooser.setCurrentDirectory(new File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Saved Game Files", "ser");
		jFileChooser.setFileFilter(filter);

		int result = jFileChooser.showOpenDialog(frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			System.out.println("game is saved");
			File file = jFileChooser.getSelectedFile();
			holder.saveGameState(file.getName());
			// saveGameState(file.getAbsolutePath());
		}

		else if (result == JFileChooser.CANCEL_OPTION) {
			System.out.println("game is not saved");
			chooseOptionFrame().dispose();
		}
	}

	/**
	 * Returns the frame to be used to dispose it after selection of an option.
	 *
	 * @return JFrame
	 */
	public JFrame chooseOptionFrame() {
		return this.frame;
	}

	/**
	 * start the attack phase
	 */
	private void startAttackPhase() {
		Player player = holder.getActivePlayer();

		if (player.getType() == 0) {
			this.loadCountryListInCombo();
			return;
		}

		AttackController controller = new AttackController();
		List<String> neighboursForAttack;
		changeControlButtonVisibility(false);
		int iterations = 0;
		Random random = new Random();
		String attacker;

		do {
			int totalCountries = player.getCountriesConquered().size();

			int pickCountry = random.nextInt(totalCountries);
			pickCountry = (pickCountry == totalCountries) ? pickCountry - 1 : pickCountry;

			attacker = player.getNthCountry(pickCountry);

			neighboursForAttack = controller.getNeighboursForAttack(attacker);

			iterations++;

			if (iterations == 10)
				break;
		} while (neighboursForAttack.size() == 0);

		if (neighboursForAttack.size() == 0) {
			holder.sendGameLog(player.getName() + " ended the attack phase");
			holder.changePhases();
			return;
		}

		int pickDefender = random.nextInt(neighboursForAttack.size());
		pickDefender = (pickDefender == neighboursForAttack.size()) ? pickDefender - 1 : pickDefender;

		String defender = neighboursForAttack.get(pickDefender);
		player.setAttackerAndDefender(attacker, defender);
		player.setAllOutMode(true);
		holder.updatePlayer(player);

		player = holder.getActivePlayer();
		int minArmiesToMove = player.attackPhase();

		player = holder.getActivePlayer();

		if (minArmiesToMove != -1) {
			int armiesToMove = minArmiesToMove;

			int existing = player.getArmiesInCountry(attacker) - 1;
			if (existing > minArmiesToMove) {
				armiesToMove = random.nextInt(existing - minArmiesToMove);
				armiesToMove += minArmiesToMove;
			}

			player.moveArmiesAfterAttack(armiesToMove);

		}
		player.resetAttackerAndDefender();

		holder.updatePlayer(player);

		player = holder.getActivePlayer();
		player.notifyChangeInPlayer();

		if (holder.hasPlayerWon(player)) {
			JOptionPane.showMessageDialog(new JFrame(), player.getName() + " has won the game!", "Yeyy!",
					JOptionPane.INFORMATION_MESSAGE);
			holder.forceEndGame();
			return;
		}

		holder.changePhases();
	}

	/**
	 * Perform the attack phase
	 */
	private void prepareAttack() {
		int attackerIndex = comboCountry.getSelectedIndex();
		int defenderIndex = comboNeighbourCountry.getSelectedIndex();

		if ((attackerIndex == -1) || (defenderIndex == -1))
			return;

		Player player = holder.getActivePlayer();

		String attacker = comboModelCountries.getElementAt(attackerIndex);
		attacker = attacker.split("-")[1].trim();
		String defender = comboModelNeighbourCountries.getElementAt(defenderIndex);
		defender = defender.split("-")[1].trim();

		Player foreignPlayer = holder.getPlayerFromCountryName(defender);
		if (foreignPlayer == null)
			return;
		int noOfAttackerArmies = player.getArmiesInCountry(attacker);
		int noOfDefenderArmies = foreignPlayer.getArmiesInCountry(defender);

		noOfAttackerArmies = (noOfAttackerArmies > 3) ? 3 : --noOfAttackerArmies;
		noOfDefenderArmies = (noOfDefenderArmies > 2) ? 2 : noOfDefenderArmies;

		player.setAttackerAndDefender(attacker, defender);

		holder.updatePlayer(player);

		this.selectAttackArmies(noOfAttackerArmies, noOfDefenderArmies, foreignPlayer.getType());

		player = holder.getActivePlayer();
		int minArmiesToMove = player.attackPhase();
		player = holder.getActivePlayer();

		if (minArmiesToMove != -1)
			selectArmiesToMove(minArmiesToMove, player.getArmiesInCountry(attacker));

		player = holder.getActivePlayer();
		player.resetAttackerAndDefender();
		holder.updatePlayer(player);

		player = holder.getActivePlayer();
		player.notifyChangeInPlayer();
		if (holder.hasPlayerWon(player)) {
			JOptionPane.showMessageDialog(new JFrame(), player.getName() + " has won the game!", "Yeyy!",
					JOptionPane.INFORMATION_MESSAGE);
			holder.forceEndGame();
			return;
		}

		comboModelNeighbourCountries.removeAllElements();
		comboNeighbourCountry.setModel(comboModelNeighbourCountries);
		loadCountryListInCombo();
		this.changePhaseAhead();
	}

	private void selectArmiesToMove(int min, int max) {
		JPanel panel = new JPanel();

		panel.add(new JLabel("Move armies from attacker to defender: "));

		JComboBox<Integer> comboArmies = new JComboBox<>();
		DefaultComboBoxModel<Integer> comboModelArmies = new DefaultComboBoxModel<>();

		comboModelArmies.removeAllElements();

		for (int i = min; i < max; i++) {
			comboModelArmies.addElement(i);
		}

		comboArmies.setModel(comboModelArmies);
		comboArmies.addActionListener((ActionEvent e) -> {
			Player player = holder.getActivePlayer();
			int selectedOption = comboArmies.getSelectedIndex();
			selectedOption = (selectedOption == -1) ? 0 : selectedOption;

			player.setArmiesToMove(comboModelArmies.getElementAt(selectedOption));

			holder.updatePlayer(player);
		});

		panel.add(comboArmies);

		int result = JOptionPane.showOptionDialog(null, panel, "Select Armies to move", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, null, null);

		Player player = holder.getActivePlayer();
		int armiesToMove = player.getArmiesToMove();
		armiesToMove = (armiesToMove == 0) ? min : armiesToMove;

		player.moveArmiesAfterAttack(armiesToMove);

		holder.updatePlayer(player);
	}

	/**
	 * Select armies for the attack phase
	 * 
	 * @param noOfAttacker max armies attacker can have
	 * @param noOfDefender max armies defender can have
	 */
	private void selectAttackArmies(int noOfAttacker, int noOfDefender, int defenderType) {
		JPanel panel = new JPanel();

		panel.add(new JLabel("No of dices (for attack): "));
		int noOfAttackerArmies = (noOfAttacker > 3) ? 3 : noOfAttacker;
		int noOfDefenderArmies = (noOfDefender > 2) ? 2 : noOfDefender;

		JComboBox<String> comboAttacker = new JComboBox<>();
		JComboBox<String> comboDefender = new JComboBox<>();
		JCheckBox checkAllOutMode = new JCheckBox("All out mode", false);

		DefaultComboBoxModel<String> modelAttacker = new DefaultComboBoxModel<>();
		DefaultComboBoxModel<String> modelDefender = new DefaultComboBoxModel<>();

		modelAttacker.removeAllElements();
		modelDefender.removeAllElements();
		modelAttacker.addElement("Attacker Armies");
		for (int i = 1; i <= noOfAttackerArmies; i++) {
			modelAttacker.addElement(String.valueOf(i));
		}
		if (defenderType == 1) {
			modelDefender.addElement("Defender Armies");
			for (int i = 1; i <= noOfDefenderArmies; i++) {
				modelDefender.addElement(String.valueOf(i));
			}
			Player player = holder.getActivePlayer();
			Random random = new Random();
			int n = random.nextInt(noOfDefenderArmies);
			n = ((n == 0) && (noOfDefenderArmies != 0)) ? 1 : n;
			player.setDefenderArmies(n);
			holder.updatePlayer(player);
		}

		comboAttacker.setModel(modelAttacker);
		comboDefender.setModel(modelDefender);

		comboAttacker.addActionListener((ActionEvent e) -> {
			if (comboAttacker.getSelectedIndex() > 0) {
				Player player = holder.getActivePlayer();
				String attackerArmies = modelAttacker.getElementAt(comboAttacker.getSelectedIndex());
				player.setAttackerArmies(Integer.parseInt(attackerArmies));
				holder.updatePlayer(player);
			}
		});

		comboDefender.addActionListener((ActionEvent e) -> {
			if (comboDefender.getSelectedIndex() > 0) {
				Player player = holder.getActivePlayer();
				String defenderArmies = modelDefender.getElementAt(comboDefender.getSelectedIndex());
				player.setDefenderArmies(Integer.parseInt(defenderArmies));
				holder.updatePlayer(player);
			}
		});

		panel.add(comboAttacker);
		panel.add(comboDefender);
		panel.add(checkAllOutMode);

		int result = JOptionPane.showOptionDialog(null, panel, "Select Dices", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, null, null);

		System.out.println("All Out Mode: " + checkAllOutMode.isSelected());

		Player player = holder.getActivePlayer();
		player.setAllOutMode(checkAllOutMode.isSelected());
		player.logAttackerAndDefender();
	}

	/** Check if we need to skip the card exchange phase */
	private void determineToSkipCardExchange() {
		Player player = holder.getActivePlayer();

		if (player.getCards().size() == 5) {
			JOptionPane.showMessageDialog(new JFrame(), "You can't hold more than 5 cards", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		this.changePhaseAhead();
		holder.changePhases();
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
			Player player = holder.getActivePlayer();
			int cardArmies = player.isCardUsed() ? (player.getCardsUsedCount() * 5) : 0;
			int totalNoOfArmies = calculateReinforcementArmies(player) + cardArmies;
			player.resetCardUsedFlag();

			holder.updatePlayer(player);

			int noOfArmies = totalNoOfArmies - this.reinforcementArmyAllocated;

			if (noOfArmies == 0)
				this.changePhaseAhead();
			else {
				for (int i = 1; i <= noOfArmies; i++) {
					comboModelNoOfArmies.addElement(i);
				}

				btnPhases.setText("Add");
				btnPhases.setActionCommand(ACTION_REINFORCEMENT_ADD_ARMY);
			}
		} else
			this.changePhaseAhead();

		comboNoOfArmies.setModel(comboModelNoOfArmies);
	}

	/**
	 * Assign armies for the startup phase from the UI. It takes the user inputs
	 * from the UI components
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
	 * Setup manual entries for the attack phase. It will help user to continue with
	 * the phase from UI.
	 */
	private void setupManualAttackPhase() {
		comboModelNeighbourCountries.removeAllElements();

		int selectedCountry = comboCountry.getSelectedIndex();

		if (selectedCountry == 0)
			return;

		Player player = holder.getActivePlayer();

		if (selectedCountry > 0) {
			AttackController controller = new AttackController();
			String countryName = comboModelCountries.getElementAt(selectedCountry);
			countryName = countryName.split("-")[1].trim();

			int noOfArmies = player.getArmiesInCountry(countryName);

			if (noOfArmies == 1)
				return;

			List<String> neighboursToAttack = controller.getNeighboursForAttack(countryName);
			for (String neighbour : neighboursToAttack) {
				int army = controller.getArmiesOfDefendingCountry(neighbour);
				comboModelNeighbourCountries.addElement(army + " - " + neighbour);
			}

			btnPhases.setText("Attack");
			btnPhases.setActionCommand(ACTION_PREPARE_ATTACK);
		} else
			this.changePhaseAhead();

		comboNeighbourCountry.setModel(comboModelNeighbourCountries);
	}

	/**
	 * Setup manual entries for fortification phase. It will help user to continue
	 * with the phase from UI.
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
				btnPhases.setActionCommand(ACTION_FORTIFICATION_SEND_ARMY);
			}
		}

		comboNoOfArmies.setModel(comboModelNoOfArmies);
		comboNeighbourCountry.setModel(comboModelNeighbourCountries);
	}

	/**
	 * Transfers armies from one country to another country as part of the
	 * fortification state.
	 */
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
	 * Loads country list in the combo box. It takes an account of the active
	 * player.
	 */
	private void loadCountryListInCombo() {
		comboModelCountries.removeAllElements();
		Player player = holder.getActivePlayer();

		AttackController controller = new AttackController();

		comboModelCountries.addElement("No country");

		for (Map.Entry<String, Integer> countryName : player.getCountriesConquered().entrySet()) {
			if ((holder.getCurrentPhase() == PhaseData.ATTACK_PHASE)
					&& (controller.getNeighboursForAttack(countryName.getKey()).size() == 0)) {
				continue;
			}
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
		if (holder.areAllPlayerDone(15)) {
			JOptionPane.showMessageDialog(new JFrame(), "Game drawn! No one won this game!", "Yeyy!",
					JOptionPane.INFORMATION_MESSAGE);
			holder.forceEndGame();
			return;
		}

		switch (holder.getCurrentPhase()) {
		case PhaseData.CARD_EXCHANGE_PHASE:
			this.setupCardExchangePhase();
			break;
		case PhaseData.REINFORCEMENT_PHASE:
			Player player = holder.getActivePlayer();
			player.takeTurn();
			holder.updatePlayer(player);

			this.setupReinforcementPhase();
			this.startReinforcement();
			break;
		case PhaseData.ATTACK_PHASE:
			this.setupAttackPhase();
			this.startAttackPhase();
			break;
		case PhaseData.FORTIFICATION_PHASE:
			this.setupFortificationPhase();
			this.startFortificationPhase();
			break;
		default:
			break;
		}
	}

	/** Initializes the card exchange phase */
	private void setupCardExchangePhase() {
		labelPhases.setText("Card Exchange Phase");
		comboNeighbourCountry.setVisible(false);
		comboCountry.setVisible(false);
		comboNoOfArmies.setVisible(false);
		btnPhases.setActionCommand(CARD_EXCHANGE_ACTION);
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
	 * Starts the reinforcement phase. It automates the reinforcements phase when
	 * the user type is "Computer"
	 */
	private void startReinforcement() {
		Player player = holder.getActivePlayer();

		if (player.getType() == 0) {
			this.loadCountryListInCombo();
			return;
		}

		changeControlButtonVisibility(false);

		int totalNoOfArmies = calculateReinforcementArmies(player);
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
		comboNeighbourCountry.setVisible(true);
		comboCountry.setVisible(true);
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
	 * 
	 * @param visibility true if they are supposed to be visible
	 */
	private void changeControlButtonVisibility(boolean visibility) {
		btnPhases.setVisible(visibility);
		comboCountry.setVisible(visibility);
		comboNeighbourCountry.setVisible(visibility);
		comboNoOfArmies.setVisible(visibility);
	}

	/**
	 * This function calculates the armies a player avails in each reinforcement
	 * phase
	 *
	 * @param player Player Object
	 * @return new armies The number of armies available for reinforcement phase.
	 */
	public int calculateReinforcementArmies(Player player) {

		// retrieving the player number whose turn is goin on
		System.out.println("Calculating armies for player " + player.getName());
		int newarmies;

		// retrieving the continents conquered by the player
		HashMap<String, Integer> countriesConquered = player.getCountriesConquered();
		System.out.println("The countries conquered by " + player.getName() + " is " + countriesConquered.keySet());

		// get armies due to conquering whole continent
		int listSizeOfCountriesConquered;
		int continentAddedArmies = 0;
		for (ContinentData continentData : holder.getContinentDataList()) {// get data for every continent
			String continentName = continentData.getName();
			List<CountryData> countriesContinent = holder.countCountriesInContinent(continentName);// get COuntries of
																									// Continent
			int countrySize = countriesContinent.size();// size of the no of countries in continent

			listSizeOfCountriesConquered = 0;
			for (CountryData countryData : countriesContinent) {/// countires in continent loop
				Iterator itForCountriesConquered = countriesConquered.entrySet().iterator();// iterator for countries
																							// conqureeed by player
				while (itForCountriesConquered.hasNext()) {
					Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
					String countryName = (String) pair.getKey();
					if (countryData.getName().equalsIgnoreCase(countryName)) {
						listSizeOfCountriesConquered++;
					}
				}
			}
			if (listSizeOfCountriesConquered == countrySize) {
				continentAddedArmies += continentData.getControlValue();
			}
		}

		System.out.println("The number of armies added due to conquering whole continent is: " + continentAddedArmies);

		// number of countries owned divided by 3 and rounded down if the player owns
		// more than 9 territores otherwise 3 territories
		if (countriesConquered.size() < 9) {
			newarmies = 3 + continentAddedArmies;
		} else {
			int armies = Math.floorDiv((countriesConquered.size()), 3);
			newarmies = armies + continentAddedArmies;
		}

		System.out.println("The number of armies available for reinforcement phase is " + newarmies);

		return newarmies;
	}

}
