/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiinggame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author raviraina
 */
public class Item {

    public void draw(Graphics graphics) {

        graphics.drawImage(image, x, y, null);

    }

    public Item(int x, int y, String type, Image image, boolean breakable) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.image = image;

//        if (type.equals()) {
//            
//        }
        this.breakable = breakable;
    }

    public static final String ITEM_TYPE_POWER_UP = "POWER_UP";
    public static final String ITEM_TYPE_TREE = "TREE";

    private int x, y;
    private String type;
    private Image image;
    private boolean breakable;

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public Rectangle getBoundary() {
        return new Rectangle(x + (image.getWidth(null) / 4), y + (image.getHeight(null) / 6), image.getWidth(null) / 2, image.getHeight(null) / 3);
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @return the breakable
     */
    public boolean isBreakable() {
        return breakable;
    }

    /**
     * @param breakable the breakable to set
     */
    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }
//</editor-fold>

}
