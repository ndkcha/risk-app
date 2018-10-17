package Game.Model;

/**
 * The continent will hold the information about continents It shall hold only
 * the name and the control value
 * 
 * @author ndkcha, jay
 * @version 1.0.0
 */
public class ContinentData {
	
	private String name;
	private int controlValue;

	/**
	 * This constructor will set the name and control value of an constructor.
	 * 
	 * @param name The name of the continent.
	 * @param controlValue The control value of continent.
	 */
	public ContinentData(String name, int controlValue) {
		this.name = name;
		this.controlValue = controlValue;
	}

	/**
	 * Returns the name of continent.
	 * 
	 * @return name Name of the continent.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return the Control Value of the continent.
	 * 
	 * @return controlValue The control value of continent.
	 */
	public int getControlValue() {
		return controlValue;
	}

}
