package Game.Controller;

import Game.Risk.DataHolder;
import Game.Model.Cards;
import Game.Model.CountryData;
import Game.Model.Player;
import java.util.*;
import java.lang.Object;

public class Cardcontroller {
	private DataHolder holder = DataHolder.getInstance();
	private Player player;
    private List<Player> p = this.holder.getPlayerList();
    Cards card=new Cards();
	HashMap<CountryData,String> playerCards=new HashMap<CountryData,String>();
	int cardexchangecounter=0;
	int noOfCards=0;
    
	public HashMap<CountryData,String> getCards(int numberOfCountriesConqueredInAttackPhase, int playerTurn)
	{
	    
		player = p.get(playerTurn);
		HashMap<String, Integer> countriesConquered = player.getCountriesConquered();

		if (numberOfCountriesConqueredInAttackPhase > 0) 
		{

			if (holder.eliminatedplayerlist().size() > 0) // check for eliminated players in data holder
			{
				// player gets all cards owned by opponent player, check for cards associated
				// with each country
				
			//	for(each player in eliminated player list get GET their cards directly from data holder using their id )
            /*
				 current player gets all cards of opponent player
				 no of cards= no of cards + no of cards eliminated opponent player
                 */
			
					// display countries he owns along with their card types

				}
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
						playerCards.put(holder.getCountry(key), card.getCardAssociatedWithEachCountry().get(holder.getCountry(key)));
		
					}
					

				 }
				
				// display countries he owns along with their card types
				
				
			}

		}

		else {
			System.out.print("you don't get any new cards in this turn"); // display in a box in gui
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
	
	public void cardExchangeView() {
		
		
	}
	
	public void calculate(int playerTurn) {
		
		HashMap<CountryData,String> calculationOfArmies=showPlayerCards();
		
	}
	
	
	public void exchange(int playerTurn, int totalarmiesreceivedafterexchange) {
		
		
	}
	
}