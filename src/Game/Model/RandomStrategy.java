/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Model;

import Game.Controller.AttackController;
import Game.Risk.DataHolder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Methods to implement Random Strategy
 * @author r-naik
 */
public class RandomStrategy implements PlayerStrategy{

    private DataHolder holder = DataHolder.getInstance();
    
    /**
     * returns random country name from the list of conquered country list for reinforcement and fortificaton
     * @return country name
     */
    public String randomCountry() {
        //get the active player
        Player player = holder.getActivePlayer();
        // retrieving the countries conquered by the player
        HashMap<String, Integer> countriesConquered = player.getCountriesConquered();
        Random rand = new Random();
        int noOfArmies=0;
        List<String> countriesList = new ArrayList<>(countriesConquered.keySet());
        String randomCountry=countriesList.get(rand.nextInt(countriesList.size())); //getting the random country
        //checking if the country has more than one armies
        Iterator itForCountriesConquered = countriesConquered.entrySet().iterator();// iterator for countries conqureeed by player
        while (itForCountriesConquered.hasNext()) {
            Map.Entry pair = (Map.Entry) itForCountriesConquered.next();// if the player has the country in the conqueredcountry list
            //if the key equal to the neighbor teh get number of armies in the country
            if (pair.getKey().equals(randomCountry)) {
                noOfArmies = (int) pair.getValue();
            }
        }
        if(noOfArmies<=1) {
            randomCountry=randomCountry();
        }
        return randomCountry;
    }
    
    
    /**
     * returns a random country which is a neigbour but not yet conquered
     * @param countryName name of a country
     * @return a country name to which attack will be done
     */
    public String randomCountryToAttack(String countryName) {
        return null;
    }
    
    /**
     * This method implements the reinforcement phase 
     * @param armiesToAllocate number of armies to allocate during the phase
     * @param country country name to which the armies to be added
     * @return message of successful allocation
     */
    @Override
    public String reinforcementPhase(int armiesToAllocate, String country) {
        String countryName=randomCountry();
        System.out.println("The random country is" +countryName);
        return countryName;
    }

    /**
     * This method implements attack
     * @return number of armies to be moved
     */
    @Override
    public List<String> attackPhase() {
        String attacker=randomCountry();
        String defender=randomCountryToAttack(attacker);
        List<String> attackerDefender= new ArrayList<>();
        attackerDefender.add(attacker);
        attackerDefender.add(defender);
        return attackerDefender;
    }
    
    /**
     * This method implements the fortification phase
     * @return message of successful fortification
     */
    @Override
    public String fortificationPhase() {
        Player player=holder.getActivePlayer();
        String sourceCountry=randomCountry();
        String targetCountry=randomCountry();
        return sourceCountry+"-"+targetCountry+"-1";
    }
    
}
