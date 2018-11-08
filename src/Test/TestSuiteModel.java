package Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Test.Testing_Controller.TestMapValidation;
import Test.Testing_Controller.Test_AssignArmies;
import Test.Testing_Controller.Test_StartupController;
import Test.Testing_Model.TestContinentData;
import Test.Testing_Model.TestCountryData;
import Test.Testing_Model.TestPlayer_Cards;
import Test.Testing_Model.TestRollDice;
import Test.Testing_Model.TestPlayerPhase;

/**
 * This Test Suite calls   tall test classes of  model.
 */
@RunWith(Suite.class)
@SuiteClasses({TestMapValidation.class,
	TestPlayerPhase.class,
	TestPlayer_Cards.class,
	TestRollDice.class,
	Test_AssignArmies.class,
	Test_StartupController.class,
	TestContinentData.class , 
	TestCountryData.class })
 
 

public class TestSuiteModel {

}
