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
import Game.Model.RollDice;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This controller is for the attack phase
 *
 * @author r-naik
 * @version 1.2.0
 */
public class AttackController {

    public static final int MODE_ALL_OUT = 0;
    public static final int MODE_NORMAL = 1;

    private DataHolder holder = DataHolder.getInstance();
    private RollDice rollDice = new RollDice();

    /**
     * called when to declare an attack. not relevant
     *
     * @param attackingCountry Attacking Country
     * @param defendingCountry Defending Country
     * @param mode Mode of turn
     * @param numberOfDice Number of dice.
     *
     * @return returnResult Number of armies to add.
     */
    public int attack(String attackingCountry, String defendingCountry,
            int mode, int numberOfDice) {

        // get players conquered country list
        Player player = holder.getActivePlayer();
        // retrieving the player type
        int playerType = player.getType();
        // retrieving the countries conquered by the player
        HashMap<String, Integer> countriesConquered = player
                .getCountriesConquered();
        // String which returns the number of armies in to move in format "min
        // no of armies to move: max no of armies to move" if attack is
        // successful
        // else return "-1"
        int returnResult = 0;

        // get the number of dice allowed to the attacker
        if (numberOfDice == -1) {
            numberOfDice = calculateNoOfDiceAllowed(attackingCountry);
            player = holder.getActivePlayer();
        }
        // attack between two countries
        boolean attackResult = false;
        int numberOfArmiesAttackerC = 0, numberOfArmiesDefenderC = 0;
        int modeCounter = 0;

        System.out.println("mode: " + mode);
        System.out.println("numberOfDice: " + numberOfDice);

        // if all out mode
        if (mode == 0) {
            holder.sendGameLog(player.getName() + ": all out mode selected");
            // till the attack is not successful
            while (!attackResult) {
                if (modeCounter < 10) {
                    player = holder.getActivePlayer();
                    attackResult = attackBetweenTwoCoutries(attackingCountry,
                            defendingCountry, numberOfDice);
                    player = holder.getActivePlayer();
                    System.out.println("attackResult: " + attackResult);
                    if (!attackResult) {
                        // check if the attacker country has only one army. stop sttack if true
                        Iterator itForCountriesConquered = countriesConquered.entrySet().iterator();// iterator for countries conqureeed by player
                        while (itForCountriesConquered.hasNext()) {
                            Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
                            if (pair.getKey().equals(attackingCountry)) {
                                numberOfArmiesAttackerC = (int) pair.getValue();
                            }
                        }
                        System.out.println("numberOfArmiesAttackerC: "+ numberOfArmiesAttackerC);
                        if (numberOfArmiesAttackerC == 1) {
                            System.out.println("Attack failed");
                            holder.sendGameLog(player.getName()+ ": Attack failed and the attacker lost the country");
                            break;

                        }

                        numberOfDice = calculateNoOfDiceAllowed(
                                attackingCountry);
                        System.out
                                .println("numberOfDice again: " + numberOfDice);
                    }
                    modeCounter++;
                }
                if (modeCounter == 10) {
                    break;
                }
            }
            if (!attackResult) {
                returnResult = -1;
            }
            System.out.println("attackResult2: " + attackResult);
            // if the attack gets successful
            if (attackResult) {
                holder.sendGameLog(player.getName() + ": the attack between "+ attackingCountry + " and " + defendingCountry+ " was successful.");
                // getting the armies in defending country
                List<Player> allPlayersList;
                allPlayersList = holder.getPlayerList();
                for (int i = 0; i < allPlayersList.size(); i++) {
                    Player tmp = allPlayersList.get(i);
                    HashMap<String, Integer> countriesConqueredTmp = tmp.getCountriesConquered();
                    Iterator itForCountriesConquered = countriesConqueredTmp.entrySet().iterator();// iterator for countries
                    // conqureeed by player
                    while (itForCountriesConquered.hasNext()) {
                        Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
                        if (pair.getKey().equals(defendingCountry)) {
                            numberOfArmiesDefenderC = (int) pair.getValue();
                        }
                    }
                }
                String cardType = holder.getCardFromCountry(defendingCountry);
                if ((cardType != null) && (player.canAddMoreCards())) {
                    player.addCard(cardType);
                    holder.useCardOfCountry(defendingCountry);
                    holder.updatePlayer(player);
                }
                player = holder.getActivePlayer();
                System.out.println(
                        "Defender country armies when attack is successful:"
                        + numberOfArmiesDefenderC);
                // deleting defending country from the defender's conquered
                // country list
                deleteDefendingCountry(defendingCountry);

                // putting defending country into the attacker's conquered
                // country list
                addCountryintoConqueredList(defendingCountry,
                        numberOfArmiesDefenderC);
                player = holder.getActivePlayer();

                // //move armies from attacking country to newly conquered
                // country
                // //minimum number of armies to move and maximum nmber of
                // armies to move
                System.out.println("Number of dices " + numberOfDice);
                int minArmies = numberOfDice;

                returnResult = minArmies;
            }

        } else { // if the attack is normal mode
            attackResult = attackBetweenTwoCoutries(attackingCountry,defendingCountry, numberOfDice);
            player = holder.getActivePlayer();
            // if attack is successful
            if (attackResult) {
                System.out.println("the attack between " + attackingCountry + " and " + defendingCountry + " was successful.");
                holder.sendGameLog(player.getName() + ": the attack between "+ attackingCountry + " and " + defendingCountry + " was successful.");
                // getting the armies in defending country
                List<Player> allPlayersList;
                allPlayersList = holder.getPlayerList();
                for (int i = 0; i < allPlayersList.size(); i++) {
                    Player tmp = allPlayersList.get(i);
                    HashMap<String, Integer> countriesConqueredTmp = tmp.getCountriesConquered();
                    Iterator itForCountriesConquered = countriesConqueredTmp.entrySet().iterator();// iterator for countries conqureeed by player
                    while (itForCountriesConquered.hasNext()) {
                        Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
                        if (pair.getKey().equals(defendingCountry)) {
                            numberOfArmiesDefenderC = (int) pair.getValue();
                        }
                    }
                }
                // deleting defending country from the defender's conqueredcountry list
                deleteDefendingCountry(defendingCountry);
                System.out.println("after deleting the conquered country from defender list");
                // check if any player conqured
                Player tmp = null;
                for (int i = 0; i < allPlayersList.size(); i++) {
                    tmp = allPlayersList.get(i);
                    boolean result = checkForConqueredPlayer(tmp);
                    if (result) {
                        System.out.println("player conquered");
                        List<Player> conqueredPlayerList = holder
                                .getConqueredPlayerList();
                        conqueredPlayerList.add(tmp);
                        holder.setConqueredPlayerList(conqueredPlayerList);
                    }

                }
                // putting defending country into the attacker's conquered country list
                addCountryintoConqueredList(defendingCountry,numberOfArmiesDefenderC);
                player = holder.getActivePlayer();

                //move armies from attacking country to newly conquered country minimum number of armies to move and maximum nmber of armies to move
                int minArmies = numberOfDice;
                returnResult = minArmies;

            } else {
                System.out.println(" the attack between " + attackingCountry + " and " + defendingCountry + " was not successful. ");
                holder.sendGameLog(player.getName() + ": the attack between "+ attackingCountry + " and " + defendingCountry+ " was not successful. ");
                // change the phase
                returnResult = -1;
            }
        }

        // Is attack possible again
        List<String> attackPossibleCountries = isAnotherAttackPossible(
                countriesConquered);
        
        return returnResult;
    }

