package Game.Model;

import Game.Controller.AttackController;

import java.io.Serializable;
import java.util.*;

/**
 * This holds the player data and the runtime data collected for player It holds
 * the countries he conquered and it can be updated anytime. It also holds list
 * of continents the he has conquered.
 * 
 * @author ndkcha, Jay, r-naik
 * @version 1.2.0
 */
public class Player extends Observable implements Serializable {
    private int noOfArmiesToAssign = 0;
    private int noOfTurnsTaken = 0;
    private String name, color;
    private int type;
    private List<String> cards = new ArrayList<>();
    private int cardsUsedCount = 0;
    private boolean isCardUsed = false;

    private String attacker, defender;
    private int attackerArmies, defenderArmies;
    private boolean isAllOutMode = true;
    private int armiesToMove = 0;

    /**
     * The countries conquered by the player. Key is the name of the country.
     * Values is the number of armies inside that country.
     */
    private HashMap<String, Integer> countriesConquered;

    /**
     * Reset the content of the player data for the new game in tournament.
     */
    public void resetPlayer() {
        this.cards.clear();
        this.isCardUsed = false;
        this.cardsUsedCount = 0;
        this.noOfTurnsTaken = 0;
        this.countriesConquered.clear();
    }

    /**
     * Called when player has taken his turn.
     * It is tracked so that we can determine the end of the game.
     */
    public void takeTurn() {
        this.noOfTurnsTaken++;
    }

    /**
     * To determine if the player can take more turns
     * @param maxLimit maximum allowed turns
     * @return true if he can
     */
    public boolean canTakeMoreTurns(int maxLimit) {
        return (this.noOfTurnsTaken <= maxLimit);
    }

    /** notify change in player */
    public void notifyChangeInPlayer() {
        setChanged();
        notifyObservers("player:changed");
    }

    /**
     * Gets the number of armies to move after attack phase
     * @return armies to move
     */
    public int getArmiesToMove() {
        return armiesToMove;
    }

    /**
     * Sets the number of armies to move after attack phase
     * @param armiesToMove armies to move
     */
    public void setArmiesToMove(int armiesToMove) {
        this.armiesToMove = armiesToMove;
    }

    /**
     * Sets the all out mode
     * @param mode true if active
     */
    public void setAllOutMode(boolean mode) {
        this.isAllOutMode = mode;
    }

    /**
     * returns the type of mode
     * @return type
     */
    public boolean getAllOutMode() {
        return isAllOutMode;
    }

    public void logAttackerAndDefender() {
        System.out.println(attackerArmies + " - " + defenderArmies);
    }

    /**
     * Set the attacker country and the defender country for the attack phase
     * @param attacker name of the attacker country
     * @param defender name of the defender country
     */
    public void setAttackerAndDefender(String attacker, String defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    /**
     * Set the armies for attacker and defender both
     * @param attackerArmies number of armies
     */
    public void setAttackerArmies(int attackerArmies) {
        this.attackerArmies = attackerArmies;
    }

    /**
     * Set the armies for attacker and defender both
     * @param defenderArmies number of armies
     */
    public void setDefenderArmies(int defenderArmies) {
        this.defenderArmies = defenderArmies;
    }

    /** Reset the attacker and defender countries */
    public void resetAttackerAndDefender() {
        this.attacker = null;
        this.defender = null;
        this.attackerArmies = 0;
        this.defenderArmies = 0;
        this.armiesToMove = 0;
    }

    /**
     * Has the card been used in this turn?
     * @return true if it has been
     */
    public boolean isCardUsed() {
        return isCardUsed;
    }

    /**
     * Use the card
     */
    public void cardHasBeenUsed() {
        this.isCardUsed = true;
        this.cardsUsedCount++;
    }

    /**
     * Returns number of cards are used.
     * @return cards used
     */
    public int getCardsUsedCount() {
        return cardsUsedCount;
    }

    /**
     * Reset the used card flag
     */
    public void resetCardUsedFlag() {
        this.isCardUsed = false;
    }

    /**
     * Gets the number of armies left to assign
     *
     * @return number of armies
     */
    public int getNoOfArmiesToAssign() {
        return noOfArmiesToAssign;
    }

    /**
     * This constructor set the player details.
     *
     * @param name  The name of player.
     * @param type  The type of player human or computer.
     * @param color The color for player.
     */
    public Player(String name, int type, String color) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.countriesConquered = new HashMap<>();
    }

