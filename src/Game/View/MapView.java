package Game.View;

import Game.Model.CountryData;
import Game.Risk.DataHolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MapView {
    private JFrame mapFrame = new JFrame("Map | Risk: The Conquest Game");
//    private SpringLayout layout = new SpringLayout();
    private BufferedImage mapImage;
    private Container contentPane;
    private JPanel canvas;

    private DataHolder holder = DataHolder.getInstance();

    public MapView() {
        try {
            if (this.holder.bmpFile != null)
                this.mapImage = ImageIO.read(this.holder.bmpFile);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void paintUi() {
        this.canvas = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                graphics.drawImage(mapImage, 0, 0, null);
            }
        };
        this.canvas.setPreferredSize(new Dimension(this.mapImage.getWidth(), this.mapImage.getHeight()));
        System.out.println("\nWidth: " + this.mapImage.getWidth() + " | Height: " + this.mapImage.getHeight());

        JScrollPane scrollPane = new JScrollPane(canvas);
        this.mapFrame.setContentPane(scrollPane);

        this.mapFrame.pack();
        this.mapFrame.setVisible(true);
    }

    public void plotPlayers() {
        System.out.print("\nCountries: ");

        for (CountryData country : this.holder.getCountryDataList()) {
            System.out.print(country.getName() + " | ");
            // X = latitude; Y = longitude
        }
    }
}
