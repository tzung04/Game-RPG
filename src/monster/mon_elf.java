package monster;

import Main.Gamepanel;
import entity.Entity;
import objects.*;

import java.util.Random;

public class mon_elf extends Entity {
    Gamepanel gp;
    public mon_elf(Gamepanel gp) {
        super(gp);
        this.gp=gp;
        type =type_monster;
        name="elf hái lượm";
        speedDefault=1;
        speed=speedDefault;
        maxHP=10000;
        hp=maxHP;
        attack=600;
        defense=500;
        dexterity=30;
        strength=40;
        exp=1000;
        currentFruit = new obj_apple(gp);

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
        up1=setUp("/monster/mon_elf_up1",gp.TileSize,gp.TileSize);
        up2=setUp("/monster/mon_elf_up2",gp.TileSize,gp.TileSize);
        down1=setUp("/monster/mon_elf_left1",gp.TileSize,gp.TileSize);
        down2=setUp("/monster/mon_elf_right1",gp.TileSize,gp.TileSize);
        left1=setUp("/monster/mon_elf_left1",gp.TileSize,gp.TileSize);
        left2=setUp("/monster/mon_elf_left2",gp.TileSize,gp.TileSize);
        right1=setUp("/monster/mon_elf_right1",gp.TileSize,gp.TileSize);
        right2=setUp("/monster/mon_elf_right2",gp.TileSize,gp.TileSize);
    }
    public void setAction(){

        actionLockCounter++;
        Random random = new Random();
        belly =  random.nextInt(100)+10000;
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
        if(i>98&& !currentFruit.alive && shotAvailableCounter==10){
            currentFruit.set(worldX,worldY,direction,true,this);
            gp.fruitList.add(currentFruit);
            //  System.out.println("add chua?");
            shotAvailableCounter=0;
        }
        if(shotAvailableCounter<10){
            shotAvailableCounter++;
        }
    }
    public void checkDrop(){
        int i= new Random().nextInt(100)+1;
        if(i>70){
            dropItem(new obj_bellyBag(gp));
        }
        else if(i<10){
            dropItem(new obj_sword_ice(gp));
        }
        else if(i>10&&i<25){
            dropItem(new obj_appleToEat(gp));
        }
    }

    @Override
    public void damageReaction() {
        actionLockCounter=0;
        speed+=2;
        switch(gp.player.direction){
            case "up": direction="down";break;
            case "down": direction="up";break;
            case "left": direction="right";break;
            case "right": direction="left";break;
        }

    }
}
