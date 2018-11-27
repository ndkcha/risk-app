package Test.Testing_Controller;

import Game.Controller.ReinforcementController;

import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

import Game.Model.ContinentData;
import Game.Model.CountryData;
import Game.Model.Player;
import Game.Risk.DataHolder;

/**
 * Check if armies are calculated correctly
 * 
 * @author r-naik
 */
public class TestReinforcementController {
	ReinforcementController rc;
	Player testplayer1;
	Player testplayer2;
	public HashMap<String, Integer> countriesConquered = new HashMap<String, Integer>();;
	private DataHolder holder = DataHolder.getInstance();

	@Before
	public void beforeTest() {
		ContinentData continentData = new ContinentData("Cockpit", 5);
		CountryData country1 = new CountryData("Cockpit01", 658.0, 355.0,
				"Cockpit");
		country1.addNeighbour("Cockpit02");
		country1.addNeighbour("Cockpit04");
		CountryData country2 = new CountryData("Cockpit02", 558.0, 255.0,
				"Cockpit");
		country2.addNeighbour("Cockpit01");
		CountryData country3 = new CountryData("Cockpit03", 758.0, 155.0,
				"Cockpit");
		country3.addNeighbour("Cockpit04");
		CountryData country4 = new CountryData("Cockpit04", 858.0, 455.0,
				"Cockpit");
		country4.addNeighbour("Cockpit03");
		country4.addNeighbour("Cockpit01");
		
		
		holder.addCountry(country1);
		holder.addCountry(country2);
		holder.addCountry(country3);
		holder.addCountry(country4);
		holder.countCountriesInContinent("Cockpit");

		this.countriesConquered.put("Cockpit01", 3);
		this.countriesConquered.put("Cockpit02", 2);
		this.countriesConquered.put("Cockpit03", 3);
		this.countriesConquered.put("Cockpit04", 1);

		testplayer1 = new Player("abc", 1, "blue");
		testplayer2 = new Player("xyz", 0, "red");

		holder.addPlayer(testplayer1);
		holder.addPlayer(testplayer2);
		
		testplayer1.updateCountry("Cockpit01",3);
		testplayer1.updateCountry("Cockpit02",1);
		testplayer2.updateCountry("Cockpit03",3);
		testplayer2.updateCountry("Cockpit04",1);
        testplayer2.updateCountry("Cockpit04",3);

		rc = new ReinforcementController();
		this.countriesConquered.put("Cockpit01", 1);
		this.countriesConquered.put("Cockpit02", 2);
		this.countriesConquered.put("Cockpit03", 3);
	}

	/**
	 * This will test the calculation of reinforcement armies.
	 * phase.
	 */
	@Test
	public void testCalculateReinforcementArmies() {

		int expected = 3;
		int numberOfArmies = rc.calculateReinforcementArmies(holder.getPlayer("abc"));
		System.out.println("number of armies: " + numberOfArmies);
		assertEquals(expected, numberOfArmies);
	}
	
	/**
	 * This method will test the calculation of armies received in
	 * reinforcement phase.
	 */
	@Test
	public void testreinforcementPhase() {
		int previousnoOfarmies=testplayer1.getArmiesInCountry("Cockpit01");
		testplayer1.reinforcementPhase(3, "Cockpit01");
		int armiesnow=testplayer1.getArmiesInCountry("Cockpit01");
		assertEquals(previousnoOfarmies+3,armiesnow);
	}
}
