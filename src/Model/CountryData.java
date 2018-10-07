/**
 * 
 */
package Model;


import java.util.ArrayList;

/**
 * We'll keep the territory details on the country class.
 * it will hold the list of neighbours, assigned continent, and the coordinates
 * it won't need id, because the name is the primary index for the map file.
 * @author jay, ndkcha
 *
 */
public class CountryData {
    private String name, continent;
    private int latitude, longitude; // x and y of the coordinate system respectively
    private ArrayList<String> neighbours;

    /**

     * Initializes the country data
     * @param name name of the country
     * @param latitude X coordinate of the map system
     * @param longitude Y coordinate of the map system
     * @param continent the continent it belongs into
     */
	public CountryData(String name, int latitude, int longitude, String continent) {
        this.name = name;
        this.continent = continent;
        this.latitude = latitude;
        this.longitude = longitude;
        this.neighbours = new ArrayList<>();
	}

	public void addNeighbour(String name) {
	    this.neighbours.add(name);
    }

	public String getName() {
	    return name;
    }

    public String getContinent() {
        return continent;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public ArrayList<String> getNeighbours() {
        return neighbours;
    }
}
