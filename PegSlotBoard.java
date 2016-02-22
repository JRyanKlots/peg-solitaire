/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegsolitaire;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Ryan
 */
public class PegSlotBoard extends Rectangle {
    
    private PegSlot[][] pSlots; //the peg slots of this peg slot board
    private int[] target, source; //the location of the target and source peg slots, stored as [row, col]
    
    public PegSlotBoard(int x, int y, int width, int height, int numRows, int numCols) {
        super(x, y, width, height); //pass arguments to Rectangle constructor
        pSlots = new PegSlot[numRows][numCols]; //create pSlots
        for(int r = 0; r < numRows; r++) { //initialize pSlots
            for(int c = 0; c < numCols; c++) {
                pSlots[r][c] = new PegSlot(x + (c * width / numCols), y + (r*height / numRows), 
                        width / numCols, height / numRows);
            }
        }
        target = new int[2]; //clear target
        reset(target);
        source = new int[2]; //clear source
        reset(source);
    }
    
    //Precondition: arr.length >= 2
    private void fill(int[] arr, int r, int c) {
        arr[0] = r;
        arr[1] = c;
    }
    
    //set all values in arr to -1
    private void reset(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = -1;
        }
    }
    
    //check if all values in arr are -1
    private boolean isNull(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != -1) {
                return false;
            }
        }
        return true;
    }
    
    //paint function
    public void paint(Graphics g) {
        for(int r = 0; r < pSlots.length; r++) { //paint each slot in pSlots
            for(int c = 0; c < pSlots[r].length; c++) {
                pSlots[r][c].paint(g);
            }
        }
    }
    
    //what to do if a click occurs withing this Peg Slot Board
    public void handleClick(int x, int y) {
        for(int r = 0; r < pSlots.length; r++) {
            for(int c = 0; c < pSlots[r].length; c++) {
                if(pSlots[r][c].contains(x, y)) { //find which pSlot the click happened in
                    setTargetSource(r, c); //manage target and source
                }
            }
        }
        doHop(); //perform hop if necessary
    }
    
    //helper function to manage target and source
    private void setTargetSource(int r, int c) {
        if(isNull(source)) { //set source if source is unset
            fill(source, r, c);
            pSlots[r][c].select();
        } else { //otherwise change the target
            fill(target, r, c);
        }
        if(target[0] == source[0] && target[1] == source[1]) { //if target and source are the same, clear them both
            resetTargetSource();
        }
    }
    
    //helper function to reset target and source
    private void resetTargetSource() {
        reset(target);
        pSlots[source[0]][source[1]].deselect();
        reset(source);
    }
    
    //do hop if can hop
    private void doHop() {
        if(!isNull(target) && !pSlots[target[0]][target[1]].hasPeg() && //check that target and source are set, with corresponding pegs
                pSlots[source[0]][source[1]].hasPeg()) {
            if(target[0] == source[0]) {
                if(Math.abs(target[1] - source[1]) == 2) { //check that x values differ by exactly 2
                    pSlots[target[0]][target[1]].fillSlot(); //fill target
                    pSlots[target[0]][(target[1] + source[1]) / 2].emptySlot(); //get midpoint, remove
                    pSlots[source[0]][source[1]].emptySlot(); //remove source
                    resetTargetSource();
                }
            } else if(target[1] == source[1]) {
                if(Math.abs(target[0] - source[0]) == 2) { //check that y values differ by exactly 2
                    pSlots[target[0]][target[1]].fillSlot(); //fill target
                    pSlots[(target[0] + source[0]) / 2][target[1]].emptySlot(); //get midpoint, remove
                    pSlots[source[0]][source[1]].emptySlot(); //remove source
                    resetTargetSource();
                }
            }
        }
    }

    //puts pegs into all of the  Peg SLots
    public void fillBoard() {
        for(int r = 0; r < pSlots.length; r++) {
            for(int c = 0; c < pSlots[r].length; c++) {
                pSlots[r][c].fillSlot();
            }
        }
    }
    
    //remove a peg from a given slot
    public void emptyPeg(int r, int c) {
        pSlots[r][c].emptySlot();
    }
}
