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
public class Knight extends Piece{

    private static BufferedImage imgBlack, imgWhite;
    
    static {
        imgBlack = ImageLoader.loadImage("/Black/Knight_Black.png");
        imgWhite = ImageLoader.loadImage("/White/Knight_White.png");
    }
    
    public Knight(int x, int y, boolean blackPlayer){
        super(x, y, blackPlayer);
        img = blackPlayer ? imgBlack : imgWhite;
    }

    @Override
    public int[][] getValidPlays(Board board) {
        
        int plays[][] = new int[board.BOARD_SIZE][board.BOARD_SIZE];
        Block b[][] = board.getBoard();
        
        try{
            if(board.getBoard()[y + 1][x + 2].getPiece() == null){
                plays[y + 1][x + 2] = 1;
            }else if(board.getBoard()[y + 1][x + 2].getPiece().blackPlayer != blackPlayer){
                    plays[y + 1][x + 2] = 2;
            }
        }catch(Exception e){
        }
        
        try{
            if(board.getBoard()[y + 1][x - 2].getPiece() == null){
                plays[y + 1][x - 2] = 1;
            }else if(board.getBoard()[y + 1][x - 2].getPiece().blackPlayer != blackPlayer){
                plays[y + 1][x - 2] = 2;
            }
        }catch(Exception e){
        }
        
        try{
            if(board.getBoard()[y - 1][x + 2].getPiece() == null){
                plays[y - 1][x + 2] = 1;
            }else if(board.getBoard()[y - 1][x + 2].getPiece().blackPlayer != blackPlayer){
                    plays[y - 1][x + 2] = 2;
            }
        }catch(Exception e){
        }
        
        try{
            if(board.getBoard()[y - 1][x - 2].getPiece() == null){
                plays[y - 1][x - 2] = 1;
            }else if(board.getBoard()[y - 1][x - 2].getPiece().blackPlayer != blackPlayer){
                    plays[y - 1][x - 2] = 2;
            }
        }catch(Exception e){
        }
        
        try{
            if(board.getBoard()[y + 2][x + 1].getPiece() == null){
                plays[y + 2][x + 1] = 1;
            }else if(board.getBoard()[y + 2][x + 1].getPiece().blackPlayer != blackPlayer){
                    plays[y + 2][x + 1] = 2;
            }
        }catch(Exception e){
        }

        try{
            if(board.getBoard()[y + 2][x - 1].getPiece() == null){
                plays[y + 2][x - 1] = 1;
            }else if(board.getBoard()[y + 2][x - 1].getPiece().blackPlayer != blackPlayer){
                plays[y + 2][x - 1] = 2;
            }
        }catch(Exception e){
        }

        try{
            if(board.getBoard()[y - 2][x + 1].getPiece() == null){
                plays[y - 2][x + 1] = 1;
            }else if(board.getBoard()[y - 2][x + 1].getPiece().blackPlayer != blackPlayer){
                    plays[y - 2][x + 1] = 2;
            }
        }catch(Exception e){
        }
        
        try{
            if(board.getBoard()[y - 2][x - 1].getPiece() == null){
                plays[y - 2][x - 1] = 1;
            }else if(board.getBoard()[y - 2][x - 1].getPiece().blackPlayer != blackPlayer){
                    plays[y - 2][x - 1] = 2;
            }
        }catch(Exception e){
        }
        
        return plays;
    }

    @Override
    public void move(Board board) {
        firstMove = false;
    }
    
}
