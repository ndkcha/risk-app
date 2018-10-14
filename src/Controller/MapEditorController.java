package Controller;

import Model.ContinentData;
import Model.CountryData;
import Risk.MapEditorDataHolder;
import View.MapEditotView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class MapEditorController {
    MapEditorDataHolder holder = MapEditorDataHolder.getInstance();

    private MapEditotView view;

    MapEditorController() {
        view = new MapEditotView();
        initActionListeners();
    }

    void initAndDisplayView() {
        view.initComponents();
        view.display();
    }

    private void initActionListeners() {
        ActionListener alSaveMap = (ActionEvent e) -> {
            System.out.println(e.getActionCommand());
            try (FileWriter fileWriter = new FileWriter(e.getActionCommand() + ".map")) {
                BufferedWriter writer = new BufferedWriter(fileWriter);

                writer.write("[Map]\n");
                writer.write("author=ndkcha\n");
                writer.write("\n");
                writer.write("[Continent]\n");

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
