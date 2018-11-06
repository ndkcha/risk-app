package Test.Testing_Controller;

import Game.Controller.CardController;
import Game.Model.Cards;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;
import java.util.*;

/**
 * testing for cards received by player
 * 
 * @author kunal ghai
 */
public class TestCardController {
	
       @Test
	   public void testgetCards(){
    	   
	         int numberOfCountriesConqueredInAttackPhase=5;

	         String[] eliminatedplayerlist={"p1","p2","p5"};

	 
	         HashMap<String,String> hm=new HashMap<String,String>();
	         hm.put("usa","u");
	         hm.put("canada","c");
	         hm.put("india","i");
	         hm.put("france","f");
	         hm.put("uk","u");
	         hm.put("greenland","g");
	         hm.put("australia","a");
	         hm.put("new zealand","nz");
	         hm.put("japan","j");
	         hm.put("china","c");

	 
	     		if (numberOfCountriesConqueredInAttackPhase > 0) 
	     		{
	     			updatedPlayerCards=new ArrayList<Cards>();
	     			//updatedPlayerCards=player.getPlayerCards();
	     			HashMap<String, Integer> countriesConquered = player.getCountriesConquered();
	     			
	     			if (holder.eliminatedplayerlist().size() > 0) // check for eliminated players in data holder
	     			{
	     				
	     				// player gets all cards owned by opponent player, check for cards associated
	     				// with each country
	     				
	     			//	for(each player in eliminated player list get GET their cards directly from data holder using their id )
	                 /*
	     				 current player gets all cards of opponent player
	     				 no of cards= no of cards + no of cards eliminated opponent player
	     				 check type of cards received
	     				 playercards.put(
	                      */
	     			
	     					// display countries he owns along with their card types

	     				player.setPlayerCards(updatedPlayerCards);
	     			 }

	     			else
	     			// player gets cards that are associated with countries he conquered
	     			{
	     				int i = 0;

	     				for (String key : countriesConquered.keySet()) {
	     					if (i < countriesConquered.size() - numberOfCountriesConqueredInAttackPhase) {
	     						i = i + 1;

	     					} else {
	     						
	                             noOfCards++;
	                             updatedPlayerCards.add(noOfCards,card.generateCardPile[cardGivenToPlayer.nextInt(card.generateCardPile().size())]);
	                             player.setPlayerCards(updatedPlayerCards);
	                             
	     		               
	     					}
	     					

	     				 }
	     				
	     				
	     				//update countries & cards of a player
	     				 player.setPlayerCards(updatedPlayerCards);
	     				
	     			}

	     		}

	     		else {
	     			System.out.print("no new cards in this turn"); 
	     		}
	        	 
				                                                                                                                         
			    	   
			      
	           
	     }
	}			
