/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Model;

import java.util.List;

/**
 * This class sets the strategy
 * @author r-naik
 */
public class StrategySetter {
     private PlayerStrategy strategy;
     
     public void setStrategy(PlayerStrategy strategy) {
         this.strategy=strategy;
     }
     public String reinforcement() {
         return this.strategy.reinforcementPhase(0, null);
     }
     
     public List<String> attack() {
         return this.strategy.attackPhase();
     }
     
     public String fortify() {
         return this.strategy.fortificationPhase(null, null, 0);
     }
    
}
