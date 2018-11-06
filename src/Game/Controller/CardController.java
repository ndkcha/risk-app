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
			
			updatedPlayerCards=player.getPlayerCards();
			
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
                       // updatedPlayerCards.put(holder.getCountry(key), Cards.getCardAssociatedWithEachCountry().get(holder.getCountry(key)));
                      //  updatedPlayerCards.add(noOfCards,Cards.generateCardDeck[cardGivenToPlayer.nextInt(Cards.generateCardDeck().size())]);
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
		//HashMap<CountryData,String> displayCards=getCards();
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
			//for()
		}
		
		else if (noOfCards<=5){
			
		}
		
		else
			System.out.println("no cards for exchange");
	}
	
	public void calculate(int playerTurn) {
		
		//HashMap<CountryData,String> calculationOfArmies=showPlayerCards();
		
	}
	
	
	public void finalExchange(int playerTurn, int totalarmiesreceivedafterexchange) {
		
		player.setPlayerCards(updatedPlayerCards);
		
	}
	
	
	/**
	 * Removes the card from the list of cards.
	 * @param card card to be removed from list. 
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void removeCard(Cards card) {
		updatedPlayerCards.remove(card);
	}
	
	
	/**
	 * Checks if player have Infantry Card
	 * @return true if player have Infantry Card otherwise false
	 */
	public boolean haveInfantryCard(){
		for (Cards card: this.cards){
			if (card.getName().equals("Infantry")){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if player have Cavalry Card
	 * @return true if player have Cavalry Card otherwise false
	 */
	public boolean haveCavalryCard(){
		for (Cards card: this.cards){
			if (card.getName().equals("Cavalry")){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if player have Artillery Card
	 * @return true if player have Artillery Card otherwise false
	 */
	public boolean haveArtilleryCard(){
		for (Cards card: this.cards){
			if (card.getName().equals("Artillery")){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if player have Infantry, Artillery and Cavalry Cards
	 * @return true if player have Infantry, Artillery and Cavalry Cards otherwise false
	 */
	public boolean haveDistinctCards(){
		if (this.haveInfantryCard() && this.haveArtilleryCard() && this.haveCavalryCard()){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Checks if player have three Artillery cards
	 * @return true if player have three Artillery cards otherwise false
	 */
	public boolean haveThreeArtilleryCards(){
		int artillery = 0;
		for (Cards card :this.cards){
			if (card.getName().equals("Artillery")){
				artillery++;
			}
		}
		if(artillery == 3){
			return true;
		}
		else{
			return false;
		}
			
	}
	
	/**
	 * Checks if player have three Cavalry cards
	 * @return true if player have three Cavalry cards otherwise false
	 */
	public boolean haveThreeCavalryCards(){
		int cavalry = 0;
		for (Cards card :this.cards){
			if (card.getName().equals("Cavalry")){
				cavalry++;
			}
		}
		if(cavalry == 3){
			return true;
		}
		else{
			return false;
		}
			
	}
	
	/**
	 * Checks if player have three Infantry cards
	 * @return true if player have three Infantry Cards otherwise false
	 */
	public boolean haveThreeInfantryCards(){
		int infantry = 0;
		for (Cards card :this.cards){
			if (card.getName().equals("Infantry")){
				infantry++;
			}
		}
		if(infantry == 3){
			return true;
		}
		else{
			return false;
		}
			
	}
	
	/**
	 * Checks if player have either three Cavalry, Artillery or Infantry cards
	 * @return true if player have either three Cavalry, Artillery or Infantry cards otherwise false
	 */
	public boolean haveThreeSameTypeCards(){
		if(this.haveThreeCavalryCards() || this.haveThreeArtilleryCards() || this.haveThreeInfantryCards()){
			return true;
		}
		else{
			return false;
		}
	}
	

	/**
	 * 
	 * @return list of cards player has.
	 */
	public ArrayList<Cards> getCards(){
		return this.getCards();
	}
	
	/**
	 * Removes one Infantry, Artillery and Cavalry cards
	 */
	public void removeDistinctCards(){
		this.removeCard(this.getCard("Cavalry"));
		this.removeCard(this.getCard("Infantry"));
		this.removeCard(this.getCard("Artillery"));
		this.assignArmies(5*this.cardexchangecounter++);
	}
	
	/**
	 * Returns the card from player cardlist
	 * @param cardname name of the card
	 * @return card with cardname equals to parameter
	 */
	public Cards getCard(String cardname){
		for (Cards card : ){
			if ( card.getName().equals(cardname)){
				return card;
			}
		}
		return null;
	}
	
	/**
	 * Removes either of three Infantry or Artillery or Cavalry cards
	 */
	public void removeSimilarThreeCards(){
		if (this.haveThreeArtilleryCards()){
			this.removeCard(this.getCard("Artillery"));
			this.removeCard(this.getCard("Artillery"));
			this.removeCard(this.getCard("Artillery"));
		}
		else if (this.haveThreeCavalryCards()){
			this.removeCard(this.getCard("Cavalry"));
			this.removeCard(this.getCard("Cavalry"));
			this.removeCard(this.getCard("Cavalry"));
		}
		else if (this.haveThreeInfantryCards()){
			this.removeCard(this.getCard("Infantry"));
			this.removeCard(this.getCard("Infantry"));
			this.removeCard(this.getCard("Infantry"));
		}
		this.assignArmies(5*this.cardexchangecounter++);
		
	}


	private void assignArmies(int i) {
		
		
	}

	
}