/*
 * ChessPieces.java
 * 
 * Versions:
 * $Id:
 * 
 * Revisions:
 * $Log:
 * 
 */

// Any imports go here
import java.util.*;

/**
 * ChessPieces is a super class that represent the most general type of every chess pieces.
 * @author Quang Vu 
 *
 */
public abstract class ChessPieces {
	
	protected int row;
	protected int col;
	
	public ChessPieces(int row, int col){
		this.row = row;
		this.col = col;
	}
	/**
	 * neighbor function get all possible move of the bishop base on the current board.
	 */
	public abstract ArrayList<ArrayList<ArrayList<ChessPieces>>> neighbor(int row, int col, ArrayList<ArrayList<ChessPieces>> board);
	/**
	 * getID return the ID of the piece.
	 */
	public abstract String getID();
	/**
	 * getRow return the current row of the piece.
	 */
	public abstract int getRow();
	/**
	 * getCol return the current column of the piece.
	 */
	public abstract int getCol();
}
