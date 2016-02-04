/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiinggame;

import audio.Playlist;
import components.HealthBar;
import environment.Environment;
import environment.Velocity;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;

/**
 *
 * @author raviraina
 */
class Mountain extends Environment {

//<editor-fold defaultstate="collapsed" desc="GameState">
    /**
     * @return the state
     */
    public GameState getState() {
        return state;
    }

    /**
     * @param aState the state to set
     */
    public void setState(GameState state) {
        this.state = state;

        if (state == GameState.SKIING) {
            if (am != null) {
                am.playAudio(AudioManager.BGMUSIC, true);
            }
        } else if (state == GameState.CRASHED) {
            if (am != null) {
                //TODO - play crash sound
                am.stopAudio(AudioManager.BGMUSIC);
            }
        } else if (state == GameState.PAUSED) {
            if (am != null) {
                am.playAudio(AudioManager.PAUSESOUND, false);
                am.stopAudio(AudioManager.BGMUSIC);
            }
        }

    }
//</editor-fold>

    Image image1, image2, tree;
    Font gamefont, gamefont_20, gamefont_40, gamefont_60;
    int topImageY = 0;
    int speed = 4;
    int moveDelay = 0;
    int moveDelayLimit = 5;
    int dropCount = 32;
    int treeCount = 20;
    private ArrayList<Item> items;
    private ArrayList<Drop> drops;
    private ArrayList<Drop> safeDrops;
    private Skier skier;
    private Playlist playlist;
    private AudioManager am;
    private HealthBar healthBar;
    private int score;
    private GameState state; // = GameState.MENU;
    private Menu menu;

    public Mountain() {
        this.setBackground(Color.white);
        tree = ResourceTools.loadImageFromResource("skiinggame/tree.png");
        //        image1 = ResourceTools.loadImageFromResource("skiinggame/bettersnow.jpg");
        //        image2 = ResourceTools.loadImageFromResource("skiinggame/bettersnow.jpg");
        //        topImageY = 0; //this.getHeight() - image1.getHeight(null);

        this.addMouseListener(new MouseInput());
        skier = new Skier(new Point(440, 5), new Velocity(0, 0));
        menu = new Menu();
        healthBar = new HealthBar(new Point(15, 10), new Dimension(10, 10), skier);
        am = new AudioManager();
        this.score = score;
        setState(GameState.PAUSED);
        //        am.playAudio(AudioManager.BGMUSIC, true);

        drops = new ArrayList<>();
        safeDrops = new ArrayList<>();
        items = new ArrayList<>();

        for (int i = 0; i < treeCount; i++) {
            items.add(new Item(((int) (Math.random() * 800)), getRandomInt(300, 900), Item.ITEM_TYPE_TREE, tree, true));
        }

        

    }

    private int getRandomInt(int minimum, int maximum) {
        return (int) (minimum + ((maximum - minimum) * Math.random()));
    }

