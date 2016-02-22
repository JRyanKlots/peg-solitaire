/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegsolitaire;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Ryan
 */
public class MessageBoard extends Rectangle {
    
    private String message;
    
    //TOBE added
    public MessageBoard(int x, int y, int width, int height) {
        super(x, y, width, height);
        message = "MESSAGE GOES HERE";
    }
    
    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int)(this.getX()), (int)this.getY(), (int)this.getWidth(), (int)this.getHeight());
        g.setColor(Color.BLACK);
        g.drawString(message, (int)(this.getX() + this.getWidth()/4), (int)(this.getY() + this.getHeight()/2));
    }
    
}
