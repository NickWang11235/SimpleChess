/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import simplechess.Piece.Rules;

/**
 *
 * @author nickw
 */
public class GUILauncher extends JFrame implements MouseListener, MouseMotionListener{

    public static final int BLOCK_SIZE = 60;
    private Board board;
    private Graphics g;
    private BoardPanel boardPanel;
    private PiecePanel piecePanel;
    
    public GUILauncher(){
        super("Simple Chess Game");
        initGraphics();
    }
    
    private void initGraphics(){
        
        addMouseListener(this);
        addMouseMotionListener(this);
        
        try {
            board = new Board();
        } catch (IOException ex) {
            Logger.getLogger(GUILauncher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boardPanel = new BoardPanel();
        piecePanel = new PiecePanel();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(BLOCK_SIZE * Board.BOARD_SIZE, BLOCK_SIZE * Board.BOARD_SIZE);
        setLocationRelativeTo(null);
        
        add(boardPanel);
        setGlassPane(piecePanel);
        piecePanel.setVisible(true);
        
        pack();
        setVisible(true);
        
        g = piecePanel.getGraphics();
    }

    private Piece selectedPiece;
    private int initX, initY;
    private int x,y;
    private int selectedX, selectedY;
    
    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX()/BLOCK_SIZE;
        y = e.getY()/BLOCK_SIZE;
        if(selectedPiece == null){
            if(board.getBlockAt(y, x) != null){
                selectedX = x;
                selectedY = y;
                selectedPiece = board.getBlockAt(y, x).getPiece();
            }
        }else{
            if(board.getBlockAt(y, x).getPiece() == null || board.getBlockAt(y, x).getPiece().blackPlayer == selectedPiece.blackPlayer){
                board.getBlockAt(y, x).setPiece(selectedPiece);
                board.clearBlockAt(selectedY, selectedX);
                selectedPiece = null;
            }
        }
        boardPanel.repaint();
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    private class BoardPanel extends JPanel{

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(BLOCK_SIZE*Board.BOARD_SIZE, BLOCK_SIZE*Board.BOARD_SIZE);
        }
        
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            for(int i = 0; i < Board.BOARD_SIZE; i++)
                for(int j = 0; j < Board.BOARD_SIZE; j++){
                    if((i+j)%2 == 1)
                        g.setColor(Color.GRAY);
                    else
                        g.setColor(Color.WHITE);
                    g.fillRect(j*BLOCK_SIZE, i*BLOCK_SIZE , BLOCK_SIZE, BLOCK_SIZE);
                }
            if(selectedPiece != null){
                g.setColor(Color.RED);
                g.fillRect(x*BLOCK_SIZE,y*BLOCK_SIZE,BLOCK_SIZE,BLOCK_SIZE);
                
                if(selectedPiece.firstMove){
                    g.setColor(Color.YELLOW);
                    if(selectedPiece.blackPlayer){
                        g.fillRect(x*BLOCK_SIZE,(y+1)*BLOCK_SIZE,BLOCK_SIZE,BLOCK_SIZE);
                        g.fillRect((x+1)*BLOCK_SIZE,(y+1)*BLOCK_SIZE,BLOCK_SIZE,BLOCK_SIZE);
                        g.fillRect((x-1)*BLOCK_SIZE,(y+1)*BLOCK_SIZE,BLOCK_SIZE,BLOCK_SIZE);
                    }else{
                        g.fillRect(x*BLOCK_SIZE,(y-1)*BLOCK_SIZE,BLOCK_SIZE,BLOCK_SIZE);
                        g.fillRect((x+1)*BLOCK_SIZE,(y-1)*BLOCK_SIZE,BLOCK_SIZE,BLOCK_SIZE);
                        g.fillRect((x-1)*BLOCK_SIZE,(y-1)*BLOCK_SIZE,BLOCK_SIZE,BLOCK_SIZE);
                    }
                
                }else{
                    Rules rules[] = selectedPiece.getRules();
                    try{
                        for(Rules r : rules){
                            for(int i = 0; i < selectedPiece.getRadius(); i++){
                                int dx1 = r.slope, dy1 = 1, dx2 = 1, dy2 = r.slope, dx3 = -r.slope, dy3 = -1, dx4 = -1, dy4 = -r.slope;
                                if(board.getBlockAt(y + dy1, x + dx1).getPiece().blackPlayer != selectedPiece.blackPlayer){
                                    dx1 = 0;
                                    dy1 = 0;
                                }else if(board.getBlockAt(y + dy1, x + dx1).getPiece() != null){
                                    g.setColor(Color.YELLOW);
                                    g.fillRect(i*(x + dx1)*BLOCK_SIZE, i*(y + dy1)*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                                }else if(board.getBlockAt(y + dy1, x + dx1).getPiece().blackPlayer != selectedPiece.blackPlayer){
                                    g.setColor(Color.BLACK);
                                    g.fillRect(i*(x + dx1)*BLOCK_SIZE, i*(y + dy1)*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                                    dx1 = 0;
                                    dy1 = 0;
                                }
                                if(board.getBlockAt(y + dy2, x + dx2).getPiece().blackPlayer != selectedPiece.blackPlayer){
                                    dx2 = 0;
                                    dy2 = 0;
                                }else if(board.getBlockAt(y + dy2, x + dx2).getPiece() != null){
                                    g.setColor(Color.YELLOW);
                                    g.fillRect(i*(x + dx2)*BLOCK_SIZE, i*(y + dy2)*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                                }else if(board.getBlockAt(y + dy2, x + dx2).getPiece().blackPlayer != selectedPiece.blackPlayer){
                                    g.setColor(Color.BLACK);
                                    g.fillRect(i*(x + dx2)*BLOCK_SIZE, i*(y + dy2)*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                                    dx2 = 0;
                                    dy2 = 0;
                                }
                                if(board.getBlockAt(y + dy3, x + dx3).getPiece().blackPlayer != selectedPiece.blackPlayer){
                                    dx3 = 0;
                                    dy3 = 0;
                                }else if(board.getBlockAt(y + dy3, x + dx3).getPiece() != null){
                                    g.setColor(Color.YELLOW);
                                    g.fillRect(i*(x + dx3)*BLOCK_SIZE, i*(y + dy3)*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                                }else if(board.getBlockAt(y + dy3, x + dx3).getPiece().blackPlayer != selectedPiece.blackPlayer){
                                    g.setColor(Color.BLACK);
                                    g.fillRect(i*(x + dx3)*BLOCK_SIZE, i*(y + dy3)*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                                    dx3 = 0;
                                    dy3 = 0;
                                }
                                if(board.getBlockAt(y + dy4, x + dx4).getPiece().blackPlayer != selectedPiece.blackPlayer){
                                    dx4 = 0;
                                    dy4 = 0;
                                }else if(board.getBlockAt(y + dy4, x + dx4).getPiece() != null){
                                    g.setColor(Color.YELLOW);
                                    g.fillRect(i*(x + dx4)*BLOCK_SIZE, i*(y + dy4)*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                                }else if(board.getBlockAt(y + dy4, x + dx4).getPiece().blackPlayer != selectedPiece.blackPlayer){
                                    g.setColor(Color.BLACK);
                                    g.fillRect(i*(x + dx4)*BLOCK_SIZE, i*(y + dy4)*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                                    dx4 = 0;
                                    dy4 = 0;
                                }
                            }
                        }
                    }catch(Exception e){
                    }
                }
            }
        }
    }
    
    private class PiecePanel extends JComponent{
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            for(int i = 0; i < Board.BOARD_SIZE; i++)
                for(int j = 0; j < Board.BOARD_SIZE; j++)
                    if(board.getBlockAt(i, j).getPiece() != null)
                        g.drawImage(board.getBlockAt(i, j).getPiece().img, j*BLOCK_SIZE, i*BLOCK_SIZE, null);
//            if(selectedPiece != null)
//                g.drawImage(selectedPiece.img, x - initX%BLOCK_SIZE, y - initY%BLOCK_SIZE, null);
        }
    }
}

