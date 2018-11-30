package Test.Testing_Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Game.Model.ContinentData;
import Game.Model.CountryData;
import Game.Model.GameLogsData;
import Game.Model.PhaseData;
import Game.Model.Player;
import Game.Risk.DataHolder;
import Game.View.PhaseView;

public class TestSaveAndLoadGame {
	private DataHolder dataHolder = DataHolder.getInstance();

	HashMap<String, Integer> conqueredCountryList1 = new HashMap<String, Integer>();
	HashMap<String, Integer> conqueredCountryList2 = new HashMap<String, Integer>();

	Player player1;
	Player player2;

	@Before
	public void beforeTest() {
		File bmpFile = new File("001_I72_Ghtroc 720.bmp");

		player1 = new Player("abc", 0, "blue");
		player2 = new Player("xyz", 1, "red");

		dataHolder.addPlayer(player1);
		dataHolder.addPlayer(player2);

		ContinentData continentData = new ContinentData("Cockpit", 5);
		CountryData country1 = new CountryData("Cockpit01", 658.0, 355.0, "Cockpit");
		country1.addNeighbour("Cockpit02");
		country1.addNeighbour("Cockpit04");
		CountryData country2 = new CountryData("Cockpit02", 558.0, 255.0, "Cockpit");
		country2.addNeighbour("Cockpit01");
		CountryData country3 = new CountryData("Cockpit03", 758.0, 155.0, "Cockpit");
		country3.addNeighbour("Cockpit04");
		CountryData country4 = new CountryData("Cockpit04", 858.0, 455.0, "Cockpit");
		country4.addNeighbour("Cockpit03");
		country4.addNeighbour("Cockpit01");

		dataHolder.addCountry(country1);
		dataHolder.addCountry(country2);
		dataHolder.addCountry(country3);
		dataHolder.addCountry(country4);
		dataHolder.addContinent(continentData);

		this.conqueredCountryList1.put("Cockpit01", 3);
		this.conqueredCountryList1.put("Cockpit02", 2);
		this.conqueredCountryList2.put("Cockpit03", 3);
		this.conqueredCountryList2.put("Cockpit04", 1);

		player1.setCountriesConquered(conqueredCountryList1);
		dataHolder.setPlayersArmiesList(0, 1);
		player2.setCountriesConquered(conqueredCountryList2);
		dataHolder.setPlayersArmiesList(1, 1);
		dataHolder.updatePlayer(player1);
		dataHolder.changePhases();
		dataHolder.changePhases();
		player1.reinforcementPhase(2, "Cockpit02");
		dataHolder.changePhases();		
	}

	@Test
	public void testSaveAndLoadGame() {
		dataHolder.saveGameState("TestSaveGame");
		DataHolder loadGameDataHold = new DataHolder();
		File file = new File("TestSaveGame.ser");
		loadGameDataHold.loadSavedGame(file);
		assertEquals("Cockpit", dataHolder.getContinentDataList().get(0).getName());
		assertEquals(4, dataHolder.getCountryDataList().size());
		assertEquals(2, dataHolder.getPlayerList().size());
		assertEquals(0, dataHolder.getConqueredPlayerList().size());
		assertNotEquals("xyz", dataHolder.getActivePlayer().getName());
		assertEquals(2, dataHolder.getCurrentPhase());
	}
}
