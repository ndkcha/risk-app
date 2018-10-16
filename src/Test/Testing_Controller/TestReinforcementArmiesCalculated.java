/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Testing_Controller;

import Game.Controller.ReinforcementController;

import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

import Game.Controller.FortificationController;
import Game.Model.ContinentData;
import Game.Model.CountryData;
import Game.Risk.DataHolder;

/**
 * Check if armies are calculated correctly
 * @author r-naik
 */
public class TestReinforcementArmiesCalculated {
    ReinforcementController rc;
    public HashMap<String, Integer> countriesConquered = new HashMap<String, Integer>();;
    private DataHolder holder = DataHolder.getInstance();
    
    @Before public void beforeTest(){
        ContinentData continentData= new ContinentData("Cockpit", 5);
        CountryData country1 = new CountryData("Cockpit01",658.0,355.0,"Cockpit");
	CountryData country2 = new CountryData("Cockpit02",558.0,255.0,"Cockpit");
	CountryData country3 = new CountryData("Cockpit03",758.0,155.0,"Cockpit");
	holder.addCountry(country1);
	holder.addCountry(country2);
	holder.addCountry(country3);
    }
    
    @Test
    public void testCalculateReinforcementArmies() {
        
        int expected = 3;
        int numberOfArmies=rc.calculateReinformentArmies(1);
        System.out.println("number of armies: "+numberOfArmies);
        assertEquals(expected, numberOfArmies);
    }
}
