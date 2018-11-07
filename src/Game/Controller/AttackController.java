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

