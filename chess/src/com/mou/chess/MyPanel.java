package com.mou.chess;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	
	public MyPanel()
	{
		setLayout(new GridLayout(8, 8));
		setPreferredSize(new Dimension(550, 550));
		setMaximumSize(getPreferredSize()); // prevent growth
		setMinimumSize(getPreferredSize()); // prevent shrink
		
	}

}