    @Override
    public void initializeEnvironment() {

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("skiinggame/8BIT.ttf");

            gamefont = Font.createFont(Font.TRUETYPE_FONT, input);
            gamefont_20 = gamefont.deriveFont((float) 20.0);
            gamefont_40 = gamefont.deriveFont((float) 40.0);
            gamefont_60 = gamefont.deriveFont((float) 60.0);

        } catch (FontFormatException ex) {
            Logger.getLogger(Mountain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Mountain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void timerTaskHandler() {

        if (state == GameState.SKIING) {
            if (moveDelay >= moveDelayLimit) {
                moveDelay = 0;
            }

            if (skier != null) {
                skier.move();
                score++;
                skier.addHealth(+1);
            }

            moveimages();
            checkCollisions();
        }

        checkHealth();
    }

//<editor-fold defaultstate="collapsed" desc="Check Methods">
    private void checkCollisions() {
        //check the boundary of the skier to see if it intersects with the
        // boundary of ANY of the trees
//        if (state == STATE.GAME) {
        if ((skier != null) && (items != null)) {
            for (Item item : items) {
                if (item.getBoundary().intersects(skier.getObjectBoundary())) {
                    System.out.println("BAAANG");
                    skier.addHealth(-30);
                    System.out.println(skier.getHealth());
                    score = score - 1;
                    am.playAudio(AudioManager.CRASHSOUND, false);
                }
            }
        }
    }

    private void checkHealth() {
        //end the game if health < minHealth
        if ((skier != null) && (skier.isDead())) {
            setState(GameState.CRASHED);
            System.out.println("Crashed");
//            am.playAudio(AudioManager.DEADSOUND, false);
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Keys and Mouse">
    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            skier.setDirection(Direction.LEFT);
            am.playAudio(AudioManager.TURNSOUND, false);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            skier.setDirection(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            skier.setDirection(Direction.RIGHT);
            am.playAudio(AudioManager.TURNSOUND, false);
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            if (state == GameState.PAUSED) {
                setState(GameState.SKIING);
            } else if (state == GameState.SKIING) {
                setState(GameState.PAUSED);
            }
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
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Paint and Move Images">
    @Override
    public void paintEnvironment(Graphics graphics) {
//        graphics.setFont();
//        graphics.drawString("PLAYER 1", 420, 300);
//        if (state == GameState.MENU) {
//            menu.render(graphics);
//        }

//            if (state == GameState.SKIING) {
        if ((image1 != null) && (image2 != null)) {
            System.out.println(image1.getHeight(null) + "  " + topImageY);

            graphics.drawImage(image1, 0, topImageY, this);
            graphics.drawImage(image2, 0, topImageY + (2 * image2.getHeight(this)), this);
        }

        for (int i = 0; i < drops.size(); i++) {
            drops.get(i).draw(graphics);
        }

        if (skier != null) {
            skier.draw(graphics);

        }

        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                items.get(i).draw(graphics);
            }
        }

        if (state == GameState.PAUSED) {
            graphics.setFont(gamefont_60);
            graphics.setColor(Color.black);
            graphics.drawString("PAUSED", 350, 250);

        }

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 900, 30);
        graphics.setColor(Color.white);
        graphics.setFont(gamefont_20);
        graphics.drawString("SKI ESCAPE", 400, 20);
        graphics.drawString(" " + score, 850, 20);
         
        if (healthBar != null) {
            healthBar.draw(graphics);
        }
        
       

    }
    private int trail = 4;

    private void moveimages() {
        int moveSpeed = 4;

        if (skier != null) {
            int yPos = skier.getPosition().y;
            int xPos = skier.getPosition().x;

            if (drops != null) {
//                System.out.println("Drops = " + drops.size());
                drops.add(new Drop(xPos + trail, yPos));
                for (Drop drop : drops) {
                    drop.setY(drop.getY() - moveSpeed);

                    if (drop.getY() <= -30) {
                        drop.opacity = 0;
                    }
                }
            }

            if (drops != null) {
                drops.add(new Drop(xPos + 14, yPos));
                for (Drop drop : drops) {
                    drop.setY(drop.getY() - moveSpeed);

                    if (drop.getY() <= -30) {
                        drop.opacity = 0;

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
        } else if (drops != null) {
            for (Drop drop : drops) {
                drop.setY(drop.getY() - moveSpeed);

                if (drop.getY() <= -100) {
                    drop.setY(650);
                    drop.setX((int) (Math.random() * 800));
                }

            }
        }

        cleanUpDrops();

//        if (image1 != null) {
//            if (topImageY < this.getHeight() - image1.getHeight(null)) {
//                topImageY = this.getHeight() - image1.getHeight(null);
//            }
    }

    public void cleanUpDrops() {
        ArrayList<Drop> toRemoveList = new ArrayList<>();
        for (Drop drop : drops) {
            if (drop.opacity == 0) {
                toRemoveList.add(drop);
            }
        }

        drops.removeAll(toRemoveList);
    }
//</editor-fold>

    /**
     * @param trail the trail to set
     */
    public void setTrail(int trail) {
        this.trail = trail;
    }

}

// 1. Have game reset to beginning when character dies.
// 2. Have character flash/reset when damage is taken.
// 3. Use drop class to texture background.
// 6. Fix a very broken start menu.
