package Controller;

import Model.ContinentData;
import Model.CountryData;
import Model.MapData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartupController {
    private File mapFile, bmpFile;
    private MapData mapData = new MapData();
    private List<ContinentData> continentDataList = new ArrayList<>();
    private List<CountryData> countryDataList = new ArrayList<>();

    StartupController(File mapFile, File bmpFile) {
        this.mapFile = mapFile;
        this.bmpFile= bmpFile;
    }

    void processFiles() {
        try {
            String existingSegment = "";
            Scanner mapScanner = new Scanner(this.mapFile);

            while (mapScanner.hasNextLine()) {
                String incoming = mapScanner.nextLine();
                if (incoming.length() == 0)
                    continue;
                if (incoming.startsWith("[")) {
                    // start a segment
                    existingSegment = incoming;
                    this.mapData.cleanUpMapData();
                    continue;
                }
                if (existingSegment.equalsIgnoreCase("[map]")) {
                    String[] contents = incoming.split("=");
                    this.addToMapData(contents[0], contents[1]);
                }
                if (existingSegment.equalsIgnoreCase("[continents]")) {
                    ContinentData data = this.addContinent(incoming);
                    this.continentDataList.add(data);
                }
                if (existingSegment.equalsIgnoreCase("[territories]")) {
                    CountryData data = this.addCountry(incoming);
                    this.countryDataList.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private CountryData addCountry(String incoming) {
        String content[] = incoming.split(",");
        CountryData data = new CountryData(content[0], Integer.parseInt(content[1]), Integer.parseInt(content[2]), content[3]);
        for (int i = 4; i < content.length; i++) {
            data.addNeighbour(content[i]);
        }
        return data;
    }

    private ContinentData addContinent(String incoming) {
        String[] contents = incoming.split("=");
        return new ContinentData(contents[0], Integer.parseInt(contents[1]));
    }

    private void addToMapData(String field, String value) {
        if (field.equalsIgnoreCase("image"))
            this.mapData.imageFileName = value;
        if (field.equalsIgnoreCase("wrap"))
            this.mapData.wrap = value.equalsIgnoreCase("yes");
        if (field.equalsIgnoreCase("scroll"))
            this.mapData.scrollType = value;
        if (field.equalsIgnoreCase("author"))
            this.mapData.author = value;
        if (field.equalsIgnoreCase("warn"))
            this.mapData.warn = value.equalsIgnoreCase("yes");
    }
}
