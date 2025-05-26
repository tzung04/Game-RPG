package Main;

import entity.*;
import monster.*;
import objects.*;
import tile_interactive.tile_fakeWall;
import tile_interactive.tile_treeForCut;
import tile_interactive.tile_tree_fruit;

public class setObjects {
    Gamepanel gp;
    public setObjects(Gamepanel gp){
        this.gp=gp;
    }
    public void setItem(){
        int i=0;

        gp.obj[i]= new obj_door2(gp);
        gp.obj[i].worldX=67*gp.TileSize;
        gp.obj[i].worldY=15*gp.TileSize;
        i++;//1
        gp.obj[i]= new obj_door1(gp);
        gp.obj[i].worldX=46*gp.TileSize;
        gp.obj[i].worldY=6*gp.TileSize;
        i++;//2
        gp.obj[i]= new obj_chest(gp,new obj_key2(gp));
        gp.obj[i].worldX=50*gp.TileSize;
        gp.obj[i].worldY=3*gp.TileSize;
        i++;//3
        gp.obj[i]= new obj_sword_leg_fake(gp);
        gp.obj[i].worldX=11*gp.TileSize;
        gp.obj[i].worldY=12*gp.TileSize;
        i++;
        gp.obj[i]= new obj_chest(gp,new obj_scroll(gp));
        gp.obj[i].worldX=67*gp.TileSize;
        gp.obj[i].worldY=31*gp.TileSize;
        i++;




    }
    public void setNPC(){

        int i=0;

        gp.npc[i]=new NPC_seller(gp);
        gp.npc[i].worldX=6* gp.TileSize;
        gp.npc[i].worldY=50* gp.TileSize;
        i++;
        gp.npc[i]=new NPC_Son(gp);
        gp.npc[i].worldX=33* gp.TileSize;
        gp.npc[i].worldY=22* gp.TileSize;
        i++;
        gp.npc[i]=new NPC_Son(gp);
        gp.npc[i].worldX=26* gp.TileSize;
        gp.npc[i].worldY=40* gp.TileSize;
        gp.npc[i].speed=gp.npc[i].speedDefault=0;
        i++;
        gp.npc[i]=new NPC_Son(gp);
        gp.npc[i].worldX=16* gp.TileSize;
        gp.npc[i].worldY=32* gp.TileSize;
        gp.npc[i].speed=gp.npc[i].speedDefault=0;
        i++;



    }
    public void setMonster(){
        int i=0;
        //gray
        gp.monster[i]=new mon_gray(gp);
        gp.monster[i].worldX=21* gp.TileSize;
        gp.monster[i].worldY=22* gp.TileSize;
        i++;//1
        gp.monster[i]=new mon_gray(gp);
        gp.monster[i].worldX=19* gp.TileSize;
        gp.monster[i].worldY=26* gp.TileSize;
        i++;//2
        gp.monster[i]=new mon_gray(gp);
        gp.monster[i].worldX=22* gp.TileSize;
        gp.monster[i].worldY=30* gp.TileSize;
        i++;//3
        gp.monster[i]=new mon_gray(gp);
        gp.monster[i].worldX=21* gp.TileSize;
        gp.monster[i].worldY=35* gp.TileSize;
        i++;//4
        gp.monster[i]=new mon_gray(gp);
        gp.monster[i].worldX=26* gp.TileSize;
        gp.monster[i].worldY=36* gp.TileSize;
        i++;//5
        gp.monster[i]=new mon_gray(gp);
        gp.monster[i].worldX=25* gp.TileSize;
        gp.monster[i].worldY=21* gp.TileSize;
        i++;//6
        gp.monster[i]=new mon_gray(gp);
        gp.monster[i].worldX=26* gp.TileSize;
        gp.monster[i].worldY=24* gp.TileSize;
        i++;//7



        //troll
        gp.monster[i]=new mon_troll(gp);
        gp.monster[i].worldX=25* gp.TileSize;
        gp.monster[i].worldY=54* gp.TileSize;
        i++;
        gp.monster[i]=new mon_troll(gp);
        gp.monster[i].worldX=21* gp.TileSize;
        gp.monster[i].worldY=54* gp.TileSize;
        i++;
        gp.monster[i]=new mon_troll(gp);
        gp.monster[i].worldX=16* gp.TileSize;
        gp.monster[i].worldY=54* gp.TileSize;
        i++;
        gp.monster[i]=new mon_troll(gp);
        gp.monster[i].worldX=16* gp.TileSize;
        gp.monster[i].worldY=49* gp.TileSize;
        i++;
        gp.monster[i]=new mon_troll(gp);
        gp.monster[i].worldX=16* gp.TileSize;
        gp.monster[i].worldY=44* gp.TileSize;
        i++;
        gp.monster[i]=new mon_troll(gp);
        gp.monster[i].worldX=21* gp.TileSize;
        gp.monster[i].worldY=44* gp.TileSize;
        i++;
        gp.monster[i]=new mon_troll(gp);
        gp.monster[i].worldX=20* gp.TileSize;
        gp.monster[i].worldY=50* gp.TileSize;
        i++;
        gp.monster[i]=new mon_troll(gp);
        gp.monster[i].worldX=25* gp.TileSize;
        gp.monster[i].worldY=50* gp.TileSize;
        i++;//15

        //new monster
        gp.monster[i]=new mon_orc(gp);
        gp.monster[i].worldX=44* gp.TileSize;
        gp.monster[i].worldY=4* gp.TileSize;
        i++;
        gp.monster[i]=new mon_orc(gp);
        gp.monster[i].worldX=38* gp.TileSize;
        gp.monster[i].worldY= 2*gp.TileSize;
        i++;
        gp.monster[i]=new mon_orc(gp);
        gp.monster[i].worldX=38* gp.TileSize;
        gp.monster[i].worldY=5* gp.TileSize;
        i++;
        gp.monster[i]=new mon_orc(gp);
        gp.monster[i].worldX=43* gp.TileSize;
        gp.monster[i].worldY=8* gp.TileSize;
        i++;
        gp.monster[i]=new mon_orc(gp);
        gp.monster[i].worldX=40* gp.TileSize;
        gp.monster[i].worldY=9* gp.TileSize;
        i++;
        gp.monster[i]=new mon_orc(gp);
        gp.monster[i].worldX=37* gp.TileSize;
        gp.monster[i].worldY=10* gp.TileSize;
        i++;//21


        // elf
        gp.monster[i]=new mon_elf(gp);
        gp.monster[i].worldX=44* gp.TileSize;
        gp.monster[i].worldY=21* gp.TileSize;
        i++;
        gp.monster[i]=new mon_elf(gp);
        gp.monster[i].worldX=45* gp.TileSize;
        gp.monster[i].worldY=23* gp.TileSize;
        i++;
        gp.monster[i]=new mon_elf(gp);
        gp.monster[i].worldX=46* gp.TileSize;
        gp.monster[i].worldY=25* gp.TileSize;
        i++;
        gp.monster[i]=new mon_elf(gp);
        gp.monster[i].worldX=47* gp.TileSize;
        gp.monster[i].worldY=20* gp.TileSize;
        i++;
        gp.monster[i]=new mon_elf(gp);
        gp.monster[i].worldX=48* gp.TileSize;
        gp.monster[i].worldY=22* gp.TileSize;
        i++;//26

        // skeleton
        gp.monster[i]=new mon_skeleton(gp);
        gp.monster[i].worldX=5* gp.TileSize;
        gp.monster[i].worldY=3* gp.TileSize;
        i++;
        gp.monster[i]=new mon_skeleton(gp);
        gp.monster[i].worldX=7* gp.TileSize;
        gp.monster[i].worldY=4* gp.TileSize;
        i++;
        gp.monster[i]=new mon_skeleton(gp);
        gp.monster[i].worldX=3* gp.TileSize;
        gp.monster[i].worldY=5* gp.TileSize;
        i++;
        gp.monster[i]=new mon_skeleton(gp);
        gp.monster[i].worldX=3* gp.TileSize;
        gp.monster[i].worldY=6* gp.TileSize;
        i++;
        gp.monster[i]=new mon_skeleton(gp);
        gp.monster[i].worldX=5* gp.TileSize;
        gp.monster[i].worldY=7* gp.TileSize;
        i++;
        gp.monster[i]=new mon_skeleton(gp);
        gp.monster[i].worldX=7* gp.TileSize;
        gp.monster[i].worldY=8* gp.TileSize;
        i++;
        gp.monster[i]=new mon_skeleton(gp);
        gp.monster[i].worldX=5* gp.TileSize;
        gp.monster[i].worldY= gp.TileSize;
        i++;
        gp.monster[i]=new mon_skeleton(gp);
        gp.monster[i].worldX=3* gp.TileSize;
        gp.monster[i].worldY=11* gp.TileSize;
        i++;
        // other

        gp.monster[i]=new mon_skeleton(gp);
        gp.monster[i].worldX=63* gp.TileSize;
        gp.monster[i].worldY=10* gp.TileSize;
        i++;
        gp.monster[i]=new mon_troll(gp);
        gp.monster[i].worldX=63* gp.TileSize;
        gp.monster[i].worldY=11* gp.TileSize;
        i++;
        gp.monster[i]=new mon_skeleton(gp);
        gp.monster[i].worldX=67* gp.TileSize;
        gp.monster[i].worldY=13* gp.TileSize;
        i++;
        gp.monster[i]=new mon_orc(gp);
        gp.monster[i].worldX=60* gp.TileSize;
        gp.monster[i].worldY=9* gp.TileSize;
        i++;gp.monster[i]=new mon_elf(gp);
        gp.monster[i].worldX=71* gp.TileSize;
        gp.monster[i].worldY=14* gp.TileSize;
        i++;
        gp.monster[i]=new mon_elf(gp);
        gp.monster[i].worldX=66* gp.TileSize;
        gp.monster[i].worldY=14* gp.TileSize;
        i++;
        gp.monster[i]=new mon_orc(gp);
        gp.monster[i].worldX=65* gp.TileSize;
        gp.monster[i].worldY=11* gp.TileSize;
        i++;
        gp.monster[i]=new mon_skeleton(gp);
        gp.monster[i].worldX=66* gp.TileSize;
        gp.monster[i].worldY=14* gp.TileSize;
        i++;
        gp.monster[i]=new mon_gray(gp);
        gp.monster[i].worldX=63* gp.TileSize;
        gp.monster[i].worldY=13* gp.TileSize;
        i++;
        gp.monster[i]=new mon_troll(gp);
        gp.monster[i].worldX=69* gp.TileSize;
        gp.monster[i].worldY=11* gp.TileSize;
        i++;
        gp.monster[i]=new mon_elf(gp);
        gp.monster[i].worldX=68* gp.TileSize;
        gp.monster[i].worldY=9* gp.TileSize;
        i++;


    }
    public void setInteractiveTile(){

        int i=0;
        gp.iTile[i]=new tile_treeForCut(gp,6,16);i++;//1
        gp.iTile[i]=new tile_treeForCut(gp,6,17);i++;
        gp.iTile[i]=new tile_treeForCut(gp,7,16);i++;
        gp.iTile[i]=new tile_treeForCut(gp,7,17);i++;
        gp.iTile[i]=new tile_treeForCut(gp,8,16);i++;
        gp.iTile[i]=new tile_treeForCut(gp,8,17);i++;
        gp.iTile[i]=new tile_treeForCut(gp,9,16);i++;
        gp.iTile[i]=new tile_treeForCut(gp,9,17);i++;
        gp.iTile[i]=new tile_treeForCut(gp,10,16);i++;
        gp.iTile[i]=new tile_treeForCut(gp,10,17);i++;//10
        gp.iTile[i]=new tile_treeForCut(gp,11,16);i++;
        gp.iTile[i]=new tile_treeForCut(gp,11,17);i++;
        gp.iTile[i]=new tile_treeForCut(gp,12,16);i++;
        gp.iTile[i]=new tile_treeForCut(gp,12,17);i++;
        gp.iTile[i]=new tile_treeForCut(gp,13,16);i++;
        gp.iTile[i]=new tile_treeForCut(gp,13,17);i++;
        gp.iTile[i]=new tile_treeForCut(gp,14,16);i++;
        gp.iTile[i]=new tile_treeForCut(gp,14,17);i++;
        gp.iTile[i]=new tile_treeForCut(gp,15,16);i++;
        gp.iTile[i]=new tile_treeForCut(gp,15,17);i++;//20
        gp.iTile[i]=new tile_treeForCut(gp,10,18);i++;
        gp.iTile[i]=new tile_treeForCut(gp,10,19);i++;
        gp.iTile[i]=new tile_treeForCut(gp,10,20);i++;
        gp.iTile[i]=new tile_treeForCut(gp,10,21);i++;
        gp.iTile[i]=new tile_treeForCut(gp,11,20);i++;
        gp.iTile[i]=new tile_treeForCut(gp,11,21);i++;
        gp.iTile[i]=new tile_treeForCut(gp,9,20);i++;
        gp.iTile[i]=new tile_treeForCut(gp,9,21);i++;
        gp.iTile[i]=new tile_treeForCut(gp,8,20);i++;
        gp.iTile[i]=new tile_treeForCut(gp,8,21);i++;
        gp.iTile[i]=new tile_treeForCut(gp,12,20);i++;
        gp.iTile[i]=new tile_treeForCut(gp,12,21);i++;
        gp.iTile[i]=new tile_fakeWall(gp,13,11);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,22,50);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,22,51);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,22,52);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,22,53);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,22,54);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,22,55);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,21,50);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,21,51);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,21,52);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,21,53);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,21,54);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,21,55);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,20,50);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,20,51);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,20,52);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,20,53);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,20,54);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,20,55);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,19,50);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,19,51);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,19,52);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,19,53);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,19,54);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,19,55);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,18,50);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,18,51);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,18,52);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,18,53);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,18,54);i++;
        gp.iTile[i]=new tile_tree_fruit(gp,18,55);i++;
    }
}
