/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Model;

import Game.Controller.AttackController;
import Game.Risk.DataHolder;
import Game.Model.Player;
import Game.Model.CountryData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.print.DocFlavor;

/**
 * Methods to implement the Aggressive strategy
 * @author r-naik
 */
public class AggressiveStrategy implements PlayerStrategy, Serializable {

    private DataHolder holder = DataHolder.getInstance();
    
    /**
     * This method returns the strongest country conquered by the player
     * @return the name of the strongest country
     */
    public String strongestCountry() {
        String strongestCountryName = null;
        int maximumArmies=0;
        //get the active player
        Player player = holder.getActivePlayer();
        // retrieving the countries conquered by the player
        HashMap<String, Integer> countriesConquered = player.getCountriesConquered();
        List<String> tried = new ArrayList<>();

        //a country can a strongest country if 1) it has a neighbour that is also conquered and 2) iff it has the highest number of armies

        while (tried.size() != countriesConquered.size()) {
            maximumArmies = 0;
            System.out.println(tried);
            for (Map.Entry<String, Integer> entry : countriesConquered.entrySet()) {
                System.out.println("entry: " + entry.getKey());
                if (tried.indexOf(entry.getKey()) != -1)
                    continue;
                List<String> neighbours = getNeighbours(entry.getKey());

                for (String neighbour : neighbours) {
                    if (countriesConquered.containsKey(neighbour) && (entry.getValue() > maximumArmies)) {
                        maximumArmies = entry.getValue();
                        strongestCountryName = entry.getKey();
                    }
                }
            }

            System.out.println(strongestCountryName);

            if (strongestCountryName != null) {
                tried.add(strongestCountryName);
                AttackController controller = new AttackController();
                if (controller.getNeighboursForAttack(strongestCountryName).size() == 0) {
                    strongestCountryName = null;
                } else
                    break;
            } else
                break;
        }

        return strongestCountryName;
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
     * gets the weakest neighbour to attack
     * @param countryName country name whose neighbors are to be found
     * @return name of the weakest neighbour
     */
    public String weakestNeighbourForAttack(String countryName) {
        AttackController ac=new AttackController();
        List<String> neighboursForAttack=ac.getNeighboursForAttack(countryName);
        System.out.println(" number of neighbours "+neighboursForAttack.size());
        String weakestNeighbour=null,tempneighbor = null;
        int lowestArmies=0, noOfArmies=0;
        List<Player> allPlayersList;
        allPlayersList = holder.getPlayerList();
        //for each country in the neighbours list
        for(int j=0;j<neighboursForAttack.size();j++) {
            //System.out.println("neighbor "+neighboursForAttack.get(j));
            // for each player in the list
            for (int i = 0; i < allPlayersList.size(); i++) {
                Player temp = allPlayersList.get(i);
                if (temp.getName().equalsIgnoreCase(holder.getActivePlayer().getName()))
                    continue;
                // get a particular player's conquered country list
                HashMap<String, Integer> countriesConqueredTmp = temp.getCountriesConquered();
                Iterator itForCountriesConquered = countriesConqueredTmp.entrySet().iterator();// iterator for countries conqureeed by player
                while (itForCountriesConquered.hasNext()) {
                    Map.Entry pair = (Map.Entry) itForCountriesConquered.next();// if the player has the country in the conqueredcountry list
                    //if the key equal to the neighbor teh get number of armies in the country
                    if (pair.getKey().equals(neighboursForAttack.get(j))) {
                        noOfArmies  = (int) pair.getValue();
                        tempneighbor=neighboursForAttack.get(j);
                        if(lowestArmies==0) {
                            lowestArmies=noOfArmies;
                            weakestNeighbour=tempneighbor;
                        }
                        System.out.println("Neigbour:: "+pair.getKey()+"armies::"+pair.getValue());
                    }
                }
            }
            //if the number of armies in the neigbour country is lowest till now
            if (noOfArmies < lowestArmies) {
                lowestArmies = noOfArmies;
                weakestNeighbour =tempneighbor;
            }
        }
        System.out.println("The weakest neighbour is "+weakestNeighbour+" with number of armies:" +lowestArmies);
        return weakestNeighbour;
    }
    
    /**
     * This method implements the reinforcement phase 
     * @param armiesToAllocate number of armies to allocate during the phase
     * @param country country name to which the armies to be added
     * @return message of successful allocation
     */
    @Override
    public String reinforcementPhase(int armiesToAllocate, String country) {
        String countryName=strongestCountry();
        System.out.println("The strongest country is" +strongestCountry());
        return countryName;
    }

    /**
     * This method implements attack
     * @return number of armies to be moved
     */
    @Override
    public List<String> attackPhase() {
        
        List<String> attackerAndDefender= new ArrayList<>();
        attackerAndDefender.add(strongestCountry());
        attackerAndDefender.add(weakestNeighbourForAttack(attackerAndDefender.get(0)));
        System.out.println("Attacker:::" +attackerAndDefender.get(0)+"Defender:::" + attackerAndDefender.get(1));
        return attackerAndDefender;
    }

    /**
     * This method implements the fortification phase
     * @return message of successful fortification
     */
    @Override
    public String fortificationPhase() {
        Player player = holder.getActivePlayer();
        String targetCountry=strongestCountry();
        String sourceCountry=null;
        
        List<String> neighbours = getNeighbours(targetCountry);
        int maxArmy = -1;
        sourceCountry = "";
        for (Map.Entry<String, Integer> entry : player.getCountriesConquered().entrySet()) {
            if ((neighbours.indexOf(entry.getKey()) > -1) && (entry.getValue() > maxArmy)) {
                maxArmy = entry.getValue();
                sourceCountry = entry.getKey();
            }
        }
        
        return sourceCountry+"-"+targetCountry+"-"+maxArmy;
    }
    
}
