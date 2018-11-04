package Game.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;

import Game.Controller.ReinforcementController;
import Game.Controller.StartupController;
import Game.Model.Player;
import Game.Risk.DataHolder;

/**
 * Full Game Play Panel consist of Game Logs, Map, Dice info, Player Info,
 * Reinforcement, Attack and Fortification controls.
 *
 * @author Jay, ndkcha, vansh
 * @version 1.0.0
 */
public class RiskMainInterface extends JFrame {
    private static final String REINFORCEMENT_ADD_ARMY_ACTION = "reinforcement:add";
    private static final String FORTIFICATION_SEND_ARMY_ACTION = "fortification:add";
    private static final String SWITCH_PHASE = "switch:phase";
    private static final String STARTUP_PHASE = "startup:phase";
    private DataHolder holder = DataHolder.getInstance();

    private static RiskMainInterface mainView;

    // Variables declaration - do not modify
    private JComboBox<String> comboCountry = new JComboBox<>();
    private JList<String> listGamePlay = new JList<>();
    private JComboBox<String> comboNeighbourCountry = new JComboBox<>();
    private JComboBox<Integer> comboNumberArmy = new JComboBox<>();
    private JButton btnPhases = new JButton();
    private JLabel labelPhases = new JLabel();
    private JLabel labelPlayerTitle = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel labelCardTitle = new JLabel();
    // End of variables declaration

    private DefaultListModel<String> listModelGamePlay = new DefaultListModel<>();
    private DefaultComboBoxModel<String> comboModelCountries = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> comboModelNeighbourCountries = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<Integer> comboModelNoOfArmies = new DefaultComboBoxModel<>();

    private ReinforcementController reinforcementController = new ReinforcementController();

    private int reinforcementArmyAllocated = -1;
    private boolean isFortificationDone = false;
    private int noOfArmiesToAssign = -1;

    public RiskMainInterface() {
        initComponents();
    }

    /** Various panel components are initialised in initComponents method */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        PhaseView phaseView = this.initializePhaseView();

        JPanel panelMap = this.initializeMapView();
        JPanel panelDice = this.initializeDiceView();
        JPanel panelPhases = phaseView.getPanel();
        JPanel panelCard = this.initializeCardView();
        JPanel panelPlayers = this.initializeWorldDominationView();
        JPanel panelGamePlay = this.initializeGameLogsView();
        
        organizeLayout(panelPhases, panelDice, panelCard, panelPlayers, panelGamePlay, panelMap);

        initValues(phaseView);
        initListeners();

