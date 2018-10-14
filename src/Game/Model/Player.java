package Game.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This holds the player data and the runtime data collected for player
 * It holds the countries he conquered and it can be updated anytime.
 * It also holds list of continents the he has conquered.
 * @author ndkcha, Jay
 */
public class Player {
    private String name, color;
    private int type;
    /**
     * The countries conquered by the player.
     * Key is the name of the country.
     * Values is the number of armies inside that country.
     */
    private HashMap<String, Integer> countriesConquered;
    /** The continents conquered by the player */
    private List<String> continentsConquered;

    public Player(String name, int type, String color) {
        this.name = name;
        this.type = type;
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

    /**
     * This method will return the name of the player.
     * @return name THe name of the player
     */
    public String getName() {
        return name;
    }
    
    /**
     * This method will return the type of the player.
     * 0 = HUMAN and 1 = Computer
     * 
     * @return type THe name of the player
     */
    public int getType() {
        return type;
    }

    /**
     * This method will return the color of the player.
     * @return color THe color of the player
     */
    public String getColor() {
        return color;
    }

    /**
     * This method is to get the countriesConquered by player
     * @return countriesConquered The list of countriesConquered by player
     */
    public HashMap<String, Integer> getCountriesConquered() {
        return countriesConquered;
    }

    /**
     * This method is to get the continentsConquered by player
     * @return continentsConquered The list of continentsConquered by player
     */
    public List<String> getContinentsConquered() {
        return continentsConquered;
    }

    /**
     * This method is to set the countriesConquered by player.
     */
    public void setCountriesConquered(HashMap<String, Integer> countriesConquered) {
        this.countriesConquered = countriesConquered;
    }
    
}
