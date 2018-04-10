package group2.sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {

    public static void playBackGroundMusic(){
        Thread t = new Thread(new playBackGroundMusicThread());
        t.start();
    }

    public static void  playEatMusic(){
        Thread t = new Thread(new playEatMusicThread());
        t.start();
    }

    public static void  playDeadMusic(){
        Thread t = new Thread(new playDeadMusicThread());
        t.start();
    }

    public static void  playGhostDeactivatedMusic(){
        Thread t = new Thread(new playGhostDiactivatedMusicThread());
        t.start();
    }

}

class playBackGroundMusicThread implements Runnable{
    public void run(){
        try{
            File sound = new File("sound/pacman_beginning.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.loop(Integer.MAX_VALUE);
            while(true){
                Thread.sleep(Integer.MAX_VALUE);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

class playEatMusicThread implements Runnable{
    public void run(){
        try{
            File sound = new File("sound/pacman_eatfruit.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
            Thread.sleep(clip.getMicrosecondLength()/1000);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

class playDeadMusicThread implements Runnable{
    public void run(){
        try{
            File sound = new File("sound/pacman_death.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
            Thread.sleep(clip.getMicrosecondLength()/1000);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

class playGhostDiactivatedMusicThread implements Runnable{
    public void run(){
        try{
            File sound = new File("sound/pacman_extrapac.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
            Thread.sleep(clip.getMicrosecondLength()/1000);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}