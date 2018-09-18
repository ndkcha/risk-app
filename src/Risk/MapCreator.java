package Risk;

import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;

public class MapCreator extends Canvas {
	
	public static void main(String[] args) {
        JFrame frame = new JFrame("Risk: The Conquest Game");
        Canvas canvas = new MapCreator();
        canvas.setSize(800, 600);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        g.fillOval(100, 100, 200, 200);
        g.fillOval(300, 200, 200, 200);
        g.fillOval(400, 400, 200, 200);
    }
}
