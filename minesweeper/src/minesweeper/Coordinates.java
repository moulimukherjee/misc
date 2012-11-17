package minesweeper;

public class Coordinates
{
	private int x;
	private int y;

	public Coordinates(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	@Override
	public boolean equals(Object c)
	{
		if (c instanceof Coordinates)
		{
			return (this.x == ((Coordinates) c).getX() && this.y == ((Coordinates) c).getY());
		}
		else
		{
			return false;
		}
	}

	@Override
	public int hashCode()
	{
		return x;
	}

	@Override
	public String toString()
	{
		return "[" + x + "," + y + "]";
	}
}
