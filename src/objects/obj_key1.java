package objects;
import Main.Gamepanel;
import entity.*;

public class obj_key1 extends Entity {
    Gamepanel gp;
    public obj_key1(Gamepanel gp){

        super(gp);
        this.gp=gp;
        name = "chìa khoá ngục tù";
        stackable=true;
        amount=1;
        type = type_consumable;
        down1=setUp("/item/key1",gp.TileSize,gp.TileSize);
        description="["+name+"]"+"\nchìa khoá được bọn troll quản lý\nhãy đoán xem nó sẽ mở cánh của nào";
    }
    public boolean use(Entity entity){

        gp.gameState=gp.dialogueState;
        int objIndex= getDetected(entity,gp.obj,"cửa ngục");
        if(objIndex!=99){
            gp.ui.currentDialogue="bạn đã mở được cửa bằng \n"+name+"!";
            gp.playSE(1);
            gp.obj[objIndex]=null;
            return true;
        }
        else{
            gp.ui.currentDialogue="sao lại dùng chìa khoá ở đây??!";
            return false;
        }
    }
}
