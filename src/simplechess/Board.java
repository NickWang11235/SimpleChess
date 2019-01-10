/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechess;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nickw
 */
public class Board {
    
    public static final int BOARD_SIZE = 8;
    
    public class Block{
        
        private Piece piece;

        public Block(Piece piece) {
            this.piece = piece;
        }
        
        public Piece getPiece(){
            return piece;
        }
        
        public void setPiece(Piece piece){
            this.piece = piece;
        }
        
        public void setPiece(Piece piece, int y, int x){
            this.piece = piece;
            piece.x = x;
            piece.y = y;
        }
        
        public boolean isEmpty(){
            return piece == null;
        }

        private void clearPiece() {
            piece = null;
        }
        
    }
    
    private Block board[][] = new Block[BOARD_SIZE][BOARD_SIZE];
    private Piece selectedPiece;
    
    public Board() throws IOException{
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                board[i][j] = new Block(null);
            }
        }
        initPieces();
    }
    
    private void initPieces() throws IOException{
        
        //Init white player pieces
        board[0][0].setPiece(new Piece(Piece.PieceType.ROOK, 0, 0, true, false,
                "/Black/Rook_Black.png"));
        board[0][1].setPiece(new Piece(Piece.PieceType.KNIGHT, 1, 0, true, false,
                "/Black/Knight_Black.png"));
        board[0][2].setPiece(new Piece(Piece.PieceType.BISHOP, 2, 0, true, false,
                "/Black/Bishop_Black.png"));
        board[0][3].setPiece(new Piece(Piece.PieceType.QUEEN, 3, 0, true, false,
                "/Black/Queen_Black.png"));
        board[0][4].setPiece(new Piece(Piece.PieceType.KING, 4, 0, true, false,
                "/Black/King_Black.png"));
        board[0][5].setPiece(new Piece(Piece.PieceType.BISHOP, 5, 0, true, false,
                "/Black/Bishop_Black.png"));
        board[0][6].setPiece(new Piece(Piece.PieceType.KNIGHT, 6, 0, true, false,
                "/Black/Knight_Black.png"));
        board[0][7].setPiece(new Piece(Piece.PieceType.ROOK, 7, 0, true, false,
                "/Black/Rook_Black.png"));
        
        //Init black player pieces
        board[7][0].setPiece(new Piece(Piece.PieceType.ROOK, 0, 7, false, false,
                "/White/Rook_White.png"));
        board[7][1].setPiece(new Piece(Piece.PieceType.KNIGHT, 1, 7, false, false,
                "/White/Knight_White.png"));
        board[7][2].setPiece(new Piece(Piece.PieceType.BISHOP, 2, 7, false, false,
                "/White/Bishop_White.png"));
        board[7][3].setPiece(new Piece(Piece.PieceType.QUEEN, 3, 7, false, false,
                "/White/Queen_White.png"));
        board[7][4].setPiece(new Piece(Piece.PieceType.KING, 4, 7, false, false,
                "/White/King_White.png"));
        board[7][5].setPiece(new Piece(Piece.PieceType.BISHOP, 5, 7, false, false,
                "/White/Bishop_White.png"));
        board[7][6].setPiece(new Piece(Piece.PieceType.KNIGHT, 6, 7, false, false,
                "/White/Knight_White.png"));
        board[7][7].setPiece(new Piece(Piece.PieceType.ROOK, 7, 7, false, false,
                "/White/Rook_White.png"));
        
        //Init pawns for both players
        for(int i = 0; i < BOARD_SIZE; i++){
            board[1][i].setPiece(new Piece(Piece.PieceType.PAWNB, i, 1, true, true,
                "/Black/Pawn_Black.png"));
            board[6][i].setPiece(new Piece(Piece.PieceType.PAWNW, i, 6, false, true,
                "/White/Pawn_White.png"));
        }

    }
    
    public Block getBlockAt(int row, int col){
        return board[row][col];
    }
    
    public void setBloctAt(int row, int col, Piece piece){
        board[row][col].setPiece(piece, col, row);
    }

    public Piece getSelectedPiece(){
        return selectedPiece;
    }
    
    public void action(int y, int x){
        Piece temp = board[y][x].getPiece();
        if(selectedPiece == null){
            if(temp != null){
                //If nothing is selected
                selectedPiece = temp;
            }
        }else{
            if(temp != selectedPiece && checkPlay(y,x)){
                //If the play is valid and is not clicking on itself 
                if(temp == null){
                    //moving to empty space
                    board[selectedPiece.y][selectedPiece.x].piece = null;
                    board[y][x].setPiece(selectedPiece, y, x);
                }else{
                    //Moving onto another piece
                    if(temp.blackPlayer != selectedPiece.blackPlayer){
                        //The piece belongs to aother player
                        board[selectedPiece.y][selectedPiece.x].piece = null;
                        board[y][x].setPiece(selectedPiece, y, x);
                    }
                }
                selectedPiece.firstMove = false;
                
            }
            selectedPiece = null;
        }

    }
    
    public boolean checkPlay(int row, int col){
        
        int plays[][] = getValidPlays(selectedPiece); 
        return plays[row][col] != 0;
        
    }
    
    public int[][] getValidPlays(Piece selectedPiece){
        
        int plays[][] = new int[BOARD_SIZE][BOARD_SIZE];
        switch(selectedPiece.getType()){
            case KING:
                int row, col;
                for(int i = -1; i <= 1; i++){
                    for(int j = -1; j <= 1; j++){
                        row = Math.max(0, Math.min( j + selectedPiece.y, BOARD_SIZE -1));
                        col = Math.max(0, Math.min( i + selectedPiece.x, BOARD_SIZE -1));
                        if(board[row][col].piece != null){
                            if(board[row][col].piece.blackPlayer != selectedPiece.blackPlayer){
                                plays[row][col] = 2;
                            }
                        }else{
                            plays[row][col] = 1;
                        }
                    }
                }
                            
                break;
            case QUEEN:
                break;
            case ROOK:
                break;
            case BISHOP:
                break;
            case KNIGHT:
                break;
            case PAWNB:
                row = selectedPiece.y;
                col = selectedPiece.x;
                if(selectedPiece.firstMove){
                    for(int i = 1; i <= 2; i++){
                        if(board[row + i][col].piece != null){
                            if(board[row + i][col].piece.blackPlayer != selectedPiece.blackPlayer){
                                plays[row + i][col] = 2;
                            }
                            break;
                        }else{
                            plays[row + i][col] = 1;
                        }
                    }
                }else{
                    if(board[row + 1][col].piece != null){
                        if(board[row + 1][col].piece.blackPlayer != selectedPiece.blackPlayer){
                            plays[row + 1][col] = 2;
                        }
                    }else{
                        plays[row + 1][col] = 1;
                    }
                }
                if(board[row + 1][col].piece != null)
                break;
            case PAWNW:
                break;
        }

        return plays;
    }
    
    public void print(int[][] plays){
                
        for(int[] a : plays){
            for(int b : a){
                System.out.print(b + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    
}
