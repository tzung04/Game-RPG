package objects;

import Main.Gamepanel;
import entity.Entity;
import entity.Fruit;

import java.awt.*;

public class obj_ice extends Fruit {
    Gamepanel gp;

    public obj_ice(Gamepanel gp) {
        super(gp);
        this.gp=gp;
        name="Ice";
        type=type_fruit;
        speed=15;
        maxHP=80;
        hp=maxHP;
        attack=10;
        useCost=20;
        alive=false;
        getImage();

    }
    public void getImage(){

        up1= setUp("/fruitTile/ice_up1",gp.TileSize,2*gp.TileSize);
        up2= setUp("/fruitTile/ice_up2",gp.TileSize,2*gp.TileSize);
        down1= setUp("/fruitTile/ice_down1",gp.TileSize,2*gp.TileSize);
        down2= setUp("/fruitTile/ice_down2",gp.TileSize,2*gp.TileSize);
        right1= setUp("/fruitTile/ice_right1",2*gp.TileSize,gp.TileSize);
        right2= setUp("/fruitTile/ice_right2",2*gp.TileSize,gp.TileSize);
        left1= setUp("/fruitTile/ice_left1",2*gp.TileSize,gp.TileSize);
        left2= setUp("/fruitTile/ice_left2",2*gp.TileSize,gp.TileSize);
    }
    public boolean haveResource(Entity user){
        boolean haveResource=false;
        if(user.mana>=useCost){
            haveResource=true;
        }
        return haveResource;
    }
    public void subtractResource(Entity user){
        user.mana-=useCost;
    }
    public Color getParticleColor(){
        return new Color(20,70,150);
    }
    public int getParticleSize(){
        return 6;
    }
    public int getParticleSpeed(){
        return 2;
    }
    public int getMaxHP(){
        return 30;
    }
}

