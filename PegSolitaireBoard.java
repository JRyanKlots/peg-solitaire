/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegsolitaire;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

/**
 *
 * @author Ryan
 */
public class PegSolitaireBoard extends JFrame implements MouseListener {

    private PegSlotBoard pSlotBoard; //peg slot board to be painted
    private MessageBoard mBoard; //message board to be painted    
    
    public static final int DEFAULT_WIDTH = 900, DEFAULT_HEIGHT = 600; //default width and height of GUI
    
    public PegSolitaireBoard() {
        //this section deals with initializing the GUI
        this.setTitle("Peg Solitaire");
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setLocation((int)(this.getGraphicsConfiguration().getBounds().getWidth() - this.getWidth()) / 2, 
                (int)(this.getGraphicsConfiguration().getBounds().getHeight() - this.getHeight()) / 2);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //this section deals with intializing the slot and message boards
        pSlotBoard = new PegSlotBoard(this.getRootPane().getX(), this.getRootPane().getY(), 
                (int)(this.getRootPane().getWidth()*.7), this.getRootPane().getHeight(), 5, 5);
        pSlotBoard.fillBoard();
        pSlotBoard.emptyPeg(0, 0);
        pSlotBoard.emptyPeg(0, 4);
        pSlotBoard.emptyPeg(4, 0);
        pSlotBoard.emptyPeg(4, 4);
        
        mBoard = new MessageBoard((int)(pSlotBoard.getX() + pSlotBoard.getWidth()), 
                (int)(pSlotBoard.getY()), (int)(this.getRootPane().getWidth() * .3), 
                this.getRootPane().getHeight());
        
        //this section deals with adding listeners
        this.addMouseListener(this);
    }
    
    //this section defines the paint method (we want the slot and message boards to be painted)
    @Override
    public void paint(Graphics g) {
        pSlotBoard.paint(g);
        mBoard.paint(g);
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        //System.out.println("CLICK!");
        if(pSlotBoard.contains(me.getX(), me.getY())) {
            //System.out.println("CONTAINS!");
            pSlotBoard.handleClick(me.getX(), me.getY());
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) {
        PegSolitaireBoard psb = new PegSolitaireBoard();
    }
    
}
