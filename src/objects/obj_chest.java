package objects;
import Main.Gamepanel;
import entity.*;

public class obj_chest extends Entity {
    Gamepanel gp;
    Entity loot;
    boolean opened=false;
    public obj_chest(Gamepanel gp,Entity loot){
        super(gp);
        this.gp=gp;
        this.loot=loot;
        name = "chest";
        type=type_obstacle;
        image1=setUp("/item/chest",gp.TileSize,gp.TileSize);
        image2=setUp("/item/chestOpened",gp.TileSize,gp.TileSize);
        down1=image1;
        collision=true;
        description="["+name+"]"+"\nYou think what you can get from me?";
        solidArea.x=4;
        solidArea.y=16;
        solidArea.width=40;
        solidArea.height=32;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
    }
    public void interact(){
        gp.gameState=gp.dialogueState;
        if(!opened){
            gp.playSE(4);
            StringBuilder sb = new StringBuilder();
            sb.append("bạn vừa mở rương và tìm được \n"+loot.name+"!");
            if(gp.player.canObtainItem(loot)==false){
                sb.append("\n...nhưng túi đồ của bạn đã đầy\nkhông thể mang thêm vật phẩm!");
            }
            else{
                sb.append("\nbạn nhận được "+loot.name+"!");
                down1=image2;
                opened=true;
            }
            gp.ui.currentDialogue=sb.toString();
        }
        else{
            gp.ui.currentDialogue="rương trống không:((!";
        }
    }

}
