package fr;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

// import java.io.IOException;
// import fr.Main.SnakePart;
 
public class Testing extends JPanel{
 
	private static final long serialVersionUID = 1L;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");
        Testing panel = new Testing();
        frame.setContentPane(panel);
        frame.setSize(13*50, 13*50);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("Nouvelle partie");
        button.setBounds(40,30,200,40);
        frame.add(button);
        JButton button2 = new JButton("Quitter le jeu");
        button2.setBounds(40,30,200,40);
        frame.add(button2);
        frame.setVisible(true);
    }
   
public class MyMouse extends JFrame implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	
	JLabel label;
      MyMouse(){
        addMouseListener(this);
        label = new JLabel();
        label.setBounds(90,80,130,20);
        label.setFont(new Font("Serif", Font.BOLD, 20));
        add(label);
        setSize(250,250);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
      }
      public void mouseClicked(MouseEvent e) {
        label.setText("Clic");
      }
      public void mouseEntered(MouseEvent e) {
        label.setText("Entrer");
      }
      public void mouseExited(MouseEvent e) {
        label.setText("Quitter");
      }
      public void mousePressed(MouseEvent e) {
        label.setText("Appuyer");
      }
      public void mouseReleased(MouseEvent e) {
        label.setText("Relachez");
      }
    }

public Testing() {
	new MyMouse();
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
	}
}