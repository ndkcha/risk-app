/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Controller;

import Game.Model.Player;
import Game.Risk.DataHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Game.Model.CountryData;
import Game.Model.ContinentData;
import Game.Model.RollDice;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This controller is for the attack phase
 *
 * @author r-naik
 * @version
 */
public class AttackController {

    private DataHolder holder = DataHolder.getInstance();
    private CountryData countryData;
    private Player player;
    private RollDice rollDice=new RollDice();
    private List<Player> p = this.holder.getPlayerList();

    //the attacker country and defending country
    int indexOfAttackingCountry;
    int indexOfDefendingCountry;
    String attackingCountry;
    String defendingCountry; //

    /**
     * called when to declare an attack
     */
    public void attack() {

        //get players conquered country list
        Player player = holder.getActivePlayer();
        //retrieving the player type
        int playerType = player.getType();
        //retrieving the countries conquered by the player
        HashMap<String, Integer> countriesConquered = player.getCountriesConquered();

        //getting the attacking and defending country
        indexOfAttackingCountry = 0;
        indexOfDefendingCountry = 1;

        //select an attacking country from the dropdown list
        attackingCountry=null;
        
        //In a different function ()
        List<String> tempCountryNeighbours=getNeigboursForAttack(attackingCountry,player);
        
        
        //show the temp country neighbours in the defending country list in view
        
        
        //get the selected defending country from the view
        defendingCountry=null;
        //get the number of dice allowed to the attacker
        int numberOfDice=calculateNoOfDiceAllowed(attackingCountry,player);
        //set the number of dice in view
        //get the number of dice chosen by the attacker
        int chosenNoOfDice = 0;
        int noOfDiceUsed=0;
        //get the mode chosen by the attacker: all out mode (0) or normal mode (1)
        int mode=0;
        //attack between two countries
        boolean attackResult=false;
        int numberOfArmiesAttackerC=0,numberOfArmiesDefenderC = 0;
        //number ofCountries Counquered in this phase
        int countOfCountriesConquered=0;
        
        //if all out mode
        if(mode==0) {
            
            //till the attack is not successful
            while(attackResult=false) {
                attackResult=attackBetweenTwoCoutries(attackingCountry,defendingCountry,numberOfDice,player);
                noOfDiceUsed+=chosenNoOfDice;
                if(attackResult=false) {
                    //check if the attacker country has only one army. stop sttack if true
                    Iterator itForCountriesConquered = countriesConquered.entrySet().iterator();//iterator for countries conqureeed by player
                    while (itForCountriesConquered.hasNext()) {
                        Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
                        if(pair.getKey().equals(attackingCountry)) {
                            numberOfArmiesAttackerC=(int) pair.getValue();
                        }
                    }
                    if(numberOfArmiesAttackerC==1) {
                        System.out.println("Attack failed and the attacker lost the country");
                        break;
                       
                    }
                    numberOfDice=calculateNoOfDiceAllowed(attackingCountry, player);
                }
            }
            //if the attack gets successful
            if(attackResult=true) {
                //getting the armies in defending country
                List<Player> allPlayersList;
                allPlayersList = holder.getPlayerList();
                for (int i = 0; i < allPlayersList.size(); i++) {
                    Player tmp = allPlayersList.get(i);
                    HashMap<String, Integer> countriesConqueredTmp = tmp.getCountriesConquered();
                    Iterator itForCountriesConquered = countriesConqueredTmp.entrySet().iterator();//iterator for countries conqureeed by player
                    while (itForCountriesConquered.hasNext()) {
                        Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
                        if (pair.getKey().equals(defendingCountry)) {
                            numberOfArmiesDefenderC = (int) pair.getValue();
                        }
                    }
                }
                //increment the number of Countries Conquered
                countOfCountriesConquered++;
                //deleting defending country from the defender's conquered country list
                deleteDefendingCountry(defendingCountry);
                //check if any player conqured
                Player tmp = null;
                for (int i = 0; i < allPlayersList.size(); i++) {
                    tmp = allPlayersList.get(i);
                    boolean result=checkForConqueredPlayer(tmp);
                    if(result=true) {
                        List<Player> conqueredPlayerList=holder.getConqueredPlayerList();
                        conqueredPlayerList.add(tmp);
                        holder.setConqueredPlayerList(conqueredPlayerList);
                    }
                }
                
                //putting defending country into the attacker's conquered country list
                addCountryintoConqueredList(defendingCountry,numberOfArmiesDefenderC,player);
                
                //move armies from attacking country to newly conquered country
                //minimum number of armies to move and maximum nmber of armies to move
                int minArmies=getMinArmies(noOfDiceUsed);
                int maxArmies=numberOfArmiesAttackerC-1;
                //set armies in the drop down box to select from
                //get the selected armies
                int selectedNoOfArmies=0;
                Iterator itForCountriesConquered=countriesConquered.entrySet().iterator();
                while(itForCountriesConquered.hasNext()) {
                    Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
                    if (pair.getKey().equals(defendingCountry)) {
                        int numberOfArmies=(int) pair.getValue();
                        //armies added to the conquered Country
                        pair.setValue(numberOfArmies+selectedNoOfArmies);
                    }
                    //armeis deducted from the attacking country
                    if(pair.getKey().equals(attackingCountry)) {
                        int numberOfArmies=(int) pair.getKey();
                        pair.setValue(numberOfArmies-selectedNoOfArmies);
                    }
                }
            }
            
        }
        else { //if the attack is normal mode
            attackResult=attackBetweenTwoCoutries(attackingCountry,defendingCountry,chosenNoOfDice,player);
            //if attack is successful
            if(attackResult=true) {
                System.out.println("the attack between "+attackingCountry+" and "+defendingCountry+" was successful.");
                //increment the number of countries Conquered
                countOfCountriesConquered++;
                //getting the armies in defending country
                List<Player> allPlayersList;
                allPlayersList = holder.getPlayerList();
                for (int i = 0; i < allPlayersList.size(); i++) {
                    Player tmp = allPlayersList.get(i);
                    HashMap<String, Integer> countriesConqueredTmp = tmp.getCountriesConquered();
                    Iterator itForCountriesConquered = countriesConqueredTmp.entrySet().iterator();//iterator for countries conqureeed by player
                    while (itForCountriesConquered.hasNext()) {
                        Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
                        if (pair.getKey().equals(defendingCountry)) {
                            numberOfArmiesDefenderC = (int) pair.getValue();
                        }
                    }
                }
                //deleting defending country from the defender's conquered country list
                deleteDefendingCountry(defendingCountry);
                //increment the number of Countries Conquered
                countOfCountriesConquered++;
                //check if any player conqured
                Player tmp = null;
                for (int i = 0; i < allPlayersList.size(); i++) {
                    tmp = allPlayersList.get(i);
                    boolean result=checkForConqueredPlayer(tmp);
                    if(result=true) {
                        List<Player> conqueredPlayerList=holder.getConqueredPlayerList();
                        conqueredPlayerList.add(tmp);
                        holder.setConqueredPlayerList(conqueredPlayerList);
                    }
                }
                //putting defending country into the attacker's conquered country list
                addCountryintoConqueredList(defendingCountry,numberOfArmiesDefenderC,player);
                
                //move armies from attacking country to newly conquered country
                //minimum number of armies to move and maximum nmber of armies to move
                int minArmies=getMinArmies(noOfDiceUsed);
                int maxArmies=numberOfArmiesAttackerC-1;
                //set armies in the drop down box to select from
                //get the selected armies
                int selectedNoOfArmies=0;
                moveArmies(selectedNoOfArmies,player); 
            }
                
            }
            else {
                System.out.println(" the attack between "+attackingCountry+" and "+defendingCountry+" was not successful. ");
                //change the phase
            }
        }
        
