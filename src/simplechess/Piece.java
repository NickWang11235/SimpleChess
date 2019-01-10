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
    
    public int[] getRulesSlope(){
        int[] slopes = new int[type.rules.length];
        for(int i = 0; i < slopes.length; i++){
            slopes[i] = type.rules[i].slope;
        }
        return slopes;
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
