package Main;

import objects.obj_heart;
import entity.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class UI {
    Gamepanel gp;
    public Graphics2D g2;
    Font Arial_30,Arial_75BI,dialogueFont;
    int dem=0;
    BufferedImage HP,fullHP;
    public boolean gameFinish=false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public String currentDialogue="";
    int dialogueScreenCounter=0;
    public int commandNum=0;
    public int titleScreenState=0;
    public int playerSlotCol =0;
    public int playerSlotRow =0;

    public int npcSlotCol=0;
    public int npcSlotRow=0;


    int subState=0;
    int counter=0;
    int counterEnd=0;
    public Entity npc;
    public UI (Gamepanel gp){
        this.gp=gp;
        Arial_30= new Font("Arial", Font.PLAIN,30);
        Arial_75BI= new Font("Arial",Font.ITALIC,75);
        // dialogue//SignPainter//
        dialogueFont=new Font("Noteworthy", Font.PLAIN, 30);
        dialogueFont=Arial_30;
        // thanh HP
        Entity heart=new obj_heart(gp);
        HP=heart.image1;
        fullHP=heart.image2;
    }



    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }
    public void draw(Graphics2D g2){


        this.g2=g2;
        g2.setFont(Arial_30);
        g2.setColor(Color.white);
        if(gp.player.end){
            counterEnd++;
            if(counterEnd>300){
                gp.gameState=gp.endGameState;
                gp.player.end=false;
                counterEnd=0;
            }
        }
        // title state
        if(gp.gameState==gp.titleState){
            drawTitleScreen();
        }

        // play state
        else if(gp.gameState==gp.playState){

            int index=gp.checkV.checkVatThe(gp.player,gp.npc);
            if(index!=99){
            String text="  nhấn space\nđể tương tác";
                int screenX = gp.npc[index].worldX - gp.player.worldX + gp.player.screenX;
                int screenY = gp.npc[index].worldY - gp.player.worldY + gp.player.screenY+gp.TileSize+10;
            g2.setColor(Color.white);
            g2.setFont(dialogueFont);
            g2.setFont(g2.getFont().deriveFont(10F));
                for(String line : text.split("\n")) {
                g2.drawString(line, screenX, screenY);
                screenY += 12;
                 }
            }
            drawPlayerMana();
            drawPlayerHP();
            drawPlayerExp();
            drawMessage();

        }
        // pause state
        else if(gp.gameState==gp.pauseState){

            gp.stopMusic();
            drawPlayerHP();
            drawPlayerMana();
            drawPlayerExp();
            drawPauseScreen();
        }
        // dialogue state
        else if(gp.gameState==gp.dialogueState){
            drawDialogueScreen();
            dialogueScreenCounter++;
            if(dialogueScreenCounter>180){
                gp.gameState=gp.playState;
                dialogueScreenCounter=0;
            }
        }
        // info state
        else if(gp.gameState==gp.infoState){
            drawInfoScreen();
            drawInventory(gp.player,true);
        }
        // options state
        else if(gp.gameState==gp.optionsState){
            drawOptionsScreen();
        }
        // game over state
        else if(gp.gameState==gp.gameOverState){
            drawGameOverScreen();
        }
        // transition state
        else if(gp.gameState==gp.transitionState){
            drawtransScreen();
        }
        else if(gp.gameState==gp.tradeState){
            drawTradeScreen();
        }
        else if(gp.gameState==gp.endGameState){
            drawEndGameScreen();
        }

    }

    public void drawMessage(){
        int messX=gp.TileSize;
        int messY=3*gp.TileSize;
        g2.setFont(dialogueFont);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));

        for(int i= 0; i< message.size(); i++){
            if(message.get(i)!=null){

                if(message.get(i).contains("level up")){

                    g2.setColor(new Color(30,20,15));
                    g2.drawString(message.get(i),gp.player.screenX+2,gp.player.screenY-10 );
                    g2.setColor(new Color(30, 250, 20));
                    g2.drawString(message.get(i), gp.player.screenX, gp.player.screenY-12);
                    int counter = messageCounter.get(i) + 1;
                    messageCounter.set(i, counter);
                    messY += 30;

                }
                else {

                    g2.setColor(new Color(30, 20, 15));
                    g2.drawString(message.get(i), messX + 2, messY + 2);


                    if (message.get(i).contains("exp")||message.get(i).contains("belly")){
                        g2.setColor(Color.green);
                    } else if (message.get(i).contains("nhận")) {
                        g2.setColor(new Color(200, 50, 200));
                    } else if (message.get(i).contains(" - ")) {
                        g2.setColor(Color.red);
                    } else {
                        g2.setColor(new Color(250, 250, 250));
                    }
                    g2.drawString(message.get(i), messX, messY);
                    int counter = messageCounter.get(i) + 1;
                    messageCounter.set(i, counter);
                    messY += 30;
                }

                    if (messageCounter.get(i) > 180) {
                        message.remove(i);
                        messageCounter.remove(i);
                    }
            }
        }
    }
    public void drawTradeScreen(){
        switch(subState){
            case 0: trade_select();break;
            case 1: trade_buy();   break;
            case 2: trade_sell();  break;
        }
        gp.keyH.enterPressed=false;
    }
    public void trade_select(){
        drawDialogueScreen();
        int x=gp.TileSize*14-20;
        int y=gp.TileSize*4;
        int width=2*gp.TileSize+20;
        int height=(int)(3.5*gp.TileSize);
        drawSubWindow(x,y,width,height);
        x+=35;
        y+=gp.TileSize;

        g2.setColor(Color.WHITE);
        g2.drawString("mua",x,y);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x-25, y-20, 20, 20, 5, 5);
        if(commandNum==0) {
//                g2.drawString(">",x-gp.TileSize,y);
            g2.setColor(Color.PINK);
            g2.fillRoundRect(x - 25, y - 20, 20, 20, 5, 5);
            g2.drawString("mua", x, y);
            if(gp.keyH.enterPressed){
                subState=1;
            }
        }
        y+=gp.TileSize;
        g2.setColor(Color.WHITE);
        g2.drawString("bán",x,y);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x-25, y-20, 20, 20, 5, 5);
        if(commandNum==1) {
//                g2.drawString(">",x-gp.TileSize,y);
            g2.setColor(Color.PINK);
            g2.fillRoundRect(x - 25, y - 20, 20, 20, 5, 5);
            g2.drawString("bán", x, y);
            if(gp.keyH.enterPressed){
                subState=2;
            }
        }
        y+=gp.TileSize;
        g2.setColor(Color.WHITE);
        g2.drawString("thoát",x,y);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x-25, y-20, 20, 20, 5, 5);
        if(commandNum==2) {
//                g2.drawString(">",x-gp.TileSize,y);
            g2.setColor(Color.PINK);
            g2.fillRoundRect(x - 25, y - 20, 20, 20, 5, 5);
            g2.drawString("thoát", x, y);
            if(gp.keyH.enterPressed){
            commandNum=0;
            subState=0;
            gp.gameState=gp.dialogueState;
            currentDialogue="cứ tới trao đổi khi cần\nmày luôn được chào đón mà\nhehe.";
            }
        }


    }
    public void trade_sell(){
        drawInventory(gp.player,true);
        int x=gp.TileSize;
        int y=gp.TileSize*11;
        int w=2*gp.TileSize+5;
        int h=(int) (0.75*gp.TileSize);
        drawSubWindow(x,y,w,h);
        g2.drawString("[ESC] Quay lại",x,y+24);

        // belly
        x=gp.TileSize*11;
        y=gp.TileSize*10;
        w=8*gp.TileSize;
        h=(int) (1.5*gp.TileSize);
        drawSubWindow(x,y,w,h);
        g2.drawString("BELLY: "+gp.player.belly,x+24,y+45);
        //price window
        int itemIndex=getItemIndexOnSlot(playerSlotCol,playerSlotRow);

        if(itemIndex<gp.player.inventory.size()) {
            x = 12 * gp.TileSize + playerSlotCol * gp.TileSize + 10;
            y = 2 * gp.TileSize + playerSlotRow * gp.TileSize + 10;
            w = (int) (gp.TileSize * 2.5);
            h = gp.TileSize;
            drawSubWindow(x, y, w, h);
            int price = (int) (0.8 * gp.player.inventory.get(itemIndex).belly);
            String text = price + "$";
            x += 30;
            y += 30;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15));
            g2.setColor(Color.green);
            g2.drawString(text, x, y);
            //buy item
            if (gp.keyH.enterPressed) {
                if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
                        gp.player.inventory.get(itemIndex) == gp.player.currentAccessory) {
                    commandNum = 0;
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "này đừng bán đồ đang mặc chứ?";
                } else {
                    if(gp.player.inventory.get(itemIndex).amount>1){
                        gp.player.inventory.get(itemIndex).amount--;
                    }
                    else {
                        gp.player.inventory.remove(itemIndex);
                    }
                    gp.player.belly += price;
                }
            }
        }

    }
    public void trade_buy(){
        drawInventory(gp.player,false);
        drawInventory(npc,true);

        int x=gp.TileSize;
        int y=gp.TileSize*11;
        int w=2*gp.TileSize+5;
        int h=(int) (0.75*gp.TileSize);
        drawSubWindow(x,y,w,h);
        g2.drawString("[ESC] Quay lại",x,y+24);

        // belly
         x=gp.TileSize*11;
         y=gp.TileSize*10;
         w=8*gp.TileSize;
         h=(int) (1.5*gp.TileSize);
        drawSubWindow(x,y,w,h);
        g2.drawString("BELLY: "+gp.player.belly,x+24,y+45);
        //price window
        int itemIndex=getItemIndexOnSlot(npcSlotCol,npcSlotRow);
        if(itemIndex<npc.inventory.size()) {
            x = 2 * gp.TileSize + npcSlotCol * gp.TileSize + 10;
            y = 2 * gp.TileSize + npcSlotRow * gp.TileSize + 10;
            w = (int) (gp.TileSize * 2.5);
            h = gp.TileSize;
            drawSubWindow(x, y, w, h);
            int price = npc.inventory.get(itemIndex).belly;
            String text = price + "$";
            x += 30;
            y += 30;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15));
            g2.setColor(Color.green);
            g2.drawString(text, x, y);
            //buy item
            if (gp.keyH.enterPressed) {

                if (npc.inventory.get(itemIndex).belly > gp.player.belly) {
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "erhhh không đủ tiền đừng tới tìm ta!\nhãy kiếm tiền đii";
                    drawDialogueScreen();
                }
                else{
                    if(gp.player.canObtainItem(npc.inventory.get(itemIndex))){
                        gp.player.belly -= npc.inventory.get(itemIndex).belly;
                    }
                    else{
                        subState = 0;
                        gp.gameState = gp.dialogueState;
                        currentDialogue = "ngươi không thể nhận thêm vật phẩm\nvới túi đồ đây ắp như vậy!";

                    }
                }
            }
        }


    }
    public void drawtransScreen(){
        counter++;
        g2.setColor(new Color(10,10,10,counter*4));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        if(counter==63){
            counter=0;
            gp.gameState=gp.playState;
            gp.player.worldX=gp.eHandler.tempCol*gp.TileSize;
            gp.player.worldY=gp.eHandler.tempRow*gp.TileSize;
            gp.eHandler.previousEventX=gp.player.worldX;
            gp.eHandler.previousEventY=gp.player.worldY;

        }
    }
    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(Arial_75BI);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110F));
        text ="GAME OVER";
        g2.setColor(Color.black);
        x=getXForCenterOfText(text);
        y=gp.TileSize*5;
        g2.drawString(text,x+5,y+5);
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        // choi lai
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        text="Quay về";
        x=7*gp.TileSize;
        y+=3* gp.TileSize;
        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x-25, y-20, 20, 20, 5, 5);
        if(commandNum==0) {
//                g2.drawString(">",x-gp.TileSize,y);
            g2.setColor(Color.PINK);
            g2.fillRoundRect(x - 25, y - 20, 20, 20, 5, 5);
            g2.drawString(text, x, y);
            if(gp.keyH.enterPressed){
                    commandNum=0;
                    subState=0;
                    gp.playMusic(0);
                    gp.goBack();
                    gp.gameState=gp.playState;
            }
        }
        // hoi sinh
        text="Hồi sinh<-10000$>";

        y+= gp.TileSize;
        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x-25, y-20, 20, 20, 5, 5);
        if(commandNum==1) {
//                g2.drawString(">",x-gp.TileSize,y);
            g2.setColor(Color.PINK);
            g2.fillRoundRect(x - 25, y - 20, 20, 20, 5, 5);
            g2.drawString(text, x, y);
            if(gp.keyH.enterPressed&&gp.player.belly>10000){
                gp.gameState=gp.playState;
                gp.revive();
                gp.playMusic(0);
                gp.player.belly-=10000;
                gp.player.invincible=true;
                addMessage("- 10000$");
                commandNum=0;
                subState=0;
            }
            else if(gp.keyH.enterPressed&&gp.player.belly<10000){
                gp.gameState=gp.dialogueState;
                currentDialogue="bạn không đủ tiền!\nhãy kiền tiền để được hồi sinh tại chỗ";
            }
        }
        // back
        text="Thoát game";
        y+= gp.TileSize;
        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x-25, y-20, 20, 20, 5, 5);
        if(commandNum==2) {
//                g2.drawString(">",x-gp.TileSize,y);
            g2.setColor(Color.PINK);
            g2.fillRoundRect(x - 25, y - 20, 20, 20, 5, 5);
            g2.drawString(text, x, y);
            if(gp.keyH.enterPressed){
                gp.gameState=gp.titleState;
                gp.restart();
                titleScreenState=0;
                commandNum=0;
                subState=0;
            }
        }

        gp.keyH.enterPressed=false;

    }
    public void drawOptionsScreen(){
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(30F));

        int frameX=gp.TileSize*4;
        int frameY=gp.TileSize;
        int frameWidth=gp.TileSize*12;
        int frameHeight=11*gp.TileSize;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        switch(subState){
            case 0: options_top(frameX,frameY); break;
            case 1: optionsControl(frameX,frameY); break;
            case 2: optionsEndgame(frameX,frameY); break;
            case 3: optionsHuongdan(frameX,frameY); break;
            case 4: optionsDonggop(frameX,frameY); break;


        }
        gp.keyH.enterPressed=false;

    }
    public void optionsDonggop(int frameX,int frameY){
        int textX;
        int textY;
        // title
        String text="Đóng góp ý kiến";
        textX=getXForCenterOfText(text);
        textY=frameY+gp.TileSize;
        g2.drawString(text,textX,textY);

        textX=frameX+gp.TileSize;
        textY=frameY+gp.TileSize*2;
        g2.setFont(dialogueFont);
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC,22F));

        currentDialogue="Mọi ý kiến muốn đóng góp xin gửi về mail\ndung.pt225295@sis.hust.edu.vn"
                +"\nRất vinh dự được tiếp nhận ý kiến,\nđóng góp từ các bạn!!!";
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,textX,textY);
            textY+=27;
        }
        textX=frameX+gp.TileSize;
        textY=frameY+gp.TileSize*10;
        g2.drawString("Quay lại",textX,textY);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(textX-25, textY-20, 20, 20, 5, 5);
        if(commandNum==0) {
//                g2.drawString(">",x-gp.TileSize,y);
            g2.setColor(Color.PINK);
            g2.fillRoundRect(textX - 25, textY - 20, 20, 20, 5, 5);
            g2.drawString("Quay lại", textX, textY);
            if(gp.keyH.enterPressed){
                subState=0;
                commandNum=5;
            }
        }
    }
    public void optionsHuongdan(int frameX,int frameY){
        int textX;
        int textY;
        // title
        String text="Hướng dẫn";
        textX=getXForCenterOfText(text);
        textY=frameY+gp.TileSize;
        g2.drawString(text,textX,textY);

        textX=frameX+gp.TileSize;
        textY=frameY+gp.TileSize*2;
        g2.setFont(dialogueFont);
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC,22F));

        currentDialogue="bạn bị bắt tới đảo hoang để rèn luyện\nhãy hoàn thành các bài khảo sát\nkiếm đủ các vật phẩm cần thiết\nmà npc yêu cầu để rời khỏi nơi này\nchúc bạn may mắn!";
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,textX,textY);
            textY+=27;
        }
        textX=frameX+gp.TileSize;
        textY=frameY+gp.TileSize*10;
        g2.drawString("Back",textX,textY);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(textX-25, textY-20, 20, 20, 5, 5);
        if(commandNum==0) {
//                g2.drawString(">",x-gp.TileSize,y);
            g2.setColor(Color.PINK);
            g2.fillRoundRect(textX - 25, textY - 20, 20, 20, 5, 5);
            g2.drawString("Back", textX, textY);
            if(gp.keyH.enterPressed){
                subState=0;
                commandNum=3;
            }
        }
    }
    public void options_top(int frameX,int frameY){
        int textX;
        int textY;
        // title
        String text="Cài đặt";
        textX=getXForCenterOfText(text);
        textY=frameY+gp.TileSize;
        g2.drawString(text,textX,textY);
        // Music
        textX=frameX+gp.TileSize;
        textY+=2*gp.TileSize;
        g2.setColor(Color.WHITE);
        g2.drawString("Nhạc",textX,textY);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(textX-25, textY-20, 20, 20, 5, 5);
        if(commandNum==0) {
//                g2.drawString(">",x-gp.TileSize,y);
            g2.setColor(Color.PINK);
            g2.fillRoundRect(textX - 25, textY - 20, 20, 20, 5, 5);
            g2.drawString("Nhạc", textX, textY);
        }
        // SE
        textY+=gp.TileSize;
        g2.setColor(Color.WHITE);
        g2.drawString("Âm thanh hiệu ứng",textX,textY);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(textX-25, textY-20, 20, 20, 5, 5);
        if(commandNum==1) {
//                g2.drawString(">",x-gp.TileSize,y);
            g2.setColor(Color.PINK);
            g2.fillRoundRect(textX - 25, textY - 20, 20, 20, 5, 5);
            g2.drawString("Âm thanh hiệu ứng", textX, textY);
        }
        // Control
        textY+=gp.TileSize;
        g2.setColor(Color.WHITE);
        g2.drawString("Điều khiển",textX,textY);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(textX-25, textY-20, 20, 20, 5, 5);
        if(commandNum==2){
//                g2.drawString(">",x-gp.TileSize,y);
            g2.setColor(Color.PINK);
            g2.fillRoundRect(textX-25, textY-20, 20, 20, 5, 5);
            g2.drawString("Điều khiển",textX,textY);
            if(gp.keyH.enterPressed){
                subState=1;
                commandNum=0;
            }
        }
        textY+=gp.TileSize;
        // Hướng dẫn

        g2.setColor(Color.WHITE);
        g2.drawString("Hướng dẫn",textX,textY);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(textX-25, textY-20, 20, 20, 5, 5);
        if(commandNum==3){
//                g2.drawString(">",x-gp.TileSize,y);
            g2.setColor(Color.PINK);
            g2.fillRoundRect(textX-25, textY-20, 20, 20, 5, 5);
            g2.drawString("Hướng dẫn",textX,textY);
            if(gp.keyH.enterPressed){
                subState=3;
                commandNum=0;
            }
        }

        // End game
        textY+=gp.TileSize;
        g2.setColor(Color.WHITE);
        g2.drawString("Thoát game",textX,textY);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(textX-25, textY-20, 20, 20, 5, 5);
        if(commandNum==4){
//                g2.drawString(">",x-gp.TileSize,y);
            g2.setColor(Color.PINK);
            g2.fillRoundRect(textX-25, textY-20, 20, 20, 5, 5);
            g2.drawString("Thoát game",textX,textY);
            if(gp.keyH.enterPressed){
                subState=2;
                commandNum=0;
            }
        }
        textY+=gp.TileSize;
        g2.setColor(Color.WHITE);
        g2.drawString("Đóng góp ý kiến",textX,textY);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(textX-25, textY-20, 20, 20, 5, 5);
        if(commandNum==5){
//                g2.drawString(">",x-gp.TileSize,y);
            g2.setColor(Color.PINK);
            g2.fillRoundRect(textX-25, textY-20, 20, 20, 5, 5);
            g2.drawString("Đóng góp ý kiến",textX,textY);
            if(gp.keyH.enterPressed){
                subState=4;
                commandNum=0;
            }
        }
        // Back
        textY+=2*gp.TileSize;
        g2.setColor(Color.WHITE);
        g2.drawString("Back",textX,textY);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(textX-25, textY-20, 20, 20, 5, 5);
        if(commandNum==6){
//                g2.drawString(">",x-gp.TileSize,y);
            g2.setColor(Color.PINK);
            g2.fillRoundRect(textX-25, textY-20, 20, 20, 5, 5);
            g2.drawString("Back",textX,textY);
            if(gp.keyH.enterPressed){
                gp.gameState=gp.playState;
                subState=0;
                commandNum=0;
            }
        }
        // Music volume
        g2.setColor(Color.WHITE);
        if(commandNum==0){
            g2.setColor(Color.PINK);
        }
        textX=frameX+gp.TileSize*8;
        textY=frameY+2*gp.TileSize+24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX,textY,120,20);
        int volumeWidth=24*gp.music.volumeScale;
        g2.fillRect(textX,textY,volumeWidth,20);
        // SE
        textY+=gp.TileSize;
        g2.setColor(Color.WHITE);
        if(commandNum==1){
            g2.setColor(Color.PINK);
        }
        g2.drawRect(textX,textY,120,20);
        volumeWidth=24*gp.SE.volumeScale;
        g2.fillRect(textX,textY,volumeWidth,20);


    }
    public void optionsEndgame(int frameX,int frameY){
        int textX=frameX+gp.TileSize;
        int textY=frameY+gp.TileSize*4;

        currentDialogue="BẠN MUỐN THOÁT TRÒ CHƠI?";

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,textX,textY);
            textY+=40;
        }
        String text="YES";
        textX=getXForCenterOfText(text);
        textY+=gp.TileSize*2;
        g2.drawString(text,textX,textY);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(textX-25, textY-20, 20, 20, 5, 5);
        if(commandNum==0){
//                g2.drawString(">",x-gp.TileSize,y);
            g2.setColor(Color.PINK);
            g2.fillRoundRect(textX-25, textY-20, 20, 20, 5, 5);
            g2.drawString(text,textX,textY);
            if(gp.keyH.enterPressed){
                subState=0;
                titleScreenState=0;
                gp.stopMusic();
                gp.gameState=gp.titleState;
                gp.restart();
            }
        }
        text="NO";
        textY+=gp.TileSize;
        g2.setColor(Color.WHITE);
        g2.drawString(text,textX,textY);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(textX-25, textY-20, 20, 20, 5, 5);
        if(commandNum==1){
//                g2.drawString(">",x-gp.TileSize,y);
            g2.setColor(Color.PINK);
            g2.fillRoundRect(textX-25, textY-20, 20, 20, 5, 5);
            g2.drawString(text,textX,textY);
            if(gp.keyH.enterPressed){
                subState=0;
                commandNum=4;
            }
        }
    }
    public void optionsControl(int frameX,int frameY){

        int textX;
        int textY;
        // title
        String text="Điều khiển";
        textX=getXForCenterOfText(text);
        textY=frameY+gp.TileSize+10;
        g2.drawString(text,textX,textY);

        textX=frameX+gp.TileSize-10;
        textY+= gp.TileSize;
        g2.drawString("Di chuyển",textX,textY);textY+= gp.TileSize;
        g2.drawString("Tấn công",textX,textY);textY+= gp.TileSize;
        g2.drawString("Bắn",textX,textY);textY+= gp.TileSize;
        g2.drawString("Thông tin người chơi",textX,textY);textY+= gp.TileSize;
        g2.drawString("Tạm dừng",textX,textY);textY+= gp.TileSize;
        g2.drawString("Cài đặt",textX,textY);textY+= gp.TileSize;
        g2.drawString("Tương tác",textX,textY);textY+= gp.TileSize;
        g2.drawString("Sử dụng",textX,textY);textY+= gp.TileSize;
        g2.drawString("Back",textX,textY);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(textX-25, textY-20, 20, 20, 5, 5);
        if(commandNum==0){
//                g2.drawString(">",x-gp.TileSize,y);
            g2.setColor(Color.PINK);
            g2.fillRoundRect(textX-25, textY-20, 20, 20, 5, 5);
            g2.drawString("Back",textX,textY);
            if(gp.keyH.enterPressed){
                subState=0;
                commandNum=2;
            }

        }

        textY+= gp.TileSize;
//        g2.drawString("",textX,textY);textY+= gp.TileSize;
//        g2.drawString("",textX,textY);textY+= gp.TileSize;
        int tailX=frameX+12*gp.TileSize-20;
        text="UP DOWN LEFT RIGHT";
        textX=getXForRightOfText(text,tailX);
        textY=frameY+2*gp.TileSize+10;
        g2.drawString(text,textX,textY);
        textY+=gp.TileSize;

        text="SPACE";
        textX=getXForRightOfText(text,tailX);
        g2.drawString(text,textX,textY);
        textY+=gp.TileSize;

        text="S";
        textX=getXForRightOfText(text,tailX);
        g2.drawString(text,textX,textY);
        textY+=gp.TileSize;

        text="I";
        textX=getXForRightOfText(text,tailX);
        g2.drawString(text,textX,textY);
        textY+=gp.TileSize;

        text="P";
        textX=getXForRightOfText(text,tailX);
        g2.drawString(text,textX,textY);
        textY+=gp.TileSize;

        text="O";
        textX=getXForRightOfText(text,tailX);
        g2.drawString(text,textX,textY);
        textY+=gp.TileSize;

        text="SPACE";
        textX=getXForRightOfText(text,tailX);
        g2.drawString(text,textX,textY);
        textY+=gp.TileSize;

        text="ENTER";
        textX=getXForRightOfText(text,tailX);
        g2.drawString(text,textX,textY);
        textY+=gp.TileSize;
    }
    public void drawInfoScreen(){
//        drawPlayerHP();

        // create a frame
        final int frameX=10,frameY=gp.TileSize,frameW=gp.TileSize*4, frameH=gp.TileSize*8;
        drawSubWindow(frameX,frameY,frameW,frameH);
        // text
        g2.setColor(new Color(30,250,30));
        g2.setFont(dialogueFont);
        g2.setFont(g2.getFont().deriveFont(20F));

        int textX=frameX+15;
        int textY=frameY+30;
        final int lineHeight=25;

        // names
        g2.drawString("Level",textX,textY);
        textY+=lineHeight;

        g2.drawString("HP",textX,textY);
        textY+=lineHeight;

        g2.drawString("Mana",textX,textY);
        textY+=lineHeight;

        g2.drawString("Strength",textX,textY);
        textY+=lineHeight;

        g2.drawString("Dexterity",textX,textY);
        textY+=lineHeight;

        g2.drawString("Attack",textX,textY);
        textY+=lineHeight;

        g2.drawString("Defense",textX,textY);
        textY+=lineHeight;

        g2.drawString("Exp",textX,textY);
        textY+=lineHeight;

        g2.drawString("Belly",textX,textY);
        textY+=lineHeight;

        g2.drawString("Fruit",textX,textY);
        textY+=lineHeight+22;

        g2.drawString("Weapon",textX,textY);
        textY+=lineHeight+22;

        g2.drawString("Accessory",textX,textY);
        textY+=lineHeight+20;


        // values
        int tailX=(frameX+frameW)-20;
        textY=frameY+30;
        String value;

        value=String.valueOf(gp.player.level);
        textX=getXForRightOfText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value=String.valueOf(gp.player.hp+"/"+gp.player.maxHP);
        textX=getXForRightOfText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value=String.valueOf(gp.player.mana+"/"+gp.player.maxMana);
        textX=getXForRightOfText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value=String.valueOf(gp.player.strength);
        textX=getXForRightOfText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value=String.valueOf(gp.player.dexterity);
        textX=getXForRightOfText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value=String.valueOf(gp.player.attack);
        textX=getXForRightOfText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value=String.valueOf(gp.player.defense);
        textX=getXForRightOfText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value=String.valueOf(gp.player.exp+"/"+gp.player.nextLevelExp);
        textX=getXForRightOfText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value=String.valueOf(gp.player.belly);
        textX=getXForRightOfText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;
// fruit sau them anh vo
        value=String.valueOf(gp.player.currentFruit.name);
        textX=getXForRightOfText(value,tailX);
        g2.drawString(value,textX,textY);

        g2.drawImage(gp.player.currentWeapon.down1,tailX-48,textY+12,null);
        textY+=gp.TileSize;

        g2.drawImage(gp.player.currentAccessory.down1,tailX-48,textY+15,null);
        textY+=gp.TileSize*2-4;


    }
    public void drawInventory(Entity entity,boolean cursor){

        int frameX=0;
        int frameY=0;
        int frameW=0;
        int frameH=0;
        int slotCol=0;
        int slotRow=0;
        if(entity==gp.player){
             frameX=11*gp.TileSize;
             frameY=gp.TileSize;
             frameW=gp.TileSize*8+10;
             frameH=gp.TileSize*5;
             slotCol=playerSlotCol;
             slotRow=playerSlotRow;
        }
        else{
            frameX=gp.TileSize;
            frameY=gp.TileSize;
            frameW=gp.TileSize*8+10;
            frameH=gp.TileSize*5;
            slotCol=npcSlotCol;
            slotRow=npcSlotRow;
        }


        // frame
        drawSubWindow(frameX,frameY,frameW,frameH);
        // slot
        final int slotXStart=frameX+20;
        final int slotYStart=frameY+20;
        int slotX=slotXStart;
        int slotY=slotYStart;
        int slotSize=gp.TileSize+3;

        //draw player items

        for(int i=0;i<entity.inventory.size();i++){
            if (entity.inventory.get(i) == entity.currentWeapon ||
                    entity.inventory.get(i) == entity.currentAccessory) {
                g2.setColor(Color.GREEN);
                g2.fillRoundRect(slotX, slotY, gp.TileSize, gp.TileSize, 10, 10);
            }

                g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);

            // display amount
            if(entity==gp.player&&entity.inventory.get(i).amount>0){
                g2.setFont(Arial_30);
                g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
                int amountX;
                int amountY;
                String s="x"+entity.inventory.get(i).amount;
                amountX= getXForRightOfText(s,slotX+44);
                amountY=slotY+gp.TileSize;
                g2.setColor(Color.BLACK);
                g2.drawString(s,amountX+3,amountY+3);
                g2.setColor(Color.white);
                g2.drawString(s,amountX,amountY);
            }
                slotX += slotSize;
                if (i == 6 || i == 13 || i == 20 || i == 27) {
                    slotX = slotXStart;
                    slotY += slotSize;
                }
        }

        // cursor
        if(cursor) {
            int cursorX = slotXStart + (slotSize * slotCol);
            int cursorY = slotYStart + (slotSize * slotRow);
            int cursorW = gp.TileSize;
            int cursorH = gp.TileSize;
            //draw cursor
            g2.setColor(Color.yellow);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(cursorX, cursorY, cursorW, cursorH, 5, 5);

            //description frame
            int dFrameX = frameX;
            int dFrameY = frameY + frameH + 2;
            int dFrameW = frameW;
            int dFrameH = (int)(4.5 * gp.TileSize);

            //draw description
            int textX = dFrameX + 20;
            int textY = dFrameY + 40;
            g2.setFont(dialogueFont);
            g2.setColor(Color.white);
            g2.setFont(Arial_30);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,15));
            int itemIndex = getItemIndexOnSlot(slotCol,slotRow);
            if (itemIndex < entity.inventory.size()) {
                drawSubWindow(dFrameX, dFrameY, dFrameW, dFrameH);
                for (String line : entity.inventory.get(itemIndex).description.split("\n")) {
                    g2.drawString(line, textX, textY);
                    textY += 28;
                }
            }
        }

    }
    public int getItemIndexOnSlot(int slotCol,int slotRow){

        return slotCol +(slotRow *7);
    }
    public void drawPauseScreen(){
//        drawPlayerHP();
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,75F));
        String text="PAUSED";
        int x= getXForCenterOfText(text);
        int y=gp.screenHeight/2;
        g2.drawString(text,x,y);

    }
    public void drawDialogueScreen(){
        // WINDOW
        int x=gp.TileSize*4;
        int y=gp.TileSize;
        int width=gp.screenWidth- 2*x;
        int height=gp.TileSize*3;
        drawSubWindow(x,y,width,height);

        // TEXT
        g2.setFont(dialogueFont);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,25));
        x+=gp.TileSize/2;
        y+=gp.TileSize-5;
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y+=30;
        }
    }
    public void drawTitleScreen(){
        if(titleScreenState==0){
            g2.setColor(new Color(0,0,10));
            g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
            // Title name
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,100F));
            String text = "ESCaPe";
            int x= getXForCenterOfText(text);
            int y=gp.TileSize*3;
            //shadow
            g2.setColor(Color.black);
            g2.drawString(text,x+7,y+7);
            //main text
            g2.setColor(Color.yellow);
            g2.drawString(text,x,y);
            //main character
            x=3*gp.TileSize;
            y=gp.TileSize*5;
            g2.drawImage(gp.player.right2,x,y,5*gp.TileSize,5*gp.TileSize,null);
            //menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,40));
            text="Chơi ngay";
            x= getXForCenterOfText(text)+gp.TileSize*4;
            y=gp.TileSize*6;
            g2.setColor(Color.white);
            g2.drawString(text,x,y);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x-50, y-30, 30, 30, 5, 5);
            if(commandNum==0){
//                g2.drawString(">",x-gp.TileSize,y);
                g2.setColor(Color.PINK);
                g2.fillRoundRect(x-50, y-30, 30, 30, 5, 5);
                g2.drawString(text,x,y);

            }

            text="Thông tin trò chơi";

            y+=gp.TileSize;
            g2.setColor(Color.white);
            g2.drawString(text,x,y);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x-50, y-30, 30, 30, 5, 5);
            if(commandNum==1){
//                g2.drawString(">",x-gp.TileSize,y);
                g2.setColor(Color.PINK);
                g2.fillRoundRect(x-50, y-30, 30, 30, 5, 5);
                g2.drawString(text,x,y);

            }

            text="Điều khiển";

            y+=gp.TileSize;
            g2.setColor(Color.white);
            g2.drawString(text,x,y);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x-50, y-30, 30, 30, 5, 5);
            if(commandNum==2){
//                g2.drawString(">",x-gp.TileSize,y);
                g2.setColor(Color.PINK);
                g2.fillRoundRect(x-50, y-30, 30, 30, 5, 5);
                g2.drawString(text,x,y);

            }

            text="Thoát";

            y+=gp.TileSize;
            g2.setColor(Color.white);
            g2.drawString(text,x,y);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x-50, y-30, 30, 30, 5, 5);
            if(commandNum==3){
//                g2.drawString(">",x-gp.TileSize,y);
                g2.setColor(Color.PINK);
                g2.fillRoundRect(x-50, y-30, 30, 30, 5, 5);
                g2.drawString(text,x,y);

            }
        }
        else if(titleScreenState==1){
            // CLASS SELECTION SCREEN
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
            String text="Bạn chọn trái ác quỷ nào?";
            int x= getXForCenterOfText(text);
            int y=gp.TileSize*4;
            g2.drawString(text,x,y);

            text="Lửa";
            x= getXForCenterOfText(text);
            y+=3*gp.TileSize;
            g2.setColor(Color.white);
            g2.drawString(text,x,y);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x-50, y-30, 30, 30, 5, 5);
            if(commandNum==0){
//                g2.drawString(">",x-gp.TileSize,y);
                g2.setColor(Color.PINK);
                g2.fillRoundRect(x-50, y-30, 30, 30, 5, 5);
                g2.drawString(text,x,y);
            }

            text="Băng";
            y+=gp.TileSize;
            g2.setColor(Color.white);
            g2.drawString(text,x,y);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x-50, y-30, 30, 30, 5, 5);
            if(commandNum==1){
//                g2.drawString(">",x-gp.TileSize,y);
                g2.setColor(Color.PINK);
                g2.fillRoundRect(x-50, y-30, 30, 30, 5, 5);
                g2.drawString(text,x,y);
            }
            text="Quay lại";
            y+=2*gp.TileSize;
            g2.setColor(Color.white);
            g2.drawString(text,x,y);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x-50, y-30, 30, 30, 5, 5);
            if(commandNum==2){
//                g2.drawString(">",x-gp.TileSize,y);
                g2.setColor(Color.PINK);
                g2.fillRoundRect(x-50, y-30, 30, 30, 5, 5);
                g2.drawString(text,x,y);
            }
        }
        else if(titleScreenState==2){
            g2.setColor(Color.BLUE);
            g2.setFont(dialogueFont);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,25F));
            int x=gp.TileSize;
            int y=gp.TileSize*2;
            String text="Xin chào bạn!\nChào mừng bạn trải nghiệm game của mình\n" +
                    "Chúc bạn chơi game vui vẻ\n" +
                    "Dù sao thì cũng mong bạn bỏ qua một số sai sót\n" +
                    "Cuối cùng là lời cảm ơn tới bạn khi đã ghé qua đây!";
            for(String line :text.split("\n")) {
                g2.drawString(line, x, y);
                y+=32;
            }
            text="Quay lại";
            y=10*gp.TileSize;
            g2.drawString(text,x,y);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x-35, y-20, 20, 20, 5, 5);
            if(commandNum==0){
//                g2.drawString(">",x-25,y);
                g2.setColor(Color.PINK);
                g2.fillRoundRect(x-35, y-20, 20, 20, 5, 5);
                g2.drawString(text,x,y);
            }
        }
        else if(titleScreenState==3){
            optionsControl(gp.TileSize*4,gp.TileSize);
        }

    }
    public void drawSubWindow(int x,int y,int width,int height){
        Color c=new Color(0,0,0,220);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);
        c=new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);

    }
    public int getXForCenterOfText(String text){
        int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        return gp.screenWidth/2-length/2;
    }
    public int getXForRightOfText(String text,int tailX){
        int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        return tailX-length;
    }

    //
    public void drawPlayerHP() {
        if(gp.player.hp>gp.player.maxHP){
            gp.player.hp=gp.player.maxHP;
        }
        int x = gp.TileSize / 2;
        int y = gp.TileSize / 2;
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
        g2.drawString("Level "+gp.player.level,x,y/2+5);
        double scaleHP=(double)(3*gp.TileSize+6)/gp.player.maxHP;
        double hpBar=gp.player.hp*scaleHP;
        g2.setColor(new Color(20, 20, 20));
        g2.fillRect(x - 2, y - 2, gp.TileSize*3+10, 16);
        g2.setColor(Color.white);
        g2.fillRect(x , y , gp.TileSize*3+6, 12);
        g2.setColor(new Color(20, 200, 20));
        g2.fillRect(x,y,(int)hpBar,12);

        g2.setFont(Arial_30);
        g2.setFont(g2.getFont().deriveFont(11F));
        g2.setColor(Color.black);
        g2.drawString("hp "+gp.player.hp + "/" + gp.player.maxHP, x + 50, y + 10);

        // debug
//        int screenX =  -gp.player.worldX + gp.player.screenX;
//        int screenY =  -gp.player.worldY + gp.player.screenY;
//        g2.fillRoundRect(gp.player.worldX+gp.player.solidArea.x+screenX,screenY+gp.player.worldY+gp.player.solidArea.y,gp.player.solidArea.width,gp.player.solidArea.height,3,3);
//        g2.fillRoundRect(gp.monster[0].worldX+gp.monster[0].solidArea.x+screenX,screenY+gp.monster[0].worldY+gp.monster[0].solidArea.y,gp.monster[0].solidArea.width,gp.monster[0].solidArea.height,3,3);
//        g2.fillRoundRect(gp.npc[0].worldX+gp.npc[0].solidArea.x+screenX,screenY+gp.npc[0].worldY+gp.npc[0].solidArea.y,gp.npc[0].solidArea.width,gp.npc[0].solidArea.height,3,3);

    }
    public void drawPlayerMana() {
        if(gp.player.mana>gp.player.maxMana){
            gp.player.mana=gp.player.maxMana;
        }

        int x = gp.TileSize / 2;
        int y = gp.TileSize;
        double scaleMana = (double) (3 * gp.TileSize + 6) / gp.player.maxMana;
        double manaBar = gp.player.mana * scaleMana;
        g2.setColor(new Color(20, 20, 20));
        g2.fillRect(x - 2, y - 2, gp.TileSize * 3 + 10, 16);
        g2.setColor(Color.white);
        g2.fillRect(x, y, gp.TileSize * 3 + 6, 12);
        g2.setColor(new Color(10, 20, 250));
        g2.fillRect(x, y, (int) manaBar, 12);

        g2.setFont(Arial_30);
        g2.setFont(g2.getFont().deriveFont(11F));
        g2.setColor(Color.black);
        g2.drawString("mana "+gp.player.mana + "/" + gp.player.maxMana, x + 40, y + 10);
    }
    public void drawPlayerExp() {
//        if(gp.player.mana>gp.player.maxMana){
//            gp.player.mana=gp.player.maxMana;
//        }

        int x = gp.TileSize / 2;
        int y = gp.TileSize*3/2;
        double scaleMana = (double) (3 * gp.TileSize + 6) / gp.player.nextLevelExp;
        double manaBar = gp.player.exp * scaleMana;
        g2.setColor(new Color(20, 20, 20));
        g2.fillRect(x - 2, y - 2, gp.TileSize * 3 + 10, 16);
        g2.setColor(Color.white);
        g2.fillRect(x, y, gp.TileSize * 3 + 6, 12);
        g2.setColor(new Color(200, 20, 250));
        g2.fillRect(x, y, (int) manaBar, 12);

        g2.setFont(Arial_30);
        g2.setFont(g2.getFont().deriveFont(11F));
        g2.setColor(Color.black);
        g2.drawString("exp " +gp.player.exp + "/" + gp.player.nextLevelExp, x + 45, y + 10);
    }
    public void drawEndGameScreen(){
        gp.stopMusic();
        int frameX=0;
        int frameY=0;
        int frameW=gp.screenWidth;
        int frameH=gp.screenHeight;
        int a=240;
        g2.setColor(new  Color(10,10,10,a));
        drawSubWindow(frameX,frameY,frameW,frameH);
        // text

        String text="EnD GaME";
        int x=getXForCenterOfText(text)-(int)(4.5*gp.TileSize);
        int y=gp.TileSize*5;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110F));
        g2.setColor(new Color(50,50,50));
        g2.drawString(text,x+10,y+10);
        g2.setColor(Color.yellow);
        g2.drawString(text,x,y);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,20));

        x=gp.TileSize*3;
        y=gp.TileSize*7;
        text="chúc mừng bạn đã phá đảo game của mình.\nMình xin cảm ơn bạn đã trải nghiệm con game này\n" +
                "Nếu có ý kiến muốn đóng góp mong bạn" +
                "\nliên hệ với chúng mình\n" +
                "Để game ngày càng hoàn thiện hơn!" +
                "\nThông tin liên hệ có tại trong game rồi ạ!" +
                "\nBYE BYE! ";
        for(String line : text.split("\n")){
            g2.drawString(line,x,y);
            y+=27;
        }
        x=gp.TileSize*3;
        y=gp.TileSize*11;
        g2.setColor(Color.YELLOW);
        g2.drawString("[Chơi lại]",x,y);
//        gp.gameThread=null;
        if(gp.keyH.enterPressed){
//            gp.gameThread=new Thread(gp);
//            gp.gameThread.start();
            gp.gameState=gp.titleState;
            titleScreenState=0;
            gp.restart();
            gp.keyH.enterPressed=false;

        }
    }
}

