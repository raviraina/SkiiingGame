/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiinggame;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author raviraina
 */
public class Drop {
    
    {
        height = 10;
        minHeight = 5;
        maxHeight = 125;
        
        width = 5;
        minWidth = 5;
        maxWidth = 125;
        
        x = (100);
        y = (10);
        
        red = 226;
        green = 226;
        blue = 226;
        
        opacity = 200;
        minOpacity = 10;
        maxOpacity = 240;
        
        
        
    }
    
    public Drop(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(red, green, blue, opacity ));
        graphics.fillRect(getX(), getY(), width, height);
        
    }
    
    int height;
    int minHeight;
    int maxHeight;
    
    int width;
    int minWidth;
    int maxWidth;
    
    private int x;
    private int y;
    
    int red;
    int green;
    int blue;
    
    int opacity;
    int minOpacity;
    int maxOpacity;

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

    void opacity(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
