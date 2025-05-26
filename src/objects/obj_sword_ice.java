package objects;

import Main.Gamepanel;
import entity.Entity;

public class obj_sword_ice extends Entity {
    public obj_sword_ice(Gamepanel gp) {
        super(gp);
        name = "kiếm băng";
        type = type_sword;
        belly=175000;
        down1=setUp("/item/sword_ice",gp.TileSize,gp.TileSize);
        attackValue=40;
        strength=1;
        description="["+name+"]"+"\ntấn công + 40\nsức mạnh +1\ntầm đánh 35 \nmột cây kiếm tốt.\nnhưng mà khá hiếm thấy";
        attackArea.width=40;
        attackArea.height=40;
    }
}
