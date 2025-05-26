package tile_interactive;

import Main.Gamepanel;
import entity.Entity;

public class tile_trunk extends interactive_tile{
    Gamepanel gp;
    public tile_trunk(Gamepanel gp,int row,int col) {
        super(gp,row,col);
        this.gp=gp;
        this.worldX= gp.TileSize*col;
        this.worldY= gp.TileSize*row;

        solidArea.x=0;
        solidArea.y=0;
        solidArea.width=0;
        solidArea.height=0;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidAreaDefaultW=solidArea.width;
        solidAreaDefaultH=solidArea.height;

        down1=setUp("/interactive_tile/it_trunk",gp.TileSize,gp.TileSize);
        destructible=false;
    }

}

