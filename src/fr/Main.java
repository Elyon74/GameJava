package fr;

import java.awt.Color;
import java.awt.Graphics; // On importe les librairie necessaire
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel{

	private final int WIDTH = 50; // Taille de chaque carrée qui compose le snake
	
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
		g.setColor(Color.green); // Couleur du snake
		g.fillRect(0, 0, WIDTH, WIDTH);
	}
	
	class Snakepart
	{
		public int x, y, direction;
		public Snakepart(int x, int y, int direction)
		{
			
		}
	}
}