/*
 * Pawn.java
 * 
 * Versions:
 * $Id:
 * 
 * Revisions:
 * $Log:
 * 
 */

import java.util.ArrayList;

/**
 * Pawn represents the pawn chess piece.
 * @author Quang Vu 
 *
 */
public class Pawn extends ChessPieces {
	
	private String ID;
	public Pawn(int row, int col){
		super(row, col);
		this.ID = "Pawn";
	}
	@Override
	public  ArrayList<ArrayList<ArrayList<ChessPieces>>> neighbor(int row, int col, ArrayList<ArrayList<ChessPieces>> board) {
		ArrayList<ArrayList<ArrayList<ChessPieces>>> neighbor = new ArrayList<ArrayList<ArrayList<ChessPieces>>>();
		
		//Eat another piece right diagonal.
		// Checking: - The position is available on the board
		//			 - The position has been occupied by another chess pieces.
		if((row-1) >= 0 && (col + 1) < board.get(row - 1).size()){
			if(!board.get(row - 1).get(col + 1).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>(); 
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				
				deepCopy.get(row - 1).set(col + 1, this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);				
			}
		}
		// Eat another piece left diagonal.
		// Checking: - The position is available on the board
				//			 - The position has been occupied by another chess pieces.
		if((row -1) >= 0 && (col - 1) >= 0){
			if(!board.get(row - 1).get(col - 1).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>(); 
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				
				deepCopy.get(row - 1).set(col - 1, this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);
			}
		}
		
		return neighbor;
		
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
