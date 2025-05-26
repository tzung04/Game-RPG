package objects;
import Main.Gamepanel;
import entity.*;

import java.util.Random;

public class obj_appleToEat extends Entity {
    Gamepanel gp;

    public obj_appleToEat(Gamepanel gp){

        super(gp);
        this.gp=gp;
        value=5000;
        belly=1000;
        type=type_consumable;
        stackable=true;
        name = "quả táo đỏ";
        belly=50000;
        down1=setUp("/fruitTile/apple1",gp.TileSize,gp.TileSize);
        description="nhìn có vẻ bình thường\nnhưng nếu ăn vào sẽ + 5000exp\nnếu may mắn. hêhhe...";
    }
    public boolean use(Entity E){
        int i=new Random().nextInt(10)+1;
        if(i<=5){
        gp.gameState=gp.dialogueState;
        gp.playSE(4);
        gp.ui.currentDialogue="bạn đã ăn "+name+" !\nbạn cảm thấy thật yomost!";
        E.exp+=value;
        }
        else{
            gp.gameState=gp.dialogueState;
            gp.playSE(3);
            gp.ui.currentDialogue="thật không may quả này đã bị hỏng\nbạn cảm thấy không khoẻ";
            E.hp-=100;
        }
        stackable=true;
        return true;
    }

}