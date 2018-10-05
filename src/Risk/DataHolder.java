package Risk;

import Model.ContinentData;
import Model.CountryData;
import Model.MapData;
import Model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * A singleton class to hold the entire data set throughout the application.
 */
public class DataHolder {
    private final static Object dataHolderLock = new Object();
    private static DataHolder dataHolder;

    private List<ContinentData> continentDataList = new ArrayList<>();
    private List<CountryData> countryDataList = new ArrayList<>();
    private List<Player> playerList = new ArrayList<>();
    public MapData mapData = new MapData();

    public static DataHolder getInstance() {
        synchronized (dataHolderLock) {
            if (dataHolder == null) {
                dataHolder = new DataHolder();
            }
        }
        return dataHolder;
    }

    public void addPlayer(Player data) {
        this.playerList.add(data);
    }

    public void addContinent(ContinentData data) {
        this.continentDataList.add(data);
    }

    public void addCountry(CountryData data) {
        this.countryDataList.add(data);
    }

    public List<ContinentData> getContinentDataList() {
        return continentDataList;
    }

    public List<CountryData> getCountryDataList() {
        return countryDataList;
    }

    public List<Player> getPlayerList() {
        return this.playerList;
    }
}
