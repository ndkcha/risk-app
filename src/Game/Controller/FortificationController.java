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
import java.util.Random;
import java.util.Set;

/**
 * This controller class performs tasks of fortification phase
 *
 * @author r-naik, ndkcha
 * @version 1.2.0
 */
public class FortificationController {

	private DataHolder holder = DataHolder.getInstance();

	/**
	 * Get neighbours of specific country
	 *
	 * @param countryName
	 *            name of the country
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
	 * This method checks if countries are connected.
	 * 
	 * @param transferingCountry
	 *            From country
	 * @param destinationCountry
	 *            To Country
	 * @param countriesConquered
	 *            All the countries from player
	 * @return connected Boolean True/False
	 */
	public boolean checkIfConnected(String transferingCountry,
			String destinationCountry,
			HashMap<String, Integer> countriesConquered) {
		// checing of countries are connected or not
		ArrayList<String> visitedCountries = new ArrayList<>();
		String tempCountry = transferingCountry;
		List<String> tempCountryNeighbours = new ArrayList<>();

		boolean connected = false;
		int tempCounter = 0; // to restrict loops when having deadlock

		while (connected == false) {
			// to restrict the deadlock and to get break the loop
			if (tempCounter == 10) {
				break;
			}
			// if the destination country is in visited list means the country
			// is connected
			if (visitedCountries.contains(destinationCountry)) {
				connected = true;
				break;
			}

			tempCountryNeighbours = getNeighbours(tempCountry);
			// if the selected country is a conquered country
			if (countriesConquered.keySet().contains(tempCountry)) {
				// if the neighbouring countries have destination country
				if (tempCountryNeighbours.contains(destinationCountry)) {
					visitedCountries.add(destinationCountry);
					connected = true;
					break;
				} else {
					// add to visited list if country not found
					visitedCountries.add(tempCountry);
				}
				// assigning new country to check if connected
				for (int i = 0; i < tempCountryNeighbours.size(); i++) {
					if (!visitedCountries
							.contains(tempCountryNeighbours.get(i))) {
						tempCountry = tempCountryNeighbours.get(i);
						break;
					}
				}
			} else {
				// if the country is not a conquered country the get a new temp
				// country
				for (int i = 0; i < tempCountryNeighbours.size(); i++) {
					if (!visitedCountries
							.contains(tempCountryNeighbours.get(i))) {
						tempCountry = tempCountryNeighbours.get(i);
						break;
					}
				}
			}
			tempCounter++;
		}
		return connected;
	}

}
