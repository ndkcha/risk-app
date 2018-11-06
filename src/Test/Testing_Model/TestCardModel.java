package Test.Testing_Model;

import Game.Model.Cards;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * cardPile generated is tested 
 * Object of Cards class is initialized
 * 
 * @author kunal ghai
 */

public class TestCardModel {
	
	Cards card;
	
	@Before
	public void beforeTest() {
	  
		ArrayList<Cards> cardDeck = new ArrayList<Cards>();
		for(int i=0;i<20;i++) {
			cardDeck.add(new Cards("Artillery","Normal"));
			cardDeck.add(new Cards("Cavalry","Normal"));
			cardDeck.add(new Cards("Infantry","Normal"));
		}
		
		testgenerateCardPile(cardDeck);
		
		card=new Cards();
		
	 }
	

	@Test
	public void testgenerateCardPile(ArrayList<Cards> cardDeck){
		Assert.assertEquals(Cards.generateCardPile(), cardDeck);
	  }
   
}
