package Game.View;

import javax.swing.*;

import Game.Controller.StartupController;
import Game.Risk.DataHolder;

/**
 * Full Game Play Panel consist of Game Logs, Map, Dice info, Player Info,
 * Reinforcement, Attack and Fortification controls.
 *
 * @author Jay, ndkcha, vansh
 * @version 1.2.0
 */
public class RiskMainInterface extends JFrame {
    private DataHolder holder = DataHolder.getInstance();

    private static RiskMainInterface mainView;

    // Variables declaration - do not modify
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel labelCardTitle = new JLabel();
    // End of variables declaration

    public RiskMainInterface(boolean isTournamentMode) {
        initComponents(isTournamentMode);
    }

    /** Various panel components are initialised in initComponents method */
    @SuppressWarnings("unchecked")
    private void initComponents(boolean isTournamentMode) {
        PhaseView phaseView = this.initializePhaseView();

        JPanel panelMap = this.initializeMapView();
        JPanel panelDice = this.initializeDiceView();
        JPanel panelPhases = phaseView.getPanel();
        JPanel panelCard = this.initializeCardView();
        JPanel panelPlayers = this.initializeWorldDominationView();
        JPanel panelGamePlay = this.initializeGameLogsView();
        
        organizeLayout(panelPhases, panelDice, panelCard, panelPlayers, panelGamePlay, panelMap);

        if (!isTournamentMode)
            setVisible(true);
        pack();

        initValues(phaseView);
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
        holder.attachObserverToPhase(cardsView);
        holder.attachObserverToPlayers(cardsView);
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
        holder.attachObserverToLogsView(gameLogsView);

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

    public static void createInstance() {
        if (mainView == null) {
            mainView = new RiskMainInterface(false);
        }
    }

}
