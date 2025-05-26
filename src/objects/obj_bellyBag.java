package objects;
import Main.Gamepanel;
import entity.*;

import java.awt.*;
import java.util.Random;

public class obj_bellyBag extends Entity {
    Gamepanel gp;
    public obj_bellyBag(Gamepanel gp){
        super(gp);
        this.gp=gp;
        name = "Belly bag";
        type=type_onlyPickUp;
        down1=setUp("/item/bellyBag",gp.TileSize,gp.TileSize);
    }
    public boolean use(Entity user){
        value=new Random().nextInt(500)+50000;
        gp.playSE(2);
        gp.ui.addMessage("earned <"+value+"> belly");
        gp.player.belly+=value;
        return true;
    }

}

