package dvt.partie;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import static dvt.devint.ConstantesDevint.*;

public class MonPanel extends JPanel{
	 
    private BufferedImage bufferedImage;
    private String name;
    private double ratioX;
    private double ratioY;
    
    public MonPanel(String fileName, double ratioX, double ratioY ){
        name = fileName;
        this.ratioX = ratioX;
        this.ratioY = ratioY;
        this.bufferedImage = this.toBufferedImage(Toolkit.getDefaultToolkit().getImage(fileName));
    }
 
    public void paintComponent(Graphics g){
        g.drawImage(bufferedImage, 0, 0, (int) (LARGEUR_STANDARD * ratioX), (int) (LONGUEUR_STANDARD * ratioY), null);
    }

    private BufferedImage toBufferedImage(Image image){ 
    	image = new ImageIcon(image).getImage();

        BufferedImage bufferedImage = new BufferedImage( image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        return bufferedImage;
    }

    public void setName(String n) {
        name = n;
        this.bufferedImage = this.toBufferedImage(Toolkit.getDefaultToolkit().getImage(n));
        repaint();
    }
}
