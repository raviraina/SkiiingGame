/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiinggame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author raviraina
 */
public class MouseInput implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        int mx = e.getX();
        int my = e.getY();
        int rectX = 390;
                
        
        if (mx >= rectX && mx <= rectX + 100) {
            if (my >= 150 && my <= 200) {
//                Button
                Mountain.setState(GameState.SKIING);
                
            }
            
        }
        
//    public Rectangle playButton = new Rectangle(390, 150, 100, 50);
//    public Rectangle helpButton = new Rectangle(390, 250, 100, 50);
//    public Rectangle quitButton = new Rectangle(390, 350, 100, 50);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
