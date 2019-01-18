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
public class King extends Piece{

    private static BufferedImage imgBlack, imgWhite;
    
    static {
        imgBlack = ImageLoader.loadImage("/Black/King_Black.png");
        imgWhite = ImageLoader.loadImage("/White/King_White.png");
    }

    public King(int x, int y, boolean blackPlayer){
        super(x, y, blackPlayer);
        img = blackPlayer ? imgBlack : imgWhite;
    }

    @Override
    public int[][] getValidPlays(Board board) {
        int row, col;
        int plays[][] = new int[board.BOARD_SIZE][board.BOARD_SIZE];  
        Block b[][] = board.getBoard();
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                row = Math.max(0, Math.min( j + y, board.BOARD_SIZE -1));
                col = Math.max(0, Math.min( i + x, board.BOARD_SIZE -1));
                if(b[row][col].getPiece() != null){
                    if(b[row][col].getPiece().blackPlayer != blackPlayer){
                        plays[row][col] = 2;
                    }
                }else{
                    plays[row][col] = 1;
                }
            }
        }
        return plays;
    }    

    @Override
    public void move(Board board) {
    }
    
}
