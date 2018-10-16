/**
 * 
 */
package Test.Testing_Controller;

import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

import Game.Controller.FortificationController;
import Game.Model.CountryData;
import Game.Risk.DataHolder;

/**
 * Check if the countries are connected for fortification.
 * 
 * @author Jay
 */
public class TestFortificationCountryConnected {
	private FortificationController fc;
	public String transferingCountry;
	public String destinationCountry;
	public HashMap<String, Integer> countriesConquered = new HashMap<String, Integer>();;
	private DataHolder holder = DataHolder.getInstance();
	
	@Before public void beforeTest(){
		CountryData country1 = new CountryData("Cockpit01",658.0,355.0,"Cockpit");
		country1.addNeighbour("Cockpit02");
		CountryData country2 = new CountryData("Cockpit02",558.0,255.0,"Cockpit");
		CountryData country3 = new CountryData("Cockpit03",758.0,155.0,"Cockpit");
		holder.addCountry(country1);
		holder.addCountry(country2);
		holder.addCountry(country3);
		
		fc = new FortificationController();
		this.transferingCountry = "Cockpit01";
		this.destinationCountry = "Cockpit02";
		this.countriesConquered.put("Cockpit01", 1);
		this.countriesConquered.put("Cockpit02", 2);
		this.countriesConquered.put("Cockpit03", 3);
	}
	
	@Test
	public void testCheckIfConnected() {
		Boolean b = fc.checkIfConnected(transferingCountry,destinationCountry,countriesConquered);
		System.out.println(b);
		assertTrue(b);
	}

}
