package Model;

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
    // name being key and noOfArmies being the value
    private HashMap<String, Integer> countriesConquered;
    private List<String> continentsConquered;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        this.countriesConquered = new HashMap<>();
        this.continentsConquered = new ArrayList<>();
    }

    public void initializeCountry(String name) {
        this.countriesConquered.put(name, 1);
    }

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
