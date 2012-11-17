package com.mou.chess;

import java.io.Serializable;

public class Piece implements Serializable{
	
	/**
	 * 
	 */
	private int role;
	private int colour;
	private Player player;
	private int posX; //should have used Coordinates class
	private int posY;
	private boolean isMoved;
	
	
	public Piece (int role, Player player, int posx, int posy)
	{
		this.role = role;
		this.player = player;
		this.posX = posx;
		this.posY = posy;
		this.colour = player.getColour();
		this.isMoved = false;
	}
	 public Piece(Piece piece)
	 {
		 this.role = piece.role;
		 this.player = piece.player;
		 this.posX = piece.posX;
		 this.posY = piece.posY;
		 this.colour = piece.colour;
		 this.isMoved = piece.isMoved;
	 }
	 
	
	/**
	 * @return the isMoved
	 */
	public boolean isMoved() {
		return isMoved;
	}


	/**
	 * @param isMoved the isMoved to set
	 */
	public void setMoved(boolean isMoved) {
		this.isMoved = isMoved;
	}


	public int getColour() {
		return colour;
	}


	public void setColour(int colour) {
		this.colour = colour;
	}


	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
	public int getRole() {
		return role;
	}


	public void setRole(int role) {
		this.role = role;
	}


	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}
	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}
	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}
	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public String toString()
	{
		return ""+this.role+" "+this.posX+""+this.posY;
	}
	
	public Boolean equals(Piece piece)
	{
		if(this.role==piece.role && this.player==piece.player && this.posX==piece.posX && this.posY==piece.posY && this.isMoved==piece.isMoved)
			return true;
		else
			return false;
	}
	
	
}
