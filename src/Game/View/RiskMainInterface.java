/**
 *
 */
package Game.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Random;

import Game.Controller.FortificationController;
import Game.Controller.ReinforcementController;
import Game.Model.CountryData;
import Game.Model.Player;
import Game.Risk.DataHolder;
import Game.Model.RollDice;

/**
 * Full Game Play Panel consist of Game Logs, Map, Dice info, Player Info,
 * Reinforcement, Attack and Fortification controls.
 *
 * @author Jay, ndkcha, vansh
 * @version 1.0.0
 */
public class RiskMainInterface extends JFrame {
    public static final String REINFORCEMENT_ADD_ARMY_ACTION = "reinforcement:add";
    public static final String FORTIFICATION_SEND_ARMY_ACTION = "fortification:add";
    public static final String SWITCH_PHASE = "switch:phase";
    private DataHolder holder = DataHolder.getInstance();

    private static RiskMainInterface mainView;

    private BufferedImage mapImage;

    // Variables declaration - do not modify                     
    private javax.swing.JButton Card_A_button;
    private javax.swing.JButton Card_B_button;
    private javax.swing.JButton Card_C_button;
    private javax.swing.JButton Card_D_button;
    private javax.swing.JButton Card_E_button;
    private javax.swing.JPanel Card_panel;
    private javax.swing.JComboBox<String> Country_combo;
    private javax.swing.JButton DiceValue;
    private javax.swing.JList<String> Dice_Jlist;
    private javax.swing.JPanel Dice_Panel;
    private javax.swing.JList<String> Gameplay_Jlist;
    private javax.swing.JPanel Gameplay_panel;
    private javax.swing.JPanel Image_Panel;
    private javax.swing.JComboBox<String> Neibhour_country_combo;
    private javax.swing.JComboBox<Integer> Number_armies_Combo;
    private javax.swing.JButton btnPhases;
    private javax.swing.JPanel Phases_panel;
    private javax.swing.JList<String> Player_Jlist;
    private javax.swing.JPanel Player_Panel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelPhases;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel labelPlayerTitle;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration

    private DefaultListModel<String> listModelPlayers, listModelGamePlay;
    private DefaultComboBoxModel<String> comboModelCountries, comboModelNeighbourCountries;
    private DefaultComboBoxModel<Integer> comboModelNoOfArmies;

    private ReinforcementController reinforcementController = new ReinforcementController();
    private FortificationController fortificationController = new FortificationController();

    private int reinforcementArmyAllocated = -1;
    private boolean isFortificationDone = false;

    public RiskMainInterface() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    /**
     *
     * Various panel components are initialised in initComponents method
     */
    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        Image_Panel = new JPanel();
        jLabel9 = new JLabel();
        Dice_Panel = new JPanel();
        jLabel1 = new JLabel();
        DiceValue = new JButton();
        jScrollPane2 = new JScrollPane();
        Dice_Jlist = new JList<>();
        Phases_panel = new JPanel();
        labelPhases = new JLabel();
        btnPhases = new JButton();
        Number_armies_Combo = new JComboBox<>();
        Country_combo = new JComboBox<>();
        Neibhour_country_combo = new JComboBox<>();
        Card_panel = new JPanel();
        jLabel3 = new JLabel();
        Card_A_button = new JButton();
        Card_B_button = new JButton();
        Card_C_button = new JButton();
        Card_D_button = new JButton();
        Card_E_button = new JButton();
        Player_Panel = new JPanel();
        jScrollPane3 = new JScrollPane();
        Player_Jlist = new JList<>();
        labelPlayerTitle = new JLabel();
        jLabel5 = new JLabel();
        Gameplay_panel = new JPanel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jScrollPane4 = new JScrollPane();
        Gameplay_Jlist = new JList<>();
        jLabel4 = new JLabel();

        listModelPlayers = new DefaultListModel<>();
        comboModelCountries = new DefaultComboBoxModel<>();
        comboModelNoOfArmies = new DefaultComboBoxModel<>();
        comboModelNeighbourCountries = new DefaultComboBoxModel<>();
        listModelGamePlay = new DefaultListModel<>();

