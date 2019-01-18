/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechess.Pieces;

import java.awt.image.BufferedImage;
import simplechess.Board;

public abstract class Piece{
    
    public BufferedImage img;
    public boolean blackPlayer;
    protected int x, y;
    
    public Piece(int x, int y, boolean blackPlayer){
        this.x = x;
        this.y = y;
        this.blackPlayer = blackPlayer;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public abstract void move(Board board);
    
    public abstract int[][] getValidPlays(Board board);

}
