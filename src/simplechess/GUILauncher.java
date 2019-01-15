/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
    private MenuBar menuBar;
    private Menu menuOptions;
    private MenuItem restart;
    
    public GUILauncher(){
        super("Simple Chess Game");
        initGraphics();
    }
    
    private void initGraphics(){
        
        addMouseListener(this);
        addMouseMotionListener(this);
        
        board = new Board();
        boardPanel = new BoardPanel();
        piecePanel = new PiecePanel();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(BLOCK_SIZE * Board.BOARD_SIZE, BLOCK_SIZE * Board.BOARD_SIZE);
        setLocationRelativeTo(null);
        
        add(boardPanel);
        setGlassPane(piecePanel);
        piecePanel.setVisible(true);
        
        menuBar = new MenuBar();
        menuOptions = new Menu("Options");
        restart = new MenuItem("Restart");
        
        
        restart.addActionListener((e) -> {Board.reset(); piecePanel.repaint();});
        
        menuOptions.add(restart);
        menuBar.add(menuOptions);
        
        setMenuBar(menuBar);
        
        pack();
        setVisible(true);
        
        g = piecePanel.getGraphics();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        int x = (e.getX() - 3)/BLOCK_SIZE,
            y = (e.getY() - 46)/BLOCK_SIZE;
        
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
                    if(Board.getSelectedPiece() != null){
                        switch(Board.getSelectedPiece().getValidPlays()[i][j]){
                            case 1:
                                g.setColor(Color.YELLOW);
                                g.fillRect(j*BLOCK_SIZE, i*BLOCK_SIZE , BLOCK_SIZE, BLOCK_SIZE);
                                break;
                            case 2:
                               g.setColor(Color.GREEN);
                                g.fillRect(j*BLOCK_SIZE, i*BLOCK_SIZE , BLOCK_SIZE, BLOCK_SIZE);
                                break;
                        }
                    }
                }
            if(Board.getSelectedPiece() != null){
                g.setColor(Color.RED);
                g.fillRect(Board.getSelectedPiece().getX()*BLOCK_SIZE, Board.getSelectedPiece().getY()*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);                
            }
        }
    }
    
    private class PiecePanel extends JComponent{
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            for(int i = 0; i < Board.BOARD_SIZE; i++)
                for(int j = 0; j < Board.BOARD_SIZE; j++)
                    if(Board.getBlockAt(i, j).getPiece() != null)
                        g.drawImage(Board.getBlockAt(i, j).getPiece().img, j*BLOCK_SIZE, i*BLOCK_SIZE, null);
        }
    }
}