        try {
            if (this.holder.bmpFile != null)
                this.mapImage = ImageIO.read(this.holder.bmpFile);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel9.setIcon(new javax.swing.ImageIcon(mapImage)); // NOI18N


        javax.swing.GroupLayout Image_PanelLayout = new javax.swing.GroupLayout(Image_Panel);
        Image_Panel.setLayout(Image_PanelLayout);
        Image_PanelLayout.setHorizontalGroup(
            Image_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Image_PanelLayout.createSequentialGroup()
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(572, 572, 572))
        );
        Image_PanelLayout.setVerticalGroup(
            Image_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );


        jScrollPane1.setViewportView(Image_Panel);


        jLabel1.setText("Dice");


        DiceValue.setText("Value");


        Dice_Jlist.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {" "};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        jScrollPane2.setViewportView(Dice_Jlist);

        javax.swing.GroupLayout Dice_PanelLayout = new javax.swing.GroupLayout(Dice_Panel);
        Dice_Panel.setLayout(Dice_PanelLayout);
        Dice_PanelLayout.setHorizontalGroup(
            Dice_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Dice_PanelLayout.createSequentialGroup()
                    .addGroup(Dice_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Dice_PanelLayout.createSequentialGroup()
                            .addGroup(Dice_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Dice_PanelLayout.createSequentialGroup()
                                    .addGap(56, 56, 56)
                                    .addComponent(jLabel1))
                                .addGroup(Dice_PanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(DiceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 118, Short.MAX_VALUE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addContainerGap())
        );
        Dice_PanelLayout.setVerticalGroup(
            Dice_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Dice_PanelLayout.createSequentialGroup()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(DiceValue)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addContainerGap())
        );

        labelPhases.setText("Phases :");

        btnPhases.setText("Phases :");

        javax.swing.GroupLayout Phases_panelLayout = new javax.swing.GroupLayout(Phases_panel);
        Phases_panel.setLayout(Phases_panelLayout);
        Phases_panelLayout.setHorizontalGroup(
            Phases_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Phases_panelLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(labelPhases, javax.swing.GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(Phases_panelLayout.createSequentialGroup()
                    .addComponent(btnPhases)
                    .addGap(18, 18, 18)
                    .addComponent(Number_armies_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Phases_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Neibhour_country_combo, 0, 47, Short.MAX_VALUE)
                    .addComponent(Country_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Phases_panelLayout.setVerticalGroup(
            Phases_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Phases_panelLayout.createSequentialGroup()
                    .addComponent(labelPhases)
                    .addGap(26, 26, 26)
                    .addGroup(Phases_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPhases)
                        .addComponent(Number_armies_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Neibhour_country_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Country_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(22, Short.MAX_VALUE))
        );
        jLabel3.setText("Card");

        Card_A_button.setText("Card 1");
        Card_B_button.setText("Card 2");
        Card_C_button.setText("Card 3");
        Card_D_button.setText("Card 4");
        Card_E_button.setText("Card 5");


        javax.swing.GroupLayout Card_panelLayout = new javax.swing.GroupLayout(Card_panel);
        Card_panel.setLayout(Card_panelLayout);
        Card_panelLayout.setHorizontalGroup(
            Card_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Card_panelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(Card_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Card_panelLayout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addComponent(jLabel3)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(Card_panelLayout.createSequentialGroup()
                            .addGroup(Card_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Card_A_button, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                .addComponent(Card_C_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(Card_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Card_B_button, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                .addComponent(Card_D_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                            .addComponent(Card_E_button, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
                .addGroup(Card_panelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(Card_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Card_panelLayout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addComponent(jLabel3)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(Card_panelLayout.createSequentialGroup()
                            .addGroup(Card_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Card_A_button, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                .addComponent(Card_C_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(Card_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Card_B_button, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                .addComponent(Card_D_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                            .addComponent(Card_E_button, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        Card_panelLayout.setVerticalGroup(
            Card_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Card_panelLayout.createSequentialGroup()
                    .addComponent(jLabel3)
                    .addGap(18, 18, 18)
                    .addGroup(Card_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Card_A_button)
                        .addComponent(Card_B_button)
                        .addComponent(Card_E_button))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(Card_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Card_C_button)
                        .addComponent(Card_D_button))
                    .addGap(0, 8, Short.MAX_VALUE))
        );


        Player_Jlist.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {" "};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        jScrollPane3.setViewportView(Player_Jlist);

        labelPlayerTitle.setText("Player :");

        jScrollPane3.setViewportView(Player_Jlist);

        javax.swing.GroupLayout Player_PanelLayout = new javax.swing.GroupLayout(Player_Panel);
        Player_Panel.setLayout(Player_PanelLayout);
        Player_PanelLayout.setHorizontalGroup(
            Player_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Player_PanelLayout.createSequentialGroup()
                    .addGroup(Player_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Player_PanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(Player_PanelLayout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(labelPlayerTitle)))
                    .addContainerGap(15, Short.MAX_VALUE))
        );
        Player_PanelLayout.setVerticalGroup(
            Player_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Player_PanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(labelPlayerTitle)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(28, Short.MAX_VALUE))
        );
        jLabel5.setText("Map");
        jLabel7.setText("             Gameplay   :");

        jScrollPane4.setViewportView(Gameplay_Jlist);

        javax.swing.GroupLayout Gameplay_panelLayout = new javax.swing.GroupLayout(Gameplay_panel);
        Gameplay_panel.setLayout(Gameplay_panelLayout);
        Gameplay_panelLayout.setHorizontalGroup(
            Gameplay_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Gameplay_panelLayout.createSequentialGroup()
                    .addGroup(Gameplay_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Gameplay_panelLayout.createSequentialGroup()
                            .addGap(145, 145, 145)
                            .addComponent(jLabel6)
                            .addGap(38, 38, 38)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(Gameplay_panelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Gameplay_panelLayout.setVerticalGroup(
            Gameplay_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Gameplay_panelLayout.createSequentialGroup()
                    .addGroup(Gameplay_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Gameplay_panelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel6))
                        .addComponent(jLabel7))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );


        jLabel4.setText("MAP :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Phases_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Dice_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Card_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(38, 38, 38)
                                    .addComponent(Player_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addGap(505, 505, 505)
                            .addComponent(jLabel5))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Gameplay_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Phases_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Dice_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Card_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(38, 38, 38)
                                    .addComponent(Player_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addGap(505, 505, 505)
                            .addComponent(jLabel5))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Gameplay_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(Card_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(22, 22, 22)
                                    .addComponent(Player_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(Dice_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(Phases_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(Gameplay_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane1)))
                    .addContainerGap())
        );

        initValues();
        initListeners();

        setVisible(true);
        pack();
    }

    /** Initialize values for the first time in the game instance */
    public void initValues() {
        listModelPlayers.removeAllElements();

        for (Player player : holder.getPlayerList()) {
            listModelPlayers.addElement(player.getName() + " (" + player.getColor() + ")");
        }

        Player_Jlist.setEnabled(false);

        Player_Jlist.setModel(listModelPlayers);

        setPhasesValues();

        initPlayerTurn();
    }

    /**
     * Initialize the player turn.
     * It refreshes number of armies and list of countries on the UI.
     */
    public void initPlayerTurn() {
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
    public void loadCountryListInCombo() {
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
    public void setPhasesValues() {
        switch (holder.currentPhase) {
            case DataHolder.REINFORCEMENT_PHASE:
                labelPhases.setText("Reinforcement Phase");
                btnPhases.setText("Next Phase");
                this.reinforcementArmyAllocated = 0;
                Neibhour_country_combo.setVisible(false);
                automateReinforcementPhase();
                break;
            case DataHolder.ATTACK_PHASE:
                labelPhases.setText("Attack Phase");
                btnPhases.setText("Next Phase");
                automateAttackPhase();
                break;
            case DataHolder.FORTIFICATION_PHASE:
                labelPhases.setText("Fortification Phase");
                isFortificationDone = false;
                Neibhour_country_combo.setVisible(true);
                btnPhases.setText("Done!");
                automateFortificationPhase();
                break;
        }
    }

    /**
     * Switch between two phases and initialize the value for the new phase.
     */
    public void changePhase() {
        holder.changePhases();
        initPlayerTurn();
        setPhasesValues();
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
    public void sendArmyInFortificationPhase() {
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
    public void calculateFortificationPhase() {
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
    public void automateFortificationPhase() {
        Player player = holder.getActivePlayer();
        String message = "";
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
                iterations++;
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
                    armiesToAllocate = 1;

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
