package objects;
import Main.Gamepanel;
import entity.*;

public class obj_princess extends Entity {
    public obj_princess(Gamepanel gp){

        super(gp);
        belly=0;
        name = "princess";
        down1=setUp("/item/princess",gp.TileSize,gp.TileSize);
    }

}