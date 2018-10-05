package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * This holds the player data and the runtime data collected for player
 * It holds the countries he conquered and it can be updated anytime.
 * It also holds list of continents the he has conquered.
 * @author ndkcha
 */
public class Player {
    private String name, color;
    private List<PlayerCountry> countriesConquered;
    private List<String> continentsConquered;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        this.countriesConquered = new ArrayList<>();
        this.continentsConquered = new ArrayList<>();
    }

    public void conquerCountry(String name, int noOfArmies) {
        PlayerCountry country = new PlayerCountry(name, noOfArmies);
        this.countriesConquered.add(country);
    }

    public void updateCountry(String name, int noOfArmies) {
//        this.countriesConquered
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<PlayerCountry> getCountriesConquered() {
        return countriesConquered;
    }

    public List<String> getContinentsConquered() {
        return continentsConquered;
    }
}
