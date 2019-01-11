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
public class Bishop extends Piece{
    
    private static BufferedImage imgBlack, imgWhite;
    
    static {
        imgBlack = ImageLoader.loadImage("/Black/Bishop_Black.png");
        imgWhite = ImageLoader.loadImage("/White/Bishop_White.png");
    }

    public Bishop(int x, int y, boolean blackPlayer){
        super(x, y, blackPlayer);
        img = blackPlayer ? imgBlack : imgWhite;
    }
    
    @Override
    public int[][] getValidPlays() {
        int plays[][] = new int[simplechess.Board.BOARD_SIZE][simplechess.Board.BOARD_SIZE];
        
        for(int i = x+1, j = y+1; i <= 7 && j <= 7; i++, j++){
            if(simplechess.Board.board[j][i].getPiece() == null){
                plays[j][i] = 1;
            }else{
                if(simplechess.Board.board[j][i].getPiece().blackPlayer != blackPlayer)
                    plays[j][i] = 2;
                break;
            }
        }
        
        for(int i = x-1, j = y-1; i >= 0 && j >= 0; i--, j--){
            if(simplechess.Board.board[j][i].getPiece() == null){
                plays[j][i] = 1;
            }else{
                if(simplechess.Board.board[j][i].getPiece().blackPlayer != blackPlayer)
                    plays[j][i] = 2;
                break;
            }
        }
        
        for(int i = x+1, j = y-1; i <= 7 && j >= 1; i++, j--){
            if(simplechess.Board.board[j][i].getPiece() == null){
                plays[j][i] = 1;
            }else{
                if(simplechess.Board.board[j][i].getPiece().blackPlayer != blackPlayer)
                    plays[j][i] = 2;
                break;
            }
        }
        
        for(int i = x-1, j = y+1; i >= 0 && j <= 7; i--, j++){
            if(simplechess.Board.board[j][i].getPiece() == null){
                plays[j][i] = 1;
            }else{
                if(simplechess.Board.board[j][i].getPiece().blackPlayer != blackPlayer)
                    plays[j][i] = 2;
                break;
            }
        }
        
        return plays;
    }

    @Override
    public void move() {
    }
    
}
