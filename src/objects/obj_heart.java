package objects;
import Main.Gamepanel;
import entity.*;

import javax.imageio.ImageIO;
import java.io.IOException;

public class obj_heart extends Entity {
    public obj_heart(Gamepanel gp){
        super(gp);
        name = "heart";
        try{
            image1= ImageIO.read(getClass().getResourceAsStream("/item/heart.png"));
            image2= ImageIO.read(getClass().getResourceAsStream("/item/heart1.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
