package Test;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Test.Testing_Controller.TestFortificationCountryConnected;
import Test.Testing_Controller.TestMapEditorController;
import Test.Testing_Controller.TestMapReader;
import Test.Testing_Controller.TestMapValidation;
import Test.Testing_Controller.TestNumberofInitialArmies;
import Test.Testing_Controller.TestReinforcementArmiesCalculated;
/**
 * This Test Suite calls   tall test classes of  Controller.
 */
@RunWith(Suite.class)
@SuiteClasses({TestFortificationCountryConnected.class , TestMapEditorController.class, TestMapReader.class, TestMapValidation.class, TestNumberofInitialArmies.class, TestReinforcementArmiesCalculated.class })
 





 
public class TestSuiteController {

}
