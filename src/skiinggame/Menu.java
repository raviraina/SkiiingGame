/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiinggame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author raviraina
 */
public class Menu {
    
    int rectX = 390;
    
    
    public Rectangle playButton = new Rectangle(rectX, 150, 100, 50);
    public Rectangle helpButton = new Rectangle(rectX, 250, 100, 50);
    public Rectangle quitButton = new Rectangle(rectX, 350, 100, 50);


    public void render(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.black);
        g.drawString("The Game", 330, 100);
        
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("Play", playButton.x + 19, playButton.y + 35);
        g2d.draw(playButton);
        g.drawString("Help", helpButton.x + 19, helpButton.y + 35);
        g2d.draw(helpButton);
        g.drawString("Quit", quitButton.x + 19, quitButton.y + 35);
        g2d.draw(quitButton);
        
    }
    
}
