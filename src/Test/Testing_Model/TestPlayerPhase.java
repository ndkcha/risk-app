package Test.Testing_Model;

import static org.junit.Assert.*;
import java.io.File;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

import Game.Risk.DataHolder;
import Game.Controller.StartupController;
import Game.Model.ContinentData;
import Game.Model.CountryData;
import Game.Model.Player;

/**
 * This class tests reinforcement, attack & fortification phases
 * 
 * @author Kunal Ghai
 */

public class TestPlayerPhase {

	Player testplayer1;
	Player testplayer2;

	public HashMap<String, Integer> countriesConquered = new HashMap<String, Integer>();
	private DataHolder holder = DataHolder.getInstance();
	public String transferingCountry;
	public String destinationCountry;
	private StartupController sc = new StartupController(new File(""));

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

		this.transferingCountry = "Cockpit01";
		this.destinationCountry = "Cockpit02";
		this.countriesConquered.put("Cockpit01", 3);
		this.countriesConquered.put("Cockpit02", 2);
		this.countriesConquered.put("Cockpit03", 3);
		this.countriesConquered.put("Cockpit04", 1);

		testplayer1 = new Player("abc", 1, "blue");
		testplayer2 = new Player("xyz", 0, "red");

		holder.addPlayer(testplayer1);
		holder.addPlayer(testplayer2);

		testplayer1.updateCountry("Cockpit01", 3);
		testplayer2.updateCountry("Cockpit03", 3);
		testplayer2.updateCountry("Cockpit04", 1);
		testplayer2.updateCountry("Cockpit04", 3);
	}

	/**
	 * This will test the calculation of armies received in reinforcement phase.
	 */
	@Test
	public void testreinforcementPhase() {
		int previousnoOfarmies = testplayer1.getArmiesInCountry("Cockpit01");
		testplayer1.reinforcementPhase(3, "Cockpit01");
		int armiesnow = testplayer1.getArmiesInCountry("Cockpit01");
		assertEquals(previousnoOfarmies + 3, armiesnow);
	}

	@Test
	public void testattackPhase() {

	}

	/**
	 * This method will test if the countries are connected.
	 */
	@Test
	public void testfortificationPhase() {
		int previousnoOfarmies = testplayer2.getArmiesInCountry("Cockpit03");
		testplayer2.fortificationPhase("Cockpit03", "Cockpit04", 2);
		int armiesnow = testplayer2.getArmiesInCountry("Cockpit03");
		assertEquals(previousnoOfarmies, armiesnow + 2);
	}

	/**
	 * This will test the Initial armies assign to user on initial startup
	 * phase.
	 */
	@Test
	public void testdetermineOfInitialArmy() {
		for (int i = 2; i <= 6; i++) {
			assertEquals((40 - ((i - 2) * 5)), sc.determineOfInitialArmy(i));
		}
	}

}
