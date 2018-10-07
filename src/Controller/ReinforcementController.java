
package Controller;

import java.util.*;
import Model.CountryData;
import Model.ContinentData;
import Risk.DataHolder;
import Model.Player; 

/**
 * This controller class is for the reinforcement phase
 * @author r-naik
 */
public class ReinforcementController {
    
    private DataHolder holder =DataHolder.getInstance();
    private Player player;
    private List<Player> p=this.holder.getPlayerList();
    
    /**
     * This function calculates the armies a player avails in each reinforcement phase
     * @param playerNumber The identity of the player to which armies is assigned
     */
    void calculateReinformentArmies(int playerNumber) {
        System.out.println("Calculating armies for player "+playerNumber);
        
        //checking if player has all countries of the continents.
        
        
        // number of countries owned divided by 3 and rounded down
        player=p.get(playerNumber-1);
        float newarmies=Math.floorDiv((player.getCountriesConquered().size()), 3);
        System.out.println("The number of armies available for reinforcement phase is "+newarmies);
        
        
        //updating the total number of armies
        
        
      
    }
    
    
    
    
    
}
