package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    Clip clip ;
    URL urlSound[]=new URL[30];
    FloatControl fc;
    int volumeScale=3;
    float volume;
    public Sound (){
        urlSound[0]=getClass().getResource("/music/nhacNen1.wav");
        urlSound[1]=getClass().getResource("/music/door.wav");
        urlSound[2]=getClass().getResource("/music/collectcoin.wav");
        urlSound[3]=getClass().getResource("/music/gameover.wav");
        urlSound[4]=getClass().getResource("/music/success.wav");
        urlSound[5]=getClass().getResource("/music/jump.wav");
        urlSound[6]=getClass().getResource("/music/kiss.wav");
        urlSound[7]=getClass().getResource("/music/levelUp.wav");
        urlSound[8]=getClass().getResource("/music/receiveDamage.wav");
        urlSound[9]=getClass().getResource("/music/hit.wav");
        urlSound[10]=getClass().getResource("/music/hitdone.wav");
        urlSound[11]=getClass().getResource("/music/woodCut.wav");
        urlSound[12]=getClass().getResource("/music/cach.wav");
        urlSound[13]=getClass().getResource("/music/shoot.wav");
        urlSound[14]=getClass().getResource("/music/gotDamage.wav");
    }
    public void setFile(int i){
        try {
            AudioInputStream ais= AudioSystem.getAudioInputStream(urlSound[i]);
            clip =AudioSystem.getClip();
            clip.open(ais);
            fc=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        }catch(Exception e){

        }

    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
    public void checkVolume(){
        switch(volumeScale){
            case 0: volume=-80f; break;
            case 1: volume=-30f; break;
            case 2: volume=-15f; break;
            case 3: volume=-10f; break;
            case 4: volume=-1f; break;
            case 5: volume=6f; break;
        }
        fc.setValue(volume);
    }
}
