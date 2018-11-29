/**
 * 
 */
package Test.Testing_Controller;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import Game.Controller.AttackController;
import Game.Controller.StartupController;
import Game.Model.ContinentData;
import Game.Model.CountryData;
import Game.Model.Player;
import Game.Model.RollDice;
import Game.Risk.DataHolder;

/**
 * The Test class for AttackController.
 * @author Jay
 *
 */
public class TestAttackController {

	Player testplayer1;
	Player testplayer2;
	
	public HashMap<String, Integer> countriesConquered = new HashMap<String, Integer>();
	private DataHolder holder = DataHolder.getInstance();
	public String transferingCountry;
	public String destinationCountry;
	AttackController controller = new AttackController();
	
	@Before
	public void testbefore() {
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
		
		testplayer1.updateCountry("Cockpit01",3);
		testplayer1.updateCountry("Cockpit02",1);
		testplayer2.updateCountry("Cockpit03",3);
		testplayer2.updateCountry("Cockpit04",1);
        testplayer2.updateCountry("Cockpit04",3);
        
	}
	
	/**
	 * This method will test to get neighbors of a country
	 */
	@Test
	public void testGetNeighbours() {
		
		List<String> result=controller.getNeighbours("Cockpit02");
		assertEquals("Cockpit01",result.get(0));
		holder.clearDataHolder();
	}
	
	/**
	 * This method will test neighbours of a country allowed for attack
	 */
	@Test
	public void testGetNeighboursForAttack() {
		
		List<String> result=controller.getNeighboursForAttack("Cockpit01");
		assertEquals("Cockpit04",result.get(0));
		holder.clearDataHolder();
	}
	
	/**
	 * This method will test number of dice allowed
	 */
	@Test
	public void testCalculateNoOfDiceAllowed() {
		
		int noOfDiceAllowed=controller.calculateNoOfDiceAllowed("Cockpit01");
	    assertEquals(2,noOfDiceAllowed);
	    holder.clearDataHolder();
	}
	
	/**
	 * This function tests if attack is successful or not
	 */
	@Test
	public void testAttackBetweenTwoCoutries(){
		
		Boolean attack = controller.attackBetweenTwoCoutries("Cockpit01", "Cockpit02", 3);
		assertTrue(attack);
		holder.clearDataHolder();
	}
	
	/**
	 * Test the armies in defending country.
	 */
	@Test
	public void testGetArmiesOfDefendingCountry(){
		
		int armies = controller.getArmiesOfDefendingCountry("Cockpit01");
		assertEquals(3,armies);
		holder.clearDataHolder();
	}

	/**
	 * This method will test Roll dice model that generate random number.
	 */
	@Test
	public void testrolldice() {

		RollDice dice = new RollDice();
		int testdice = dice.roll();
		
		Boolean dices = (testdice >= 1 && testdice <= 6);
		assertTrue(dices);
	}
}
