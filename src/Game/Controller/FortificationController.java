/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Controller;

import Game.Model.Player;
import Game.Risk.DataHolder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Game.Model.CountryData;
import Game.Model.ContinentData;
import java.util.ArrayList;
import java.util.Set;

/**
 * This controller class performs tasks of fortification phase
 *
 * @author r-naik
 */
public class FortificationController {

    private DataHolder holder = DataHolder.getInstance();
    private Player player;
    private List<Player> p = this.holder.getPlayerList();

    /**
     * This function initializes the fortification phase for each player
     *
     * @param playerTurn The identity of the player
     */
    void fortification(int playersTurn) {

        Scanner scanner = new Scanner(System.in);
        String transferingCountry, destinationCountry;
        List<CountryData> countryDataList = holder.getCountryDataList();
        int noOfArmies = 0; //armies to be transferred

        //retrieving the player number whose turn is goin on
        player = p.get(playersTurn - 1);
        //retrieving the continents conquered by the player
        HashMap<String, Integer> countriesConquered = player.getCountriesConquered();
        System.out.println("The countries conquered by " + player.getName() + " is " + countriesConquered.keySet());
        //taking input of two countries between which arnies have to be transfered
        System.out.println("Enter the country name from which armies to be transfered: ");
        transferingCountry = scanner.nextLine();
        System.out.println("Enter the country name to which armies to be transfered: ");
        destinationCountry = scanner.nextLine();

        int existingArmiesA=0; //existing armies with transfereing country
        int existingArmiesB=0; // existing armies with destination country
        Iterator itForCountriesConquered = countriesConquered.entrySet().iterator();//iterator for countries conqureeed by player
        while (itForCountriesConquered.hasNext()) {
            Map.Entry pair = (Map.Entry) itForCountriesConquered.next();
            String countryName = (String) pair.getKey();
            if(countryName.equals(transferingCountry)){
                existingArmiesA=(int) pair.getValue();
            }
            if(countryName.equals(destinationCountry)){
                existingArmiesB=(int) pair.getValue();
            }
        }
        System.out.println("Number of armies in country " + transferingCountry + " are: "+existingArmiesA);
        System.out.println("Enter the armies to be transfered:");
        noOfArmies = scanner.nextInt();

        
        //check if countries connected or not
        boolean checkIfConnected=checkIfConnected(transferingCountry,destinationCountry,countriesConquered);
        if(checkIfConnected==true){
            //check if transfering countries have more than 2 armies
            if(existingArmiesA<=2){
                System.out.println("Cannot perform fortifiation. A transfering country should have minimum two armies");
            }
            else{
                //check if number of armies to transfered is atleast one less than the existing armies in transfering country
                if(noOfArmies<=(existingArmiesA-1)){
                    existingArmiesB=existingArmiesB+noOfArmies; //adding armies to destination country
                    existingArmiesA=existingArmiesA-noOfArmies; //subtracting armies from transfering country
                    
                    //update the armies in conquered countries list
                    Iterator iteratorCountries = countriesConquered.entrySet().iterator(); //iterator for countries conqureeed by player
                    while (iteratorCountries.hasNext()) {
                        Map.Entry pair = (Map.Entry) iteratorCountries.next();
                        String countryName = (String) pair.getKey();
                        if (countryName.equals(transferingCountry)) {
                            pair.setValue(existingArmiesA);
                        }
                        if (countryName.equals(destinationCountry)) {
                            pair.setValue(existingArmiesB);
                        }
                    }
                    player.setCountriesConquered(countriesConquered);
                    //The updated conquered countries list
                    System.out.println("The updated counquered country list");
                    for (Map.Entry<String, Integer> country : player.getCountriesConquered().entrySet()) {
                        System.out.print(country.getKey() + " - " + country.getValue() + " | ");
                    }
                }
                else{
                    System.out.println("not enough armies in transfering country");
                }
            }
        }
        else {
            System.out.println("Cannot perform fortification, countries not connected");
        }
    }

    /**
     * Get neighbours of specific country
     *
     * @param country
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

    private boolean checkIfConnected(String transferingCountry, String destinationCountry, HashMap<String, Integer> countriesConquered) {
        //checing of countries are connected or not
        ArrayList<String> visitedCountries = new ArrayList<>();
        String tempCountry = transferingCountry;
        List<String> tempCountryNeighbours = new ArrayList<>();

        boolean connected = false;
        int tempCounter = 0; //to restrict loops when having deadlock

        while (connected == false) {
            //to restrict the deadlock and to get break the loop
            if (tempCounter == 10) {
                System.out.println("countries not connected");
                break;
            }
            //if the destination country is in visited list means the country is connected
            if (visitedCountries.contains(destinationCountry)) {
                connected = true;
                break;
            }

            tempCountryNeighbours = getNeighbours(tempCountry);
            //if the selected country is a conquered country
            if (countriesConquered.keySet().contains(tempCountry)) {
                //if the neighbouring countries have destination country
                if (tempCountryNeighbours.contains(destinationCountry)) {
                    visitedCountries.add(destinationCountry);
                    System.out.println("country found 2 ");
                    connected = true;
                    break;
                } else {
                    //add to visited list if country not found
                    visitedCountries.add(tempCountry);
                    System.out.println("visited country " + tempCountry);
                }
                //assigning new country to check if connected
                for (int i = 0; i < tempCountryNeighbours.size(); i++) {
                    if (!visitedCountries.contains(tempCountryNeighbours.get(i))) {
                        tempCountry = tempCountryNeighbours.get(i);
                        System.out.println("new temp country " + tempCountry);
                        break;
                    }
                }
            } else {
                //if the country is not a conquered country the get a new temp country
                for (int i = 0; i < tempCountryNeighbours.size(); i++) {
                    if (!visitedCountries.contains(tempCountryNeighbours.get(i))) {
                        tempCountry = tempCountryNeighbours.get(i);
                        System.out.println("new temp country " + tempCountry);
                        break;
                    }
                }
            }
            tempCounter++;
        }
        return connected;
    }

}
