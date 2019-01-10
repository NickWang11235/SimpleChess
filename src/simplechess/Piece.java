/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechess;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Piece{
    
    private PieceType type;
    public BufferedImage img;
    public boolean firstMove;
    public boolean blackPlayer;
    public int x, y;
    
    public Piece(PieceType type, int x, int y, boolean blackPlayer, boolean firstMove, String path) throws IOException {
        this.type = type;
        this.x = x;
        this.y = y;
        this.blackPlayer = blackPlayer;
        this.firstMove = firstMove;
        img = ImageLoader.loadImage(path);
    }
    
    public PieceType getType(){
        return type;
    }
    
    public enum PieceType{

        KING,
        QUEEN,
        ROOK,
        BISHOP,
        KNIGHT,
        PAWNB,
        PAWNW;

    }

}
