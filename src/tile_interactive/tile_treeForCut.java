package tile_interactive;

import Main.Gamepanel;
import entity.Entity;
import objects.*;

import java.awt.*;
import java.util.Random;

public class tile_treeForCut extends interactive_tile{
    Gamepanel gp;
    public tile_treeForCut(Gamepanel gp,int row,int col) {
        super(gp,row,col);
        this.gp=gp;
        this.worldX= gp.TileSize*col;
        this.worldY= gp.TileSize*row;
        maxHP=new Random().nextInt(15)+1;
        hp=maxHP;

        down1=setUp("/interactive_tile/it_tree",gp.TileSize,gp.TileSize);
        destructible=true;
    }
    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem=false;
        if(entity.currentWeapon.type==type_axe){
            isCorrectItem=true;
        }
        return isCorrectItem;
    }
    public void playSE(){
        gp.playSE(11);
    }
    public interactive_tile getDestroyForm(){
        interactive_tile tile=new tile_trunk(gp,worldY/gp.TileSize,worldX/gp.TileSize);
        checkDrop();
        return tile;
    }
    public Color getParticleColor(){
        return new Color(70,40,30);
    }
    public int getParticleSize(){
        return 6;
    }
    public int getParticleSpeed(){
        return 2;
    }
    public int getMaxHP(){
        return 30;
    }
    public void checkDrop(){
        int i= new Random().nextInt(100)+1;
        if(i<70){
                if(gp.player.canObtainItem(new obj_wood(gp))){
                    gp.ui.addMessage("bạn nhận dược "+1+" gỗ");
            }
        }
    }

}
