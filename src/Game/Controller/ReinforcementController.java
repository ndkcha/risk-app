package Game.Controller;

import java.util.*;
import Game.Model.CountryData;
import Game.Model.ContinentData;
import Game.Risk.DataHolder;
import Game.Model.Player;

/**
 * This controller class is for the reinforcement phase
 *
 * @author r-naik
 * @version 1.2.0
 */
public class ReinforcementController {
	public ReinforcementController() {
	}

	private DataHolder holder = DataHolder.getInstance();

	/**
	 * This function calculates the armies a player avails in each reinforcement phase
	 * @param player Player Object.
	 * @return new armies The number of armies available for reinforcement phase.
	 */
	public int calculateReinforcementArmies(Player player) {

		// retrieving the player number whose turn is goin on
		int newarmies;

		// retrieving the continents conquered by the player
		HashMap<String, Integer> countriesConquered = player
				.getCountriesConquered();

		// get armies due to conquering whole continent
		int listSizeOfCountriesConquered;
		int continentAddedArmies = 0;
		for (ContinentData continentData : holder.getContinentDataList()) {// get data for every continent
			String continentName = continentData.getName();

			List<CountryData> countriesContinent = holder.countCountriesInContinent(continentName);// get COuntries of Continent
			int countrySize = countriesContinent.size();// size of the no of countries in continent

			listSizeOfCountriesConquered = 0;
			for (CountryData countryData : countriesContinent) {/// countires in continent loop
				Iterator itForCountriesConquered = countriesConquered.entrySet().iterator();// iterator for countries conqureeed by player
				while (itForCountriesConquered.hasNext()) {
					Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
					String countryName = (String) pair.getKey();
					if (countryData.getName().equalsIgnoreCase(countryName)) {
						listSizeOfCountriesConquered++;
					}
				}
			}
			if (listSizeOfCountriesConquered == countrySize) {
				continentAddedArmies += continentData.getControlValue();
			}
		}

		// number of countries owned divided by 3 and rounded down if the player owns more than 9 territores otherwise 3 territories
		if (countriesConquered.size() < 9) {
			newarmies = 3 + continentAddedArmies;
		} else {
			int armies = Math.floorDiv((countriesConquered.size()), 3);
			newarmies = armies + continentAddedArmies;
		}

		// holder.setPlayersArmiesList(playerTurn, newarmies);

		return newarmies;
	}

}
