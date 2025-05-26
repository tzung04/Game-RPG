package Main;
import entity.Entity;

public class checkVaCham {
    Gamepanel gp;
    public checkVaCham(Gamepanel gp){
        this.gp=gp;

    }
    public void checkTile(Entity vatThe){


        int canhTrai= vatThe.worldX+ vatThe.solidArea.x;
        int canhPhai= vatThe.worldX+ vatThe.solidArea.x+ vatThe.solidArea.width;
        int canhTren= vatThe.worldY+ vatThe.solidArea.y;
        int canhDuoi= vatThe.worldY+ vatThe.solidArea.y+ vatThe.solidArea.height;
        int cotTrai=canhTrai/gp.TileSize;
        int cotPhai=canhPhai/gp.TileSize;
        int hangTren=canhTren/gp.TileSize;
        int hangDuoi=canhDuoi/gp.TileSize;
        int num1=0,num2=0;
        switch (vatThe.direction){
            case "up":
                hangTren=(canhTren-vatThe.speed)/gp.TileSize;
                num1=gp.tiles.mapTileNum[hangTren][cotTrai];
                num2=gp.tiles.mapTileNum[hangTren][cotPhai];
                break;
            case "down":
                hangDuoi=(canhDuoi+vatThe.speed)/gp.TileSize;
                num1=gp.tiles.mapTileNum[hangDuoi][cotPhai];
                num2=gp.tiles.mapTileNum[hangDuoi][cotTrai];
                break;
            case "left":
                cotTrai=(canhTrai-vatThe.speed)/gp.TileSize;
                num1=gp.tiles.mapTileNum[hangTren][cotTrai];
                num2=gp.tiles.mapTileNum[hangDuoi][cotTrai];
                break;
            case "right":
                cotPhai=(canhPhai+vatThe.speed)/gp.TileSize;
                num1=gp.tiles.mapTileNum[hangTren][cotPhai];
                num2=gp.tiles.mapTileNum[hangDuoi][cotPhai];
                break;
        }
        if(gp.tiles.tile[num1].VaCham||gp.tiles.tile[num2].VaCham){
            vatThe.collisionOn =true;
        }

        vatThe.solidArea.x= vatThe.solidAreaDefaultX;
        vatThe.solidArea.y= vatThe.solidAreaDefaultY;
        vatThe.solidArea.width =vatThe.solidAreaDefaultW;
        vatThe.solidArea.height =vatThe.solidAreaDefaultH;

    }

    public int checkObjects(Entity vatthe, boolean player){
    int index=99;


    for(int i=0;i<gp.obj.length;i++){
        if(gp.obj[i]!=null){
            //vi tri va cham cua vat the
            vatthe.solidArea.x=vatthe.worldX+vatthe.solidArea.x;
            vatthe.solidArea.y=vatthe.worldY+vatthe.solidArea.y;

            //vi tri va cham cua doi tuong
            gp.obj[i].solidArea.x=gp.obj[i].solidArea.x+gp.obj[i].worldX;
            gp.obj[i].solidArea.y=gp.obj[i].solidArea.y+gp.obj[i].worldY;

            switch(vatthe.direction){
                case "up":
                    vatthe.solidArea.y-= vatthe.speed;
                    break;
                case "down":
                    vatthe.solidArea.y+= vatthe.speed;
                    break;
                case "left":
                    vatthe.solidArea.x-= vatthe.speed;
                    break;
                case "right":
                    vatthe.solidArea.x+= vatthe.speed;
                    break;
            }
            if(vatthe.solidArea.intersects(gp.obj[i].solidArea)){
                if(gp.obj[i].collision){
                    vatthe.collisionOn =true;
                }
                if(player){
                    index=i;
                }
        }
            vatthe.solidArea.x= vatthe.solidAreaDefaultX;
            vatthe.solidArea.y= vatthe.solidAreaDefaultY;
            vatthe.solidArea.width =vatthe.solidAreaDefaultW;
            vatthe.solidArea.height =vatthe.solidAreaDefaultH;

            gp.obj[i].solidArea.x=gp.obj[i].solidAreaDefaultX;
            gp.obj[i].solidArea.y=gp.obj[i].solidAreaDefaultY;


        }
    }



    return index;
    }
    // NPC or monsters collision
    public int checkVatThe(Entity vatthe, Entity[] target){

        int index=99;


        for(int i=0;i<target.length;i++){
            if(target[i]!=null) {
                //vi tri va cham cua vat the
                vatthe.solidArea.x = vatthe.worldX + vatthe.solidArea.x;
                vatthe.solidArea.y = vatthe.worldY + vatthe.solidArea.y;

                //vi tri va cham cua doi tuong
                target[i].solidArea.x = target[i].solidArea.x + target[i].worldX;
                target[i].solidArea.y = target[i].solidArea.y + target[i].worldY;

                switch (vatthe.direction) {
                    case "up":
                        vatthe.solidArea.y -= vatthe.speed;
                        break;
                    case "down":
                        vatthe.solidArea.y += vatthe.speed;
                        break;
                    case "left":
                        vatthe.solidArea.x -= vatthe.speed;
                        break;
                    case "right":
                        vatthe.solidArea.x += vatthe.speed;
                        break;
                    }
                if (vatthe.solidArea.intersects(target[i].solidArea)) {
                    if(vatthe!=target[i]) {
                        vatthe.collisionOn = true;
                        index = i;
                    }

                }

                vatthe.solidArea.x = vatthe.solidAreaDefaultX;
                vatthe.solidArea.y = vatthe.solidAreaDefaultY;
                vatthe.solidArea.width =vatthe.solidAreaDefaultW;
                vatthe.solidArea.height =vatthe.solidAreaDefaultH;


                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;

            }
        }

        return index;
    }
    public boolean checkPlayer(Entity vatthe){

        boolean contactPlayer=false;
            //vi tri va cham cua vat the
            vatthe.solidArea.x = vatthe.worldX + vatthe.solidArea.x;
            vatthe.solidArea.y = vatthe.worldY + vatthe.solidArea.y;

            //vi tri va cham cua doi tuong
            gp.player.solidArea.x = gp.player.solidArea.x + gp.player.worldX;
            gp.player.solidArea.y = gp.player.solidArea.y + gp.player.worldY;

            switch (vatthe.direction) {
                case "up":
                    vatthe.solidArea.y -= vatthe.speed;
                    break;
                case "down":
                    vatthe.solidArea.y += vatthe.speed;
                    break;
                case "left":
                    vatthe.solidArea.x -= vatthe.speed;
                    break;
                case "right":
                    vatthe.solidArea.x += vatthe.speed;
                    break;
            }
            if (vatthe.solidArea.intersects(gp.player.solidArea)) {
                vatthe.collisionOn = true;
                contactPlayer=true;
            }
            vatthe.solidArea.x= vatthe.solidAreaDefaultX;
            vatthe.solidArea.y= vatthe.solidAreaDefaultY;

            gp.player.solidArea.x=gp.player.solidAreaDefaultX;
            gp.player.solidArea.y=gp.player.solidAreaDefaultY;

            return contactPlayer;
    }


}
