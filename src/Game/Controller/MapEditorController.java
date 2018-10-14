package Game.Controller;

import Game.Model.ContinentData;
import Game.Model.CountryData;
import Game.Model.MapData;
import Game.Risk.MapEditorDataHolder;
import Game.View.MapEditorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Map;
import java.util.Scanner;

/**
 * The controller for the Map Editor view.
 * It handles the data flown inside the map editor view.
 * The controller can manage the new map and load the existing map.
 * @author ndkcha
 * @version 1.0.0
 */
public class MapEditorController {
    /** the data holder for the entire data set inside map editor */
    private MapEditorDataHolder holder = MapEditorDataHolder.getInstance();

    /** the user interface for map editor */
    private MapEditorView view;

    /**
     * The constructor that initializes controller with initial values.
     * It also subscribes to different event listeners that are later passed to the view.
     */
    public MapEditorController() {
        holder.mapData = new MapData();
        holder.mapData.author = "ndkcha";
        view = new MapEditorView();
        initActionListeners();
    }

    /** Initializes the viewport of Map Editor. and displays on the screen. */
    public void initAndDisplayView() {
        view.initComponents();
        view.display();
    }

    /**
     * The load existing map into the holder data structures.
     * It parses the map file followed by Conqueror standards.
     * @param mapFile input map file
     */
    public void loadExistingMap(File mapFile) {
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
                    holder.putContinent(data);
                }
                if (existingSegment.equalsIgnoreCase("[territories]")) {
                    CountryData data = this.addCountry(incoming);
                    holder.putCountry(data);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        view.setUpValues();
    }

    /**
     * Constructs the country data object in order to fill it inside the DataHolder hashmap
     * @param incoming collected string from the map file
     * @return Data object of the parsed string.
     */
    private CountryData addCountry(String incoming) {
        String content[] = incoming.split(",");
        CountryData data = new CountryData(content[0], Double.parseDouble(content[1]), Double.parseDouble(content[2]), content[3]);
        for (int i = 4; i < content.length; i++) {
            data.addNeighbour(content[i]);
        }
        return data;
    }

    /**
     * Constructs the continent data object in order to fill it inside the DataHolder hashmap
     * @param incoming collected string from the map file
     * @return Data object of the parsed string.
     */
    private ContinentData addContinent(String incoming) {
        String[] contents = incoming.split("=");
        return new ContinentData(contents[0], Integer.parseInt(contents[1]));
    }

    /**
     * Adds the map meta data into the DataHolder hasmap
     * @param field name of the field
     * @param value value of the corresponding.
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
     * Initialize the action listeners that are eventually passed on to the view.
     * SaveMap listener listens to the click event of the save button.
     * the saveMap listeners saves the map data from the editor into a file.
     */
    private void initActionListeners() {
        ActionListener alSaveMap = (ActionEvent e) -> {
            System.out.println(e.getActionCommand());
            try (FileWriter fileWriter = new FileWriter(e.getActionCommand() + ".map")) {
                BufferedWriter writer = new BufferedWriter(fileWriter);

                writer.write("[Map]\n");

                if (holder.mapData.author != null)
                    writer.write("author=" + holder.mapData.author + "\n");

                if (holder.mapData.imageFileName != null)
                    writer.write("image=" + holder.mapData.imageFileName + "\n");

                writer.write("wrap=" + (holder.mapData.wrap ? "yes" : "no") + "\n");

                if (holder.mapData.scrollType != null)
                    writer.write("scroll=" + holder.mapData.scrollType + "\n");

                writer.write("warn=" + (holder.mapData.warn ? "yes" : "no") + "\n");

                writer.write("\n");
                writer.write("[Continents]\n");

                for (Map.Entry<String, ContinentData> continentDataEntry : holder.getContinents().entrySet()) {
                    ContinentData data = continentDataEntry.getValue();
                    writer.write(data.getName() + "=" + data.getControlValue() + "\n");
                }

                writer.write("\n");
                writer.write("[Territories]\n");

                for (Map.Entry<String, CountryData> countryDataEntry : holder.getCountries().entrySet()) {
                    CountryData data = countryDataEntry.getValue();
                    String temp = data.getName() + "," + data.getLatitude() + "," + data.getLongitude()
                        + "," + data.getContinent();

                    for (String neighbour : data.getNeighbours()) {
                        temp = temp.concat("," + neighbour);
                    }

                    writer.write(temp + "\n");
                }

                writer.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        };

        this.view.initPublicListeners(alSaveMap);
    }
}
