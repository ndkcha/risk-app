package Game.View;

import javax.swing.*;

/**
 * This class will provide the dialog box for number of games and maps
 * @author ndkcha
 *
 */
public class DialogSelectGameNo extends JPanel {
    private JComboBox<String> comboAttacker = new JComboBox<>();
    private JLabel title = new JLabel();

    /**
     * Constructor for dialog box UI initialization.
     */
    public DialogSelectGameNo() {
        this.add(title);

        DefaultComboBoxModel<String> modelAttacker = new DefaultComboBoxModel<>();

        modelAttacker.removeAllElements();
        for (int i = 1; i <= 5; i++) {
            modelAttacker.addElement(String.valueOf(i));
        }

        comboAttacker.setModel(modelAttacker);
        this.add(comboAttacker);
    }

    /**
     * This method will select the number of games and maps.
     * 
     * @param type The game or Map
     * @return getSelectedIndex The number of games and maps.
     */
    public int selectNumberOfGames(String type) {
        this.title.setText("No of " + type);
        int result = JOptionPane.showOptionDialog(null, this, "Select number of " + type,
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        return comboAttacker.getSelectedIndex() + 1;
    }
}
