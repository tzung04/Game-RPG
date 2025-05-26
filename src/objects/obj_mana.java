package objects;
import Main.Gamepanel;
import entity.*;

public class obj_mana extends Entity {
    Gamepanel gp;

    public obj_mana(Gamepanel gp){

        super(gp);
        this.gp=gp;
        value=1000;
        belly=10000;
        stackable=true;
        amount=1;
        type=type_consumable;
        name = "bình mana";
        down1=setUp("/item/mana",gp.TileSize,gp.TileSize);
        description="bình này sẽ hồi cho bạn \nđâu đó 1000 mana";
    }
    public boolean use(Entity E){
        gp.gameState=3;
        gp.ui.currentDialogue="bạn uống "+name+" !\nbạn cảm thấy thật yomost!";
        E.mana+=value;
        gp.playSE(4);
        return true;
    }
}
