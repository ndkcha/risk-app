/**
 * 
 */
package Model;

import java.util.ArrayList;

/**
 * This class represents a players id, name, country owned, armies details.
 * 
 * @author Jay
 *
 */
public class PlayerData {

	public static String[][] playerDataArray;
	public static String[] playerArmyData;
	public static String[] playerCountryData;
	public static String[] countries;
	private int armiesCount;
	private boolean turn = false;
	
	public PlayerData() {
		
	}
	/**
	 * Player full data including name, armies, country owned, conteinents owned, cards owned.
	 * 
	 * @param name
	 *    name of player.
	 */
	public PlayerData(String name) {
		
	}
	
	public void setGamePlayerData(String[][] anArrayOfStrings)
    {
		playerDataArray = anArrayOfStrings;
    }
 
    public static String[][] getGamePlayerData()
    {
        return playerDataArray;
    }
    
    /**
	 * Add new continent to the list of continents owned by player.
	 * @param continent 
	 *    continent to be added to list of continent owned by player.
	 */
	public void addContinent(ContinentData continent) {
		
	}
	
	/**
	 * remove continent from the list of continent owned by the player.
	 * @param continent 
	 *    continent to be removed from list of continents owned by player.
	 */
	public void removeContinent(ContinentData continent) {
		
	}
	
	/**
	 * Gets the list of continent owned by the player.
	 * @return list of continent names
	 */
	public String[] getAllContinentNames() {
		String[] continentNames = new String[1];
		
		return continentNames;
	}
	
    /**
	 * Add country to the list of countries owned by player.
	 * @param country 
	 *    country owned by player
	 */
	public void addCountry(CountryData country) {
		
	}
	
	/**
	 * Remove country to the list of countries owned by player.
	 * @param country 
	 *    country owned by player
	 */
	public void removeCountry(CountryData country) {
		
	}
	
	/**
	 * returns list of countries owned by the player.
	 * @return Array containing countries.
	 */
	public static String[] getCountries() {
		return countries ;	
	}
	
	/**
	 * Gets the list of countries owned by the player.
	 * @return list of country names
	 */
	public String[] getAllCountriesNames() {
		String[] names = new String[1];
		
		return names;
	}
	
	/**
	 * Add new card to list of cards owned by player.
	 * @param card 
	 *    new card to be added to list.
	 */
	public void addCard(String card) {
		
	}

	/**
	 * Removes the card from the list of cards owned by players.
	 * @param card 
	 *    card to be removed from list. 
	 */
	public void removeCard(String card) {
		
	}
	
	/**
	 * 
	 */
	public void setPlayerArmiesData(String playerName) {
		// TODO Auto-generated constructor stub	
	}
	
	/**
	 * 
	 */
	public static String[] getPlayerArmiesData() {
		// TODO Auto-generated constructor stub	
		return playerArmyData;
	}
	
	/**
	 * 
	 */
	public void setPlayerCountryData(String playerName) {
		// TODO Auto-generated constructor stub	
	}
	
	/**
	 * 
	 */
	public static String[] getPlayerCountryData() {
		// TODO Auto-generated constructor stub	
		return playerCountryData;
	}
	
	/**
	 * Calculates the armies to be alloted to the player at each turn.
	 * @return army count
	 */
	public int getArmies() {
		int armyCount = 0;
	
		return armyCount;
	}
	
	/**
	 *  Add armies to the player.
	 *  @param newCount armies to be assigned.
	 */
	public void addPlayerArmies(int count) {
		
	}
	
	/**
	 *  Remove Armies from player.
	 *  @param newCount armies to be removed.
	 */
	public void removePlayerArmies(int count) {
		
	}
	
	/**
	 *  sets player turn to true
	 */
	public void setTurnTrue() {
		this.turn = true;
	}
	
	/**
	 *  sets player turn to false
	 */
	public void setTurnFalse() {
		this.turn = false;
	}
	
	/**
	 * Gives the value of the turn of the player (True or False).
	 *  @return turn of player
	 */
	public boolean getTurn() {
		return this.turn;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
