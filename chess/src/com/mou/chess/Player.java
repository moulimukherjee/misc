package com.mou.chess;

import java.io.Serializable;

public class Player implements Serializable {
	
	public static final int WHITE = 0;
	public static final int BLACK = 1;
	
	private int colour;
	private String name;
	
	public Player(int colour)
	{
		this.colour = colour;
	}

	public int getColour() {
		return colour;
	}

	public void setColour(int colour) {
		this.colour = colour;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean equals(Player player)
	{
		return (player.getColour()==this.getColour());
	}
	
	public String toString()
	{
		if(this.colour == Player.WHITE)
			return "WHITE";
		else
			return "BLACK";
	}
	
	
}
