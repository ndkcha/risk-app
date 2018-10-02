/**
 * @file the Start up Phase of the game. 
 */
package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.Controller;

/**
 * Start of the game: Main screen and game settings.
 * 
 * @author Jay
 *
 */
public class StartUpPhase {

	// Panel height and width.
	int width=100;
    int height=50;
  
    int num_players=2;
	
	private JFrame frame;
	private JButton start_game, load_game, map_editor, credits, help, jb_ok;
	
	//Stores the path of the map file.
	private String map_path = null;
	
	// First screen on game load
	public void startUpPhase() {
		// New JFrame Object.
		frame = new JFrame("Risk: The Conquest Game");
		
		// JButtons objects.
		start_game = new JButton("Start Game");
		load_game = new JButton("Load Game");
		map_editor = new JButton("Map Editor");
		credits = new JButton("credits");
		help = new JButton("Help");
		
		frame.add(start_game);
		frame.add(load_game);
		frame.add(map_editor);		
		frame.add(credits);
		frame.add(help);
		
		frame.setLayout(new FlowLayout());
		frame.setSize(width,height);  
        
	    frame.pack();
	    frame.setVisible(true); 	    
		frame.validate();
	}
	
	// JFrame for Game Settings.
	public void game_settings() {
		// TODO Auto-generated method stub
		final JFrame jf = new JFrame();
		// Settings panel
        JPanel p1= new JPanel();
        jf.setBounds(100,100,width*8,height*4);
        // Players color Panel
        JPanel p2= new JPanel();
        // Ok and Cancel panel
        JPanel p3= new JPanel();
        
        final JComboBox player1_select;
        final JComboBox player2_select;
        final JComboBox player3_select;
        final JComboBox player4_select;
        final JComboBox player5_select;
        final JComboBox player6_select;
        
        final JComboBox players_list;
        final JComboBox difficulty_list;
        final JComboBox countries_list;
        final JComboBox troops_list;
      
        //final JTextField player1_name; 
        
        jf.setTitle("Game Settings");
   
        String[] difficulty={"Difficulty","Easy","Medium","Hard"};
        difficulty_list = new JComboBox(difficulty);         
        difficulty_list.setSelectedIndex(0);

        String[] countries={"Countries Distribution ","Auto","Manual"};
        countries_list = new JComboBox(countries);         
        countries_list.setSelectedIndex(0);
       
        String[] troops={"Troops Distribucion","Auto","Manual"};
        troops_list = new JComboBox(troops);         
        troops_list.setSelectedIndex(0);
        
        String[] player1={"Human"};
        player1_select = new JComboBox(player1);         
        player1_select.setSelectedIndex(0);
        player1_select.setBackground(Color.BLUE);
        
        String[] player2={"Human","Computer"};
        player2_select = new JComboBox(player2);         
        player2_select.setSelectedIndex(1);
        player2_select.setBackground(Color.GREEN);
       
        String[] player3={"Human","Computer"};
        player3_select = new JComboBox(player3);         
        player3_select.setSelectedIndex(1);
        player3_select.setBackground(Color.YELLOW);
         
        String[] player4={"Human","Computer"};
        player4_select = new JComboBox(player4);         
        player4_select.setSelectedIndex(1);
        player4_select.setBackground(Color.MAGENTA);
         
        String[] player5={"Human","Computer"};
        player5_select = new JComboBox(player5);         
        player5_select.setSelectedIndex(1);
        player5_select.setBackground(Color.RED);
         
        String[] player6={"Human","Computer"};
        player6_select = new JComboBox(player6);         
        player6_select.setSelectedIndex(1);
        player6_select.setBackground(Color.ORANGE);
        
        JButton jb_ok = new JButton("OK");
        JButton jb_cancel = new JButton("Cancel");
        
        jf.getContentPane().setLayout(null);
        jf.getContentPane().setLayout(new GridLayout(3,6));
        p1.setLayout(new GridLayout(1,5));
        p2.setLayout(new GridLayout(1,5));
               
        String[] players={"2 Players","3 Players","4 Players","5 Players","6 Players"};
        players_list = new JComboBox(players);         
        players_list.setSelectedIndex(0);
        
        //player1_name = new JTextField(26);

        // Display players colors based on selection on number of players.
        players_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				num_players=players_list.getSelectedIndex()+2;
               
				switch(num_players)
	               {
	                   case 3:
	                	   player3_select.setVisible(true);
	                	   player4_select.setVisible(false);
	                       player5_select.setVisible(false);
	                       player6_select.setVisible(false);
	                       break;
	                       
	                   case 4:
	                	   player3_select.setVisible(true);
	                	   player4_select.setVisible(true);
	                       player5_select.setVisible(false);
	                       player6_select.setVisible(false);
	                       break;
	                       
	                   case 5:
	                	   player3_select.setVisible(true);
	                	   player4_select.setVisible(true);
	                       player5_select.setVisible(true);
	                       player6_select.setVisible(false);
	                      break;
	                      
	                   case 6:
	                	   player3_select.setVisible(true);
	                	   player4_select.setVisible(true);
	                       player5_select.setVisible(true);
	                       player6_select.setVisible(true);
	                       break;
	                       
	                   default:
	                	   player3_select.setVisible(false);
	                	   player4_select.setVisible(false);
	                	   player5_select.setVisible(false);
	                	   player6_select.setVisible(false);
	                       break;
	               }
			}
		});
         
        jb_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
                         jf.dispose();
			}
		});
        
        p1.add(players_list);
        p1.add(difficulty_list);
        p1.add(countries_list);
        p1.add(troops_list);
       
        //p2.add(player1_name);
        p2.add(player1_select);
        p2.add(player2_select);
        p2.add(player3_select);
        p2.add(player4_select);
        p2.add(player5_select);
        p2.add(player6_select);
        
        player3_select.setVisible(false);
        player4_select.setVisible(false);
        player5_select.setVisible(false);
        player6_select.setVisible(false);
                                       
        p3.add(jb_ok);
        p3.add(jb_cancel);
        
        jf.getContentPane().add(p1);
        jf.getContentPane().add(p2);
        jf.getContentPane().add(p3);
  
        jb_ok.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  chooseOptionFrame().dispose();
			  String map_file = map_selector("map");
			  String bmp_file = map_selector("bmp");
			  Controller con = new Controller();
              con.gameStart(map_file, bmp_file);
			  
		  }
		});
        
        jf.setVisible(true);
	}
	
	/**
	 * Upload map file to be used for the game.
	 * 
	 * @param ext extension of the file.
	 * @return map_path Stores the absolute path of the map file and bmp file.
	 */
	public String map_selector(String ext){
		JFrame frame = new JFrame("Select Map File");
		
		// Upload map file. https://coderanch.com/t/466536/java/closing-jFileChooser-window
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("./files/map"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Map Files", ext);
		fileChooser.setFileFilter(filter);

		int returnValue = fileChooser.showOpenDialog(frame);
		// Get the path of the file.
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			map_path = selectedFile.getAbsolutePath();
			frame.dispose();
			if(map_path.substring(map_path.lastIndexOf("."),map_path.length()).equalsIgnoreCase("."+ext)){
				//System.out.println(map_path);
				return map_path;
			}
		}
		if(ext.equals("map")) {
			return map_selector(ext);
		}
		return null;
	}
	
	/**
	 * Returns the frame to be used to dispose it after selection of an option.
	 * @return JFrame
	 */
	public JFrame chooseOptionFrame() {
		return this.frame;
	}
	
	// Action Listener for start game button.
	public void startGameAction(ActionListener newAction) {
		this.start_game.addActionListener(newAction);
	}
	
	// Action Listener for start game button.
	public void selectMapAction(ActionListener n) {
		System.out.println('a');
		this.jb_ok.addActionListener(n);
	}
	
	/**
	 * 
	 */
	public StartUpPhase() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
