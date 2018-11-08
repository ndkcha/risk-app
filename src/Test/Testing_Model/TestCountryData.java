package Test.Testing_Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import Game.Model.CountryData;

public class TestCountryData {

	private CountryData country;
	private String Name;
	private String Continent;
	private double Latitude;
	private double Longtitude;

	@Before
	public void setup() {

		Name = "India";
		Continent = "Asia";
		Latitude = 45.6;
		Longtitude = 102.45;
		country = new CountryData(Name, Latitude, Longtitude, Continent);

	}

	@Test
	public void testgetname() {

		System.out.println("testing the " + Name + "in method getname ");
		assertEquals(Name, country.getName());

	}

	@Test
	public void testcontinent() {

		System.out.println(
				"testing the " + Continent + "in method getcontinent ");
		assertEquals(Continent, country.getContinent());

	}

	@Test
	public void testLongitutde() {

		System.out.println(
				"testing the " + Longtitude + "in method getlongitude ");
		assertTrue(Longtitude == country.getLongitude());

	}

	@Test
	public void testLatitude() {

		System.out
				.println("testing the " + Latitude + "in method getlatitude ");
		assertTrue(Latitude == country.getLatitude());

	}

}
