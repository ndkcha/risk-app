package Test.Testing_Controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.File;
import org.junit.Test;

import Game.Controller.MapEditorController;

/**
 * Check for map validation
 * 
 * @author kunal, Jay
 */
public class TestMapValidation {

	MapEditorController object = new MapEditorController();

	/**
	 * Refactoring 3: All map validation tests in one Test Class.
	 * This method will do map validations.
	 */
	@Test
	public void validationtest() {
		Boolean b = object.loadExistingMap(new File(
				"C:\\Users\\vansh\\Documents\\NetBeansProjects\\risk-app\\data\\testMap.map "));
		assertTrue(b);
	}

	/**
	 * This method will test if the map is invalid.
	 */
	@Test
	public void testIsMapInvalid() {
		Boolean b = object.loadExistingMap(new File(
				"D:\\macs docs\\advance programming practices\\risk app project-github\\risk-app\\src\\Test\\testMap3.map"));
		assertTrue(b);
	}

	/**
	 * This method is checking the validty of map
	 */

	@Test
	public void testIsMapvalid() {

		Boolean b = object.loadExistingMap(new File(
				"C:\\Users\\vansh\\Documents\\NetBeansProjects\\risk-app\\src\\Test\\ik.map"));

		assertFalse(b);
	}

}