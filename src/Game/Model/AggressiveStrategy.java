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
public class AggressiveStrategy implements PlayerStrategy{

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
        
        boolean flag=false;
        //a country can a strongest country if 1) it has a neighbour that is also conquered and 2) iff it has the highest number of armies 
        Iterator itForCountriesConquered = countriesConquered.entrySet().iterator();// iterator for countries conqureeed by player
        while (itForCountriesConquered.hasNext()) {
            Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
            String countryName = (String) pair.getKey();
            if(maximumArmies==0) {
                maximumArmies= (int) pair.getValue();
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
                if((int)pair.getValue()>maximumArmies) {
                    strongestCountryName = countryName;
                    maximumArmies = (int) pair.getValue();
                }
            }
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
     * This method is to move armies from one country to another
     * @param armiesToMove number of armies to move
     */
    @Override
    public void moveArmiesAfterAttack(int armiesToMove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method implements the fortification phase
     * @param sourceCountry source country name from which armies to be moved
     * @param targetCountry destination country name to which armies to be moved
     * @param noOfArmies number of armies to be moved
     * @return message of successful fortification
     */
    @Override
    public String fortificationPhase(String sourceCountry, String targetCountry, int noOfArmies) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
