package Test.Testing_Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Game.Model.*;

/**
 * Test class for Player Cards full implementation
 * 
 * @author kunal ghai
 *
 */
public class TestPlayer_Cards {

	Player player1;
	Player player2;

	/**
	 * Method is called before each test case of this class is executed.
	 * 
	 */
	@Before
	public void beforeTest() {

		player1 = new Player("abc", 1, "blue");
		player2 = new Player("xyz", 0, "red");

	}

	/**
	 * Testing method haveInfantryCard
	 */
	@Test
	public void testHaveInfantryCard() {
		player1.addCard(CardData.CARD_TYPE_INFANTRY);
		assertTrue(player1.haveInfantryCard());
	}

	/**
	 * Testing method haveCavalryCard
	 */
	@Test
	public void testHaveCavalryCard() {
		player1.addCard(CardData.CARD_TYPE_CAVALRY);
		assertTrue(player1.haveCavalryCard());
	}

	/**
	 * Testing method haveArtilleryCard
	 */
	@Test
	public void testHaveArtilleryCard() {
		player1.addCard(CardData.CARD_TYPE_ARTILLERY);
		assertTrue(player1.haveArtilleryCard());
	}

	/**
	 * Testing method haveDistinctCards
	 */
	@Test
	public void testHaveDistinctCard() {
		player1.addCard(CardData.CARD_TYPE_ARTILLERY);
		player1.addCard(CardData.CARD_TYPE_CAVALRY);
		player1.addCard(CardData.CARD_TYPE_INFANTRY);
		assertTrue(player1.haveDistinctCards());
	}

	/**
	 * Testing method haveThreeArtilleryCards
	 */
	@Test
	public void testHaveThreeArtilleryCards() {
		player1.addCard(CardData.CARD_TYPE_ARTILLERY);
		player1.addCard(CardData.CARD_TYPE_ARTILLERY);
		player1.addCard(CardData.CARD_TYPE_ARTILLERY);
		assertTrue(player1.haveThreeArtilleryCards());
	}

	/**
	 * Testing method haveThreeCavalryCards
	 */
	@Test
	public void testHaveThreeCavalryCards() {
		player2.addCard(CardData.CARD_TYPE_CAVALRY);
		player2.addCard(CardData.CARD_TYPE_CAVALRY);
		player2.addCard(CardData.CARD_TYPE_CAVALRY);
		assertTrue(player2.haveThreeCavalryCards());
	}

	/**
	 * Testing method haveThreeInfantryCards
	 */
	@Test
	public void testHaveThreeInfantryCards() {
		player2.addCard(CardData.CARD_TYPE_INFANTRY);
		player2.addCard(CardData.CARD_TYPE_INFANTRY);
		player2.addCard(CardData.CARD_TYPE_INFANTRY);
		assertTrue(player2.haveThreeInfantryCards());
	}

	/**
	 * Testing method haveThreeSameTypeCards
	 */
	@Test
	public void testHaveThreeSameTypeCards() {
		player2.addCard(CardData.CARD_TYPE_INFANTRY);
		player2.addCard(CardData.CARD_TYPE_INFANTRY);
		player2.addCard(CardData.CARD_TYPE_INFANTRY);
		assertTrue(player2.haveThreeSameTypeCards());
	}

	/**
	 * Testing method removeDistinctCards
	 */
	@Test
	public void testRemoveDistinctCards() {
		player1.addCard(CardData.CARD_TYPE_ARTILLERY);
		player1.addCard(CardData.CARD_TYPE_INFANTRY);
		player1.addCard(CardData.CARD_TYPE_CAVALRY);
		player1.removeDistinctCards();
		assertEquals(0, player1.getCards().size());
	}

	/**
	 * Testing method removeSimilarThreeCards
	 */
	@Test
	public void testRemoveSimilarThreeCards() {
		player2.addCard(CardData.CARD_TYPE_ARTILLERY);
		player2.addCard(CardData.CARD_TYPE_ARTILLERY);
		player2.addCard(CardData.CARD_TYPE_ARTILLERY);
		player2.addCard(CardData.CARD_TYPE_CAVALRY);
		player2.removeSimilarThreeCards(CardData.CARD_TYPE_ARTILLERY);
		assertEquals(1, player2.getCards().size());
	}

}
