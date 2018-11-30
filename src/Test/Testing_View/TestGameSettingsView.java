package Test.Testing_View;

import Game.View.GameSettingsView;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Test if game setting is loading successfully.
 * 
 * @author Jay
 *
 */
public class TestGameSettingsView {
	private GameSettingsView gsv;

	@Before
	public void initValues() {
		this.gsv = new GameSettingsView();
	}
	
	@Test public void testGameSettings() {
		this.gsv.gameSettings(false);
		assertNotNull(this.gsv.getFrameGameSettings());
	}
}
