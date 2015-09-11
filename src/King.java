/*
 * King.java
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
 * King represents the King chess piece.
 * @author Quang Vu 
 *
 */
public class King extends ChessPieces{
	
	private String ID;
	
	public King(int row, int col){
		super(row,col);
		this.ID = "King";
		
	}
	@Override
	public  ArrayList<ArrayList<ArrayList<ChessPieces>>> neighbor(int row, int col, ArrayList<ArrayList<ChessPieces>> board) {
		ArrayList<ArrayList<ArrayList<ChessPieces>>> neighbor = new ArrayList<ArrayList<ArrayList<ChessPieces>>>();
		
		
		//1.Eat another piece right diagonal. 
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
		// 2.Eat another piece left diagonal.
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
		// 3.Eat another piece upward.
		// Checking: - The position is available on the board.
		//           - The position has been occupied by another chess pieces.
		if((row -1) >= 0){
			if(!board.get(row -1).get(col).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>(); 
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				deepCopy.get(row-1).set(col, this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);
			}
		}
		// 4.Eat another piece on the left side.
		// Same check as above.
		if((col - 1) >= 0){
			if(!board.get(row).get(col - 1).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>(); 
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				deepCopy.get(row).set(col-1, this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);
			}
		}
		// 5.Eat another piece on the right side.
		// Same check as above.
		if((col + 1) < board.get(row).size()){
			if(!board.get(row).get(col+1).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>(); 
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				deepCopy.get(row).set(col+1, this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);
			}
		}
		// 6.Eat another piece downward
		if((row + 1) < board.size()){
			if(!board.get(row+1).get(col).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>(); 
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				deepCopy.get(row + 1).set(col, this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);
			}
		}
		// 7.eat another piece South East
		if((row + 1) < board.size() && (col + 1) < board.get(row + 1).size()){
			if(!board.get(row+1).get(col+1).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>(); 
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				deepCopy.get(row + 1).set(col + 1, this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);
				
			}
		}
		// 8.eat another piece South West
		if((row+ 1)< board.size() && (col - 1) >= 0){
			if(!board.get(row + 1).get(col-1).getID().equals("Dot")){
				
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>(); 
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				deepCopy.get(row + 1).set(col - 1, this);
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