    /**
     * when an army is assigned, it decrements the count from the total.
     */
    public void assignInitialArmies() {
        this.noOfArmiesToAssign--;
    }

    /**
     * Assigns the maximum number of armies to assign
     *
     * @param noOfArmies number of armies to assign
     */
    public void setMaxInitialArmies(int noOfArmies) {
        this.noOfArmiesToAssign = noOfArmies;
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
     * @param name       Name of the country
     * @param noOfArmies Number of armies used for the country.
     */
    public void updateCountry(String name, int noOfArmies) {
        this.countriesConquered.put(name, noOfArmies);
    }

    /**
     * Get the number of armies the player has in the country
     *
     * @param name name of then country
     * @return an integer that states the number of armies.
     */
    public int getArmiesInCountry(String name) {
        return this.countriesConquered.get(name);
    }

    /**
     * This method will return the name of the player.
     *
     * @return name THe name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Collect the card for a player
     * @param card card that has been collected
     */
    public void addCard(String card) {
        this.cards.add(card);
    }

    /**
     * Removes the card from the list of cards.
     *
     * @param card card to be removed from list.
     */
    public void removeCard(String card) {
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
     *
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
     * Performs the reinforcement phase for the player.
     *
     * @param armiesToAllocate total armies to allocated to a country.
     * @param country          the name of the country to allocated armies to.
     * @return message produced from the fortification phase
     */
    public String reinforcementPhase(int armiesToAllocate, String country) {
        if (country == null) {
            Random random = new Random();
            Object countries[] = this.getCountriesConquered().keySet().toArray();
            int countryIndex = random.nextInt(countries.length);
            country = (String) countries[countryIndex];
        }

        int existingArmies = this.getCountriesConquered().get(country);
        existingArmies += armiesToAllocate;

        this.updateCountry(country, existingArmies);

        return name + " added " + armiesToAllocate + " armies to " + country;
    }

    /**
     * Refactoring 2: All phases in player model.
     * Attack Phase
     * @return noOfArmies to be moved (minimum)
     */
    public int attackPhase() {
        AttackController controller = new AttackController();
        int attackerArmy = this.isAllOutMode ? -1 : this.attackerArmies;
        int mode = this.isAllOutMode ? 0 : 1;
        int result=controller.attack(this.attacker, this.defender, mode, attackerArmy);
        return result;
    }

    /**
     * Moves armies after attack from attacker to defender
     * @param armiesToMove no of armies to move
     */
    public void moveArmiesAfterAttack(int armiesToMove) {
        int existing = this.getArmiesInCountry(attacker);
        int armiesLeftInAttacker = existing - armiesToMove;

        this.updateCountry(defender, armiesToMove);
        this.updateCountry(attacker, armiesLeftInAttacker);
    }

    /**
     * Refactoring 2: All phases in player model.
     * Implementation of Fortification Phase.
     *
     * @param sourceCountry Source country
     * @param targetCountry Target Country
     * @param noOfArmies No of armies.
     * 
     * @return message produced from the fortification phase
     */
    public String fortificationPhase(String sourceCountry, String targetCountry, int noOfArmies) {
        Random random = new Random();
        if (sourceCountry == null) {
            int iterations = 10;
            do {
                int pickCountry = random.nextInt(this.getCountriesConquered().size());

                if (pickCountry < 0)
                    return name + " skipped the fortification phase!";

                sourceCountry = this.getNthCountry(pickCountry);
                if (this.getArmiesInCountry(sourceCountry) != 1) {
                    sourceCountry = null;
                    break;
                }

                iterations--;
            } while (iterations != 0);
        }

        if (sourceCountry == null)
            return name + " skipped the fortification phase!";

        if (targetCountry == null) {
            int pickCountry = random.nextInt(this.getCountriesConquered().size());

            if (pickCountry < 0)
                pickCountry++;

            targetCountry = this.getNthCountry(pickCountry);

            if (targetCountry.equalsIgnoreCase(sourceCountry))
                return name + " skipped the fortification phase!";
        }

        if (noOfArmies == -1) {
            noOfArmies = this.getArmiesInCountry(sourceCountry);
            noOfArmies = random.nextInt(noOfArmies - 1);
        }

        // the transfer the armies
        int armiesLeftInSource = this.getArmiesInCountry(sourceCountry) - noOfArmies;
        int armiesInTarget = this.getArmiesInCountry(targetCountry) + noOfArmies;

        this.updateCountry(sourceCountry, armiesLeftInSource);
        this.updateCountry(targetCountry, armiesInTarget);

        return name + " sent " + noOfArmies + " arm(ies) from " + sourceCountry + " to " + targetCountry;
    }


    public boolean haveInfantryCard() {
        for (String card : this.cards) {
            if (card.equals(CardData.CARD_TYPE_INFANTRY)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if player have Cavalry Card
     *
     * @return true if player have Cavalry Card otherwise false
     */
    public boolean haveCavalryCard() {
        for (String card : this.cards) {
            if (card.equals(CardData.CARD_TYPE_CAVALRY)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if player have Artillery Card
     *
     * @return true if player have Artillery Card otherwise false
     */
    public boolean haveArtilleryCard() {
        for (String card : this.cards) {
            if (card.equals(CardData.CARD_TYPE_ARTILLERY)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if player have Infantry, Artillery and Cavalry Cards
     *
     * @return true if player have Infantry, Artillery and Cavalry Cards otherwise false
     */
    public boolean haveDistinctCards() {
        return (this.haveInfantryCard() && this.haveArtilleryCard() && this.haveCavalryCard());
    }

    /**
     * Checks if player have three Artillery cards
     *
     * @return true if player have three Artillery cards otherwise false
     */
    public boolean haveThreeArtilleryCards() {
        int artillery = 0;
        for (String card : this.cards) {
            if (card.equals(CardData.CARD_TYPE_ARTILLERY)) {
                artillery++;
            }
        }
        return (artillery == 3);
    }

    /**
     * Checks if player have three Cavalry cards
     *
     * @return true if player have three Cavalry cards otherwise false
     */
    public boolean haveThreeCavalryCards() {
        int cavalry = 0;
        for (String card : this.cards) {
            if (card.equals(CardData.CARD_TYPE_CAVALRY)) {
                cavalry++;
            }
        }
        return (cavalry == 3);
    }

    /**
     * Checks if player have three Infantry cards
     *
     * @return true if player have three Infantry Cards otherwise false
     */
    public boolean haveThreeInfantryCards() {
        int infantry = 0;
        for (String card : this.cards) {
            if (card.equals(CardData.CARD_TYPE_INFANTRY)) {
                infantry++;
            }
        }
        return (infantry == 3);
    }

    /**
     * Checks if player have either three Cavalry, Artillery or Infantry cards
     *
     * @return true if player have either three Cavalry, Artillery or Infantry cards otherwise false
     */
    public boolean haveThreeSameTypeCards() {
        return (this.haveThreeCavalryCards() || this.haveThreeArtilleryCards() || this.haveThreeInfantryCards());
    }

    /**
     * Is it possible to add more cards for this player?
     * @return true if you can add more cards
     */
    public boolean canAddMoreCards() {
        return (this.cards.size() < 5);
    }

    /**
     * @return list of cards player has.
     */
    public List<String> getCards() {
        return this.cards;
    }

    /**
     * Removes one Infantry, Artillery and Cavalry cards
     */
    public void removeDistinctCards() {
        this.cards.remove(CardData.CARD_TYPE_ARTILLERY);
        this.cards.remove(CardData.CARD_TYPE_CAVALRY);
        this.cards.remove(CardData.CARD_TYPE_INFANTRY);
    }

    /**
     * Returns the card from player cardlist
     *
     * @param cardName name of the card
     * @return card with cardName equals to parameter
     */
    public String getCard(String cardName) {
        for (String card : this.cards) {
            if (card.equals(cardName)) {
                return card;
            }
        }
        return null;
    }

    /**
     * Removes either of three Infantry or Artillery or Cavalry cards
     * 
     * @param type Sting type of card
     */
    public void removeSimilarThreeCards(String type) {
        this.cards.remove(type);
        this.cards.remove(type);
        this.cards.remove(type);
    }
    
    /**
     * This method will return the total number of armies player have
     * 
     * @return totalArmies Return total number.
     */
    public int getTotalPlayerArmies(){
    	int totalArmies = 0;
    	int count = 0;
        for (Map.Entry<String, Integer> country : this
				.getCountriesConquered().entrySet()) {
        	count++;
			totalArmies += country.getValue();
		}
        return totalArmies;
    }
}
