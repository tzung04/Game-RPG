package objects;

import Main.Gamepanel;
import entity.*;

public class obj_door2 extends Entity {
    Gamepanel gp;
    public obj_door2(Gamepanel gp){

        super(gp);
        this.gp=gp;
        name = "cửa hầm";
        type=type_obstacle;
        down1=setUp("/item/door2",gp.TileSize,gp.TileSize);
        collision =true;
    }

    public void interact(){

        gp.gameState=gp.dialogueState;
        gp.ui.currentDialogue="không có chìa khoá thì sao vào?";
    }
}

