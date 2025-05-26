package objects;

import Main.Gamepanel;
import entity.Entity;

public class obj_sword_leg_fake extends Entity {
    Gamepanel gp;
    public obj_sword_leg_fake(Gamepanel gp) {
        super(gp);

        this.gp=gp;
        name = "kiếm bị nguyền rủa";
        type = type_obstacle;
        down1=setUp("/item/sword_leg",gp.TileSize,gp.TileSize);
        attackValue=100;
        defenseValue=-10;
        description="["+name+"]"+"\ntấn công + 100\nphòng thủ - 10\ntầm đánh 45\ncây kiếm này đã bị nguyền rủa!";
        attackArea.width=45;
        attackArea.height=45;
        solidArea.x=10;
        solidArea.y=10;
        solidArea.width=28;
        belly=1000000;
        solidArea.height=28;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        collision=false;
    }
    public void interact(){
        if(gp.eHandler.obtainedSword){
            if(gp.player.canObtainItem(new obj_sword_leg(gp))){
                String text="bạn nhận được thanh kiếm bị nguyền rủa";
              gp.obj[3]=null;
              gp.iTile[32] = null;
              gp.ui.addMessage(text);
            }
        }
    }
}
