package Background;

import Main.Gamepanel;
import Main.Tool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class QuanLyNen {

    Gamepanel gp;
    public tile[] tile;
    public int mapTileNum[][];
    public QuanLyNen(Gamepanel gp){

        this.gp=gp;
        tile=new tile[80];
        mapTileNum= new int[gp.maxWorldRow][gp.maxWorldCol];
        getImageTile();
       //loadMap("/map/map04.txt");
        loadMap("/map/finalMap.txt");

    }
    public void getImageTile(){
        int i=0;
        setUp(i,"0.grasss",false);i++;//0
        setUp(i,"1.grass_rock",false);i++;
        setUp(i,"2.bridge1",false);i++;
        setUp(i,"3.bridge2",false);i++;
        setUp(i,"4.bridge3",false);i++;
        setUp(i,"5.bridge4",false);i++;
        setUp(i,"6.bridge5",false);i++;
        setUp(i,"7.bridge6",false);i++;
        setUp(i,"8.bridge7",false);i++;
        setUp(i,"9.bridge8",false);i++;
        setUp(i,"10.cactus",true);i++;//10
        setUp(i,"11.lake1",true);i++;
        setUp(i,"12.lake2",true);i++;
        setUp(i,"13.lake3",true);i++;
        setUp(i,"14.lake4",true);i++;
        setUp(i,"15.lake5",true);i++;
        setUp(i,"16.lake6",true);i++;
        setUp(i,"17.river_conner_topright2",false);i++;
        setUp(i,"18.riverside_right",true);i++;
        setUp(i,"19.riverside_down",true);i++;
        setUp(i,"20.river_conner_topleft1",false);i++;//20
        setUp(i,"21.riverside_up",true);i++;
        setUp(i,"22.riverside_left",true);i++;
        setUp(i,"23.river_conner_bottomright1",false);i++;
        setUp(i,"24.river_conner_topright1",false);i++;
        setUp(i,"25.river_conner_topleft2",false);i++;
        setUp(i,"26.river_conner_bottomright2",false);i++;
        setUp(i,"27.river_conner_bottomleft1",false);i++;
        setUp(i,"28.shipreck1",false);i++;
        setUp(i,"29.shipreck2",false);i++;
        setUp(i,"30.shipreck3",false);i++;//30
        setUp(i,"31.shipreck4",false);i++;
        setUp(i,"32.tree",true);i++;
        setUp(i,"33.wall1",true);i++;
        setUp(i,"34.wall2",true);i++;
        setUp(i,"35.wate1",true);i++;
        setUp(i,"36.wate2",true);i++;
        setUp(i,"37.biglake1",true);i++;
        setUp(i,"38.biglake2",true);i++;
        setUp(i,"39.biglake3",true);i++;
        setUp(i,"40.biglake4",true);i++;//40
        setUp(i,"41.biglake5",true);i++;
        setUp(i,"42.biglake6",true);i++;
        setUp(i,"43.biglake7",true);i++;
        setUp(i,"44.biglake8",true);i++;
        setUp(i,"45.biglake9",true);i++;
        setUp(i,"46.biglake10",true);i++;
        setUp(i,"47.biglake11",true);i++;
        setUp(i,"48.biglake12",true);i++;
        setUp(i,"49.biglake13",true);i++;
        setUp(i,"50.biglake14",true);i++;//50
        setUp(i,"51.biglake15",true);i++;
        setUp(i,"52.biglake16",true);i++;
        setUp(i,"53.biglake17",true);i++;
        setUp(i,"54.biglake18",true);i++;
        setUp(i,"55.biglake19",true);i++;
        setUp(i,"56.biglake20",true);i++;
        setUp(i,"57.biglake21",true);i++;
        setUp(i,"58.biglake22",true);i++;
        setUp(i,"59.river_conner_bottomleft2",false);i++;
        setUp(i,"60.sand",false);i++;//60
        setUp(i,"60.sand",false);i++;
        setUp(i,"62.sand",false);i++;
        setUp(i,"63.sand",false);i++;
        setUp(i,"64.sand",false);i++;
        setUp(i,"65.sand",false);i++;
        setUp(i,"66.sand",false);i++;
        setUp(i,"67.sand",false);i++;
        setUp(i,"68.sand",false);i++;
        setUp(i,"69.sand",false);i++;
        setUp(i,"70.floor1",false);i++;//70
        setUp(i,"71.farm",false);i++;
        setUp(i,"72.tp_tile",false);i++;
//        setUp(i,"70.floor1",false);i++;
//        setUp(i,"70.floor1",false);i++;
    }


    public void setUp(int index, String imgName,boolean vaCham){
        Tool tool=new Tool();
        try{
            tile[index]=new tile();
            tile[index].image=ImageIO.read(getClass().getResourceAsStream("/tiles/"+imgName+".png"));
            tile[index].image=tool.scaleImage(tile[index].image,gp.TileSize,gp.TileSize);
            tile[index].VaCham=vaCham;


        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void loadMap(String filepath){

        int row=0, col=0;
        try{
            InputStream is= getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while(col<gp.maxWorldCol&&row< gp.maxWorldRow){

                String line = br.readLine();

                while(col<gp.maxWorldCol){
                    String numbers[]=line.split(" ");
                    int num= Integer.parseInt(numbers[col]);
                    mapTileNum[row][col]=num;
                    col++;
                }

                if(col==gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            br.close();



        }catch(Exception e){

        }
    }






    public void draw(Graphics2D g2){
        int worldCol=0;
        int worldRow=0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int numTile=mapTileNum[worldRow][worldCol];
            int worldX = worldCol*gp.TileSize;
            int worldY = worldRow*gp.TileSize;
            int screenX= worldX-gp.player.worldX+gp.player.screenX;
            int screenY= worldY-gp.player.worldY+gp.player.screenY;


            if(worldX+gp.TileSize>gp.player.worldX-gp.player.screenX&&
               worldX-gp.TileSize<gp.player.worldX+gp.player.screenX&&
               worldY+gp.TileSize>gp.player.worldY-gp.player.screenY&&
               worldY-gp.TileSize<gp.player.worldY+gp.player.screenY) {
                g2.drawImage(tile[numTile].image, screenX, screenY, gp.TileSize, gp.TileSize, null);
            }


            worldCol++;
            if(worldCol== gp.maxWorldCol){
                worldCol=0;
                worldRow++;
            }
        }

    }
}
