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
    private File mapFile;
    private DataHolder holder = DataHolder.getInstance();

    StartupController(File mapFile) {
        this.mapFile = mapFile;
    }

    void processFiles() {
        try {
            String existingSegment = "";
            Scanner mapScanner = new Scanner(this.mapFile);
            this.holder.mapData.cleanUpMapData();

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

    void assignArmies() {
        List<Player> players = this.holder.getPlayerList();
        List<CountryData> countries = this.holder.getCountryDataList();

        Random random = new Random();

        int noOfArmiesToAssign = this.determineOfInitialArmy(players.size());

        for (int i = 0; i < noOfArmiesToAssign; i++) {
            for (Player player : players) {
                HashMap<String, Integer> countriesConquered = player.getCountriesConquered();
                Object[] entries =  countriesConquered.keySet().toArray();

                int randomCountryIndex = random.nextInt(entries.length);
                String randomCountry = (String) entries[randomCountryIndex];
                int noOfArmies = countriesConquered.get(randomCountry);

                player.updateCountry(randomCountry, ++noOfArmies);
            }
        }

        this.holder.updatePlayerList(players);

        System.out.println("Initial armies allocation:");
        for (Player player : this.holder.getPlayerList()) {
            System.out.println("\n"+player.getName() + ": ");

            for (Map.Entry<String, Integer> country : player.getCountriesConquered().entrySet()) {
                System.out.print(country.getKey() + " - " + country.getValue() + " | ");
            }
        }
    }

    private int determineOfInitialArmy(int noOfPlayers) {
        return 40 - ((noOfPlayers -2) * 5);
    }

    private CountryData addCountry(String incoming) {
        String content[] = incoming.split(",");
        CountryData data = new CountryData(content[0], Double.parseDouble(content[1]), Double.parseDouble(content[2]), content[3]);
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
}
