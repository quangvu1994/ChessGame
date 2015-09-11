/*
 * Knight.java
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
 * Knight represents the Knight chess piece.
 * @author Quang Vu 
 *
 */
public class Knight extends ChessPieces{
	
	private String ID;
	
	public Knight(int row, int col){
		super(row, col);
		this.ID = "Knight";	
	}
	@Override
	public  ArrayList<ArrayList<ArrayList<ChessPieces>>> neighbor(int row, int col, ArrayList<ArrayList<ChessPieces>> board) {
		ArrayList<ArrayList<ArrayList<ChessPieces>>> neighbor = new ArrayList<ArrayList<ArrayList<ChessPieces>>>();
		
		// Move North - Right.
		if((row - 2) >= 0 && (col + 1) < board.get(row - 2).size()){
			if(!board.get(row-2).get(col+1).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>();
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				
				deepCopy.get(row-2).set(col+ 1, this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);
			}
		}
		// Move North - Left.
		if((row -2) >= 0 && (col -1) >=0){
			if(!board.get(row-2).get(col - 1).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>();
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				
				deepCopy.get(row-2).set(col-1, this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);
			}
		}
		// Move West  - Upper.
		if((col - 2) >= 0 && (row - 1) >=0){
			if(!board.get(row - 1).get(col -2).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>();
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				
				deepCopy.get(row-1).set(col-2, this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);
			}
		}
		// Move West - Down.
		if((col - 2) >=0 && (row + 1) < board.size()){
			if(!board.get(row + 1).get(col - 2).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>();
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				
				deepCopy.get(row + 1).set(col - 2, this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);
			}
		}
		
		// Move South - Left.
		if((row + 2) < board.size() && (col - 1) >= 0){
			if(!board.get(row + 2).get(col - 1).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>();
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				
				deepCopy.get(row + 2).set( col - 1,  this);
				deepCopy.get(row).set(col,  new Dot(row, col));
				neighbor.add(deepCopy);
			}
		}
		// move South - right.
		if((row + 2) < board.size() && (col + 1) < board.get(row + 2).size()){
			if(!board.get(row + 2).get(col + 1).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>();
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				
				deepCopy.get(row+2).set(col+1,  this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);
			}
		}
		// move East - Down.
		if((row + 1) < board.size() && (col + 2) < board.get(row + 1).size()){
			if(!board.get(row+1).get(col+2).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>();
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				
				deepCopy.get(row+1).set(col + 2, this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);
			}
		}
		// move East - Upper.
		if((row - 1) >= 0 && (col + 2) < board.get(row-1).size()){
			if(!board.get(row-1).get(col+2).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>();
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				
				deepCopy.get(row - 1).set(col+2, this);
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
