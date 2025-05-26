package Main;

import entity.Entity;

import java.util.Objects;

public class eventHandler {
    Gamepanel gp;
    EventRect eventRect[][];

    int previousEventX,previousEventY;
    boolean canTouchEvent=true;
    public boolean obtainedSword=false;
    int telepotCounter=0;
    int tempCol,tempRow;
    public eventHandler(Gamepanel gp){
        this.gp=gp;
        eventRect =new EventRect[gp.maxWorldRow][gp.maxWorldCol];

        int col=0,row=0;
        while(col<gp.maxWorldCol&&row<gp.maxWorldRow){
            eventRect[row][col]=new EventRect();
            eventRect[row][col].x=23;
            eventRect[row][col].y=23;
            eventRect[row][col].width=2;
            eventRect[row][col].height=2;
            eventRect[row][col].eventRectDefaultX= eventRect[row][col].x;
            eventRect[row][col].eventRectDefaultY= eventRect[row][col].y;
            col++;
            if(col==gp.maxWorldCol){
                col=0;
                row++;
            }
        }
        eventRect[12][11].x=5;
        eventRect[12][11].y=5;
        eventRect[12][11].width=38;
        eventRect[12][11].height=38;
        eventRect[12][11].eventRectDefaultX= eventRect[12][11].x;
        eventRect[12][11].eventRectDefaultY= eventRect[12][11].y;

    }

    public void checkEvent(){
        // check player far from event 1 tile
        int xDistance=Math.abs(gp.player.worldX-previousEventX);
        int yDistance=Math.abs(gp.player.worldY-previousEventY);
        int distance=Math.max(xDistance,yDistance);
        if(distance>gp.TileSize){
            canTouchEvent=true;
        }
        if(canTouchEvent){
            if(hit(5,20,"any")){
                eventRect[5][20].eventDone=true;
                gotDamage(gp.dialogueState);
            }
            else if(hit(12,20,"any")){
                eventRect[12][20].eventDone=true;
                gotDamage(gp.dialogueState);
            }
            else if(hit(24,38,"any")){
                eventRect[24][38].eventDone=true;
                gotDamage(gp.dialogueState);
            }
            else if(hit(19,42,"any")){
                eventRect[19][42].eventDone=true;
                gotDamage(gp.dialogueState);
            }
            else if(hit(7,12,"any")){
                eventRect[7][12].eventDone=true;
                gotDamage(gp.dialogueState);
            }
            else if(hit(28,36,"any")){
                eventRect[28][36].eventDone=true;
                gotDamage(gp.dialogueState);
            }
            else if(hit(23,36,"any")){
                eventRect[23][36].eventDone=true;
                gotDamage(gp.dialogueState);
            }
            else if(hit(50,21,"any")){
                eventRect[50][21].eventDone=true;
                gotDamage(gp.dialogueState);
            }
            else if(hit(36,19,"any")){
                eventRect[36][19].eventDone=true;
                gotDamage(gp.dialogueState);
            }else if(hit(24,19,"any")){
                eventRect[24][19].eventDone=true;
                gotDamage(gp.dialogueState);
            }else if(hit(11,56,"any")){
                eventRect[11][56].eventDone=true;
                gotDamage(gp.dialogueState);
            }else if(hit(45,19,"any")){
                eventRect[45][19].eventDone=true;
                gotDamage(gp.dialogueState);
            }else if(hit(36,24,"any")){
                eventRect[36][24].eventDone=true;
                gotDamage(gp.dialogueState);
            }else if(hit(25,37,"any")){
                eventRect[25][37].eventDone=true;
                gotDamage(gp.dialogueState);
            }else if(hit(54,29,"any")){
                eventRect[54][29].eventDone=true;
                gotDamage(gp.dialogueState);
            }else if(hit(16,54,"any")){
                eventRect[16][54].eventDone=true;
                gotDamage(gp.dialogueState);
            }
//            if(hit(14,15,"any")){
//                eventRect[14][15].eventDone=true;
//                healingShit(gp.dialogueState);
//            }

            else if(hit(10,15,"any")){
                teleport(1,1,gp.transitionState);
            }
//            if(hit(24,37,"any")){
//                teleport(12,9,gp.transitionState);
//            }
            else if(hit(12,11,"any")){
                obtainSword(gp.dialogueState);
            }
            else if(hit(13,11,"any")){
                teleport(1,34,gp.transitionState);
            }
        }

    }
    public boolean hit(int row,int col, String reqDirection){
        boolean hit=false;

        gp.player.solidArea.x=gp.player.worldX+ gp.player.solidArea.x;
        gp.player.solidArea.y=gp.player.worldY+ gp.player.solidArea.y;

        eventRect[row][col].x=col*gp.TileSize+ eventRect[row][col].x;
        eventRect[row][col].y=row*gp.TileSize+ eventRect[row][col].y;

        if(gp.player.solidArea.intersects(eventRect[row][col]) && !eventRect[row][col].eventDone){
            if(Objects.equals(gp.player.direction, reqDirection)||reqDirection.contentEquals("any")){
                hit = true;
                previousEventX=gp.player.worldX;
                previousEventY=gp.player.worldY;
            }
        }

        gp.player.solidArea.x= gp.player.solidAreaDefaultX;
        gp.player.solidArea.y= gp.player.solidAreaDefaultY;

        eventRect[row][col].x=eventRect[row][col].eventRectDefaultX;
        eventRect[row][col].y=eventRect[row][col].eventRectDefaultY;

        return hit;
    }
    public void gotDamage(int gameState){
//        gp.gameState=gameState;
//        gp.ui.currentDialogue="cẩn thận dính bẫy...";
//        gp.player.hp-=(int)(0.2*gp.player.hp);
//        gp.playSE(3);
//        canTouchEvent=false;
    }
    public void healingShit(int gameState){

        if(gp.keyH.spacePress){

            gp.gameState=gameState;
            gp.playSE(4);
            gp.player.hp=gp.player.maxHP;
            gp.player.mana=gp.player.maxMana;

            gp.player.attackCanceled=true;
            gp.setOb.setMonster();
        }
    }
    public void teleport(int row,int col,int gameState){
        gp.gameState=gameState;
        tempCol=col;
        tempRow=row;
        canTouchEvent=false;
        gp.playSE(5);
    }
    public void obtainSword( int gameState){
        if(gp.monster[26]==null&&gp.monster[27]==null&&gp.monster[28]==null&&gp.monster[29]==null&&gp.monster[30]==null&& gp.monster[31]==null&&gp.monster[32]==null&&gp.monster[33]==null){
            gp.gameState=gp.dialogueState;
            gp.ui.currentDialogue="ngươi cũng khá mạnh đấy!\nđược rồi ta sẽ cùng ngươi đi chinh chiến..";
//            gp.ui.addMessage("bạn nhận được thanh kiếm bị nguyền rủa!");
            eventRect[12][11].eventDone=true;
            obtainedSword=true;

        }
        else{
            gp.gameState=gp.dialogueState;
            gp.ui.currentDialogue="biến đi\nđồ yếu đuối!?";
            canTouchEvent=false;
        }

    }
    public void speak(Entity npc){
//        if(gp.keyH.spacePress){
            gp.gameState=gp.dialogueState;
            gp.player.attackCanceled=true;
            npc.speak();
//        }
    }
}
