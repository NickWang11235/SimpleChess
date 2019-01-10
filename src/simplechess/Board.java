/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechess;

import java.io.IOException;

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
        
        public boolean isEmpty(){
            return piece == null;
        }

        private void clearPiece() {
            piece = null;
        }
        
    }
    
    private Block board[][] = new Block[BOARD_SIZE][BOARD_SIZE];
    
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
            board[1][i].setPiece(new Piece(Piece.PieceType.PAWN, i, 1, true, true,
                "/Black/Pawn_Black.png"));
            board[6][i].setPiece(new Piece(Piece.PieceType.PAWN, i, 6, false, true,
                "/White/Pawn_White.png"));
        }
        
    }

    public void clearBlockAt(int row, int col) {
        board[row][col].clearPiece();
    }
    
    public Block getBlockAt(int row, int col){
        return board[row][col];
    }
    
    public void setBloctAt(int row, int col, Piece piece){
        board[row][col].setPiece(piece);
    }
    
    public boolean checkPlay(Piece selectedPiece, int row, int col){
        
        int plays[][] = getValidPlays(selectedPiece); 
        
        return plays[row][col] == 1;
    }
    
    public int[][] getValidPlays(Piece selectedPiece){
        
        int plays[][] = new int[BOARD_SIZE][BOARD_SIZE];
        int slopes[] = selectedPiece.getRulesSlope();
        
        int x = selectedPiece.x;
        int y = selectedPiece.y;
        
        for(int i = 0; i < slopes.length; i++){
            int currSlope = slopes[i];
            plays = checkSlope(slopes[i], 1, plays, selectedPiece);
            plays = checkSlope(-1, -slopes[i], plays, selectedPiece);
            plays = checkSlope(slopes[i], 1, plays, selectedPiece);
            plays = checkSlope(-1, -slopes[i], plays, selectedPiece);
        }
        for(int[] a : plays){
            for(int b : a){
                System.out.print(b + " ");
            }
            System.out.println();
        }
        return plays;
    }
    
    private int[][] checkSlope(int dx, int dy, int[][] plays, Piece selectedPiece){
            
            int x = selectedPiece.x,
                y = selectedPiece.y;
        
            for(int i = 0; i < selectedPiece.getRadius(); i++){
                
                x += dx;
                y += dy;
                
                if(x > BOARD_SIZE - 1 || y > BOARD_SIZE -1)
                    break;
                
                if(board[y][x].piece != null){
                    boolean notNull = board[y][x].piece != null,
                        diffPlayer = board[y][x].piece.blackPlayer != selectedPiece.blackPlayer;
                
                    if(notNull && diffPlayer)
                        break;
                }
                
                plays[y][x] = 1;
            }        
        
        return plays;
    }
    
}
