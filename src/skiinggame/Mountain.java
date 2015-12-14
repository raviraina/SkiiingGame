/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiinggame;

import audio.AudioPlayer;
import environment.Environment;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author raviraina
 */
class Mountain extends Environment {

    Image image1, image2;
    private ArrayList<Item> items;

    public Mountain() {
//        this.setBackground(Color.white);
        image1 = ResourceTools.loadImageFromResource("skiinggame/snow.jpg");
        image2 = ResourceTools.loadImageFromResource("skiinggame/snow.jpg");
        
        topImageY = this.getHeight() - image1.getHeight(null);
        
        items = new ArrayList<>();
        items.add(new Item(10, 5, "POWER_UP", ResourceTools.loadImageFromResource("skiinggame/snow.jpg"), true));
        
                

    }

    @Override
    public void initializeEnvironment() {

    }

    int moveDelay = 0;
    int moveDelayLimit = 5;
    
    @Override
    public void timerTaskHandler() {
        
        if (moveDelay >= moveDelayLimit) {
            moveDelay = 0;
            
        }
        

        moveimages();
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            AudioPlayer.play("/skiinggame/MP5_SMG-GunGuru-703432894.wav");
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
//        graphics.setFont();
//        graphics.drawString("PLAYER 1", 420, 300);
        if ((image1 != null) && (image2 != null)) {
            graphics.drawImage(image1, 0, topImageY, this);
            graphics.drawImage(image2, 0, topImageY - image2.getHeight(this), this);

        }
        
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                items.get(i).draw(graphics);
            }
        }

    }

    int topImageY = 0;

    private void moveimages() {
        topImageY++;
        topImageY++;
        topImageY++;
        topImageY++;
        if (image1 != null) {
            if (topImageY > this.getHeight()) {
                topImageY = this.getHeight() - image1.getHeight(null);
            }
        }

    }


}
