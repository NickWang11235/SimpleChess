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
        
        /**
         * Set piece and update the coordinate of the piece
         * @param piece
         * @param y
         * @param x 
         */
        public void setPiece(Piece piece, int y, int x){
            this.piece = piece;
            piece.setX(x);
            piece.setY(y);
        }
        
        public boolean isEmpty(){
            return piece == null;
        }

    }
    
    public static Block board[][] = new Block[BOARD_SIZE][BOARD_SIZE];
    private static Piece selectedPiece;
    
    public Board(){
        //Init blocks to contain null
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
    
    /**
     * Set the piece at row, col to piece, and update y,x of piece
     * @param row
     * @param col
     * @param piece 
     */
    public static void setBloctAt(int row, int col, Piece piece){
        board[row][col].setPiece(piece, row, col);
    }

    /**
     * @return currently selected piece
     */
    public static Piece getSelectedPiece(){
        return selectedPiece;
    }
        
    /**
     * checks if selectedPiece can move to row, col
     * @param row
     * @param col
     * @return 
     */
    public boolean checkPlay(int row, int col){
        
        int plays[][] = selectedPiece.getValidPlays(); 
        return plays[row][col] != 0;
        
    }
    
    /**
     * Attempt to make a movement with the selectedPiece
     * @param y
     * @param x
     * @return is move possible
     */
    public boolean move(int y, int x){

        Piece temp = board[y][x].getPiece();
        boolean moved;
        
        if(temp != selectedPiece && //not moving onto itself
                checkPlay(y,x) && //play is possible
                !(temp != null && //place to move to is not empty
                temp.blackPlayer == selectedPiece.blackPlayer)){ //not moving onto the player's own piece

            //Clear the block selectedPiece was at
            board[selectedPiece.getY()][selectedPiece.getX()].piece = null;
            //Set selectedPiece to the desired block
            board[y][x].setPiece(selectedPiece, y, x);
            //Handles any action needed to be decided after the piece is moved
            selectedPiece.move();
            moved = true;
        }
        
        moved = false;
        selectedPiece = null;
        
        return moved;
    }
    
    /**
     * select a piece if no piece has been selected
     * @param y
     * @param x
     * @return 
     */
    private boolean selectedPiece(int y, int x){
        Piece temp = board[y][x].getPiece();
        if(temp != null){
            //If nothing is selected
            selectedPiece = temp;
            return true;
        }        
        return false;
    }
    
    /**
     * Any time a selection of a block is made
     * @param y
     * @param x 
     */
    public void action(int y, int x){
        //Check if selecting
        if(selectedPiece == null){
            selectedPiece(y,x);
        //If moving
        }else{
           move(y,x);
        }

    }
    
}
