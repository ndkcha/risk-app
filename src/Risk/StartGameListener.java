package Risk;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
* The game setting listener.
*
* @author  Jatin Rupeja
* @version 1.0
*/

public class StartGameListener implements ActionListener {

	int width=100;
    int height=50;
    int num_players=2;
    private Risk r;
    
    /**
     * Creates a new instance of GameSettingsListener
     * @param r 
     */
    public StartGameListener(Risk r) {
        this.r=r;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
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
        
        jf.setVisible(true);
	}
}
