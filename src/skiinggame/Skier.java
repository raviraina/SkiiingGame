/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiinggame;

import environment.Actor;
import environment.Velocity;
import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author raviraina
 */
public class Skier extends Actor {

    private BufferedImage ski_down, ski_left, ski_right;
    private int health;
    private Direction direction;

    public void draw(Graphics graphics) {
        graphics.drawImage(getImage(), getPosition().x, getPosition().y, null);
    }

    {
//        BufferedImage temp = (BufferedImage) ResourceTools.loadImageFromResource("skiiinggame/spritesheet.png");
//        image_straight = temp.getSubimage(75, 16, 16, 37);
        ski_down = (BufferedImage) ResourceTools.loadImageFromResource("skiinggame/ski_down.png");
        ski_left = (BufferedImage) ResourceTools.loadImageFromResource("skiinggame/ski_left.png");
        ski_right = (BufferedImage) ResourceTools.loadImageFromResource("skiinggame/ski_right.png");

        setDirection(Direction.DOWN);
        
        health = 100;
    }

    public Skier(Point position, Velocity velocity) {
        super(position, velocity);

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

    public boolean isAlive() {
        return (health > 0);
    }

    /**
     * @return the direction
     */
    @Override
    public BufferedImage getImage() {
        if (direction == Direction.LEFT) {
            return ski_left;
        } else if (direction == Direction.RIGHT) {
            return ski_right;
        } else {
            return ski_down;
        }
    }

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void move() {
        if (isAlive()) {
            if (direction == Direction.LEFT) {
                this.getPosition().x--;
            }
            else if (direction == Direction.RIGHT) {
                this.getPosition().x++;
                this.getPosition().x++;
                
            }
          

        }
    }

}
