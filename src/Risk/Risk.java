package Risk;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Risk {
	public static final int width = 800;
    public static final int height = 600;
    
	Risk(){
		JFrame frame = new JFrame("Risk: The Conquest Game");
		JButton newGame,loadGame, map, config, settings, credits, help, exit;
		
		newGame = new JButton("Start Game");
		loadGame = new JButton("Load Game");
		map = new JButton("Select Map");
		config = new JButton("Configurations");
		settings = new JButton("Game Settings");
		credits = new JButton("credits");
		help = new JButton("Help");
		
		frame.add(newGame);
		//frame.add(loadGame);
		frame.add(map);
		frame.add(settings);
		frame.add(config);
		frame.add(credits);
		frame.add(help);
		
		frame.setLayout(new FlowLayout());
		frame.setSize(width,height);  
		
		
		newGame.addActionListener(new ActionListener()
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
    
	    frame.pack();
	    frame.setVisible(true); 
	
	}
	
	public static void main(String[] args) {
		new Risk();
	}
}