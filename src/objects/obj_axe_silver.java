package objects;

import Main.Gamepanel;
import entity.Entity;

public class obj_axe_silver extends Entity {
    public obj_axe_silver(Gamepanel gp) {
        super(gp);
        name= "rìu bạc";
        type=type_axe;
        down1=setUp("/item/silver_axe",gp.TileSize,gp.TileSize);
        attackValue=20;
        belly=150000;
        description="["+name+"]"+"\ntấn công + 20\ntầm đánh 30\nrìu là để chặt cây\nrìu này tốt";
        attackArea.width=30;
        attackArea.height=30;
    }
}
