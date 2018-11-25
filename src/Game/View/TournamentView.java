/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Observable;
import java.util.Observer;

/**
 * The view for the tournament mode
 * @author vansh, ndkcha
 */
@SuppressWarnings("deprecation")
public class TournamentView extends JFrame implements Observer {

    private JButton btnMap1Game1, btnMap1Game2, btnMap1Game3, btnMap1Game4, btnMap1Game5;
    private JButton btnMap2Game1, btnMap2Game2, btnMap2Game3, btnMap2Game4, btnMap2Game5;
    private JButton btnMap3Game1, btnMap3Game2, btnMap3Game3, btnMap3Game4, btnMap3Game5;
    private JButton btnMap4Game1, btnMap4Game2, btnMap4Game3, btnMap4Game4, btnMap4Game5;
    private JButton btnMap5Game1, btnMap5Game2, btnMap5Game3, btnMap5Game4, btnMap5Game5;
    private JLabel labelMap1, labelMap2, labelMap3, labelMap4, labelMap5;
    private JLabel labelResults, labelGameLogs;
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JSeparator jSeparator1 = new JSeparator();
    private JTable tableTournament = new JTable();
    private JList<String> listGameLogs = new JList<>();

    private DefaultListModel<String> modelListGameLogs = new DefaultListModel<>();
    private DefaultTableModel modelTournament = new DefaultTableModel();

     /**
     * Creates new form NewJFrame
     */
    public TournamentView  () {
        labelMap1 = new JLabel("Map 1:");
        btnMap1Game1 = new JButton("Game 1");
        btnMap1Game2 = new JButton("Game 2");
        btnMap1Game3 = new JButton("Game 3");
        btnMap1Game4 = new JButton("Game 4");
        btnMap1Game5 = new JButton("Game 5");
        labelMap2 = new JLabel("Map 2:");
        btnMap2Game1 = new JButton("Game 1");
        btnMap2Game2 = new JButton("Game 2");
        btnMap2Game3 = new JButton("Game 3");
        btnMap2Game4 = new JButton("Game 4");
        btnMap2Game5 = new JButton("Game 5");
        labelMap3 = new JLabel("Map 3:");
        btnMap3Game1 = new JButton("Game 1");
        btnMap3Game2 = new JButton("Game 2");
        btnMap3Game3 = new JButton("Game 3");
        btnMap3Game4 = new JButton("Game 4");
        btnMap3Game5 = new JButton("Game 5");
        labelMap4 = new JLabel("Map 4:");
        btnMap4Game1 = new JButton("Game 1");
        btnMap4Game2 = new JButton("Game 2");
        btnMap4Game3 = new JButton("Game 3");
        btnMap4Game4 = new JButton("Game 4");
        btnMap4Game5 = new JButton("Game 5");
        labelMap5 = new JLabel("Map 5:");
        btnMap5Game1 = new JButton("Game 1");
        btnMap5Game2 = new JButton("Game 2");
        btnMap5Game3 = new JButton("Game 3");
        btnMap5Game4 = new JButton("Game 4");
        btnMap5Game5 = new JButton("Game 5");
        labelResults = new JLabel("Results:");
        labelGameLogs = new JLabel("Game Logs:");
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    public void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(listGameLogs);
        jScrollPane2.setViewportView(tableTournament);
        tableTournament.setModel(this.modelTournament);
        listGameLogs.setModel(this.modelListGameLogs);

        this.organizeTheLayout();

        setVisible(true);
        pack();
    }

    /**
     * Organize the layout on the UI.
     * It will setup all the components on the view.
     */
    private void organizeTheLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(labelMap1, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelMap2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelMap4, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelMap5, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelMap3, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelResults)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnMap1Game1)
                                        .addComponent(btnMap2Game1))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnMap1Game2, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnMap1Game3))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnMap2Game2, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnMap2Game3, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnMap2Game4, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnMap2Game5, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnMap1Game4)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnMap1Game5))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnMap4Game1)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnMap4Game2, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnMap4Game3, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnMap3Game1)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnMap3Game2, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                                            .addGap(20, 20, 20)
                                            .addComponent(btnMap3Game3))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnMap5Game1)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnMap5Game2, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnMap5Game3, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(btnMap3Game4)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnMap3Game5, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(btnMap4Game4, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnMap4Game5, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(btnMap5Game4)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnMap5Game5, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))))
                            .addGap(17, 17, 17)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                    .addComponent(labelGameLogs, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                    .addGap(145, 145, 145))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSeparator1))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1)
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labelMap1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnMap1Game1)
                                .addComponent(btnMap1Game2)
                                .addComponent(btnMap1Game3)
                                .addComponent(btnMap1Game4)
                                .addComponent(btnMap1Game5))
                            .addGap(18, 18, 18)
                            .addComponent(labelMap2)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnMap2Game1)
                                .addComponent(btnMap2Game2)
                                .addComponent(btnMap2Game3)
                                .addComponent(btnMap2Game4)
                                .addComponent(btnMap2Game5))
                            .addGap(27, 27, 27)
                            .addComponent(labelMap3)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnMap3Game1)
                                .addComponent(btnMap3Game2)
                                .addComponent(btnMap3Game3)
                                .addComponent(btnMap3Game4)
                                .addComponent(btnMap3Game5))
                            .addGap(18, 18, 18)
                            .addComponent(labelMap4)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnMap4Game1)
                                .addComponent(btnMap4Game2)
                                .addComponent(btnMap4Game3)
                                .addComponent(btnMap4Game4)
                                .addComponent(btnMap4Game5))
                            .addGap(18, 18, 18)
                            .addComponent(labelMap5)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnMap5Game1)
                                .addComponent(btnMap5Game2)
                                .addComponent(btnMap5Game3)
                                .addComponent(btnMap5Game4)
                                .addComponent(btnMap5Game5))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(labelResults)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labelGameLogs)
                            .addGap(5, 5, 5)
                            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 490, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
