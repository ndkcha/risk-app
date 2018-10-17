package Test.Testing_Controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import Game.Controller.MapEditorController;

/**
 * This test class checks the validity of the Map Editor.
 */
public class TestMapEditorController {
//	private String Path;
	
	
	/**
	 *  Contructor of Map editor controller called
	 */
	
 MapEditorController object= new MapEditorController();
 
 /**
  * This method is checking the validty of map
  */
 
 @Test public void testIsMapInvalid() {
	 
	 
		Boolean b = object.loadExistingMap(new File("C:\\Users\\vansh\\Documents\\NetBeansProjects\\risk-app\\src\\Test\\ik.map"));
		 
assertFalse(b);
	 
 }
 
 
 
 
	 

}
