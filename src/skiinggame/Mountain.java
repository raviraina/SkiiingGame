/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiinggame;

import audio.AudioPlayer;
import environment.Environment;
import environment.Velocity;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author raviraina
 */
class Mountain extends Environment {

    Image image1, image2, tree;//, ski_down, ski_left, ski_right;
    int topImageY = 0;
    private ArrayList<Item> items;
    private Skier skier;

    public Mountain() {
        this.setBackground(Color.white);
//        image1 = ResourceTools.loadImageFromResource("skiinggame/bettersnow.jpg");
//        image2 = ResourceTools.loadImageFromResource("skiinggame/bettersnow.jpg");
        tree = ResourceTools.loadImageFromResource("skiinggame/tree.png");

        topImageY = 0; //this.getHeight() - image1.getHeight(null);

        items = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            items.add(new Item(((int) (Math.random() * 800)), ((int) (Math.random() * 600)), Item.ITEM_TYPE_TREE, tree, true));
        }

        skier = new Skier(new Point(400, 25), new Velocity(0, 0));

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
        
        if (skier != null) {
            skier.move();
        }

        moveimages();
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            AudioPlayer.play("/skiinggame/What You Eatin Daddy-SoundBible.com-104789761.mp3");
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            skier.setDirection(Direction.LEFT);
            
            
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            skier.setDirection(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            skier.setDirection(Direction.RIGHT);
        }
    }
    
//    xlower = 25
//    xupper 675~~
//    

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

        if (skier != null) {
            skier.draw(graphics);
        }

    }

    int speed = 4;

    private void moveimages() {
        for (int i = 0; i < speed; i++) {
            //move the background
            topImageY--;

            //move the trees and stuff...
            if (items != null) {
                for (Item item : items) {
                    item.setY(item.getY() - 1);
                    //hey, if the tree has gone off the top, then put it down 
                    //below the bottom a new, random x value 

                    if (item.getY() <= -100) {
                        item.setY(650);
                        item.setX((int) (Math.random() * 800));
                        //random x value, somewhere between 0 and the width of the screen

                    }
                }

            }
        }

        if (image1 != null) {
            if (topImageY < this.getHeight() - image1.getHeight(null)) {
                topImageY = this.getHeight() - image1.getHeight(null);
            }
        }

    }

}
