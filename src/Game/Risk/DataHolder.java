package Game.Risk;

import Game.Model.ContinentData;
import Game.Model.CountryData;
import Game.Model.MapData;
import Game.Model.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A singleton class to hold the entire data set throughout the application.
 * 
 * @author Jay
 * @version 1.0.0
 */
public class DataHolder {
	/** instance of the singleton class */
	private static DataHolder dataHolder;

	/** List of continents on the map */
	private List<ContinentData> continentDataList = new ArrayList<>();
	/** List of countries on the map */
	private List<CountryData> countryDataList = new ArrayList<>();
	/** List of player in the game */
	private List<Player> playerList = new ArrayList<>();
	/** Meta data of the map */
	public MapData mapData = new MapData();
	/** Image file of the map */
	public File bmpFile;

	/**
	 * Get the instance of the singleton class. It first checks if there is an
	 * existing instance. If not, then it creates a new one.
	 * 
	 * @return the instance of the singleton object.
	 */
	public static DataHolder getInstance() {
		if (dataHolder == null)
			dataHolder = new DataHolder();
		return dataHolder;
	}

	/** 
	 * Deletes all the players from the game play. 
	 */
	public void clearPlayers() {
		this.playerList.clear();
	}

	/**
	 * Add a player into the game play
	 * 
	 * @param data Data object of the player
	 */
	public void addPlayer(Player data) {
		this.playerList.add(data);
	}

	/**
	 * Add a country into the game play
	 * 
	 * @param data Data object of the player
	 */
	public void addContinent(ContinentData data) {
		this.continentDataList.add(data);
	}

	/**
	 * Add a country into the game play
	 * 
	 * @param data Data object of the player
	 */
	public void addCountry(CountryData data) {
		this.countryDataList.add(data);
	}

	/**
	 * @return continentDataList Return the list of continents.
	 */
	public List<ContinentData> getContinentDataList() {
		return continentDataList;
	}

	/**
	 * @return countryDataList Return the country list.
	 */
	public List<CountryData> getCountryDataList() {
		return countryDataList;
	}

	/**
	 * @return The player list.
	 */
	public List<Player> getPlayerList() {
		return this.playerList;
	}

	/**
	 * Update the list of players in the game.
	 * 
	 * @param players List of players to update to.
	 */
	public void updatePlayerList(List<Player> players) {
		this.playerList = players;
	}

	/**
	 * Get the list of countries in the continent
	 * 
	 * @param continentName The name of the continent.
	 * @return countryDataList the list of countries in that continent.
	 */
	public List<CountryData> countCountriesInContinent(String continentName) {
		List<CountryData> countryDataList = new ArrayList<>();

		for (CountryData country : this.countryDataList) {
			if (country.getContinent().equalsIgnoreCase(continentName))
				countryDataList.add(country);
		}

		return countryDataList;
	}

}
