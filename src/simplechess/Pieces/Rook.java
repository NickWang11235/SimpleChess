/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechess.Pieces;

import java.awt.image.BufferedImage;
import simplechess.ImageLoader;

/**
 *
 * @author nickw
 */
public class Rook extends Piece {

    private static BufferedImage imgBlack, imgWhite;
        
    static {
        imgBlack = ImageLoader.loadImage("/Black/Rook_Black.png");
        imgWhite = ImageLoader.loadImage("/White/Rook_White.png");
    }
    
    public Rook(int x, int y, boolean blackPlayer){
        super(x, y, blackPlayer);
        img = blackPlayer ? imgBlack : imgWhite;
    }

    @Override
    public int[][] getValidPlays() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}