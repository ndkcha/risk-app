package View;

import Model.CountryData;
import Risk.DataHolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MapView {
    private JFrame mapFrame = new JFrame("Map | Risk: The Conquest Game");
    private SpringLayout layout = new SpringLayout();
    private BufferedImage mapImage;
    private Container contentPane;

    private DataHolder holder = DataHolder.getInstance();

    public MapView() {
        try {
            if (this.holder.bmpFile != null)
                this.mapImage = ImageIO.read(this.holder.bmpFile);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        this.contentPane = this.mapFrame.getContentPane();
    }

    public void paintUi() {
        JPanel canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                graphics.drawImage(mapImage, 0, 0, null);
            }
        };
        canvas.setPreferredSize(new Dimension(this.mapImage.getWidth(), this.mapImage.getHeight()));
        System.out.println("\nWidth: " + this.mapImage.getWidth() + " | Height: " + this.mapImage.getHeight());

        JScrollPane scrollPane = new JScrollPane(canvas);
        this.contentPane.setLayout(layout);
        this.contentPane.add(scrollPane);
        this.mapFrame.setContentPane(scrollPane);

        this.mapFrame.pack();
        this.mapFrame.setVisible(true);
    }

    public void plotPlayers() {
        System.out.print("\nCountries: ");
        for (CountryData country : this.holder.getCountryDataList()) {
            System.out.print(country.getName() + " | ");
            JLabel label = new JLabel(country.getName());
            // X coordinate
            label.setHorizontalAlignment(country.getLatitude());
            // Y coordinate
            label.setVerticalAlignment(country.getLongitude());
            this.contentPane.add(label);
        }
    }
}
