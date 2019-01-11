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
public class Pawn extends Piece {

    private static BufferedImage imgBlack, imgWhite;
    
    static {
        imgBlack = ImageLoader.loadImage("/Black/Pawn_Black.png");
        imgWhite = ImageLoader.loadImage("/White/Pawn_White.png");
    }
    
    private boolean firstMove = true;
    
    public Pawn(int x, int y, boolean blackPlayer){
        super(x, y, blackPlayer);
        img = blackPlayer ? imgBlack : imgWhite;
    }
    
    @Override
    public int[][] getValidPlays() {
        
        int plays[][] = new int[simplechess.Board.BOARD_SIZE][simplechess.Board.BOARD_SIZE];
        int dy = blackPlayer? 1 : -1;
        
        if(firstMove){
            for(int i = 1; i <= 2 ; i++){
                if(simplechess.Board.board[y + i*dy][x].getPiece() != null){
                    break;
                }
                plays[y + i*dy][x] = 1;
            }
        }else{
            if( y + dy >= 0 && y + dy <= 7 && simplechess.Board.board[y + dy][x].getPiece() == null){
                plays[y + dy][x] = 1;
            }
        }
        
        if(y + dy >= 0 && y + dy <= 7){
            if(x <= 6)
                plays[y + dy][x + 1] =  simplechess.Board.board[y + dy][x+1].getPiece() != null && 
                                            simplechess.Board.board[y + dy][x+1].getPiece().blackPlayer != blackPlayer ? 2 : 0;
            if(x >= 1)
                plays[y + dy][x - 1] =  simplechess.Board.board[y + dy][x-1].getPiece() != null && 
                                            simplechess.Board.board[y + dy][x-1].getPiece().blackPlayer != blackPlayer ? 2 : 0;
        }
        
        return plays;
    }

    @Override
    public void move() {
        
        firstMove = false;

        if(y == 0 || y == 7){
            simplechess.Board.setBloctAt(y, x, new Queen(2, 7, blackPlayer));
            System.out.println(simplechess.Board.getBlockAt(y, x).getPiece());
            System.out.println(y);
            System.out.println(simplechess.Board.getBlockAt(y, x).getPiece().y);
            System.out.println(x);
            System.out.println(simplechess.Board.getBlockAt(y, x).getPiece().x);
        }

    }
    
}
