/*
 * Chess.java
 * 
 * Versions:
 * $Id:
 * 
 * Revisions:
 * $Log:
 * 
 */
// Any imports go here.
import java.util.ArrayList;

/**
 * Dot represents the empty space of the chess. 
 * Even though it represent a chess piece, but it does not do anything.
 * @author Quang Vu 
 *
 */

public class Dot extends ChessPieces {

	private String ID;
	
	public Dot(int row, int col){
		super(row, col);
		this.ID = "Dot";
	}
	public  ArrayList<ArrayList<ArrayList<ChessPieces>>> neighbor(int row, int col, ArrayList<ArrayList<ChessPieces>> board) {
		return null;
	}
	
	public String getID() {
		return ID;
	}
	@Override
	public int getRow() {
		return row;
	}
	@Override
	public int getCol() {
		return col;
	}

}
		

