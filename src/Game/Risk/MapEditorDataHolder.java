package Game.Risk;

import Game.Model.ContinentData;
import Game.Model.CountryData;
import Game.Model.MapData;

import java.util.HashMap;

/**
 * A singleton class to hold the data throughout the map editor phase.
 */
public class MapEditorDataHolder {
    /** instance of the singleton class */
    private static MapEditorDataHolder dataHolder;

    /**
     * A map that contains the list of countries.
     * Key is the name of the country.
     * Value is the data object of the country.
     */
    private HashMap<String, CountryData> countries= new HashMap<>();
    /**
     * A map that contains the list of continents.
     * Key is the name of the country.
     * Value is the data object of the country.
     */
    private HashMap<String, ContinentData> continents = new HashMap<>();
    /** The meta data of the map */
    public MapData mapData = new MapData();

    /**
     * Get the instance of the singleton class.
     * It first checks if there is an existing instance.
     * If not, then it creates a new one.
     * @return the instance of the singleton object.
     */
    public static MapEditorDataHolder getInstance() {
        if (dataHolder == null)
            dataHolder = new MapEditorDataHolder();
        return dataHolder;
    }

    /**
     * Checks if the continent exists on the map
     * @param name name of the continent
     * @return boolean value of the result.
     */
    public boolean doesContinentExist(String name) {
        return this.continents.containsKey(name);
    }

    /** Clear all the continent records from the map */
    public void clearContinents() {
        this.continents.clear();
    }

    /**
     * Add a new continent into the map
     * @param continentData data object of the continent
     */
    public void putContinent(ContinentData continentData) {
        this.continents.put(continentData.getName(), continentData);
    }

    /**
     * Delete a continent from the map
     * @param name name of the continent to delete
     */
    public void deleteContinent(String name) {
        this.continents.remove(name);
    }

    /**
     * Checks if the country exists on the map
     * @param name name of the country
     * @return boolean value of the result.
     */
    public boolean doesCountryExist(String name) {
        return this.countries.containsKey(name);
    }

    /** clear the list of countries on the map */
    public void clearCountries() {
        this.countries.clear();
    }

    /**
     * Add a country into a map
     * @param countryData data object of the country
     */
    public void putCountry(CountryData countryData) {
        this.countries.put(countryData.getName(), countryData);
    }

    /**
     * Get a particular country
     * @param name name of the country
     * @return data object of the country
     */
    public CountryData getCountry(String name) {
        return this.countries.get(name);
    }

    /**
     * remove a particular country
     * @param name name of the country
     */
    public void removeCountry(String name) {
        this.countries.remove(name);
    }

    public HashMap<String, ContinentData> getContinents() {
        return continents;
    }

    public HashMap<String, CountryData> getCountries() {
        return countries;
    }
}
