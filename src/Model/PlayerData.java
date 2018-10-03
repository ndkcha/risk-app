/**
 * 
 */
package Model;

/**
 * This class represents a players id, name, country owned, armies details.
 * 
 * @author Jay
 *
 */
public class PlayerData {

	public static String[][] playerDataArray;
	
	public void setGamePlayerData(String[][] anArrayOfStrings)
    {
		playerDataArray = anArrayOfStrings;
    }
 
    public static String[][] getGamePlayerData()
    {
        return playerDataArray;
    }
    
	/**
	 * 
	 */
//	public PlayerData(String playerName, String playerColor, String playerType) {
//		// TODO Auto-generated constructor stub
//		
//	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
