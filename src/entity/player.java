package entity;
import Main.keyHandler;
import Main.Gamepanel;
import objects.*;


import java.awt.*;
import java.awt.image.BufferedImage;


public class player extends Entity {

    keyHandler keyH;
    int preHP,preMana;
    public boolean end=false;
    boolean check1=true,check2=true,check3=true,check4=true,check5=true;

    public final int screenX;

    public final int screenY;
   //dung cu cua player
    public int relives=1;
    boolean checkQuantity=false;

    public boolean attackCanceled=false;
    public int wood=0;
    int speedCounter=0;



    public player(Gamepanel gp,keyHandler keyH){
        super(gp);
        this.keyH=keyH;
        type =type_player;
        screenX=gp.screenWidth/2-(gp.TileSize/2);
        screenY=gp.screenHeight/2-(gp.TileSize/2);
//solidArea
        solidArea =new Rectangle();
        solidArea.x=12;
        solidArea.y=16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width=24;
        solidArea.height=30;
        solidAreaDefaultW = solidArea.width;
        solidAreaDefaultH = solidArea.height;


        setGiaTriMacDinh();
        getPlayerAttackImage();
        setPlayerItem();
        getPlayerImage();
    }


    public void setGiaTriMacDinh(){

        setDefaultPositions();
        setPlayerItem();
        speedDefault=4;
        speed=speedDefault;
        direction="down";
        // player status
        level=1;
        strength=1;
        dexterity=1;
        exp=0;
        nextLevelExp=20;
        belly=0;
        maxHP=100;
        preHP=maxHP;
        hp=maxHP;
        maxMana=100;
        preMana=maxMana;
        mana=maxMana;

        invincible=false;
    }
    public void setDefaultPositions(){
        worldX=37*gp.TileSize;
        worldY=23*gp.TileSize;
        direction="down";
    }
    public void restoredLifeMana(){
        hp=maxHP;
        mana=maxMana;
        invincible=false;
    }
    public void setPlayerItem(){
        inventory.clear();
        currentWeapon= new obj_sword_wood(gp);
        currentWeapon= new obj_axe_wood(gp);
        currentAccessory= new obj_shield_wood(gp);
        inventory.add(currentWeapon);
        inventory.add(currentAccessory);
//        canObtainItem(new obj_wood(gp));
//        canObtainItem(new obj_wood(gp));
//        canObtainItem(new obj_wood(gp));
//        canObtainItem(new obj_wood(gp));
//        canObtainItem(new obj_wood(gp));
//        canObtainItem(new obj_wood(gp));
//        canObtainItem(new obj_wood(gp));
//        canObtainItem(new obj_wood(gp));
//        canObtainItem(new obj_wood(gp));
//        canObtainItem(new obj_wood(gp));
//        canObtainItem(new obj_wood(gp));
//        canObtainItem(new obj_wood(gp));
//        canObtainItem(new obj_wood(gp));
//        canObtainItem(new obj_wood(gp));
//        canObtainItem(new obj_wood(gp));
//        canObtainItem(new obj_mana(gp));
//        canObtainItem(new obj_mana(gp));
//        canObtainItem(new obj_mana(gp));
//        canObtainItem(new obj_mana(gp));
//        canObtainItem(new obj_mana(gp));
//        canObtainItem(new obj_mana(gp));
//        canObtainItem(new obj_mana(gp));
//        canObtainItem(new obj_mana(gp));
//        canObtainItem(new obj_mana(gp));
//        canObtainItem(new obj_mana(gp));
//        canObtainItem(new obj_potion(gp));
//        canObtainItem(new obj_potion(gp));
//        canObtainItem(new obj_potion(gp));
//        canObtainItem(new obj_potion(gp));
//        canObtainItem(new obj_potion(gp));
//        canObtainItem(new obj_potion(gp));
//        canObtainItem(new obj_potion(gp));
//        canObtainItem(new obj_potion(gp));
//        canObtainItem(new obj_potion(gp));
//        canObtainItem(new obj_potion(gp));
//        canObtainItem(new obj_potion(gp));
//        canObtainItem(new obj_potion(gp));
//        canObtainItem(new obj_potion(gp));
//        canObtainItem(new obj_potion(gp));
//        canObtainItem(new obj_potion(gp));
//        canObtainItem(new obj_potion(gp));
//        canObtainItem(new obj_appleToEat(gp));
//        canObtainItem(new obj_appleToEat(gp));
//        canObtainItem(new obj_appleToEat(gp));
//        canObtainItem(new obj_appleToEat(gp));
//        canObtainItem(new obj_appleToEat(gp));
//        canObtainItem(new obj_appleToEat(gp));
//        canObtainItem(new obj_appleToEat(gp));
//        canObtainItem(new obj_appleToEat(gp));
//        canObtainItem(new obj_scroll(gp));





        attack=getAttack();
        defense=getDefense();
        maxHP=getHP();
        maxMana=getMana();
    }
    public int getDexterity(){
       return  dexterity+ currentWeapon.dexterity+ currentAccessory.dexterity;
    }
    public int getStrength(){
        return strength+ currentWeapon.strength+ currentAccessory.strength;
    }
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        int strength1=getStrength();
        return strength1* (currentWeapon.attackValue+currentAccessory.attackValue);
    }
    public int getDefense(){
        int dexterity1=getDexterity();
        return (dexterity1)* (currentAccessory.defenseValue+currentWeapon.defenseValue);
    }
    public int getHP(){
        int h=maxHP-preHP;
        return maxHP+currentAccessory.hp+currentWeapon.hp-h;}
    public int getMana(){
        int h=maxMana-preMana;
        return preMana+currentAccessory.mana+currentWeapon.mana-h;}
    public void getPlayerImage(){
//            up1= setUp("/player/boy_up1",gp.TileSize,gp.TileSize);
//            up2= setUp("/player/boy_up2",gp.TileSize,gp.TileSize);
//            down1= setUp("/player/boy_down2",gp.TileSize,gp.TileSize);
//            down2= setUp("/player/boy_down1",gp.TileSize,gp.TileSize);
//            right1= setUp("/player/boy_right2",gp.TileSize,gp.TileSize);
//            right2= setUp("/player/boy_right1",gp.TileSize,gp.TileSize);
//            left1= setUp("/player/boy_left2",gp.TileSize,gp.TileSize);
//            left2= setUp("/player/boy_left1",gp.TileSize,gp.TileSize);
//            kiss= setUp("/player/kiss1",gp.TileSize,gp.TileSize);
//        up1= setUp("/player/main_up1",gp.TileSize,gp.TileSize);
//        up2= setUp("/player/main_up2",gp.TileSize,gp.TileSize);
//        down1= setUp("/player/main_down1",gp.TileSize,gp.TileSize);
//        down2= setUp("/player/main_down2",gp.TileSize,gp.TileSize);
//        right1= setUp("/player/main_right1",gp.TileSize,gp.TileSize);
//        right2= setUp("/player/main_right2",gp.TileSize,gp.TileSize);
//        left1= setUp("/player/main_left1",gp.TileSize,gp.TileSize);
//        left2= setUp("/player/main_left2",gp.TileSize,gp.TileSize);
//        kiss= setUp("/player/kiss1",gp.TileSize,gp.TileSize);
        up1= setUp("/player/human_up1",gp.TileSize,gp.TileSize);
        up2= setUp("/player/human_up2",gp.TileSize,gp.TileSize);
        down1= setUp("/player/human_down1",gp.TileSize,gp.TileSize);
        down2= setUp("/player/human_down2",gp.TileSize,gp.TileSize);
        right1= setUp("/player/human_right1",gp.TileSize,gp.TileSize);
        right2= setUp("/player/human_right_2",gp.TileSize,gp.TileSize);
        left1= setUp("/player/human_left1",gp.TileSize,gp.TileSize);
        left2= setUp("/player/human_left_2",gp.TileSize,gp.TileSize);
        kiss= setUp("/player/kiss1",gp.TileSize,gp.TileSize);
    }
    public void getPlayerAttackImage(){

//        attackUp1= setUp("/player/boyAttack_up2",gp.TileSize,2*gp.TileSize);
//        attackUp2= setUp("/player/boyAttack_up1",gp.TileSize,2*gp.TileSize);
//        attackDown1= setUp("/player/boyAttack_down2",gp.TileSize,2*gp.TileSize);
//        attackDown2= setUp("/player/boyAttack_down1",gp.TileSize,2*gp.TileSize);
//        attackRight1= setUp("/player/boyAttack_right2",2*gp.TileSize,gp.TileSize);
//        attackRight2= setUp("/player/boyAttack_right1",2*gp.TileSize,gp.TileSize);
//        attackLeft1= setUp("/player/boyAttack_left2",2*gp.TileSize,gp.TileSize);
//        attackLeft2= setUp("/player/boyAttack_left1",2*gp.TileSize,gp.TileSize);
//        kiss= setUp("/player/kiss1",gp.TileSize,gp.TileSize);

        if(currentWeapon.type==type_sword){
            attackUp1= setUp("/player/human_at_up1_sw",gp.TileSize,2*gp.TileSize);
            attackUp2= setUp("/player/human_at_up2_sw",gp.TileSize,2*gp.TileSize);
        attackDown1= setUp("/player/human_at_down1_sw",gp.TileSize,2*gp.TileSize);
        attackDown2= setUp("/player/human_at_down2_sw",gp.TileSize,2*gp.TileSize);
        attackRight1= setUp("/player/human_at_right1_sw",2*gp.TileSize,gp.TileSize);
        attackRight2= setUp("/player/human_at_right2_sw",2*gp.TileSize,gp.TileSize);
        attackLeft1= setUp("/player/human_at_left1_sw",2*gp.TileSize,gp.TileSize);
        attackLeft2= setUp("/player/human_at_left2_sw",2*gp.TileSize,gp.TileSize);
        }
        else if(currentWeapon.type==type_axe){
            attackUp1= setUp("/player/human_at_up1_axe",gp.TileSize,2*gp.TileSize);
            attackUp2= setUp("/player/human_at_up2_axe",gp.TileSize,2*gp.TileSize);
            attackDown1= setUp("/player/human_at_down1_axe",gp.TileSize,2*gp.TileSize);
            attackDown2= setUp("/player/human_at_down2_axe",gp.TileSize,2*gp.TileSize);
            attackRight1= setUp("/player/human_at_right1_axe",2*gp.TileSize,gp.TileSize);
            attackRight2= setUp("/player/human_at_right2_axe",2*gp.TileSize,gp.TileSize);
            attackLeft1= setUp("/player/human_at_left1_axe",2*gp.TileSize,gp.TileSize);
            attackLeft2= setUp("/player/human_at_left2_axe",2*gp.TileSize,gp.TileSize);
        }
    }


    public void update(){
//        System.out.println(hp+"/"+maxHP+"/"+preHP);
        getPlayerAttackImage();
        checkLevelUp();
        // gray
        if(checkMonsterDie(0,6)&&check1){
            gp.gameState=gp.dialogueState;
            gp.ui.currentDialogue="bạn đã tiêu diệt hết quái ở vùng này\nhãy di chuyển tới nơi tiếp theo!\nbạn nhận được 500 exp";
            gp.ui.addMessage("exp + 500");
            exp+=500;
            check1=false;
        }
        // troll
        else if(checkMonsterDie(7,14)&&check2){
            gp.gameState=gp.dialogueState;
            gp.ui.currentDialogue="bạn đã tiêu diệt hết quái ở vùng này\nhãy di chuyển tới nơi tiếp theo!\nbạn nhận được 3000 exp";
            gp.ui.addMessage("exp + 3000");
            exp+=3000;
            check2=false;
        }
        //skeleton
        else if(checkMonsterDie(26,33)&&check3){
            gp.gameState=gp.dialogueState;
            gp.ui.currentDialogue="bạn đã tiêu diệt hết quái ở vùng này\nhãy di chuyển tới nơi tiếp theo!\nbạn nhận được 20000 exp";
            gp.ui.addMessage("exp + 20000");
            exp+=20000;
            check3=false;
        }
        // orc
        else if(checkMonsterDie(15,20)&&check4){
            gp.gameState=gp.dialogueState;
            gp.ui.currentDialogue="bạn đã tiêu diệt hết quái ở vùng này\nbạn nhận được 125000 exp\nbạn nhận được vật phẩm lạ!!!";
            gp.ui.addMessage("exp + 125000");
            gp.player.canObtainItem(new obj_key1(gp));
            exp+=125000;
            check4=false;
        }

        else if(checkMonsterDie(21,25)&&check5){
            gp.gameState=gp.dialogueState;
            gp.ui.currentDialogue="bạn đã tiêu diệt hết quái ở vùng này\nhãy di chuyển tới nơi tiếp theo!\nbạn nhận được 750000 exp";
            gp.ui.addMessage("exp + 750000");
            exp+=750000;
            check5=false;
        }



        if(attacking){
           attacking();
        }

        else if(keyH.len|| keyH.phai||keyH.trai||keyH.xuong||keyH.spacePress||keyH.use) {


            if (keyH.len) {
                direction = "up";
            } else if (keyH.xuong) {
                direction = "down";
            } else if (keyH.trai) {
                direction = "left";
            } else if (keyH.phai) {
                direction = "right";
            } else if (keyH.use) {
                direction = "use";
            }



            collisionOn = false;
            // check tiles collision
            gp.checkV.checkTile(this);


            // check objects collision
            int objIndex = gp.checkV.checkObjects(this, true);
            pickUpObj(objIndex);

            // check npc collision
            int npcIndex = gp.checkV.checkVatThe(this, gp.npc);
            interactNPC(npcIndex);

            // check monster collision
            int monsterIndex = gp.checkV.checkVatThe(this, gp.monster);
            contactMonster(monsterIndex);

            // check interactive tile collision
            int tileIndex = gp.checkV.checkVatThe(this, gp.iTile);

            // check event
            gp.eHandler.checkEvent();




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
                    case "use":
                        if (relives > 0) {
                            setGiaTriMacDinh();
                            relives--;
                        }
                        break;
                }
            }
            if(!attackCanceled&&gp.keyH.spacePress){


                gp.playSE(9);
                attacking=true;
                spriteCounter=0;

            }

            attackCanceled=false;
            gp.keyH.spacePress = false;


            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) spriteNum = 2;
                else if (spriteNum == 2) spriteNum = 1;
                spriteCounter = 0;
            }
        }
        else {
            standCounter++;
            if (standCounter > 20) {
                spriteNum = 1;
                standCounter = 0;
            }
        }

        if(gp.keyH.shootPress&&!currentFruit.alive&&
                shotAvailableCounter ==120 && currentFruit.haveResource(this)){
            // set default
            currentFruit.set(worldX,worldY,direction,true,this);

            // subtract cost
            currentFruit.subtractResource(this);

            // add to the list
            gp.fruitList.add(currentFruit);

            gp.playSE(13);
            // reset counter
            shotAvailableCounter =0;

        }

        if(shotAvailableCounter <120){
            shotAvailableCounter++;

        }

        if(invincible){
            invincibleCounter++;
            alphaCounter++;
            if(invincibleCounter>90){
                invincible=false;
                invincibleCounter=0;
            }
        }
        if(speedUp){
            speedCounter++;
            if(speedCounter>1800){
                speed=speedDefault;
                speedUp=false;
                speedCounter=0;
            }
        }
        reset_HP_Mana();
        if(hp<=0){
            gp.stopMusic();
            gp.gameState=gp.gameOverState;
            gp.playSE(3);
        }

    }
    public void attacking(){
        spriteCounter++;
        if(spriteCounter<=3){
            spriteNum=1;
        }
        if(spriteCounter>3&&spriteCounter<=30){
            spriteNum=2;

            //SAVE THE CURRENT STATE
            int currentWorldX=worldX;
            int currentWorldY=worldY;
            int solidAreaWidth=solidArea.width;
            int solidAreaHeight=solidArea.height;
            //ATTACKING
            switch(direction){
                case "up":
                    worldY-=attackArea.height;
                    solidArea.width=attackArea.width;
                    solidArea.height=2*attackArea.height;
//                    System.out.println("up "+solidArea.width + " " + solidArea.height);
                    break;
                case "down":
                    worldY+=attackArea.height/4;
                    solidArea.width=attackArea.width;
                    solidArea.height=2*attackArea.height;
//                    System.out.println("down "+solidArea.width + " " + solidArea.height);
                    break;
                case "right":
                    worldX+=attackArea.width/4;
                    solidArea.width=2*attackArea.width;
                    solidArea.height=attackArea.height;
                    break;
                case "left":
                    worldX-=attackArea.width;
                    solidArea.width=2*attackArea.width;
                    solidArea.height=attackArea.height;
                    break;
            }

            //check monster collision
            int monsterIndex=gp.checkV.checkVatThe(this,gp.monster);
            damageMonster(monsterIndex,attack);

            //check tile collision
            int tileIndex = gp.checkV.checkVatThe(this, gp.iTile);
            damageInteractiveTile(tileIndex);

            //restored data
            worldX=currentWorldX;
            worldY=currentWorldY;
            solidArea.width=solidAreaWidth;
            solidArea.height=solidAreaHeight;
        }
        if(spriteCounter>30){
            spriteNum=1;
            attacking=false;
        }
    }


    public void contactMonster(int i){
        if(i!=99){
            if(!invincible&&!gp.monster[i].dying){
                gp.playSE(8);
                int damage= gp.monster[i].attack-defense;
                if(damage<0) damage=0;
                hp-=damage;
                gp.ui.addMessage("HP - "+damage);
                invincible =true;
            }
        }
    }
    public void damageMonster(int i, int attack){
        if(i!=99){
            if(!gp.monster[i].invincible){
                gp.playSE(14);
                int damage= attack-gp.monster[i].defense;
                if(damage<0) damage=0;
                gp.monster[i].hp-=damage;
                gp.monster[i].damageReaction();
                gp.ui.addMessage(damage+" sát thương");
                gp.monster[i].invincible=true;

                if(gp.monster[i].hp<=0){
                    gp.ui.addMessage("đã tiêu diệt "+gp.monster[i].name);
                    if(level<50) {
                        gp.ui.addMessage("exp +" + gp.monster[i].exp);
                        exp += gp.monster[i].exp;
                    }

                    gp.ui.addMessage("belly +"+gp.monster[i].belly);
                    belly+=gp.monster[i].belly;

                    gp.monster[i].dying =true;
                }
            }

        }
    }
    public void damageInteractiveTile(int i){
        if(i!=99&&gp.iTile[i].destructible&&
        !gp.iTile[i].invincible&&gp.iTile[i].isCorrectItem(this)) {
            if(gp.player.currentWeapon.name.equals("rìu gỗ")){gp.iTile[i].hp--;}
            else if(gp.player.currentWeapon.name.equals("rìu bạc")){gp.iTile[i].hp-=2;}
            if(gp.player.currentWeapon.name.equals("rìu vàng")){gp.iTile[i].hp-=5;}
            gp.iTile[i].playSE();
            gp.iTile[i].invincible=true;
            // generalParticle
            generateParticle(gp.iTile[i],gp.iTile[i]);
            if (gp.iTile[i].hp <= 0) {
                gp.iTile[i] = gp.iTile[i].getDestroyForm();
            }
        }
    }
    public void checkLevelUp(){
        if(exp>= nextLevelExp){
            level++;
            if(level==50){
                gp.ui.addMessage("bạn đã level max, không thể nhận thêm exp");
            }else {
                exp -= nextLevelExp;
                nextLevelExp = (int) (nextLevelExp * 1.2);
//            int temp=maxHP;
//            int temp1=maxMana;
                maxHP = preHP += 100;
                maxMana = preMana += 100;
//            if((hp<(int)(0.8*temp)))hp=(int)(0.8*maxHP);
//            if((mana<(int)(0.8*temp1)))mana=(int)(0.8*maxMana);
                hp = maxHP;
                mana = maxMana;
                strength++;
                dexterity++;
                currentFruit.attack++;
                currentFruit.useCost+=10;
                attack = getAttack();
                defense = getDefense();
                gp.playSE(7);
                gp.ui.addMessage("level up!");
//                gp.ui.addMessage("bạn đã lên level " + level + " bây giờ!\nbạn đã có thêm sức mạnh để thoát khỏi đây");

            }
        }
    }
    public void pickUpObj(int i){
        if(i!=99){

                // PICKUP ONLY ITEM
                if (gp.obj[i].type == type_onlyPickUp) {
                    gp.obj[i].use(this);
                    gp.obj[i] = null;
                }
                //obstacle
                else if(gp.obj[i].type == type_obstacle){
                    attackCanceled=true;
                    gp.obj[i].interact();
                }
                else {
                    // INVENTORY ITEM


                    String text="";
                    text = "bạn nhận được " + gp.obj[i].name + "!";

                    if (canObtainItem(gp.obj[i])) {
                        //try to draw item 1 time
                        gp.playSE(2);
                        gp.obj[i] = null;
                    } else if(inventory.size()==maxInventorySize){
                        text = "túi đồ đã đầy!\nkhông thể mang thêm\nhãy vứt hoặc bán đi thứ k cần thiết";
                    }
                    gp.ui.addMessage(text);

            }
        }
    }

    public void interactNPC(int index){

        if(gp.keyH.spacePress){
            if(index!=99) {
                int a = 0, b = 0, c = 0, d = 0, e = 0;
                for (int i = 0; i < inventory.size(); i++) {


                    if (inventory.get(i).name.equals("bình hp")) {
                        a = inventory.get(i).amount;
                    }
                    if (inventory.get(i).name.equals("bình mana")) {
                        b = inventory.get(i).amount;
                    }
                    if (inventory.get(i).name.equals("quả táo đỏ")) {
                        c = inventory.get(i).amount;
                    }
                    if (inventory.get(i).name.equals("bản đồ")) {
                        d = 1;
                    }
                    if (inventory.get(i).name.equals("gỗ")) {
                        e = inventory.get(i).amount;
                    }
                }
                System.out.println(a + " " + b + " " + c + " " + d + " " + e);
                if (a >= 10 && b >= 7 && c >= 5 && d == 1 && e >= 15) {
                    gp.gameState = gp.dialogueState;
                    gp.ui.currentDialogue = "anh bạn, anh đã sẵn sàng để ra khơi\nvà thoát khỏi nơi đây";
                    end=true;
                }
                else {gp.npc[index].speak();}
                attackCanceled = true;
            }

        }
    }
    public void selectItem(){
        int itemIndex=gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol,gp.ui.playerSlotRow);
        if(itemIndex<inventory.size()){
            Entity selectedItem=inventory.get(itemIndex);

            if(selectedItem.type==type_sword||selectedItem.type==type_axe){
                currentWeapon= selectedItem;
                defense = getDefense();
                attack = getAttack();
                maxHP= getHP();
                maxMana=getMana();
            }
            if(selectedItem.type==type_accessory){
                currentAccessory= selectedItem;
                defense = getDefense();
                attack = getAttack();
                maxHP=getHP();
                maxMana=getMana();
            }
            if(selectedItem.type==type_consumable){
                if(selectedItem.use(this)) {
                    if(selectedItem.amount>1) {
                        selectedItem.amount--;
                    }
                    else {
                        inventory.remove(itemIndex);
                    }
                }
            }
        }
    }
    public int searchItem(String itemName){
        int itemIndex=99;
        for(int i=0;i<inventory.size();i++){
            if(inventory.get(i).name.equals(itemName)){
                itemIndex=i;
                break;
            }
        }
        return itemIndex;
    }
    public boolean canObtainItem(Entity item){
        boolean canObtain=false;
        if(item.stackable){
            int index=searchItem(item.name);
            if(index!=99){
                inventory.get(index).amount++;
                canObtain=true;
            }
            else{
                if(inventory.size()!=maxInventorySize){
                    inventory.add(item);
                    canObtain=true;
                }
            }
        }
        else{
            if(inventory.size()!=maxInventorySize){
                inventory.add(item);
                canObtain=true;
            }
        }
        return canObtain;
    }


    public void draw(Graphics2D g2){

        BufferedImage image=null;
        int tempScreenX=screenX,tempScreenY=screenY;
        switch (direction){
            case "up":
                if(!attacking) {
                    if (spriteNum == 1) image = up1;
                    if (spriteNum == 2) image = up2;
                }
                else{
                    tempScreenY=screenY-gp.TileSize;
                    if (spriteNum == 1) image = attackUp1;
                    if (spriteNum == 2) image = attackUp2;
                }
                break;
            case "down":
                if(!attacking) {
                    if (spriteNum == 1) image = down1;
                    if (spriteNum == 2) image = down2;
                }
                else{
                    if (spriteNum == 1) image = attackDown1;
                    if (spriteNum == 2) image = attackDown2;
                }
                break;
            case "right":
                if(!attacking) {
                    if (spriteNum == 1) image = right1;
                    if (spriteNum == 2) image = right2;
                }
                else{
                    if (spriteNum == 1) image = attackRight1;
                    if (spriteNum == 2) image = attackRight2;
                }
                break;
            case "left":
                if(!attacking) {
                    if (spriteNum == 1) image = left1;
                    if (spriteNum == 2) image = left2;
                }
                else{
                    tempScreenX=screenX-gp.TileSize;
                    if (spriteNum == 1) image = attackLeft1;
                    if (spriteNum == 2) image = attackLeft2;
                }
                break;
        }

        if(invincible){
            if(alphaCounter>12) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2F));
                alphaCounter=0;
            }
        }
        g2.drawImage(image,tempScreenX,tempScreenY,null);

        // reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));

        // debug
    }
    public boolean checkMonsterDie(int start,int end){
        boolean done=true;
        for(int i=start;i<=end;i++){
            if(gp.monster[i]!=null){
                done=false;
                break;
            }
        }
        return done;
    }
}
