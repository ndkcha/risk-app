package Game.View;

import Game.Model.ContinentData;
import Game.Model.CountryData;
import Game.Risk.MapEditorDataHolder;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The view that displays map editor
 * 
 * @author Jay, ndkcha
 * @version 1.0.0
 */
public class MapEditorView extends JFrame {
	private MapEditorDataHolder holder = MapEditorDataHolder.getInstance();

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private JButton btnAddContinent, btnDeleteContinent, btnUpdateContinent;
	private JButton btnSaveMap, btnUpdateMap, btnExit, btnAddCountry,
			btnUpdateCountry, btnDeleteCountry;
	private Box.Filler filler1;
	private JComboBox comboNeighbourCountry, comboContinents;
	private DefaultComboBoxModel<String> comboModelContinents,
			comboModelNeighbourCountries;
	private JLabel labelContinent, labelNeighbourCountry, labelAdjacentCountry,
			labelCountry, labelCountryName, labelContinentName;
	private JLabel labelControlValue, labelCountryX, labelCountryContinent,
			labelCountryY;
	private JList listContinents, listCountries, listNeighbourCountries;
	private DefaultListModel<String> listModelContinents, listModelCountries,
			listModelNeighbourCountries;
	private JPanel panel;
	private JScrollPane jScrollPane1, jScrollPane4, jScrollPane5, jScrollPane6;
	private JSeparator jSeparator2;
	private JTable jTable1;
	private JTextField textCountryName, textCountryX, textCountryY,
			textContinentName, textContinentControlValue;
	private JTextField textMapName;
	// End of variables declaration//GEN-END:variables

	private int selectedContinent = -1;
	private int selectedCountry = -1;
	private int selectedNeighbouringCountry = -1;

	/**
	 * A constructor that initializes all the UI elements and sets up the
	 * initial values. It also initializes the listeners.
	 */
	public MapEditorView() {
		jScrollPane1 = new JScrollPane();
		jTable1 = new JTable();
		panel = new JPanel();
		labelContinent = new JLabel();
		labelContinentName = new JLabel();
		labelControlValue = new JLabel();
		textContinentName = new JTextField();
		textContinentControlValue = new JTextField();
		btnAddContinent = new JButton();
		btnUpdateContinent = new JButton();
		btnDeleteContinent = new JButton();
		jSeparator2 = new JSeparator();
		filler1 = new Box.Filler(new java.awt.Dimension(0, 4),
				new java.awt.Dimension(0, 4), new java.awt.Dimension(32767, 4));
		labelCountry = new JLabel();
		labelCountryName = new JLabel();
		textCountryName = new JTextField();
		labelCountryX = new JLabel();
		labelCountryContinent = new JLabel();
		textCountryX = new JTextField();
		labelCountryY = new JLabel();
		textCountryY = new JTextField();
		labelNeighbourCountry = new JLabel();
		comboNeighbourCountry = new JComboBox();
		comboContinents = new JComboBox();
		btnAddCountry = new JButton();
		btnUpdateCountry = new JButton();
		btnDeleteCountry = new JButton();
		jScrollPane4 = new JScrollPane();
		listContinents = new JList();
		jScrollPane5 = new JScrollPane();
		listCountries = new JList();
		jScrollPane6 = new JScrollPane();
		listNeighbourCountries = new JList();
		btnSaveMap = new JButton();
		btnExit = new JButton();
		labelAdjacentCountry = new JLabel();
		btnUpdateMap = new JButton();
		textMapName = new JTextField("Untitled Map");

		listModelContinents = new DefaultListModel<>();
		comboModelContinents = new DefaultComboBoxModel<>();
		listModelCountries = new DefaultListModel<>();
		comboModelNeighbourCountries = new DefaultComboBoxModel<>();
		listModelNeighbourCountries = new DefaultListModel<>();

		setValues();
		initContinentListeners();
		initCountryListeners();
	}

