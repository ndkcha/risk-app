package Game.Controller;

import Game.Risk.DataHolder;
import Game.Model.Cards;
import Game.Model.CountryData;
import Game.Model.Player;
import java.util.*;
import java.lang.Object;

public class CardController {
	private DataHolder holder = DataHolder.getInstance();
	private Player player;
    private List<Player> p = this.holder.getPlayerList();
    Cards card=new Cards();
    HashMap<CountryData,String> updatedPlayerCards;
	ArrayList<Cards> updatedlistOfPlayerCards;
	int cardexchangecounter=0;
	int noOfCards=0;
	Random cardGivenToPlayer=new Random();
	
	public void getCards(int numberOfCountriesConqueredInAttackPhase, int playerTurn)
	{
	    
		player = p.get(playerTurn);
		

		if (numberOfCountriesConqueredInAttackPhase > 0) 
		{
			updatedPlayerCards=new HashMap<CountryData,String>();
			updatedlistOfPlayerCards=new ArrayList<Cards>();
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
                      //updatedPlayerCards.put(holder.getCountry(key), card.getCardAssociatedWithEachCountry().get(holder.getCountry(key)));
                        updatedPlayerCards.add(noOfCards,card.generateCardPile[cardGivenToPlayer.nextInt(card.generateCardPile().size())]);
                        player.setPlayerCards(updatedPlayerCards);
                        
		               
					}
					

				 }
				
				
				//update countries & cards of a player
				 player.setPlayerCards(updatedPlayerCards);
				
			}

		}

		else {
			System.out.print("no new cards in this turn"); // display in a box in gui
		}
		
	}

	
	public void displayPlayerCardsWithCountries(HashMap<CountryData,String> playerCards){
		// display country name with its card of a player
		HashMap<CountryData,String> displayCards=getCards();
	}
	
	
	/*
	  check for no of cards a player has:
	  if more than 5 then disable player from going to next phase & force him to click exchange card view 
	  & then exchange cards till he has <= 5 cards
	  else he can choose if he wants to skip exchange card view or not
	 */
	
	public void exchange(int playerTurn) {
		
		if(noOfCards>5)
		{
			for()
		}
		
		else if (noOfCards<=5){
			
		}
		
		else
			System.out.println("no cards for exchange");
	}
	
	public void calculate(int playerTurn) {
		
		HashMap<CountryData,String> calculationOfArmies=showPlayerCards();
		
	}
	
	
	public void finalExchange(int playerTurn, int totalarmiesreceivedafterexchange) {
		
		player.setPlayerCards(updatedPlayerCards);
		
	}
	
	
}