        setVisible(true);
        pack();
    }

    /**
     * Initialize the map view.
     * @return the panel in which the map area is loaded
     */
    private JPanel initializeMapView() {
        MapView mapView = new MapView();
        jLabel5.setText("Map");
        jLabel4.setText("MAP :");

        return mapView.getPanel();
    }

    /**
     * Initialize the dice view
     * @return the panel in which the dice area is loaded
     */
    private JPanel initializeDiceView() {
        DiceView diceView = new DiceView();

        return diceView.getPanel();
    }

    /**
     * Initialize the phase view.
     * It also attaches the relevant observers in order to keep the view updated.
     * @return the view in which the phase area is loaded
     */
    private PhaseView initializePhaseView() {
        PhaseView phaseView = new PhaseView();
        phaseView.changePhaseTitle();
        holder.attachObserverToPhase(phaseView);

        return phaseView;
    }

    /**
     * Initialize the card view.
     * @return the panel in which the card area is loaded
     */
    private JPanel initializeCardView() {
        CardsView cardsView = new CardsView();
        labelCardTitle.setText("Card");

        return cardsView.getPanel();
    }

    /**
     * Initialize the world domination view.
     * It contains the information about the player in the game.
     * It also attaches the relevant observers in order to keep the view updated.
     * @return the panel in which the domination view is loaded
     */
    private JPanel initializeWorldDominationView() {
        WorldDominationView worldDominationView = new WorldDominationView();
        worldDominationView.reloadTheList();
        worldDominationView.setActivePlayerLabel();
        holder.attachObserverToPlayers(worldDominationView);
        holder.attachObserverToPhase(worldDominationView);

        return worldDominationView.getPanel();
    }

    /**
     * Initializes the game logs view.
     * It contains all the information about the game.
     * @return the panel in which the logs view is loaded
     */
    private JPanel initializeGameLogsView() {
        GameLogsView gameLogsView = new GameLogsView();

        return gameLogsView.getPanel();
    }

    /**
     * It organizes the main content layout
     * @param panelPhases the phases view
     * @param dicePanel the dice view
     * @param panelCard the card view
     * @param panelPlayers the players view
     * @param panelGamePlay the gameplay view
     * @param mapPanel the map view
     */
    private void organizeLayout(JPanel panelPhases, JPanel dicePanel, JPanel panelCard, JPanel panelPlayers,
                                JPanel panelGamePlay, JPanel mapPanel) {
        JScrollPane jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(mapPanel);

        // Full layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(panelPhases, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(dicePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(panelCard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(38, 38, 38)
                                    .addComponent(panelPlayers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addGap(505, 505, 505)
                            .addComponent(jLabel5))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(panelGamePlay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 1132, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(panelPhases, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(dicePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(panelCard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(38, 38, 38)
                                    .addComponent(panelPlayers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addGap(505, 505, 505)
                            .addComponent(jLabel5))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(panelGamePlay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 740, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(panelCard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(22, 22, 22)
                                    .addComponent(panelPlayers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(dicePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(panelPhases, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(panelGamePlay, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane1)))
                    .addContainerGap())
        );
    }

    /** Initialize values for the first time in the game instance */
    private void initValues(PhaseView phaseView) {
        if (holder.isArmiesAutomatic) {
            StartupController controller = new StartupController();
            controller.assignArmies();

            holder.changePhases();
        } else {
            holder.determineOfInitialArmy();
            phaseView.startInitialArmyAssignment();
        }
    }

    /** Autoassign armies for start up phase */
    private void autoAssignArmies() {
        if (noOfArmiesToAssign == 0) {
            holder.currentPhase = 0;
            this.setPhasesValues();
            initPlayerTurn();
            return;
        }

        Random random = new Random();
        Player player = holder.getActivePlayer();

        labelPlayerTitle.setText("Player: (turn: " + player.getName() + ")");

        if (player.getType() == 1) {
            HashMap<String, Integer> countriesConquered = player
                .getCountriesConquered();
            Object[] entries = countriesConquered.keySet().toArray();

            int randomCountryIndex = random.nextInt(entries.length);
            String randomCountry = (String) entries[randomCountryIndex];
            int noOfArmies = countriesConquered.get(randomCountry);

            player.updateCountry(randomCountry, ++noOfArmies);
            holder.updatePlayer(player);

            holder.changeTurn();
            autoAssignArmies();
        } else {
            loadCountryListInCombo();
            btnPhases.setText("Add Army");
            btnPhases.setActionCommand(STARTUP_PHASE);
        }
    }

    /**
     * Initialize the player turn.
     * It refreshes number of armies and list of countries on the UI.
     */
    private void initPlayerTurn() {
        String currentPlayerName = holder.getActivePlayer().getName();
        labelPlayerTitle.setText("Player: (turn: " + currentPlayerName + ")");

        comboModelNoOfArmies.removeAllElements();
        comboModelNeighbourCountries.removeAllElements();

        this.loadCountryListInCombo();

        String message = currentPlayerName + "'s turn  - ";
        switch (holder.currentPhase) {
            case DataHolder.REINFORCEMENT_PHASE:
                message += " Reinforcement Phase";
                break;
            case DataHolder.ATTACK_PHASE:
                message += " Attack Phase";
                break;
            case DataHolder.FORTIFICATION_PHASE:
                message += " Fortification Phase";
                break;
        }
        listModelGamePlay.add(0, message);
        listGamePlay.setModel(listModelGamePlay);

        comboNeighbourCountry.setModel(comboModelNeighbourCountries);
        comboNumberArmy.setModel(comboModelNoOfArmies);
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

    /**
     * Set generic variables that are supposed to be changed because of the change in phases.
     */
    private void setPhasesValues() {
        switch (holder.currentPhase) {
            case DataHolder.REINFORCEMENT_PHASE:
                labelPhases.setText("Reinforcement Phase");
                btnPhases.setText("Next Phase");
                this.reinforcementArmyAllocated = 0;
                comboNeighbourCountry.setVisible(false);
                comboCountry.setVisible(true);
                comboNumberArmy.setVisible(true);
                automateReinforcementPhase();
                break;
            case DataHolder.ATTACK_PHASE:
                labelPhases.setText("Attack Phase");
                btnPhases.setText("Next Phase");
                comboCountry.setVisible(false);
                comboNumberArmy.setVisible(false);
                automateAttackPhase();
                break;
            case DataHolder.FORTIFICATION_PHASE:
                labelPhases.setText("Fortification Phase");
                isFortificationDone = false;
                comboNeighbourCountry.setVisible(true);
                comboCountry.setVisible(true);
                comboNumberArmy.setVisible(true);
                btnPhases.setText("Done!");
                automateFortificationPhase();
                break;
        }
    }

    /**
     * Switch between two phases and initialize the value for the new phase.
     */
    private void changePhase() {
        holder.changePhases();
        initPlayerTurn();
        setPhasesValues();
    }

    /** Assign armies in start up phase */
    private void assignArmies() {
        int selectedCountry = comboCountry.getSelectedIndex();

        if (selectedCountry < 1)
            return;

        Player player = holder.getActivePlayer();

        String country = comboModelCountries.getElementAt(selectedCountry).split("-")[1].trim();
        int noOfArmies = player.getCountriesConquered().get(country);

        player.updateCountry(country, ++noOfArmies);
        holder.updatePlayer(player);

        holder.changeTurn();
        this.noOfArmiesToAssign--;
        System.out.println(this.noOfArmiesToAssign + " left");
        autoAssignArmies();
    }

    private void initListeners() {
        comboCountry.addActionListener((ActionEvent e) -> {
            switch (holder.currentPhase) {
                case DataHolder.REINFORCEMENT_PHASE:
                    calculateReinforcementPhase();
                    break;
                case DataHolder.FORTIFICATION_PHASE:
                    calculateFortificationPhase();
                    break;
            }
        });

        btnPhases.addActionListener((ActionEvent e) -> {
            String actionCommands = btnPhases.getActionCommand();

            switch (actionCommands) {
                case STARTUP_PHASE:
                    assignArmies();
                    break;
                case REINFORCEMENT_ADD_ARMY_ACTION:
                    addArmyInReinforcementPhase();
                    break;
                case FORTIFICATION_SEND_ARMY_ACTION:
                    sendArmyInFortificationPhase();
                    break;
                case SWITCH_PHASE:
                    this.changePhase();
                    break;
            }
        });
    }

    /** Transfers armies from one country to another country as part of the fortification state. */
    private void sendArmyInFortificationPhase() {
        int selectedCountry = comboCountry.getSelectedIndex();
        int selectedNoOfArmies = comboNumberArmy.getSelectedIndex();
        int selectedNeighbourCountry = comboNeighbourCountry.getSelectedIndex();

        if ((selectedCountry == -1) || (selectedNoOfArmies == -1) || (selectedNeighbourCountry == -1))
            return;

        Player player = holder.getActivePlayer();

        String country = comboModelCountries.getElementAt(selectedCountry);
        int noOfArmies = comboModelNoOfArmies.getElementAt(selectedNoOfArmies);
        String neighbour = comboModelNeighbourCountries.getElementAt(selectedNeighbourCountry);

        country = country.split("-")[1].trim();
        neighbour = neighbour.split("-")[1].trim();

        int armiesInSender = player.getCountriesConquered().get(country);
        int armiesInReceiver = player.getCountriesConquered().get(neighbour);

        armiesInSender -= noOfArmies;
        armiesInReceiver += noOfArmies;

        player.updateCountry(country, armiesInSender);
        player.updateCountry(neighbour, armiesInReceiver);

        holder.updatePlayer(player);

        comboModelNoOfArmies.removeAllElements();
        comboModelNeighbourCountries.removeAllElements();

        comboNumberArmy.setModel(comboModelNoOfArmies);
        comboNeighbourCountry.setModel(comboModelNeighbourCountries);

        isFortificationDone = true;

        listModelGamePlay.add(0, player.getName() + " sent " + noOfArmies
            + " arm(ies) from " + country + " to " + neighbour);
        listGamePlay.setModel(listModelGamePlay);

        loadCountryListInCombo();
        prepareForFinishingTurn();
    }

    /**
     * Add selected number of armies to the country in reinforcement phase
     */
    private void addArmyInReinforcementPhase() {
        int selectedCountry = comboCountry.getSelectedIndex();
        int selectedNoOfArmies = comboNumberArmy.getSelectedIndex();

        if ((selectedCountry == -1) || (selectedNoOfArmies == -1))
            return;

        String country = comboModelCountries.getElementAt(selectedCountry);
        int noOfArmies = comboModelNoOfArmies.getElementAt(selectedNoOfArmies);

        Player player = holder.getActivePlayer();
        country = country.split("-")[1].trim();
        int existingArmies = player.getArmiesInCountry(country);

        player.updateCountry(country, noOfArmies + existingArmies);

        holder.updatePlayer(player);
        this.reinforcementArmyAllocated += noOfArmies;

        listModelGamePlay.add(0, player.getName() + " added " + noOfArmies + " armies to " + country);
        listGamePlay.setModel(listModelGamePlay);

        loadCountryListInCombo();
        calculateReinforcementPhase();
    }

    /**
     * It calculates the countries in order to display on the UI for fortification phase.
     * It's done only once.
     */
    private void calculateFortificationPhase() {
        if (isFortificationDone)
            return;

        comboModelNoOfArmies.removeAllElements();
        comboModelNeighbourCountries.removeAllElements();

        int selectedCountry = comboCountry.getSelectedIndex();
        Player player = holder.getActivePlayer();

        if (selectedCountry == -1)
            return;

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
                prepareForFinishingTurn();
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

        comboNeighbourCountry.setModel(comboModelNeighbourCountries);
        comboNumberArmy.setModel(comboModelNoOfArmies);
    }

    /**
     * Changes the visibility of the buttons that are used to play the game.
     * @param visibility true if they are supposed to be visible
     */
    private void changeControlButtonVisibility(boolean visibility) {
        btnPhases.setVisible(visibility);
        comboCountry.setVisible(visibility);
        comboNeighbourCountry.setVisible(visibility);
        comboNumberArmy.setVisible(visibility);
    }

    /**
     * It automates the fortification phase
     */
    private void automateFortificationPhase() {
        System.out.println("Entered fortification phase [automated]");
        Player player = holder.getActivePlayer();
        String message;
        Random random = new Random();

        if (player.getType() == 0)
            return;

        changeControlButtonVisibility(false);
        int noOfCountriesConquered = player.getCountriesConquered().size();
        int iterations = 0;

        String countryName = "", targetCountry = "";

        do {
            int pickCountry = random.nextInt(noOfCountriesConquered - 1);

            if (pickCountry < 0)
                break;

            countryName = player.getNthCountry(pickCountry);
            if (player.getCountriesConquered().get(countryName) == 1) {
                System.out.println(countryName + " has only one army");
                iterations++;

                if (iterations == 10)
                    break;
                continue;
            }

            for (String neighbour : holder.getCountry(countryName).getNeighbours()) {
                if (player.getCountriesConquered().containsKey(neighbour)) {
                    targetCountry = neighbour;
                    break;
                }
            }
            iterations++;

            if (iterations == 10)
                break;
        } while (targetCountry.length() != 0);

        if (targetCountry.length() == 0)
            message = " skipped fortification";
        else {
            int noOfArmies = player.getCountriesConquered().get(countryName);
            int noOfArmiesToSend = random.nextInt(noOfArmies);

            int armiesLeftInSource = noOfArmies - noOfArmiesToSend;
            int armiesInTarget = player.getCountriesConquered().get(targetCountry) + noOfArmiesToSend;

            player.updateCountry(countryName, armiesLeftInSource);
            player.updateCountry(targetCountry, armiesInTarget);

            holder.updatePlayer(player);

            message = " transferred " + noOfArmiesToSend + " armies from " + countryName + " to " + targetCountry;
        }

        changeControlButtonVisibility(true);

        listModelGamePlay.add(0, player.getName() + message);
        listGamePlay.setModel(listModelGamePlay);

        this.changePhase();
    }

    /** It automates the attack phase for computer users */
    private void automateAttackPhase() {
        this.changePhase();
    }

    /** It automates the reinforcement phase for computer users */
    private void automateReinforcementPhase() {
        Player player = holder.getActivePlayer();

        if (player.getType() == 0)
            return;

        changeControlButtonVisibility(false);

        int totalNumberOfArmies = reinforcementController.calculateReinformentArmies(holder.playerTurn);
        int noOfArmies = totalNumberOfArmies - this.reinforcementArmyAllocated;

        if (noOfArmies != 0)  {
            Random random = new Random();
            do {
                int armiesToAllocate = random.nextInt(noOfArmies);
                if (armiesToAllocate == 0)
                    break;

                this.reinforcementArmyAllocated += armiesToAllocate;

                Object countries[] = player.getCountriesConquered().keySet().toArray();
                int countryIndex = random.nextInt(countries.length);
                String country = (String) countries[countryIndex];

                int existingArmies = player.getCountriesConquered().get(country);
                existingArmies += armiesToAllocate;
                player.updateCountry(country, existingArmies);

                listModelGamePlay.add(0, player.getName() + " added " + armiesToAllocate + " armies to " + country);
                listGamePlay.setModel(listModelGamePlay);

                holder.updatePlayer(player);

                noOfArmies = totalNumberOfArmies - this.reinforcementArmyAllocated;
            } while (noOfArmies != 0);

            System.out.println("Armies allocation has been completed!");
        }

        changeControlButtonVisibility(true);
        prepareForAttackPhase();
        changePhase();
    }

    /**
     * Calculate number of armies to assign in reinforcement phase
     */
    private void calculateReinforcementPhase() {
        comboModelNoOfArmies.removeAllElements();

        if (comboCountry.getSelectedIndex() == -1)
            return;

        if (comboCountry.getSelectedIndex() > 0) {
            int totalNumberOfArmies = reinforcementController.calculateReinformentArmies(holder.playerTurn);
            int noOfArmies = totalNumberOfArmies - this.reinforcementArmyAllocated;

            if (noOfArmies == 0)
                prepareForAttackPhase();
            else {
                for (int i = 1; i <= noOfArmies; i++) {
                    comboModelNoOfArmies.addElement(i);
                }

                btnPhases.setText("Add");
                btnPhases.setActionCommand(REINFORCEMENT_ADD_ARMY_ACTION);
            }
        } else
            prepareForAttackPhase();

        comboNumberArmy.setModel(comboModelNoOfArmies);
    }

    private void prepareForFinishingTurn() {
        btnPhases.setText("Done!");
        btnPhases.setActionCommand(SWITCH_PHASE);
    }

    private void prepareForAttackPhase() {
        btnPhases.setText("Attack");
        btnPhases.setActionCommand(SWITCH_PHASE);
    }

    public static void createInstance() {
        if (mainView == null) {
            mainView = new RiskMainInterface();
        }
    }

}
