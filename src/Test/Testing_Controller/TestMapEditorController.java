package Test.Testing_Controller;

import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

import Game.Controller.MapEditorController;
import Game.Model.ContinentData;
import Game.Model.CountryData;

import Game.Risk.MapEditorDataHolder;

/**
 * This class will test the map for No continent in countries, no neighbours and countries connection.
 * 
 * @author Jay
 */
public class TestMapEditorController {
	MapEditorController mec = new MapEditorController();
	public HashMap<String, Integer> countriesConquered = new HashMap<String, Integer>();;
	private MapEditorDataHolder holder = MapEditorDataHolder.getInstance();

	/**
	 * This method will initialize the dummy values to validate before every
	 * test.
	 */
	@Before
	public void beforeTest() {
		ContinentData continentData = new ContinentData("Cockpit", 5);
		ContinentData continentDatas = new ContinentData("Cockpit1", 3);
		CountryData country1 = new CountryData("Cockpit01", 658.0, 355.0,
				"Cockpit");
		CountryData country2 = new CountryData("Cockpit02", 558.0, 255.0,
				"Cockpit");
		CountryData country3 = new CountryData("Cockpit03", 758.0, 155.0,
				"Cockpit");

		CountryData country4 = new CountryData("Cockpit04", 658.0, 355.0,
				"Cockpit1");
		CountryData country5 = new CountryData("Cockpit05", 558.0, 255.0,
				"Cockpit1");

		holder.putCountry(country1);
		holder.putCountry(country2);
		holder.putCountry(country3);
		holder.putCountry(country4);
		holder.putCountry(country5);

		// Continent 1 data
		country1.addNeighbour("Cockpit02");
		country2.addNeighbour("Cockpit01");
		country3.addNeighbour("Cockpit01");
		country1.addNeighbour("Cockpit03");
		country2.addNeighbour("Cockpit03");
		country3.addNeighbour("Cockpit02");

		// Continent 2 data
		country4.addNeighbour("Cockpit05");
		country5.addNeighbour("Cockpit04");

		// Connect continents
		country3.addNeighbour("Cockpit04");
		country4.addNeighbour("Cockpit03");

		holder.putContinent(continentData);
		holder.putContinent(continentDatas);
	}

	/**
	 * This method will test if country is in continent.
	 */
	@Test
	public void testValidateNoContinent() {
		Boolean countryInCOntinent = mec.validateNoContinent();
		System.out.println(countryInCOntinent);
		assertFalse(countryInCOntinent);
		
	}

	/**
	 * This method will test if country has neighbours.
	 */
	@Test
	public void testValidateNoNeighbours() {
		Boolean countryInCOntinent = mec.validateNoNeighbours();
		System.out.println(countryInCOntinent);
		assertFalse(countryInCOntinent);
	}

	/**
	 * This method will test if continent has any country inside.
	 */
	@Test
	public void testValidateNoCountryInContinent() {
		Boolean countryInCOntinent = mec.validateNoCountryInContinent();
		System.out.println(countryInCOntinent);
		assertFalse(countryInCOntinent);
	}

	/**
	 * This method will test if country is linked with any other country.
	 */
	@Test
	public void testValidateGhostNeighboursNolink() {
		Boolean countryInCOntinent = mec.validateGhostNeighboursNolink();
		System.out.println(countryInCOntinent);
		assertFalse(countryInCOntinent);
	}
	
	/**
	 * Test to Check if the sub-connected graph is valid or not for each continents
	 */
	@Test
	public void TestIsErrorInSubConnectedGraph(){
		
		Boolean subgraph = mec.isErrorInSubConnectedGraph();
		assertFalse(subgraph);
	}
}
