/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Model;

/**
 * Methods to implement the Aggressive strategy
 * @author r-naik
 */
public class AggressiveStrategy implements PlayerStrategy{

    /**
     * This method implements the reinforcement phase 
     * @param armiesToAllocate number of armies to allocate during the phase
     * @param country country name to which the armies to be added
     * @return message of successful allocation
     */
    @Override
    public String reinforcementPhase(int armiesToAllocate, String country) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method implements attack
     * @return number of armies to be moved
     */
    @Override
    public int attackPhase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is to move armies from one country to another
     * @param armiesToMove number of armies to move
     */
    @Override
    public void moveArmiesAfterAttack(int armiesToMove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method implements the fortification phase
     * @param sourceCountry source country name from which armies to be moved
     * @param targetCountry destination country name to which armies to be moved
     * @param noOfArmies number of armies to be moved
     * @return message of successful fortification
     */
    @Override
    public String fortificationPhase(String sourceCountry, String targetCountry, int noOfArmies) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
