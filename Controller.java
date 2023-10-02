// Jordan Lyle, 10/14/2022, Controller.java for Assignment 4

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller extends JPanel implements ActionListener, MouseListener, KeyListener {
	boolean keyLeft;
	boolean keyRight;
	boolean keySpace;
	View view;
	Model model;
	boolean editingMode = false;
	int scrollContPos;
	boolean isMoving = false;

	Controller(Model m) 
	{
		model = m;
		scrollContPos = model.mario.mx - 2*model.mario.mw;
	}

	void setView(View v) 
	{
		view = v;
	}

	void setModel(Model m) 
	{
		model = m;
	}

	public void mousePressed(MouseEvent e) 
	{
		if (e.getY() < 100) 
		{
			System.out.println("break here");
		}
		int count = 0;
		if (editingMode == true)
		{
		for (int i = 0; i < model.pipes.size(); i++) 
		{
			Pipe p = model.pipes.get(i);
			if (p.onUserClickLocation(e.getX() + scrollContPos, e.getY()) == 10) 
			{
				//System.out.println("got here");
				count = 1;
				model.pipes.remove(p);
			} 
			else if (p.onUserClickLocation(e.getX() + scrollContPos, e.getY()) == 5) 
			{
				count = 1;
			}
		}
		if (count == 0) 
		{
			Pipe p = new Pipe(e.getX() + scrollContPos, e.getY());
			model.pipes.add(p);
		}
		}
	}

	public void mouseReleased(MouseEvent e) 
	{
	}

	public void mouseEntered(MouseEvent e) 
	{
	}

	public void mouseExited(MouseEvent e) 
	{
	}

	public void mouseClicked(MouseEvent e) 
	{
	}

	public void keyPressed(KeyEvent e) 
	{
		switch (e.getKeyCode()) 
		{
			case KeyEvent.VK_RIGHT:
				keyRight = true;
				break;
			case KeyEvent.VK_LEFT:
				keyLeft = true;
				break;

		}

		if (((e.getKeyCode()) == (KeyEvent.VK_ESCAPE)) || ((e.getKeyCode()) == (KeyEvent.VK_Q))) 
		{
			System.exit(0);
		}

		if (e.getKeyCode() == KeyEvent.VK_S) 
		{
			model.marshal();
		}

		if (e.getKeyCode() == KeyEvent.VK_L) 
		{
			model.unmarshal();
			scrollContPos = model.mario.mx - 2*model.mario.mw;
			view.scrollPos = scrollContPos;
		}

		if (e.getKeyCode() == KeyEvent.VK_E)
		{
			if (editingMode == true)
			{
				System.out.println("Edit Mode Toggled == False");
				editingMode = false;
			}
			else
			{
				System.out.println("Edit Mode Toggled == True");
				editingMode = true;
			}
		}

		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keySpace = true;
		}
	}

	public void keyReleased(KeyEvent e) 
	{
		switch (e.getKeyCode()) 
		{
			case KeyEvent.VK_RIGHT:
			{
				keyRight = false;
				isMoving = false;
				break;
			}   
			case KeyEvent.VK_LEFT:
			{
				keyLeft = false;
				isMoving = false;
				break;
			}
			case KeyEvent.VK_SPACE:
			{
				keySpace = false;
				break;
			}
		}
	}

	public void keyTyped(KeyEvent e) 
	{
	}

	void update() 
	{
		model.mario.setPreviousPosition();
		if (keyRight) 
		{
			scrollContPos = model.mario.mpx - 2*model.mario.mw;
			isMoving = true;
			model.mario.mx += 4;
		}
		if (keyLeft) 
		{
			scrollContPos = model.mario.mpx - 2*model.mario.mw;
			isMoving = true;
			model.mario.mx -= 4;
		}
		if (keySpace && model.mario.numFramesInAir < 5)
		{
			//System.out.print("key space");
			model.mario.vertVelocity += -5;
		}
	}

	public void actionPerformed(ActionEvent e) 
	{
	}
}
