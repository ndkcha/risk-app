package Risk;

import java.io.Serializable;

/**
* The game settings.
*
* @author  Jatin Rupeja
* @version 1.0
*/
public class GameSettings implements Serializable {

	/** Creates a new instance of GameSettings */
    public int num_opponents=1;
    public int match_type=1;
    public int territories_home=1;
    public int initials_troops=1;
    public int difficulty=1;
    
    public int comp_player1=1;//1 for human player and 2 for computer
    public int comp_player2=2;
    public int comp_player3=2;
    public int comp_player4=2;
    public int comp_player5=2;
    public int comp_player6=2;
    
	public GameSettings() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
