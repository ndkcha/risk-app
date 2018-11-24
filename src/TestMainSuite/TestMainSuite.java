package TestMainSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Test.TestSuiteController;
import Test.TestSuiteModel;
import Test.Testing_Controller.TestFortificationController;
import Test.Testing_Controller.TestMapEditorController;
import Test.Testing_Controller.TestMapReader;
import Test.Testing_Controller.TestMapEditorController;
import Test.Testing_Controller.TestNumberofInitialArmies;
import Test.Testing_Controller.TestReinforcementController;
/**
 * This Test Suite calls   the test suite of Model and Controller.
 */
@RunWith(Suite.class)
@SuiteClasses({TestSuiteController.class , TestSuiteModel.class})
 





public class TestMainSuite {
	
	

}
