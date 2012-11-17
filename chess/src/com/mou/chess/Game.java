package com.mou.chess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable{
	
	private ArrayList activePieces; //maintained to save state of the board
	private List<Player> players;
	private Player nextMove;
	private List<ArrayList<Piece>> history;
		
	public Game()
	{
		activePieces = new ArrayList<Piece>();
		players = new ArrayList<Player>();
		history = new ArrayList<ArrayList<Piece>>(); 
		reset();
	}
	
	public void reset()
	{
		
		Player white = new Player(Player.WHITE);
		Player black = new Player(Player.BLACK);
		players.add(white);
		players.add(black);
		
		nextMove = white;
		
		activePieces.add(new Piece(Role.ROOK, black, 0, 0));
		activePieces.add(new Piece(Role.ROOK, black, 0, 7));
		activePieces.add(new Piece(Role.ROOK, white, 7, 0));
		activePieces.add(new Piece(Role.ROOK, white, 7, 7));
		
		activePieces.add(new Piece(Role.KNIGHT, black, 0, 1));
		activePieces.add(new Piece(Role.KNIGHT, black, 0, 6));
		activePieces.add(new Piece(Role.KNIGHT, white, 7, 1));
		activePieces.add(new Piece(Role.KNIGHT, white, 7, 6));
		
		activePieces.add(new Piece(Role.BISHOP, black, 0, 2));
		activePieces.add(new Piece(Role.BISHOP, black, 0, 5));
		activePieces.add(new Piece(Role.BISHOP, white, 7, 2));
		activePieces.add(new Piece(Role.BISHOP, white, 7, 5));
		
		activePieces.add(new Piece(Role.QUEEN, black, 0, 3));
		activePieces.add(new Piece(Role.QUEEN, white, 7, 3));
		
		activePieces.add(new Piece(Role.KING, black, 0, 4));
		activePieces.add(new Piece(Role.KING, white, 7, 4));
		
		for(int i=1; i<=8; i++)
		{
			activePieces.add(new Piece(Role.PAWN, black, 1, i-1));
			activePieces.add(new Piece(Role.PAWN, white, 6, i-1));
		}
		
	}
	
	public Player getOtherPlayer(Player player)
	{
		for(Player play: this.players)
		{
			if(!player.equals(play))
			{
				return play;
			}
		}
		return null;
	}
	/**
	 * @return the nextMove
	 */
	public Player getNextMove() {
		return nextMove;
	}

	/**
	 * @param nextMove the nextMove to set
	 */
	public void setNextMove(Player nextMove) {
		this.nextMove = nextMove;
	}

	public ArrayList<Piece> getActivePieces() {
		return activePieces;
	}

	public void setActivePieces(ArrayList<Piece> activePieces) {
		this.activePieces = activePieces;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	public void addPiece(Piece piece)
	{
		activePieces.add(piece);
	}
	public void removePiece(Piece piece)
	{
		activePieces.remove(piece);
	}
	
	public void updateHistory()
	{
		ArrayList<Piece> temp = Board.copyList(activePieces);
		history.add(temp);
	}
	public void removeLastHistory()
	{
		
		{
			history.remove(history.size()-1);
			
			if(history.size()>=1)
			{
				ArrayList<Piece> temp = Board.copyList(history.get(history.size()-1));
				activePieces = temp;
				setNextMove(getOtherPlayer(getNextMove()));
			}
			else
				System.out.println("error");
		}
		
	}
	
	public int getHistorySize()
	{
		return history.size();
	}
	
}
