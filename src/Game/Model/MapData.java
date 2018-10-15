package Game.Model;

/**
 * The basic details about the map
 * 
 * @author Jay, ndkcha, gunvansh
 * @version 1.0.0
 */
public class MapData {
	
	public String author, imageFileName, scrollType, mapFileName;
	public boolean wrap, warn;

	public MapData() {
		wrap = false;
		warn = false;
	}

	/**
	 * cleanUpMapData method
	 */
	public void cleanUpMapData() {
		this.mapFileName = "";
		this.author = "";
		this.imageFileName = "";
		this.scrollType = "";
		this.wrap = false;
		this.warn = false;
	}
}
