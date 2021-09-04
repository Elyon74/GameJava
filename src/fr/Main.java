package fr;

import java.awt.Color;
import java.awt.Graphics; // On importe les librairie necessaire
import java.util.ArrayDeque;
import java.util.Deque;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel{

	private final int WIDTH = 50; // Taille de chaque carrée qui compose le snake
	private Deque<Snakepart> snake = new ArrayDeque<>();
	private int offset = 0;
			
	public static void main(String[] args) 
	{
    JFrame frame = new JFrame("Snake");
    frame.setContentPane(new Main()); // On affiche le contenue de la fonction Main (notre jeux)
	frame.setSize(12*50, 12*50);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	frame.setResizable(false);
	}
	
	public Main() 
	{
		snake.add(new Snakepart(0, 0, 39)); // 39 Correspond a la direction droite en Java // On instance le Snake
		setBackground(Color.black); // Couleur de la fenetre
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				while(true) 
				{
					repaint();
					try {
						Thread.sleep(1000/60); // 60 Fps
					}
					catch (InterruptedException e) 
					{
						e.printStackTrace();
						}
					}
			}
		}).start();
	}
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		offset++;
		if(offset == WIDTH)
		{
			
		}
		for(Snakepart p : snake);
		g.setColor(Color.green); // Couleur du snake
		g.fillRect(p.x * WIDTH, p.y * WIDTH, WIDTH, WIDTH);
	}
	
	class Snakepart // On creer une class qui crée notre snake
	{
		public int x, y, direction;
		public Snakepart(int x, int y, int direction)
		{
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
		
		@Override
		protected Object clone() throws CloneNotSupportedException
		{
			return new Snakepart(x, y, direction);
		}
	}
}