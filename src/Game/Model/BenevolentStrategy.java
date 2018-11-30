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
        int minArmies = -1;
        //get the active player
        Player player = holder.getActivePlayer();
        // retrieving the countries conquered by the player
        HashMap<String, Integer> countriesConquered = player.getCountriesConquered();

        //a country can a weakest country if 1) it has a neighbour that is also conquered and 2) iff it has the lowest number of armies

        for (Map.Entry<String, Integer> entry : countriesConquered.entrySet()) {
            if (minArmies == -1)
                minArmies = entry.getValue();
            List<String> neighbours = getNeighbours(entry.getKey());

            for (String neighbour : neighbours) {
                if (countriesConquered.containsKey(neighbour) && (entry.getValue() < minArmies)) {
                    minArmies = entry.getValue();
                    weakestCountryName = entry.getKey();
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
     * @return message of successful fortification
     */
    @Override
    public String fortificationPhase() {
        Player player=holder.getActivePlayer();
        Random random=new Random();
        String targetCountry=weakestCountry();
        if (player.getCountriesConquered().size() <= 1) {
            return "-null-1";
        }
        int pickCountry = random.nextInt(player.getCountriesConquered().size() - 1);

        String sourceCountry = player.getNthCountry(pickCountry);

        int armies=player.getArmiesInCountry(sourceCountry);
        return sourceCountry+"-"+targetCountry+"-"+armies;
    }
    
}
