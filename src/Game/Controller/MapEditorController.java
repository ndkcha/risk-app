package Game.Controller;

import Game.Model.ContinentData;
import Game.Model.CountryData;
import Game.Model.MapData;
import Game.Risk.MapEditorDataHolder;
import Game.View.MapEditorView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The controller for the Map Editor view. It handles the data flown inside the
 * map editor view. The controller can manage the new map and load the existing
 * map.
 * 
 * @author ndkcha
 * @version 1.2.0
 */
public class MapEditorController {
	/** the data holder for the entire data set inside map editor */
	private MapEditorDataHolder holder = MapEditorDataHolder.getInstance();

	/** the user interface for map editor */
	private MapEditorView view;

	/** Error message to display */
	private String errorMessage = "";

	/**
	 * The constructor that initializes controller with initial values. It also
	 * subscribes to different event listeners that are later passed to the
	 * view.
	 */
	public MapEditorController() {
		holder.mapData = new MapData();
		holder.mapData.author = "ndkcha";
		view = new MapEditorView();
		initActionListeners();
	}

	/**
	 * Initializes the viewport of Map Editor. and displays on the screen.
	 */
	public void initAndDisplayView() {
		view.initComponents();
		view.display();
	}

	/**
	 * The load existing map into the holder data structures. It parses the map
	 * file followed by Conqueror standards.
	 * 
	 * @param mapFile
	 *            Input map file
	 * @return true If there are any errors
	 */
	public boolean loadExistingMap(File mapFile) {
		boolean invalidFormatError = false;
		this.errorMessage = "";
		try {
			String existingSegment = "";
			Scanner mapScanner = new Scanner(mapFile);
			holder.mapData.cleanUpMapData();
			holder.mapData.mapFileName = mapFile.getName().replace(".map", "");

			while (mapScanner.hasNextLine()) {
				String incoming = mapScanner.nextLine();
				if (incoming.length() == 0)
					continue;
				if (incoming.startsWith("[")) {
					// start a segment
					existingSegment = incoming;
					continue;
				}
				if (existingSegment.equalsIgnoreCase("[map]")) {
					String[] contents = incoming.split("=");
					this.addToMapData(contents[0], contents[1]);
				}
				if (existingSegment.equalsIgnoreCase("[continents]")) {
					ContinentData data = this.addContinent(incoming);
					if (data == null) {
						invalidFormatError = true;
						continue;
					}
					holder.putContinent(data);
				}
				if (existingSegment.equalsIgnoreCase("[territories]")) {
					CountryData data = this.addCountry(incoming);
					if (data == null) {
						invalidFormatError = true;
						continue;
					}
					holder.putCountry(data);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		view.setUpValues();

		boolean otherErrors = this.checkForErrors();

		if (otherErrors || invalidFormatError) {
			JOptionPane.showMessageDialog(new JFrame(), this.errorMessage,
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		return otherErrors || invalidFormatError;
	}

	/**
	 * Refactoring 1 This Method checks if country is part of any continent.
	 * 
	 * @return true If there are any errors.
	 */
	public boolean validateNoContinent() {
		boolean noContinent = false;

		for (Map.Entry<String, CountryData> countryDataEntry : holder
				.getCountries().entrySet()) {
			CountryData countryData = countryDataEntry.getValue();
			if (countryData.getContinent().length() == 0) {
				noContinent = true;
				this.errorMessage = this.errorMessage.concat("\n"
						+ countryData.getName() + " is part of no continents");
				System.out.println(
						countryData.getName() + " is part of no continents");
			}
		}

		return noContinent;
	}

	/**
	 * Refactoring 1 This Method checks if country has neighbours.
	 * 
	 * @return true If there are any errors.
	 */
	public boolean validateNoNeighbours() {
		boolean noNeighbours = false;

		for (Map.Entry<String, CountryData> countryDataEntry : holder
				.getCountries().entrySet()) {
			CountryData countryData = countryDataEntry.getValue();
			if (countryData.getNeighbours().size() == 0) {
				noNeighbours = true;
				this.errorMessage = this.errorMessage.concat(
						"\n" + countryData.getName() + " has no neighbour");
				System.out.println(countryData.getName() + " has no neighbour");
			}
		}

		return noNeighbours;
	}

	/**
	 * Refactoring 1 This Method checks if continent has country/countries
	 * inside.
	 * 
	 * @return true If there are any errors.
	 */
	public boolean validateNoCountryInContinent() {
		boolean noCountryInContinent = false;

		for (Map.Entry<String, ContinentData> continentDataEntry : holder
				.getContinents().entrySet()) {
			ContinentData data = continentDataEntry.getValue();
			boolean hasLink = false;

			List<CountryData> countries = holder
					.getCountriesInContinent(data.getName());
			if (countries.size() == 0) {
				noCountryInContinent = true;
				this.errorMessage = this.errorMessage.concat(
						"\n" + data.getName() + " has no country inside");
				System.out.println(data.getName() + " has no country inside");
			}
		}

		return noCountryInContinent;
	}

	/**
	 * Refactoring 1 This Method checks if the country does not exist but is a
	 * neighbour of any country. and has no link to other continents.
	 * 
	 * @return true If there are any errors.
	 */
	public boolean validateGhostNeighboursNolink() {
		boolean ghostNeighbours = false;
		boolean noLink = false;

		for (Map.Entry<String, ContinentData> continentDataEntry : holder
				.getContinents().entrySet()) {
			ContinentData data = continentDataEntry.getValue();
			boolean hasLink = false;

			List<CountryData> countries = holder
					.getCountriesInContinent(data.getName());

			for (CountryData country : countries) {
				for (String neighbour : country.getNeighbours()) {
					if (!holder.doesCountryExist(neighbour)) {
						ghostNeighbours = true;
						this.errorMessage = this.errorMessage
								.concat("\n" + neighbour
										+ " doesn't exist, but is a neighbour of "
										+ country.getName());
						System.out.println(neighbour
								+ " doesn't exist, but is a neighbour of "
								+ country.getName());
					} else {
						if (!holder.getCountry(neighbour).getContinent()
								.equalsIgnoreCase(data.getName()))
							hasLink = true;
					}
				}
			}

			if (!hasLink) {
				noLink = true;
				this.errorMessage = this.errorMessage
						.concat("\n" + data.getName()
								+ " has no link to any other continent");
				System.out.println(
						data.getName() + " has no link to any other continent");
			}
		}

		return ghostNeighbours || noLink;
	}

	/**
	 * Refactoring 1 It checks for possible errors.
	 * 
	 * @return true If there are any errors.
	 */
	public boolean checkForErrors() {
		boolean noNeighbours, noContinent, noCountryInContinent, ghostNeighbours, subConnectedGraph;

		noContinent = this.validateNoContinent();
		noNeighbours = this.validateNoNeighbours();
		noCountryInContinent = this.validateNoCountryInContinent();
		ghostNeighbours = this.validateGhostNeighboursNolink();
		subConnectedGraph = this.isErrorInSubConnectedGraph();

		return noNeighbours || noContinent || noCountryInContinent
				|| ghostNeighbours || subConnectedGraph;
	}

	private boolean isErrorInSubConnectedGraph() {
		for (Map.Entry<String, ContinentData> continentDataEntry : this.holder.getContinents().entrySet()) {
			boolean isSubConnectedGraph = true;

			List<CountryData> countries = this.holder.getCountriesInContinent(continentDataEntry.getKey());
			List<String> countryNames = this.countriesToName(countries);

			for (CountryData countryData : countries) {
				for (String neighbour : countryData.getNeighbours()) {
					if (countryNames.indexOf(neighbour) == -1) {
						isSubConnectedGraph = false;
						break;
					}
				}

				if (!isSubConnectedGraph)
					break;
			}

			if (!isSubConnectedGraph) {
				this.errorMessage = this.errorMessage.concat("\n" + continentDataEntry.getKey() +
					" is not a sub-connected graph");
				return true;
			}
		}

		return false;
	}

	private List<String> countriesToName(List<CountryData> countryDataList) {
		List<String> names = new ArrayList<>();
		for (CountryData data : countryDataList) {
			names.add(data.getName());
		}

		return names;
	}

	/**
	 * Constructs the country data object in order to fill it inside the
	 * DataHolder hashmap
	 * 
	 * @param incoming
	 *            Collected string from the map file
	 * @return Data Object of the parsed string.
	 */
	private CountryData addCountry(String incoming) {
		String content[] = incoming.split(",");
		if (content.length < 4) {
			this.errorMessage = this.errorMessage
					.concat("\n" + "Invalid format of the file");
			System.out.println("Invalid format of the file");
			return null;
		}
		CountryData data = new CountryData(content[0],
				Double.parseDouble(content[1]), Double.parseDouble(content[2]),
				content[3]);
		for (int i = 4; i < content.length; i++) {
			data.addNeighbour(content[i]);
		}
		return data;
	}

	/**
	 * Constructs the continent data object in order to fill it inside the
	 * DataHolder hashmap
	 * 
	 * @param incoming
	 *            Collected string from the map file
	 * @return Data Object of the parsed string.
	 */
	private ContinentData addContinent(String incoming) {
		String[] contents = incoming.split("=");
		if (contents.length != 2) {
			this.errorMessage = this.errorMessage
					.concat("\n" + "Invalid format of the file");
			System.out.println("Invalid format of the file");
		}
		return contents.length == 2
				? new ContinentData(contents[0], Integer.parseInt(contents[1]))
				: null;
	}

	/**
	 * Adds the map meta data into the DataHolder hasmap
	 * 
	 * @param field
	 *            Name of the field
	 * @param value
	 *            value of the corresponding.
	 */
	private void addToMapData(String field, String value) {
		if (field.equalsIgnoreCase("image"))
			holder.mapData.imageFileName = value;
		if (field.equalsIgnoreCase("wrap"))
			holder.mapData.wrap = value.equalsIgnoreCase("yes");
		if (field.equalsIgnoreCase("scroll"))
			holder.mapData.scrollType = value;
		if (field.equalsIgnoreCase("author"))
			holder.mapData.author = value;
		if (field.equalsIgnoreCase("warn"))
			holder.mapData.warn = value.equalsIgnoreCase("yes");
	}

	/**
	 * Initialize the action listeners that are eventually passed on to the
	 * view. SaveMap listener listens to the click event of the save button. the
	 * saveMap listeners saves the map data from the editor into a file.
	 */
	private void initActionListeners() {
		ActionListener alSaveMap = (ActionEvent e) -> {
			this.errorMessage = "";
			if (this.checkForErrors()) {
				JOptionPane.showMessageDialog(new JFrame(), this.errorMessage,
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			System.out.println(e.getActionCommand());
			try (FileWriter fileWriter = new FileWriter(
					e.getActionCommand() + ".map")) {
				BufferedWriter writer = new BufferedWriter(fileWriter);

				writer.write("[Map]\n");

				if (holder.mapData.author != null)
					writer.write("author=" + holder.mapData.author + "\n");

				String fileName = (holder.mapData.imageFileName != null)
						? holder.mapData.imageFileName : mapImageFileSelector();
				writer.write("image=" + fileName + "\n");

				writer.write(
						"wrap=" + (holder.mapData.wrap ? "yes" : "no") + "\n");

				if (holder.mapData.scrollType != null)
					writer.write("scroll=" + holder.mapData.scrollType + "\n");

				writer.write(
						"warn=" + (holder.mapData.warn ? "yes" : "no") + "\n");

				writer.write("\n");
				writer.write("[Continents]\n");

				for (Map.Entry<String, ContinentData> continentDataEntry : holder
						.getContinents().entrySet()) {
					ContinentData data = continentDataEntry.getValue();
					writer.write(data.getName() + "=" + data.getControlValue()
							+ "\n");
				}

				writer.write("\n");
				writer.write("[Territories]\n");

				for (Map.Entry<String, CountryData> countryDataEntry : holder
						.getCountries().entrySet()) {
					CountryData data = countryDataEntry.getValue();
					String temp = data.getName() + "," + data.getLatitude()
							+ "," + data.getLongitude() + ","
							+ data.getContinent();

					for (String neighbour : data.getNeighbours()) {
						temp = temp.concat("," + neighbour);
					}

					writer.write(temp + "\n");
				}

				writer.close();
				view.dispose();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		};

		this.view.initPublicListeners(alSaveMap);
	}

	/**
	 * Upload .map along with .bmp file to be used for the game.
	 * 
	 * @return the file descriptor.
	 */
	private String mapImageFileSelector() {
		System.out.println("BMP file selector opened");
		JFrame frame = new JFrame("Select BMP File");

		// Upload map file.
		// https://coderanch.com/t/466536/java/closing-jFileChooser-window
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("./files/map"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"BMP Files", "bmp");
		fileChooser.setFileFilter(filter);

		int returnValue = fileChooser.showOpenDialog(frame);
		// Get the path of the file.
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String map_path = selectedFile.getAbsolutePath();
			frame.dispose();
			if (map_path.substring(map_path.lastIndexOf("."))
					.equalsIgnoreCase(".bmp"))
				return selectedFile.getName();
		}
		return null;
	}
}
