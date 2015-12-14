/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiinggame;

import environment.Actor;
import environment.Velocity;
import images.ResourceTools;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author raviraina
 */
public class Skier extends Actor {
    
    private Image image;
    private int health;

    {
        BufferedImage temp = (BufferedImage) ResourceTools.loadImageFromResource("skiiinggame/spritesheet.png");
        image = temp.getSubimage(75, 16, 16, 37);
    }
    
    public Skier(Point position, Velocity velocity) {
        super(position, velocity);
        
    }
    
    public void move() {
        if (isAlive()) {
 
        }
    }
    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }
    
       /**
     * @param health the health to set
     */
    public void addHealth(int health) {
        this.health += health;
    }
    
    public boolean isAlive(){
        return (health > 0);
    }
    


}


