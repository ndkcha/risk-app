package Game.Controller;

import Game.Risk.DataHolder;
import Game.Risk.MapEditorDataHolder;
import Game.Model.Cards;
import Game.Model.CountryData;
import Game.Model.Player;
import java.util.*;


public class Cardcontroller {
	private DataHolder holder = DataHolder.getInstance();
	private MapEditorDataHolder mapeditorholder=MapEditorDataHolder.getInstance();
	private Player player;
    private List<Player> p = this.holder.getPlayerList();
   
	
    String[] cardKeys=(String[]) Cards.cardType.keySet().toArray();
    
	public void cardDistribution() { 
		
		 HashMap<String,String> cardAssociatedWithEachCountry=new HashMap<String,String>();  
	    
	     Random cardDistributionToCountries=new Random();
	     
	     
	     for(String countryname: mapeditorholder.getCountries().keySet())
	     {
	    	String randomstring=cardKeys[cardDistributionToCountries.nextInt(cardKeys.length)];
	    	cardAssociatedWithEachCountry.put(countryname,randomstring);
	    	//System.out.println(countryname+" is assigned"+randomstring+" card");
	     }
	}
	
	
	public void getCards(int numberOfConqueredCountriesInAttackPhase, int playerTurn){
	   
		//check for player turn 
		
		player = p.get(playerTurn);
		
		if(numberOfConqueredCountriesInAttackPhase>0)
		{
		  //if(current player conquers last country owned by another player)
		  //    player gets all cards owned by opponent player, check for cards associated with those countries
			//   
			
		}
		
		else
			System.out.print("you don't get any new cards in this turn"); // display in a box in gui
		
	}

}