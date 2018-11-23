/**
 * 
 */
package Game.View;

import Game.Risk.DataHolder;

import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class for the Dice View on Risk Main screen with observable pattern.
 * 
 * @author Jay
 * @version 1.2.0
 */
public class SaveGameView implements Observer {
	private DataHolder holder = DataHolder.getInstance();

	private JButton btnSaveGame;
	private JPanel panelSaveGame;
	
	/**
	 * Constructor for the Dice View.
	 */
	public SaveGameView() {
		JLabel jLabel1 = new JLabel();
		btnSaveGame = new JButton();
		panelSaveGame = new JPanel();
		
		jLabel1.setText("Risk!");
        btnSaveGame.setText("Save Game");
        
        GroupLayout Dice_PanelLayout = new GroupLayout(panelSaveGame);
        panelSaveGame.setLayout(Dice_PanelLayout);
        Dice_PanelLayout.setHorizontalGroup(
            Dice_PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(Dice_PanelLayout.createSequentialGroup()
                    .addGroup(Dice_PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(Dice_PanelLayout.createSequentialGroup()
                            .addGroup(Dice_PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(Dice_PanelLayout.createSequentialGroup()
                                    .addGap(56, 56, 56)
                                    .addComponent(jLabel1))
                                .addGroup(Dice_PanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(btnSaveGame, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 118, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        Dice_PanelLayout.setVerticalGroup(
            Dice_PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(Dice_PanelLayout.createSequentialGroup()
                    .addComponent(jLabel1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnSaveGame)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addContainerGap())
        );

        panelSaveGame.setVisible(true);

        this.initListeners();
	}

	/** Initialize the action listeners */
	private void initListeners() {
		this.btnSaveGame.addActionListener((ActionEvent e) -> saveGame(fileSelector()));
	}

	/**
	 * Save the game to file
	 */
	public void saveGame(File fileToSave) {
		try {
			if (fileToSave == null)
				return;

			FileOutputStream fileOutputStream = new FileOutputStream(fileToSave);
			ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

			outputStream = holder.saveTheGameData(outputStream);

			outputStream.close();
			fileOutputStream.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (IOException e) {
			System.out.println("Error initializing the stream");
			e.printStackTrace();
		}
	}

	/**
	 * Collect the file from the user that needs to be saved at.
	 *
	 * @return map_path Stores the absolute path of the map file and bmp file.
	 */
	private File fileSelector() {
		System.out.println("File save dialog opened");
		JFrame frame = new JFrame("Save Game File");

		// Upload map file.
		// https://coderanch.com/t/466536/java/closing-jFileChooser-window
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("./files/map"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Risk Game Files", "risk");
		fileChooser.setFileFilter(filter);

		int returnValue = fileChooser.showSaveDialog(frame);
		// Get the path of the file.
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			frame.dispose();

			String path = selectedFile.getAbsolutePath();
			int indexOfDot = path.lastIndexOf(".");
			if ((indexOfDot == -1) || !path.substring(indexOfDot).equalsIgnoreCase(".risk")) {
				selectedFile = new File(path + ".risk");
			}

			return selectedFile;
		}
		return null;
	}

	/**
	 * Return Dice Panel for main risk view.
	 * 
	 * @return panelSaveGame Panel for Dice View.
	 */
	public JPanel getPanel() {
		 return this.panelSaveGame;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
