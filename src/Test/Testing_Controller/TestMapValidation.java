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
 * This class will test the map
 * 
 * @author Jay
 */
public class TestMapValidation {
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
		CountryData country1 = new CountryData("Cockpit01", 658.0, 355.0,
				"Cockpit");
		country1.addNeighbour("Cockpit02");
		CountryData country2 = new CountryData("Cockpit02", 558.0, 255.0,
				"Cockpit");
		CountryData country3 = new CountryData("Cockpit03", 758.0, 155.0,
				"Cockpit");
		holder.putCountry(country1);
		holder.putCountry(country2);
		holder.putCountry(country3);
		
		holder.putContinent(continentData);
	}

	@Test
	public void testValidateNoContinent(){
		Boolean countryInCOntinent = mec.validateNoContinent();
		System.out.println(countryInCOntinent);
		assertFalse(countryInCOntinent);
	}
	
	@Test
	public void testValidateNoNeighbours(){
		Boolean countryInCOntinent = mec.validateNoNeighbours();
		System.out.println(countryInCOntinent);
		assertFalse(countryInCOntinent);
	}
	
	@Test
	public void testValidateNoCountryInContinent(){
		Boolean countryInCOntinent = mec.validateNoCountryInContinent();
		System.out.println(countryInCOntinent);
		assertFalse(countryInCOntinent);
	}
	
	@Test
	public void testValidateGhostNeighboursNolink(){
		Boolean countryInCOntinent = mec.validateGhostNeighboursNolink();
		System.out.println(countryInCOntinent);
		assertFalse(countryInCOntinent);
	}
}
