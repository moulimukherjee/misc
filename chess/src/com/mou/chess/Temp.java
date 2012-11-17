package com.mou.chess;

public class Temp {
	
	int x;
	int y;
	int colour;
	
	public Temp(int x, int y, int colour)
	{
		this.x = x;
		this.y = y;
		this.colour = colour;
	}
	
	/**
	 * @return the colour
	 */
	public int getColour() {
		return colour;
	}

	/**
	 * @param colour the colour to set
	 */
	public void setColour(int colour) {
		this.colour = colour;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public String toString()
	{
		return "value: "+this.x+ " "+this.y;
	}

}
