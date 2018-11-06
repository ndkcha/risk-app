package Game.Model;

import Game.Controller.ReinforcementController;

import java.util.*;

/**
 * This holds the player data and the runtime data collected for player It holds
 * the countries he conquered and it can be updated anytime. It also holds list
 * of continents the he has conquered.
 * 
 * @author ndkcha, Jay
 * @version 1.0.0
 */
public class Player extends Observable {
	private int noOfArmiesToAssign = 0;
	private String name, color;
	private int type;
	
	/**
	 * The countries conquered by the player. Key is the name of the country.
	 * Values is the number of armies inside that country.
	 */
	private HashMap<String, Integer> countriesConquered;
	
	/** The continents conquered by the player */
	private List<String> continentsConquered;

	/**
	 * Gets the number of armies left to assign
	 * @return number of armies
	 */
	public int getNoOfArmiesToAssign() {
		return noOfArmiesToAssign;
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

	/** when an army is assigned, it decrements the count from the total. */
	public void assignInitialArmies() {
		this.noOfArmiesToAssign--;
	}

	/**
	 * Assigns the maximum number of armies to assign
	 *
	 * @param noOfArmies number of armies to assign
	 */
	public void setMaxInitialArmies(int noOfArmies) {
		this.noOfArmiesToAssign = noOfArmies;
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
     * @param name name of then country
     * @return an integer that states the number of armies.
     */
    public int getArmiesInCountry(String name) {
        return this.countriesConquered.get(name);
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
	 * Refactoring 2: All phases in player model.
	 * Performs the reinforcement phase for the player.
	 * @param armiesToAllocate total armies to allocated to a country.
	 * @param country the name of the country to allocated armies to.
	 * @return message produced from the fortification phase
	 */
	public String reinforcementPhase(int armiesToAllocate, String country) {
		if (country == null) {
			Random random = new Random();
			Object countries[] = this.getCountriesConquered().keySet().toArray();
			int countryIndex = random.nextInt(countries.length);
			country = (String) countries[countryIndex];
		}

		int existingArmies = this.getCountriesConquered().get(country);
		existingArmies += armiesToAllocate;

		this.updateCountry(country, existingArmies);

		return name + " added " + armiesToAllocate + " armies to " + country;
	}
	
	/**
	 * Refactoring 2: All phases in player model.
	 * Attack Phase
	 */
	public void attackPhase() {
		
	}
	
	/**
	 * Refactoring 2: All phases in player model.
	 * Implementation of Fortification Phase.
	 * @return message produced from the fortification phase
	 */
	public String fortificationPhase(String sourceCountry, String targetCountry, int noOfArmies) {
		Random random = new Random();
		if (sourceCountry == null) {
			int iterations = 10;
			do {
				int pickCountry = random.nextInt(this.getCountriesConquered().size());

				if (pickCountry < 0)
					return name + " skipped the fortification phase!";

				sourceCountry = this.getNthCountry(pickCountry);
				if (this.getArmiesInCountry(sourceCountry) != 1) {
					sourceCountry = null;
					break;
				}

				iterations--;
			} while (iterations != 0);
		}

		if (sourceCountry == null)
			return name + " skipped the fortification phase!";

		if (targetCountry == null) {
			int pickCountry = random.nextInt(this.getCountriesConquered().size());

			if (pickCountry < 0)
				pickCountry++;

			targetCountry = this.getNthCountry(pickCountry);

			if (targetCountry.equalsIgnoreCase(sourceCountry))
				return name + " skipped the fortification phase!";
		}

		if (noOfArmies == -1) {
			noOfArmies = this.getArmiesInCountry(sourceCountry);
			noOfArmies = random.nextInt(noOfArmies - 1);
		}

		// the transfer the armies
		int armiesLeftInSource = this.getArmiesInCountry(sourceCountry) - noOfArmies;
		int armiesInTarget = this.getArmiesInCountry(targetCountry) + noOfArmies;

		this.updateCountry(sourceCountry, armiesLeftInSource);
		this.updateCountry(targetCountry, armiesInTarget);

		return name + " sent " + noOfArmies + " arm(ies) from " + sourceCountry + " to " + targetCountry;
	}

}
