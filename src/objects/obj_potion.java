package objects;
import Main.Gamepanel;
import entity.*;

public class obj_potion extends Entity {
    Gamepanel gp;

    public obj_potion(Gamepanel gp){

        super(gp);
        this.gp=gp;
        value=1000;
        belly=5000;
        stackable=true;
        amount=1;
        type=type_consumable;
        name = "bình hp";
        down1=setUp("/item/potion",gp.TileSize,gp.TileSize);
        description="bình này sẽ hồi cho bạn \nđâu đó 1000 hp";
    }
    public boolean use(Entity E){
        gp.gameState=3;
        gp.ui.currentDialogue="bạn uống "+name+" !\nbạn cảm thấy thật yomost!";
        E.hp+=value;
        gp.playSE(4);
        return true;
    }

}