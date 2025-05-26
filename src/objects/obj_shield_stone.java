package objects;

import Main.Gamepanel;
import entity.Entity;

public class obj_shield_stone extends Entity {
    public obj_shield_stone(Gamepanel gp) {
        super(gp);
        name = "khiên sắt";
        type= type_accessory;
        belly=120000;
        down1=setUp("/item/shield_stone",gp.TileSize,gp.TileSize);
        defenseValue=20;
        dexterity=5;
        hp=500;
        description="["+name+"]"+"\nphòng thủ + 20\nkhéo léo +5\nhp + 500\nđược làm từ kim loại\ncủa các chiến binh bị đày\ntới nơi này để lại";
    }
}
