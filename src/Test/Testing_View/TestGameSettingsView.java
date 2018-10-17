package Test.Testing_View;

import Game.View.GameSettingsView;
import org.junit.Test;
import org.junit.Assert.*;

public class TestGameSettingsView {
	
	GameSettingsView gsv=new GameSettingsView();
	
	@Test public void testGameSettings()
	{
		String[] playertype= {"Human","Computer"}; 
		//assertArray(playertype, gsv.player2_select);
	}
}
