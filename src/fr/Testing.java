package fr;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import fr.Main.SnakePart;;
 
public class Testing extends JPanel{
 
	private static final long serialVersionUID = 1L;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Testing");
        Testing panel = new Testing();
        
        frame.setContentPane(panel);
        frame.setSize(13*50, 13*50);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
 
    public Testing() {
        setBackground(Color.BLACK);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    repaint();
                    try {
                        Thread.sleep(1000/60l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Comic Sans MS", 20, 30));
        g.drawString("Score : "+(SnakePart.size() -0), 10, 40);
        
    }
}