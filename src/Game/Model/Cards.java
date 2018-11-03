package Game.Model;

import java.util.*;
import Game.Risk.DataHolder;
import java.util.Observable;

/**
 * This class is used to create cards according to the Risk rules. The created
 * cards can then be assigned to players, and they receive additional army for
 * exchanging cards.
 * 
 * @author Jay
 * @version 1.0.0
 */
public class Cards extends Observable{
	
  private DataHolder holder = DataHolder.getInstance();	
  private static HashMap<String,Integer> cardType;
  private HashMap<CountryData,String> cardAssociatedWithEachCountry;
  static {
	
		new HashMap<String, Integer>();
		cardType.put("Infantry", 1);
		cardType.put("Cavalry", 5);
		cardType.put("Artillery", 10);
	
	}	
   
   
   String[] cardKeys=(String[]) cardType.keySet().toArray();
   
   public void cardDistribution() { 
		
		 cardAssociatedWithEachCountry=new HashMap<CountryData,String>();  
	     Random cardDistributionToCountries=new Random();
	     
	     
	     for(CountryData countryname: holder.getCountryDataList())
	     {
	    	String randomstring=cardKeys[cardDistributionToCountries.nextInt(cardKeys.length)];
	    	cardAssociatedWithEachCountry.put(countryname,randomstring);
	    	//System.out.println(countryname+" is assigned"+randomstring+" card");
	     }
	}
	
   
   public HashMap<CountryData, String> getCardAssociatedWithEachCountry() {
		return cardAssociatedWithEachCountry;
	}

	public void setCardAssociatedWithEachCountry(HashMap<CountryData, String> cardAssociatedWithEachCountry) {
		this.cardAssociatedWithEachCountry = cardAssociatedWithEachCountry;
	}
   
   
	/**
	 * The card's player ID. Every card has only one owner.
	 */
	private Integer playerId = 0;

	/**
	 * The number of armies rewarded for exchanging cards.
	 */
	public Integer cardExchangeArmies = 15;

	
	/**
	 * Constructor to assign value to name and type of the card.
	 * 
	 * @param cardId The ID for this card
	 * @param name The name of the card.
	 * @param type The type of the card.
	 */
	/*
	public Cards(Integer cardId, String name, String type) {
		this.cardId = cardId;
		this.name = name;
		this.type = type;
	}
	*/

	/**
	 * Function to get the name of the card.
	 * 
	 * @return name of the card.
	 */
	
	/*
	 * public String getName() {
		return this.name;
	}
	*/

	/**
	 * Function to get the type of the card.
	 * 
	 * @return type of the card.
	 */
	
	/*
	public String getType() {
		return this.type;
	}
     */
    
	/**
	 * @return This method get the player id
	 */
	
	/*
	public Integer getOwnerId() {
		return this.playerId;
	}
     */
   
	/**
	 * This method set owner id
	 *
	 * @param id The new card player ID
	 */
	
	/*
	public void setOwnerId(Integer id) {
		this.playerId = id;
	}
	
   */
}
