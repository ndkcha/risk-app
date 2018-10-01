/**
 * 
 */
package Model;

import java.io.Serializable;

/**
 * This class is used to create cards according to the Risk rules.
 * The created cards can then be assigned to players, and they receive additional
 * army for exchanging cards.
 * 
 * @author Jay
 *
 */
public class Cards implements Serializable{

	/**
	 * Auto Generated Serial UID.
	 */
	private static final long serialVersionUID = 8059305780663252891L;
	
	String name,type;
    
    //The card's unique ID.
    private Integer cardId = null;
    
    //The card's player ID. Every card has only one owner.
    private Integer playerId = null;
    
    //The number of armies rewarded for exchanging cards.
    public Integer cardExchangeArmies = 15;
	
	/**
	 * 
	 */
	public Cards() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor to assign value to name and type of the card.
	 * 
	 * @param cardId
	 *    The ID for this card
	 * @param name 
	 *    The name of the card.
	 * @param type 
	 *    The type of the card.
	 */
	public Cards(Integer cardId, String name, String type) {
		this.cardId = cardId;
		this.name = name;
		this.type = type;
	}
    
	/**
	 * Function to get the name of the card.
	 * @return name of the card.
	 */
	String getName() {
		return this.name;
	}
	
	/**
	 * Function to get the type of the card.
	 * @return type of the card.
	 */
	String getType() {
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
    void setOwnerId(Integer id) {
        this.playerId = id;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
