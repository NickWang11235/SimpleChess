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

    @Override
    public void mouseClicked(MouseEvent e) {
        
        int x = (e.getX() - 3)/BLOCK_SIZE,
            y = (e.getY() - 26)/BLOCK_SIZE;
        board.action(y, x);
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
                    if(board.getSelectedPiece() != null){
                        switch(board.getValidPlays(board.getSelectedPiece())[i][j]){
                            case 1:
                                g.setColor(Color.YELLOW);
                                g.fillRect(j*BLOCK_SIZE, i*BLOCK_SIZE , BLOCK_SIZE, BLOCK_SIZE);
                            case 2:
                               g.setColor(Color.BLACK);
                                g.fillRect(j*BLOCK_SIZE, i*BLOCK_SIZE , BLOCK_SIZE, BLOCK_SIZE);
                        }
                    }
                }
            if(board.getSelectedPiece() != null){
                g.setColor(Color.RED);
                g.fillRect(board.getSelectedPiece().x*BLOCK_SIZE, board.getSelectedPiece().y*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);                
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

