package objects;

import Main.Gamepanel;
import entity.Entity;
import entity.Fruit;

import java.awt.*;

public class obj_apple extends Fruit {
    Gamepanel gp;

    public obj_apple(Gamepanel gp) {
        super(gp);
        this.gp=gp;
        name="táo";
        type=type_fruit;
        speed=6;
        maxHP=60;
        hp=maxHP;
        attack=75;
        useCost=0;
        alive=false;

        getImage();

    }
    public void getImage(){

        up1= setUp("/fruitTile/apple1",gp.TileSize,gp.TileSize);
        up2= setUp("/fruitTile/apple2",gp.TileSize,gp.TileSize);
        down1= setUp("/fruitTile/apple1",gp.TileSize,gp.TileSize);
        down2= setUp("/fruitTile/apple2",gp.TileSize,gp.TileSize);
        right1= setUp("/fruitTile/apple1",gp.TileSize,gp.TileSize);
        right2= setUp("/fruitTile/apple2",gp.TileSize,gp.TileSize);
        left1= setUp("/fruitTile/apple1",gp.TileSize,gp.TileSize);
        left2= setUp("/fruitTile/apple2",gp.TileSize,gp.TileSize);
    }
    public Color getParticleColor(){
        return new Color(200,50,40);
    }
    public int getParticleSize(){
        return 4;
    }
    public int getParticleSpeed(){
        return 1;
    }
    public int getMaxHP(){
        return 30;
    }
}


