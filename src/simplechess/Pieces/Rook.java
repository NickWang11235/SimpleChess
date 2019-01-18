/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechess.Pieces;

import java.awt.image.BufferedImage;
import simplechess.Board;
import simplechess.Board.Block;
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
    public int[][] getValidPlays(Board board) {
                
        int plays[][] = new int[board.BOARD_SIZE][board.BOARD_SIZE];
        Block b[][] = board.getBoard();
        
        for(int i = x+1; i <= 7; i++){
            if(b[y][i].getPiece() == null){
                plays[y][i] = 1;
            }else{ 
                if(b[y][i].getPiece().blackPlayer != blackPlayer)
                    plays[y][i] = 2;
                break;
            }
        }
        
        for(int i = x-1; i >= 0; i--){
            if(b[y][i].getPiece() == null){
                plays[y][i] = 1;
            }else{
                if(b[y][i].getPiece().blackPlayer != blackPlayer)
                    plays[y][i] = 2;
                break;
            }
        }
        
        for(int i = y+1; i <= 7; i++){
            if(b[i][x].getPiece() == null){
                plays[i][x] = 1;
            }else{
                if(b[i][x].getPiece().blackPlayer != blackPlayer)
                    plays[i][x] = 2;
                break;
            }
        }
        
        for(int i = y-1; i >= 0; i--){
            if(b[i][x].getPiece() == null){
                plays[i][x] = 1;
            }else{
                if(b[i][x].getPiece().blackPlayer != blackPlayer)
                    plays[i][x] = 2;
                break;
            }
        }
        
        return plays;
    }

    @Override
    public void move(Board board) {
    }

}
