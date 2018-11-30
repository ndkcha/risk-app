package Test.Testing_Controller;

import Game.Controller.*;
import Game.Model.*;
import Game.Risk.DataHolder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Test Number of countries assign to players.
 * 
 * @author Ku_ghai, Jay
 */
public class TestStartupController {

	StartupController sc = new StartupController();

	private DataHolder holder = DataHolder.getInstance();
	List<Integer> countryIndexes = new ArrayList<>();
	int playersTurn = 0;
	Player testplayer1;
	Player testplayer2;

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

	}

	/**
	 * This will test the Initial armies assign to user on initial startup
	 * phase.
	 */
	@Test
	public void testDetermineOfInitialArmy() {
		for (int i = 2; i <= 6; i++) {
			assertEquals((40 - ((i - 2) * 5)), sc.determineOfInitialArmy(i));
		}
		holder.clearDataHolder();
	}
	
	/**
	 * This will test the intial countries assignments.
	 */
	@Test
	public void testAssignCountries() {

		List<CountryData> countries = this.holder.getCountryDataList();
		List<Player> p = this.holder.getPlayerList();
		List<Integer> countryIndexes = new ArrayList<>();
		int playersTurn = 0;
		Player[] players = new Player[p.size()];

		for (int i = 0; i < p.size(); i++)
			players[i] = p.get(i);

		for (int i : IntStream.rangeClosed(0, countries.size() - 1).toArray())
			countryIndexes.add(i);

		Collections.shuffle(countryIndexes);

		System.out.println("Total countries: " + countryIndexes.size());

		for (int i : countryIndexes) {
			if (playersTurn == players.length)
				playersTurn = 0;

			players[playersTurn].initializeCountry(countries.get(i).getName());

			playersTurn++;
		}

		for (Player player : this.holder.getPlayerList()) {
			System.out.println(player.getName() + " has "
					+ player.getCountriesConquered().size() + " countries");
		}

		this.holder.updatePlayerList(Arrays.asList(players));

		assertTrue(testplayer1.getCountriesConquered().size() > 0);
		assertTrue(testplayer2.getCountriesConquered().size() > 0);
		holder.clearDataHolder();
	}

	/**
	 * Test Each country has assigned the cards.
	 */
	@Test
	public void testAssignCardsToCountry(){
	
		sc.assignCardsToCountry(holder.getCountryDataList());
		int count = 0;
		List<CountryData> countries = holder.getCountryDataList();
		int totalCountries = countries.size();
		
		for (int i = 0; i < totalCountries; i++) {
			CountryData data = countries.get(i);
			if(data.getCardType() != null){
				count++;
			}
		}
		System.out.println("counter: " +count);
		assertEquals(8, count);
		holder.clearDataHolder();
	}
	
}
