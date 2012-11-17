package minesweeper;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class Square extends JButton
{
	public enum State
	{
		MARKED, CLOSED, OPEN
	}

	public enum SquareType
	{
		MINE, BLANK, NUMBER;
	}

	private final SquareType type;

	private State state;

	private final int number;

	public Square(SquareType type, int number)
	{
		this.type = type;
		this.number = number;
		
		this.setBorder(new LineBorder(Color.BLACK, 1));
	}

	public int getNumber()
	{
		return number;
	}

	public State getState()
	{
		return state;
	}

	public SquareType getType()
	{
		return type;
	}

	public void setState(State state)
	{
		this.state = state;
		switch (state)
		{
			case MARKED:
				this.setBackground(Color.LIGHT_GRAY);
				this.setForeground(Color.BLACK);
				this.setText("!");
				break;
			case OPEN:
				this.setBackground(Color.WHITE);
				if (number > 0)
				{
					this.setForeground(Color.RED);
					this.setText("" + number);
				}
				else if (number < 0)
				{
					this.setText("*");
					this.setForeground(Color.BLACK);
				}
				break;
			case CLOSED:
				this.setBackground(Color.LIGHT_GRAY);
				this.setForeground(Color.BLACK);
				this.setText("");
				this.setEnabled(true);
				break;

		}
	}
}
