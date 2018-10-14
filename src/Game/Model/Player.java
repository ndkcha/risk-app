package Game.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This holds the player data and the runtime data collected for player
 * It holds the countries he conquered and it can be updated anytime.
 * It also holds list of continents the he has conquered.
 * @author ndkcha
 */
public class Player {
    private String name, color;
    /**
     * The countries conquered by the player.
     * Key is the name of the country.
     * Values is the number of armies inside that country.
     */
    private HashMap<String, Integer> countriesConquered;
    /** The continents conquered by the player */
    private List<String> continentsConquered;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        this.countriesConquered = new HashMap<>();
        this.continentsConquered = new ArrayList<>();
    }

    /**
     * Assign a country to the players.
     * It will automatically award the player with one army inside that country.
     * @param name name of the country
     */
    public void initializeCountry(String name) {
        this.countriesConquered.put(name, 1);
    }

    /**
     * Update the number of armies assigned to that country
     * @param name name of the country
     * @param noOfArmies number of armies used for the country.
     */
    public void updateCountry(String name, int noOfArmies) {
        this.countriesConquered.put(name, noOfArmies);
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public HashMap<String, Integer> getCountriesConquered() {
        return countriesConquered;
    }

    public List<String> getContinentsConquered() {
        return continentsConquered;
    }

    public void setCountriesConquered(HashMap<String, Integer> countriesConquered) {
        this.countriesConquered = countriesConquered;
    }
    
}
