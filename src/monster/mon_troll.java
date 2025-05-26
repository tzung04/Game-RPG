package monster;

import Main.Gamepanel;
import entity.Entity;
import objects.*;

import java.util.Random;

public class mon_troll extends Entity{
    Gamepanel gp;
    public mon_troll(Gamepanel gp) {
        super(gp);
        this.gp=gp;
        type =type_monster;
        name="troll";
        speedDefault=1;
        speed=speedDefault;
        maxHP=500;
        hp=maxHP;
        attack=200;
        defense=20;
        strength=10;
        dexterity=7;
        exp=50;
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
        up1=setUp("/monster/monster_troll_up1",gp.TileSize,gp.TileSize);
        up2=setUp("/monster/monster_troll_up2",gp.TileSize,gp.TileSize);
        down1=setUp("/monster/monster_troll_down1",gp.TileSize,gp.TileSize);
        down2=setUp("/monster/monster_troll_down2",gp.TileSize,gp.TileSize);
        left1=setUp("/monster/monster_troll_down1",gp.TileSize,gp.TileSize);
        left2=setUp("/monster/monster_troll_left2",gp.TileSize,gp.TileSize);
        right1=setUp("/monster/monster_troll_down1",gp.TileSize,gp.TileSize);
        right2=setUp("/monster/monster_troll_right2",gp.TileSize,gp.TileSize);
    }
    public void setAction(){

        actionLockCounter++;
        Random random = new Random();
        belly =  random.nextInt(100)+1000;
        if (actionLockCounter == 120) {
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "right";
            }
            if (i > 25 && i <= 50) {
                direction = "left";
            }
            if (i > 50 && i <= 75) {
                direction = "right";
            }
            if (i > 75 ) {
                direction = "left";
            }
            actionLockCounter=0;
        }

        int i=new Random().nextInt(100)+1;
        if(i>99&& !currentFruit.alive && shotAvailableCounter==30){
            currentFruit.set(worldX,worldY,direction,true,this);
            gp.fruitList.add(currentFruit);
            shotAvailableCounter=0;
        }
        if(shotAvailableCounter<30){
            shotAvailableCounter++;
        }
    }
    public void checkDrop(){
        int i= new Random().nextInt(100)+1;
        if(i>70){
            dropItem(new obj_bellyBag(gp));
        }
        else if(i<5){
            dropItem(new obj_mana(gp));
        }
        else if(i>10&&i<20){
            dropItem(new obj_axe_wood(gp));
        }
        else if(i>20&&i<35){
            dropItem(new obj_potion(gp));
        }
    }

    @Override
    public void damageReaction() {
        actionLockCounter=0;
        switch(gp.player.direction){
            case "up": direction="down";break;
            case "down": direction="up";break;
            case "left": direction="right";break;
            case "right": direction="left";break;
        }
    }
}

