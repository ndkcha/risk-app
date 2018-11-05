/**
 * 
 */
package Game.Controller;

import java.util.*;

import Game.Model.Cards;
import Game.Model.CountryData;
import Game.Model.Player;
import Game.Risk.DataHolder;

/**
 * This is card controller for the Card exchange.
 * 
 * @author Jay
 */
public class CardsController {

	private DataHolder holder = DataHolder.getInstance();
    private Player player;
    private Cards cardsData;
    private Random randomGenerator;

    private List<Player> p = this.holder.getPlayerList();
    private List<CountryData> countryDataList = holder.getCountryDataList();
    private ArrayList<String> cardTypes = new ArrayList<String>();
    private HashMap<String, String> countryCards = new HashMap<String,String>();
    
    public void createRandomCards(){
    	// Three type of cards 
    	cardTypes.add("infantry");
        cardTypes.add("cavalry");
        cardTypes.add("artillery");
        
        // Assign it to random countries
    	for (int i = 0; i < countryDataList.size(); i++) {
    		randomGenerator = new Random();
            int index = randomGenerator.nextInt(cardTypes.size());
            String item = cardTypes.get(index);
            String countryName = countryDataList.get(i).getName();
            countryCards.put(countryName, item);
        }
    	
    	// Add the newly generated cards to holder.
    	holder.addCardList(countryCards);
    	
    	// Log it on Console
    	System.out.println(countryCards);
    }
    
    /**
     * @param Country The country name conquered.
     */
    public void assignPlayerCards(String attacker, String country){
    	/**
    	 * Find card from the card pile associated with country and assign it to player
    	 */
    	String card = "card";
    	int noOfCards = noOfPlayerCard(attacker);
    	
    	// if he have less then 5 and defender does not have card
    	if(noOfCards < 5){
    		// Means defender does not have card so now add the card to attack pile
    		//holder.addPlayerCards(attacker, card);
    	}
    	
    }
    
    /**
     * Check the number of cards that a player have, as max is 5.
     * @param player The player to check the card for.
     * @return cards The number of cards that a player have.
     */
    public int noOfPlayerCard(String player){
    	int cards = 0;
    	
    	
    	
    	return cards;
    }
    
    /**
     * Calculate the number of armies that the player will get on card excahnge
     * @return armies Integer value for number of armies.
     */
    public int calculateExchangeArmies(){
    	int armies = 0;
    	
    	return armies;
    }

}
