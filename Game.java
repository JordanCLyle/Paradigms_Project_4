// Jordan Lyle, 10/14/2022, Game.java file for Assignment 4

import javax.swing.JFrame;
import java.awt.Toolkit;
//CLEAN UP CODE _ REMOVE EVERYTHING NOT NEEDED
public class Game extends JFrame 
{
	Model model;
	View view;
	Controller controller;

	public Game() 
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		this.setTitle("A4 - Side Scroller");
		this.setSize(500, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		view.addMouseListener(controller);
		this.addKeyListener(controller);
		controller.setModel(model);
	}

	public void run() 
	{
		while (true) {
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen

			// Go to sleep for 50 milliseconds
			try {
				Thread.sleep(40);
			} catch (Exception e) {
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}
	}

	public static void main(String[] args) 
	{
		Game g = new Game();
		g.run();
	}
}