        //Is attack possible again
        List<String> attackPossibleCountries=isAnotherAttackPossible(countriesConquered);
        //call the attack function againn
        
        //number of countries conquered in this phase
        player.setCountriesConqueredCurrentPhase(countOfCountriesConquered);
        
        //check if whole map is conquered
        if(countriesConquered.keySet().equals(holder.getCountryDataList())) {
            System.out.println("the map is conquered");
            //game ends
        }
        
    }

/**
     * to calculate number of dice allowed depending upon the armies existing in the country
     * @param CountryName name of the country
     * @return number of dice allowed 
     */
    public int calculateNoOfDiceAllowed(String CountryName, Player player) {
        
        int noOfDiceAllowed = 0;
        int numberOfArmies=0;
        HashMap<String, Integer> countriesConquered = player.getCountriesConquered();
        Iterator itForCountriesConquered = countriesConquered.entrySet().iterator();//iterator for countries conqureeed by player
        while (itForCountriesConquered.hasNext()) {
            Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
            if(pair.getKey().equals(CountryName)) {
                numberOfArmies=(int) pair.getValue();
            }
        }
        if(numberOfArmies>3) {
            noOfDiceAllowed=3;
        }
        else if ( numberOfArmies==3) {
            noOfDiceAllowed=2;
        }
        else if (numberOfArmies==2) {
            noOfDiceAllowed=1;
        }
        else {
            noOfDiceAllowed=0;
        }
        
        return noOfDiceAllowed;
    }

