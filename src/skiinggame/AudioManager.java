/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiinggame;

import audio.Audio;
import audio.AudioEvent;
import audio.AudioEventListenerIntf;
import audio.AudioPlayer;
import audio.SoundManager;
import audio.Playlist;
import audio.Source;
import audio.Track;
import java.util.ArrayList;

/**
 *
 * @author raviraina
 */
public class AudioManager implements AudioPlayerIntf {
    
    private SoundManager sm;
    private ArrayList<Track> music = new ArrayList<>();
    
    public static String BGMUSIC = "BGMUSIC";
    public static String TURNSOUND = "TURNSOUND";
    public static String PAUSESOUND = "PAUSESOUND";
    public static String CRASHSOUND = "CRASHSOUND";
//    public static String DEADSOUND = "DEADSOUND";
    {
        music.add(new Track( BGMUSIC, Source.RESOURCE, "/skiinggame/BGMusic.wav" ));
        music.add(new Track( TURNSOUND, Source.RESOURCE, "/skiinggame/MoveSound-old1.wav"));
        music.add(new Track( PAUSESOUND, Source.RESOURCE, "/skiinggame/PauseSound.wav"));
        music.add(new Track( CRASHSOUND, Source.RESOURCE, "/skiinggame/CrashSound.wav"));
//        music.add(new Track( DEADSOUND, Source.RESOURCE, "/skiinggame/DeathSound"));
        
        
        sm = new SoundManager(new Playlist(music));
    
    
    }

  
   

    @Override
    public void playAudio(String name, boolean loop) {
        if (loop) sm.play(name, Integer.MAX_VALUE);
        else sm.play(name);
        
        
    }

    @Override
    public void stopAudio(String name) {
        sm.stop(name);
    }
    
    
    
    
}


//IF ERRORS OCCUR, UN COMMENT SOUTS FROM SOUNDMANAGER IN GAMELIBRARY