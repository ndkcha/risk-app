package Test.Testing_Model;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import Game.Model.*;
import Game.Risk.DataHolder;

import java.util.ArrayList;
import java.util.HashMap;


public class TestDataholder {

	Player testplayer;
	private DataHolder holder = DataHolder.getInstance();
	
	@Before
	public void testbefore() {

		 HashMap<String, Integer> conqueredCountryList=new HashMap<String,Integer>();
		 
		 conqueredCountryList.put("a", 1);
		 conqueredCountryList.put("b", 2);
		 conqueredCountryList.put("c", 3);
		 conqueredCountryList.put("d", 4);
		 conqueredCountryList.put("e",5);
		 conqueredCountryList.put("f", 1);
		 conqueredCountryList.put("g", 1); 
		 conqueredCountryList.put("h", 1);
		 
		 int con=conqueredCountryList.size();

		 Player testplayer=new Player("abc",0,"blue");
		 
		 testplayer.updateCountry("a", 1);
		 testplayer.updateCountry("b", 2);
		 testplayer.updateCountry("c", 3);
		 testplayer.updateCountry("d", 4);
		 testplayer.updateCountry("e", 5);
		 testplayer.updateCountry("f", 1);
		 
	}
	
	 @Test
	 public void testhasplayerwon() {
		 
		 Boolean b=holder.hasPlayerWon(testplayer);
		 assertTrue(b);
		 holder.clearDataHolder();
		
	 }
	
	
}
