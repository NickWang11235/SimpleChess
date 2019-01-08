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
    public boolean firstMove = true;
    public boolean blackPlayer;

    public Piece(PieceType type, boolean blackPlayer, String path) throws IOException {
        this.type = type;
        this.blackPlayer = blackPlayer;
        img = ImageIO.read(new File(path));
    }
    
    public PieceType getType(){
        return type;
    }
    
    public enum PieceType{

        KING(1, Rules.FORWARD, Rules.DIAGONAL),
        QUEEN(Integer.MAX_VALUE, Rules.FORWARD, Rules.DIAGONAL),
        ROOK(Integer.MAX_VALUE, Rules.FORWARD),
        BISHOP(Integer.MAX_VALUE, Rules.DIAGONAL),
        KNIGHT(1, Rules.SLANT),
        PAWN(1, Rules.FORWARD);

        Rules rules[];
        int radius;

        PieceType(int radius, Rules... rule){
            this.radius = radius;
            rules = rule;
        }
    }

    private enum Rules{

        FORWARD(0),
        DIAGONAL(1),
        SLANT(2);

        int slope;

        Rules(int m){
            slope = m;
        }

    }

}
