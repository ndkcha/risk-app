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
    private int noOfArmiesToAssign = 0;
    private String name, color;
    private int type;
    private List<String> cards = new ArrayList<>();
    private int cardsUsedCount = 0;
    private boolean isCardUsed = false;

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
     * The countries conquered by the player. Key is the name of the country.
     * Values is the number of armies inside that country.
     */
    private HashMap<String, Integer> countriesConquered;

    /**
     * The continents conquered by the player
     */
    private List<String> continentsConquered;

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
        this.continentsConquered = new ArrayList<>();
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
     */
    public void attackPhase() {

    }

    /**
     * Refactoring 2: All phases in player model.
     * Implementation of Fortification Phase.
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
     */
    public void removeSimilarThreeCards(String type) {
        this.cards.remove(type);
        this.cards.remove(type);
        this.cards.remove(type);
    }
}
