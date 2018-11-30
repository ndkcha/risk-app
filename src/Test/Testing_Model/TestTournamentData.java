package Test.Testing_Model;

import static org.junit.Assert.*;

import Game.Model.TournamentData;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jay, ndkcha
 */
public class TestTournamentData {
	private TournamentData tournamentData;

	/**
	 * Initialize the data before running all the cases.
	 * Add all the games to the data
	 */
	@Before
	public void initData() {
		tournamentData = new TournamentData();

		tournamentData.addGamePath("one/path", "image/path", "2");
		tournamentData.addGamePath("one/path", "image/path", "2");
	}

	/**
	 * Test if it actually added to the data structures
	 */
	@Test
	public void isAbleToAddMap() {
		assertEquals(tournamentData.getMapBuffer().size(), 2);
	}

	/**
	 * Change the game.
	 * Test if it actually changes the game.
	 * And when the map is out of game. Change the map
	 */
	@Test
	public void isAbleToChangeTheGame() {
		tournamentData.changeGame();

		assertEquals(tournamentData.currentMap, 0);
		assertEquals(tournamentData.currentGame, 0);

		tournamentData.changeGame();

		assertEquals(tournamentData.currentMap, 0);
		assertEquals(tournamentData.currentGame, 1);

		tournamentData.changeGame();

		assertEquals(tournamentData.currentMap, 1);
		assertEquals(tournamentData.currentGame, 0);
	}
}
