package com.mou.chess;

//should have used this class to note the position everywhere
public class Coordinates {
	
	private int x;
	private int y;
	
	public Coordinates(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	public Boolean equals(Coordinates coord)
	{
		if (this.getX() == coord.getX() && this.getY()==coord.getY())
			return true;
		else
			return false;
	}
	
	public static String getCellNotation(int x, int y)
	{
		//returns the standard algebraic notation of a chess square
		int a = y + 65;
		char b = (char) a;
		return ""+b+""+(8-x);
	}
	
	public static String getCellNotation(Coordinates coord)
	{
		//returns the standard algebraic notation of a chess square
		int a = coord.getY() + 65;
		char b = (char) a;
		return ""+b+""+(8-coord.getX());
	}
	
	public static Coordinates getInternalCellNotation(String str)
	{
		//returns the internal numbering of the cells
		char a = str.charAt(0);
		int b = ((int)a - 65);
		int x = Integer.parseInt(str.substring(1));
		return new Coordinates(x, b);
	}
	
	public String toString()
	{
		return "x: "+this.x+" y: "+this.y;
	}
}
