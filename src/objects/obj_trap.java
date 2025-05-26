package objects;
import Main.Gamepanel;
import entity.*;

public class obj_trap extends Entity {
    public obj_trap(Gamepanel gp){

        super(gp);
        name = "trap";
        down1=setUp("/item/trap",gp.TileSize,gp.TileSize);
    }

}
