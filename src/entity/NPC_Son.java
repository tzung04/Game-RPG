package entity;

import Main.Gamepanel;

import java.util.Random;

public class NPC_Son extends Entity {

    public NPC_Son(Gamepanel gp){
        super(gp);
        name="son";
        type =type_npc;
        direction = "down";
        speedDefault=2;
        speed=speedDefault;
        getSonImage();
        setDialogue();

    }
    public void getSonImage(){
        up1= setUp("/npc/npcUp_1",gp.TileSize,gp.TileSize);
        up2= setUp("/npc/npcUp_2",gp.TileSize,gp.TileSize);
        down1= setUp("/npc/npcDown1",gp.TileSize,gp.TileSize);
        down2= setUp("/npc/npcDown2",gp.TileSize,gp.TileSize);
        right1= setUp("/npc/npcRight1",gp.TileSize,gp.TileSize);
        right2= setUp("/npc/npcRight2",gp.TileSize,gp.TileSize);
        left1= setUp("/npc/npcLeft_1",gp.TileSize,gp.TileSize);
        left2= setUp("/npc/npcLeft_2",gp.TileSize,gp.TileSize);
    }
    public void setAction() {

        actionLockCounter++;
        Random random = new Random();
        if (actionLockCounter == 120) {
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 ) {
                direction = "right";
            }
            actionLockCounter=0;
        }
    }
    public void setDialogue(){

        dialogue[0]="noob!";
        dialogue[1]="hãy giúp ta đánh những con gray đằng kia!";
        dialogue[2]="ta nghe đồn rằng cứ đi về phía tây\nsẽ có 1 mê cung";
        dialogue[3]="nếu có 1 cây rìu\nta sẽ ghé thăm khu rừng phía bắc!";
        dialogue[4]="muốn tăng level thì đi săn quái";
        dialogue[5]="hãy đảm báo túi của ngươi có 10 bình hp";
        dialogue[6]="không thể ít hơn 7 bình mana!";
        dialogue[7]="phải có bản đồ mới đi được chứ nhỉ?";
        dialogue[8]="đi trên đường sẽ thèm ăn táo lắm.\nhãy dem thêm 5 quả táo nhé";
        dialogue[9]="muốn làm thuyền ư? hãy kiếm về đây 15 khúc gỗ!";
        dialogue[10]="hãy kiếm vài công chúa để đem theo. ahahah...";
        dialogue[11]="chúc anh bạn may mắn!";
        dialogue[12]="tránh đường cho bố đi con nợn này?";

    }
    public void speak(){

       super.speak();

    }
}
