package objects;

import Main.Gamepanel;
import entity.Entity;

public class obj_sword_leg extends Entity {
    Gamepanel gp;
    public obj_sword_leg(Gamepanel gp) {
        super(gp);

        this.gp=gp;
        name = "kiếm bị nguyền rủa";
        type = type_sword;
        down1=setUp("/item/sword_leg",gp.TileSize,gp.TileSize);
        attackValue=80;
        defenseValue=-30;
        description="["+name+"]"+"\ntấn công + 100\nphòng thủ - 10\ntầm đánh 45\ncây kiếm này đã bị nguyền rủa!";
        attackArea.width=50 ;
        attackArea.height=50;
        belly=1000000;
    }

}

