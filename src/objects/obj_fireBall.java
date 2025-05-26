package objects;

import Main.Gamepanel;
import entity.Entity;
import entity.Fruit;

import java.awt.*;

public class obj_fireBall extends Fruit {
    Gamepanel gp;

    public obj_fireBall(Gamepanel gp) {
        super(gp);
        this.gp=gp;
        name="fire ball";
        type=type_fruit;
        speed=3;
        maxHP=100;
        hp=maxHP;
        attack=20;
        useCost=20;
        alive=false;
        getImage();

    }
    public void getImage(){

        up1= setUp("/fruitTile/ball1",gp.TileSize,gp.TileSize);
        up2= setUp("/fruitTile/ball2",gp.TileSize,gp.TileSize);
        down1= setUp("/fruitTile/ball1",gp.TileSize,gp.TileSize);
        down2= setUp("/fruitTile/ball2",gp.TileSize,gp.TileSize);
        right1= setUp("/fruitTile/ball1",gp.TileSize,gp.TileSize);
        right2= setUp("/fruitTile/ball2",gp.TileSize,gp.TileSize);
        left1= setUp("/fruitTile/ball1",gp.TileSize,gp.TileSize);
        left2= setUp("/fruitTile/ball2",gp.TileSize,gp.TileSize);
    }
    public Color getParticleColor(){
        return new Color(80,20,40);
    }
    public int getParticleSize(){
        return 6;
    }
    public int getParticleSpeed(){
        return 1;
    }
    public int getMaxHP(){
        return 30;
    }
}

