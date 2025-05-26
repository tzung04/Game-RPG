package objects;

import Main.Gamepanel;
import entity.Entity;

public class obj_shield_wood extends Entity {
    public obj_shield_wood(Gamepanel gp) {
        super(gp);
        name = "khiên gỗ";
        type= type_accessory;
        belly=30000;
        dexterity=3;
//        hp=100;
        down1=setUp("/item/shield_wood",gp.TileSize,gp.TileSize);
        defenseValue=7;
        description="["+name+"]"+"\nphòng thủ + 7\nkhéo léo + 5\nđược làm từ gõ hịn. \nkhông dễ dàng bị phá huỷ đâu";
    }
}
