package objects;
import Main.Gamepanel;
import entity.*;

public class obj_scroll extends Entity {
    Gamepanel gp;
    public obj_scroll(Gamepanel gp){

        super(gp);
        this.gp=gp;
        name = "bản đồ";
        stackable=false;
        type = type_consumable;
        down1=setUp("/item/scroll",gp.TileSize,gp.TileSize);
        description="["+name+"]"+"\nmột cái bản đồ?\nhãy đi theo chỉ dẫn của nó!!";
    }
    public boolean use(Entity entity){

        gp.gameState=gp.dialogueState;
        int objIndex= getDetected(entity,gp.obj,"thuyền");
        if(objIndex!=99){
            gp.ui.currentDialogue="bạn đã có đủ hành trang để lên thuyền!\nthoát khỏi nơi này thôi nào!";
            gp.playSE(4);
//            gp.obj[objIndex]=null;
            return true;
        }
        else{
            gp.ui.currentDialogue="không thể xem ngay bây giờ!!";
            return false;
        }
    }
}