/**
     * Get neighbours of specific country
     * @param countryName name of the country
     * @return list of neighbouring countries
     */
    public List<String> getNeighbours(String countryName) {
        List<CountryData> countryDataList = holder.getCountryDataList();
        int index = 0;
        for (int i = 0; i < countryDataList.size(); i++) {
            if (countryDataList.get(i).getName().equals(countryName)) {
                index = i; //index of the country in countryDataList whose neighbouring countries have to be searched
            }
        }
        ArrayList<String> neighbours = countryDataList.get(index).getNeighbours();
        return neighbours;
    }

    /**
    * This function checks if attack is successful
    * @param attackingCountry the attacking country name
    * @param defendingCountry the country on which attack is done
    * @param chosenNoOfDice number of dice used by attacker
    * @param player the player who is attacking
    * @return true if attack successful, false if not
    */
    public boolean attackBetweenTwoCoutries(String attackingCountry, String defendingCountry, int chosenNoOfDice, Player player) {
       
        Integer[] diceRollValuesOfAttacker= new Integer[chosenNoOfDice];
        Integer[] diceRollValuesOfDefender= new Integer[chosenNoOfDice-1];
        boolean attackstatus=false;
        
        //dice rolls for attacker
        for(int i=0;i<chosenNoOfDice;i++){
            diceRollValuesOfAttacker[i]=rollDice.roll();
        }
        //sorting the dice rolls in decreasing values
        Arrays.sort(diceRollValuesOfAttacker, Collections.reverseOrder());
        
        //dice rolls for defender
        for(int i=0;i<chosenNoOfDice-1;i++) {
             diceRollValuesOfDefender[i]=rollDice.roll();
        }
        
        //sorting the dice rolls in decreasing values
        Arrays.sort(diceRollValuesOfDefender, Collections.reverseOrder());
        
        HashMap<String, Integer> countriesConquered = player.getCountriesConquered();
        
        //matching the dice roll values
        for(int i=0;i<chosenNoOfDice-1;i++) {
            //if the dice value of attacker is more than dice value of defender
            if (diceRollValuesOfAttacker[i] > diceRollValuesOfDefender[i]) {
                //decrement armies in defending country
                decrementArmiesDefendingC(defendingCountry);
            } 
            //if the dice value of attacker is same as dice roll of defender
            else if(diceRollValuesOfAttacker.equals(diceRollValuesOfDefender)) {
                //decrement the armies in attacking country
                Iterator itForCountriesConquered = countriesConquered.entrySet().iterator();//iterator for countries conqureeed by player
                while (itForCountriesConquered.hasNext()) {
                    Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
                    if (pair.getKey().equals(attackingCountry)) {
                        int numberOfArmies = (int) pair.getValue();
                        pair.setValue(numberOfArmies--);
                    }
                }
            }
            //if dice value of attacker is less then the dice value of defender
            else {
                //decrement the armies in attacking country
                Iterator itForCountriesConquered = countriesConquered.entrySet().iterator();//iterator for countries conqureeed by player
                while (itForCountriesConquered.hasNext()) {
                    Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
                    if (pair.getKey().equals(attackingCountry)) {
                        int numberOfArmies = (int) pair.getValue();
                        pair.setValue(numberOfArmies--);
                    }
                }
            }
        }
        //update the conquered country list of the player
        player.setCountriesConquered(countriesConquered);
        //if the defending country is left with zero countries then the country is conquered, thus attcak successful
        Iterator itForCountriesConquered = countriesConquered.entrySet().iterator();//iterator for countries conqureeed by player
        while (itForCountriesConquered.hasNext()) {
            Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
            if (pair.getKey().equals(defendingCountry)) {
                int numberOfArmies = (int) pair.getValue();
                if (numberOfArmies==0) {
                    //attack successful
                    attackstatus=true;
                }
            }
        }
        
        //return the attack success status
        return attackstatus;
    }