	/**
	 * Sets up the values in all the UI elements.
	 */
	@SuppressWarnings("unchecked")
	private void setValues() {
		labelContinent.setText("Continent:");
		labelContinentName.setText("Name");
		labelControlValue.setText("Control value");
		btnAddContinent.setText("Add");
		btnUpdateContinent.setText("Update");
		btnDeleteContinent.setText("Delete");

		labelCountry.setText("Country:");
		labelCountryName.setText("Name");
		labelCountryX.setText("Latitude (x)");
		labelCountryY.setText("Longitude (y)");
		labelCountryContinent.setText("Continent");
		labelNeighbourCountry.setText("Neighbour Country  :");

		comboNeighbourCountry.setModel(comboModelNeighbourCountries);
		comboContinents.setModel(comboModelContinents);

		btnAddCountry.setText("Add");
		btnUpdateCountry.setText("Update");
		btnDeleteCountry.setText("Delete");
		btnSaveMap.setText("Save Map");
		btnSaveMap.setActionCommand("Untitled Map");
		btnExit.setText("EXIT");
		labelAdjacentCountry.setText("Adjacent Country");
		btnUpdateMap.setText("Update Map");
		jTable1.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null },
						{ null, null, null, null } },
				new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));

		holder.clearContinents();
	}

	/**
	 * Sets up values from the existing data structure (mainly used for loading
	 * the map from file)
	 */
	@SuppressWarnings("unchecked")
	public void setUpValues() {
		listModelCountries.removeAllElements();
		comboModelNeighbourCountries.removeAllElements();

		for (Map.Entry<String, CountryData> countryDataEntry : holder
				.getCountries().entrySet()) {
			CountryData countryData = countryDataEntry.getValue();
			listModelCountries.addElement(countryData.getName());
			comboModelNeighbourCountries.addElement(countryData.getName());
		}

		listCountries.setModel(listModelCountries);
		comboNeighbourCountry.setModel(comboModelNeighbourCountries);

		listModelContinents.removeAllElements();
		comboModelContinents.removeAllElements();

		for (Map.Entry<String, ContinentData> continentDataEntry : holder
				.getContinents().entrySet()) {
			ContinentData data = continentDataEntry.getValue();
			listModelContinents.addElement(
					data.getControlValue() + " - " + data.getName());
			comboModelContinents.addElement(data.getName());
		}

		listContinents.setModel(listModelContinents);
		comboContinents.setModel(comboModelContinents);

		textMapName.setText(holder.mapData.mapFileName);
	}

	/**
	 * Initializes the UI components and displays the UI
	 */
	public void initComponents() {
		jScrollPane1.setViewportView(jTable1);
		jScrollPane4.setViewportView(listContinents);
		jScrollPane5.setViewportView(listCountries);
		jScrollPane6.setViewportView(listNeighbourCountries);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		GroupLayout groupLayout = new GroupLayout(panel);
		panel.setLayout(groupLayout);
		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout
								.createParallelGroup(
										GroupLayout.Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout
												.createParallelGroup(
														GroupLayout.Alignment.LEADING)
												.addGroup(groupLayout
														.createSequentialGroup()
														.addGap(13, 13, 13)
														.addComponent(
																labelContinentName,
																GroupLayout.PREFERRED_SIZE,
																86,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout
														.createSequentialGroup()
														.addGap(24, 24, 24)
														.addComponent(
																btnAddContinent)))
										.addGroup(groupLayout
												.createParallelGroup(
														GroupLayout.Alignment.LEADING)
												.addGroup(groupLayout
														.createSequentialGroup()
														.addPreferredGap(
																LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(
																textContinentName,
																GroupLayout.PREFERRED_SIZE,
																117,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout
														.createSequentialGroup()
														.addGap(16, 16, 16)
														.addComponent(
																btnUpdateContinent)
														.addGap(32, 32, 32)
														.addComponent(
																btnDeleteContinent))))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(100, 100, 100)
										.addComponent(labelContinent,
												GroupLayout.PREFERRED_SIZE, 135,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(labelControlValue,
												GroupLayout.PREFERRED_SIZE, 79,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(textContinentControlValue,
												GroupLayout.PREFERRED_SIZE, 117,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(172, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(30, 30, 30)
						.addComponent(labelContinent,
								GroupLayout.PREFERRED_SIZE, 37,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(groupLayout
								.createParallelGroup(
										GroupLayout.Alignment.BASELINE)
								.addComponent(labelContinentName,
										GroupLayout.PREFERRED_SIZE, 28,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textContinentName,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(
								LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(groupLayout
								.createParallelGroup(
										GroupLayout.Alignment.BASELINE)
								.addComponent(labelControlValue,
										GroupLayout.PREFERRED_SIZE, 31,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textContinentControlValue,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(27, 27, 27)
						.addGroup(groupLayout
								.createParallelGroup(
										GroupLayout.Alignment.BASELINE)
								.addComponent(btnAddContinent)
								.addComponent(btnUpdateContinent)
								.addComponent(btnDeleteContinent,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap()));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE,
								851, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addGroup(layout
								.createParallelGroup(
										GroupLayout.Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup()
										.addContainerGap()
										.addComponent(jScrollPane4,
												GroupLayout.PREFERRED_SIZE, 312,
												GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout
										.createParallelGroup(
												GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGap(193, 193, 193)
												.addGroup(layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING,
																false)
														.addGroup(layout
																.createSequentialGroup()
																.addComponent(
																		filler1,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(75, 75,
																		75)
																.addGroup(layout
																		.createParallelGroup(
																				GroupLayout.Alignment.LEADING,
																				false)
																		.addComponent(
																				labelCountryY,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				labelCountryX,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				labelCountryContinent,
																				GroupLayout.DEFAULT_SIZE,
																				53,
																				Short.MAX_VALUE)
																		.addComponent(
																				labelCountryName,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
																.addGap(52, 52,
																		52)
																.addGroup(layout
																		.createParallelGroup(
																				GroupLayout.Alignment.LEADING,
																				false)
																		.addComponent(
																				comboContinents,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				labelCountry,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				textCountryY)
																		.addComponent(
																				textCountryX,
																				GroupLayout.PREFERRED_SIZE,
																				127,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				textCountryName,
																				GroupLayout.PREFERRED_SIZE,
																				91,
																				GroupLayout.PREFERRED_SIZE)))
														.addGroup(layout
																.createSequentialGroup()
																.addGap(61, 61,
																		61)
																.addGroup(layout
																		.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																		.addGroup(
																				layout.createSequentialGroup()
																						.addComponent(
																								btnAddCountry)
																						.addGap(18,
																								18,
																								18)
																						.addComponent(
																								btnUpdateCountry)
																						.addGap(18,
																								18,
																								18)
																						.addComponent(
																								btnDeleteCountry))
																		.addGroup(
																				layout.createSequentialGroup()
																						.addComponent(
																								labelNeighbourCountry,
																								GroupLayout.PREFERRED_SIZE,
																								99,
																								GroupLayout.PREFERRED_SIZE)
																						.addGap(18,
																								18,
																								18)
																						.addComponent(
																								comboNeighbourCountry,
																								GroupLayout.PREFERRED_SIZE,
																								119,
																								GroupLayout.PREFERRED_SIZE))))))
										.addGroup(layout.createSequentialGroup()
												.addGap(10, 10, 10)
												.addComponent(jScrollPane5,
														GroupLayout.PREFERRED_SIZE,
														183,
														GroupLayout.PREFERRED_SIZE)
												.addGap(74, 74, 74)
												.addGroup(layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane6,
																GroupLayout.PREFERRED_SIZE,
																188,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																labelAdjacentCountry,
																GroupLayout.PREFERRED_SIZE,
																90,
																GroupLayout.PREFERRED_SIZE)))))
						.addGroup(layout.createSequentialGroup()
								.addGap(70, 70, 70)
								.addComponent(textMapName,
										GroupLayout.PREFERRED_SIZE, 167,
										GroupLayout.PREFERRED_SIZE)
								.addGap(39, 39, 39).addComponent(btnSaveMap,
										GroupLayout.PREFERRED_SIZE, 113,
										GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout
								.createSequentialGroup().addGap(25, 25, 25)
								.addComponent(labelCountry,
										GroupLayout.PREFERRED_SIZE, 23,
										GroupLayout.PREFERRED_SIZE)
								.addGap(3, 3, 3)
                            .addGroup(layout
										.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
										.addComponent(labelCountryContinent,
												GroupLayout.PREFERRED_SIZE, 22,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(comboContinents))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout
										.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
										.addComponent(labelCountryName,
												GroupLayout.PREFERRED_SIZE, 24,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(textCountryName,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(layout
										.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
										.addComponent(labelCountryX,
												GroupLayout.PREFERRED_SIZE, 22,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(textCountryX,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout
										.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
										.addComponent(labelCountryY,
												GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(textCountryY,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(8, 8, 8)
								.addGroup(layout
										.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
										.addComponent(comboNeighbourCountry,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(labelNeighbourCountry))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout
										.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
										.addComponent(btnAddCountry)
										.addComponent(btnUpdateCountry)
										.addComponent(btnDeleteCountry,
												GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
								57, Short.MAX_VALUE)
						.addComponent(labelAdjacentCountry)
						.addPreferredGap(
								LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout
								.createParallelGroup(
										GroupLayout.Alignment.LEADING, false)
								.addComponent(jScrollPane4,
										GroupLayout.DEFAULT_SIZE, 243,
										Short.MAX_VALUE)
								.addComponent(jScrollPane5)
								.addComponent(jScrollPane6))
						.addGap(108, 108, 108)
						.addGroup(layout
								.createParallelGroup(
										GroupLayout.Alignment.BASELINE)
								.addComponent(textMapName,
										GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSaveMap,
										GroupLayout.PREFERRED_SIZE, 51,
										GroupLayout.PREFERRED_SIZE))
						.addGap(711, 711, 711)
						.addComponent(filler1, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE,
								0, GroupLayout.PREFERRED_SIZE)
						.addGap(82, 82, 82)));
	}

	/**
	 * Initializes the listeners associated to country data. Adding a country, -
	 * It initializes the country object and pushes into - It adds only one
	 * neighbour at a time. Update a country - It updates the hashmap in the
	 * holder - And also it adds only one neighbour at a time. Delete a country
	 * - It deletes the country from hashmap in the holder - But if the
	 * neighbouring country is selected, it will be deleted instead.
	 */
	@SuppressWarnings("unchecked")
	private void initCountryListeners() {
		this.btnAddCountry.addActionListener((ActionEvent e) -> {
			String name = textCountryName.getText();
			String latX = textCountryX.getText();
			String longY = textCountryY.getText();
			String continent = comboModelContinents
					.getElementAt(comboContinents.getSelectedIndex());

			if (name.length() == 0)
				return;

			CountryData data = new CountryData(name, Double.parseDouble(latX),
					Double.parseDouble(longY), continent);

			if ((comboNeighbourCountry.getSelectedIndex() != -1)
					&& !comboModelNeighbourCountries
							.getElementAt(
									comboNeighbourCountry.getSelectedIndex())
							.equalsIgnoreCase("No country"))
				data.addNeighbour(comboModelNeighbourCountries.getElementAt(
						comboNeighbourCountry.getSelectedIndex()));

			if (holder.doesCountryExist(name))
				return;

			holder.putCountry(data);

			listModelCountries.addElement(name);
			listCountries.setModel(listModelCountries);

			textCountryName.setText("");
			textCountryX.setText("");
			textCountryY.setText("");

			listCountries.clearSelection();
			listNeighbourCountries.clearSelection();
			this.selectedCountry = -1;
			this.selectedNeighbouringCountry = -1;

			System.out.println(name + " has been added!");

			computeCountries();
		});

		this.btnUpdateCountry.addActionListener((ActionEvent e) -> {
			if (this.selectedCountry == -1)
				return;

			String name = textCountryName.getText();
			String latX = textCountryX.getText();
			String longY = textCountryY.getText();
			String continent = comboModelContinents
					.getElementAt(comboContinents.getSelectedIndex());

			if (name.length() == 0)
				return;

			String originalName = listModelCountries
					.getElementAt(listCountries.getSelectedIndex());
			CountryData data = holder.getCountry(originalName);
			data.setValues(name, Double.parseDouble(latX),
					Double.parseDouble(longY), continent);

			if ((comboNeighbourCountry.getSelectedIndex() != -1)
					&& !comboModelNeighbourCountries
							.getElementAt(
									comboNeighbourCountry.getSelectedIndex())
							.equalsIgnoreCase("No country"))
				data.addNeighbour(comboModelNeighbourCountries.getElementAt(
						comboNeighbourCountry.getSelectedIndex()));

			holder.putCountry(data);
			if (!originalName.equalsIgnoreCase(name)) {
				holder.removeCountry(originalName);
				listModelCountries.removeElementAt(this.selectedCountry);
				listModelCountries.addElement(name);

				listCountries.setModel(listModelCountries);
			}

			textCountryName.setText("");
			textCountryX.setText("");
			textCountryY.setText("");

			listModelNeighbourCountries.removeAllElements();
			listNeighbourCountries.setModel(listModelNeighbourCountries);

			listCountries.clearSelection();
			listNeighbourCountries.clearSelection();
			this.selectedCountry = -1;
			this.selectedNeighbouringCountry = -1;

			System.out.println(name + " has been updated!");

			computeCountries();
		});

		this.listCountries.addListSelectionListener((ListSelectionEvent e) -> {
			this.selectedCountry = listCountries.getSelectedIndex();

			if (this.selectedCountry == -1)
				return;

			String name = listModelCountries.getElementAt(this.selectedCountry);

			CountryData countryData = holder.getCountries().get(name);
			textCountryName.setText(countryData.getName());
			textCountryX.setText(String.valueOf(countryData.getLatitude()));
			textCountryY.setText(String.valueOf(countryData.getLongitude()));
			int continentIndex = comboModelContinents
					.getIndexOf(countryData.getContinent());
			if (continentIndex != -1)
				comboContinents.setSelectedIndex(continentIndex);

			listModelNeighbourCountries.removeAllElements();
			List<String> neighbours = countryData.getNeighbours();

			for (String neighbour : neighbours) {
				listModelNeighbourCountries.addElement(neighbour);
			}

			listNeighbourCountries.setModel(listModelNeighbourCountries);

			listNeighbourCountries.clearSelection();
			this.selectedNeighbouringCountry = -1;

			computeCountries();
		});

		this.btnDeleteCountry.addActionListener((ActionEvent e) -> {
			if (this.selectedCountry == -1)
				return;

			String name = listModelCountries.getElementAt(this.selectedCountry);
			CountryData data = holder.getCountry(name);

			for (Map.Entry<String, CountryData> countryDataEntry : holder
					.getCountries().entrySet()) {
				CountryData countryData = countryDataEntry.getValue();
				if (countryData.getName().equalsIgnoreCase(name))
					continue;

				for (String neighbour : countryData.getNeighbours()) {
					if (neighbour.equalsIgnoreCase(name)) {
						String message = name + " is already neighbour of "
								+ countryData.getName() + ".\nCan not delete "
								+ name;
						System.out.println(message);
						JOptionPane.showMessageDialog(new JFrame(), message,
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}

			if (this.selectedNeighbouringCountry != -1) {
				String neighbourName = listModelNeighbourCountries
						.getElementAt(this.selectedNeighbouringCountry);
				data.removeNeighbour(neighbourName);
				holder.putCountry(data);
			} else {
				holder.removeCountry(name);

				listModelCountries.removeElementAt(this.selectedCountry);
				listCountries.setModel(listModelCountries);
			}

			this.selectedCountry = -1;
			this.selectedNeighbouringCountry = -1;

			listModelNeighbourCountries.removeAllElements();
			listNeighbourCountries.setModel(listModelNeighbourCountries);

			listNeighbourCountries.clearSelection();
			listCountries.clearSelection();
		});

		this.listNeighbourCountries
				.addListSelectionListener((ListSelectionEvent e) -> {
					this.selectedNeighbouringCountry = listNeighbourCountries
							.getSelectedIndex();
				});
	}

	/**
	 * Compute the list of neighbouring country. It ignores the selected country
	 * and already added neighbours.
	 */
	@SuppressWarnings("unchecked")
	private void computeCountries() {
		comboModelNeighbourCountries.removeAllElements();
		String selectedCountryName = null;
		List<String> neighbours = new ArrayList<>();
		HashMap<String, CountryData> countries = this.holder.getCountries();

		if (this.selectedCountry != -1) {
			selectedCountryName = this.listModelCountries
					.getElementAt(this.selectedCountry);
			neighbours = holder.getCountry(selectedCountryName).getNeighbours();
		}

		comboModelNeighbourCountries.addElement("No country");

		for (Map.Entry<String, CountryData> country : countries.entrySet()) {
			CountryData countryData = country.getValue();
			if ((selectedCountryName != null) && selectedCountryName
					.equalsIgnoreCase(countryData.getName()))
				continue;
			if (neighbours.indexOf(countryData.getName()) != -1)
				continue;
			comboModelNeighbourCountries.addElement(countryData.getName());
		}

		this.selectedNeighbouringCountry = -1;
		listNeighbourCountries.clearSelection();

		comboNeighbourCountry.setModel(comboModelNeighbourCountries);
	}

	/**
	 * Initializes the listeners associated to continent data. * Adding a
	 * continent, * - It initializes the continent object and pushes into *
	 * Update a country * - It updates the hashmap in the holder * Delete a
	 * country * - It deletes the continent from hashmap in the holder
	 */
	@SuppressWarnings("unchecked")
	private void initContinentListeners() {
		this.btnAddContinent.addActionListener((ActionEvent e) -> {
			String name = textContinentName.getText();
			String controlValue = textContinentControlValue.getText();

			if ((name.length() == 0) && (controlValue.length() == 0))
				return;

			if (holder.doesContinentExist(name))
				return;

			holder.putContinent(
					new ContinentData(name, Integer.parseInt(controlValue)));

			listModelContinents.addElement(controlValue + " - " + name);
			comboModelContinents.addElement(name);

			listContinents.setModel(listModelContinents);
			comboContinents.setModel(comboModelContinents);

			textContinentName.setText("");
			textContinentControlValue.setText("");

			listContinents.clearSelection();
			this.selectedContinent = -1;

			System.out.println(name + " has been added!");
		});

		this.listContinents.addListSelectionListener((ListSelectionEvent e) -> {
			this.selectedContinent = listContinents.getSelectedIndex();

			if (this.selectedContinent == -1)
				return;

			String continent = listModelContinents
					.getElementAt(this.selectedContinent);
			String[] temp = continent.split("-");
			textContinentName.setText(temp[1].trim());
			textContinentControlValue.setText(temp[0].trim());
		});

		this.btnDeleteContinent.addActionListener((ActionEvent e) -> {
			if (this.selectedContinent == -1)
				return;

			String continent = listModelContinents
					.getElementAt(this.selectedContinent);
			String name = continent.split("-")[1].trim();
			System.out.println(name);

			if (holder.getCountriesInContinent(name).size() != 0) {
				String message = "Can not delete continent. There are countries inside!";
				System.out.println(message);
				JOptionPane.showMessageDialog(new JFrame(), message, "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (continent.contains(name)) {
				holder.deleteContinent(name);
				listModelContinents.removeElementAt(this.selectedContinent);
				comboModelContinents.removeElement(name);
			}

			listContinents.setModel(listModelContinents);
			comboContinents.setModel(comboModelContinents);

			textContinentName.setText("");
			textContinentControlValue.setText("");

			listContinents.clearSelection();
			this.selectedContinent = -1;

			System.out.println(name + " has been removed!");
		});

		this.btnUpdateContinent.addActionListener((ActionEvent e) -> {
			if (this.selectedContinent == -1)
				return;

			String name = this.textContinentName.getText();
			String controlValue = this.textContinentControlValue.getText();

			holder.deleteContinent(this.listModelContinents
					.getElementAt(this.selectedContinent).split("-")[1].trim());
			holder.putContinent(
					new ContinentData(name, Integer.parseInt(controlValue)));
			listModelContinents.removeElementAt(this.selectedContinent);
			listModelContinents.addElement(controlValue + " - " + name);

			listContinents.setModel(listModelContinents);

			textContinentName.setText("");
			textContinentControlValue.setText("");

			listContinents.clearSelection();
			this.selectedContinent = -1;

			System.out.println(name + " has been updated!");
		});
	}

	/**
	 * Initialize listeners from the controller.
	 * 
	 * @param alSaveMap Listener for saveMap
	 */
	public void initPublicListeners(ActionListener alSaveMap) {
		this.btnSaveMap.addActionListener(alSaveMap);
		this.textMapName.getDocument()
				.addDocumentListener(new DocumentListener() {
					@Override
					public void insertUpdate(DocumentEvent e) {
						btnSaveMap.setActionCommand(textMapName.getText());
					}

					@Override
					public void removeUpdate(DocumentEvent e) {
						btnSaveMap.setActionCommand(textMapName.getText());
					}

					@Override
					public void changedUpdate(DocumentEvent e) {

					}
				});
	}

	/**
	 * Display the frame for the map editor.
	 */
	public void display() {
		this.setVisible(true);
		pack();
	}
}
