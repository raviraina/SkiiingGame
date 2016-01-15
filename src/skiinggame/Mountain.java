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

    Image image1, image2, tree, ski_down, ski_left, ski_right;
    int topImageY = 0;
    private ArrayList<Item> items;

    public Mountain() {
//        this.setBackground(Color.white);
        image1 = ResourceTools.loadImageFromResource("skiinggame/bettersnow.jpg");
        image2 = ResourceTools.loadImageFromResource("skiinggame/bettersnow.jpg");
        tree = ResourceTools.loadImageFromResource("skiinggame/tree.png");
        
        topImageY = 0 ; //this.getHeight() - image1.getHeight(null);
        
        items = new ArrayList<>();
        items.add(new Item(10, 5, "POWER_UP", ResourceTools.loadImageFromResource("skiinggame/snow.jpg"), true));
        items.add(new Item(10, 5, "TREE", ResourceTools.loadImageFromResource("skiinggame/snow.jpg"), true));
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
            System.out.println(image1.getHeight(null) + "  " + topImageY);
            graphics.drawImage(image1, 0, topImageY, this);
            graphics.drawImage(image2, 0, topImageY + (2 * image2.getHeight(this)), this);
            

        }
        
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                items.get(i).draw(graphics);
            }
        }

    }
    
    int speed = 4;

    private void moveimages() {
        for (int i = 0; i < speed; i++) {
            topImageY--;            
        }

        if (image1 != null) {
            if (topImageY < this.getHeight() - image1.getHeight(null)) {
                topImageY = this.getHeight() - image1.getHeight(null);
            }
        }

    }


}
