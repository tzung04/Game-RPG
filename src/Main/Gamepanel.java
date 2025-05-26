package Main;
import Background.QuanLyNen;
import entity.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import tile_interactive.*;

public class Gamepanel extends JPanel implements Runnable{

    public final int FPS = 60;
    public final int OriginalTileSize = 16;
    public final int XSize = 3;
    public final int TileSize = OriginalTileSize*XSize;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = TileSize*maxScreenCol;
    public final int screenHeight = TileSize*maxScreenRow;
    public final int maxWorldCol = 80;
    public final int maxWorldRow = 80;







    // SYSTEM
    public QuanLyNen tiles=new QuanLyNen(this);
    public keyHandler keyH=new keyHandler(this);
    public setObjects setOb=new setObjects(this);
    public checkVaCham checkV=new checkVaCham(this);
    public Sound music=new Sound();
    public Sound SE=new Sound();
    public UI ui=new UI(this);
    public eventHandler eHandler=new eventHandler(this);


    Thread gameThread;



    //player n objects n NPC
    public player player=new player (this,keyH);
    public Entity[] obj =new Entity[30];
    public Entity[] npc =new Entity[10];
    public Entity[] monster =new Entity[50];
    public interactive_tile iTile[]= new interactive_tile[80];
    public ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> fruitList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();


    //game state
    public int gameState;
    // GAME STATE
    public final int titleState=0;
    public final int playState=1;
    public final int pauseState=2;
    public final int dialogueState=3;
    public final int infoState=4;
    public final int optionsState=5;
    public final int gameOverState=6;
    public final int transitionState=7;
    public final int tradeState=8;
    public final int endGameState=9;



    public Gamepanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    public void setUpGame(){

        setOb.setItem();
        setOb.setNPC();
        setOb.setMonster();
        setOb.setInteractiveTile();
        gameState=titleState;
    }
    public void goBack(){
        ui.message.clear();
        player.setDefaultPositions();
        player.restoredLifeMana();
        player.invincible=true;
//        setOb.setMonster();
//        setOb.setNPC();
    }
    public void revive(){
        ui.message.clear();
        player.restoredLifeMana();
    }
    public void restart(){
        ui.message.clear();
        player.setGiaTriMacDinh();
        setOb.setNPC();
        setOb.setItem();
        setOb.setMonster();
        setOb.setInteractiveTile();
        stopMusic();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        double TimeDrawAgain = 1000000000/(double)FPS;
        double delta=0;
        long lastTime=System.nanoTime();
        long currentTime;

        while(gameThread!=null) {

            currentTime=System.nanoTime();
            delta += (double) (currentTime - lastTime) / TimeDrawAgain;
            if (delta >= 1) {
                update();
                repaint();
                delta--;

            }
            lastTime = currentTime;

        }
    }


    public void update() {
        if(gameState==playState){
            player.update();
            for(int i=0;i< npc.length;i++){
                if(npc[i]!=null){
                    npc[i].update();
                }
            }
            for(int i=0;i< monster.length;i++){
                if(monster[i]!=null){
                    if(monster[i].alive&&!monster[i].dying) {
                        monster[i].update();
                    }
                    if(!monster[i].alive) {
                        monster[i].checkDrop();
                        monster[i]=null;
                    }
                }
            }
            for(int i=0;i< fruitList.size();i++){
                if(fruitList.get(i)!=null){
                    if(fruitList.get(i).alive) {
                        fruitList.get(i).update();
                    }
                    if(!fruitList.get(i).alive) fruitList.remove(i);
                }
            }
            for(int i=0;i< particleList.size();i++){
                if(particleList.get(i)!=null){
                    if(particleList.get(i).alive) {
                        particleList.get(i).update();
                    }
                    if(!particleList.get(i).alive) {
                        particleList.remove(i);
                    }
                }
            }
            for(int i=0;i< iTile.length;i++){
                if(iTile[i]!=null){
                    iTile[i].update();
                }
            }
        }
        if(gameState==pauseState){

        }



    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        long drawStart=System.nanoTime();

        Graphics2D g2 = (Graphics2D) g;

        if(gameState==titleState){

            ui.draw(g2);
        }

        else {
            // Background
            tiles.draw(g2);

            // Interactive tile
            for(int i=0;i< iTile.length;i++){
                if(iTile[i]!=null){
                    iTile[i].draw(g2);
                }
            }

            // ADD vatThe vo list
            entityList.add(player);

            for(int i=0; i<npc.length; i++){
                if(npc[i]!=null) {
                    entityList.add(npc[i]);
                }
            }

            for(int i=0; i<obj.length; i++){
                if(obj[i]!=null) {
                    entityList.add(obj[i]);
                }
            }

            for(int i=0; i<monster.length; i++){
                if(monster[i]!=null) {
                    entityList.add(monster[i]);
                }
            }

            for(int i=0; i<fruitList.size(); i++){
                if(fruitList.get(i)!=null) {
                    entityList.add(fruitList.get(i));
                }
            }
            for(int i=0; i<particleList.size(); i++){
                if(particleList.get(i)!=null) {
                    entityList.add(particleList.get(i));
                }
            }

            //SORT BASE WORLD Y

            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    return Integer.compare(e1.worldY,e2.worldY);
                }
            });

            // DRAW
            for(int i=0;i< entityList.size();i++){
                entityList.get(i).draw(g2);
            }

            // EMPTY LIST
            entityList.clear();


            // UI
            ui.draw(g2);
        }
        // DEBUG
        if(keyH.showDebug){
            long drawEnd=System.nanoTime();
            long passed=drawEnd-drawStart;
            int x = 10;
            int y = 400;
            int lineHeight=25;

            g2.setFont(new Font("Noteworthy",Font.BOLD,20));
            g2.setColor(Color.white);
            g2.drawString("WorldX "+player.worldX,x,y);y+=lineHeight;
            g2.drawString("WorldY "+player.worldY,x,y);y+=lineHeight;
            g2.drawString("Col "+(player.worldX+player.solidArea.x)/TileSize,x,y);y+=lineHeight;
            g2.drawString("Row "+(player.worldY+player.solidArea.y)/TileSize,x,y);y+=lineHeight;
            g2.drawString("Draw time: "+passed,x,y);
        }
        g2.dispose();
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){music.stop();
    }
    public void playSE(int i) {
        SE.setFile(i);
        SE.play();
    }
}
