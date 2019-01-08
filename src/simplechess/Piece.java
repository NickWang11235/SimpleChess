/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechess;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Piece{
    
    private PieceType type;
    public BufferedImage img;
    public boolean firstMove;
    public boolean blackPlayer;

    public Piece(PieceType type, boolean blackPlayer, String path, boolean firstMove) throws IOException {
        this.type = type;
        this.blackPlayer = blackPlayer;
        img = ImageIO.read(new File(path));
        this.firstMove = firstMove;
    }
    
    public PieceType getType(){
        return type;
    }
    
    public Rules[] getRules(){
        return type.rules;
    }
    
    public int getRadius(){
        return type.radius;
    }
    
    public enum PieceType{

        KING(1, Rules.FORWARD, Rules.DIAGONAL),
        QUEEN(8, Rules.FORWARD, Rules.DIAGONAL),
        ROOK(8, Rules.FORWARD),
        BISHOP(8, Rules.DIAGONAL),
        KNIGHT(1, Rules.SLANT),
        PAWN(1, Rules.FORWARD);

        Rules rules[];
        int radius;

        PieceType(int radius, Rules... rule){
            this.radius = radius;
            rules = rule;
        }
        
        
    }

    public enum Rules{

        FORWARD(0),
        DIAGONAL(1),
        SLANT(2);

        int slope;

        Rules(int m){
            slope = m;
        }

    }

}
