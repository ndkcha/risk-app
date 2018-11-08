package Test.Testing_Controller;

import Game.Controller.*;
import Game.Model.*;
import Game.Risk.DataHolder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.*;
import java.util.*;

public class Test_AssignArmies {

	StartupController sc=new StartupController();
	
	private DataHolder holder = DataHolder.getInstance();
	List<Integer> countryIndexes = new ArrayList<>();
	int playersTurn = 0;
	Player testplayer1;
	Player testplayer2;
	public HashMap<String, Integer> countriesConquered = new HashMap<String, Integer>();
	
	@Before
	public void testbefore() {
		
		ContinentData continentData = new ContinentData("Cockpit", 5);
		CountryData country1 = new CountryData("Cockpit01", 658.0, 355.0,
				"Cockpit");
		country1.addNeighbour("Cockpit02");
		CountryData country2 = new CountryData("Cockpit02", 558.0, 255.0,
				"Cockpit");
		CountryData country3 = new CountryData("Cockpit03", 758.0, 155.0,
				"Cockpit");
		country3.addNeighbour("Cockpit04");
		CountryData country4 = new CountryData("Cockpit04", 858.0, 455.0,
				"Cockpit");
		holder.addCountry(country1);
		holder.addCountry(country2);
		holder.addCountry(country3);
		holder.addCountry(country4);
		holder.countCountriesInContinent("Cockpit");

	    testplayer1 = new Player("abc", 1, "blue");
	    testplayer2 = new Player("xyz", 0, "red");
		
		holder.addPlayer(testplayer1);
		holder.addPlayer(testplayer2);
		
		this.countriesConquered.put("Cockpit01",3);
		this.countriesConquered.put("Cockpit02", 2);
		
		testplayer1.updateCountry("Cockpit01", 0);
		testplayer1.updateCountry("Cockpit02", 0);
		testplayer2.updateCountry("Cockpit03", 0);
		testplayer2.updateCountry("Cockpit04", 0);

		
	}
	
	
	@Test
	public void testassignArmies() {
	
		List<CountryData> countries = this.holder.getCountryDataList();
		List<Player> players = this.holder.getPlayerList();
		Random random = new Random();
		int noOfArmiesToAssign = this.determineOfInitialArmy(players.size());

		for (int i = 0; i < noOfArmiesToAssign; i++) {
			for (Player player : players) {
				HashMap<String, Integer> countriesConquered = player
						.getCountriesConquered();
				Object[] entries = countriesConquered.keySet().toArray();

				int randomCountryIndex = random.nextInt(entries.length);
				System.out.println(randomCountryIndex + "  ");
				String randomCountry = (String) entries[randomCountryIndex];
				int noOfArmies = countriesConquered.get(randomCountry);

				player.updateCountry(randomCountry, ++noOfArmies);
			}
		}

		this.holder.updatePlayerList(players);

		System.out.println("Initial armies allocation:");
		for (Player player : this.holder.getPlayerList()) {
			System.out.println("\n" + player.getName() + ": ");

			for (Map.Entry<String, Integer> country : player
					.getCountriesConquered().entrySet()) {
				System.out.print(
						country.getKey() + " - " + country.getValue() + " | ");
			}
		}
		
	int result=testplayer1.getTotalPlayerArmies();
	
	assertEquals(40, result);
 
	}	

	public int determineOfInitialArmy(int noOfPlayers) {
		return 40 - ((noOfPlayers - 2) * 5);
	}

	 
}
