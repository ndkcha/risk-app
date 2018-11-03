package Game.Controller;

import Game.Risk.DataHolder;
import Game.Model.Cards;
import Game.Model.CountryData;
import Game.Model.Player;
import java.util.*;


public class Cardcontroller {
	private DataHolder holder = DataHolder.getInstance();
	private Player player;
    private List<Player> p = this.holder.getPlayerList();
    Cards card;
	
	public void getCards(int numberOfCountriesConqueredInAttackPhase, int playerTurn)
	{
	    card=new Cards();
		player = p.get(playerTurn);
		HashMap<String, Integer> countriesConquered = player.getCountriesConquered();
		
		if(numberOfCountriesConqueredInAttackPhase>0)
		 {
		
			String[] conqueredCountriesKeys=(String[]) player.getCountriesConquered().keySet().toArray();
			//int length=conqueredCountriesKeys.length;
			
		    if(holder.eliminatedplayerlist().size()>0) // check for eliminated players in data holder 
		    {	
		    	//player gets all cards owned by opponent player, check for cards associated with those countries
		    	  Iterator itForCountriesConquered = countriesConquered.entrySet().iterator();
		    	  while (itForCountriesConquered.hasNext()) {
	                    Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
	                    String countryName = (String) pair.getKey();
	                    Iterator itCardsList = card.getCardAssociatedWithEachCountry();
	              
		    	  // display countries he owns along with their card types
		    	}                                                                                                                           
		                                                                                                                                         
		        
		     }
			
		     else
			    //player gets cards that are associated with countries he conquered
		     {
		    	 
		    	                                                                                                                      
			            
		       }
			
		 }
		
		else
			System.out.print("you don't get any new cards in this turn"); // display in a box in gui
		
	}

	
	public void calculate() {
		
		
		
	}
	
	
	public void exchange(int totalarmiesreceivedafterexchange) {
		
		
	}
	
}