package Test;

import Test.Testing_Model.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Test.Testing_Controller.TestMapEditorController;
import Test.Testing_Controller.Test_AssignArmies;
import Test.Testing_Controller.TestStartupController;

/**
 * This Test Suite calls   tall test classes of  model.
 */
@RunWith(Suite.class)
@SuiteClasses({TestMapEditorController.class,
	TestPlayerPhase.class,
	TestPlayerCards.class,
	TestRollDice.class,
	Test_AssignArmies.class,
	TestStartupController.class,
	TestContinentData.class , 
	TestCountryData.class,
	TestPlayerModel.class})
 
 

public class TestSuiteModel {

}
