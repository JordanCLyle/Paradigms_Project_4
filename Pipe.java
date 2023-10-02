// Jordan Lyle, 10/14/2022, Pipe.java for Assignment 4
import java.awt.image.BufferedImage;
import java.awt.Graphics;

class Pipe
{
	int x;
	int y;
	int w = 55;
	int h = 400;
	static BufferedImage image;

	Pipe(int horizontal,int vertical)
	{
		x = horizontal;
		y = vertical;
		// Lazy Loading (fix this later)
		if (image == null)
		{
			image = View.loadImage("Pipe.png");
		}
	}

	@Override
	public String toString()
	{
		return "Pipe (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h;
	}

	public int onUserClickLocation(int clickX, int clickY)
	{
		{
			if ((x <= clickX) && (x >= clickX - w) && (y <= clickY) && (y >= clickY - h))
			{
				return 10;
			}
			else if (((x <= clickX + w) && (x >= clickX - w)))
			{
				return 5;
			}
			else
			{
				return 2;
			}
		}
	}

	void drawYourself(Graphics g, int scrollPos)
	{
		g.drawImage(image, x - scrollPos, y, null);
	}


	Json marshal()
	{
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
        return ob;
	}

	Pipe(Json ob)
	{
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		if (image == null)
		{
			image = View.loadImage("Pipe.png");
		}
	}
}