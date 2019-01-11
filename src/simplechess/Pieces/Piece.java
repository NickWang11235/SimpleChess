/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechess.Pieces;

import java.awt.image.BufferedImage;

public abstract class Piece{
    
    public BufferedImage img;
    public boolean blackPlayer;
    public int x, y;
    
    public Piece(int x, int y, boolean blackPlayer){
        this.x = x;
        this.y = y;
        this.blackPlayer = blackPlayer;
    }
    
    public abstract void move();
    
    public abstract int[][] getValidPlays();

}
