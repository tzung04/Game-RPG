package objects;
import Main.Gamepanel;
import entity.*;

import javax.imageio.ImageIO;
import java.io.IOException;

public class obj_shit extends Entity {
    public obj_shit(Gamepanel gp){

        super(gp);
        name = "shit";
        solidArea.x=23;
        solidArea.y=23;
        solidArea.width=2;
        solidArea.height=2;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidAreaDefaultW=solidArea.width;
        solidAreaDefaultH=solidArea.height;


        try{
            down1= ImageIO.read(getClass().getResourceAsStream("/item/sh!t.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}
