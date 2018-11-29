package Test.Testing_Controller;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Game.Controller.FortificationController;
import Game.Model.ContinentData;
import Game.Model.CountryData;
import Game.Risk.DataHolder;

/**
 * Check if the countries are connected for fortification.
 * 
 * @author Jay
 */
public class TestFortificationController {
	private FortificationController fc;
	public String transferingCountry;
	public String destinationCountry;
	public HashMap<String, Integer> countriesConquered = new HashMap<String, Integer>();;
	private DataHolder holder = DataHolder.getInstance();

	/**
	 * This method will initialize the dummy values to validate before every
	 * test.
	 */
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

		fc = new FortificationController();
		this.transferingCountry = "Cockpit01";
		this.destinationCountry = "Cockpit02";
		this.countriesConquered.put("Cockpit01", 1);
		this.countriesConquered.put("Cockpit02", 2);
		this.countriesConquered.put("Cockpit03", 3);
	}

	/**
	 * This method will test if the countries are connected.
	 */
	@Test
	public void testCheckIfConnected() {
		Boolean b = fc.checkIfConnected(transferingCountry, destinationCountry,
				countriesConquered);
		System.out.println(b);
		assertTrue(b);
		holder.clearDataHolder();
	}
	
	/**
	 * This method will test to get neighbors of a country
	 */
	@Test
	public void testGetNeighbours() {
		
		List<String> result = fc.getNeighbours("Cockpit02");
		assertEquals("Cockpit01",result.get(0));
		holder.clearDataHolder();
	}

}
