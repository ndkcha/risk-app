package Model;

/**
 * This holds the data of a country conquered by the player
 * It holds the armies held inside the country
 * @author ndkcha
 */
public class PlayerCountry {
    private String name;
    private int noOfArmies;

    PlayerCountry(String name, int noOfArmies) {
        this.name = name;
        this.noOfArmies = noOfArmies;
    }

    public void updateNoOfArmies(int noOfArmies) {
        this.noOfArmies = noOfArmies;
    }

    public String getName() {
        return name;
    }

    public int getNoOfArmies() {
        return noOfArmies;
    }
}
