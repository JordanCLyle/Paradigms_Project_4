// Jordan Lyle, 10/14/2022, Model.java for Assignment 4
import java.util.ArrayList;
import java.util.Iterator;

class Model
{
    ArrayList<Pipe> pipes;
	Mario mario;
	boolean check;
	Json tmpList;

	Model()
	{
		pipes = new ArrayList<Pipe>();
		mario = new Mario(100,100);
		tmpList = Json.newList();
		tmpList = Json.load("map.json");
		for (int i = 0; i < tmpList.size(); i++)
		{
			pipes.add(new Pipe(tmpList.get(i)));
		}
		check = false;
	}

	public void update()
	{
		mario.update();
		for (int i = 0; i < pipes.size(); i++)
		{
			check = areColliding(pipes.get(i));
			if (check == true)
			{
				if ((mario.mpy + mario.mh <= pipes.get(i).y) || (mario.mpy >= pipes.get(i).y + pipes.get(i).h))
				{
					mario.getOutOfPipeY();
				}
				else
				{
					mario.getOutOfPipeX();
				}
				//System.out.println("Colliding!");
			}
		}
	}

	boolean areColliding(Pipe p)
	{
		if(mario.mx + mario.mw < p.x)
		{
			return false;
		}
		if(mario.mx > p.x + p.w)
		{
			return false;
		}
		if(mario.my + mario.mh < p.y) // assumes bigger is downward
		{
			return false;
		}
		if(mario.my > p.y + p.h) // assumes bigger is downward
		{
			return false;
		}
		return true;
	}

	Json marshal()
	{  
		Json ob = Json.newObject(); 
		Json tmpList2 = Json.newList();    
        ob.add("pipes", tmpList2);
		Iterator<Pipe> counter = pipes.iterator();
        while(counter.hasNext())
		{
            tmpList2.add((counter.next()).marshal());
		}
		System.out.println("marshal");
		tmpList2.save("map.json");
        return ob;
	}

	void unmarshal()
	{
		pipes = new ArrayList<Pipe>();
		tmpList = Json.load("map.json");
		for (int i = 0; i < tmpList.size(); i++)
		{
			pipes.add(new Pipe(tmpList.get(i)));
		}
		mario.mx = 100;
		mario.my = 100;
	}
}