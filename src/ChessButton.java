/*
 * ChessButton.java
 * 
 * Versions:
 * $Id:
 * 
 * Revisions:
 * $Log:
 * 
 */

import javax.swing.JButton;


public class ChessButton extends JButton{
	
	private int rowPosition;
	private int colPosition;
	
	public ChessButton(int rowPostion, int colPosition){
		this.rowPosition = rowPostion;
		this.colPosition = colPosition;
	}
	
	public int getRowPosition(){
		return rowPosition;
	}
	public int getColPosition(){
		return colPosition;
	}
	
	
}
