package Test.Testing_Controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import Game.Controller.MapEditorController;

/** Check for invalid map
 * 
 * @author kunal ghai
 *
 */
public class TestMapReader {

	MapEditorController object = new MapEditorController();

	@Test public void testIsMapInvalid() 
	{
     	Boolean b = object.loadExistingMap(new File("C:\\Users\\kunal\\Documents\\NetBeansProjects\\risk-app\\src\\Test\\Testing_Controller\\testMap3.map"));
		assertTrue(b);

	}

}