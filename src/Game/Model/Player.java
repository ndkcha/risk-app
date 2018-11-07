package Game.Model;

import java.util.*;

/**
 * This holds the player data and the runtime data collected for player It holds
 * the countries he conquered and it can be updated anytime. It also holds list
 * of continents the he has conquered.
 * 
 * @author ndkcha, Jay
 * @version 1.0.0
 */
public class Player extends Observable {
	
	private String name, color;
	private int type;
	private ArrayList<Cards> cards;
        private int cardsUsedCount = 1;
	/**
	 * The countries conquered by the player. Key is the name of the country.
	 * Values is the number of armies inside that country.
	 */
	private HashMap<String, Integer> countriesConquered;
	
	/** The continents conquered by the player */
	private List<String> continentsConquered;

	/**
	 * This constructor set the player details.
	 * 
	 * @param name The name of player.
	 * @param type The type of player human or computer.
	 * @param color The color for player.
	 */
	public Player(String name, int type, String color) {
		this.name = name;
		this.type = type;
		this.color = color;
		this.countriesConquered = new HashMap<>();
		this.continentsConquered = new ArrayList<>();
	}

	/**
	 * Assign a country to the players. It will automatically award the player
	 * with one army inside that country.
	 * 
	 * @param name Name of the country
	 */
	public void initializeCountry(String name) {
		this.countriesConquered.put(name, 1);
	}

	/**
	 * Update the number of armies assigned to that country
	 * 
	 * @param name Name of the country
	 * @param noOfArmies Number of armies used for the country.
	 */
	public void updateCountry(String name, int noOfArmies) {
		this.countriesConquered.put(name, noOfArmies);
	}

    /**
     * Get the number of armies the player has in the country
     * @param name name of then country
     * @return an integer that states the number of armies.
     */
    public int getArmiesInCountry(String name) {
        return this.countriesConquered.get(name);
    }

    /**
     * This method will return the name of the player.
     * @return name THe name of the player
     */
    public String getName() {
        return name;
    }
    
    public void addCard(Cards card) {
		this.cards.add(card);
	}

	/**
	 * Removes the card from the list of cards.
	 * @param card card to be removed from list. 
	 */
	public void removeCard(Cards card) {
		this.cards.remove(card);
	}
    /**
     * This method will return the type of the player.
     * 0 = HUMAN and 1 = Computer
     * 
     * @return type THe name of the player
     */
    public int getType() {
        return type;
    }

	/**
	 * This method will return the color of the player.
	 * 
	 * @return color The color of the player
	 */
	public String getColor() {
		return color;
	}

	/**
	 * This method is to get the countriesConquered by player
	 * 
	 * @return countriesConquered The list of countriesConquered by player
	 */
	public HashMap<String, Integer> getCountriesConquered() {
		return countriesConquered;
	}

	/**
	 * Gets the nth country in the list of countries conquered
	 * @param n index of the country
	 * @return name of the country
	 */
	public String getNthCountry(int n) {
		List<String> countries = new ArrayList<>();

		for (Map.Entry<String, Integer> countryEntry : this.countriesConquered.entrySet()) {
			countries.add(countryEntry.getKey());
		}

		return countries.get(n);
	}

	/**
	 * This method is to get the continentsConquered by player
	 * 
	 * @return continentsConquered The list of continentsConquered by player
	 */
	public List<String> getContinentsConquered() {
		return continentsConquered;
	}

	/**
	 * This method is to set the countriesConquered by player.
	 * 
	 * @param countriesConquered The key value pair of countries Conquered.
	 */
	public void setCountriesConquered(
			HashMap<String, Integer> countriesConquered) {
		this.countriesConquered = countriesConquered;
	}
	
	/**
	 * Refactoring 2: All phases in player model.
	 * Reinforcement Phase
	 */
	public void reinforcementPhase() {
		
	}
	
	/**
	 * Refactoring 2: All phases in player model.
	 * Attack Phase
	 */
	public void attackPhase() {
		
	}
	
	/**
	 * Refactoring 2: All phases in player model.
	 * Fortification Phase
	 */
	public void fortificationPhase() {
		
	}

        
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
		return this.cards;
	}
	
	/**
	 * Removes one Infantry, Artillery and Cavalry cards
	 */
	public void removeDistinctCards(){
		this.removeCard(this.getCard("Cavalry"));
		this.removeCard(this.getCard("Infantry"));
		this.removeCard(this.getCard("Artillery"));
		this.assignArmies(5*this.cardsUsedCount++);
	}
	
	/**
	 * Returns the card from player cardlist
	 * @param cardname name of the card
	 * @return card with cardname equals to parameter
	 */
	public Cards getCard(String cardname){
		for (Cards card : this.cards){
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
		this.assignArmies(5*this.cardsUsedCount++);
		
	}

}
