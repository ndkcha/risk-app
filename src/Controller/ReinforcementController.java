package Controller;

import java.util.*;
import Model.CountryData;
import Model.ContinentData;
import Risk.DataHolder;
import Model.Player;

/**
 * This controller class is for the reinforcement phase
 *
 * @author r-naik
 */
public class ReinforcementController {

    private DataHolder holder = DataHolder.getInstance();
    private Player player;
 
    private List<Player> p = this.holder.getPlayerList();

    /**
     * This function calculates the armies a player avails in each reinforcement
     * phase
     *
     * @param playerNumber The identity of the player to which armies is
     * assigned
     */
    void calculateReinformentArmies(int playerNumber) {
        System.out.println("Calculating armies for player " + playerNumber);

        int newarmies;

        //retrieving the player number whose turn is goin on
        player = p.get(playerNumber - 1);

        //retrieving the continents conquered by the player
        HashMap<String, Integer> countriesConquered = player.getCountriesConquered();

        //get armies due to conquering whole continent
        int listSizeOfCountriesConquered;
        int continentAddedArmies = 0;
        for (ContinentData continentData : holder.getContinentDataList()) {//get data for every continent
            String continentName = continentData.getName();
            List<CountryData> countriesContinent = holder.countCountriesInContinent(continentName);//get COuntries of Continent
            int countrySize = countriesContinent.size();//size of the no of countries in continent
            listSizeOfCountriesConquered = 0;
            for (CountryData countryData : countriesContinent) {///countires in continent loop
                Iterator itForCountriesConquered = countriesConquered.entrySet().iterator();//iterator for countries conqureeed by player
                while (itForCountriesConquered.hasNext()) {
                    Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
                    String countryName = (String) pair.getKey();
                    if (countryData.getName().equalsIgnoreCase(countryName)) {
                        listSizeOfCountriesConquered++;
                    }
                }
            }
            if (listSizeOfCountriesConquered == countrySize) {
                continentAddedArmies += continentData.getControlValue();
            }
        }

        System.out.println("The number of armies added due to conquering whole continent is: " + continentAddedArmies);

        System.out.println("Calculating armies for player " + playerNumber);

        // number of countries owned divided by 3 and rounded down if the player owns more than 9 territores otherwise 3 territories
        if (countriesConquered.size() < 9) {
            newarmies = 3;
        } else {
            int armies = Math.floorDiv((countriesConquered.size()), 3);
            newarmies = armies;
        }

        System.out.println("The number of armies available for reinforcement phase is " + newarmies);

        //updating the total number of armies
        updateArmiesInCountries(playerNumber,newarmies);

    }

    /**
     * This function is for adding the calculated reinforcement armies to the countries as part of the phase
     * @param playerTurn the player whose turn is going on
     * @param noOfArmies number of armies allowed to the player for reinforcement phase 
     */
    void updateArmiesInCountries(int playerTurn, int noOfArmies) {
        
        //retrieving the player whose turn is going on
        player = p.get(playerTurn - 1);
        
        //retrieving the continents conquered by the player
        HashMap<String, Integer> countriesConquered = player.getCountriesConquered();
        
    }

}
