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
import audio.Playlist;
import audio.Track;
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
    private ArrayList<SnowBG> drops;
    private ArrayList<SnowBG> safeDrops;
    private Skier skier;
    private Playlist playlist;
    int speed = 4;
    int moveDelay = 0;
    int moveDelayLimit = 5;
    private AudioManager am;
    int dropCount = 32;
    int treeCount = 12;

    public static enum STATE {

        MENU, GAME
    };
    public static STATE state = STATE.MENU;
    private Menu menu;

    public Mountain() {
        this.setBackground(Color.white);
//        image1 = ResourceTools.loadImageFromResource("skiinggame/bettersnow.jpg");
//        image2 = ResourceTools.loadImageFromResource("skiinggame/bettersnow.jpg");
        tree = ResourceTools.loadImageFromResource("skiinggame/tree.png");

        topImageY = 0; //this.getHeight() - image1.getHeight(null);

        this.addMouseListener(new MouseInput());

//        if (state == STATE.GAME) {
            items = new ArrayList<>();
            for (int i = 0; i < treeCount; i++) {
                items.add(new Item(((int) (Math.random() * 800)), ((int) (Math.random() * 600)), Item.ITEM_TYPE_TREE, tree, true));
            }

            drops = new ArrayList<>();
            safeDrops = new ArrayList<>();
//        for (int i = 0; i < dropCount; i++) {
//            drops.add(new SnowBG(((int) (Math.random() * 800)), ((int) (Math.random() * 600))));
//
//        }

            skier = new Skier(new Point(400, 5), new Velocity(0, 0));

            am = new AudioManager();

            am.playAudio(AudioManager.BGMUSIC, true);

//        }

        menu = new Menu();
    }

    @Override
    public void initializeEnvironment() {

    }

    @Override
    public void timerTaskHandler() {

//        if (state == STATE.GAME) {

            if (moveDelay >= moveDelayLimit) {
                moveDelay = 0;
            }

            if (skier != null) {
                skier.move();
            }

            moveimages();
            checkCollisions();
//        }
    }

    private void checkCollisions() {
        //check the boundary of the skier to see if it intersects with the
        // boundary of ANY of the trees
//        if (state == STATE.GAME) {
            if ((skier != null) && (items != null)) {

                for (Item item : items) {
                    if (item.getBoundary().intersects(skier.getObjectBoundary())) {
                        System.out.println("BAAANG");
                        skier.addHealth(-1);
                        System.out.println(skier.getHealth());
                    }
                }
            }

//        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {

//        if (state == STATE.GAME) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            skier.setDirection(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            skier.setDirection(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            skier.setDirection(Direction.RIGHT);
        }
    }

//    }
//    xlower = 25
//    xupper 675~~    
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

//        if (state == STATE.MENU) {
//            menu.render(graphics);
//            
//            
//
//        } 
//            else if (state == STATE.GAME) {
        if ((image1 != null) && (image2 != null)) {
            System.out.println(image1.getHeight(null) + "  " + topImageY);

            graphics.drawImage(image1, 0, topImageY, this);
            graphics.drawImage(image2, 0, topImageY + (2 * image2.getHeight(this)), this);
        }

        for (int i = 0; i < drops.size(); i++) {
            drops.get(i).draw(graphics);

        }

        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                items.get(i).draw(graphics);
            }
        }

        if (skier != null) {
            skier.draw(graphics);
        }

//        }
    }

    private void moveimages() {
        int moveSpeed = 4;
        if (skier != null) {
            int yPos = skier.getPosition().y;
            int xPos = skier.getPosition().x;

            if (drops != null) {
                drops.add(new SnowBG(xPos, yPos));
                for (SnowBG drop : drops) {
                    drop.setY(drop.getY() - moveSpeed);

                    if (drop.getY() <= -5) {

                    }
                }
            }
        }

        if (items != null) {
            for (Item item : items) {
                item.setY(item.getY() - moveSpeed);
                //hey, if the tree has gone off the top, then put it down 
                //below the bottom a new, random x value 

                if (item.getY() <= -100) {
                    item.setY(650);
                    item.setX((int) (Math.random() * 800));
                    //random x value, somewhere between 0 and the width of the screen
                }
            }
        }
        safeDrops = drops;

//        if (safeDrops != null) {
//            if (skier != null) {
//                if (skier.getPosition().y > 0) {
//                    int yPos = skier.getPosition().y;
//                    int xPos = skier.getPosition().x;
//                    drops.add(new SnowBG(xPos, yPos));
//                    ArrayList<SnowBG> safeDrops = drops;
//                    if (drops != null) {
//                    for (SnowBG drop : safeDrops) {
//                        drop.setY(drop.getY() - moveSpeed);
//
//                        if (drop.getY() <= -5) {
//                            drops.remove(drop);
//                        }}
//                    }
//                }
//            }
//
//        }
//        if (image1 != null) {
//            if (topImageY < this.getHeight() - image1.getHeight(null)) {
//                topImageY = this.getHeight() - image1.getHeight(null);
//            }
    }

}
