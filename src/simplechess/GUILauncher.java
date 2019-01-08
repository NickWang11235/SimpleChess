/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechess;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    Panel panel;
    
    public GUILauncher(){
        super("Simple Chess Game");
        addMouseListener(this);
        addMouseMotionListener(this);
        try {
            board = new Board();
        } catch (IOException ex) {
            Logger.getLogger(GUILauncher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        panel = new Panel();
        //Terminates the program when the frame is closed. This 
        //should be adjusted with concurency
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(BLOCK_SIZE * Board.BOARD_SIZE, BLOCK_SIZE * Board.BOARD_SIZE);
        //Center the frame
        setLocationRelativeTo(null);
        //Set the frame visible and displays it
        setVisible(true);
        add(panel);
        //Resize the frame
        pack();
        System.out.println(getWidth() + " " +getHeight());
        g = getGraphics();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    private Piece selectedPiece;
    private int initX, initY;
    
    @Override
    public void mousePressed(MouseEvent e) {
        initX = e.getX();
        initY = e.getY();
        int x = e.getX()/BLOCK_SIZE, y = e.getY()/BLOCK_SIZE;
        if(board.getBlockAt(y, x) != null){
            selectedPiece = board.getBlockAt(y, x).getPiece();
            board.clearBlockAt(y, x);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX()/BLOCK_SIZE, y = e.getY()/BLOCK_SIZE;
        if(board.getBlockAt(y, x) == null){
            board.setBloctAt(y, x, selectedPiece);
            selectedPiece = null;
        }
        g.clearRect(0, 0, getWidth(), getHeight());
        panel.paintComponent(g);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(selectedPiece != null){
//            System.out.println(initX%BLOCK_SIZE + " " +initY%BLOCK_SIZE);
            g.drawImage(selectedPiece.img, e.getX()-initX%BLOCK_SIZE, e.getY()-initY%BLOCK_SIZE + 29, null);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println(e.getX() + " "+ e.getY());
    }

    private class Panel extends JPanel{

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
            for(int i = 0; i < Board.BOARD_SIZE; i++)
                for(int j = 0; j < Board.BOARD_SIZE; j++)
                    if(board.getBlockAt(i, j) != null)
                        g.drawImage(board.getBlockAt(i, j).getPiece().img, j*BLOCK_SIZE, i*BLOCK_SIZE, null);
        }
    }
}
