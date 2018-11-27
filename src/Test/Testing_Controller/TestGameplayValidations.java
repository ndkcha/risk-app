package Test.Testing_Controller;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

import Game.Controller.FortificationController;
import Game.Controller.ReinforcementController;
import Game.Controller.StartupController;
import Game.Model.ContinentData;
import Game.Model.CountryData;
import Game.Model.Player;
import Game.Risk.DataHolder;

/**
 * Check if the countries are connected for fortification.
 * 
 * @author Jay
 */
public class TestGameplayValidations {
	private FortificationController fc;
	private ReinforcementController rc;
	public String transferingCountry;
	public String destinationCountry;
	public HashMap<String, Integer> countriesConquered = new HashMap<String, Integer>();;
	private DataHolder holder = DataHolder.getInstance();
	private StartupController sc = new StartupController(new File(""));

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
		holder.addCountry(country1);
		holder.addCountry(country2);
		holder.addCountry(country3);
		holder.countCountriesInContinent("Cockpit");
		fc = new FortificationController();
		this.transferingCountry = "Cockpit01";
		this.destinationCountry = "Cockpit02";
		this.countriesConquered.put("Cockpit01", 1);
		this.countriesConquered.put("Cockpit02", 2);
		this.countriesConquered.put("Cockpit03", 3);
		
		//Reinforcement
		Player player1 = new Player("abc", 1, "blue", 0);
		Player player2 = new Player("xyz", 0, "red", 1);
		holder.addPlayer(player1);
		holder.addPlayer(player2);

		rc = new ReinforcementController();
		this.countriesConquered.put("Cockpit01", 1);
		this.countriesConquered.put("Cockpit02", 2);
		this.countriesConquered.put("Cockpit03", 3);
	}

}
