package Test.Testing_Controller;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import Game.Controller.MapEditorController;

/** Check for map validation
 * 
 * @author kunal ghai
 */
public class TestMapValidation {

	MapEditorController object = new MapEditorController();

	@Test public void validationtest()

	{
		Boolean b = object.loadExistingMap(new File("C:\\Users\\kunal\\Documents\\NetBeansProjects\\risk-app\\src\\Test\\Testing_Controller\\testMap.map"));
		assertTrue(b);

	}

}