package objects;

import Main.Gamepanel;
import entity.Entity;

import java.util.Random;

public class obj_wood extends Entity {
    Gamepanel gp;

    public obj_wood(Gamepanel gp){


        super(gp);
        this.gp=gp;
        stackable=true;
        amount=1;
        value=100;
        belly=1000;
        type=type_consumable;
        name = "gỗ";
        down1=setUp("/item/wood",gp.TileSize,gp.TileSize);
        description="gỗ bình thường \ncó thể dùng làm kiếm, khiên, rìu";
    }
    public boolean use(Entity E){
        gp.gameState=3;
        int i=new Random().nextInt(100)+1;
        if(i>80){
            if(gp.player.canObtainItem(new obj_sword_wood(gp))){
                gp.ui.currentDialogue="oof bạn thành công làm ra 1 thanh kiếm";
            }
        }else if(i<20){
            if(gp.player.canObtainItem(new obj_shield_wood(gp))){
                gp.ui.currentDialogue="oof bạn thành công làm ra 1 thanh kiếm";
            }
        }
        else if(i>20&&i<40){
            if(gp.player.canObtainItem(new obj_axe_wood(gp))){
                gp.ui.currentDialogue="oof bạn thành công làm ra 1 cái rìu";
            }
        }
        else {
            gp.ui.currentDialogue = "không có gì xảy ra cả!";
        }
        stackable=true;
        gp.playSE(4);
        return true;
    }

}
