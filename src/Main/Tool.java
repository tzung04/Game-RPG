package Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tool {
    public BufferedImage scaleImage(BufferedImage original,int width,int height){
        BufferedImage scaleImage= new BufferedImage(width,height,original.getType());
        Graphics2D g2= scaleImage.createGraphics();
        g2.drawImage(original,0,0,width,height,null);
        g2.dispose();

        return scaleImage;
    }
}
