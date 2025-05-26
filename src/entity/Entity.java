package entity;
import Main.Gamepanel;
import Main.Tool;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Entity {
    Gamepanel gp;
    public BufferedImage image1,image2;
    public BufferedImage up1,up2,down1,down2, right1,
                         right2,left1,left2,kiss;
    public BufferedImage attackUp1,attackUp2,attackDown1,attackDown2,
                         attackRight1,attackRight2,attackLeft1,attackLeft2;

    // SOLID AREA
    public Rectangle solidArea = new Rectangle(12,24,24,24);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX =12, solidAreaDefaultY =24
              ,solidAreaDefaultW =24, solidAreaDefaultH =24;
    public boolean collisionOn =false;
    public boolean collision =false;
    public boolean invincible =false;


    // CHARACTER STATUS
    public int worldX,worldY;
    public String direction="down" ;

    public boolean attacking=false;
    public boolean alive=true;
    public boolean dying =false;
    public boolean hpBarOn=false;

    // CHARACTER ATTRIBUTES

    public int speed,speedDefault;

    public String name;
//    public Entity fruit;
    public String opposite;
    public int honor;
    public int bounty;
    public int maxHP;
    public int hp;
    public int maxMana;
    public int mana;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int belly;
    public Entity currentWeapon;
    public Fruit currentFruit;
    public Entity currentAccessory;

    // ITEM ATTRIBUTES
    public int attackValue;
    public int defenseValue;
    public String description="";
    public int useCost;
    public int value;
    public boolean stackable=false;
    public int amount=0;
    public boolean speedUp=false;

    public ArrayList<Entity> inventory= new ArrayList<>();
    public final int maxInventorySize=28;
//    public int quantity;


    // TYPE
    public int type;// 0=player; 1=npc; 2=monster
    public final int type_player=0;
    public final int type_npc=1;
    public final int type_monster=2;
    public final int type_sword=3;
    public final int type_accessory=4;
    public final int type_axe=5;
    public final int type_consumable=6;
    public final int type_fruit=7;
    public final int type_obstacle=8;
    public final int type_onlyPickUp=9;






    // COUNTER
    public int spriteCounter =0;
    public int spriteNum =1;
    public int standCounter=0;
    public int speedCounter=0;
    public int actionLockCounter=0;
    public int shotAvailableCounter =0;
    public int invincibleCounter=0,alphaCounter=0;
    public int dyingCounter=0;
    public int hpBarCounter=0;

    // DIALOGUE
    public String dialogue[]=new String[15];
    public int dialogueIndex=0;


    // CONSTRUCTOR

    public Entity(Gamepanel gp){
        this.gp=gp;
    }
    public int getLeftX(){
        return worldX+solidArea.x;
    }
    public int getRightX(){
        return worldX+solidArea.x+solidArea.width;
    }
    public int getTopY(){
        return worldY+solidArea.y;
    }
    public int getBottomY(){
        return worldY+solidArea.y+solidArea.height;
    }
    public int getCol(){
        return (worldX+solidArea.x)/gp.TileSize;
    }
    public int getRow(){
        return (worldY+solidArea.y)/gp.TileSize;
    }



    public BufferedImage setUp(String imageName,int width,int height){
        Tool tool=new Tool();
        BufferedImage image=null;
        try{
            image= ImageIO.read(getClass().getResourceAsStream(imageName+".png"));
            image=tool.scaleImage(image,width,height);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public void setAction(){}
    public void damageReaction(){}
    public void interact(){}
    public void checkDrop(){}
    public Color getParticleColor(){
        return new Color(0,0,0);
    }
    public int getParticleSize(){
        return 0;
    }
    public int getParticleSpeed(){
        return 0;
    }
    public int getMaxHP(){
        return 0;
    }
    public void generateParticle(Entity generate, Entity target){
        Color color = generate.getParticleColor();
        int size= generate.getParticleSize();
        int speed= generate.getParticleSpeed();
        int maxHP= generate.getMaxHP();
        Particle p1= new Particle(gp,target,color,size,speed,maxHP,-1,-1);
        gp.particleList.add(p1);
        Particle p2= new Particle(gp,target,color,size,speed,maxHP,-1,1);
        gp.particleList.add(p2);
        Particle p3= new Particle(gp,target,color,size,speed,maxHP,2,1);
        gp.particleList.add(p3);
        Particle p4= new Particle(gp,target,color,size,speed,maxHP,2,2);
        gp.particleList.add(p4);
        Particle p5= new Particle(gp,target,color,size,speed,maxHP,-1,2);
        gp.particleList.add(p5);
        Particle p6= new Particle(gp,target,color,size,speed,maxHP,1,2);
        gp.particleList.add(p6);
    }
    public void dropItem(Entity itemDrop){
        for(int i=0;i<gp.obj.length;i++){
            if(gp.obj[i]==null){
                gp.obj[i]=itemDrop;
                gp.obj[i].worldX=worldX;
                gp.obj[i].worldY=worldY;
                break;
            }
        }
    }

    public boolean use(Entity E){return false;}
    public void speak(){
        gp.gameState=gp.dialogueState;
        gp.ui.currentDialogue=dialogue[dialogueIndex];
        dialogueIndex++;

        if(dialogue[dialogueIndex]==null){
            dialogueIndex=0;
        }

        switch(gp.player.direction){
            case "up":
                direction="down";
                break;
            case "down":
                direction="up";
                break;
            case "left":
                direction="right";
                break;
            case "right":
                direction="left";
                break;
        }
    }
    public void update(){
        setAction();
        reset_HP_Mana();
        collisionOn =false;
        gp.checkV.checkTile(this);
        gp.checkV.checkObjects(this,false);
        gp.checkV.checkVatThe(this,gp.npc);
        gp.checkV.checkVatThe(this,gp.monster);
        gp.checkV.checkVatThe(this,gp.iTile);
        boolean contactPlayer=gp.checkV.checkPlayer(this);
        if(contactPlayer&&this.type ==type_monster){
            damagePlayer(attack);
        }


        if(!collisionOn) {
            switch (direction) {
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
        }
            spriteCounter++;
            if(spriteCounter >12){
                if(spriteNum ==1) spriteNum =2;
                else if(spriteNum ==2) spriteNum =1;
                spriteCounter =0;
            }

        standCounter++;
        if(standCounter>20){
            spriteNum=2;
            standCounter=0;
        }
        if(invincible){
            invincibleCounter++;
            alphaCounter++;
            if(invincibleCounter>45){
                invincible=false;
                invincibleCounter=0;
            }
        }
        speedCounter++;
        if(speedCounter>120){
            speed=speedDefault;
            speedCounter=0;
        }
    }
    public void damagePlayer(int attack){
        if(!gp.player.invincible){
            gp.playSE(8);
            int damage= attack-gp.player.defense;
            if(damage<0) damage=0;
            gp.player.hp-=damage;
            gp.ui.addMessage("HP - "+damage);
            gp.player.invincible=true;
        }

    }

    public void draw(Graphics2D g2) {
        double scaleHP=(double)gp.TileSize/maxHP;
        double hpBar=hp*scaleHP;
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.TileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.TileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.TileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.TileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1)
                        image = up1;
                    if (spriteNum == 2)
                        image = up2;
                    break;
                case "down":
                    if (spriteNum == 1)
                        image = down1;
                    if (spriteNum == 2)
                        image = down2;
                    break;
                case "right":
                    if (spriteNum == 1)
                        image = right1;
                    if (spriteNum == 2)
                        image = right2;
                    break;
                case "left":
                    if (spriteNum == 1)
                        image = left1;
                    if (spriteNum == 2)
                        image = left2;
                    break;
                default :
                    image = down1;
            }

            // monster hp bar
            if(type ==2&&hpBarOn){
                g2.setColor(new Color(10,50,50));
                g2.fillRect(screenX-2,screenY-17,gp.TileSize+4,12);
                g2.setColor(new Color(255,10,50));
                g2.fillRect(screenX, screenY - 15, (int)hpBar, 8);
                hpBarCounter++;
                if(hpBarCounter>300){
                    hpBarOn=false;
                    hpBarCounter=0;
                }
                reset_HP_Mana();
            }


            if(invincible){
                hpBarOn=true;
                hpBarCounter=0;
                if(alphaCounter>10) {
                    changeAlpha(g2,0.1F);
                    alphaCounter=0;
                }
            }
            if(dying){
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, null);

            changeAlpha(g2,1F);
        }
    }
    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        int i=5;
        if(dyingCounter<=i){changeAlpha(g2,0F);}
        if(dyingCounter>i&&dyingCounter<=2*i){changeAlpha(g2,1F);}
        if(dyingCounter>2*i&&dyingCounter<=3*i){changeAlpha(g2,0F);}
        if(dyingCounter>3*i&&dyingCounter<=4*i){changeAlpha(g2,1F);}
        if(dyingCounter>4*i&&dyingCounter<=5*i){changeAlpha(g2,0F);}
        if(dyingCounter>5*i&&dyingCounter<=6*i){changeAlpha(g2,0F);}
        if(dyingCounter>6*i&&dyingCounter<=7*i){changeAlpha(g2,1F);}
        if(dyingCounter>7*i&&dyingCounter<=8*i){changeAlpha(g2,0F);}
        if(dyingCounter>8*i){
            alive=false;
        }
    }
    public void changeAlpha(Graphics2D g2,float a){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a));
    }
    public void reset_HP_Mana(){
        if(hp<0) hp=0;
        if(hp>maxHP) hp=maxHP;
        if(mana<0) mana=0;
        if(mana>maxMana) mana=maxMana;
    }
    public int getDetected(Entity user,Entity target[],String targetName){
            int index=99;

            int nextWorldX=user.getLeftX();
            int nextWorldY=user.getTopY();
            switch(user.direction){
                case "up": nextWorldY=user.getTopY()-1;break;
                case "down": nextWorldY=user.getBottomY()+1;break;
                case "left": nextWorldX=user.getLeftX()-1;break;
                case "right": nextWorldX=user.getRightX()+1;break;
            }
            int col=nextWorldX/gp.TileSize;
            int row=nextWorldY/gp.TileSize;

            for(int i=0;i<target.length;i++){
                if(target[i]!=null){
                    if(target[i].getCol()==col&&target[i].getRow()==row&& Objects.equals(targetName, target[i].name)){
                        index=i;
                        break;
                    }
                }
            }
            return index;
    }

}
