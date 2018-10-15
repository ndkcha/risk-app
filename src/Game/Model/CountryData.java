package Game.Model;


import java.util.ArrayList;

/**
 * We'll keep the territory details on the country class.
 * it will hold the list of neighbours, assigned continent, and the coordinates
 * it won't need id, because the name is the primary index for the map file.
 * @author jay, ndkcha, gunvansh
 *
 */
public class CountryData {
    private String name, continent;
    private double latitude, longitude; // x and y of the coordinate system respectively
    private ArrayList<String> neighbours;

    /**

     * Initializes the country data
     * @param name name of the country
     * @param latitude X coordinate of the map system
     * @param longitude Y coordinate of the map system
     * @param continent the continent it belongs into
     */
	public CountryData(String name, double latitude, double longitude, String continent) {
        this.name = name;
        this.continent = continent;
        this.latitude = latitude;
        this.longitude = longitude;
        this.neighbours = new ArrayList<>();
	}

    /**
     * Set up values of the country after the country is initialized.
     * It is mainly used to update the existing country's details.
     * @param name name of the country
     * @param latitude the X coordinate of the country
     * @param longitude the Y coordinate of the country
     * @param continent the continent in which the country is
     */
	public void setValues(String name, double latitude, double longitude, String continent) {
        this.name = name;
        this.continent = continent;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Remove a neighbouring country
     * @param name name of the country
     */
    public void removeNeighbour(String name) {
	    this.neighbours.remove(name);
    }

    /**
     * Add a neighbouring country
     * @param name name of the country
     */
	public void addNeighbour(String name) {
	    this.neighbours.add(name);
    }
/**
     * get the name
     * @return name string name
     * 
     */
	public String getName() {
	    return name;
    }
/**
     * get the continent 
     * @return continent  
     * 
     */
    public String getContinent() {
        return continent;
    }
/**
     * get the Latitutde method 
     * @return latitude 
     * 
     */
    public double getLatitude() {
        return latitude;
    }
/**
     * get the Longitude method 
     * @return longitude 
     * 
     */
    public double getLongitude() {
        return longitude;
    }
/**
     * get the neibhours method
     * @return neibhours list 
     * 
     */
    public ArrayList<String> getNeighbours() {
        return neighbours;
    }
}
