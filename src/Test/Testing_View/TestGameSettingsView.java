package Test.Testing_View;

import Game.View.GameSettingsView;
import org.junit.Test;
import org.junit.Assert.*;

/**
 * Test if game setting is loading successfully.
 * 
 * @author Jay
 *
 */
public class TestGameSettingsView {
	
	GameSettingsView gsv=new GameSettingsView();
	
	/**
	 * Test if game setting is loading successfully
	 */
	@Test 
	public void testGameSettings()
	{
		gsv.gameSettings(true);
	}
}