//**
     * This method is used to delete the defending country from the defending player's conquered country list
     * @param defendingCountry the country on which attack is taking place
     */
    public void deleteDefendingCountry(String defendingCountry) {
        List <Player> allPlayersList;
        allPlayersList= holder.getPlayerList();
        for(int i=0;i<allPlayersList.size();i++) {
                    Player tmp=allPlayersList.get(i);
                    HashMap<String, Integer> countriesConqueredTmp = tmp.getCountriesConquered();
                    if(countriesConqueredTmp.containsKey(defendingCountry)) {
                        countriesConqueredTmp.remove(defendingCountry);
                        tmp.setCountriesConquered(countriesConqueredTmp);
                    }                    
                }
    }
    
    /**
     * This method is used to add country in conquered country list
     * @param defendingCountry the country on which attack is taking place
     * @param numberOfArmiesDefenderC the number of armies in the country
     */
    public void addCountryintoConqueredList(String defendingCountry, int numberOfArmiesDefenderC, Player player) {
        HashMap<String, Integer> countriesConquered = player.getCountriesConquered();
        countriesConquered.put(defendingCountry,numberOfArmiesDefenderC);
        player.setCountriesConquered(countriesConquered);
    }

    /**
     * this method decrements the number of armies a defending country
     * @param defendingCountry the country on which attack is done
     */
    public void decrementArmiesDefendingC(String defendingCountry) {
        //taking al player
        List<Player> allPlayersList;
        allPlayersList=holder.getPlayerList();
        //for each player in the list
        for (int i=0;i<allPlayersList.size();i++) {
            Player temp=allPlayersList.get(i);
            //get a particular player's conquered country list
            HashMap<String, Integer> countriesConqueredTmp = temp.getCountriesConquered();
            Iterator itForCountriesConquered = countriesConqueredTmp.entrySet().iterator();//iterator for countries conqureeed by player
            while (itForCountriesConquered.hasNext()) {
                Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
                //if the player has the defending country in the conquered country list
                if (pair.getKey().equals(defendingCountry)) {
                    int numberOfArmies = (int) pair.getValue();
                    pair.setValue(numberOfArmies--);
                }
            }
            //update the players conquered country list
            temp.setCountriesConquered(countriesConqueredTmp);
        }
    }
/**
     * Get minimum number of armies that need to be transfered to conquered country
     * @param noOfDiceUsed number of dice used in the attack
     * @return 
     */
    public int getMinArmies(int noOfDiceUsed) {
        int minArmies=noOfDiceUsed;
        return minArmies;
    }

    /**
     * Move armies from one country to another
     * @param selectedNoOfArmies number of armies to be moved
     */
    public void moveArmies(int selectedNoOfArmies, Player player) {
        HashMap<String, Integer> countriesConquered = player.getCountriesConquered();
        Iterator itForCountriesConquered=countriesConquered.entrySet().iterator();
                while(itForCountriesConquered.hasNext()) {
                    Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
                    if (pair.getKey().equals(defendingCountry)) {
                        int numberOfArmies=(int) pair.getValue();
                        //armies added to the conquered Country
                        pair.setValue(numberOfArmies+selectedNoOfArmies);
                    }
                    //armeis deducted from the attacking country
                    if(pair.getKey().equals(attackingCountry)) {
                        int numberOfArmies=(int) pair.getKey();
                        pair.setValue(numberOfArmies-selectedNoOfArmies);
                    }
                }
    }
    
    /**
     * check if any player conquered in this phase?
     * @return true if conquered and false if not
     */
    public boolean checkForConqueredPlayer(Player player) {
        
        boolean result=false;
       
            //get a particular player's conquered country list
            HashMap<String, Integer> countriesConqueredTmp = player.getCountriesConquered();
            if(countriesConqueredTmp.size()==0) {
                //player is conquered
                result=true;
                
            }
        
        return result;
    }
/**
     * check if another attack is possible
     * @param countriesConquered list of conquered countries
     * @return list of countries that can attack
     */
    public List<String> isAnotherAttackPossible(HashMap<String, Integer> countriesConquered) {
       
        List<String> countriesPossible= new ArrayList<>();
        Iterator itForCountriesConquered = countriesConquered.entrySet().iterator();
        while (itForCountriesConquered.hasNext()) {
            Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
            String countryName=(String) pair.getKey();
            int numberOfArmies=(int) pair.getValue();
            if(numberOfArmies>=2) {
                countriesPossible.add(countryName);
            }
        }
        return countriesPossible;
    }

    /**
     * The list of neighbours that are not conquered
     * @param attackingCountry name of the attacking country
     * @return 
     */
    public List<String> getNeigboursForAttack(String attackingCountry, Player player) {
        //get the list of neighbouring countries of the attacking country
        List<String> CountryNeighbours = new ArrayList<>();
        CountryNeighbours = getNeighbours(attackingCountry);
        //retrieving the countries conquered by the player
        HashMap<String, Integer> countriesConquered = player.getCountriesConquered();
        //list of neighbours attacking country can attack - neighbouring countries that are not already conquered
        List<String> tempCountryNeighbours = new ArrayList<>();
        for (int i = 0; i < CountryNeighbours.size(); i++) {
            if (!countriesConquered.containsKey(CountryNeighbours.get(i))) {
                tempCountryNeighbours.add(CountryNeighbours.get(i));
            }
        }
        return tempCountryNeighbours;
    }
}

