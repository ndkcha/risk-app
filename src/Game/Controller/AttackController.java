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
            
            
        }
        else { //if the attack is normal mode
            
                
            }
            else {
                System.out.println(" the attack between "+attackingCountry+" and "+defendingCountry+" was not successful. ");
                //change the phase
            }
        }
        
        //Is attack possible again
        List<String> attackPossibleCountries=isAnotherAttackPossible(countriesConquered);
        //call the attack function again
        
        //number of countries conquered in this phase
        player.setCountriesConqueredCurrentPhase(countOfCountriesConquered);
        
        //check if whole map is conquered
        if(countriesConquered.keySet().equals(holder.getCountryDataList())) {
            System.out.println("the map is conquered");
            //game ends
        }
        
    }

