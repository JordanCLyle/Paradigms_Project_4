// Jordan Lyle, 10/14/2022, Mario.java for Assignment 4
import java.awt.image.BufferedImage;
import java.awt.Graphics;

class Mario
{
	int mx;
	int my;
	int mw;
	int mh;
	int mpx;
	int mpy;
	int drawingCounter;
	double vertVelocity;
	int numFramesInAir;
	static BufferedImage[] images;

	Mario(int horizontal,int vertical)
	{
		mx = horizontal;
		my = vertical;
		drawingCounter = 0;
		numFramesInAir = 0;
		mw = 60;
		mh = 95;
		images = new BufferedImage[5];
		for (int i = 0; i < 5; i++)
		{
			if (images[i] == null)
			{
				images[i] = View.loadImage("Mario" + (i+1) + ".png");
			}

		}
	}

	void update()
	{
		numFramesInAir++;
		vertVelocity += 1.2;
		my += vertVelocity;

		if(my > 510)
		{
			vertVelocity = 0;
			my = 510;
			numFramesInAir = 0;
		}
	}

	public void setPreviousPosition()
	{
		mpx = mx;
		mpy = my;
	}

	void drawYourself(Graphics g, int scrollPos, boolean movingRight)
	{
		if (movingRight == true)
		{
			g.drawImage(images[drawingCounter], mx - scrollPos, my, null);
			drawingCounter++;
			if (drawingCounter == 5)
			{
				drawingCounter = 0;
			}
		}
		else
		{
			g.drawImage(images[0], mx - scrollPos, my, null);
		}
	}

	public void getOutOfPipeX()
	{
		mx = mpx;
	}

	public void getOutOfPipeY()
	{
		my = mpy;
		vertVelocity = 0;
		numFramesInAir = 0;
	}

	@Override
	public String toString()
	{
		return "Mario (x,y) = (" + mx + ", " + my + "), width = " + mw + ", height = " + mh;
	}

}