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
    private JComboBox<String> Country_combo;
    private JList<String> Gameplay_Jlist;
    private JComboBox<String> Neibhour_country_combo;
    private JComboBox<Integer> Number_armies_Combo;
    private JButton btnPhases;
    private JLabel labelPhases;
    private JLabel labelPlayerTitle;
    // End of variables declaration

    private DefaultListModel<String> listModelGamePlay;
    private DefaultComboBoxModel<String> comboModelCountries, comboModelNeighbourCountries;
    private DefaultComboBoxModel<Integer> comboModelNoOfArmies;

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
        JScrollPane jScrollPane1 = new JScrollPane();
        JLabel jLabel4 = new JLabel();
        JLabel jLabel5 = new JLabel();
        JLabel labelCardTitle = new JLabel();
        labelPhases = new JLabel();
        btnPhases = new JButton();
        Number_armies_Combo = new JComboBox<>();
        Country_combo = new JComboBox<>();
        Neibhour_country_combo = new JComboBox<>();
        labelPlayerTitle = new JLabel();
        Gameplay_Jlist = new JList<>();

        comboModelCountries = new DefaultComboBoxModel<>();
        comboModelNoOfArmies = new DefaultComboBoxModel<>();
        comboModelNeighbourCountries = new DefaultComboBoxModel<>();
        listModelGamePlay = new DefaultListModel<>();  
        
        // Map View Start.
        MapView mapView = new MapView();
        JPanel mapPanel = mapView.getPanel();
        jLabel5.setText("Map");
        jLabel4.setText("MAP :");
        jScrollPane1.setViewportView(mapPanel);
        // Map View Ends

        // Dice View Starts.
        DiceView diceView = new DiceView();
        JPanel dicePanel = diceView.getPanel();
        // Dice Panel ENds
        
        // Phase Control start
        PhaseView phaseView = new PhaseView();
        JPanel panelPhases = phaseView.getPanel();
        phaseView.changePhaseTitle();
        holder.attachObserverToPhase(phaseView);
        // Phase Control Ends
        
        // Cards view Start.
        CardsView cardsView = new CardsView();
        JPanel panelCard = cardsView.getPanel();
        labelCardTitle.setText("Card");
        // Cards view ends

        // World Domination View Start.
        WorldDominationView worldDominationView = new WorldDominationView();
        JPanel panelPlayers = worldDominationView.getPanel();
        worldDominationView.reloadTheList();
        worldDominationView.setActivePlayerLabel();
        holder.attachObserverToPlayers(worldDominationView);
        holder.attachObserverToPhase(worldDominationView);
        // WDV Ends
        
        // Game Log start
        GameLogsView gameLogsView = new GameLogsView();
        JPanel panelGamePlay = gameLogsView.getPanel();
        // Game Log ends
        
//        organizeLayout();

        initValues();
        initListeners();

        setVisible(true);
        pack();
    }

    private void organizeLayout(JPanel panelPhases, JPanel dicePanel, JPanel panelCard, JPanel panelPlayers,
                                JPanel panelGamePlay, JLabel jLabel4, JLabel jLabel5) {
        JScrollPane jScrollPane1 = new JScrollPane();

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
    private void initValues() {
        Country_combo.setVisible(true);
        Number_armies_Combo.setVisible(false);
        Neibhour_country_combo.setVisible(false);

        if (holder.isArmiesAutomatic) {
            StartupController controller = new StartupController();
            controller.assignArmies();

            holder.currentPhase = 0;
            setPhasesValues();
            initPlayerTurn();
        } else {
            this.determineOfInitialArmy(holder.getPlayerList().size());
            autoAssignArmies();
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
        Gameplay_Jlist.setModel(listModelGamePlay);

        Neibhour_country_combo.setModel(comboModelNeighbourCountries);
        Number_armies_Combo.setModel(comboModelNoOfArmies);
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

        Country_combo.setModel(comboModelCountries);
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
                Neibhour_country_combo.setVisible(false);
                Country_combo.setVisible(true);
                Number_armies_Combo.setVisible(true);
                automateReinforcementPhase();
                break;
            case DataHolder.ATTACK_PHASE:
                labelPhases.setText("Attack Phase");
                btnPhases.setText("Next Phase");
                Country_combo.setVisible(false);
                Number_armies_Combo.setVisible(false);
                automateAttackPhase();
                break;
            case DataHolder.FORTIFICATION_PHASE:
                labelPhases.setText("Fortification Phase");
                isFortificationDone = false;
                Neibhour_country_combo.setVisible(true);
                Country_combo.setVisible(true);
                Number_armies_Combo.setVisible(true);
                btnPhases.setText("Done!");
                automateFortificationPhase();
                break;
        }
    }

    /**
     * Based on number of players, this method determines the number of armies
     * allowed for the initial game play
     *
     * @param noOfPlayers Number of players in the game play
     */
    private void determineOfInitialArmy(int noOfPlayers) {
        this.noOfArmiesToAssign = 40 - ((noOfPlayers - 2) * 5);
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
        int selectedCountry = Country_combo.getSelectedIndex();

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
        Country_combo.addActionListener((ActionEvent e) -> {
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
        int selectedCountry = Country_combo.getSelectedIndex();
        int selectedNoOfArmies = Number_armies_Combo.getSelectedIndex();
        int selectedNeighbourCountry = Neibhour_country_combo.getSelectedIndex();

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

        Number_armies_Combo.setModel(comboModelNoOfArmies);
        Neibhour_country_combo.setModel(comboModelNeighbourCountries);

        isFortificationDone = true;

        listModelGamePlay.add(0, player.getName() + " sent " + noOfArmies
            + " arm(ies) from " + country + " to " + neighbour);
        Gameplay_Jlist.setModel(listModelGamePlay);

        loadCountryListInCombo();
        prepareForFinishingTurn();
    }

    /**
     * Add selected number of armies to the country in reinforcement phase
     */
    private void addArmyInReinforcementPhase() {
        int selectedCountry = Country_combo.getSelectedIndex();
        int selectedNoOfArmies = Number_armies_Combo.getSelectedIndex();

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
        Gameplay_Jlist.setModel(listModelGamePlay);

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

        int selectedCountry = Country_combo.getSelectedIndex();
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

        Neibhour_country_combo.setModel(comboModelNeighbourCountries);
        Number_armies_Combo.setModel(comboModelNoOfArmies);
    }

    /**
     * Changes the visibility of the buttons that are used to play the game.
     * @param visibility true if they are supposed to be visible
     */
    private void changeControlButtonVisibility(boolean visibility) {
        btnPhases.setVisible(visibility);
        Country_combo.setVisible(visibility);
        Neibhour_country_combo.setVisible(visibility);
        Number_armies_Combo.setVisible(visibility);
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
        Gameplay_Jlist.setModel(listModelGamePlay);

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
                Gameplay_Jlist.setModel(listModelGamePlay);

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

        if (Country_combo.getSelectedIndex() == -1)
            return;

        if (Country_combo.getSelectedIndex() > 0) {
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

        Number_armies_Combo.setModel(comboModelNoOfArmies);
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
