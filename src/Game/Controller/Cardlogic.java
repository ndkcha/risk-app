package Game.Controller;

import Game.Risk.DataHolder;
import Game.Risk.MapEditorDataHolder;
import Game.Model.Cards;
import Game.Model.CountryData;
import Game.Model.Player;
import java.util.*;


public class Cardlogic {
	private DataHolder holder = DataHolder.getInstance();
	private MapEditorDataHolder mapeditorholder=MapEditorDataHolder.getInstance();
	private Player player;
    private List<Player> p = this.holder.getPlayerList();
   // CountryData countryname=new CountryData();
	
    String[] cardKeys=(String[]) Cards.cardType.keySet().toArray();
    
	public void cardDistribution() { 
		
		 HashMap<String,String> cardAssociatedWithEachCountry=new HashMap<String,String>();  
	     //private List<CountryData> country; 
	     Random cardDistributionToCountries=new Random();
	     
	     
	     for(String countryname: mapeditorholder.getCountries().keySet())
	     {
	    	String randomstring=cardKeys[cardDistributionToCountries.nextInt(cardKeys.length)];
	    	cardAssociatedWithEachCountry.put(countryname,randomstring);
	    	System.out.println(countryname+" is assigned"+randomstring+" card");
	     }
	}
	
	
	

}