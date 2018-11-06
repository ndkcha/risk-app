package Game.Model;

import java.util.*;

/**
 * This holds the player data and the runtime data collected for player It holds
 * the countries he conquered and it can be updated anytime. It also holds list
 * of continents the he has conquered.
 *
 * @author ndkcha, Jay, r-naik
 * @version 1.0.0
 */
public class Player extends Observable  {

    private String name, color;
    private int type;

    /**
     * The countries conquered by the player. Key is the name of the country.
     * Values is the number of armies inside that country.
     */
    private HashMap<String, Integer> countriesConquered;

    /**
     * The continents conquered by the player
     */
    private List<String> continentsConquered;
    
    /**
     * number of countries conquered by a player in current phase
     */
    private int countriesConqueredCurrentPhase;
    
    /**
     * The countries conquered in the current phase
     * @return number of countries
     */
    public int getCountriesConqueredCurrentPhase() {
        return countriesConqueredCurrentPhase;
    }
    
    /**
     * sets the value of number of countries conquered in the current phase
     * @param countriesConqueredCurrentPhase number of countries
     */
    public void setCountriesConqueredCurrentPhase(int countriesConqueredCurrentPhase) {
        this.countriesConqueredCurrentPhase = countriesConqueredCurrentPhase;
    }

    /**
     * This constructor set the player details.
     *
     * @param name The name of player.
     * @param type The type of player human or computer.
     * @param color The color for player.
     */
    public Player(String name, int type, String color) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.countriesConquered = new HashMap<>();
        this.continentsConquered = new ArrayList<>();
    }

    /**
     * Assign a country to the players. It will automatically award the player
     * with one army inside that country.
     *
     * @param name Name of the country
     */
    public void initializeCountry(String name) {
        this.countriesConquered.put(name, 1);
    }

    /**
     * Update the number of armies assigned to that country
     *
     * @param name Name of the country
     * @param noOfArmies Number of armies used for the country.
     */
    public void updateCountry(String name, int noOfArmies) {
        this.countriesConquered.put(name, noOfArmies);
    }

    /**
     * Get the number of armies the player has in the country
     *
     * @param name name of then country
     * @return an integer that states the number of armies.
     */
    public int getArmiesInCountry(String name) {
        return this.countriesConquered.get(name);
    }

    /**
     * This method will return the name of the player.
     *
     * @return name THe name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * This method will return the type of the player. 0 = HUMAN and 1 =
     * Computer
     *
     * @return type THe name of the player
     */
    public int getType() {
        return type;
    }

    /**
     * This method will return the color of the player.
     *
     * @return color The color of the player
     */
    public String getColor() {
        return color;
    }

    /**
     * This method is to get the countriesConquered by player
     *
     * @return countriesConquered The list of countriesConquered by player
     */
    public HashMap<String, Integer> getCountriesConquered() {
        return countriesConquered;
    }

    /**
     * Gets the nth country in the list of countries conquered
     *
     * @param n index of the country
     * @return name of the country
     */
    public String getNthCountry(int n) {
        List<String> countries = new ArrayList<>();

        for (Map.Entry<String, Integer> countryEntry : this.countriesConquered.entrySet()) {
            countries.add(countryEntry.getKey());
        }

        return countries.get(n);
    }

    /**
     * This method is to get the continentsConquered by player
     *
     * @return continentsConquered The list of continentsConquered by player
     */
    public List<String> getContinentsConquered() {
        return continentsConquered;
    }

    /**
     * This method is to set the countriesConquered by player.
     *
     * @param countriesConquered The key value pair of countries Conquered.
     */
    public void setCountriesConquered(
            HashMap<String, Integer> countriesConquered) {
        this.countriesConquered = countriesConquered;
    }

    /**
     * Refactoring 2: All phases in player model. Reinforcement Phase
     */
    public void reinforcementPhase() {

    }

    /**
     * Refactoring 2: All phases in player model. Attack Phase
     */
    public void attackPhase() {

    }

    /**
     * Refactoring 2: All phases in player model. Fortification Phase
     */
    public void fortificationPhase() {

    }

}
