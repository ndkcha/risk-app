package Game.Model;

import java.io.Serializable;

/**
 * The basic details about the map
 * 
 * @author Jay, ndkcha, gunvansh
 * @version 1.2.0
 */
public class MapData implements Serializable {
	
	public String author, imageFileName, scrollType, mapFileName;
	public String imageFilePath, mapFilePath;
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
