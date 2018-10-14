package Model;

/**
 * The basic details about the map
 * @author Jay, ndkcha
 */
public class MapData {
    public String author, imageFileName, scrollType;
    public boolean wrap, warn;

	public MapData() {
		wrap = false;
		warn = false;
	}

	public void cleanUpMapData() {
	    this.author = "";
	    this.imageFileName = "";
	    this.scrollType = "";
	    this.wrap = false;
	    this.warn = false;
    }
}
