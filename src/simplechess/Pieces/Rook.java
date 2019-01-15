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
    private boolean firstMove = true;
        
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
                
        int plays[][] = new int[simplechess.Board.BOARD_SIZE][simplechess.Board.BOARD_SIZE];
        
        for(int i = x+1; i <= 7; i++){
            if(simplechess.Board.board[y][i].getPiece() == null){
                plays[y][i] = 1;
            }else{ 
                if(simplechess.Board.board[y][i].getPiece().blackPlayer != blackPlayer)
                    plays[y][i] = 2;
                break;
            }
        }
        
        for(int i = x-1; i >= 0; i--){
            if(simplechess.Board.board[y][i].getPiece() == null){
                plays[y][i] = 1;
            }else{
                if(simplechess.Board.board[y][i].getPiece().blackPlayer != blackPlayer)
                    plays[y][i] = 2;
                break;
            }
        }
        
        for(int i = y+1; i <= 7; i++){
            if(simplechess.Board.board[i][x].getPiece() == null){
                plays[i][x] = 1;
            }else{
                if(simplechess.Board.board[i][x].getPiece().blackPlayer != blackPlayer)
                    plays[i][x] = 2;
                break;
            }
        }
        
        for(int i = y-1; i >= 0; i--){
            if(simplechess.Board.board[i][x].getPiece() == null){
                plays[i][x] = 1;
            }else{
                if(simplechess.Board.board[i][x].getPiece().blackPlayer != blackPlayer)
                    plays[i][x] = 2;
                break;
            }
        }
        
        if(firstMove){
            plays[y][4] = 1;
        }
        
        return plays;
    }

    @Override
    public void move() {
    }

}
