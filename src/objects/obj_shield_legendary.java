package objects;

import Main.Gamepanel;
import entity.Entity;

public class obj_shield_legendary extends Entity {
    public obj_shield_legendary(Gamepanel gp) {
        super(gp);
        name = "khiên huyền thoại";
        type= type_accessory;
        belly=250000;
        down1=setUp("/item/shield_leg",gp.TileSize,gp.TileSize);
        defenseValue=30;
        dexterity=10;
        hp=1000;
        attackValue=10;
        description="["+name+"]"+"\ntấn công +10\nphòng thủ + 30\nhp + 1000\nkhéo léo + 10\nđược nếm máu qua vô số trận chiến\nchiếc khiên này đã có  linh tính";
    }
}
