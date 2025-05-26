package monster;

import Main.Gamepanel;
import entity.*;
import objects.*;

import java.util.Random;

public class mon_skeleton extends Entity {
    Gamepanel gp;
    public mon_skeleton(Gamepanel gp) {
        super(gp);
        this.gp=gp;
        type =type_monster;
        name="xương khô";
        speedDefault=1;
        speed=speedDefault;
        maxHP=2000;
        dexterity=30;
        hp=maxHP;
        attack=450;
        strength=20;
        defense=80;
        exp=200;
        currentFruit = new obj_fireBall(gp);

        solidArea.x=10;
        solidArea.y=10;
        solidArea.width=28;
        solidArea.height=28;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidAreaDefaultW = solidArea.width;
        solidAreaDefaultH = solidArea.height;

        getImage();
    }
    public void getImage(){
        up1=setUp("/monster/mon_skeleton_up1",gp.TileSize,gp.TileSize);
        up2=setUp("/monster/mon_skeleton_up2",gp.TileSize,gp.TileSize);
        down1=setUp("/monster/mon_skeleton_left1",gp.TileSize,gp.TileSize);
        down2=setUp("/monster/mon_skeleton_right1",gp.TileSize,gp.TileSize);
        left1=setUp("/monster/mon_skeleton_left1",gp.TileSize,gp.TileSize);
        left2=setUp("/monster/mon_skeleton_left2",gp.TileSize,gp.TileSize);
        right1=setUp("/monster/mon_skeleton_right1",gp.TileSize,gp.TileSize);
        right2=setUp("/monster/mon_skeleton_right2",gp.TileSize,gp.TileSize);
        right2=setUp("/monster/mon_skeleton_right2",gp.TileSize,gp.TileSize);
    }
    public void setAction(){

        actionLockCounter++;
        Random random = new Random();
        belly =  random.nextInt(100)+10000;
        if (actionLockCounter == 90) {
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "down";
            }
            if (i > 25 && i <= 50) {
                direction = "up";
            }
            if (i > 50 && i <= 75) {
                direction = "up";
            }
            if (i > 75 ) {
                direction = "down";
            }
            actionLockCounter=0;
        }

        int i=new Random().nextInt(100)+1;
        if(i>99&& !currentFruit.alive && shotAvailableCounter==20){
            currentFruit.set(worldX,worldY,direction,true,this);
            gp.fruitList.add(currentFruit);
            //  System.out.println("add chua?");
            shotAvailableCounter=0;
        }
        if(shotAvailableCounter<20){
            shotAvailableCounter++;
        }
    }
    public void checkDrop(){
        int i= new Random().nextInt(100)+1;
        if(i>75){
            dropItem(new obj_bellyBag(gp));
        }
        else if(i<10){
            dropItem(new obj_sword_ice(gp));
        }
        else if(i>20&&i<30){
            dropItem(new obj_shield_stone(gp));
        }
        else if(i<20&&i>10){
            dropItem(new obj_potion(gp));
        }
        else if(i>30&&i<40){
            dropItem(new obj_mana(gp));
        }
    }

    @Override
    public void damageReaction() {
        actionLockCounter = 0;
        speed += 1;
        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
}
