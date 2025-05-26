package objects;

import Main.Gamepanel;
import entity.Entity;
import entity.Fruit;

import java.awt.*;

public class obj_flame extends Fruit {
    Gamepanel gp;

    public obj_flame(Gamepanel gp) {
        super(gp);
        this.gp=gp;
        name="Flame";
        type=type_fruit;
        speed=3;
        maxHP=120;
        hp=maxHP;
        attack=15;
        useCost=20;
        alive=false;
        getImage();

    }
    public void getImage(){

        up1= setUp("/fruitTile/flame_up1",gp.TileSize,2*gp.TileSize);
        up2= setUp("/fruitTile/flame_up2",gp.TileSize,2*gp.TileSize);
        down1= setUp("/fruitTile/flame_down1",gp.TileSize,2*gp.TileSize);
        down2= setUp("/fruitTile/flame_down2",gp.TileSize,2*gp.TileSize);
        right1= setUp("/fruitTile/flame_right1",2*gp.TileSize,gp.TileSize);
        right2= setUp("/fruitTile/flame_right2",2*gp.TileSize,gp.TileSize);
        left1= setUp("/fruitTile/flame_left1",2*gp.TileSize,gp.TileSize);
        left2= setUp("/fruitTile/flame_left2",2*gp.TileSize,gp.TileSize);
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
        return new Color(250,40,30);
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
