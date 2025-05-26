package Main;

import objects.obj_flame;
import objects.obj_ice;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener{

    public boolean len, xuong, trai, phai, use, jump,spacePress,shootPress, showDebug,enterPressed;

    Gamepanel gp;
    public keyHandler(Gamepanel gp){
        this.gp=gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // TITLE STATE
        if(gp.gameState== gp.titleState){
            titleState(code);
        }
        // PLAY STATE
        else if(gp.gameState==gp.playState){
            playState(code);
        }
        // PAUSED STATE
        else if(gp.gameState==gp.pauseState){
            pauseState(code);
        }
        // DIALOGUE STATE
        else if(gp.gameState==gp.dialogueState){
            dialogueState(code);
        }
        // INFO STATE
        else if(gp.gameState==gp.infoState){
            infoState(code);
        }
        // OPTIONS STATE
        else if(gp.gameState==gp.optionsState){
            optionsState(code);
        }
        // GAME OVER STATE
        else if(gp.gameState==gp.gameOverState){
            gameOverState(code);
        }
        else if(gp.gameState==gp.tradeState){
            tradeState(code);
        }
        else if(gp.gameState==gp.endGameState){
            endGameState(code);
        }


    }
    public void endGameState(int code){
        if (code == KeyEvent.VK_ENTER){
            enterPressed=true;
            gp.playMusic(12);
        }
    }
    public void tradeState(int code){

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if(gp.ui.subState==0) {
            if (code == KeyEvent.VK_DOWN) {
                gp.playSE(12);
                gp.ui.commandNum++;
            }
            if (code == KeyEvent.VK_UP){
                gp.playSE(12);
                gp.ui.commandNum--;
            }
            if (gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
        }
        else if(gp.ui.subState==1){
            npcInventory(code);
            if( code == KeyEvent.VK_ESCAPE){
                gp.ui.subState=0;
            }
        }
        else if(gp.ui.subState==2){
            playerInventory(code);
            if( code == KeyEvent.VK_ESCAPE){
                gp.ui.subState=0;
            }
        }
    }
    public void gameOverState(int code){
        if (code == KeyEvent.VK_UP){
            gp.playSE(12);
            gp.ui.commandNum--;
        }
        if (code == KeyEvent.VK_DOWN){
            gp.playSE(12);
            gp.ui.commandNum++;
        }
        if (code == KeyEvent.VK_ENTER){
            gp.playSE(12);
            enterPressed=true;
        }
        if(gp.ui.commandNum>2){
            gp.ui.commandNum=0;
        }
        if(gp.ui.commandNum<0){
            gp.ui.commandNum=2;
        }
    }
    public void optionsState(int code){
        if (code == KeyEvent.VK_O){
            gp.playSE(12);
            gp.gameState=gp.playState;
        }
        if (code == KeyEvent.VK_ENTER){
            gp.playSE(12);
            enterPressed=true;
        }
        int maxCommandNum=0;
        switch(gp.ui.subState){
            case 0: maxCommandNum=6; break;
            case 1: break;
            case 2: maxCommandNum=1; break;
        }

        if (code == KeyEvent.VK_UP){

            gp.playSE(12);
            gp.ui.commandNum--;

        }
        if (code == KeyEvent.VK_DOWN){

            gp.playSE(12);
            gp.ui.commandNum++;

        }
        if(gp.ui.commandNum>maxCommandNum){
            gp.ui.commandNum=0;
        }
        if(gp.ui.commandNum<0){
            gp.ui.commandNum=maxCommandNum;
        }
        if (code == KeyEvent.VK_LEFT){
            gp.playSE(12);

            if(gp.ui.subState==0){
                if(gp.ui.commandNum==0&&gp.music.volumeScale>0){
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                }
                if(gp.ui.commandNum==1&&gp.SE.volumeScale>0){
                    gp.SE.volumeScale--;
                }
            }


        }
        if (code == KeyEvent.VK_RIGHT){
            gp.playSE(12);

            if(gp.ui.subState==0){
                if(gp.ui.commandNum==0&&gp.music.volumeScale<5){
                    gp.music.volumeScale++;
                    gp.music.checkVolume();

                }
                if(gp.ui.commandNum==1&&gp.SE.volumeScale<5){
                    gp.SE.volumeScale++;

                }
            }


        }

    }
    public void titleState(int code){
        if (gp.ui.titleScreenState==0){
            if (code == KeyEvent.VK_UP){

                gp.playSE(12);
                gp.ui.commandNum--;

            }
            if (code == KeyEvent.VK_DOWN){

                gp.playSE(12);
                gp.ui.commandNum++;

            }
            if(gp.ui.commandNum>3){
                gp.ui.commandNum=0;
            }
            if(gp.ui.commandNum<0){
                gp.ui.commandNum=3;
            }
            if (code == KeyEvent.VK_ENTER){
                gp.playSE(12);

                switch(gp.ui.commandNum){
                    case 0:
                        gp.ui.titleScreenState=1;
                        break;
                    case 1:

                        gp.ui.titleScreenState=2;
                        gp.ui.commandNum=0;
                        gp.playMusic(7);
                        break;
                    case 2:

                        gp.ui.titleScreenState=3;
                        gp.ui.commandNum=0;
                        // huong dan
//                        gp.ui.optionsControl(4*gp.TileSize,gp.TileSize);

                        break;
                    case 3:

                        System.exit(0);
                        break;
                }
            }


        }
        else if(gp.ui.titleScreenState==1){


            if (code == KeyEvent.VK_UP){
                gp.playSE(12);
                gp.ui.commandNum--;
            }
            if (code == KeyEvent.VK_DOWN){
                gp.playSE(12);
                gp.ui.commandNum++;
            }
            if(gp.ui.commandNum>2){
                gp.ui.commandNum=0;
            }
            if(gp.ui.commandNum<0){
                gp.ui.commandNum=2;
            }

            if (code == KeyEvent.VK_ENTER){
                gp.playSE(12);

                switch(gp.ui.commandNum){
                    case 0:
                        gp.gameState=gp.playState;
                        gp.player.currentFruit=new obj_flame(gp);
                        gp.ui.addMessage("BẠN TRỞ THÀNH NGƯỜI SỬ DỤNG TRÁI LỬA");
                        gp.playMusic(0);
                        gp.ui.commandNum=0;
                        break;
                    case 1:
                        gp.gameState=gp.playState;
                        gp.player.currentFruit=new obj_ice(gp);
                        gp.ui.addMessage("BẠN TRỞ THÀNH NGƯỜI SỬ DỤNG TRÁI BĂNG");
                        gp.playMusic(0);
                        gp.ui.commandNum=0;
                        break;
                    case 2:
                        gp.ui.titleScreenState=0;
                        gp.ui.commandNum=0;
                        break;
                }
            }

        }
        else if(gp.ui.titleScreenState==2){
            if(code == KeyEvent.VK_ENTER){
                gp.playSE(12);
                gp.ui.titleScreenState=0;
                gp.ui.commandNum=0;
                gp.stopMusic();
            }

        }
        else if(gp.ui.titleScreenState==3){
            if(code == KeyEvent.VK_ENTER){
                gp.playSE(12);
                gp.ui.titleScreenState=0;
                gp.ui.commandNum=0;
                gp.stopMusic();
            }

        }
    }
    public void playState(int code){
        if (code == KeyEvent.VK_UP){

            len=true;

        }
        if (code == KeyEvent.VK_DOWN){

            xuong=true;

        }
        if (code == KeyEvent.VK_LEFT){

            trai=true;

        }
        if (code == KeyEvent.VK_RIGHT){

            phai=true;

        }
//        if (code == KeyEvent.VK_E){
//            use=true;
//        }

        if (code == KeyEvent.VK_P){
            gp.gameState=gp.pauseState;
        }
        if (code == KeyEvent.VK_I){
            System.out.println(gp.player.wood);
            gp.gameState=gp.infoState;
        }
        if (code == KeyEvent.VK_SPACE){
            spacePress=true;
        }
        if (code == KeyEvent.VK_S){
            shootPress=true;

        }
        if (code == KeyEvent.VK_O){
            gp.gameState=gp.optionsState;

        }


        //DEBUG
        if (code == KeyEvent.VK_D){
            showDebug = !showDebug;
        }
        if (code == KeyEvent.VK_U){
            System.out.println("t bam u roi load map di oooooo");
            gp.tiles.loadMap("/map/worldmapv2.txt");
        }

    }
    public void pauseState(int code){
        if (code == KeyEvent.VK_P){
            gp.gameState=gp.playState;
            gp.playMusic(0);
        }
    }
    public void dialogueState(int code){
        if(code == KeyEvent.VK_SPACE){
            gp.gameState=gp.playState;
            gp.ui.dialogueScreenCounter=0;
        }
    }
    public void infoState(int code){
        if (code == KeyEvent.VK_I){
            gp.gameState=gp.playState;
        }
        playerInventory(code);
        if (code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }
    }
    public void playerInventory(int code){
        if (code == KeyEvent.VK_UP){
            if(gp.ui.playerSlotRow !=0) {
                gp.playSE(12);
                gp.ui.playerSlotRow--;
            }
        }
        if (code == KeyEvent.VK_DOWN){
            if(gp.ui.playerSlotRow !=3) {
                gp.playSE(12);
                gp.ui.playerSlotRow++;
            }
        }
        if (code == KeyEvent.VK_RIGHT){
            if(gp.ui.playerSlotCol !=6) {
                gp.playSE(12);
                gp.ui.playerSlotCol++;
            }
        }
        if (code == KeyEvent.VK_LEFT){
            if(gp.ui.playerSlotCol !=0) {
                gp.playSE(12);
                gp.ui.playerSlotCol--;
            }
        }
    }
        public void npcInventory(int code){
            if (code == KeyEvent.VK_UP){
                if(gp.ui.npcSlotRow !=0) {
                    gp.playSE(12);
                    gp.ui.npcSlotRow--;
                }
            }
            if (code == KeyEvent.VK_DOWN){
                if(gp.ui.npcSlotRow !=3) {
                    gp.playSE(12);
                    gp.ui.npcSlotRow++;
                }
            }
            if (code == KeyEvent.VK_RIGHT){
                if(gp.ui.npcSlotCol !=6) {
                    gp.playSE(12);
                    gp.ui.npcSlotCol++;
                }
            }
            if (code == KeyEvent.VK_LEFT){
                if(gp.ui.npcSlotCol !=0) {
                    gp.playSE(12);
                    gp.ui.npcSlotCol--;
                }
            }
    }


            @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP){

            len=false;

        }
        if (code == KeyEvent.VK_DOWN){

            xuong=false;

        }
        if (code == KeyEvent.VK_LEFT){

            trai=false;

        }
        if (code == KeyEvent.VK_RIGHT){

            phai=false;

        }
        if (code == KeyEvent.VK_E){
            use=false;

        }
        if (code == KeyEvent.VK_J){
            jump=false;

        }
        if (code == KeyEvent.VK_S){
            shootPress=false;

        }


    }
}
