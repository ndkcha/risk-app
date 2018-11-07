package Test;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Test.Testing_Controller.*;

/**
 * This Test Suite calls   tall test classes of  Controller.
 */
@RunWith(Suite.class)
@SuiteClasses({TestGameplayValidations.class , TestMapValidation.class })
 
 
public class TestSuiteController {

}
