package objects;

import Main.Gamepanel;
import entity.Entity;

public class obj_axe_wood extends Entity {
    public obj_axe_wood(Gamepanel gp) {
        super(gp);
        name= "rìu gỗ";
        type=type_axe;
        down1=setUp("/item/wood_axe",gp.TileSize,gp.TileSize);
        attackValue=10;
        belly=50000;
        description="["+name+"]"+"\ntấn công + 10\ntầm đánh 25\nrìu là để chặt cây";
        attackArea.width=25;
        attackArea.height=25;
    }
}
