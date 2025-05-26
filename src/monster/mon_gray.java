package monster;

import Main.Gamepanel;
import entity.Entity;
import objects.*;

import java.util.Random;

public class mon_gray extends Entity{
    Gamepanel gp;
    public mon_gray(Gamepanel gp) {
        super(gp);
        this.gp=gp;
        type =type_monster;
        name="gray";
        speedDefault=1;
        speed=speedDefault;
        maxHP=70;
        hp=maxHP;
        attack=75;
        defense=0;
        exp=5;

        solidArea.x=2;
        solidArea.y=12;
        solidArea.width=44;
        solidArea.height=36;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidAreaDefaultW = solidArea.width;
        solidAreaDefaultH = solidArea.height;

        getImage();
    }
    public void getImage(){
        up1=setUp("/monster/mon_gray1",gp.TileSize,gp.TileSize);
        up2=setUp("/monster/mon_gray2",gp.TileSize,gp.TileSize);
        down1=setUp("/monster/mon_gray3",gp.TileSize,gp.TileSize);
        down2=setUp("/monster/mon_gray4",gp.TileSize,gp.TileSize);
        left1=setUp("/monster/mon_gray3",gp.TileSize,gp.TileSize);
        left2=setUp("/monster/mon_gray4",gp.TileSize,gp.TileSize);
        right1=setUp("/monster/mon_gray1",gp.TileSize,gp.TileSize);
        right2=setUp("/monster/mon_gray2",gp.TileSize,gp.TileSize);
    }
    public void setAction(){
        actionLockCounter++;
        Random random = new Random();
        belly = random.nextInt(50)+100;
        if (actionLockCounter == 120) {
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "right";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 ) {
                direction = "down";
            }
            actionLockCounter=0;
        }
    }
    public void checkDrop(){
        int i= new Random().nextInt(100)+1;
        if(i>70){
            dropItem(new obj_bellyBag(gp));
        }
        else if(i>20&&i<30){
            dropItem(new obj_potion(gp));
        }
        else if(i>40&&i<55){
            dropItem(new obj_shield_wood(gp));
        }
    }

    @Override
    public void damageReaction() {
        actionLockCounter=0;
        direction=gp.player.direction;
    }
}
