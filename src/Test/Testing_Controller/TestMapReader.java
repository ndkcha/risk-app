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

	/**
	 * This method will test if the map is invalid.
	 */
	@Test public void testIsMapInvalid() {
     	Boolean b = object.loadExistingMap(new File("D:\\macs docs\\advance programming practices\\risk app project-github\\risk-app\\src\\Test\\testMap3.map"));
		assertTrue(b);
	}

}