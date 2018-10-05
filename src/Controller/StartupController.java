package Controller;

import Model.ContinentData;
import Model.CountryData;
import Model.Player;
import Risk.DataHolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.IntStream;

public class StartupController {
    private File mapFile, bmpFile;
    private DataHolder holder = DataHolder.getInstance();

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
                    this.holder.mapData.cleanUpMapData();
                    continue;
                }
                if (existingSegment.equalsIgnoreCase("[map]")) {
                    String[] contents = incoming.split("=");
                    this.addToMapData(contents[0], contents[1]);
                }
                if (existingSegment.equalsIgnoreCase("[continents]")) {
                    ContinentData data = this.addContinent(incoming);
                    this.holder.addContinent(data);
                }
                if (existingSegment.equalsIgnoreCase("[territories]")) {
                    CountryData data = this.addCountry(incoming);
                    this.holder.addCountry(data);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void assignCountries() {
        List<CountryData> countries = this.holder.getCountryDataList();
        List<Player> p= this.holder.getPlayerList();
        List<Integer> countryIndexes = new ArrayList<>();
        int playersTurn = 0;
        Player[] players = new Player[p.size()];

        for (int i = 0; i < p.size(); i++)
            players[i] = p.get(i);

        for (int i : IntStream.rangeClosed(0, countries.size() - 1).toArray())
            countryIndexes.add(i);

        Collections.shuffle(countryIndexes);

        System.out.println("Total countries: " + countryIndexes.size());

        for (int i : countryIndexes) {
            if (playersTurn == players.length)
                playersTurn = 0;

            players[playersTurn].initializeCountry(countries.get(i).getName());

            playersTurn++;
        }

        for (Player player : this.holder.getPlayerList()) {
            System.out.println(player.getName() + " : " + player.getCountriesConquered().size());
        }

        this.holder.updatePlayerList(Arrays.asList(players));
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
            this.holder.mapData.imageFileName = value;
        if (field.equalsIgnoreCase("wrap"))
            this.holder.mapData.wrap = value.equalsIgnoreCase("yes");
        if (field.equalsIgnoreCase("scroll"))
            this.holder.mapData.scrollType = value;
        if (field.equalsIgnoreCase("author"))
            this.holder.mapData.author = value;
        if (field.equalsIgnoreCase("warn"))
            this.holder.mapData.warn = value.equalsIgnoreCase("yes");
    }

    public File getBmpFile() {
        return this.bmpFile;
    }
}
