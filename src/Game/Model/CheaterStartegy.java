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

/**
 * Methods to implement Cheater Strategy
 * @author r-naik
 */
public class CheaterStartegy implements PlayerStrategy{

    private DataHolder holder = DataHolder.getInstance();
    
    /**
     * This method implements the reinforcement phase 
     * @param armiesToAllocate number of armies to allocate during the phase
     * @param country country name to which the armies to be added
     * @return message of successful allocation
     */
    @Override
    public String reinforcementPhase(int armiesToAllocate, String country) {
        
        //get the active player
        Player player = holder.getActivePlayer();
        // retrieving the countries conquered by the player
        HashMap<String, Integer> countriesConquered = player.getCountriesConquered();
        int armies=0;
        Iterator itForCountriesConquered = countriesConquered.entrySet().iterator();// iterator for countries conqureeed by player
        while (itForCountriesConquered.hasNext()) {
            Map.Entry pair = (Map.Entry) itForCountriesConquered.next();// if the player has the country in the conqueredcountry list
            //double the armies in the country
            armies+=(int)pair.getValue();
            pair.setValue(armies);
        }
        player.setCountriesConquered(countriesConquered);
        holder.updatePlayer(player);
        return "Player "+player.getName()+" reinforced all its armies";
    }

    /**
     * This method implements attack
     * @return number of armies to be moved
     */
    @Override
    public List<String> attackPhase() {
        //get the active player
        Player player = holder.getActivePlayer();
        AttackController ac=new AttackController();
        List<String> tempList=new ArrayList<>();
        List<String> message=new ArrayList<>();
        
        // retrieving the countries conquered by the player
        HashMap<String, Integer> countriesConquered = new HashMap<>(player.getCountriesConquered());
        HashMap<String, Integer> ptrCountriesConquered = player.getCountriesConquered();
        
        for (Map.Entry<String, Integer> pair : countriesConquered.entrySet()) {
            List<String> neighbours=new ArrayList<>(ac.getNeighboursForAttack((String)pair.getKey()));
            for(int j=0;j<neighbours.size();j++) {
                //if the neighbour is not already conquered in previous iterations
                if(!tempList.contains(neighbours.get(j))) {
                    tempList.add(neighbours.get(j));
                    // getting the armies in neigbouring country
                    List<Player> allPlayersList;
                    allPlayersList = holder.getPlayerList();
                    for (int i = 0; i < allPlayersList.size(); i++) {
                        Player tmp = allPlayersList.get(i);
                        HashMap<String, Integer> countriesConqueredTmp = tmp.getCountriesConquered();
                        
                        if (countriesConqueredTmp.containsKey(neighbours.get(j))) {
                            int armies = countriesConqueredTmp.get(neighbours.get(j));
                            ptrCountriesConquered.put(neighbours.get(j), armies);
                            
                            countriesConqueredTmp.remove(neighbours.get(j));
                        }
                        
                        holder.updatePlayer(tmp);
                    }
                }
            }
        }
        
        player.setCountriesConquered(ptrCountriesConquered);
        holder.updatePlayer(player);
        message.add(player.getName()+" conquered all the neighbouring countries");
        //no armeis to be moved in attack phase of cheater player.
        return message;
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
        
        //get the active player
        Player player = holder.getActivePlayer();
        // retrieving the countries conquered by the player
        HashMap<String, Integer> countriesConquered = player.getCountriesConquered();
     
        Iterator itForCountriesConquered = countriesConquered.entrySet().iterator();// iterator for countries conqureeed by player
        while (itForCountriesConquered.hasNext()) {
            Map.Entry pair = (Map.Entry) itForCountriesConquered.next();// if the player has the country in the conqueredcountry list
            List<String> neighbours = getNeighbours((String)pair.getKey());
            for(int i=0;i<neighbours.size();i++) {
                if(!countriesConquered.containsKey(neighbours.get(i))) {
                    System.out.println("The armies in "+(String)pair.getKey()+" is " +(int) pair.getValue());
                    
                    int armies=(int)pair.getValue()*2;
                    pair.setValue(armies);
                    break;
                }
            }
        }
        
        for (Map.Entry<String, Integer> entry : countriesConquered.entrySet()) {
            System.out.println("The updated armies in " + entry.getKey() + " is " + entry.getValue());
        }
        
        player.setCountriesConquered(countriesConquered);
        holder.updatePlayer(player);
        return "cheater's fortification";
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

}
