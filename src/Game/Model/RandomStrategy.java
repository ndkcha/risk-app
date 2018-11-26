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
public class RandomStrategy implements PlayerStrategy{

    /**
     * 
     * @param armiesToAllocate
     * @param country
     * @return 
     */
    @Override
    public String reinforcementPhase(int armiesToAllocate, String country) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @return 
     */
    @Override
    public int attackPhase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param armiesToMove 
     */
    @Override
    public void moveArmiesAfterAttack(int armiesToMove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * 
     * @param sourceCountry
     * @param targetCountry
     * @param noOfArmies
     * @return 
     */
    @Override
    public String fortificationPhase(String sourceCountry, String targetCountry, int noOfArmies) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
