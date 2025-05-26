package entity;
import Main.Gamepanel;
import entity.*;

import javax.imageio.ImageIO;
import java.io.IOException;

public class NPC_shit extends Entity {
    public NPC_shit(Gamepanel gp){

        super(gp);
        type=type_npc;
        name = "shit";
        solidArea.x=10;
        solidArea.y=20;
        solidArea.width=20;
        solidArea.height=24;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidAreaDefaultW=solidArea.width;
        solidAreaDefaultH=solidArea.height;

        try{
            down1= ImageIO.read(getClass().getResourceAsStream("/item/sh!t.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/item/sh!t.png"));
            up1= ImageIO.read(getClass().getResourceAsStream("/item/sh!t.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/item/sh!t.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/item/sh!t.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/item/sh!t.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/item/sh!t.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/item/sh!t.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
        setDialogue();
    }

    public void setDialogue(){

        dialogue[0]="hehe vi cua tao that tuyet";
        dialogue[1]="hay an that nhieu\n";
        dialogue[2]="may se cam thay tuyet voi";
        dialogue[3]="dung quen tao nhe";
        dialogue[4]="ooh shiet, vị kít thật tuyệt\nmày cảm thấy tuyệt vời";

    }
    public void speak(){

        super.speak();

    }

}
