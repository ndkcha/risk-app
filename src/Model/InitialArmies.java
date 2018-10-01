/**
 * 
 */
package Model;

/**
 * Initial Number of armies based on Number of players.
 * 2 players 40
 * 3 players 35
 * 4 players 30
 * 5 players 25
 * 6 players 20
 * 
 * @author Jay
 *
 */
public enum InitialArmies {

	InitialArmiesCount{
		public int getArmiesCount(int numPlayer){
			int armies = 40 - ((numPlayer -2) * 5);
			
			return armies;
		}
	};
	
	public abstract int getArmiesCount(int numPlayer);

}
