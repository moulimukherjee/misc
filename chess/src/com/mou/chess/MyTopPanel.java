package com.mou.chess;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyTopPanel extends JPanel {
	
	private JButton newGame;
	private JButton saveGame;
	private JButton loadGame;
	private JButton undoMove;
	private JLabel lblField;
	private JTextField txtField;
	
	public MyTopPanel()
	{
		//setPreferredSize(new Dimension(100, 500));
		setMaximumSize(getPreferredSize()); // prevent growth
		setMinimumSize(getPreferredSize()); // prevent shrink
		
		setLayout(new FlowLayout());
				
		newGame = new JButton("New");
		saveGame = new JButton("Save");
		loadGame = new JButton("Load");
		lblField = new JLabel("Status:");
		txtField = new JTextField("New Game. Next move of: "+Board.getGame().getNextMove(), 20);
		undoMove = new JButton("Undo");
		
		txtField.setEditable(false);
		
		add(newGame);
		add(saveGame);
		add(loadGame);
		add(lblField);
		add(txtField);
		add(undoMove);
		
		undoMove.setEnabled(false);
		
		newGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to discard the current game and start a new one?", "New Game", JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.OK_OPTION)
					Board.INSTANCE.startNewGame(null);
			}
		});
		
		saveGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				final JFileChooser fc = new JFileChooser();
				File fileName;
				fc.setDialogTitle("Save");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Chess Game files (.ser)", "ser");
				fc.setFileFilter(filter);
				int result = fc.showOpenDialog(Board.INSTANCE);
				fileName = fc.getSelectedFile ();
			    if( ( fileName == null ) || ( fileName.getName().equals( "" ) ) )
				{
					// display error message
					JOptionPane.showMessageDialog( null, "Enter a valid File Name", "Invalid File Name", JOptionPane.ERROR_MESSAGE );
					return;
				}
			    String[] splits = fileName.getName().split( "\\."); 
				String extension = splits[ splits.length - 1 ]; 
				if( !extension.equals( "ser" ) ) // Not "ser" type
				{
					// display error message
					JOptionPane.showMessageDialog( null, "It is only allowed to save a file in \".ser\" type.", "Invalid File Type", JOptionPane.ERROR_MESSAGE );
				}
				else
				{
					if( fileName.exists() ) // if the file is already existed
					{
						// get the response from the JOptionPane
						int response = JOptionPane.showConfirmDialog ( null, "Overwrite existing file?", "Confirm Overwrite", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );
						// if the user clicked NO or CLOSE
						if ( ( response == JOptionPane.NO_OPTION )||
	                                         ( response == JOptionPane.CLOSED_OPTION ) )
						{
							return; // do nothing
						}
					}
					// open file for output
					try {
						ObjectOutputStream output =  new ObjectOutputStream( new FileOutputStream( fileName ) );
						output.writeObject(Board.getGame());
						output.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			      
			       
			    }

		});
		
		loadGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				final JFileChooser fc = new JFileChooser();
				File fileName;
				fc.setDialogTitle("Save");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Chess Game files  (.ser)", "ser");
				fc.setFileFilter(filter);
				int result = fc.showOpenDialog(Board.INSTANCE);
				fileName = fc.getSelectedFile ();
			    if( ( fileName == null ) || ( fileName.getName().equals( "" ) ) )
				{
					// display error message
					JOptionPane.showMessageDialog( null, "Invalid File Name", "Invalid File Name", JOptionPane.ERROR_MESSAGE );
					return;
				}
			    String[] splits = fileName.getName().split( "\\."); 
				String extension = splits[ splits.length - 1 ]; 
				if( !extension.equals( "ser" ) ) // Not "ser" type
				{
					// display error message
					JOptionPane.showMessageDialog( null, "It is only allowed to open a file in \".ser\" type.", "Invalid File Type", JOptionPane.ERROR_MESSAGE );
				}
				else
				{
					if( fileName.exists() ) // if the file is already existed
					{
						try {
							ObjectInputStream input =  new ObjectInputStream( new FileInputStream( fileName ) );
							Game game = (Game)input.readObject();
							Board.INSTANCE.startNewGame(game);
							input.close();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			      
			       
			}
		});
		undoMove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				Game game = Board.INSTANCE.getGame();
				if(game.getHistorySize()>=1)
				{
					game.removeLastHistory();
					if(game.getHistorySize()==1)
					{
						undoMove.setEnabled(false);
					}
					
					Board.INSTANCE.startNewGame(game);
					
				}
				else
				{
					Board.showStatus("Error: No moves made yet");
				}
			}
		});
	}

	public JLabel getLblField() {
		return lblField;
	}

	public void setLblField(String lblField) {
		this.lblField.setText(lblField);
	}

	public JTextField getTxtField() {
		return txtField;
	}

	public void setTxtField(String txtField) {
		this.txtField.setText(txtField);
	}
	
	public JButton getUndoButton()
	{
		return this.undoMove;
	}
	
}
