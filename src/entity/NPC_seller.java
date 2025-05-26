package entity;

import Main.Gamepanel;
import objects.*;

public class NPC_seller extends Entity{
    public NPC_seller(Gamepanel gp) {
        super(gp);
        name="seller";
        speed=0;
        type =type_npc;
        direction = "up";
        getSellerImage();
        setDialogue();
        setItems();

    }
    public void getSellerImage(){
        up1= setUp("/npc/npc_seller",gp.TileSize,gp.TileSize);
        up2= setUp("/npc/npc_seller2",gp.TileSize,gp.TileSize);
        down1= setUp("/npc/npc_seller",gp.TileSize,gp.TileSize);
        down2= setUp("/npc/npc_seller2",gp.TileSize,gp.TileSize);
        right1= setUp("/npc/npc_seller",gp.TileSize,gp.TileSize);
        right2= setUp("/npc/npc_seller2",gp.TileSize,gp.TileSize);
        left1= setUp("/npc/npc_seller",gp.TileSize,gp.TileSize);
        left2= setUp("/npc/npc_seller2",gp.TileSize,gp.TileSize);
    }
    public void setDialogue(){
        dialogue[0]="hey noob, có tiền chứ?\ntao có đồ tốt đấy\nhãy mua vài thứ đi nào/";
//        dialogue[1]="hey noob, mầy có tiền chứ?";
//        dialogue[2]="tao có đồ tốt đấy";
//        dialogue[3]="muốn giao dịch không";
//        dialogue[4]="";
//        dialogue[5]="";
    }
    public void setItems(){
        inventory.clear();
        inventory.add(new obj_sword_wood(gp));
        inventory.add(new obj_sword_ice(gp));
        inventory.add(new obj_shield_wood(gp));
        inventory.add(new obj_shield_stone(gp));
        inventory.add(new obj_shield_legendary(gp));
        inventory.add(new obj_potion(gp));
        inventory.add(new obj_mana(gp));
        inventory.add(new obj_princess(gp));
        inventory.add(new obj_axe_wood(gp));
        inventory.add(new obj_axe_silver(gp));
        inventory.add(new obj_axe_gold(gp));
        inventory.add(new obj_appleToEat(gp));
        inventory.add(new obj_boot(gp));
    }
    public void speak(){
        gp.ui.currentDialogue=dialogue[dialogueIndex];
        dialogueIndex++;

        if(dialogue[dialogueIndex]==null){
            dialogueIndex=0;
        }
        gp.gameState=gp.tradeState;
        gp.ui.npc=this;
    }
    public void update() {
        setAction();
        reset_HP_Mana();
        collisionOn = false;
        gp.checkV.checkTile(this);
        gp.checkV.checkObjects(this, false);
        gp.checkV.checkVatThe(this, gp.npc);
        gp.checkV.checkVatThe(this, gp.monster);
        gp.checkV.checkVatThe(this, gp.iTile);
        boolean contactPlayer = gp.checkV.checkPlayer(this);
        if (contactPlayer && this.type == type_monster) {
            damagePlayer(attack);
        }


        if (!collisionOn) {
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
        if (spriteCounter > 30) {
            if (spriteNum == 1) spriteNum = 2;
            else if (spriteNum == 2) spriteNum = 1;
            spriteCounter = 0;
        }

    }
}
