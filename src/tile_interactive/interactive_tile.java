package tile_interactive;

import Main.Gamepanel;
import entity.Entity;

import java.awt.*;

public class interactive_tile extends Entity {

    Gamepanel gp;
    public boolean destructible=false;
    public interactive_tile(Gamepanel gp,int row,int col) {
        super(gp);
        this.gp=gp;
    }
    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem=false;
        return isCorrectItem;
    }
    public void  update(){
        if(invincible){
            invincibleCounter++;
            alphaCounter++;
            if(invincibleCounter>40){
                invincible=false;
                invincibleCounter=0;
            }
        }
    }
    public void playSE(){
    }
    public interactive_tile getDestroyForm(){
        interactive_tile tile=null;
        return tile;
    }
    public void draw(Graphics2D g2){

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.TileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.TileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.TileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.TileSize < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(down1, screenX, screenY, null);

        }
    }

}
