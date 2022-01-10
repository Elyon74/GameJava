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

import fr.Testing.MyMouse;

// import java.io.IOException;
// import javax.swing.JButton;
// import javax.swing.JLabel;

public class Main extends JPanel{
 
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 50;
    private Deque<SnakePart> snake = new ArrayDeque<>();
    private Point apple = new Point(0,0);
    private Point apple2 = new Point(0,0);
    private Random rand = new Random();
    
    private boolean isGrowing = false;
    private boolean gameLost = false;
    private boolean gameWin = false;
    
    private int offset = 0;
    private int newDirection = 39;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");
        Main panel = new Main();
        frame.addKeyListener(new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                panel.onKeyPressed(e.getKeyCode());
            }
        });
        
        frame.setContentPane(panel);
        frame.setSize(13*50, 13*50);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
 
    public Main() {
        createApple();
        createApple2();
        new Score();
        snake.add(new SnakePart(2, 2, 39));
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
    
    public void createApple() {
        boolean positionAvailable;
        do {
            apple.x = rand.nextInt(12);
            apple.y = rand.nextInt(12);
            positionAvailable = true;
            for(SnakePart p : snake) {
                if(p.x == apple.x && p.y == apple.y) {
                    positionAvailable = false;
                    break;
                }
            }
        } while(!positionAvailable);
    }
    
    public void createApple2() {
    	boolean positionAvailable2;
    	do {
    		apple2.x = rand.nextInt(12);
    		apple2.y = rand.nextInt(12);
    		positionAvailable2 = true;
    		for(SnakePart p : snake) {
    			if(p.x == apple2.x && p.y == apple2.y) {
    				positionAvailable2 = false;
    				break;
    			}
    		}
    	} while (!positionAvailable2);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(gameLost) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Comic Sans MS", 45, 45));
            g.drawString("Perdu !", 13*50/2 - g.getFontMetrics().stringWidth("Perdu !")/2, 13*50/2);
            return;
        }
        
        if(gameWin) {
        	g.setColor(Color.YELLOW);
        	g.setFont(new Font("Comic Sans MS", 45, 45));
        	g.drawString("Vous avez gagnez !", 13*50/2 - g.getFontMetrics().stringWidth("Vous avez gagnez !")/2, 13*50/2);
        	return;
        }
        
        offset += 5;
        SnakePart head = null;
        if(offset == WIDTH) {
            offset = 0;
            try {
                head = (SnakePart) snake.getFirst().clone();
                head.move();
                head.direction = newDirection;
                snake.addFirst(head);
                if(head.x == apple.x && head.y == apple.y) {
                    isGrowing = true;
                    createApple();
                }
                if(head.x == apple2.x && head.y == apple2.y) {
                	isGrowing = true;
                	createApple2();
                }
                if(!isGrowing)
                    snake.pollLast();
                else
                    isGrowing = false;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
        }
        }
        
        g.setColor(Color.green);
        g.fillOval(apple.x*WIDTH + WIDTH/4, apple.y*WIDTH + WIDTH/4, WIDTH/2, WIDTH/2);
        
        g.setColor(Color.red);
        g.fillOval(apple2.x*WIDTH + WIDTH/4, apple2.y*WIDTH + WIDTH/4, WIDTH/2, WIDTH/2);
        
        g.setColor(Color.green);
        for(SnakePart p : snake) {
            if(offset == 0) {
                if(p != head) {
                    if(p.x == head.x && p.y == head.y) {
                        gameLost = true;
                    }
                }
            }
            if(p.direction == 37 || p.direction == 39) {
                g.fillRect(p.x * WIDTH + ((p.direction == 37) ? -offset : offset), p.y*WIDTH, WIDTH, WIDTH);
            } else {
                g.fillRect(p.x * WIDTH, p.y*WIDTH + ((p.direction == 38) ? -offset : offset), WIDTH, WIDTH);
            }
        }
        if(snake.size() == 11) {
        	gameWin = true;
        }
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Comic Sans MS", 20, 30));
        g.drawString("Score : "+(snake.size() -1), 10, 40);
        
    }
    
    public void onKeyPressed(int keyCode) {
        if(keyCode >= 37 && keyCode <= 40) {
            if(Math.abs(keyCode - newDirection) != 2) {
                newDirection = keyCode;
            }
        }
    }
    
    public void onKeyPressed1(KeyEvent key) {
        int codeDeLaTouche = key.getKeyCode();
        switch (codeDeLaTouche)
        {
    	case KeyEvent.VK_Z:
    		if(Math.abs(codeDeLaTouche - newDirection) != 2) {
                newDirection = key.getKeyCode();
                }
    		break;
        case KeyEvent.VK_S:
    		if(Math.abs(codeDeLaTouche - newDirection) != 2) {
                newDirection = key.getKeyCode();
                }
    		break;
        case KeyEvent.VK_Q:
    		if(Math.abs(codeDeLaTouche - newDirection) != 2) {
                newDirection = key.getKeyCode();
                }
    		break;
        case KeyEvent.VK_D:
    		if(Math.abs(codeDeLaTouche - newDirection) != 2) {
                newDirection = key.getKeyCode();
                }
    		break;
        }
    }
    
    class SnakePart {
        
        public int x, y, direction;
        
        public SnakePart(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
        
        public void move() {
            if(direction == 37 || direction == 39) {
                x += (direction == 37) ? -1 : 1;
                if(x > 13)
                    x = -1;
                else if(x < -1)
                    x = 13;
            }else {
                y += (direction == 38) ? -1 : 1;
                if(y > 13)
                    y = -1;
                else if(y < -1)
                    y = 13;
            }
        }
        
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new SnakePart(x, y, direction);
        }

		public static int size() {
			return 0;
		}
    }
public class Score extends JFrame{
    	
    	private static final long serialVersionUID = 1L;
    	JFrame frame = new JFrame("Score");
    	JLabel label;
    	Score(){
    		label = new JLabel();
    		label.setBackground(Color.BLACK);
            label.setBounds(90,80,130,20);
            label.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            add(label);
            setSize(250,250);
            setLayout(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            setVisible(true);
          }
    	}
}