package entity;

import Main.Gamepanel;

import java.awt.*;

public class Particle extends Entity{
    Entity generator;
    Color color;
    int size;
    int xd;
    int yd;
    public Particle(Gamepanel gp,Entity generator,Color color, int size,
                    int speed, int maxHP,int xd,int yd) {
        super(gp);
        this.generator=generator;
        this.color=color;
        this.speed=speed;
        this.maxHP=maxHP;
        this.size=size;
        this.xd=xd;
        this.yd=yd;
        hp=maxHP;
        int offset=(gp.TileSize/2)-(size/2);
        worldX=generator.worldX+offset;
        worldY=generator.worldY+offset;
    }

    @Override
    public void update() {
        hp--;
        if(hp<maxHP/4){
            yd++;
        }
        worldX+=(speed*xd)/2;
        worldY+=(speed*yd)/2;
        if(hp<=0){
            alive=false;
        }
    }
    public void draw(Graphics2D g2){
        int screenX=worldX-gp.player.worldX+gp.player.screenX;
        int screenY=worldY-gp.player.worldY+gp.player.screenY;

        g2.setColor(color);
        g2.fillRect(screenX,screenY,size,size);

    }
}
