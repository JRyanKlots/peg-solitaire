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
public class PegSlot extends Rectangle {
    
    private boolean hasPeg; //true if this has a peg
    private boolean isSelected; ///true if this slot is selected
    
    public PegSlot(int x, int y, int width, int height) {
        super(x, y, width, height); //pass arguments to Rectangle constructor
        hasPeg = false; 
        isSelected = false;
    }
    
    //helper
    public boolean hasPeg() {
        return hasPeg;
    }
    
    public void fillSlot() {
        hasPeg = true;
    }
    
    public void emptySlot() {
        hasPeg = false;
    }
    
    public void select() {
        isSelected = true;
    }
    
    public void deselect() {
        isSelected = false;
    }
    
    public void paint(Graphics g) {
        g.setColor(Color.GRAY); //background color
        g.fillRect((int)this.getX(), (int)this.getY(), (int)this.getWidth(), (int)this.getHeight()); //fill background
        if(hasPeg) { //paint peg if hasPeg
            g.setColor(isSelected ? Color.RED : Color.BLUE); //red if selected, blue otherwise
            g.fillRect((int)(this.getX() + this.getWidth()/4), (int)(this.getY() + this.getHeight()/4),  //paint peg
                    (int)(this.getWidth()/2), (int)(this.getHeight()/2));
        }
    }
    
}
