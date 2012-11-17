package com.mou.chess;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Cell extends JButton {
	
	private Piece piece;
	private Boolean isOccupied;
	private final int posX; //should have used Coordinates class
	private final int posY;
	private static Boolean isClicked; //variable to check the status if clicking first or second time
	private static Temp temp; //to store value of cell coordinates temporarily for moving pieces
	
	public Cell(int posx, int posy)
	{
		this.posX = posx;
		this.posY = posy;
		setIsOccupied(false);
		setIsClicked(false);
		setBackGroundColour();
		addMouseListener(new MyMouseAdaper());
	}
	
	public void setBackGroundColour()
	{
		if((posX+posY)%2 == 0)
		{
			setBackground(Color.WHITE);
		}
		else
		{
			setBackground(Color.GRAY);
		}
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public static Boolean getIsClicked() {
		return isClicked;
	}

	public static void setIsClicked(Boolean isClicked) {
		Cell.isClicked = isClicked;
	}

	public Piece getPiece() {
		if(this.piece !=null)
			return piece;
		else
			return null;
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
		getPiece().setPosX(this.posX);
		getPiece().setPosY(this.posY);
		setIsOccupied(true);
		setPieceImage();
	}
	public Boolean getIsOccupied() {
		return isOccupied;
	}
	public void setIsOccupied(Boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	
	public void setPieceImage()
	{
		switch(getPiece().getRole())
		{
		case Role.BISHOP:
			if(getPiece().getColour()== Player.BLACK)
			{
				setIcon(new ImageIcon("images/black_bishop.png"));
			}
			else
			{
				setIcon(new ImageIcon("images/white_bishop.png"));
			}
			break;
		case Role.KING:
			if(getPiece().getColour()== Player.BLACK)
			{
				setIcon(new ImageIcon("images/black_king.png"));
			}
			else
			{
				setIcon(new ImageIcon("images/white_king.png"));
			}
			
			break;
		case Role.KNIGHT:
			if(getPiece().getColour()== Player.BLACK)
			{
				setIcon(new ImageIcon("images/black_knight.png"));
			}
			else
			{
				setIcon(new ImageIcon("images/white_knight.png"));
			}
			
			break;
		case Role.PAWN:
			if(getPiece().getColour()== Player.BLACK)
			{
				setIcon(new ImageIcon("images/black_pawn.png"));
			}
			else
			{
				setIcon(new ImageIcon("images/white_pawn.png"));
			}
			
			break;
		case Role.QUEEN:
			if(getPiece().getColour()== Player.BLACK)
			{
				setIcon(new ImageIcon("images/black_queen.png"));
			}
			else
			{
				setIcon(new ImageIcon("images/white_queen.png"));
			}
			
			break;
		case Role.ROOK:
			if(getPiece().getColour()== Player.BLACK)
			{
				setIcon(new ImageIcon("images/black_rook.png"));
			}
			else
			{
				setIcon(new ImageIcon("images/white_rook.png"));
			}
			
			break;
		}
	}
	
	public void freeCell()
	{
		piece = null;
		setIsOccupied(false);
		setIcon(null);
	}
	
	public String toString()
	{
		if(this.piece!=null)
			return "position: "+this.posX+" "+this.posY+" piece is: "+this.piece+" whose position: "+this.piece.getPosX()+" "+this.piece.getPosY();
		else
			return "position: "+this.posX+" "+this.posY+" no piece";
	}
	
	class MyMouseAdaper extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(!getIsClicked()) //checking if clicking the first time or second
			{
				if(getIsOccupied()) //if clicking on an empty box, no selection is made
				{
					//to check that players take 1 turn each
					if(Board.getGame().getNextMove().equals(getPiece().getPlayer()))
					{
						setIsClicked(true);
						Cell.temp = new Temp(getPosX(), getPosY(), getPiece().getColour());
						Board.showStatus("Selected: "+Coordinates.getCellNotation(getPosX(), getPosY()));
					}
					
				}
				
			}
			else
			{
				//if user clicks on the same piece twice then it should be unselected
				if(temp.getX()==getPosX() && temp.getY() == getPosY())
				{
					setIsClicked(false);
					Cell.temp = null;
					Board.showStatus("No piece selected. Next move of: "+Board.getGame().getNextMove());
				}
				//if user clicks on another piece of same colour, then the second piece is selected for move
				else if (getPiece()!= null && temp.getColour() == getPiece().getColour())
				{
					setIsClicked(true);
					Cell.temp = new Temp(getPosX(), getPosY(), getPiece().getColour());
					Board.showStatus("Selected: "+Coordinates.getCellNotation(getPosX(), getPosY()));
				}
				else
				{
					setIsClicked(false);
					//moves the piece
					Board.movePiece(temp.getX(), temp.getY(), getPosX(), getPosY());
				}
				
			}
			
		}
	}
	

	
}
