/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiinggame;

import components.HealthProviderIntf;
import environment.Actor;
import environment.Velocity;
import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

/**
 *
 * @author raviraina
 */
public class Skier extends Actor implements HealthProviderIntf {

    private BufferedImage ski_down, ski_left, ski_right;
    private int health;
    private Direction direction;
    private final int maxY = 100;
    private int minX = 5;
    private int maxX = 870;
    private int speed = 3;
    private int invultimer;
    private int maxHealth;

    public void draw(Graphics graphics) {
        graphics.drawImage(getImage(), getPosition().x, getPosition().y, null);
    }

    {
        ski_down = (BufferedImage) ResourceTools.loadImageFromResource("skiinggame/ski_down.png");
        ski_left = (BufferedImage) ResourceTools.loadImageFromResource("skiinggame/ski_left.png");
        ski_right = (BufferedImage) ResourceTools.loadImageFromResource("skiinggame/ski_right.png");

        setDirection(Direction.DOWN);
        setHealth(100);
    }

    public Skier(Point position, Velocity velocity) {
        super(position, velocity);
        

    }

    /**
     * @return the health
     */
    /**
     * @param health the health to set
     */
    /**
     * @param health the health to set
     */
    public boolean isAlive() {
        return (getHealth() > 0);
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

        if (getPosition().y < maxY) {
            setVelocity(0, 1);
            super.move();
        }

        if (isAlive()) {
            // if skier is less than minimum x then
            //   - set direction to DOWN
            //   - push his x back to min x

            if (this.getPosition().x < minX) {
                setDirection(Direction.DOWN);
                getPosition().x = minX;
            }

            if (this.getPosition().x > maxX) {
                setDirection(direction.DOWN);
                getPosition().x = maxX;
            }

            //TODO - limit motion to the RIGHT
            if (direction == Direction.LEFT) {
                this.getPosition().x -= speed;
            } else if (direction == Direction.RIGHT) {
                this.getPosition().x += speed;
            }

        }
    }

    @Override
    public int getMinimumHealth() {
        return 0;
    }

    @Override
    public int getMaximumHealth() {
        return 100;
    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public double getFractionalHealth() {
        return 0;
    }

    @Override
    public double getPercentHealth() {
        return 0;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        
    }

    public void addHealth(int health) {

        this.health += health;
        
        
        if (this.health > maxHealth) {
            this.health = maxHealth;
        }
        
        if (this.health < getMinimumHealth()) {
            this.health = getMinimumHealth();
        }
        
        if (invultimer == 0) {
            this.setHealth(this.getHealth() + health);
        }

        if (this.getHealth() <= 0) {
            JOptionPane.showMessageDialog(null, "RIP");
        }
        
        

    }

}
