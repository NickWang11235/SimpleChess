/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechess;

import simplechess.Pieces.*;

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

    }
    
    public static Block board[][] = new Block[BOARD_SIZE][BOARD_SIZE];
    private static Piece selectedPiece;
    
    public Board(){
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                board[i][j] = new Block(null);
            }
        }
        initPieces();
    }
    
    private void initPieces(){

        //Init white player pieces
        board[0][0].setPiece(new Rook(0, 0, true));
        board[0][1].setPiece(new Knight(1, 0, true));
        board[0][2].setPiece(new Bishop(2, 0, true));
        board[0][3].setPiece(new Queen(3, 0, true));
        board[0][4].setPiece(new King(4, 0, true));
        board[0][5].setPiece(new Bishop(5, 0, true));
        board[0][6].setPiece(new Knight(6, 0, true));
        board[0][7].setPiece(new Rook(7, 0, true));
        
        //Init black player pieces
        board[7][0].setPiece(new Rook(0, 7, false));
        board[7][1].setPiece(new Knight( 1, 7, false));
        board[7][2].setPiece(new Bishop(2, 7, false));
        board[7][3].setPiece(new Queen( 3, 7, false));
        board[7][4].setPiece(new King(4, 7, false));
        board[7][5].setPiece(new Bishop( 5, 7, false));
        board[7][6].setPiece(new Knight( 6, 7, false));
        board[7][7].setPiece(new Rook(7, 7, false));
        
        //Init pawns for both players
        for(int i = 0; i < BOARD_SIZE; i++){
            board[1][i].setPiece(new Pawn(i, 1, true));
            board[6][i].setPiece(new Pawn(i, 6, false));
        }

    }
    
    public Block[][] getBoard(){
        return board;
    }
    
    public static Block getBlockAt(int row, int col){
        return board[row][col];
    }
    
    public static void setBloctAt(int row, int col, Piece piece){
        board[row][col].setPiece(piece, col, row);
    }

    public static Piece getSelectedPiece(){
        return selectedPiece;
    }
        
    public boolean checkPlay(int row, int col){
        
        int plays[][] = selectedPiece.getValidPlays(); 
        return plays[row][col] != 0;
        
    }
    
    public boolean move(int y, int x){

        Piece temp = board[y][x].getPiece();
        boolean moved;
        
        if(temp != selectedPiece && 
                checkPlay(y,x) && 
                !(temp != null && 
                temp.blackPlayer == selectedPiece.blackPlayer)){
            
            //moving to empty space
            board[selectedPiece.y][selectedPiece.x].piece = null;
            board[y][x].setPiece(selectedPiece, y, x);
            selectedPiece.move();
            moved = true;
        }
        
        moved = false;
        selectedPiece = null;
        
        return moved;
    }
    
    private boolean selectedPiece(int y, int x){
        Piece temp = board[y][x].getPiece();
        if(temp != null){
            //If nothing is selected
            selectedPiece = temp;
            return true;
        }        
        return false;
    }
    
    public void action(int y, int x){
        if(selectedPiece == null){
            selectedPiece(y,x);
        }else{
           move(y,x);
        }

    }
    
    public static void print(int[][] plays){
                
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
