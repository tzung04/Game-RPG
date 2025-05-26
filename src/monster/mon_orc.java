package monster;

import Main.Gamepanel;
import entity.*;
import objects.*;

import java.util.Random;

public class mon_orc extends Entity {
    Gamepanel gp;
    public mon_orc(Gamepanel gp) {
        super(gp);

            this.gp=gp;
            type =type_monster;
            name="orc gác ngục";
            speedDefault=1;
            speed=speedDefault;
            maxHP=7000;
            hp=maxHP;
            attack=450;
            defense=200;
            exp=500;
            strength=30;
            currentFruit = new obj_fireBall(gp);
            dexterity=30;

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
            up1=setUp("/monster/mon_orc_up1",gp.TileSize,gp.TileSize);
            up2=setUp("/monster/mon_orc_up2",gp.TileSize,gp.TileSize);
            down1=setUp("/monster/mon_orc_down1",gp.TileSize,gp.TileSize);
            down2=setUp("/monster/mon_orc_left1",gp.TileSize,gp.TileSize);
            left1=setUp("/monster/mon_orc_left1",gp.TileSize,gp.TileSize);
            left2=setUp("/monster/mon_orc_left2",gp.TileSize,gp.TileSize);
            right1=setUp("/monster/mon_orc_down1",gp.TileSize,gp.TileSize);
            right2=setUp("/monster/mon_orc_down2",gp.TileSize,gp.TileSize);
        }
        public void setAction(){

            actionLockCounter++;
            Random random = new Random();
            belly =  random.nextInt(100)+5000;
            if (actionLockCounter == 90) {
                int i = random.nextInt(100) + 1;
                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
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
            if(i>99&& !currentFruit.alive && shotAvailableCounter==15){
                currentFruit.set(worldX,worldY,direction,true,this);
                gp.fruitList.add(currentFruit);
                //  System.out.println("add chua?");
                shotAvailableCounter=0;
            }
            if(shotAvailableCounter<15){
                shotAvailableCounter++;
            }
        }
        public void checkDrop(){
            int i= new Random().nextInt(100)+1;
            if(i>60){
                dropItem(new obj_bellyBag(gp));
            }
            else if(i<5){
                dropItem(new obj_shield_legendary(gp));
            }
            else if(i>5&&i<20){
                dropItem(new obj_sword_ice(gp));
            }
            else if(i<35){
                dropItem(new obj_mana(gp));
            }
        }

        @Override
        public void damageReaction() {
            actionLockCounter=0;
            speed+=1;
            switch(gp.player.direction){
                case "up": direction="down";break;
                case "down": direction="up";break;
                case "left": direction="right";break;
                case "right": direction="left";break;
            }

    }
}
