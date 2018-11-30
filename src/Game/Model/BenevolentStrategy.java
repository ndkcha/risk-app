/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Model;

import Game.Risk.DataHolder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Methods to implement Benevolent Strategy
 * @author r-naik
 */
public class BenevolentStrategy implements PlayerStrategy{

    private DataHolder holder = DataHolder.getInstance();
    
    /**
     * Returns the name of the Weakest Country
     * @return name of the weakest country
     */
    public String weakestCountry() {
        String weakestCountryName = null;
        int minArmies=0;
        //get the active player
        Player player = holder.getActivePlayer();
        // retrieving the countries conquered by the player
        HashMap<String, Integer> countriesConquered = player.getCountriesConquered();
        
        boolean flag=false;
        //a country can a weakest country if 1) it has a neighbour that is also conquered and 2) iff it has the lowest number of armies 
        Iterator itForCountriesConquered = countriesConquered.entrySet().iterator();// iterator for countries conqureeed by player
        while (itForCountriesConquered.hasNext()) {
            Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
            String countryName = (String) pair.getKey();
            if(minArmies==0) {
                minArmies= (int) pair.getValue();
            }
            
            // get the list of neighbouring countries of the country
            List<String> countryNeighbours = new ArrayList<>();
            countryNeighbours = getNeighbours(countryName);
            for (int i = 0; i < countryNeighbours.size(); i++) {
                if (countriesConquered.containsKey(countryNeighbours.get(i))) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                if((int)pair.getValue()<minArmies) {
                    weakestCountryName = countryName;
                    minArmies = (int) pair.getValue();
                }
            }
        }
        return weakestCountryName;
    }
    
    /**
     * Get neighbours of specific country
     *
     * @param countryName name of the country
     * @return list of neighbouring countries
     */
    public List<String> getNeighbours(String countryName) {
        List<CountryData> countryDataList = holder.getCountryDataList();
        int index = 0;
        for (int i = 0; i < countryDataList.size(); i++) {
            if (countryDataList.get(i).getName().equals(countryName)) {
                index = i; // index of the country in countryDataList whose
                // neighbouring countries have to be searched
            }
        }
        ArrayList<String> neighbours = countryDataList.get(index)
                .getNeighbours();
        return neighbours;
    }
    
    /**
     * This method implements the reinforcement phase 
     * @param armiesToAllocate number of armies to allocate during the phase
     * @param country country name to which the armies to be added
     * @return message of successful allocation
     */
    @Override
    public String reinforcementPhase(int armiesToAllocate, String country) {
        String weakCountry=weakestCountry();
        return weakestCountry();
    }

    /**
     * This method implements attack
     * @return number of armies to be moved
     */
    @Override
    public List<String> attackPhase() {
        List<String> temp= new ArrayList<>();
        temp.add("Attack of benevolent player is skipped");
        return temp;
    }

    /**
     * This method implements the fortification phase
     * @param sourceCountry source country name from which armies to be moved
     * @param targetCountry destination country name to which armies to be moved
     * @param noOfArmies number of armies to be moved
     * @return message of successful fortification
     */
    @Override
    public String fortificationPhase() {
        Player player=holder.getActivePlayer();
        Random random=new Random();
        String targetCountry=weakestCountry();
        int pickCountry = random.nextInt(player.getCountriesConquered().size() - 1);
        System.out.println("The index of country "+pickCountry);
        String sourceCountry = player.getNthCountry(pickCountry);
        System.out.println("source country "+sourceCountry);
        int armies=player.getArmiesInCountry(sourceCountry);
        return sourceCountry+"-"+targetCountry+"-"+armies;
    }
    
}
