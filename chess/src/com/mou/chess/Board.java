package com.mou.chess;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Board extends JFrame {
	
	private MyTopPanel topPanel;
	private MyPanel mainPanel;
	private Cell[][] cells;
	private static Game game;
	
	public static final Board INSTANCE = new Board("Chess Game");
	
	public static final int CHECKMATE = 1;
	public static final int STALEMATE = 2;
	public static final int SURRENDER = 3;
	public static final int ONGOING = 0;
	
	
	/**
	 * @return the game
	 */
	public static Game getGame() {
		return game;
	}

	private Board(String title)
	{
		super(title);
		setLayout(new FlowLayout());
		//GridBagConstraints c = new GridBagConstraints();
		game = new Game();
		
		topPanel = new MyTopPanel();
		add(topPanel);
		
		mainPanel = new MyPanel();
		add(mainPanel);
		
		initializeBoard();
		initPieces();
		game.updateHistory();
		
		setPreferredSize(new Dimension(550, 625));
		setMaximumSize(getPreferredSize()); // prevent growth
		setMinimumSize(getPreferredSize()); // prevent shrink
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void initializeBoard()
	{
		cells = new Cell[8][8];
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				cells[i][j] = new Cell(i,j);
				mainPanel.add(cells[i][j]);
			}
		}
		
	}
	
	public void initPieces()
	{
		setActivePieces();	
	}
	
	public void setActivePieces()
	{
		for(Piece piece: game.getActivePieces())
		{
			int x = piece.getPosX();
			int y = piece.getPosY();
			cells[x][y].setPiece(piece);
		}
	}
	
	public static void movePiece(int x1, int y1, int x2, int y2)
	{
		Cell cell1 = INSTANCE.cells[x1][y1];
		Cell cell2 = INSTANCE.cells[x2][y2];
		
		boolean isValid;
		
		if(!cell2.getIsOccupied())
		{
			isValid = Role.isValidMove(cell1.getPiece().getRole(), 0, cell1.getPosX(), cell1.getPosY(), cell2.getPosX(), cell2.getPosY(), cell1.getPiece().getColour());
			
			if(isValid)
			{
				simpleMovePiece(cell1, cell2);
				//update who has to move next
				game.setNextMove(Board.getGame().getOtherPlayer(game.getNextMove()));
				showStatus("Piece moved. Next move of: "+Board.getGame().getNextMove());
				game.updateHistory();
				INSTANCE.topPanel.getUndoButton().setEnabled(true);
				
			}
			else
			{
				Cell.setIsClicked(false);
				showStatus("Invalid move. Next move of: "+game.getNextMove());
			}
				
			
		}
		else
		{
			isValid = Role.isValidMove(cell1.getPiece().getRole(), cell2.getPiece().getRole(), cell1.getPosX(), cell1.getPosY(), cell2.getPosX(), cell2.getPosY(), cell1.getPiece().getColour());
			
			if(isValid)
			{
				capturePiece(cell2);
				simpleMovePiece(cell1, cell2);
				//update who has to move next
				game.setNextMove(Board.getGame().getOtherPlayer(game.getNextMove()));
				showStatus("Piece moved. Next move of: "+Board.getGame().getNextMove());
				game.updateHistory();
				INSTANCE.topPanel.getUndoButton().setEnabled(true);
			}
			else
			{
				Cell.setIsClicked(false);
				showStatus("Invalid move. Next move of: "+game.getNextMove());
			}
				
		}
		int finished = checkGameCompletion();
		switch(finished)
		{
		case ONGOING:
			break;
		case CHECKMATE:
			break;
		case STALEMATE:
			break;
		case SURRENDER:
			break;
		default:
				
		}
		
	}
	public static void capturePiece(int x, int y)
	{
		Cell cell = INSTANCE.cells[x][y];
		game.removePiece(cell.getPiece());
		cell.freeCell();
		
	}
	public static void capturePiece(Cell cell)
	{
		game.removePiece(cell.getPiece());
		cell.freeCell();
		
	}
	public static void simpleMovePiece(Cell cell1, Cell cell2)
	{
		
		//move piece to next cell
		cell2.setPiece(cell1.getPiece());
		//to note that piece has been moved at least once
		cell2.getPiece().setMoved(true);
		//free previous cell
		cell1.freeCell();
	}
	
	public static void simpleMovePiece(int x1, int y1, int x2, int y2, String msg)
	{
		Cell cell1 = INSTANCE.cells[x1][y1];
		Cell cell2 = INSTANCE.cells[x2][y2];
		
		simpleMovePiece(cell1, cell2);
		showStatus(msg);
	}
	
	public static void showStatus(String msg)
	{
		INSTANCE.topPanel.setTxtField(msg);
	}
	
	
	public void startNewGame(Game game)
	{
		if(game != null)
		{
			this.game = game;
		}
		else
		{
			this.game = new Game();
		}
		mainPanel.removeAll();	
		initializeBoard();
		initPieces();
		showStatus("Next move of: "+this.game.getNextMove());
	}
	
	public static int checkGameCompletion()
	{
		return 0;
	}
	
	public Cell getCell(int x, int y)
	{
		return cells[x][y];
	}
	
	//performs deep copy of each object in the array
	public static ArrayList<Piece> copyList(ArrayList<Piece> two)
	{
		ArrayList<Piece> one = new ArrayList<Piece>(two.size());
		for(Piece piece : two) {
			Piece temp = new Piece(piece);
		    one.add(temp);
		    
		}
		return one;
	}
}
