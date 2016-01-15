/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiinggame;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author raviraina
 */
public class Item {

    public void draw(Graphics graphics) {

//        graphics.drawImage(image, ., y, x, x, null);
    }

    public Item(int x, int y, String type, Image image, boolean breakable) {
        this.x = x;
        this.y = y;
        this.type = type;

        this.breakable = breakable;
    }

    private int x, y;
    private String type;
    private Image image;
    private boolean breakable;

    //<editor-fold defaultstate="collapsed" desc="Properties">
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
//</editor-fold>

}
