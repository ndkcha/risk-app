package Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Test.Testing_Model.*;
import Test.Testing_View.*;
import Test.Testing_Controller.*;

/**
 * This Test Suite calls   tall test classes of  model.
 */
@RunWith(Suite.class)
@SuiteClasses({
	TestAttackController.class,
	TestFortificationController.class,
	TestMapEditorController.class,
	TestReinforcementController.class,
	TestStartupController.class,
	
	TestContinentData.class , 
	TestCountryData.class,
	TestPlayerCards.class,
	TestPlayerModel.class,
	TestPlayerPhase.class,
	TestRollDice.class,
	
	TestGameSettingsView.class})
 
 
public class TestSuiteModel {

}
