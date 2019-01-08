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
        board[0][0].setPiece(new Piece(Piece.PieceType.ROOK, true,
                "D:\\Java Prrojects\\SimpleChess\\res\\Black\\Rook_Black.png"));
        board[0][1].setPiece(new Piece(Piece.PieceType.KNIGHT, true,
                "D:\\Java Prrojects\\SimpleChess\\res\\Black\\Knight_Black.png"));
        board[0][2].setPiece(new Piece(Piece.PieceType.BISHOP, true,
                "D:\\Java Prrojects\\SimpleChess\\res\\Black\\Bishop_Black.png"));
        board[0][3].setPiece(new Piece(Piece.PieceType.QUEEN, true,
                "D:\\Java Prrojects\\SimpleChess\\res\\Black\\Queen_Black.png"));
        board[0][4].setPiece(new Piece(Piece.PieceType.KING, true,
                "D:\\Java Prrojects\\SimpleChess\\res\\Black\\King_Black.png"));
        board[0][5].setPiece(new Piece(Piece.PieceType.BISHOP, true,
                "D:\\Java Prrojects\\SimpleChess\\res\\Black\\Bishop_Black.png"));
        board[0][6].setPiece(new Piece(Piece.PieceType.KNIGHT, true,
                "D:\\Java Prrojects\\SimpleChess\\res\\Black\\Knight_Black.png"));
        board[0][7].setPiece(new Piece(Piece.PieceType.ROOK, true,
                "D:\\Java Prrojects\\SimpleChess\\res\\Black\\Rook_Black.png"));
        
        //Init black player pieces
        board[7][0].setPiece(new Piece(Piece.PieceType.ROOK, false,
                "D:\\Java Prrojects\\SimpleChess\\res\\White\\Rook_White.png"));
        board[7][1].setPiece(new Piece(Piece.PieceType.KNIGHT, false,
                "D:\\Java Prrojects\\SimpleChess\\res\\White\\Knight_White.png"));
        board[7][2].setPiece(new Piece(Piece.PieceType.BISHOP, false,
                "D:\\Java Prrojects\\SimpleChess\\res\\White\\Bishop_White.png"));
        board[7][3].setPiece(new Piece(Piece.PieceType.QUEEN, false,
                "D:\\Java Prrojects\\SimpleChess\\res\\White\\Queen_White.png"));
        board[7][4].setPiece(new Piece(Piece.PieceType.KING, false,
                "D:\\Java Prrojects\\SimpleChess\\res\\White\\King_White.png"));
        board[7][5].setPiece(new Piece(Piece.PieceType.BISHOP, false,
                "D:\\Java Prrojects\\SimpleChess\\res\\White\\Bishop_White.png"));
        board[7][6].setPiece(new Piece(Piece.PieceType.KNIGHT, false,
                "D:\\Java Prrojects\\SimpleChess\\res\\White\\Knight_White.png"));
        board[7][7].setPiece(new Piece(Piece.PieceType.ROOK, false,
                "D:\\Java Prrojects\\SimpleChess\\res\\White\\Rook_White.png"));
        
        //Init pawns for both players
        for(int i = 0; i < BOARD_SIZE; i++){
            board[1][i].setPiece(new Piece(Piece.PieceType.PAWN, true,
                "D:\\Java Prrojects\\SimpleChess\\res\\Black\\Pawn_Black.png"));
            board[6][i].setPiece(new Piece(Piece.PieceType.PAWN, false,
                "D:\\Java Prrojects\\SimpleChess\\res\\White\\Pawn_White.png"));
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
    
}
