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
        holder.mapData.author = "ndkcha";
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
