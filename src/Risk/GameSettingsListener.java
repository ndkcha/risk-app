package Risk;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameSettingsListener implements ActionListener {

	int width=100;
    int height=50;
    int num_players=2;
    private Risk r;
    
    /**
     * Creates a new instance of GameSettingsListener
     * @param r 
     */
    public GameSettingsListener(Risk r) {
        this.r=r;
      
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		final JFrame jf = new JFrame();
		JPanel headings = new JPanel();
        JPanel p1= new JPanel();
        jf.setBounds(100,100,width*8,height*4);
        JPanel p2= new JPanel();
        JPanel p3= new JPanel();
        
        final JComboBox jcb1;
        final JComboBox jcb2;
        final JComboBox jcb3;
        final JComboBox jcb4;
        final JComboBox jcb5;
        final JComboBox jcb6;
        
        final JComboBox jc1;
        final JComboBox jc3;
        final JComboBox jc4;
        final JComboBox jc5;
      
        jf.setTitle("Game Settings");
        
        String[] difficulty={"Difficulty","Easy","Medium","Hard"};
        jc3 = new JComboBox(difficulty);         
        jc3.setSelectedIndex(0);
        
        String[] countries={"Countries Distribution ","Auto","Manual"};
        jc4 = new JComboBox(countries);         
        jc4.setSelectedIndex(0);
       
        String[] troops={"Troops Distribucion","Auto","Manual"};
        jc5 = new JComboBox(troops);         
        jc5.setSelectedIndex(0);
             
        JButton jb1= new JButton("OK");
        JButton jb2= new JButton("Cancel");
        
        jf.getContentPane().setLayout(null);
        jf.getContentPane().setLayout(new GridLayout(3,6));
        headings.setLayout(new GridLayout(1,5));
        p1.setLayout(new GridLayout(1,5));
        p2.setLayout(new GridLayout(1,5));
      
        String[] players_list={"2 Players","3 Players","4 Players","5 Players","6 Players"};
        jc1 = new JComboBox(players_list);         
        jc1.setSelectedIndex(0);

        jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
                         jf.dispose();
			}
		});
        
        headings.add(jc1);  
        p1.add(jc1);
        p1.add(jc3);
        p1.add(jc4);
        p1.add(jc5);
                                       
        p3.add(jb1);
        p3.add(jb2);
        
        jf.getContentPane().add(headings);
        jf.getContentPane().add(p1);
        jf.getContentPane().add(p3);
        
        jf.setVisible(true);
	}

}
