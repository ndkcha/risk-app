/**
 * 
 */
package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.Controller;
import Model.PlayerData;

/**
 * Start of the game: Main screen and game settings.
 * This class consist of initial menu and game settings.
 * 
 * @author Jay
 *
 */
public class GameSettingsView {

	// Panel height and width.
	public static final int width=100;
	public static final int height=50;
	public int num_players=2; // Minimum number of players
	
	private JFrame frame;
	private JButton startGame, loadGame, mapEditor, credits, help, selectMap;
	
	//Stores the path of the map file.
	private String map_path = null;
	
	/**
	 * This method will privide the initial startup screen of the game.
	 * 
	 * @author Jay
	 */
	public void startGame() {
		// New JFrame Object.
		frame = new JFrame("Risk: The Conquest Game");
		
		// JButtons objects.
		startGame = new JButton("Start Game");
		loadGame = new JButton("Load Game");
		mapEditor = new JButton("Map Editor");
		credits = new JButton("credits");
		help = new JButton("Help");
		
		frame.add(startGame);
		frame.add(loadGame);
		frame.add(mapEditor);		
		frame.add(credits);
		frame.add(help);
		
		frame.setLayout(new FlowLayout());
		frame.setSize(width,height);  
        
	    frame.pack();
	    frame.setVisible(true); 	    
		frame.validate();
	}
	
