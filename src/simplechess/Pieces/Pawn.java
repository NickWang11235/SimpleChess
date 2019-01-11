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
        
        int row = y;
        int col = x;
        int dy = blackPlayer? 1 : -1;
        
        if(firstMove){
            for(int i = 1; i <= 2 ; i++){
                if(simplechess.Board.board[row + i*dy][col].getPiece() != null){
                    break;
                }
                plays[row + i*dy][col] = 1;
            }
        }else{
            if( row + dy >= 0 && row + dy <= 7 && simplechess.Board.board[row + dy][col].getPiece() == null){
                plays[row + dy][col] = 1;
            }
        }
        
        if(row + dy >= 0 && row + dy <= 7){
            if(col <= 6)
                plays[row + dy][col + 1] =  simplechess.Board.board[row + dy][col+1].getPiece() != null && 
                                            simplechess.Board.board[row + dy][col+1].getPiece().blackPlayer != blackPlayer ? 2 : 0;
            if(col >= 1)
                plays[row + dy][col - 1] =  simplechess.Board.board[row + dy][col-1].getPiece() != null && 
                                            simplechess.Board.board[row + dy][col-1].getPiece().blackPlayer != blackPlayer ? 2 : 0;
        }
        
        return plays;
    }

    @Override
    public void move() {
        
        firstMove = false;

        if(y == 0 || y == 7){
            simplechess.Board.setBloctAt(y, x, new Queen(y, x, blackPlayer));
        }

    }
    
}
