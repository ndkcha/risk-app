package Game.Model;

import java.util.ArrayList;

/**
 * We'll keep the territory details on the country class. it will hold the list
 * of neighbours, assigned continent, and the coordinates it won't need id,
 * because the name is the primary index for the map file.
 * 
 * @author jay, ndkcha, gunvansh
 * @version 1.2.0
 */
public class CountryData {
	private String name, continent;
	private double latitude, longitude; // x and y of the coordinate system  respectively
	private ArrayList<String> neighbours;

	/**
	 * 
	 * Initializes the country data
	 * 
	 * @param name Name of the country
	 * @param latitude X coordinate of the map system
	 * @param longitude Y coordinate of the map system
	 * @param continent The continent it belongs into
	 */
	public CountryData(String name, double latitude, double longitude,
			String continent) {
		this.name = name;
		this.continent = continent;
		this.latitude = latitude;
		this.longitude = longitude;
		this.neighbours = new ArrayList<>();
	}

	/**
	 * Set up values of the country after the country is initialized. It is
	 * mainly used to update the existing country's details.
	 * 
	 * @param name of the country
	 * @param latitude The X coordinate of the country
	 * @param longitude The Y coordinate of the country
	 * @param continent The continent in which the country is
	 */
	public void setValues(String name, double latitude, double longitude,
			String continent) {
		this.name = name;
		this.continent = continent;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Remove a neighboring country
	 * 
	 * @param name Name of the country
	 */
	public void removeNeighbour(String name) {
		this.neighbours.remove(name);
	}

	/**
	 * Add a neighboring country
	 * 
	 * @param name Name of the country
	 */
	public void addNeighbour(String name) {
		this.neighbours.add(name);
	}

	/**
	 * Get the name
	 * 
	 * @return name String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * get the continent
	 * 
	 * @return continent
	 */
	public String getContinent() {
		return continent;
	}

	/**
	 * Get the Latitutde method
	 * 
	 * @return latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Get the Longitude method
	 * 
	 * @return longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Get the neibhours method
	 * 
	 * @return neibhours list
	 */
	public ArrayList<String> getNeighbours() {
		return neighbours;
	}
	
}
