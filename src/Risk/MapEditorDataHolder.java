package Risk;

import Model.ContinentData;
import Model.CountryData;
import Model.MapData;

import java.util.HashMap;

public class MapEditorDataHolder {
    private static MapEditorDataHolder dataHolder;

    private HashMap<String, CountryData> countries= new HashMap<>();
    private HashMap<String, ContinentData> continents = new HashMap<>();
    public MapData mapData = new MapData();

    public static MapEditorDataHolder getInstance() {
        if (dataHolder == null)
            dataHolder = new MapEditorDataHolder();
        return dataHolder;
    }

    public boolean doesContinentExist(String name) {
        return this.continents.containsKey(name);
    }

    public void clearContinents() {
        this.continents.clear();
    }

    public void putContinent(ContinentData continentData) {
        this.continents.put(continentData.getName(), continentData);
    }

    public void deleteContinent(String name) {
        this.continents.remove(name);
    }

    public boolean doesCountryExist(String name) {
        return this.countries.containsKey(name);
    }

    public void clearCountries() {
        this.continents.clear();
    }

    public void putCountry(CountryData countryData) {
        this.countries.put(countryData.getName(), countryData);
    }

    public CountryData getCountry(String name) {
        return this.countries.get(name);
    }

    public void removeCountry(String name) {
        this.countries.remove(name);
    }

    public void deleteCountry(String name) {
        this.countries.remove(name);
    }

    public HashMap<String, ContinentData> getContinents() {
        return continents;
    }

    public HashMap<String, CountryData> getCountries() {
        return countries;
    }
}
