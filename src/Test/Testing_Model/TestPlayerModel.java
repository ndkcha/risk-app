package Test.Testing_Model;

import Game.Model.CardData;
import Game.Model.ContinentData;
import Game.Model.CountryData;
import Game.Model.Player;
import Game.Risk.DataHolder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Test class for Player Cards
 *
 * @author ndkcha
 */
public class TestPlayerModel {
	DataHolder holder = DataHolder.getInstance();
    Player player, player2;
    Player testplayer1;
	Player testplayer2;

    /**
     * Method is called before each test case of this class is executed.
     *
     */
    @Before
    public void beforeTest() {
    	holder.addCountry(new CountryData("attacker", 2.2, 2.2, "Continent1"));
        player = new Player("abc", 1, "blue");
        player2 = new Player("a", 2, "red");
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
    
    /**
     * End of game. If the player has won
     */
    @Test
    public void testPlayerHasWon() {
    	holder.addCountry(new CountryData("Country1", 2.2, 2.2, "Continent1"));
    	holder.addCountry(new CountryData("Country2", 2.2, 2.2, "Continent1"));
    	holder.addCountry(new CountryData("Country3", 2.2, 2.2, "Continent1"));
    	holder.addCountry(new CountryData("Country4", 2.2, 2.2, "Continent1"));
    	holder.addCountry(new CountryData("Country5", 2.2, 2.2, "Continent1"));
    	holder.addCountry(new CountryData("Country6", 2.2, 2.2, "Continent1"));
    	holder.addCountry(new CountryData("Country7", 2.2, 2.2, "Continent1"));
    	holder.addCountry(new CountryData("Country8", 2.2, 2.2, "Continent1"));
    	holder.addCountry(new CountryData("Country9", 2.2, 2.2, "Continent1"));
        player.updateCountry("Country1", 4);
        player.updateCountry("Country2", 4);
        player.updateCountry("Country3", 4);
        player.updateCountry("Country4", 4);
        player.updateCountry("Country5", 4);
        player.updateCountry("Country6", 4);
        player.updateCountry("Country7", 4);
        player.updateCountry("Country7", 3);
        player.updateCountry("Country8", 3);
        player2.updateCountry("Country9", 3);
    	boolean player1Won = holder.hasPlayerWon(player);
    	boolean player2Won = holder.hasPlayerWon(player2);
    	
    	//assertEquals(player1Won, true);
    	assertEquals(player2Won, false);
    }
  
}
