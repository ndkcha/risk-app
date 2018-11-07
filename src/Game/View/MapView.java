package Game.View;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.*;

import Game.Risk.DataHolder;

/**
 * Map View observer implementation.
 * 
 * @author Jay
 * @version 1.2.0
 */
public class MapView implements Observer {

	private DataHolder holder = DataHolder.getInstance();
    private BufferedImage mapImage;
    
	public JLabel jLabel9;
	public JLabel jLabel5;
	public JPanel Image_Panel;
	public JScrollPane jScrollPane1;
	
	public MapView() {
		// TODO Auto-generated constructor stub
		jLabel9 = new JLabel();
		jLabel5 = new JLabel();
		Image_Panel = new JPanel();
		jScrollPane1 = new JScrollPane();
		
		jLabel5.setText("Map");
		
		Image_Panel = new JPanel();
		try {
            if (this.holder.bmpFile != null)
                this.mapImage = ImageIO.read(this.holder.bmpFile);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

		jLabel9.setIcon(new ImageIcon(mapImage)); // NOI18N
		
		GroupLayout Image_PanelLayout = new GroupLayout(Image_Panel);
        Image_Panel.setLayout(Image_PanelLayout);
        Image_PanelLayout.setHorizontalGroup(
            Image_PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(Image_PanelLayout.createSequentialGroup()
                    .addComponent(jLabel9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(572, 572, 572))
        );
        Image_PanelLayout.setVerticalGroup(
            Image_PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jLabel9, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

	}
	
	/**
	 * Return Map Panel for main risk view.
	 * 
	 * @return Image_Panel Panel for Map View.
	 */
	public JPanel getPanel() {
		 return this.Image_Panel;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
