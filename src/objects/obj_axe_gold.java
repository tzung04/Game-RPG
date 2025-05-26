package objects;

import Main.Gamepanel;
import entity.Entity;

public class obj_axe_gold extends Entity {
    public obj_axe_gold(Gamepanel gp) {
        super(gp);
        name= "rìu vàng";
        type=type_axe;
        down1=setUp("/item/gold_axe",gp.TileSize,gp.TileSize);
        attackValue=50;
        defenseValue=10;
        belly=300000;
        description="["+name+"]"+"\ntấn công + 50\nphòng thủ +10\ntầm đánh 35\nrìu là để chặt cây\rìu này xịn\nbạn thật may mắn khi sở hữu";
        attackArea.width=35;
        attackArea.height=35;
    }
}
