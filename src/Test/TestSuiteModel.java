package Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Test.Testing_Model.TestContinentData;
import Test.Testing_Model.TestCountryData;
import Test.Testing_Model.TestPlayer_Cards;
import Test.Testing_Model.TestRollDice;

/**
 * This Test Suite calls   tall test classes of  model.
 */
@RunWith(Suite.class)
@SuiteClasses({TestContinentData.class , TestCountryData.class, TestPlayer_Cards.class, TestRollDice.class })
 





 

public class TestSuiteModel {

}
