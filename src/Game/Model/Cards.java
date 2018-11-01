package Game.Model;

import java.util.HashMap;

/**
 * This class is used to create cards according to the Risk rules. The created
 * cards can then be assigned to players, and they receive additional army for
 * exchanging cards.
 * 
 * @author Jay
 * @version 1.0.0
 */
public class Cards {
	
	
private static HashMap<String,Integer> cardType;
	
	static {
		new HashMap<String, Integer>();
		cardType.put("Infantry", 1);
		cardType.put("Cavalry", 5);
		cardType.put("Artillery", 10);
	}
	


	private String name, type;

	/**
	 * The card's unique ID.
	 */
	private Integer cardId = null;

	/**
	 * The card's player ID. Every card has only one owner.
	 */
	private Integer playerId = null;

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
	public Cards(Integer cardId, String name, String type) {
		this.cardId = cardId;
		this.name = name;
		this.type = type;
	}

	/**
	 * Function to get the name of the card.
	 * 
	 * @return name of the card.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Function to get the type of the card.
	 * 
	 * @return type of the card.
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @return This method get the player id
	 */
	public Integer getOwnerId() {
		return this.playerId;
	}

	/**
	 * This method set owner id
	 *
	 * @param id The new card player ID
	 */
	public void setOwnerId(Integer id) {
		this.playerId = id;
	}

}
