package entity;

import Main.Gamepanel;

import java.awt.*;

public class Fruit extends Entity{

    Entity user;
    public Fruit(Gamepanel gp) {
        super(gp);
    }
    public void set(int worldX, int worldY,String direction,boolean alive, Entity user){

        this.worldX=worldX;
        this.worldY=worldY;
        this.direction=direction;
        this.alive=alive;
        this.user=user;
        this.hp=maxHP;
    }
    public void update(){

        setAction();

        if(user==gp.player){
            int monsterIndex=gp.checkV.checkVatThe(this,gp.monster);
            if(monsterIndex!=99){
                int at =(attack)*user.strength;
                gp.player.damageMonster(monsterIndex,at);
                generateParticle(user.currentFruit,gp.monster[monsterIndex]);
                alive=false;
            }
        }
        if(user!=gp.player){
            boolean contactPlayer=gp.checkV.checkPlayer(this);
            if(!gp.player.invincible&&contactPlayer){
                generateParticle(user.currentFruit,gp.player);
                int at =(attack)*user.strength;
                damagePlayer(at);
                alive= false;
            }
        }

        switch(direction) {
            case "up":
                worldY -= speed;
                break;
            case "down":
                worldY += speed;
                break;
            case "left":
                worldX -= speed;
                break;
            case "right":
                worldX += speed;
                break;
        }
        hp--;
        if(hp<0){
            alive=false;
        }
        spriteCounter++;
        if(spriteCounter>12){
            if(spriteNum==1) spriteNum=2;
            else if(spriteNum==2) spriteNum=1;
            spriteCounter=0;
        }
    }

    public boolean haveResource(Entity user){
        boolean haveResource=true;
        return haveResource;
    }
    public void subtractResource(Entity user){
    }
}
