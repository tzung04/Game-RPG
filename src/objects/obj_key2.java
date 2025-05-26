package objects;
import Main.Gamepanel;
import entity.*;

public class obj_key2 extends Entity {
    Gamepanel gp;
    public obj_key2(Gamepanel gp){

        super(gp);
        this.gp=gp;
        name = "chìa khoá hầm sa mạc";
        stackable=true;
        amount=1;
        type = type_consumable;
        down1=setUp("/item/key2",gp.TileSize,gp.TileSize);
        description="["+name+"]"+"\ncánh của tại hầm sa mạc sẽ được mở\nbởi chiếc chìa khoá này";
    }
    public boolean use(Entity entity){

        gp.gameState=gp.dialogueState;
        int objIndex= getDetected(entity,gp.obj,"cửa hầm");
        if(objIndex!=99){
            gp.ui.currentDialogue="bạn đã mở được cửa bằng \n"+name+"!";
            gp.playSE(1);
            gp.obj[objIndex]=null;
            return true;
        }
        else{
            gp.ui.currentDialogue="hiện tại không thể dùng??!";
            return false;
        }
    }
}