	/**
	 * This method will provide the game settings panel for Number of players,
	 * difficulty, player names and there type with colors
	 * 
	 * @author Jay
	 */
	public void gameSettings() {
		System.out.println("Game Setting panel is opened");
		// TODO Auto-generated method stub
		final JFrame jf = new JFrame();
		jf.setBounds(100,100,width*8,height*4);
		
        JPanel p1= new JPanel(); // Settings panel
        JPanel p2= new JPanel(); // Players Names Panel.
        JPanel p3= new JPanel(); // Players color Panel
        JPanel p4= new JPanel(); // Select map and Cancel panel
        
        // Textfields for players name
        final JTextField player1_name; 
        final JTextField player2_name; 
        final JTextField player3_name; 
        final JTextField player4_name; 
        final JTextField player5_name;
        final JTextField player6_name; 
        
        // Select sist for player type and color of player
        final JComboBox player1_select;
        final JComboBox player2_select;
        final JComboBox player3_select;
        final JComboBox player4_select;
        final JComboBox player5_select;
        final JComboBox player6_select;
        
        // Combo select lists
        final JComboBox players_list;
        final JComboBox difficulty_list;
        final JComboBox countries_list;
        final JComboBox troops_list;

        jf.setTitle("Game Settings");
        
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
        
        String[] players={"2 Players","3 Players","4 Players","5 Players","6 Players"};
        players_list = new JComboBox(players);         
        players_list.setSelectedIndex(0);
        
        String[] difficulty={"Difficulty","Easy","Medium","Hard"};
        difficulty_list = new JComboBox(difficulty);         
        difficulty_list.setSelectedIndex(0);

        String[] countries={"Countries Distribution ","Auto","Manual"};
        countries_list = new JComboBox(countries);         
        countries_list.setSelectedIndex(0);
       
        String[] troops={"Troops Distribucion","Auto","Manual"};
        troops_list = new JComboBox(troops);         
        troops_list.setSelectedIndex(0);
        
        JButton selectMap = new JButton("Select Map");
        JButton jb_cancel = new JButton("Cancel");
        
        jf.getContentPane().setLayout(null);
        jf.getContentPane().setLayout(new GridLayout(4,6));
        p1.setLayout(new GridLayout(1,5));
        p2.setLayout(new GridLayout(1,5));
        p3.setLayout(new GridLayout(1,5));
               
        player1_name = new JTextField("Player One");
        player2_name = new JTextField("Player two");
        player3_name = new JTextField("Player three");
        player4_name = new JTextField("Player four");
        player5_name = new JTextField("Player five");
        player6_name = new JTextField("Player six");

        // Display players name and colors based on selection on number of players.
        players_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				num_players=players_list.getSelectedIndex()+2;
				
				switch(num_players)
	               {
	                   case 3:
	                	   player3_name.setVisible(true);
	                       player4_name.setVisible(false);
	                       player5_name.setVisible(false);
	                       player6_name.setVisible(false);
	                       
	                	   player3_select.setVisible(true);
	                	   player4_select.setVisible(false);
	                       player5_select.setVisible(false);
	                       player6_select.setVisible(false);
	                       break;
	                       
	                   case 4:
	                	   player3_name.setVisible(true);
	                       player4_name.setVisible(true);
	                       player5_name.setVisible(false);
	                       player6_name.setVisible(false);
	                       
	                	   player3_select.setVisible(true);
	                	   player4_select.setVisible(true);
	                       player5_select.setVisible(false);
	                       player6_select.setVisible(false);
	                       break;
	                       
	                   case 5:
	                	   player3_name.setVisible(true);
	                       player4_name.setVisible(true);
	                       player5_name.setVisible(true);
	                       player6_name.setVisible(false);
	                       
	                	   player3_select.setVisible(true);
	                	   player4_select.setVisible(true);
	                       player5_select.setVisible(true);
	                       player6_select.setVisible(false);
	                      break;
	                      
	                   case 6:
	                	   player3_name.setVisible(true);
	                       player4_name.setVisible(true);
	                       player5_name.setVisible(true);
	                       player6_name.setVisible(true);
	                       
	                	   player3_select.setVisible(true);
	                	   player4_select.setVisible(true);
	                       player5_select.setVisible(true);
	                       player6_select.setVisible(true);
	                       break;
	                       
	                   default:
	                	   player3_name.setVisible(false);
	                       player4_name.setVisible(false);
	                       player5_name.setVisible(false);
	                       player6_name.setVisible(false);
	                       
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
       
        p2.add(player1_name);
        p2.add(player2_name);
        p2.add(player3_name);
        p2.add(player4_name);
        p2.add(player5_name);
        p2.add(player6_name);
        
        p3.add(player1_select);
        p3.add(player2_select);
        p3.add(player3_select);
        p3.add(player4_select);
        p3.add(player5_select);
        p3.add(player6_select);
        
        player3_name.setVisible(false);
        player4_name.setVisible(false);
        player5_name.setVisible(false);
        player6_name.setVisible(false);
        
        player3_select.setVisible(false);
        player4_select.setVisible(false);
        player5_select.setVisible(false);
        player6_select.setVisible(false);
                                       
        p4.add(selectMap);
        p4.add(jb_cancel);
        
        jf.getContentPane().add(p1);
        jf.getContentPane().add(p2);
        jf.getContentPane().add(p3);
        jf.getContentPane().add(p4);
  
        selectMap.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  System.out.println("number of player selected: " + num_players);
			  String player1Name = null, 
					  player2Name =null, 
					  player3Name = null, 
					  player4Name = null, 
					  player5Name = null, 
					  player6Name = null;
			  String player1Color = null,
					  player2Color= null, 
					  player3Color = null, 
					  player4Color = null,
					  player5Color = null, 
					  player6Color = null;
			  
			  player1Name =  player1_name.getText();
			  player1Color =  " BLUE ";
			  player2Name =  player2_name.getText();
			  player2Color =  " GREEN ";
			  if(num_players >= 3){
				  player3Name =  player3_name.getText();
				  player3Color =  " YELLOW ";
			  }
			  if(num_players >= 4){
				  player4Name =  player4_name.getText();
				  player4Color =  " MAGENTA ";
			  }
			  if(num_players >= 5){
				  player5Name =  player5_name.getText();
				  player5Color =  " RED ";
			  }
			  if(num_players == 6){
				  player6Name =  player6_name.getText();
				  player6Color =  " ORANGE ";
			  }
			  
			  String[][] anArrayOfStrings = new String[6][2];
			  anArrayOfStrings[0][0] = player1Name;
			  anArrayOfStrings[0][1] = player1Color;
			  anArrayOfStrings[1][0] = player2Name;
			  anArrayOfStrings[1][1] = player2Color;
			  anArrayOfStrings[2][0] = player3Name;
			  anArrayOfStrings[2][1] = player3Color;
			  anArrayOfStrings[3][0] = player4Name;
			  anArrayOfStrings[3][1] = player4Color;
			  anArrayOfStrings[4][0] = player5Name;
			  anArrayOfStrings[4][1] = player5Color;
			  anArrayOfStrings[5][0] = player6Name;
			  anArrayOfStrings[5][1] = player6Color;
	
			  // Setting players data in controller
			  PlayerData pd = new PlayerData();
			  pd.setGamePlayerData(anArrayOfStrings);              
			  
			  System.out.println("Select Map Button is clicked");
			  chooseOptionFrame().dispose();
			  String map_file = map_selector("map");
			  String bmp_file = map_selector("bmp");
			  
			  Controller c = new Controller();
              c.gameStart(map_file, bmp_file);
			  
		  }
		});
        
        jf.setVisible(true);
	}	
	
	/**
	 * Upload .map along with .bmp file to be used for the game.
	 * 
	 * @param ext extension of the file.
	 * @return map_path Stores the absolute path of the map file and bmp file.
	 */
	public String map_selector(String ext){
		System.out.println("Map and BMP file selector opened");
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
		System.out.println("");
		return this.frame;
	}
	
	/**
	 * Action Listener for start game button.
	 */
	public void startGameAction(ActionListener newAction) {		
		this.startGame.addActionListener(newAction);
	}
	
	/**
	 * Action Listener for start game button.
	 */
	public void selectMapAction(ActionListener n) {
		this.selectMap.addActionListener(n);
	}
	
	/**
	 * Action Listener for Map Editor button.
	 */
	public void mapEditorAction(ActionListener newAction) {
		this.mapEditor.addActionListener(newAction);
	}
	
	
	public GameSettingsView() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}