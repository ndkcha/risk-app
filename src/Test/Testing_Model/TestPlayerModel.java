package Test.Testing_Model;

import Game.Model.CardData;
import Game.Model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for Player Cards
 *
 * @author ndkcha
 */
public class TestPlayerModel {
    Player player;

    /**
     * Method is called before each test case of this class is executed.
     *
     */
    @Before
    public void beforeTest() {
        player = new Player("abc", 1, "blue");
        player.updateCountry("attacker", 4);
    }

    /**
     * Testing method moveArmiesAfterAttack
     */
    @Test
    public void testMoveArmiesAfterAttack() {

        player.setAttackerAndDefender("attacker", "defender");
        player.moveArmiesAfterAttack(3);

        assertEquals(player.getArmiesInCountry("attacker"),1);
        assertEquals(player.getArmiesInCountry("defender"), 3);
    }
}
