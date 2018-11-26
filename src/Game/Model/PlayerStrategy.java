/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Model;

/**
 *
 * @author Admin
 */
public interface PlayerStrategy {
    /**
     * 
     * @param armiesToAllocate
     * @param country
     * @return 
     */
    String reinforcementPhase(int armiesToAllocate, String country);
    
    /**
     * 
     * @return 
     */
    int attackPhase();
    
    /**
     * 
     * @param armiesToMove 
     */
    void moveArmiesAfterAttack(int armiesToMove);
    
    /**
     * 
     * @param sourceCountry
     * @param targetCountry
     * @param noOfArmies
     * @return 
     */
    String fortificationPhase(String sourceCountry, String targetCountry, int noOfArmies);
}
