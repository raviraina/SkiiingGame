/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiinggame;

/**
 *
 * @author raviraina
 */
public interface AudioPlayerIntf {
    
    public void playAudio(String name, boolean loop);
    
    public void stopAudio(String name);
    
}