    /**
     * to calculate number of dice allowed depending upon the armies existing in
     * the country
     *
     * @param CountryName name of the country
     * @return number of dice allowed
     */
    public int calculateNoOfDiceAllowed(String CountryName) {

        int noOfDiceAllowed;
        int numberOfArmies = 0;
        Player player = holder.getActivePlayer();
        HashMap<String, Integer> countriesConquered = player
                .getCountriesConquered();
        // iterator for countries conquered by player
        Iterator itForCountriesConquered = countriesConquered.entrySet()
                .iterator();
        while (itForCountriesConquered.hasNext()) {
            Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
            if (pair.getKey().equals(CountryName)) {
                numberOfArmies = (int) pair.getValue();
            }
        }
        if (numberOfArmies > 3) {
            noOfDiceAllowed = 3;
        } else if (numberOfArmies == 3) {
            noOfDiceAllowed = 2;
        } else if (numberOfArmies == 2) {
            noOfDiceAllowed = 1;
        } else {
            noOfDiceAllowed = 0;
        }

        return noOfDiceAllowed;
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
     * This function checks if attack is successful
     *
     * @param attackingCountry the attacking country name
     * @param defendingCountry the country on which attack is done
     * @param chosenNoOfDice number of dice used by attacker
     * @return true if attack successful, false if not
     */
    public boolean attackBetweenTwoCoutries(String attackingCountry,
            String defendingCountry, int chosenNoOfDice) {
        Player player = holder.getActivePlayer();
        System.out.println("chosenNoOfDice: " + chosenNoOfDice);
        int dice = (chosenNoOfDice == 1) ? 1 : chosenNoOfDice - 1;
        if (dice < 1) {
            return false;
        }
        Integer[] diceRollValuesOfAttacker = new Integer[chosenNoOfDice];
        Integer[] diceRollValuesOfDefender = new Integer[dice];
        boolean attackstatus = false;
        int numberOfArmiesAttacker = 0;
        // armies in attacking country
        HashMap<String, Integer> countriesConquered = player
                .getCountriesConquered();
        Iterator itForCountries = countriesConquered.entrySet().iterator();// iterator for countries conqureeed by player
        while (itForCountries.hasNext()) {
            Map.Entry pair = (Map.Entry) itForCountries.next();
            if (pair.getKey().equals(attackingCountry)) {
                numberOfArmiesAttacker = (int) pair.getValue();
                System.out.println("number of armies attacker : "
                        + numberOfArmiesAttacker);
            }
        }

        int numberOfArmiesDefender = 0;
        // armies in defending country
        List<Player> allPlayersList;
        allPlayersList = holder.getPlayerList();
        for (int i = 0; i < allPlayersList.size(); i++) {
            Player tmp = allPlayersList.get(i);
            HashMap<String, Integer> countriesConqueredTmp = tmp
                    .getCountriesConquered();
            if (countriesConqueredTmp.containsKey(defendingCountry)) {
                Iterator itForCountriess = countriesConqueredTmp.entrySet().iterator();// iterator for countries conqureeed by player
                while (itForCountriess.hasNext()) {
                    Map.Entry pair = (Map.Entry) itForCountriess.next();
                    if (pair.getKey().equals(defendingCountry)) {
                        numberOfArmiesDefender = (int) pair.getValue();
                        System.out.println("number of armies defender : " + numberOfArmiesDefender);
                    }
                }
            }
        }

        // dice rolls for attacker
        if (numberOfArmiesAttacker <= 1) {
            return false;
        }
        for (int i = 0; i < chosenNoOfDice; i++) {
            diceRollValuesOfAttacker[i] = rollDice.roll();
            System.out.println(diceRollValuesOfAttacker[i]);
        }
        int temp = 0;
        // sorting the dice rolls in decreasing values
        for (int i = 0; i < chosenNoOfDice; i++) {
            for (int j = i + 1; j < chosenNoOfDice; j++) {
                if (diceRollValuesOfAttacker[i] < diceRollValuesOfAttacker[j]) {
                    temp = diceRollValuesOfAttacker[i];
                    diceRollValuesOfAttacker[i] = diceRollValuesOfAttacker[j];
                    diceRollValuesOfAttacker[j] = temp;
                }
            }
        }

        // dice rolls for defender
        for (int i = 0; i < dice; i++) {
            diceRollValuesOfDefender[i] = rollDice.roll();
            System.out.println(diceRollValuesOfDefender[i]);
        }
        temp = 0;
        // sorting the dice rolls in decreasing values
        for (int i = 0; i < dice; i++) {
            for (int j = i + 1; j < dice; j++) {
                if (diceRollValuesOfDefender[i] < diceRollValuesOfDefender[j]) {
                    temp = diceRollValuesOfDefender[i];
                    diceRollValuesOfDefender[i] = diceRollValuesOfDefender[j];
                    diceRollValuesOfDefender[j] = temp;
                }
            }
        }

        // matching the dice roll values
        for (int i = 0; i < dice; i++) {
            // if the dice value of attacker is more than dice value of defender
            if (diceRollValuesOfAttacker[i] > diceRollValuesOfDefender[i]) {
                System.out.println("dice value of attacker :"
                        + diceRollValuesOfAttacker[i]);
                System.out.println("dice value of defender :"
                        + diceRollValuesOfDefender[i]);
                // decrement armies in defending country
                decrementArmiesDefendingC(defendingCountry);
                List<Player> allPlayersList2;
                allPlayersList2 = holder.getPlayerList();
                for (int j = 0; j < allPlayersList2.size(); j++) {
                    Player tmp = allPlayersList2.get(j);
                    HashMap<String, Integer> countriesConqueredTmp = tmp
                            .getCountriesConquered();
                    Iterator itForCountriesConquered = countriesConqueredTmp
                            .entrySet().iterator();// iterator for countries
                    // conqureeed by player
                    while (itForCountriesConquered.hasNext()) {
                        Map.Entry pair = (Map.Entry) itForCountriesConquered
                                .next();
                        if (pair.getKey().equals(defendingCountry)) {
                            int numberOfArmies = (int) pair.getValue();
                            if (numberOfArmies == 0) {
                                // attack successful
                                attackstatus = true;
                            }
                        }
                    }
                }
                if (attackstatus) {
                    System.out.println(
                            "defender left with zero armeis in the country");
                    break;
                }
                System.out.println("army decremented in defender ");
            } // if the dice value of attacker is same as dice roll of defender
            else if (diceRollValuesOfAttacker
                    .equals(diceRollValuesOfDefender)) {
                System.out.println("dice value of attacker :"
                        + diceRollValuesOfAttacker[i]);
                System.out.println("dice value of defender :"
                        + diceRollValuesOfDefender[i]);
                // decrement the armies in attacking country
                System.out.println(
                        "decrement in attacking country if dice value is equal");
                Iterator itForCountriesConquered = countriesConquered.entrySet()
                        .iterator();// iterator for countries conqureeed by
                // player
                while (itForCountriesConquered.hasNext()) {
                    Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
                    if (pair.getKey().equals(attackingCountry)) {
                        int numberOfArmies = (int) pair.getValue();
                        pair.setValue(--numberOfArmies);
                    }
                }
                player.setCountriesConquered(countriesConquered);
                holder.updatePlayer(player);
                Iterator itForCountriesConquere = countriesConquered.entrySet()
                        .iterator();// iterator for countries conqureeed by
                // player
                while (itForCountriesConquere.hasNext()) {
                    Map.Entry pair = (Map.Entry) itForCountriesConquere.next();
                    if (pair.getKey().equals(attackingCountry)) {
                        int numberOfArmies = (int) pair.getValue();
                        System.out.println(
                                "decremented armies in attacking country"
                                + numberOfArmies);
                    }
                }
            } // if dice value of attacker is less then the dice value of defender
            else {
                System.out.println("dice value of attacker :"
                        + diceRollValuesOfAttacker[i]);
                System.out.println("dice value of defender :"
                        + diceRollValuesOfDefender[i]);
                // decrement the armies in attacking country
                System.out.println(
                        "decrement in attacking country if the dice value is less");
                Iterator itForCountriesConquered = countriesConquered.entrySet()
                        .iterator();// iterator for countries conqureeed by
                // player
                while (itForCountriesConquered.hasNext()) {
                    Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
                    if (pair.getKey().equals(attackingCountry)) {
                        int numberOfArmies = (int) pair.getValue();
                        pair.setValue(--numberOfArmies);

                    }
                }
                player.setCountriesConquered(countriesConquered);
                holder.updatePlayer(player);
                Iterator itForCountriesConquere = countriesConquered.entrySet()
                        .iterator();// iterator for countries conqureeed by
                // player
                while (itForCountriesConquere.hasNext()) {
                    Map.Entry pair = (Map.Entry) itForCountriesConquere.next();
                    if (pair.getKey().equals(attackingCountry)) {
                        int numberOfArmies = (int) pair.getValue();
                        System.out.println(
                                "decremented armies in attacking country"
                                + numberOfArmies);
                    }
                }
            }
        }
        // update the conquered country list of the player
        player.setCountriesConquered(countriesConquered);
        // if the defending country is left with zero countries then the country
        // is conquered, thus attcak successful
        List<Player> allPlayersList2;
        allPlayersList2 = holder.getPlayerList();
        for (int i = 0; i < allPlayersList2.size(); i++) {
            Player tmp = allPlayersList2.get(i);
            HashMap<String, Integer> countriesConqueredTmp = tmp
                    .getCountriesConquered();
            Iterator itForCountriesConquered = countriesConqueredTmp.entrySet()
                    .iterator();// iterator for countries conqureeed by player
            while (itForCountriesConquered.hasNext()) {
                Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
                if (pair.getKey().equals(defendingCountry)) {
                    int numberOfArmies = (int) pair.getValue();
                    if (numberOfArmies == 0) {
                        // attack successful
                        attackstatus = true;
                        System.out.println(
                                "defender left with zero armeis in the country");
                    }
                }
            }
        }

        Iterator itForCountriesConquered = countriesConquered.entrySet()
                .iterator();// iterator for countries conqureeed by player
        while (itForCountriesConquered.hasNext()) {
            Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
            if (pair.getKey().equals(defendingCountry)) {
                int numberOfArmies = (int) pair.getValue();
                if (numberOfArmies == 0) {
                    // attack successful
                    attackstatus = true;
                    System.out.println(
                            "defender left with zero armeis in the country");
                }
            }
        }
        holder.updatePlayer(player);
        // return the attack success status
        return attackstatus;
    }

    /**
     * This method is used to delete the defending country from the defending
     * player's conquered country list
     *
     * @param defendingCountry the country on which attack is taking place
     */
    public void deleteDefendingCountry(String defendingCountry) {
        List<Player> allPlayersList;
        allPlayersList = holder.getPlayerList();
        for (int i = 0; i < allPlayersList.size(); i++) {
            Player tmp = allPlayersList.get(i);
            HashMap<String, Integer> countriesConqueredTmp = tmp
                    .getCountriesConquered();
            if (countriesConqueredTmp.containsKey(defendingCountry)) {
                countriesConqueredTmp.remove(defendingCountry);
                tmp.setCountriesConquered(countriesConqueredTmp);
                holder.updatePlayer(tmp);
            }
        }
        System.out.println("Deleting defending country: " + defendingCountry);
    }

    public int getArmiesOfDefendingCountry(String defendingCountry) {
        List<Player> allPlayersList = holder.getPlayerList();

        for (int i = 0; i < allPlayersList.size(); i++) {
            Player tmp = allPlayersList.get(i);
            HashMap<String, Integer> countriesConqueredTmp = tmp
                    .getCountriesConquered();
            if (countriesConqueredTmp.containsKey(defendingCountry)) {
                return countriesConqueredTmp.get(defendingCountry);
            }
        }

        return -1;
    }

    /**
     * This method is used to add country in conquered country list
     *
     * @param defendingCountry the country on which attack is taking place
     * @param numberOfArmiesDefenderC the number of armies in the country
     */
    public void addCountryintoConqueredList(String defendingCountry,
            int numberOfArmiesDefenderC) {
        Player player = holder.getActivePlayer();
        HashMap<String, Integer> countriesConquered = player.getCountriesConquered();
        countriesConquered.put(defendingCountry, numberOfArmiesDefenderC);
        player.setCountriesConquered(countriesConquered);
        holder.updatePlayer(player);
    }

    /**
     * this method decrements the number of armies a defending country
     *
     * @param defendingCountry the country on which attack is done
     */
    public void decrementArmiesDefendingC(String defendingCountry) {
        System.out.println("decrementing armies defender country");
        // taking al player
        List<Player> allPlayersList;
        allPlayersList = holder.getPlayerList();
        // for each player in the list
        for (int i = 0; i < allPlayersList.size(); i++) {
            Player temp = allPlayersList.get(i);
            // get a particular player's conquered country list
            HashMap<String, Integer> countriesConqueredTmp = temp
                    .getCountriesConquered();
            Iterator itForCountriesConquered = countriesConqueredTmp.entrySet()
                    .iterator();// iterator for countries conqureeed by player
            while (itForCountriesConquered.hasNext()) {
                Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
                // if the player has the defending country in the conquered
                // country list
                if (pair.getKey().equals(defendingCountry)) {
                    int numberOfArmies = (int) pair.getValue();
                    pair.setValue(--numberOfArmies);
                }
            }
            // update the players conquered country list
            temp.setCountriesConquered(countriesConqueredTmp);
            holder.updatePlayer(temp);
        }
        // for printing the decremented army
        for (int i = 0; i < allPlayersList.size(); i++) {
            Player temp = allPlayersList.get(i);
            // get a particular player's conquered country list
            HashMap<String, Integer> countriesConqueredTmp = temp
                    .getCountriesConquered();
            Iterator itForCountriesConquered = countriesConqueredTmp.entrySet()
                    .iterator();// iterator for countries conqureeed by player
            while (itForCountriesConquered.hasNext()) {
                Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
                // if the player has the defending country in the conquered
                // country list
                if (pair.getKey().equals(defendingCountry)) {
                    int numberOfArmies = (int) pair.getValue();
                    System.out.println("value after update:" + numberOfArmies);
                }
            }
        }
    }

    /**
     * Move armies from one country to another after attack
     *
     *
     * @param selectedNoOfArmies number of armies to be moved
     * @param attackingCountry Attacking country
     * @param defendingCountry Defending country
     */
    public void moveArmies(int selectedNoOfArmies, String attackingCountry,
            String defendingCountry) {
        Player player = holder.getActivePlayer();
        HashMap<String, Integer> countriesConquered = player
                .getCountriesConquered();
        Iterator itForCountriesConquered = countriesConquered.entrySet()
                .iterator();
        while (itForCountriesConquered.hasNext()) {
            Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
            if (pair.getKey().equals(defendingCountry)) {
                int numberOfArmies = (int) pair.getValue();
                // armies added to the conquered Country
                pair.setValue(numberOfArmies + selectedNoOfArmies);
            }
            // armeis deducted from the attacking country
            if (pair.getKey().equals(attackingCountry)) {
                int numberOfArmies = (int) pair.getKey();
                pair.setValue(numberOfArmies - selectedNoOfArmies);
            }
        }
        holder.updatePlayer(player);
    }

    /**
     * check if any player conquered in this phase?
     *
     * @param player Player Object.
     * @return true if conquered and false if not
     */
    public boolean checkForConqueredPlayer(Player player) {

        boolean result = false;

        // get a particular player's conquered country list
        HashMap<String, Integer> countriesConqueredTmp = player
                .getCountriesConquered();
        if (countriesConqueredTmp.size() == 0) {
            // player is conquered
            result = true;

        }

        return result;
    }

    /**
     * check if another attack is possible for the same player
     *
     * @param countriesConquered list of conquered countries
     * @return list of countries that can attack
     */
    public List<String> isAnotherAttackPossible(
            HashMap<String, Integer> countriesConquered) {

        List<String> countriesPossible = new ArrayList<>();
        Iterator itForCountriesConquered = countriesConquered.entrySet()
                .iterator();
        while (itForCountriesConquered.hasNext()) {
            Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
            String countryName = (String) pair.getKey();
            int numberOfArmies = (int) pair.getValue();
            if (numberOfArmies >= 2) {
                countriesPossible.add(countryName);
            }
        }
        return countriesPossible;
    }

    /**
     * The list of neighbours that are not conquered Get the potential defenders
     *
     * @param attackingCountry name of the attacking country
     * @return List of neighbours
     */
    public List<String> getNeighboursForAttack(String attackingCountry) {
        Player player = holder.getActivePlayer();
        // get the list of neighbouring countries of the attacking country
        List<String> CountryNeighbours = new ArrayList<>();
        CountryNeighbours = getNeighbours(attackingCountry);
        // retrieving the countries conquered by the player
        HashMap<String, Integer> countriesConquered = player
                .getCountriesConquered();
        // list of neighbours attacking country can attack - neighbouring
        // countries that are not already conquered
        List<String> tempCountryNeighbours = new ArrayList<>();
        for (int i = 0; i < CountryNeighbours.size(); i++) {
            if (!countriesConquered.containsKey(CountryNeighbours.get(i))) {
                tempCountryNeighbours.add(CountryNeighbours.get(i));
            }
        }
        return tempCountryNeighbours;
    }
}
