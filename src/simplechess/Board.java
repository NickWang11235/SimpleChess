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
        
    }
    
    private Block board[][] = new Block[BOARD_SIZE][BOARD_SIZE];
    
    public Board() throws IOException{
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                board[i][j] = null;
            }
        }
        initPieces();
    }
    
    private void initPieces() throws IOException{
        
        //Init white player pieces
        board[0][0] = new Block(new Piece(Piece.PieceType.ROOK, true,
                "C:\\Users\\nickw\\Documents\\NetBeansProjects\\SimpleChess\\res\\Black\\Rook_Black.png"));
        board[0][1] = new Block(new Piece(Piece.PieceType.KNIGHT, true,
                "C:\\Users\\nickw\\Documents\\NetBeansProjects\\SimpleChess\\res\\Black\\Knight_Black.png"));
        board[0][2] = new Block(new Piece(Piece.PieceType.BISHOP, true,
                "C:\\Users\\nickw\\Documents\\NetBeansProjects\\SimpleChess\\res\\Black\\Bishop_Black.png"));
        board[0][3] = new Block(new Piece(Piece.PieceType.QUEEN, true,
                "C:\\Users\\nickw\\Documents\\NetBeansProjects\\SimpleChess\\res\\Black\\Queen_Black.png"));
        board[0][4] = new Block(new Piece(Piece.PieceType.KING, true,
                "C:\\Users\\nickw\\Documents\\NetBeansProjects\\SimpleChess\\res\\Black\\King_Black.png"));
        board[0][5] = new Block(new Piece(Piece.PieceType.BISHOP, true,
                "C:\\Users\\nickw\\Documents\\NetBeansProjects\\SimpleChess\\res\\Black\\Bishop_Black.png"));
        board[0][6] = new Block(new Piece(Piece.PieceType.KNIGHT, true,
                "C:\\Users\\nickw\\Documents\\NetBeansProjects\\SimpleChess\\res\\Black\\Knight_Black.png"));
        board[0][7] = new Block(new Piece(Piece.PieceType.ROOK, true,
                "C:\\Users\\nickw\\Documents\\NetBeansProjects\\SimpleChess\\res\\Black\\Rook_Black.png"));
        
        //Init black player pieces
        board[7][0] = new Block(new Piece(Piece.PieceType.ROOK, false,
                "C:\\Users\\nickw\\Documents\\NetBeansProjects\\SimpleChess\\res\\White\\Rook_White.png"));
        board[7][1] = new Block(new Piece(Piece.PieceType.KNIGHT, false,
                "C:\\Users\\nickw\\Documents\\NetBeansProjects\\SimpleChess\\res\\White\\Knight_White.png"));
        board[7][2] = new Block(new Piece(Piece.PieceType.BISHOP, false,
                "C:\\Users\\nickw\\Documents\\NetBeansProjects\\SimpleChess\\res\\White\\Bishop_White.png"));
        board[7][3] = new Block(new Piece(Piece.PieceType.QUEEN, false,
                "C:\\Users\\nickw\\Documents\\NetBeansProjects\\SimpleChess\\res\\White\\Queen_White.png"));
        board[7][4] = new Block(new Piece(Piece.PieceType.KING, false,
                "C:\\Users\\nickw\\Documents\\NetBeansProjects\\SimpleChess\\res\\White\\King_White.png"));
        board[7][5] = new Block(new Piece(Piece.PieceType.BISHOP, false,
                "C:\\Users\\nickw\\Documents\\NetBeansProjects\\SimpleChess\\res\\White\\Bishop_White.png"));
        board[7][6] = new Block(new Piece(Piece.PieceType.KNIGHT, false,
                "C:\\Users\\nickw\\Documents\\NetBeansProjects\\SimpleChess\\res\\White\\Knight_White.png"));
        board[7][7] = new Block(new Piece(Piece.PieceType.ROOK, false,
                "C:\\Users\\nickw\\Documents\\NetBeansProjects\\SimpleChess\\res\\White\\Rook_White.png"));
        
        //Init pawns for both players
        for(int i = 0; i < BOARD_SIZE; i++){
            board[1][i] = new Block(new Piece(Piece.PieceType.PAWN, true,
                "C:\\Users\\nickw\\Documents\\NetBeansProjects\\SimpleChess\\res\\Black\\Pawn_Black.png"));
            board[6][i] = new Block(new Piece(Piece.PieceType.PAWN, false,
                "C:\\Users\\nickw\\Documents\\NetBeansProjects\\SimpleChess\\res\\White\\Pawn_White.png"));
        }
        
    }

    public void clearBlockAt(int row, int col) {
        board[row][col] = null;
    }
    
    public Block getBlockAt(int row, int col){
        return board[row][col];
    }
    
    public void setBloctAt(int row, int col, Piece piece){
        board[row][col] = new Block(piece);
    }
    
}
