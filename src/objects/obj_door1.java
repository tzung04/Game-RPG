package objects;
import Main.Gamepanel;
import entity.*;

public class obj_door1 extends Entity {
    Gamepanel gp;
    public obj_door1(Gamepanel gp){

        super(gp);
        this.gp=gp;
        name = "cửa ngục";
        type=type_obstacle;
        down1=setUp("/item/door1",gp.TileSize,gp.TileSize);
        collision =true;
    }

    public void interact(){

        gp.gameState=gp.dialogueState;
        gp.ui.currentDialogue="bạn phải có chìa khoá!!";
    }
}

