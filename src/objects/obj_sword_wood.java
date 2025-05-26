package objects;

import Main.Gamepanel;
import entity.Entity;

public class obj_sword_wood extends Entity {
    public obj_sword_wood(Gamepanel gp) {
        super(gp);

                name = "kiếm gỗ";
                type = type_sword;
                down1=setUp("/item/sword_wood",gp.TileSize,gp.TileSize);
                attackValue=7;
                belly=30000;
                description="["+name+"]"+"\ntấn công + 7\ntầm đánh 25\nmột cây kiếm cũ\nkiếm dành cho mọi tân thủ";
                attackArea.width=35;
                attackArea.height=35;
    }
}
