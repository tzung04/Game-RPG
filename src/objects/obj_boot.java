package objects;
import Main.Gamepanel;
import entity.*;

public class obj_boot extends Entity {
    Gamepanel gp;

    public obj_boot(Gamepanel gp){

        super(gp);
        this.gp=gp;
        value=5;
        belly=5000;
        stackable=true;
        amount=1;
        type=type_consumable;
        name = "giày";
        down1=setUp("/item/boots",gp.TileSize,gp.TileSize);
        description="loại giày này giúp bạn chạy nhanh hơn trong 30s\nkhông thể cộng dồn!";
    }
    public boolean use(Entity E){
//        gp.gameState=3;
//        gp.ui.currentDialogue="bạn uống "+name+" !\nbạn cảm thấy thật yomost!";
        if(!gp.player.speedUp) {
        E.speed+=value;
        gp.playSE(4);
        gp.player.speedUp = true;
        }
        else {
            gp.gameState=gp.dialogueState;
            gp.ui.currentDialogue="không thể dùng tiếp ngay bây giờ!";
            return false;
        }
        return true;
    }

}
