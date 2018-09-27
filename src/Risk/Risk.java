package Risk;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* The RISK game main class.
*
* @author  Jatin Rupeja
* @version 1.0
*/
public class Risk {
	public static final int width = 800;
    public static final int height = 600;
    
    public GameSettings config = new GameSettings();
    
	Risk(){
		JFrame frame = new JFrame("Risk: The Conquest Game");
		JButton new_game, load_game, map, load_map, map_editor, settings, credits, help, exit;
		
		new_game = new JButton("Start Game");
		load_game = new JButton("Load Game");
		map = new JButton("Select Map");
		load_map = new JButton("Load Map");
		map_editor = new JButton("Map Editor");
		settings = new JButton("Game Settings");
		credits = new JButton("credits");
		help = new JButton("Help");
		
		frame.add(new_game);
		frame.add(load_game);
		frame.add(map);
		frame.add(load_map);
		frame.add(map_editor);
		frame.add(settings);		
		frame.add(credits);
		frame.add(help);
		
		frame.setLayout(new FlowLayout());
		frame.setSize(width,height);  
		
		
		new_game.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    JDialog d = new JDialog(frame, "newGame", true);
		    d.setLocationRelativeTo(frame);
		    d.setSize(width,height);
		    d.setVisible(true);
		  }
		});
		
		help.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    JDialog d = new JDialog(frame, "help", true);
		    d.setLocationRelativeTo(frame);
		    d.setSize(width,height);
		    d.setVisible(true);
		  }
		});
		
		
		credits.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    JDialog d = new JDialog(frame, "Credits", true);
		    d.setLocationRelativeTo(frame);
		    d.setSize(width,height);
		    d.setVisible(true);
		  }
		});
    
		StartGameListener sgl = new StartGameListener(this);
		new_game.addActionListener(sgl);
		
		LoadGameListener lgl = new LoadGameListener(this);
		load_game.addActionListener(lgl);
		
		SelectMapListener sml = new SelectMapListener(this);
		map.addActionListener(sml);
		
		LoadMapListener lml = new LoadMapListener(this);
		load_map.addActionListener(lml);
		
		MapEditorListener mel = new MapEditorListener(this);
		map_editor.addActionListener(mel);
		
		GameSettingsListener gsl = new GameSettingsListener(this);
		settings.addActionListener(gsl);
		
		CreditListener cl = new CreditListener(this);
		credits.addActionListener(cl);
		
		HelpListener hl = new HelpListener(this);
		help.addActionListener(hl);
        
	    frame.pack();
	    frame.setVisible(true); 
	
	}
	
	public static void main(String[] args) {
		new Risk();
	}
}