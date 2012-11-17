package minesweeper;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MineSweeper extends JFrame
{

	private final int ROWS;
	private final int COLUMNS;
	private final int MINES;

	private int nonMineSquares;

	private final Set<Coordinates> minePositions;

	private Square[][] squares;

	private JPanel panel;

	public MineSweeper(int rows, int columns, int mines)
	{
		ROWS = rows;
		COLUMNS = columns;
		MINES = mines;

		nonMineSquares = (ROWS * COLUMNS) - MINES;

		minePositions = new HashSet<Coordinates>(MINES);

		squares = new Square[ROWS][COLUMNS];

		panel = new JPanel();
		this.add(panel);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		init();
	}

	public static void main(String argsp[])
	{
		MineSweeper ms = new MineSweeper(9, 9, 10);
		ms.setSize(400, 400);
		ms.setVisible(true);

	}

	public void init()
	{
		initializeMines();
		initializeUI();
	}

	private void initializeMines()
	{
		while (minePositions.size() < MINES)
		{
			Coordinates mine = new Coordinates(new Long(Math.abs(Math.round(Math.random() * (ROWS - 1)))).intValue(), new Long(
				Math.abs(Math.round(Math.random() * (COLUMNS - 1)))).intValue());

			minePositions.add(mine);
		}
		System.out.println(minePositions);
	}

	private void initializeUI()
	{
		panel.setLayout(new GridLayout(ROWS, COLUMNS));

		for (int row = 0; row < ROWS; row++)
		{
			for (int column = 0; column < COLUMNS; column++)
			{
				// Setting the mine
				if (minePositions.contains(new Coordinates(row, column)))
				{
					squares[row][column] = new Square(Square.SquareType.MINE, -1);
				}
				else
				{
					int noOfMines = 0;
					Set<Coordinates> surroundingCoordinates = this.getSurroundingCoordinates(row, column);
					for (Coordinates c : surroundingCoordinates)
					{
						if (minePositions.contains(c))
						{
							noOfMines++;
						}
					}

					if (noOfMines > 0)
					{
						squares[row][column] = new Square(Square.SquareType.NUMBER, noOfMines);
					}
					else
					{
						squares[row][column] = new Square(Square.SquareType.BLANK, 0);
					}

				}

				squares[row][column].setState(Square.State.CLOSED);
				squares[row][column].addMouseListener(new ClickHandler(row, column));
				// Adding to panel
				panel.add(squares[row][column]);
			}
		}

	}

	private void openAllMines()
	{
		for (Coordinates c : minePositions)
		{
			openSquare(c.getX(), c.getY());
		}
	}

	private void openSquare(int row, int col)
	{
		squares[row][col].setState(Square.State.OPEN);
		if (squares[row][col].getType() != Square.SquareType.MINE)
		{
			nonMineSquares--;
		}
	}

	private void checkWinningCondition()
	{
		if (nonMineSquares == 0)
		{
			JOptionPane.showMessageDialog(this, "Game Won!");
		}
	}

	private void checkAndOpenSquare(int x, int y)
	{
		switch (squares[x][y].getType())
		{
			case MINE:
				openAllMines();
				JOptionPane.showMessageDialog(this, "Game Over!");
				break;
			case NUMBER:
			case BLANK:
				checkAndOpenNonMineSquares(x, y);
				break;
		}
	}

	private void checkAndOpenNonMineSquares(int row, int column)
	{
		Square square = squares[row][column];
		if (square.getState() == Square.State.CLOSED)
		{
			if (square.getType() != Square.SquareType.MINE)
			{
				openSquare(row, column);

				if (square.getType() == Square.SquareType.BLANK)
				{
					Set<Coordinates> surroundingSquares = this.getSurroundingCoordinates(row, column);

					for (Coordinates c : surroundingSquares)
					{
						checkAndOpenNonMineSquares(c.getX(), c.getY());
					}
				}
				checkWinningCondition();
			}
		}
	}

	private void onLeftClick(int x, int y)
	{
		if (squares[x][y].getState() == Square.State.CLOSED)
		{
			checkAndOpenSquare(x, y);
		}
	}

	private void onRightClick(int x, int y)
	{
		switch (squares[x][y].getState())
		{
			case CLOSED:
				squares[x][y].setState(Square.State.MARKED);
				break;
			case MARKED:
				squares[x][y].setState(Square.State.CLOSED);
				break;
		}
	}

	private void onMiddleClick(int x, int y)
	{
		System.out.println("Middle Click!");
		if (squares[x][y].getType() == Square.SquareType.NUMBER && squares[x][y].getState() == Square.State.OPEN)
		{
			Set<Coordinates> coordinates = this.getSurroundingCoordinates(x, y);
			Iterator<Coordinates> iterator = coordinates.iterator();

			int markedSquares = 0;
			while (iterator.hasNext())
			{
				Coordinates c = iterator.next();
				if (squares[c.getX()][c.getY()].getState() == Square.State.MARKED)
				{
					markedSquares++;
					iterator.remove();
				}
			}
			if (markedSquares == squares[x][y].getNumber())
			{
				for (Coordinates c : coordinates)
				{
					checkAndOpenSquare(c.getX(), c.getY());
				}
			}

		}
	}

	private Set<Coordinates> getSurroundingCoordinates(int row, int column)
	{
		Set<Coordinates> surroundingCoordinates = new HashSet<Coordinates>();

		if (row + 1 < ROWS)
		{
			surroundingCoordinates.add(new Coordinates(row + 1, column));
		}
		if (row - 1 >= 0)
		{
			surroundingCoordinates.add(new Coordinates(row - 1, column));
		}
		if (row + 1 < ROWS && column + 1 < COLUMNS)
		{
			surroundingCoordinates.add(new Coordinates(row + 1, column + 1));
		}
		if (row + 1 < ROWS && column - 1 >= 0)
		{
			surroundingCoordinates.add(new Coordinates(row + 1, column - 1));
		}
		if (column + 1 < COLUMNS)
		{
			surroundingCoordinates.add(new Coordinates(row, column + 1));
		}
		if (column - 1 >= 0)
		{
			surroundingCoordinates.add(new Coordinates(row, column - 1));
		}
		if (row - 1 >= 0 && column + 1 < COLUMNS)
		{
			surroundingCoordinates.add(new Coordinates(row - 1, column + 1));
		}
		if (row - 1 >= 0 && column - 1 >= 0)
		{
			surroundingCoordinates.add(new Coordinates(row - 1, column - 1));
		}

		return surroundingCoordinates;
	}

	private class ClickHandler extends MouseAdapter
	{
		private int x;
		private int y;

		public ClickHandler(int x, int y)
		{
			this.x = x;
			this.y = y;
		}

		@Override
		public void mouseClicked(MouseEvent e)
		{
			if (SwingUtilities.isMiddleMouseButton(e))
			{
				onMiddleClick(x, y);
			}
			else if (SwingUtilities.isLeftMouseButton(e))
			{
				onLeftClick(x, y);
			}
			else if (SwingUtilities.isRightMouseButton(e))
			{
				onRightClick(x, y);
			}

		}
	}
}
