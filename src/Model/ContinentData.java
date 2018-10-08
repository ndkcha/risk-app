/**
 * 
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The continent will hold the information about continents
 * It shall hold only the name and the control value
 * @author ndkcha, jay
 *
 */
public class ContinentData {
    private String name;
    private int controlValue;


	public ContinentData(String name, int controlValue) {
		this.name = name;
		this.controlValue = controlValue;
	}

    public String getName() {
        return name;
    }

    public int getControlValue() {
        return controlValue;
    }
//    public ArrayList<CountryData> getCountries(){
//        return countries;
//    }
//
//    public void setCountries(ArrayList<CountryData> countries) {
//        this.countries = countries;
//    }
    
    
}
